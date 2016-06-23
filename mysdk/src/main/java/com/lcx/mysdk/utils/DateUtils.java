/**
 * @Description(功能描述)   : 处理日期时间的工具类
 * @Title(名称)            :  DateUtils.java
 * @Package(包)            ：  com.grass.utils
 * @company (开发公司)     :  重庆贵思新材料股份有限公司--新材料全球交易网
 * @copyright (版权)       :  本文件归属重庆贵思新材料股份有限公司所有 
 * @since (JDK版本)        ：  1.7 
 * @version (版本)         ：  V1.0
 * @author(作者)           ：  panjun
 * @date (开发日期)        ：  2015年2月10日 上午11:36:27
 */
package com.lcx.mysdk.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName(类名) : DateUtils
 * @Description(描述) : 处理日期时间的工具类。
 * @author(作者) : liuchunxu
 *
 */
public abstract class DateUtils {

	private static final int[] DAY_OF_MONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final Map<String, String> MONTHMAP = new HashMap<String, String>();
	private static final Map<String, String> WEEKMAP = new HashMap<String, String>();
	static {
		WEEKMAP.put("monday", "一");
		WEEKMAP.put("tuesday", "二");
		WEEKMAP.put("wednesday", "三");
		WEEKMAP.put("thursday", "四");
		WEEKMAP.put("friday", "五");
		WEEKMAP.put("saturday", "六");
		WEEKMAP.put("sunday", "日");

		WEEKMAP.put("2", "周一");
		WEEKMAP.put("3", "周二");
		WEEKMAP.put("4", "周三");
		WEEKMAP.put("5", "周四");
		WEEKMAP.put("6", "周五");
		WEEKMAP.put("7", "周六");
		WEEKMAP.put("1", "周日");

		MONTHMAP.put("1", "一");
		MONTHMAP.put("2", "二");
		MONTHMAP.put("3", "三");
		MONTHMAP.put("4", "四");
		MONTHMAP.put("5", "五");
		MONTHMAP.put("6", "六");
		MONTHMAP.put("7", "七");
		MONTHMAP.put("8", "八");
		MONTHMAP.put("9", "九");
		MONTHMAP.put("10", "十");
		MONTHMAP.put("11", "十一");
		MONTHMAP.put("12", "十二");
	}

	/**
	 * @Description 取得指定年份后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param yearAmount
	 *            指定年数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static Date addYear(Date date, int yearAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, yearAmount);
		return calendar.getTime();
	}

	/**
	 * @Description 取得指定月份后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param monthAmount
	 *            指定天数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static Date addMonth(Date date, int monthAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthAmount);
		return calendar.getTime();
	}

	/**
	 * @Description 取得指定天数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param dayAmount
	 *            指定天数，允许为负数
	 * @return 指定天数后的时间
	 */
	public static Date addDay(Date date, int dayAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dayAmount);
		return calendar.getTime();
	}

	/**
	 * @Description(功能描述) : 获取当前年份
	 * @return Integer
	 */
	public static Integer currYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @Description(功能描述) : 获取年份
	 * @return Integer
	 */
	public static Integer getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * @Description(功能描述) : 获取当前月份
	 * @return Integer
	 */
	public static Integer currMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * @Description(功能描述) : 获取月份
	 * @return Integer
	 */
	public static Integer getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * @Description(功能描述) : 获取月份(中文名)
	 * @return Integer
	 */
	public static String getChMonth(Date date) {
		String month = getMonth(date).toString();
		return MONTHMAP.get(month) + "月";
	}

	/**
	 * @Description(功能描述) : 获取天
	 * @return Integer
	 */
	public static String getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day > 10 ? String.valueOf(day) : "0" + day;
	}

	/**
	 * @Description 取得指定小时数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param hourAmount
	 *            指定小时数，允许为负数
	 * @return 指定小时数后的时间
	 */
	public static Date addHour(Date date, int hourAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hourAmount);
		return calendar.getTime();
	}

	/**
	 * @Description 取得指定分钟数后的时间
	 * 
	 * @param date
	 *            基准时间
	 * @param minuteAmount
	 *            指定分钟数，允许为负数
	 * @return 指定分钟数后的时间
	 */
	public static Date addMinute(Date date, int minuteAmount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minuteAmount);
		return calendar.getTime();
	}

	/**
	 * @Description 比较两日期对象中的小时和分钟部分的大小.
	 * 
	 * @param date
	 *            日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @param anotherDate
	 *            日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
	 */
	public static int compareHourAndMinute(Date date, Date anotherDate) {
		if (date == null) {
			date = new Date();
		}

		if (anotherDate == null) {
			anotherDate = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hourOfDay1 = cal.get(Calendar.HOUR_OF_DAY);
		int minute1 = cal.get(Calendar.MINUTE);

		cal.setTime(anotherDate);
		int hourOfDay2 = cal.get(Calendar.HOUR_OF_DAY);
		int minute2 = cal.get(Calendar.MINUTE);

		if (hourOfDay1 > hourOfDay2) {
			return 1;
		} else if (hourOfDay1 == hourOfDay2) {
			// 小时相等就比较分钟
			return minute1 > minute2 ? 1 : (minute1 == minute2 ? 0 : -1);
		} else {
			return -1;
		}
	}

	/**
	 * @Description 比较两日期对象的大小, 忽略秒, 只精确到分钟.
	 * 
	 * @param date
	 *            日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @param anotherDate
	 *            日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
	 * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
	 */
	public static int compareIgnoreSecond(Date date, Date anotherDate) {
		if (date == null) {
			date = new Date();
		}

		if (anotherDate == null) {
			anotherDate = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		date = cal.getTime();

		cal.setTime(anotherDate);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		anotherDate = cal.getTime();

		return date.compareTo(anotherDate);
	}

	/**
	 * @Description 取得当前时间的字符串表示，格式为2006-01-10 20:56:30
	 * 
	 * @return 当前时间的字符串表示
	 */
	public static String currentDate2String() {
		return date2String(new Date());
	}

	/**
	 * @Description 取得当前时间的字符串表示，格式为2006-01-10
	 * 
	 * @return 当前时间的字符串表示
	 */
	public static String currentDate2StringByDay() {
		return date2StringByDay(new Date());
	}

	/**
	 * @Description 取得今天的最后一个时刻
	 * 
	 * @return 今天的最后一个时刻
	 */
	public static Date currentEndDate() {
		return getEndDate(new Date());
	}

	/**
	 * @Description 取得今天的第一个时刻
	 * 
	 * @return 今天的第一个时刻
	 */
	public static Date currentStartDate() {
		return getStartDate(new Date());
	}

	/**
	 * @Description 把时间转换成字符串，格式为2006-01-10 20:56:30
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串
	 */
	public static String date2String(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @Description 按照指定格式把时间转换成字符串，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param date
	 *            时间
	 * @param pattern
	 *            格式
	 * @return 时间字符串
	 */
	public static String date2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return (new SimpleDateFormat(pattern)).format(date);
	}

	/**
	 * @Description 把时间转换成字符串，格式为2006-01-10
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串
	 */
	public static String date2StringByDay(Date date) {
		return date2String(date, "yyyy-MM-dd");
	}

	/**
	 * @Description 把时间转换成字符串，格式为2006-01-10 20:56
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串
	 */
	public static String date2StringByMinute(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * @Description 把时间转换成字符串，格式为2006-01-10 20:56:30
	 * 
	 * @param date
	 *            时间
	 * @return 时间字符串
	 */
	public static String date2StringBySecond(Date date) {
		return date2String(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @Description 根据某星期几的英文名称来获取该星期几的中文数. <br>
	 *              e.g. <li>monday -> 一</li> <li>sunday -> 日</li>
	 * 
	 * @param englishWeekName
	 *            星期的英文名称
	 * @return 星期的中文数
	 */
	public static String getChineseWeekNumber(String englishWeekName) {
		if (englishWeekName == null) {
			return null;
		}
		return WEEKMAP.get(englishWeekName.toLowerCase());
	}

	/**
	 * 
	 * @Description(功能描述) : 获取当前日期是星期几
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年9月24日 下午3:08:18
	 * @exception :
	 * @return String
	 */
	public static String getCurrentChineseWeek() {
		int d = getDayOfWeek(currentDate());
		return WEEKMAP.get(d + "");
	}

	/**
	 * @Description 根据指定的年, 月, 日等参数获取日期对象.
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date) {
		return getDate(year, month, date, 0, 0);
	}

	/**
	 * @Description 根据指定的年, 月, 日, 时, 分等参数获取日期对象.
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param hourOfDay
	 *            时(24小时制)
	 * @param minute
	 *            分
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute) {
		return getDate(year, month, date, hourOfDay, minute, 0);
	}

	/**
	 * @Description 根据指定的年, 月, 日, 时, 分, 秒等参数获取日期对象.
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param date
	 *            日
	 * @param hourOfDay
	 *            时(24小时制)
	 * @param minute
	 *            分
	 * @param second
	 *            秒
	 * @return 对应的日期对象
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, hourOfDay, minute, second);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * @Description 取得某个日期是星期几，星期日是1，依此类推
	 * 
	 * @param date
	 *            日期
	 * @return 星期几
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取某天的结束时间, e.g. 2005-10-01 23:59:59.999
	 * 
	 * @param date
	 *            日期对象
	 * @return 该天的结束时间
	 */
	public static Date getEndDate(Date date) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);

		return cal.getTime();
	}

	/**
	 * @Description 取得一个月最多的天数
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份，0表示1月，依此类推
	 * @return 最多的天数
	 */
	public static int getMaxDayOfMonth(int year, int month) {
		if (month == 1 && isLeapYear(year)) {
			return 29;
		}
		return DAY_OF_MONTH[month];
	}

	/**
	 * @Description 得到指定日期的下一天
	 * 
	 * @param date
	 *            日期对象
	 * @return 同一时间的下一天的日期对象
	 */
	public static Date getNextDay(Date date) {
		return addDay(date, 1);
	}

	/**
	 * 获取某天的起始时间, e.g. 2005-10-01 00:00:00.000
	 * 
	 * @param date
	 *            日期对象
	 * @return 该天的起始时间
	 */
	public static Date getStartDate(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 根据日期对象来获取日期中的时间(HH:mm:ss).
	 * 
	 * @param date
	 *            日期对象
	 * @return 时间字符串, 格式为: HH:mm:ss
	 */
	public static String getTime(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 根据日期对象来获取日期中的时间(HH:mm).
	 * 
	 * @param date
	 *            日期对象
	 * @return 时间字符串, 格式为: HH:mm
	 */
	public static String getTimeIgnoreSecond(Date date) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}

	/**
	 * @Description 判断是否是闰年
	 * 
	 * @param year
	 *            年份
	 * @return 是true，否则false
	 */
	public static boolean isLeapYear(int year) {
		Calendar calendar = Calendar.getInstance();
		return ((GregorianCalendar) calendar).isLeapYear(year);
	}

	/**
	 * @Description 把字符串转换成日期，格式为2006-01-10
	 * 
	 * @param str
	 *            字符串
	 * @return 日期
	 */
	public static Date string2Date(String str) {
		return string2Date(str, "yyyy-MM-dd");
	}

	/**
	 * @Description 按照指定的格式把字符串转换成时间，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
	 * 
	 * @param str
	 *            字符串
	 * @param pattern
	 *            格式
	 * @return 时间
	 */
	public static Date string2Date(String str, String pattern) {
		if (CommonUtil.isEmpty(str)) {
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * @Description 把字符串转换成日期，格式为2006-01-10 20:56:30
	 * 
	 * @param str
	 *            字符串
	 * @return 日期
	 */
	public static Date string2DateTime(String str) {
		return string2Date(str, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @Description 取得一年中的第几周。
	 * 
	 * @param date
	 *            日期
	 * @return int
	 */
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * @Description 获取上周的指定星期的日期。
	 * 
	 * @param dayOfWeek
	 *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
	 * @return Date
	 */
	public static Date getDateOfPreviousWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("参数必须是1-7之间的数字");
		}

		return getDateOfRange(dayOfWeek, -7);
	}

	/**
	 * @Description 获取本周的指定星期的日期。
	 * 
	 * @param dayOfWeek
	 *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
	 * @return Date
	 */
	public static Date getDateOfCurrentWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("参数必须是1-7之间的数字");
		}

		return getDateOfRange(dayOfWeek, 0);
	}

	/**
	 * @Description 获取下周的指定星期的日期。
	 * 
	 * @param dayOfWeek
	 *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
	 * @return Date
	 */
	public static Date getDateOfNextWeek(int dayOfWeek) {
		if (dayOfWeek > 7 || dayOfWeek < 1) {
			throw new IllegalArgumentException("参数必须是1-7之间的数字");
		}

		return getDateOfRange(dayOfWeek, 7);
	}

	/**
	 * 
	 * @Description(功能描述) : 获取下周的指定星期的日期
	 * @author(作者) ： panjun
	 * @date (开发日期) : 2015年2月12日 下午6:33:58
	 * @param dayOfWeek
	 *            :
	 * @param dayOfRange
	 *            :
	 * @return Date
	 */
	private static Date getDateOfRange(int dayOfWeek, int dayOfRange) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + dayOfRange);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 
	 * @Description(功能描述) : 获取当前日期(时分秒都为零)
	 * @author(作者) ： pengfei
	 * @date (开发日期) ： 2015年1月29日 上午9:28:48
	 * 
	 * @return Date
	 */
	public static Date currentDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0); // 获得时
		cal.set(Calendar.MINUTE, 0); // 获得分
		cal.set(Calendar.SECOND, 0); // 获得秒
		cal.set(Calendar.MILLISECOND, 0); // 获得秒
		return cal.getTime();
	}

	/**
	 * 
	 * @Description(功能描述) : 获取当前日期(包含时分秒)
	 * @author(作者) ： pengfei
	 * @date (开发日期) ： 2015年1月29日 上午9:28:59
	 * @return Date
	 */
	public static Date currentDateTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.getTime();
	}

	/**
	 * @Description(功能描述) : 把指定日期的十分秒设置为最大
	 * @author(作者) ： pengfei
	 * @date (开发日期) ： 2015年2月3日 下午4:57:39
	 * @param date
	 *            日期
	 * @return String
	 */
	public static String getDateEnd(final Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23); // 获得时
		cal.set(Calendar.MINUTE, 59); // 获得分
		cal.set(Calendar.SECOND, 59); // 获得秒
		cal.set(Calendar.MILLISECOND, 999); // 获得秒
		return date2String(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @Description(功能描述) : 将字符串转化成毫秒数
	 * @author(作者) ： yangxianzhao
	 * @date (开发日期) ： 2015年2月5日 下午4:13:19
	 * @param date
	 *            日期
	 * @return Long
	 */
	public static Long getTime(String date) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return c.getTimeInMillis();
	}

	/**
	 * @Description(功能描述) : 获取当前日期时间戳
	 * @author(作者) ： pengfei
	 * @date (开发日期) : 2015年2月9日 下午3:21:25
	 * @exception :
	 * @return Long
	 */
	public static Long currentTimeMillis() {
		return currentDateTime().getTime();
	}

	/**
	 * @Description(功能描述) : 计算两个日期相差天数
	 * @author(作者) ： pengfei
	 * @date (开发日期) : 2015年3月16日 下午7:39:58
	 * @exception :
	 * @param beginDate
	 *            :开始日期
	 * @param endDate
	 *            ：结束日期
	 * @return BigDecimal
	 */
	public static BigDecimal getBetweenDays(final Date beginDate, final Date endDate) {
		// 格式化到天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.format(endDate);
		// 获取结束日期
		long endTime = format.getCalendar().getTime().getTime();
		format.format(beginDate);
		// 获取开始日期
		long beginTime = format.getCalendar().getTime().getTime();
		return BigDecimal.valueOf((endTime - beginTime) / 86400000 + 1);
	}

	/**
	 * @Description(功能描述) : 计算两个日期相差小时数
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年3月16日 下午7:39:58
	 * @exception :
	 * @param beginDate
	 *            :开始日期
	 * @param endDate
	 *            ：结束日期
	 * @return BigDecimal
	 */
	public static BigDecimal getBetweenHours(final Date beginDate, final Date endDate) {
		// 格式化到天
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.format(endDate);
		// 获取结束日期
		long endTime = format.getCalendar().getTime().getTime();
		format.format(beginDate);
		// 获取开始日期
		long beginTime = format.getCalendar().getTime().getTime();
		return BigDecimal.valueOf((endTime - beginTime) / 3600000);
	}

	/**
	 * @Description(功能描述) : 将日期时间戳转换为字符串yyyy-MM-dd
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年2月9日 下午3:21:25
	 * @param mill
	 *            long
	 * @return String
	 */
	public static String convert(long mill) {
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	/**
	 * @Description(功能描述) : 将字符串yyyy-MM-dd转换为时间戳
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年2月9日 下午3:21:25
	 * @param str
	 *            String
	 * @return long
	 */
	public static Long convert(String str) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.getTimeInMillis();
	}

	/**
	 * 
	 * @Description(功能描述) : 将毫秒数转化为日期格式的字符串
	 * @author(作者) ： yangxianzhao
	 * @date (开发日期) : 2015年3月9日 上午11:43:20
	 * @param s
	 *            Long
	 * @param pattern
	 *            String
	 * @return String
	 */
	public static String timeToDate(Long s, String pattern) {
		if (s != null) {
			Date date = new Date(s);
			return new SimpleDateFormat(pattern).format(date);
		}
		return "";
	}

	/**
	 * 
	 * @Description(功能描述) : 将毫秒数转化为yyyy-MM-dd HH:mm:ss格式的字符串
	 * @author(作者) ： yangxianzhao
	 * @date (开发日期) : 2015年3月23日 上午11:03:31
	 * @param s
	 *            Long
	 * @return String
	 */
	public static String timeToDate(Long s) {
		return timeToDate(s, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @Description(功能描述) : 获取当月最后一天
	 * @author(作者) ： yangying
	 * @date (开发日期) : 2015年5月18日 下午6:11:57
	 * @return String
	 */
	public static String lastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 0);
		cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
		String lastDay = format.format(cale.getTime());
		return lastDay;
	}

	/**
	 * 
	 * @Description(功能描述) : 获取当月第一天
	 * @author(作者) ： yangying
	 * @date (开发日期) : 2015年5月18日 下午6:12:16
	 * @return String
	 */
	public static String firstDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}

	/**
	 * 
	 * @Description(功能描述) : yyyy-M-d转换为yyyy-MM-dd
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年9月2日 下午2:52:20
	 * @exception :
	 * @param m
	 * @return String
	 */
	public static String m2MM(String m) {
		if (CommonUtil.isEmpty(m))
			return "";
		Date string2Date = string2Date(m, "yyyy-M-d");
		String date2String = date2String(string2Date, "yyyy-MM-dd");
		return date2String;
	}

	/**
	 * 
	 * @Description(功能描述) : 字符串类型转换
	 * @author(作者) ： liuchunxu
	 * @date (开发日期) : 2015年10月15日 下午4:52:50
	 * @exception :
	 * @param dateStr
	 * @param from
	 * @param to
	 * @return String
	 */
	public static String str2str(String dateStr, String from, String to) {
		return DateUtils.date2String(DateUtils.string2Date(dateStr, from), to);
	}
}
