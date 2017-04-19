package liql.redmine;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

public class L_GetIssue_LastWorkTime{
	private static final String OUTDIR = L_Security.BASEDIR + "人员最后一次工作分配时间" + File.separator;

	public static void getFocusIssues(RedmineManager mgr) {
		try {
			L_Util.mkdir(OUTDIR);

			HashMap<String, RedmineRowData> userLastWork = new HashMap<String, RedmineRowData>();

			// list projects
//			List<Project> projects = mgr.getProjectManager().getProjects();
			Project proj = mgr.getProjectManager().getProjectById(113);
			List<Project> projects = new ArrayList<>();
			projects.add(proj);
			
			
			for (Project project : projects) {
				if (L_Security.NOSCRIBE.contains(project.getId())) {
					continue;// don't subcribe
				}
				L_LOG.OUT_Nece("======curpro======"+project.getName());
				// each project list issues
				List<Issue> issues_normal = mgr.getIssueManager().getIssues(String.valueOf(project.getId()), null);
				L_LOG.OUT_Nece("======normal======" + issues_normal.size());

				
				HashMap<String, String> param = new HashMap<String, String>();
				param.put("project_id", String.valueOf(project.getId()));
				param.put("status_id", "5");
				List<Issue> issues_closed = mgr.getIssueManager().getIssues(param);
				L_LOG.OUT_Nece("======closed======" + issues_closed.size());

				issues_normal.addAll(issues_closed);
				
				Collections.sort(issues_normal,new Comparator<Issue>() {
					@Override
					public int compare(Issue o1, Issue o2) {
						if(o1.getId() < o2.getId())
							return -1;
						return 0;
					}
				});
				
				for (int i=0; i< issues_normal.size();i++) {
					Issue issue = (Issue) issues_normal.get(i);
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

						} else {
							userLastWork.put(issue.getAssigneeName(), rrd);
						}
					} else {
						userLastWork.put(issue.getAssigneeName(), rrd);
					}

					rrd = null;
				}

//				L_LOG.OUT_Nece(project.getName() + "\t\t issue nums : " + userLastWork.size());
			}
			userLastWork.remove(null);
			
			Iterator<String> userkey = userLastWork.keySet().iterator();
			while (userkey.hasNext()) {
				String username = (String) userkey.next();
				RedmineRowData lastissue = userLastWork.get(username);
				L_LOG.OUT_Nece("username[" + username + "] lastupdatetime["+lastissue.getUpdate_time()+"] lastcreatetime["+lastissue.getCreate_time()+"]"+lastissue.toString());
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
