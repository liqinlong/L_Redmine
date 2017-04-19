package liql.test;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Membership;
import com.taskadapter.redmineapi.bean.Project;

import nosubmit.L_Security;

public class TestRedmine {
	public static void main(String[] args) throws RedmineException {
		// http://www.redmine.org/projects/redmine/wiki/Rest_api
		// http://www.redmine.org/projects/redmine/wiki/Rest_api_with_java
		// https://github.com/taskadapter/redmine-java-api
		// http://www.redmine.org/projects/redmine/wiki/Rest_Issues

		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);

		// add membership
//		Collection<Membership> msc = mgr.getUserManager().getUserById(249).getMemberships();
//		Iterator<Membership> it = msc.iterator();
//		while (it.hasNext()) {
//			Membership membership = (Membership) it.next();;
//			try {
//				mgr.getMembershipManager().createMembershipForUser(membership.getProject().getId(), 65,membership.getRoles());
//				System.out.println("添加项目 ==> " + membership.getProject().getId()+"==="+membership.getRoles());
//			} catch (Exception e) {
//				System.out.println("已经存在于项目 ==> "+membership.getProject());
//			}
//		}
		
		// project info
		List<Project> projects = mgr.getProjectManager().getProjects();
		for (Project project : projects) {
			System.out.println(project.getName() + ";" + project.getId());
//			for (int i = 0; i < msp.size(); i++) {
//				Membership ms = msp.get(i);
//				Collection<Role> colrose = ms.getRoles();
////				Iterator<Role> it = colrose.iterator();
////				while (it.hasNext()) {
////					Role role = (Role) it.next();
////					if(role.getId() == 3){
////						System.out.println(role);
////						mgr.getMembershipManager().createMembershipForUser(145, 65,role);
////						break;
////					}
////				}
//				System.out.println(colrose);
//			}
		}
		
		
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

//		HashMap<String, String> param = new HashMap<String, String>();
//		param.put("project_id", "101");
		// param.put("status_id", "5");

		// issue info
//		List<Issue> issues = mgr.getIssueManager().getIssues("113", null);// Redmine项目编号
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
//			// System.out.println("问题概要:" + issue.toString());
//			System.out.println("所属项目:" + issue.getProject().getName());
//			System.out.println("任务ID:" + issue.getId());
//			System.out.println("主题:" + issue.getSubject());
//			System.out.println("跟踪:" + issue.getTracker().getName());// 1-BUG；2-需求；3-支持；4-里程碑；6-任务
//			System.out.println("状态:" + issue.getStatusId() + issue.getStatusName());// 1-新建；2-进行中；3-已解决；4-反馈；5-已关闭；6-已拒绝
//			System.out.println("优先级:" + issue.getPriorityId() + issue.getPriorityText());// 1-低；2-普通；3-高；4-紧急；5-立刻
//			System.out.println("已完成百分比:" + issue.getDoneRatio());// % Done已完成百分比
//			System.out.println("创建人:" + issue.getAuthor());
//			System.out.println(issue.getAuthor().getId());
//			System.out.println("指派人:" + issue.getAssigneeName());
//			System.out.println("创建日期:" + fmt_YYYYMMDD(issue.getCreatedOn()));// 创建时间
//			System.out.println("开始日期:" + fmt_YYYYMMDD(issue.getStartDate()));// 开始时间
//			System.out.println("最后更新时间:" + fmt_YYYYMMDD(issue.getUpdatedOn()));// 最后更新时间
//			System.out.println("计划完成日期:" + fmt_YYYYMMDD(issue.getDueDate()));// 完成时间
//			System.out.println("关闭日期:" + fmt_YYYYMMDD(issue.getClosedOn()));// 关闭时间
//			System.out.println("描述:" + issue.getDescription());
//			System.out.println("#####################################################################");
//
//			// issue.setStatusId(5);
//			// issue.setClosedOn(new Date());
//			// issue.setNotes("应张诚需要，将非里程碑的协同工作，如果完成百分比是100%，则将状态修改为以关闭");
//			// mgr.getIssueManager().update(issue);
//
//		}
		
		// 项目里程碑初始化
//		Project pro = mgr.getProjectManager().getProjectById(201);// 项目编号
//		List<Tracker> tracklist = mgr.getIssueManager().getTrackers();
//		Tracker tra = tracklist.get(3);// 1-BUG；2-需求；3-支持；4-里程碑；6-任务
//		String[] subjects = new String[] { "售前", "产品出库", "需求分析", "运营接入", "计划评审", "UI设计交付", "前端交付", "移动客户端交付", "客户化开发",
//				"测试", "上线", "事故报告", };
//		for (int i = 0; i < subjects.length; i++) {
//			Issue issuenew = new Issue();
//			issuenew.setProject(pro);
//			issuenew.setTracker(tra);
//			issuenew.setSubject(subjects[i]);
//			issuenew.setStartDate(new Date());
//			mgr.getIssueManager().createIssue(issuenew);
//		}

		
		System.out.println("===========end==========");
	}

	public static String fmt_YYYYMMDD(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
}
