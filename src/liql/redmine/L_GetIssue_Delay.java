package liql.redmine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

public class L_GetIssue_Delay {
	private static final String OUTDIR = L_Security.BASEDIR + "当前已经延期issue" + File.separator;

	public static void getCurrentDelayissues(RedmineManager mgr) {
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

				HashMap<String, String> param = new HashMap<String, String>();
				param.put("project_id", String.valueOf(project.getId()));

				// each project list issues
				List<Issue> issues = mgr.getIssueManager().getIssues(param);
				for (Issue issue : issues) {

					// 正常查询排除已关闭&里程碑
					// 4-里程碑
					if (issue.getStatusId() == 5 || issue.getTracker().getId() == 4) {
						continue;
					}
					if (null != issue.getDueDate()) {
						long issueduedate = issue.getDueDate().getTime();// issue计划完成时间
						long curdate = System.currentTimeMillis();// 当前时间

						// if 状态不是已关闭 && 当前时间>计划完成时间
						if (issueduedate > curdate) {
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
				L_Excel.WriteExcel_Redmine(OUTDIR + "[" + project.getName() + "]_issues_delay(" + rowsdata.size() + ")"
						+ L_Security.EXCELFIX, rowsdata);
				rowsdata = null;
			}

			L_Excel.WriteExcel_Redmine(OUTDIR + "ALLINONE_issues_delay(" + ALLINONE.size() + ")" + L_Security.EXCELFIX,
					ALLINONE);
			L_LOG.OUT_Nece("all issue nums : " + ALLINONE.size());
		} catch (RedmineException | WriteException | IOException e) {
			e.printStackTrace();
		}
	}
}
