package liql.redmine;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

import nosubmit.L_Security;

public class L_GetIssue_Main {
	public static void main(String[] args) {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);

		// 当前遗留所有未关闭 && 不是里程碑issue
		L_GetIssue_ALL.getCurrentAllNoneClosedissues(mgr);

		// 昨日已关闭issue
		L_GetIssue_Closed.getYesterdayClosedIssues(mgr);

		// 昨日新建issue
		L_GetIssue_Created.getYesterdayCreatedIssues(mgr);

		// 昨日更新issue
		L_GetIssue_Updated.getYesterdayUpdatedIssues(mgr);

		// 完成日期小于今天 && 状态不是已关闭
		L_GetIssue_Delay.getCurrentDelayissues(mgr);

	}
}
