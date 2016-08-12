package liql.redmine;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

import liql.util.L_LOG;
import liql.util.L_Util;
import nosubmit.L_Security;

public class L_GetIssue_LastWorkTime {
	private static final String OUTDIR = L_Security.BASEDIR + "人员最后一次工作分配时间" + File.separator;

	public static void getFocusIssues(RedmineManager mgr) {
		try {
			L_Util.mkdir(OUTDIR);

			HashMap<String, RedmineRowData> userLastWork = new HashMap<String, RedmineRowData>();

			// list projects
			List<Project> projects = mgr.getProjectManager().getProjects();

			for (Project project : projects) {
				if (L_Security.NOSCRIBE.contains(project.getId())) {
					continue;// don't subcribe
				}

				HashMap<String, String> param = new HashMap<String, String>();
				param.put("project_id", "113");//String.valueOf(project.getId())
				param.put("offset", L_GetIssue_Main.offset);
				param.put("limit", L_GetIssue_Main.limit);
				
				// each project list issues
				List<Issue> issues = mgr.getIssueManager().getIssues("113",null);
				System.out.println("============"+issues.size());
				for (Issue issue : issues) {

					RedmineRowData rrd = new RedmineRowData(issue.getProject().getName(), issue.getId(),
							issue.getSubject(), issue.getTracker().getName(), issue.getStatusName(),
							issue.getPriorityText(), issue.getAuthor().toString(), issue.getAssigneeName(),
							L_Util.fmt_YYYYMMDD(issue.getCreatedOn()), L_Util.fmt_YYYYMMDD(issue.getStartDate()),
							L_Util.fmt_YYYYMMDD(issue.getUpdatedOn()), L_Util.fmt_YYYYMMDD(issue.getDueDate()),
							issue.getDescription());

					System.out.println(rrd.toString());
					if (userLastWork.get(issue.getAuthor().toString()) != null) {
						// 对比时间
						RedmineRowData rrd_old = userLastWork.get(issue.getAuthor().toString());
						if (Integer.parseInt(rrd.getCreate_time()) < Integer.parseInt(rrd_old.getCreate_time())) {

						} else {
							userLastWork.put(issue.getAuthor().toString(), rrd);
						}
					} else {
						userLastWork.put(issue.getAuthor().toString(), rrd);
					}

					if (userLastWork.get(issue.getAssigneeName()) != null) {
						// 对比时间
						RedmineRowData rrd_old = userLastWork.get(issue.getAssigneeName());
						if (Integer.parseInt(rrd.getCreate_time()) < Integer.parseInt(rrd_old.getCreate_time())) {
							
						}else{
							userLastWork.put(issue.getAssigneeName(), rrd);
						}
					}else{
						userLastWork.put(issue.getAssigneeName(), rrd);
					}

					rrd = null;
				}

				L_LOG.OUT_Nece(project.getName() + "\t\t issue nums : " + userLastWork.size());
			}

			Iterator<String> userkey = userLastWork.keySet().iterator();
			while (userkey.hasNext()) {
				String username = (String) userkey.next();
				L_LOG.OUT_Nece("username["+username+"]"+userLastWork.get(username));
			}

			// L_Excel.WriteExcel_Redmine(OUTDIR + "ALLINONE_issues_focus(" +
			// ALLINONE.size() + ")" + L_Security.EXCELFIX,
			// ALLINONE);
			// L_LOG.OUT_Nece("all issue nums : " + ALLINONE.size());
		} catch (RedmineException e) {
			e.printStackTrace();
		}

	}
}
