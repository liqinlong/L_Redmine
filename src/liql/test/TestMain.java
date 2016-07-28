package liql.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

import nosubmit.L_Security;

public class TestMain {
	public static void main(String[] args) throws RedmineException {
		// http://www.redmine.org/projects/redmine/wiki/Rest_api
		// http://www.redmine.org/projects/redmine/wiki/Rest_api_with_java
		// https://github.com/taskadapter/redmine-java-api

		/**
		 * ǰһ����������&����N��δ���������&��Ŀ��׼��̱�
		 */

		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);

		// project info
		List<Project> projects = mgr.getProjectManager().getProjects();
		for (Project project : projects) {
			System.out.println(project.getName() + ";" + project.getId());
		}

		// issue info
		List<Issue> issues = mgr.getIssueManager().getIssues("112", null);// Redmine��Ŀ���
		for (Issue issue : issues) {
			// System.out.println("�����Ҫ:" + issue.toString());
			System.out.println("������Ŀ:" + issue.getProject().getName());
			System.out.println("����ID:" + issue.getId());
			System.out.println("����:" + issue.getSubject());
			System.out.println("����:" + issue.getTracker().getName());// 1-BUG��2-����3-֧�֣�4-��̱���6-����
			System.out.println("״̬:" + issue.getStatusId() + issue.getStatusName());// 1-�½���2-�����У�3-�ѽ����4-������5-�ѹرգ�6-�Ѿܾ�
			System.out.println("���ȼ�:" + issue.getPriorityId() + issue.getPriorityText());// 1-�ͣ�2-��ͨ��3-�ߣ�4-������5-����
			System.out.println("����ɰٷֱ�:" + issue.getDoneRatio());// % Done����ɰٷֱ�
			// System.out.println(issue.getEstimatedHours());// Ԥ�ƺ�ʱ
			// System.out.println(issue.getSpentHours());
			System.out.println("������:" + issue.getAuthor());
			System.out.println("ָ����:" + issue.getAssigneeName());
			System.out.println("��������:" + fmt_YYYYMMDD(issue.getCreatedOn()));// ����ʱ��
			System.out.println("��ʼ����:" + fmt_YYYYMMDD(issue.getStartDate()));// ��ʼʱ��
			System.out.println("������ʱ��:" + fmt_YYYYMMDD(issue.getUpdatedOn()));// ������ʱ��
			System.out.println("�ƻ��������:" + fmt_YYYYMMDD(issue.getDueDate()));// ���ʱ��
			// System.out.println(fmt_YYYYMMDD(issue.getClosedOn()));//�ر�ʱ��
			System.out.println("����:" + issue.getDescription());
			System.out.println("#####################################################################");
		}

	}

	public static String fmt_YYYYMMDD(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
}
