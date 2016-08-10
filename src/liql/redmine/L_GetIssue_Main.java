package liql.redmine;

import java.util.Calendar;
import java.util.Date;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

import liql.util.L_Mail;
import liql.util.L_Util;
import liql.util.L_ZIPCompress;
import nosubmit.L_Security;

public class L_GetIssue_Main {
	public static String curdate = null;
	static {
		Calendar calc = Calendar.getInstance();
		calc.setTime(new Date());
		calc.add(Calendar.DATE, -1);
		Date curd = calc.getTime();
		curdate = L_Util.fmt_YYYYMMDD(curd);
	}

	public static void main(String[] args) throws Exception {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);

		// ��ǰ��������δ�ر� && ������̱�issue
		// L_GetIssue_ALL.getCurrentAllNoneClosedissues(mgr);

		// �������С�ڽ��� && ״̬�����ѹر�
		L_GetIssue_Delay.getCurrentDelayissues(mgr);

		// �ѹر�issue
		L_GetIssue_Closed.getYesterdayClosedIssues(mgr, curdate);

		// �½�issue
		L_GetIssue_Created.getYesterdayCreatedIssues(mgr, curdate);

		// ����issue
		L_GetIssue_Updated.getYesterdayUpdatedIssues(mgr, curdate);

		// ѹ���ļ�
		L_ZIPCompress.zip(L_Security.ZIPFILE, L_Security.BASEDIR);
		
		// �����ʼ�
		L_Mail.sendMail(L_Security.ZIPFILE);
		
	}
}
