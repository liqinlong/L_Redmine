package liql.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class L_Util {
	public static String fmt_YYYYMMDD(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
}
