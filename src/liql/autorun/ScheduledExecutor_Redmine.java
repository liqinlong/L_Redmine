package liql.autorun;

import java.util.Date;
import java.util.TimerTask;

import liql.redmine.L_GetIssue_Main;
import liql.util.L_LOG;

public class ScheduledExecutor_Redmine extends TimerTask {

	private String jobName = "";

	public ScheduledExecutor_Redmine(String jobName) {
		super();
		this.jobName = jobName;
	}

	@Override
	public void run() {
		try {
			L_LOG.OUT_Nece("curr exec Date = " + new Date() + ", execute " + jobName);
			L_GetIssue_Main.StartAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}