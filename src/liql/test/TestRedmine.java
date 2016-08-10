package liql.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

import nosubmit.L_Security;

public class TestRedmine {
	public static void main(String[] args) throws RedmineException {
		// http://www.redmine.org/projects/redmine/wiki/Rest_api
		// http://www.redmine.org/projects/redmine/wiki/Rest_api_with_java
		// https://github.com/taskadapter/redmine-java-api
		// http://www.redmine.org/projects/redmine/wiki/Rest_Issues

		/**
		 * ǰһ����������&����N��δ���������&��Ŀ��׼��̱�
		 */

		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);
		// project info
		List<Project> projects = mgr.getProjectManager().getProjects();
		for (Project project : projects) {
			System.out.println(project.getName() + ";" + project.getId());
		}
		
		
//		project_id
//		tracker_id
//		status_id
//		priority_id
//		subject
//		description
//		category_id
//		fixed_version_id - ID of the Target Versions (previously called 'Fixed Version' and still referred to as such in the API)
//		assigned_to_id - ID of the user to assign the issue to (currently no mechanism to assign by name)
//		parent_issue_id - ID of the parent issue
//		custom_fields - See Custom fields
//		watcher_user_ids - Array of user ids to add as watchers (since 2.3.0)
//		is_private - Use true or false to indicate whether the issue is private or not
//		estimated_hours - Number of hours estimated for issue
		
		HashMap<String, String> param = new HashMap<String,String>();
		param.put("project_id", "40");
		param.put("status_id", "5");
		
		
		// issue info
		List<Issue> issues = mgr.getIssueManager().getIssues(param);// Redmine��Ŀ���
		for (Issue issue : issues) {
			
//			//get all 100%
//			if(issue.getDoneRatio() != 100){
//				continue;
//			}
//			
//			if(issue.getTracker().getId() == 4){
//				continue;
//			}
			
			// System.out.println("�����Ҫ:" + issue.toString());
			System.out.println("������Ŀ:" + issue.getProject().getName());
			System.out.println("����ID:" + issue.getId());
			System.out.println("����:" + issue.getSubject());
			System.out.println("����:" + issue.getTracker().getName());// 1-BUG��2-����3-֧�֣�4-��̱���6-����
			System.out.println(issue.getParentId());
			System.out.println("״̬:" + issue.getStatusId() + issue.getStatusName());// 1-�½���2-�����У�3-�ѽ����4-������5-�ѹرգ�6-�Ѿܾ�
			System.out.println("���ȼ�:" + issue.getPriorityId() + issue.getPriorityText());// 1-�ͣ�2-��ͨ��3-�ߣ�4-������5-����
			System.out.println("����ɰٷֱ�:" + issue.getDoneRatio());// % Done����ɰٷֱ�
			System.out.println("������:" + issue.getAuthor());
			System.out.println("ָ����:" + issue.getAssigneeName());
			System.out.println("��������:" + fmt_YYYYMMDD(issue.getCreatedOn()));// ����ʱ��
			System.out.println("��ʼ����:" + fmt_YYYYMMDD(issue.getStartDate()));// ��ʼʱ��
			System.out.println("������ʱ��:" + fmt_YYYYMMDD(issue.getUpdatedOn()));// ������ʱ��
			System.out.println("�ƻ��������:" + fmt_YYYYMMDD(issue.getDueDate()));// ���ʱ��
			
			System.out.println();
			System.out.println("�ر�����:" + fmt_YYYYMMDD(issue.getClosedOn()));//�ر�ʱ��
			System.out.println("����:" + issue.getDescription());
			System.out.println("#####################################################################");
			
			
//			issue.setStatusId(5);
//			issue.setClosedOn(new Date());
//			issue.setNotes("Ӧ�ų���Ҫ��������̱���Эͬ�����������ɰٷֱ���100%����״̬�޸�Ϊ�Թر�");
//			mgr.getIssueManager().update(issue);
			
		}

	}

	public static String fmt_YYYYMMDD(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
}
