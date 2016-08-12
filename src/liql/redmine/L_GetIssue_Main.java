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

			// 当前遗留所有未关闭 && 不是里程碑issue
//			L_GetIssue_ALL.getCurrentAllNoneClosedissues(mgr);

//			// 完成日期小于今天 && 状态不是已关闭
//			L_LOG.OUT_Nece("getCurrentDelayissues");
//			L_GetIssue_Delay.getCurrentDelayissues(mgr);
//
//			// 已关闭issue
//			L_LOG.OUT_Nece("getYesterdayClosedIssues");
//			L_GetIssue_Closed.getYesterdayClosedIssues(mgr, curdate);
//
//			// 新建issue
//			L_LOG.OUT_Nece("getYesterdayCreatedIssues");
//			L_GetIssue_Created.getYesterdayCreatedIssues(mgr, curdate);
//
//			// 更新issue
//			L_LOG.OUT_Nece("getYesterdayUpdatedIssues");
//			L_GetIssue_Updated.getYesterdayUpdatedIssues(mgr, curdate);

//			// 重点关注issue 目前没有关闭 && 没有完成时间 && 完成时间距离创建时间超过15天
//			L_LOG.OUT_Nece("getFocusIssues");
//			L_GetIssue_Focus.getFocusIssues(mgr);

			// 统计人员最后一次在协同工作的时间
			L_LOG.OUT_Nece("getFocusIssues");
			L_GetIssue_LastWorkTime.getFocusIssues(mgr);

			// 压缩文件
			L_LOG.OUT_Nece("zip");
			L_ZIPCompress.zip(L_Security.ZIPFILE, L_Security.BASEDIR);

			// // 发送邮件
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
