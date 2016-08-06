package liql.svn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNProperties;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import nosubmit.L_Security;

public class L_GetSvnLastVersion {
	public static void main(String[] args) {

		String tt = "/jinzhoubankjfy/src";
		System.err.println(tt.indexOf("/src/"));
//		if(true)return;
		
		try {// 初始化版本库
			DAVRepositoryFactory.setup();
			SVNRepositoryFactoryImpl.setup();
			FSRepositoryFactory.setup();

			// 创建库连接
			SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(L_Security.SVN_Root));

			// 身份验证
			ISVNAuthenticationManager authManager = SVNWCUtil
					.createDefaultAuthenticationManager(L_Security.SVN_username, L_Security.SVN_password);

			// 创建身份验证管理器
			repository.setAuthenticationManager(authManager);

			getLastCommitDateByProject(repository);
		} catch (SVNException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getLastCommitDateByProject(SVNRepository repository) throws SVNException, IOException {
		HashMap<String, LinkedList<SVNRowData>> retMap = new HashMap<String, LinkedList<SVNRowData>>();

		long endver = -1;
		int latestnums = 5;

		repository.log(new String[] { "" }, 0, endver, true, true, new ISVNLogEntryHandler() {
			String svnproname = "";
			String svnbashurl = "";

			public void handleLogEntry(SVNLogEntry svnlogentry) throws SVNException {

				SVNProperties svnpros = svnlogentry.getRevisionProperties();
				if (null != svnpros.getStringValue("svn:sync-from-url")) {
					svnbashurl = svnlogentry.getRevisionProperties().getStringValue("svn:sync-from-url");
				} else {
					Iterator<String> itkeys = svnlogentry.getChangedPaths().keySet().iterator();

					boolean issrc = false;

					while (itkeys.hasNext()) {
						issrc = false;
						String key = (String) itkeys.next();
						if (key.indexOf("/src/") != -1) {
							System.out.println(key);
							String[] tmparr = key.split("/");
							svnproname = tmparr[1];
							issrc = true;
							break;
						}
					}

					if (issrc) {
						LinkedList<SVNRowData> latestRow = null;
						String key = svnbashurl + "/" + svnproname;
						if (null == retMap.get(key)) {
							latestRow = new LinkedList<SVNRowData>();
						} else {
							latestRow = retMap.get(key);
						}

						latestRow.add(
								new SVNRowData(svnpros.getStringValue("svn:log"), svnpros.getStringValue("svn:author"),
										svnpros.getStringValue("svn:date"), svnlogentry.getRevision()));
						retMap.put(key, latestRow);
					}
				}
			}

		});

		String OUTDIR = "outfile" + File.separator;
		String LASTFIX = ".txt";
		File outf = new File(OUTDIR + "svn_commit_log" + LASTFIX);
		StringBuffer buff = new StringBuffer();
		Iterator<String> outkey = retMap.keySet().iterator();
		while (outkey.hasNext()) {
			String key = (String) outkey.next();

			LinkedList<SVNRowData> outrows = retMap.get(key);
			Collections.reverse(outrows);

			System.out.println("===========================" + key + "\t total commit : " + outrows.size()
					+ "===========================");
			buff.append("===========================" + key + "\t total commit : " + outrows.size()
					+ "===========================").append("\n");
			int i = 0;
			Iterator<SVNRowData> rowit = outrows.iterator();
			while (rowit.hasNext()) {
				i++;
				if (i > latestnums)
					break;

				SVNRowData srd = (SVNRowData) rowit.next();
				// System.out.println(key + "\t" + srd.getVersion() + "\t" +
				// srd.getCommitauthor() + "\t"
				// + srd.getCommitdate() + "\t" + srd.getCommitlog());

				buff.append(key + "\t" + srd.getVersion() + "\t" + srd.getCommitauthor() + "\t" + srd.getCommitdate()
						+ "\t" + srd.getCommitlog()).append("\n");

			}
		}

		if (!outf.exists())
			outf.createNewFile();

		BufferedWriter bw = new BufferedWriter(new FileWriter(outf));
		bw.write(buff.toString());
		bw.close();
	}

}
