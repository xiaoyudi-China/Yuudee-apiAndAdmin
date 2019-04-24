package com.xfkj.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 日期处理工具类,常用的方法以get开头 <br/>
 * format date ->string <br/>
 * parse string->date
 * 
 * @author ddcz418
 * 
 */
public class DateUtil {
	/**
	 * 取得当前时间的string 形式,样式yyyy-MM-dd hh:mm:ss
	 * 
	 * @return
	 */
	public static String getDate() {
		return format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}


	/**
	 * 返回给给定日期的string形式,格式yyyy-MM-dd hh:mm:ss
	 * 
	 * @param d
	 * @return
	 */
	public static String getDate(Date d) {
		return format(d, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static String formatDate(Date d) {
		return format(d, "dd日HH时mm分");
	}
	
	public static Date getDate4Day(Date d) {
		String date = getDate(d);
		return getDate(date, "yyyy-MM-dd");
	}

	/**
	 * 取得当前日期,年月日
	 * 
	 * @return
	 */
	public static String getDateyyyyMMdd() {
		return format(new Date(), "yyyyMMdd");
	}

	public static String getDateyyyyMMdd(Date d) {
		return format(d, "yyyyMMdd");
	}
	
	public static String getDateyyyyMM(Date d) {
		return format(d, "yyyyMM");
	}

	public static String getDateyyyy_MM_dd(Date d) {
		String str = "";
		try {
			str = format(d, "yyyy-MM-dd");			
		} catch (Exception e) {
			str = "";
		}
		return str;
	}
	
	public static String getDateyyyy_MM_dd() {
		return format(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 取得当前日期,年月日,格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDateY_M_D() {
		return format(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 取得当前时间,时分秒
	 * 
	 * @return
	 */
	public static String getDateHMS() {
		return format(new Date(), "HH:mm:ss");
	}

	/**
	 * 将date类型格式化为style类型的字符串
	 * 
	 * @param date
	 * @param style
	 * @return
	 */
	public static String format(Date date, String style) {
		DateFormat df = new SimpleDateFormat(style);
		return df.format(date);
	}

	/**
	 * 将dateStr解析为date类型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parse(String dateStr, String style) {
		if (dateStr == null || style == null)
			return null;
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(style);
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}
	/**
	 * 将dateStr解析为date类型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parse(String dateStr) {
		if(dateStr == null || "".equals(dateStr))
			return null;
		return DateUtil.parse(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	public static Date parseDate(String dateStr) {
		if(dateStr == null || "".equals(dateStr))
			return null;
		return DateUtil.parse(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 返回英文格式 like Jan 01,2009
	 *
	 *            locale.English
	 * @param date
	 * @return
	 */
	public static String format2English(Date date) {
		return format("MMM dd,yyyy", Locale.ENGLISH, date);
	}

	public static String format(String format, Locale locale, Date date) {
		DateFormat df = new SimpleDateFormat(format, locale);
		return df.format(date);
	}
	
	public static Date getMonthFirst(String str, String pattern) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.parse(str, pattern));
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.getActualMinimum(Calendar.DAY_OF_MONTH));
			return calendar.getTime();
		} catch (Exception e) {
			return null;
		}
 
	}
	public static Date getMonthEnd(String str, String pattern) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.parse(str, pattern));
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.getActualMaximum(Calendar.DAY_OF_MONTH));
			return calendar.getTime();
		} catch (Exception e) {
			return null;
		}
	}
	public static String getMonthFirstDay(String str, String pattern) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.parse(str, pattern));
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.getActualMinimum(Calendar.DAY_OF_MONTH));
			return DateUtil.getDateyyyy_MM_dd(calendar.getTime());
		} catch (Exception e) {
			return null;
		}

	}

	public static String getMonthEndDay(String str, String pattern) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.parse(str, pattern));
			calendar.set(Calendar.DAY_OF_MONTH, calendar
					.getActualMaximum(Calendar.DAY_OF_MONTH));
			return DateUtil.getDateyyyy_MM_dd(calendar.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date getNextZeroDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getZeroDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static int getIntevelDays(Date beginDate, Date endDate) {
		Calendar c = Calendar.getInstance();
		int day = 0;
		Date bDate = getNextZeroDate(beginDate);
		Date eDate = getZeroDate(endDate);
		for(c.setTime(bDate); c.getTime().before(eDate); c.add(Calendar.DAY_OF_MONTH, 1)) {
			day ++;
		}
		return day;
	}
	
	public static int getIntervalMonths(Date date) {
		return getIntervalMonths(date, new Date());
	}
	
	public static int getIntervalMonths(Date beginDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(beginDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		
		return end.get(Calendar.MONTH) - start.get(Calendar.MONTH) 
				+ 12 * (end.get(Calendar.YEAR) - start.get(Calendar.YEAR));
//				+ (now.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH) + 1)/now.get(Calendar.DAY_OF_MONTH);
	}


	
	public static Date getWeekFirstDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if(dayOfWeek == 1) {
			dayOfWeek = -6;
		} else {
			dayOfWeek = 2 - dayOfWeek;
		}
		c.add(Calendar.DAY_OF_MONTH, dayOfWeek);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}


	public static Date getWeekLastDay(Date date) throws ParseException {
		String dataStr = DateUtil.getDateyyyy_MM_dd(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dataStr));
		int d = 0;
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			d = -6;
		} else {
			d = 2 - cal.get(Calendar.DAY_OF_WEEK);
		}
		cal.add(Calendar.DAY_OF_WEEK, d);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		// 所在周结束日期
		String data2 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		return DateUtil.parseDate(data2);
	}


	public static int getMonthsCount(Date begindate, Date endDate) {
		Calendar cb = Calendar.getInstance();
		cb.setTime(begindate);
		int by = cb.get(cb.YEAR);
		int bm = cb.get(cb.MONTH);
		
		Calendar ce = Calendar.getInstance();
		ce.setTime(endDate);
		int ey = ce.get(ce.YEAR);
		int em = ce.get(ce.MONTH);
		return Math.abs(ey-by)*12+em-bm;
	}
	
	public static String getMonthLastDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return format(c.getTime(), "yyyy年MM月dd日");
	}
	
	public static Date getMonthLastDayByDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}
	
	public static List<String> getChargeMonth(Date date){
		List<String> strList = new ArrayList<String>();
		Calendar gc = Calendar.getInstance();
        for(gc.setTime(date); gc.getTime().before(new Date()); gc.add(Calendar.MONTH, 1)) {
        	strList.add(DateUtil.format(gc.getTime(), "yyyy-MM"));
        }
        Collections.reverse(strList);
        return strList;
		
	}
	public static Date getFirstDayByNextMonth(int year, int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}

	/**
	 * @param strDate 格式为：“yyyy-MM”
	 * @return
	 */
	public static Date getFirstDayByMonth(String strDate){
		return getFirstDayByMonth(strDate, "yyyy-MM");
	}
	
	public static Date getFirstDayByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getDate(String strDate){
		return DateUtil.getDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date getDate(String strDate, String formatStr){
		if(formatStr == null ||"".equals(formatStr))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		try {
			if(strDate != null){
				return sdf.parse(strDate);
			}
			return null;
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Calendar getCalendar(Date date){
		if(date == null)
			return null;
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca;
	}
	/**
	 *@param strDate	//日期字符传
	 * @param formatStr	//日期格式
	 * @return	Date
	 */
	public static Date getFirstDayByMonth(String strDate , String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		try {
			if(strDate != null)
				return sdf.parse(strDate);
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static Date getLastTimeByMonth(Date date){
		if(date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MILLISECOND, -1);
		return c.getTime();
	}
	public static Date getDayEndByDate(Date date){
		if(date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MILLISECOND, -1);
		return c.getTime();
	}
	
	public static Date getFirstDayByMonth(int year, int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getMonthFirstDayByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static String getCurrentTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar beforeTime = Calendar.getInstance();
		beforeTime.add(Calendar.MINUTE, -20);// 3分钟之前的时间
		Date beforeD = beforeTime.getTime();
		String time = sdf.format(beforeD);
		return time;
	}

	public static Date getNextDayByDate(Date date){
		if(date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	//获取前一天
	public static Date getPriorDayByDate(Date date){
		if(date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}
	
	/**
	 * @param date
	 * @return 下个月的这个时间
	 */
	public static Date getNextMonthDayByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,1);
		return c.getTime();
	}
	/**
	 * @param date
	 * @return 上一月的这个时间
	 */
	public static Date getBeforeMothDateByDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH,-1);
		return c.getTime();
	}

	public static boolean getThreeMothDateByDate(Date date){
		Calendar c=Calendar.getInstance();//默认是当前日期
		int nowday=c.get(c.DAY_OF_YEAR);
		Calendar c1=Calendar.getInstance();
		c1.setTime(date);
		int oldday=c.get(c.DAY_OF_YEAR);
		return Math.abs(nowday-oldday) > 90;
	}


	public static Date getPreDayByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}
	
	public static Date getPreDayByDate(Date date, int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, -days);
		return c.getTime();
	}
	
	public static Date getPreDay000ByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		
		return c.getTime();
	}
	
	public static Date getLastDayByMonth(int year, int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}
	
	public static Date getFirstDayByPreMonth(int year, int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		c.add(Calendar.MONTH, -1);
		return c.getTime();
	}
	
	/**
	 * @param date
	 * @return data所在月的下个月的第一天
	 */
	public static Date getFirstDayByNextMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}
	
	public static Date getFirstDayByPreMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		c.add(Calendar.MONTH, -1);
		return c.getTime();
	}
	
	public static Date getDealDayByMonth(int year, int month){
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, 1);
		return c.getTime();
	}
	
	/**
	 * @param date
	 * @return	指定日期的上月的这一时刻
	 */
	public static Date getLastMonthByDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, -1);
		return c.getTime();
	}
	
	public static int getLastMonthYYYYMM(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		c.add(Calendar.MONTH, -1);
		return StringUtil.str2Int(c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1));
	}
	
	public static int getYYYYMM(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return StringUtil.str2Int(c.get(Calendar.YEAR) + "" + (c.get(Calendar.MONTH) + 1));
	}
	
	public static int getYYYYMMDD(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return StringUtil.str2Int(getDateyyyyMMdd(date));
	}
	
	public static Date get30DayZeroClock(Date date){
		return getDayZeroClock(date, 30);
	}
	/**
	 * 给定时间之前多少 天的时间
	 * @param date
	 * @param beforeDay
	 * @return
	 */
	public static Date getDayZeroClock(Date date, int beforeDay){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -beforeDay);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	
	public static Date getBeforeDay(Date date, int beforeDay){
		if(date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -beforeDay);
		return c.getTime();
	}
	
	public static Date getAfterDay(Date date, int afterDay){
		if(date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, afterDay);
		return c.getTime();
	}
	/**
	 * 
	 * @param date
	 * @param calendarType
	 * @return
	 */
	public static int getInt(Date date, int calendarType){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(calendarType);
	}
	
	public static String getNext12Hours() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 12);
		return sdf.format(c.getTime());
	}
	
	public static boolean dateEqualYM(Date first, Date sec) {
		if(first == null || sec == null)
			return false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(first).equals(sdf.format(sec));
	}
	
	public static boolean isLastMonth(Date first, Date sec) {
		if(first == null || sec == null)
			return false;
		Calendar c = Calendar.getInstance();
		c.setTime(first);
		c.add(Calendar.MONTH, 1);
		return dateEqualYM(c.getTime(), sec);
	}

	public static Date getAfterMonth(Date date, int afterMonth){
		if(date == null)
			return date;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, afterMonth);
		return c.getTime();
	}

	/**
	 * 判断两日期是否是同一天
	 * <p>Description:</p>
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isTheSameDay(Date date1, Date date2) {
		if(date1 == null || date2 == null)
			return false;
		Date dayZeroClock = DateUtil.getDayZeroClock(date1, 0);
		Date dayEndClock = DateUtil.getDayEndByDate(date1);
		if(date2.before(dayZeroClock) || date2.after(dayEndClock))
			return false;
		return true;
	}
	/**
	 * 判断两日期是否是同一月份
	 * <p>Description:</p>
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isTheSameMonth(Date date1, Date date2) {
		if(date1 == null || date2 == null)
			return false;
		Date monthFirstDayByDate = DateUtil.getMonthFirstDayByDate(date1);
		Date dayZeroClock = DateUtil.getDayZeroClock(monthFirstDayByDate, 0);//当月第一天凌晨
		
		Date monthLastDayByDate = DateUtil.getMonthLastDayByDate(date1);
		Date dayEndClock = DateUtil.getMonthLastDayByDate(monthLastDayByDate);//当月最后一天最后一刻
		
		if(date2.before(dayZeroClock) || date2.after(dayEndClock))
			return false;
		return true;
	}
	
	public static boolean isTheSameMonth(Date date1, int year, int month) {
		if(date1 == null)
			return false;
		Calendar c = Calendar.getInstance();
		c.setTime(date1);
		return c.get(Calendar.MONTH) + 1 == month && c.get(Calendar.YEAR) == year;
	}

	public static String getStrDate(Date date, String fmtStr) {
		String time = "";
		try {
			if(date != null)
				time = format(date, fmtStr);
		} catch (Exception e) {
			
		}
		return time;
	}
	
	
	public static Date get7DayZeroClock(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -7);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getNow(){
		return new Date(System.currentTimeMillis());
	}

	//获取当前时间的上一周该时间
	public static Date getLastWeekDate(Date time) {
		return DateUtil.getAfterDay(time, -7);
	}

	//获取当前孩子月龄
	public static int getMonthAge(Date fromDate, Date toDate) {
		Calendar  from  =  Calendar.getInstance();
		from.setTime(fromDate);
		Calendar  to  =  Calendar.getInstance();
		to.setTime(toDate);

		int fromYear = from.get(Calendar.YEAR);
		int fromMonth = from.get(Calendar.MONTH);
		int fromDay = from.get(Calendar.DAY_OF_MONTH);

		int toYear = to.get(Calendar.YEAR);
		int toMonth = to.get(Calendar.MONTH);
		int toDay = to.get(Calendar.DAY_OF_MONTH);
		int year = toYear  -  fromYear;  //获取年份
		int month = year*12+toMonth  - fromMonth;
		int day = toDay  - fromDay;
		if (day >= 15){
			month++;
		}
		if (day <= -15){
			month--;
		}
		return month;
	}

	/**
	 * 返回某天的开始时间
	 * @param date
	 * @return
	 */
	public static Date begin(Date date) {
		if (null == date) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY,0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		return c.getTime();
	}
	/**
	 * 返回某天的结束时间
	 * @param date
	 * @return
	 */
	public static Date end(Date date) {
		if (null == date) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MINUTE, 59);
		return c.getTime();
	}
	/**
	 * 获取指定月多少天
	 * @param date
	 * @return
	 */
	public static int getCurrentMonthDay(Date date) {
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 获取当前时间是第几个月
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar  to  =  Calendar.getInstance();
		to.setTime(date);
		int toMonth = to.get(Calendar.MONTH)+1;
		return toMonth;
	}


	/**
	 * 获取某时间三个月之后的毫秒数
	 * @param date
	 * @return
	 */
	public static Long getThreeMonthTimeLong(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		//过去一年
		c.setTime(date);
		c.add(Calendar.MONTH, 3);
		//时
		c.set(Calendar.HOUR_OF_DAY, 20);
		//分
		c.set(Calendar.MINUTE, 0);
		//秒
		c.set(Calendar.SECOND, 0);
		Date m3 = c.getTime();
		System.out.println(DateUtil.getDate(m3));
		return  (m3.getTime() - date.getTime());
	}

	/**
	 * 获取某时间三个月之后的毫秒数
	 * @param date
	 * @return
	 */
	public static Long getThreeMonthMornTimeLong(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		//过去一年
		c.setTime(date);
		c.add(Calendar.MONTH, 3);
		//时
		c.set(Calendar.HOUR_OF_DAY, 8);
		//分
		c.set(Calendar.MINUTE, 0);
		//秒
		c.set(Calendar.SECOND, 0);
		Date m3 = c.getTime();
		System.out.println("time:"+DateUtil.getDate(m3));
		return  (m3.getTime() - date.getTime());
	}

	//JAVA获取某段时间内的所有日期
	public static List<Date> findDates(Date dStart, Date dEnd) {
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(dStart);

		List dateList = new ArrayList();
		//别忘了，把起始日期加上
		dateList.add(dStart);
		// 此日期是否在指定日期之后
		while (dEnd.after(cStart.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cStart.add(Calendar.DAY_OF_MONTH, 1);
			dateList.add(cStart.getTime());
		}
		return dateList;
	}

	/**
	 * DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,
	 * Locale.CHINA); String s = df.format(new Date()); System.out.println(s);
	 * Full Thursday, July 29, 2010 2010年7月29日 星期四 long July 29, 2010 2010年7月29日
	 * medium Jul 29, 2010 2010-7-29 short 7/29/10 10-7-29
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		/*Date date = DateUtil.getDate("2018-10-15", "yyyy-MM-dd");
		Date date2 = DateUtil.getDate("2018-11-05", "yyyy-MM-dd");
		List<Date> list = DateUtil.findDates(date, date2);
		for (Date date1 : list) {
			System.out.println(DateUtil.getDate(date1));
		}*/
		System.out.println(DateUtil.getThreeMonthMornTimeLong(new Date()));
	}

}
