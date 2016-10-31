package smallTools;

import java.util.Calendar;

/**
 * Created by geyao on 2016/10/29.
 */
public class Time {

	public static Calendar calendar = Calendar.getInstance();

	public static int getYear(){    // 返回年份比如 2012
		return calendar.get(Calendar.YEAR);
	}
	public static int getMonth(){   //返回月份，比如1，10，12
		return calendar.get(Calendar.MONTH);
	}
	public static int getDay(){     //返回日期，比如29，1
		return calendar.get(Calendar.DATE);
	}
	public static int getHour(){    //返回小时
		return calendar.get(Calendar.HOUR);
	}
	public static int getMinute(){     //返回分钟
		return calendar.get(Calendar.MINUTE);
	}
	public static int getSecond(){      //返回秒钟
		return calendar.get(Calendar.SECOND);
	}
}
