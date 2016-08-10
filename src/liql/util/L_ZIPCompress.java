package liql.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class L_ZIPCompress {


	public static void zip(String zipFileName, String filename) throws Exception {
		L_LOG.OUT_Nece("start zip...");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		File inputFile = new File(filename);
		zip(out, inputFile, inputFile.getName(), bo);
		bo.close();
		out.close(); // ������ر�
		L_LOG.OUT_Nece("end zip...");
	}

	private static void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo) throws Exception { // ��������
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			if (fl.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/")); // ����zipѹ�������base
			}
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + "/" + fl[i].getName(), bo); // �ݹ�������ļ���
			}
		} else {
			out.putNextEntry(new ZipEntry(base)); // ����zipѹ�������base
			FileInputStream in = new FileInputStream(f);
			BufferedInputStream bi = new BufferedInputStream(in);
			int b;
			while ((b = bi.read()) != -1) {
				bo.write(b); // ���ֽ���д�뵱ǰzipĿ¼
			}
			bo.flush();
			bi.close();
			in.close(); // �������ر�
		}
	}
}
