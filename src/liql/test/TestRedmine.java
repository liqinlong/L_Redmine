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
import com.taskadapter.redmineapi.bean.User;

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
		// ===================================================all projects
		// id==========================================================
		// 0-��Ѷ������Ϣ��Դ;1
		// 1.A.8-�ֻ�Ǯ��(TIFA);143
		// 1.G-֧����Ʒ��������;184
		// 1.U-����������������;178
		// 2-��Ŀʵʩ�����;4
		// 2.+��׼��ʵʩ�ĵ�;128
		// 2.+.1-����ʵʩ��׼���ĵ�;129
		// 2.+.2-֧��ʵʩ��׼���ĵ�;130
		// 2.+.3-����ʵʩ��׼�ĵ�;131
		// 2.0-�ۺϻ���ƽ̨;11
		// 2.0.0-��Ӫ���л�����;24
		// 2.0.1-��Ӫ���̴������л�����;25
		// 2.0.2-�������л�����;26
		// 2.0.2.0-�������л������̻�����ƽ̨;94
		// 2.0.3-�������л�����;27
		// 2.0.4-�������л�����;28
		// 2.0.5-�������л�����;29
		// 2.0.6-�������л�����;30
		// 2.0.6.1-�������л����׿ͻ���;53
		// 2.0.7-�㽭��̩��ҵ���л�����;31
		// 2.0.8-������ͨũ���л�����;33
		// 2.0.9-��֦�����л�����;34
		// 2.0.A-��������ũ���л�����;35
		// 2.0.B-�������л�����;36
		// 2.0.D-̩�����л�����;38
		// 2.0.F-�������л�����;40
		// 2.0.G-��������ũ���л�����;41
		// 2.0.H-��������ũ���л�����;42
		// 2.0.I-�������л�����;43
		// 2.0.J-��³���л�����Ŀ;99
		// 2.0.K-�������л�����;100
		// 2.0.L-���ũ���л�������Ŀ;104
		// 2.0.M-Ң��ũ�����ۺϻ���;107
		// 2.0.N-��ׯ�����ۺϻ���;110
		// 2.0.O-���������ۺϻ���;111
		// 2.0.P-���������ۺϻ���;114
		// 2.0.R-���������ۺϻ���;132
		// 2.0.S-���������ۺϻ���;135
		// 2.0.T-����ũ�����ۺϻ���;138
		// 2.0.U-���������ۺϻ���;147
		// 2.0.V-���������ۺϻ���ϵͳ;176
		// 2.0.W-����ũ����ҵ�����ۺϻ���;193
		// 2.0.X-��ԭ���л����̳�;194
		// 2.0.Y-���ũ�����ۺϻ���;195
		// 2.1-֧��ƽ̨;12
		// 2.1.0-����������ά�׸�;46
		// 2.1.1-��������֧��ƽ̨;101
		// 2.1.2-��������֧������;102
		// 2.1.3-��ɽ����֧��������Ŀ;108
		// 2.1.4-��������֧����Ŀ;109
		// 2.1.5-��������֧��ƽ̨;112
		// 2.1.6-�ൺũ������֧����Ŀ;144
		// 2.1.7-��������֧����Ŀ;145
		// 2.1.9-��������УѶͨ�͸�У�ɷ���Ŀ;175
		// 2.1.A-��������ͳһ֧��ƽ̨;177
		// 2.1.B-ʯ��ɽ���л�����֧����Ŀ;185
		// 2.1.C-��֦������ҵ����ͳһ֧��ƽ̨;192
		// 2.2-����ƽ̨;13
		// 2.2.0-����������ά�̳�;47
		// 2.2.0.1-����������ά�̳ǿͻ���;52
		// 2.2.1-�������н����̳�;48
		// 2.2.2-��������o2oӦ���豸;51
		// 2.2.3-�������е���ƽ̨;65
		// 2.2.4-�������е���ƽ̨;113
		// 2.2.5-�������е���ƽ̨;133
		// 2.3-p2bͶ����ƽ̨;5
		// 2.3.1-ɽ������ũ����P2B;6
		// 2.4-eסe��;14
		// 2.4.1-���������³�;137
		// 2.6-����ƽ̨;16
		// 2.7-�绰����;17
		// 2.A-��Ѷ��������С�ն�;21
		// 2.A.1-̩����������С�ն�;85
		// 2.C-������֧����������;20
		// 2.C.1-�������вƸ�ͨ���;44
		// 2.C.2-�������вƸ�ͨ���;103
		// 2.C.3-�������вƸ�ͨ���;140
		// 2.C.4-�������о������;141
		// 2.D-ũ����Ȩ��ת����ƽ̨;98
		// 2.D.0-������Ȩ��Ŀ;139
		// 2.Q-������ҵӦ��;172
		// 2.Q.1-���ڿյ���Ŀ;173
		// 2.Z-PMM����;81
		// 6.1-������������;180

		// project_id
		// tracker_id
		// status_id
		// priority_id
		// subject
		// description
		// category_id
		// fixed_version_id - ID of the Target Versions (previously called
		// 'Fixed Version' and still referred to as such in the API)
		// assigned_to_id - ID of the user to assign the issue to (currently no
		// mechanism to assign by name)
		// parent_issue_id - ID of the parent issue
		// custom_fields - See Custom fields
		// watcher_user_ids - Array of user ids to add as watchers (since 2.3.0)
		// is_private - Use true or false to indicate whether the issue is
		// private or not
		// estimated_hours - Number of hours estimated for issue

		HashMap<String, String> param = new HashMap<String, String>();
		param.put("project_id", "101");
		// param.put("status_id", "5");

		
		// issue info
		List<Issue> issues = mgr.getIssueManager().getIssues(param);// Redmine��Ŀ���
//		for (Issue issue : issues) {
//
//			// //get all 100%
//			// if(issue.getDoneRatio() != 100){
//			// continue;
//			// }
//			//
//			// if(issue.getTracker().getId() == 4){
//			// continue;
//			// }
//
//			// System.out.println("�����Ҫ:" + issue.toString());
//			System.out.println("������Ŀ:" + issue.getProject().getName());
//			System.out.println("����ID:" + issue.getId());
//			System.out.println("����:" + issue.getSubject());
//			System.out.println("����:" + issue.getTracker().getName());// 1-BUG��2-����3-֧�֣�4-��̱���6-����
//			System.out.println("״̬:" + issue.getStatusId() + issue.getStatusName());// 1-�½���2-�����У�3-�ѽ����4-������5-�ѹرգ�6-�Ѿܾ�
//			System.out.println("���ȼ�:" + issue.getPriorityId() + issue.getPriorityText());// 1-�ͣ�2-��ͨ��3-�ߣ�4-������5-����
//			System.out.println("����ɰٷֱ�:" + issue.getDoneRatio());// % Done����ɰٷֱ�
//			System.out.println("������:" + issue.getAuthor());
//			System.out.println("ָ����:" + issue.getAssigneeName());
//			System.out.println("��������:" + fmt_YYYYMMDD(issue.getCreatedOn()));// ����ʱ��
//			System.out.println("��ʼ����:" + fmt_YYYYMMDD(issue.getStartDate()));// ��ʼʱ��
//			System.out.println("������ʱ��:" + fmt_YYYYMMDD(issue.getUpdatedOn()));// ������ʱ��
//			System.out.println("�ƻ��������:" + fmt_YYYYMMDD(issue.getDueDate()));// ���ʱ��
//			System.out.println("�ر�����:" + fmt_YYYYMMDD(issue.getClosedOn()));// �ر�ʱ��
//			System.out.println("����:" + issue.getDescription());
//			System.out.println(issue.getEstimatedHours());
//			System.out.println(issue.getSpentHours());
//			System.out.println("#####################################################################");
//
//			// issue.setStatusId(5);
//			// issue.setClosedOn(new Date());
//			// issue.setNotes("Ӧ�ų���Ҫ��������̱���Эͬ�����������ɰٷֱ���100%����״̬�޸�Ϊ�Թر�");
//			// mgr.getIssueManager().update(issue);
//
//		}

	}

	public static String fmt_YYYYMMDD(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
}
