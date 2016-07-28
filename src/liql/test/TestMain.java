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
		 * 前一天新增任务&超过N天未解决的任务&项目标准里程碑
		 */

		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);

		// project info
		List<Project> projects = mgr.getProjectManager().getProjects();
		for (Project project : projects) {
			System.out.println(project.getName() + ";" + project.getId());
		}

		// issue info
		List<Issue> issues = mgr.getIssueManager().getIssues("112", null);// Redmine项目编号
		for (Issue issue : issues) {
			// System.out.println("问题概要:" + issue.toString());
			System.out.println("所属项目:" + issue.getProject().getName());
			System.out.println("任务ID:" + issue.getId());
			System.out.println("主题:" + issue.getSubject());
			System.out.println("跟踪:" + issue.getTracker().getName());// 1-BUG；2-需求；3-支持；4-里程碑；6-任务
			System.out.println("状态:" + issue.getStatusId() + issue.getStatusName());// 1-新建；2-进行中；3-已解决；4-反馈；5-已关闭；6-已拒绝
			System.out.println("优先级:" + issue.getPriorityId() + issue.getPriorityText());// 1-低；2-普通；3-高；4-紧急；5-立刻
			System.out.println("已完成百分比:" + issue.getDoneRatio());// % Done已完成百分比
			// System.out.println(issue.getEstimatedHours());// 预计耗时
			// System.out.println(issue.getSpentHours());
			System.out.println("创建人:" + issue.getAuthor());
			System.out.println("指派人:" + issue.getAssigneeName());
			System.out.println("创建日期:" + fmt_YYYYMMDD(issue.getCreatedOn()));// 创建时间
			System.out.println("开始日期:" + fmt_YYYYMMDD(issue.getStartDate()));// 开始时间
			System.out.println("最后更新时间:" + fmt_YYYYMMDD(issue.getUpdatedOn()));// 最后更新时间
			System.out.println("计划完成日期:" + fmt_YYYYMMDD(issue.getDueDate()));// 完成时间
			// System.out.println(fmt_YYYYMMDD(issue.getClosedOn()));//关闭时间
			System.out.println("描述:" + issue.getDescription());
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
