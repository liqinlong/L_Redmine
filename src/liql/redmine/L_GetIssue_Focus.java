package liql.redmine;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

import jxl.write.WriteException;
import liql.util.L_Excel;
import liql.util.L_LOG;
import liql.util.L_Util;
import nosubmit.L_Security;

public class L_GetIssue_Focus {
	private static final String OUTDIR = L_Security.BASEDIR + "重点关注issue" + File.separator;

	public static void getFocusIssues(RedmineManager mgr) {
		try {
			L_Util.mkdir(OUTDIR);

			LinkedList<RedmineRowData> ALLINONE = new LinkedList<RedmineRowData>();

			// list projects
			List<Project> projects = mgr.getProjectManager().getProjects();

			for (Project project : projects) {
				if (L_Security.NOSCRIBE.contains(project.getId())) {
					continue;// don't subcribe
				}

				LinkedList<RedmineRowData> rowsdata = new LinkedList<RedmineRowData>();

				// each project list issues
				List<Issue> issues = mgr.getIssueManager().getIssues(String.valueOf(project.getId()),null);
				
				for (Issue issue : issues) {

					//重点关注任务
					//！已关闭 && 结束日期-开始日期 >= 15天
					// 4-里程碑
					if (issue.getStatusId() == 5 || issue.getTracker().getId() == 4) {
						continue;
					}
					if (null != issue.getDueDate()) {
						long issueduedate = issue.getDueDate().getTime();// issue计划完成时间
						long curtimes = System.currentTimeMillis();// 当前时间

						long day_15 = 15 * 24 * 60 * 60 * 1000;
						// if 状态不是已关闭 && 当前时间>计划完成时间
						if (issueduedate < (curtimes+day_15)) {
							continue;
						}
					}
					

					rowsdata.add(new RedmineRowData(issue.getProject().getName(), issue.getId(), issue.getSubject(),
							issue.getTracker().getName(), issue.getStatusName(), issue.getPriorityText(),
							issue.getAuthor().toString(), issue.getAssigneeName(),
							L_Util.fmt_YYYYMMDD(issue.getCreatedOn()), L_Util.fmt_YYYYMMDD(issue.getStartDate()),
							L_Util.fmt_YYYYMMDD(issue.getUpdatedOn()), L_Util.fmt_YYYYMMDD(issue.getDueDate()),
							issue.getDescription()));
				}

				L_LOG.OUT_Nece(project.getName() + "\t\t issue nums : " + rowsdata.size());
				ALLINONE.addAll(rowsdata);
				L_Excel.WriteExcel_Redmine(OUTDIR + "[" + project.getName() + "]_issues_focus(" + rowsdata.size()
						+ ")" + L_Security.EXCELFIX, rowsdata);
				rowsdata = null;
			}

			L_Excel.WriteExcel_Redmine(
					OUTDIR + "ALLINONE_issues_focus(" + ALLINONE.size() + ")" + L_Security.EXCELFIX, ALLINONE);
			L_LOG.OUT_Nece("all issue nums : " + ALLINONE.size());
		} catch (RedmineException | WriteException | IOException e) {
			e.printStackTrace();
		}

	}
}
