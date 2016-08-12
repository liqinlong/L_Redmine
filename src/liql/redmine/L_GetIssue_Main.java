package liql.redmine;

import java.util.Calendar;
import java.util.Date;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

import liql.util.L_LOG;
import liql.util.L_Util;
import liql.util.L_ZIPCompress;
import nosubmit.L_Security;

public class L_GetIssue_Main {
	public static String curdate = null;
	public static final String offset = "0";
	public static final String limit = "10000";
	
	static {
		Calendar calc = Calendar.getInstance();
		calc.setTime(new Date());
		calc.add(Calendar.DATE, -1);
		Date curd = calc.getTime();
		curdate = L_Util.fmt_YYYYMMDD(curd);
	}

	public static void StartAll() throws Exception {
		try {
			RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);

			// ��ǰ��������δ�ر� && ������̱�issue
//			L_GetIssue_ALL.getCurrentAllNoneClosedissues(mgr);

//			// �������С�ڽ��� && ״̬�����ѹر�
//			L_LOG.OUT_Nece("getCurrentDelayissues");
//			L_GetIssue_Delay.getCurrentDelayissues(mgr);
//
//			// �ѹر�issue
//			L_LOG.OUT_Nece("getYesterdayClosedIssues");
//			L_GetIssue_Closed.getYesterdayClosedIssues(mgr, curdate);
//
//			// �½�issue
//			L_LOG.OUT_Nece("getYesterdayCreatedIssues");
//			L_GetIssue_Created.getYesterdayCreatedIssues(mgr, curdate);
//
//			// ����issue
//			L_LOG.OUT_Nece("getYesterdayUpdatedIssues");
//			L_GetIssue_Updated.getYesterdayUpdatedIssues(mgr, curdate);

//			// �ص��עissue Ŀǰû�йر� && û�����ʱ�� && ���ʱ����봴��ʱ�䳬��15��
//			L_LOG.OUT_Nece("getFocusIssues");
//			L_GetIssue_Focus.getFocusIssues(mgr);

			// ͳ����Ա���һ����Эͬ������ʱ��
			L_LOG.OUT_Nece("getFocusIssues");
			L_GetIssue_LastWorkTime.getFocusIssues(mgr);

			// ѹ���ļ�
			L_LOG.OUT_Nece("zip");
			L_ZIPCompress.zip(L_Security.ZIPFILE, L_Security.BASEDIR);

			// // �����ʼ�
			// L_LOG.OUT_Nece("sendMail");
			// L_Mail.sendMail(L_Security.ZIPFILE);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		StartAll();
	}
}
