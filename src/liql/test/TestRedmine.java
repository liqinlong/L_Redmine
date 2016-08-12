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
		 * 前一天新增任务&超过N天未解决的任务&项目标准里程碑
		 */

		RedmineManager mgr = RedmineManagerFactory.createWithApiKey(L_Security.REDMINEURL, L_Security.APIACCESSKEY);
		// project info
		List<Project> projects = mgr.getProjectManager().getProjects();
		for (Project project : projects) {
			System.out.println(project.getName() + ";" + project.getId());
		}
		// ===================================================all projects
		// id==========================================================
		// 0-优讯公共信息资源;1
		// 1.A.8-手机钱包(TIFA);143
		// 1.G-支付产品服务化治理;184
		// 1.U-测试组资料与事务;178
		// 2-项目实施与服务;4
		// 2.+标准化实施文档;128
		// 2.+.1-积分实施标准化文档;129
		// 2.+.2-支付实施标准化文档;130
		// 2.+.3-电商实施标准文档;131
		// 2.0-综合积分平台;11
		// 2.0.0-东营银行积分易;24
		// 2.0.1-东营莱商村镇银行积分易;25
		// 2.0.2-桂林银行积分易;26
		// 2.0.2.0-桂林银行积分易商户管理平台;94
		// 2.0.3-济宁银行积分易;27
		// 2.0.4-江苏银行积分易;28
		// 2.0.5-辽阳银行积分易;29
		// 2.0.6-兰州银行积分易;30
		// 2.0.6.1-兰州银行积分易客户端;53
		// 2.0.7-浙江民泰商业银行积分易;31
		// 2.0.8-江苏南通农商行积分易;33
		// 2.0.9-攀枝花商行积分易;34
		// 2.0.A-江苏邳州农商行积分易;35
		// 2.0.B-齐商银行积分易;36
		// 2.0.D-泰安商行积分易;38
		// 2.0.F-厦门银行积分易;40
		// 2.0.G-江苏新沂农商行积分易;41
		// 2.0.H-江苏仪征农商行积分易;42
		// 2.0.I-遵义商行积分易;43
		// 2.0.J-齐鲁银行积分项目;99
		// 2.0.K-阜新银行积分易;100
		// 2.0.L-睢宁农商行积分易项目;104
		// 2.0.M-尧都农商行综合积分;107
		// 2.0.N-枣庄银行综合积分;110
		// 2.0.O-锦州银行综合积分;111
		// 2.0.P-曲靖商行综合积分;114
		// 2.0.R-长安银行综合积分;132
		// 2.0.S-威海银行综合积分;135
		// 2.0.T-常熟农商行综合积分;138
		// 2.0.U-铁岭银行综合积分;147
		// 2.0.V-晋中银行综合积分系统;176
		// 2.0.W-无锡农村商业银行综合积分;193
		// 2.0.X-中原银行积分商城;194
		// 2.0.Y-瑞丰农商行综合积分;195
		// 2.1-支付平台;12
		// 2.1.0-兰州银行三维易付;46
		// 2.1.1-柳州银行支付平台;101
		// 2.1.2-齐商银行支付电商;102
		// 2.1.3-乐山商行支付电商项目;108
		// 2.1.4-晋城银行支付项目;109
		// 2.1.5-锦州银行支付平台;112
		// 2.1.6-青岛农商银行支付项目;144
		// 2.1.7-上饶银行支付项目;145
		// 2.1.9-锦州银行校讯通和高校缴费项目;175
		// 2.1.A-晋中银行统一支付平台;177
		// 2.1.B-石嘴山银行互联网支付项目;185
		// 2.1.C-攀枝花市商业银行统一支付平台;192
		// 2.2-电商平台;13
		// 2.2.0-兰州银行三维商城;47
		// 2.2.0.1-兰州银行三维商城客户端;52
		// 2.2.1-兰州银行金融商城;48
		// 2.2.2-兰州银行o2o应用设备;51
		// 2.2.3-桂林银行电商平台;65
		// 2.2.4-锦州银行电商平台;113
		// 2.2.5-柳州银行电商平台;133
		// 2.3-p2b投融资平台;5
		// 2.3.1-山西长治农商行P2B;6
		// 2.4-e住e行;14
		// 2.4.1-兰州银行新车;137
		// 2.6-短信平台;16
		// 2.7-电话银行;17
		// 2.A-优讯金融自助小终端;21
		// 2.A.1-泰安商行自助小终端;85
		// 2.C-第三方支付合作网关;20
		// 2.C.1-桂林银行财付通快捷;44
		// 2.C.2-兰州银行财付通快捷;103
		// 2.C.3-柳州银行财付通快捷;140
		// 2.C.4-柳州银行京东快捷;141
		// 2.D-农村三权流转交易平台;98
		// 2.D.0-兰州三权项目;139
		// 2.Q-其他行业应用;172
		// 2.Q.1-深圳空调项目;173
		// 2.Z-PMM管理;81
		// 6.1-技术服务中心;180

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
		List<Issue> issues = mgr.getIssueManager().getIssues(param);// Redmine项目编号
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
//			System.out.println("指派人:" + issue.getAssigneeName());
//			System.out.println("创建日期:" + fmt_YYYYMMDD(issue.getCreatedOn()));// 创建时间
//			System.out.println("开始日期:" + fmt_YYYYMMDD(issue.getStartDate()));// 开始时间
//			System.out.println("最后更新时间:" + fmt_YYYYMMDD(issue.getUpdatedOn()));// 最后更新时间
//			System.out.println("计划完成日期:" + fmt_YYYYMMDD(issue.getDueDate()));// 完成时间
//			System.out.println("关闭日期:" + fmt_YYYYMMDD(issue.getClosedOn()));// 关闭时间
//			System.out.println("描述:" + issue.getDescription());
//			System.out.println(issue.getEstimatedHours());
//			System.out.println(issue.getSpentHours());
//			System.out.println("#####################################################################");
//
//			// issue.setStatusId(5);
//			// issue.setClosedOn(new Date());
//			// issue.setNotes("应张诚需要，将非里程碑的协同工作，如果完成百分比是100%，则将状态修改为以关闭");
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
