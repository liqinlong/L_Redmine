package liql;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import liql.autorun.ScheduledExecutor_Redmine;
import liql.util.JarUtil;
import liql.util.L_LOG;

public class Redmine_Main {
	static {
		// 读取lib目录下的jar包
		try {
			JarUtil.readJarLib("lib");
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		ScheduledExecutor_Redmine rm = new ScheduledExecutor_Redmine("redmine jobs");

		// 获取当前时间
		Calendar currentDate = Calendar.getInstance();
		L_LOG.OUT_Nece("Current Date = " + currentDate.getTime().toString());

		// 目标时间
		Calendar doDate = Calendar.getInstance();
		doDate.set(Calendar.HOUR_OF_DAY, 9);
		doDate.set(Calendar.MINUTE, 0);
		doDate.set(Calendar.SECOND, 0);
		doDate.set(Calendar.MILLISECOND, 0);
		L_LOG.OUT_Nece("first Date = " + doDate.getTime().toString());

		long delay = doDate.getTimeInMillis() - currentDate.getTimeInMillis();
		L_LOG.OUT_Nece("delay time = " + delay);

		// @@@@@@@@@@D * H * Min* Sec * 1000;
		long period = 1 * 24 * 60 * 60 * 1000;
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		// 从现在开始delay毫秒之后，每XX执行一次job1
		service.scheduleAtFixedRate(rm, delay, period, TimeUnit.MILLISECONDS);
	}
}
