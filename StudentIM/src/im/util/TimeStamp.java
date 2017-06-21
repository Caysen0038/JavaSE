package im.util;
import java.util.*;
import java.text.*;
public class TimeStamp {
	/*
	 * 时间戳
	 */
	private static SimpleDateFormat format=null;
	/*
	 * 返回当前日期
	 */
	public static String getDate(){
		format=new SimpleDateFormat("yyyy-MM-dd");
		String date=format.format(new Date());
		format=null;
		return date;
	}
	/*
	 * 返回当前时间
	 */
	public static String getTime(){
		format=new SimpleDateFormat("hh:mm:ss");
		String time=format.format(new Date());
		format=null;
		return time;
	}
	/*
	 * 返回日期+时间，以空格隔开
	 */
	public static String getDateTime(){
		format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime=format.format(new Date());
		format=null;
		return dateTime;
	}
}
