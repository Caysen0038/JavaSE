package im.util;
import java.util.*;
import java.text.*;
public class TimeStamp {
	/*
	 * ʱ���
	 */
	private static SimpleDateFormat format=null;
	/*
	 * ���ص�ǰ����
	 */
	public static String getDate(){
		format=new SimpleDateFormat("yyyy-MM-dd");
		String date=format.format(new Date());
		format=null;
		return date;
	}
	/*
	 * ���ص�ǰʱ��
	 */
	public static String getTime(){
		format=new SimpleDateFormat("hh:mm:ss");
		String time=format.format(new Date());
		format=null;
		return time;
	}
	/*
	 * ��������+ʱ�䣬�Կո����
	 */
	public static String getDateTime(){
		format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime=format.format(new Date());
		format=null;
		return dateTime;
	}
}
