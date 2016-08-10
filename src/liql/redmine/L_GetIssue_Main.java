package liql.redmine;

import java.util.Calendar;
import java.util.Date;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

import liql.util.L_Util;
import nosubmit.L_Security;

public class L_GetIssue_Main {
	public static void main(String[] args) {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);

		// 当前遗留所有未关闭 && 不是里程碑issue
//		L_GetIssue_ALL.getCurrentAllNoneClosedissues(mgr);

		// 完成日期小于今天 && 状态不是已关闭
		L_GetIssue_Delay.getCurrentDelayissues(mgr);

		// 已关闭issue
		Calendar calc = Calendar.getInstance();
		calc.setTime(new Date());
		calc.add(Calendar.DATE, -1);
		Date curd = calc.getTime();
		String curdate = L_Util.fmt_YYYYMMDD(curd);
		
		L_GetIssue_Closed.getYesterdayClosedIssues(mgr,curdate);

		// 新建issue
		L_GetIssue_Created.getYesterdayCreatedIssues(mgr,curdate);

		// 更新issue
		L_GetIssue_Updated.getYesterdayUpdatedIssues(mgr,curdate);

		
	}
}
