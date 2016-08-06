package liql.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNStatusClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import nosubmit.L_Security;

public class TestSVN {

	public static void main(String[] args) throws Exception {

		// ��ʼ���汾��
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();

		// ����������
		SVNRepository repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(L_Security.SVN_Root));

		// �����֤
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(L_Security.SVN_username,
				L_Security.SVN_password);

		// ���������֤������
		repository.setAuthenticationManager(authManager);

		DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
		SVNClientManager clientManager = SVNClientManager.newInstance(options, authManager);

		SVNStatusClient sc = clientManager.getStatusClient();

		long lastver = repository.getLatestRevision();
		System.out.println(lastver);

		// String dir = "D:\\uxunsvn\\jinzhoubankepay\\doc\\�����ĵ�";
		//
		// File filedir = new File(dir);
		// String[] filelist = filedir.list();
		// for (int i = 0; i < filelist.length; i++) {
		// File file = new File(dir + "\\" + filelist[i]);
		// SVNStatus fs = sc.doStatus(new File(file.getAbsolutePath()), true);
		// System.out.println(
		// "last version : " + fs.getCommittedRevision() + ";commit date : " +
		// fs.getCommittedDate() + ";");
		// }

		filterCommitHistoryTest(repository);

	}

	public static void filterCommitHistoryTest(SVNRepository repository) throws Exception {
		// ��������
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		final Date begin = format.parse("2016-08-04");
		final Date end = format.parse("2016-08-14");
		final String author = "";
		long startRevision = 0;
		long endRevision = -1;// ��ʾ���һ���汾
		final List<String> history = new ArrayList<String>();
		// String[] Ϊ���˵��ļ�·��ǰ׺��Ϊ�ձ�ʾ�����й���
		repository.log(new String[] { "" }, startRevision, endRevision, true, true, new ISVNLogEntryHandler() {
			@Override
			public void handleLogEntry(SVNLogEntry svnlogentry) throws SVNException {
				// �����ύʱ����й���
				// if (svnlogentry.getDate().after(begin) &&
				// svnlogentry.getDate().before(end)) {
				// // �����ύ�˹���
				// if (!"".equals(author)) {
				// if (author.equals(svnlogentry.getAuthor())) {
				// fillResult(svnlogentry);
				// }
				// } else {
				// fillResult(svnlogentry);
				// }
				// }
				
				
//				System.out.println(svnlogentry.getRevision() + "\t" + svnlogentry.getAuthor() + "\t"
//						+ svnlogentry.getChangedPaths().keySet() + "\t" + svnlogentry.getMessage());
//				System.out.println(svnlogentry.getRevisionProperties());
				System.out.println("version : ["+svnlogentry.getRevision()+"]" + " properties : ["+svnlogentry.getRevisionProperties()+"]" + svnlogentry.getChangedPaths().keySet());
			}

			public void fillResult(SVNLogEntry svnlogentry) {
				// getChangedPathsΪ�ύ����ʷ��¼MAP keyΪ�ļ�����valueΪ�ļ�����
				history.addAll(svnlogentry.getChangedPaths().keySet());
			}
		});
		for (String path : history) {
			System.out.println(path);
		}
	}
}
