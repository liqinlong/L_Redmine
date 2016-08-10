package liql.redmine;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

import nosubmit.L_Security;

public class L_GetIssue_Main {
	public static void main(String[] args) {
		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);

		// ��ǰ��������δ�ر� && ������̱�issue
		L_GetIssue_ALL.getCurrentAllNoneClosedissues(mgr);

		// �����ѹر�issue
		L_GetIssue_Closed.getYesterdayClosedIssues(mgr);

		// �����½�issue
		L_GetIssue_Created.getYesterdayCreatedIssues(mgr);

		// ���ո���issue
		L_GetIssue_Updated.getYesterdayUpdatedIssues(mgr);

		// �������С�ڽ��� && ״̬�����ѹر�
		L_GetIssue_Delay.getCurrentDelayissues(mgr);

	}
}
