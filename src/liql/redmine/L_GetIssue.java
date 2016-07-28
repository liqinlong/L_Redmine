package liql.redmine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;

import jxl.write.WriteException;
import liql.util.L_Excel;
import liql.util.L_Util;
import nosubmit.L_Security;

public class L_GetIssue {
	private static final ArrayList<Integer> proIdList = new ArrayList<Integer>();
	static {
		proIdList.add(1); // 0-优讯公共信息资源;1
		proIdList.add(184); // 1.G-支付产品服务化治理;184
		proIdList.add(178); // 1.U-测试组资料与事务;178
		proIdList.add(4); // 2-项目实施与服务;4
		proIdList.add(128); // 2.+标准化实施文档;128
		proIdList.add(129); // 2.+.1-积分实施标准化文档;129
		proIdList.add(130); // 2.+.2-支付实施标准化文档;130
		proIdList.add(131); // 2.+.3-电商实施标准文档;131
		proIdList.add(11); // 2.0-综合积分平台;11
		proIdList.add(30); // 2.0.6-兰州银行积分易;30
		proIdList.add(53); // 2.0.6.1-兰州银行积分易客户端;53
		proIdList.add(12); // 2.1-支付平台;12
		proIdList.add(46); // 2.1.0-兰州银行三维易付;46
		proIdList.add(81); // 2.Z-PMM管理;81
		proIdList.add(180); // 6.1-技术服务中心;180

	}

	public static void main(String[] args) {
		try {
			ArrayList<RowData> ALLINONE = new ArrayList<RowData>();
			RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);
			// list projects
			List<Project> projects = mgr.getProjectManager().getProjects();
			for (Project project : projects) {
				if (proIdList.contains(project.getId())) {
					continue;// don't subcribe
				}
				System.out.println(project.getName() + ";" + project.getId());

				ArrayList<RowData> rowsdata = new ArrayList<RowData>();

				// each project list issues
				List<Issue> issues = mgr.getIssueManager().getIssues(String.valueOf(project.getId()), null);// Redmine项目编号
				for (Issue issue : issues) {

					if (issue.getStatusId() == 5 || issue.getTracker().getId() == 4) {
						continue;
					}
					rowsdata.add(new RowData(issue.getProject().getName(), issue.getId(), issue.getSubject(),
							issue.getTracker().getName(), issue.getStatusName(), issue.getPriorityText(),
							issue.getAuthor().toString(), issue.getAssigneeName(),
							L_Util.fmt_YYYYMMDD(issue.getCreatedOn()), L_Util.fmt_YYYYMMDD(issue.getStartDate()),
							L_Util.fmt_YYYYMMDD(issue.getUpdatedOn()), L_Util.fmt_YYYYMMDD(issue.getDueDate()),
							issue.getDescription()));
				}

				// create by yesterday

				// create time is long time to today ,about N > 10
				ALLINONE.addAll(rowsdata);
				L_Excel.WriteExcel("outfile/" + project.getName() + "_out.xls", rowsdata);
			}
			L_Excel.WriteExcel("outfile/ALLINONE.xls", ALLINONE);
		} catch (RedmineException | IOException | WriteException e) {
			e.printStackTrace();
		}
	}
}
