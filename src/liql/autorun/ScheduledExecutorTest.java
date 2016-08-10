package liql.autorun;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest extends TimerTask {

	private String jobName = "";

	public ScheduledExecutorTest(String jobName) {
		super();
		this.jobName = jobName;
	}

	@Override
	public void run() {
		System.out.println("Date = " + new Date() + ", execute " + jobName);
	}

	public static void main(String[] args) throws Exception {
		ScheduledExecutorTest test = new ScheduledExecutorTest("job1");

		// 获取当前时间
		Calendar currentDate = Calendar.getInstance();
		System.out.println("Current Date = " + currentDate.getTime().toString());

		// 目标时间
		Calendar doDate = Calendar.getInstance();
		doDate.set(Calendar.HOUR_OF_DAY, 15);
		doDate.set(Calendar.MINUTE, 18);
		doDate.set(Calendar.SECOND, 30);
		doDate.set(Calendar.MILLISECOND, 0);
		System.out.println("first Date = " + doDate.getTime().toString());

		long delay = doDate.getTimeInMillis() - currentDate.getTimeInMillis();
		System.out.println("delay time = " + delay);

		// @@@@@@@@@@D * H * Min* Sec * 1000;
		long period = 1 * 1 * 1 * 60 * 1000;
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		// 从现在开始delay毫秒之后，每隔一星期执行一次job1
		service.scheduleAtFixedRate(test, delay, period, TimeUnit.MILLISECONDS);

	}
}