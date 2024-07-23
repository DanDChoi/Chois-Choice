package choi.choice.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtilz {

    //현재 날짜를 yyyy-MM-dd hh:mm:ss 형식으로 반환한다.
   	public static Timestamp getCurrentDateTime1() {
   		return convertToTimestamp(getCurrentDateString());
   	}

   	//현재 날짜를 yyyyMMddhhmmss 형식으로 반환한다.
   	public static String getCurrentDateTime2() {
   		return getCurrentDateString()+getCurrentTimeString();
   	}

   	//현재 날짜를 yyyyMMdd hh:mm:ss 형식으로 반환한다.
   	public static String getCurrentDateTimeString() {
   		return (getCurrentDateString("yyyyMMdd") + getCurrentDateString("HHmmssSSS"));
   	}

   	//현재 날짜를 yyyyMMdd 형식으로 반환한다.
   	public static String getCurrentDateString() {
   		return getCurrentDateString("yyyyMMdd");
   	}

   	//현재 시각을  HHmmss 형식으로 반환한다.
   	public static String getCurrentTimeString() {
   		return getCurrentDateString("HHmmss");
   	}

   	//현재날짜를 주어진 pattern 에 따라 반환한다.
   	public static String getCurrentDateString(String pattern) {
   		return convertToString(getCurrentTimeStamp(), pattern);
   	}

   	public static String getCurrentDateFirstDayString() {
   		String retuDate = "";
   		String currentlyDate = getTodayYearMonth(convertToString(getCurrentTimeStamp(), "yyyyMMdd"));
   		retuDate = currentlyDate + "01";
   		return retuDate;
   	}

   	public static String getCurrentDateLastDayString() {
   		String lastDayStr = "";
   		try {
   			lastDayStr = lastDayOfMonth(convertToString(getCurrentTimeStamp(), "yyyyMMdd"));
   		} catch (Exception e) {
   		}
   		return lastDayStr;
   	}

   	//yyyyMMdd 형식의 날짜를 yyyy/MM/dd 형식으로 반환한다.
   	public static String convertFormat(String dateData) {
   		return convertFormat(dateData, "yyyy/MM/dd");
   	}

   	public static String convertFormat2(String dateData) {
   		return convertFormat(dateData, "yyyy-MM-dd");
   	}

   	//yyyyMMdd 형식의 날짜를 yyyy/MM/dd 형식으로 반환한다.
   	public static String convertFormat(String dateData, String format) {
   		return convertToString(convertToTimestamp(dateData), format);
   	}

   	//yyyyMMdd 형식의 날짜를 yyyy/MM/dd 형식으로 반환한다.
   	public static Timestamp getCurrentTimeStamp() {
   		Calendar cal = new GregorianCalendar();
   		Timestamp result = new Timestamp(cal.getTime().getTime());
   		return result;
   	}

   	//yyyyMMdd 형식의 Timestamp 날짜를 yyyy/MM/dd 형식으로 반환한다.
   	public static String convertToString(Timestamp dateData) {
   		return convertToString(dateData, "yyyy/MM/dd");
   	}

   	//yyyyMMdd 형식의 Timestamp 날짜를 pattern 에 따른 형식으로 반환한다.
   	public static String convertToString(Timestamp dateData, String pattern) {
   		return convertToString(dateData, pattern, java.util.Locale.KOREA);
   	}

   	//yyyyMMdd 형식의 Timestamp 날짜를 pattern 과 locale 에 따른 형식으로 반환한다.
   	public static String convertToString(	Timestamp dateData,
   											String pattern,
   											java.util.Locale locale) {
   		if (dateData == null) {
   			return null;
   		}
   		SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
   		formatter.applyPattern( pattern );
   		return formatter.format(dateData);
   	}

   	//yyyyMMdd 형식의  날짜를 Timestamp 로  반환한다.
   	public static Timestamp convertToTimestamp(String dateData) {
   		if (dateData == null)
   			return null;
   		if (dateData.trim().equals(""))
   			return null;
   		int dateObjLength = dateData.length();
   		String yearString = "2002";
   		String monthString = "01";
   		String dayString = "01";
   		if (dateObjLength >= 4) {
   			yearString = dateData.substring(0, 4);
   		}
   		if (dateObjLength >= 6) {
   			monthString = dateData.substring(4, 6);
   		}
   		if (dateObjLength >= 8) {
   			dayString = dateData.substring(6, 8);
   		}
   		int year = Integer.parseInt(yearString);
   		int month = Integer.parseInt(monthString) - 1;
   		int day = Integer.parseInt(dayString);
   		Calendar cal = new GregorianCalendar();
   		cal.set(year, month, day);
   		//cal.getTime();
   		return new Timestamp(cal.getTime().getTime());
   	}

   	//yyyyMMddHHmmss 형식의  날짜시각을 Timestamp 로  반환한다.
   	public static Timestamp convertToTimestampHMS(String dateData) {
   		if (dateData == null)
   			return null;
   		if (dateData.trim().equals(""))
   			return null;
   		String yearString = dateData.substring(0, 4);
   		String monthString = dateData.substring(4, 6);
   		String dayString = dateData.substring(6, 8);
   		String hourString = dateData.substring(8, 10);
   		String minString = dateData.substring(10, 12);
   		String secString = dateData.substring(12, 14);
   		int year = Integer.parseInt(yearString);
   		int month = Integer.parseInt(monthString) - 1;
   		int day = Integer.parseInt(dayString);
   		int hour = Integer.parseInt(hourString);
   		int min = Integer.parseInt(minString);
   		int sec = Integer.parseInt(secString);
   		Calendar cal = new GregorianCalendar();
   		cal.set(year, month, day, hour, min, sec);
   		return new Timestamp(cal.getTime().getTime());
   	}

   	public static String timestampHMSOfName(String dateData) {
   		if (dateData == null)
   			return null;
   		if (dateData.trim().equals(""))
   			return null;
   		String yearString = dateData.substring(0, 4);
   		String monthString = dateData.substring(4, 6);
   		String dayString = dateData.substring(6, 8);
   		String hourString = dateData.substring(8, 10);
   		String minString = dateData.substring(10, 12);
   		String secString = dateData.substring(12, 14);
   		String makeStr =	yearString	+ monthString + dayString + hourString + minString	+ secString;
   		return makeStr;
   	}

   	//check date string validation with an user defined format.
   	private static java.util.Date check(String s, String format)
   		throws java.text.ParseException {

   		if (s == null)
   			throw new java.text.ParseException("date string to check is null",	0);

   		if (format == null)
   			throw new java.text.ParseException("format string to check date is null", 0);

   		java.text.SimpleDateFormat formatter =	new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
   		java.util.Date date = null;

   		try {
   			date = formatter.parse(s);
   		} catch (java.text.ParseException e) {
   			throw new java.text.ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
   		}

   		if (!formatter.format(date).equals(s))
   			throw new java.text.ParseException("Out of bound date:\""	+ s + "\" with format \""	+ format + "\"", 0);

   		return date;
   	}

   	//check date string validation with the default format "yyyyMMdd".
   	public static boolean isValid(String s) {
   		return DateUtilz.isValid(s, "yyyyMMdd");
   	}

   	//check date string validation with an user defined format.
   	public static boolean isValid(String s, String format) {
   		/*
   		if ( s == null )
   		  throw new NullPointerException("date string to check is null");
   		if ( format == null )
   		  throw new NullPointerException("format string to check date is null");
   		*/
   		java.text.SimpleDateFormat formatter =
   			new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
   		java.util.Date date = null;
   		try {
   			date = formatter.parse(s);
   		} catch (java.text.ParseException e) {
   			return false;
   		}
   		if (!formatter.format(date).equals(s))
   			return false;
   		return true;
   	}

   	/**
   	 * @title	days between two date strings with default defined format.(yyyyMMdd)
   	 *
   	 * @param s : date string you want to check.
   	 * @return	 :	int 날짜 형식이 맞고, 존재하는 날짜일 때 요일을 리턴
   	 *           	형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
   	 *           	0: 일요일 (java.util.Calendar.SUNDAY 와 비교)
   	 *           	1: 월요일 (java.util.Calendar.MONDAY 와 비교)
   	 *           	2: 화요일 (java.util.Calendar.TUESDAY 와 비교)
   	 *           	3: 수요일 (java.util.Calendar.WENDESDAY 와 비교)
   	 *           	4: 목요일 (java.util.Calendar.THURSDAY 와 비교)
   	 *           	5: 금요일 (java.util.Calendar.FRIDAY 와 비교)
   	 *           	6: 토요일 (java.util.Calendar.SATURDAY 와 비교)
   	 * 			  	예) String s = "20000529";
   	 * 			    int dayOfWeek = whichDay(s, format);
   	 *			    if (dayOfWeek == java.util.Calendar.MONDAY)
   	 *			    	log.debug(" 월요일: " + dayOfWeek);
   	 * 			    if (dayOfWeek == java.util.Calendar.TUESDAY)
   	 *			    	log.debug(" 화요일: " + dayOfWeek);
   	 */
   	public static int whichDay(String s) throws ParseException {
   		return whichDay(s, "yyyyMMdd");
   	}

   	public static int whichDay(String s, String format) throws ParseException {
   		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
   		java.util.Date date = check(s, format);
   		java.util.Calendar calendar = formatter.getCalendar();
   		calendar.setTime(date);
   		return calendar.get(java.util.Calendar.DAY_OF_WEEK);
   	}

   	public static String whichDayStr(String s, String format)
   		throws ParseException {
   		int ct = whichDay(s, format);
   		String ctStr = "";
   		if (ct == 2)
   			ctStr = "월";
   		else if (ct == 3)
   			ctStr = "화";
   		else if (ct == 4)
   			ctStr = "수";
   		else if (ct == 5)
   			ctStr = "목";
   		else if (ct == 6)
   			ctStr = "금";
   		else if (ct == 7)
   			ctStr = "토";
   		else if (ct == 1)
   			ctStr = "일";
   		return ctStr;
   	}

   	public static String getWhatDay(String yyyymmdd) {
   		int year = StringUtilz.strToInt(StringUtilz.substr(yyyymmdd, 0, 4));
   		int month = StringUtilz.strToInt(StringUtilz.substr(yyyymmdd, 4, 6));
   		int day = StringUtilz.strToInt(StringUtilz.substr(yyyymmdd, 6, 8));
   		if (month == 1 || month == 2)
   			year--;
   		month = (month + 9) % 12 + 1;
   		int y = year % 100;
   		int century = year / 100;
   		int week = ((13 * month - 1) / 5
   						+ day
   						+ y
   						+ y / 4
   						+ century / 4
   						- 2 * century)
   						% 7;
   		if (week < 0)
   			week = (week + 7) % 7;
   		String ch_week = "";
   		switch (week) {
   			case 0 :
   				ch_week = "SAT";
   				break;
   			case 1 :
   				ch_week = "SUN";
   				break;
   			case 2 :
   				ch_week = "MON";
   				break;
   			case 3 :
   				ch_week = "TUE";
   				break;
   			case 4 :
   				ch_week = "WEB";
   				break;
   			case 5 :
   				ch_week = "THU";
   				break;
   			case 6 :
   				ch_week = "FRI";
   				break;
   		}
   		return ch_week;
   	}

   	public static String getWhatDay2(String yyyymmdd) {
   		int year = StringUtilz.strToInt(StringUtilz.substr(yyyymmdd, 0, 4));
   		int month = StringUtilz.strToInt(StringUtilz.substr(yyyymmdd, 4, 6));
   		int day = StringUtilz.strToInt(StringUtilz.substr(yyyymmdd, 6, 8));
   		java.util.Calendar c = java.util.Calendar.getInstance();
   		/*
   		year - the value used to set the YEAR time field.
   		month - the value used to set the MONTH time field. Month value is 0-based. e.g., 0 for January.
   		date - the value used to set the DATE time field.
   		*/
   		c.clear();
   		c.set(year, month, day);
   		String ch_week = "";
   		switch (c.get(Calendar.DAY_OF_WEEK)) {
   			case java.util.Calendar.SUNDAY :
   				ch_week = "SUN";
   				break;
   			case java.util.Calendar.MONDAY :
   				ch_week = "MON";
   				break;
   			case java.util.Calendar.TUESDAY :
   				ch_week = "TUE";
   				break;
   			case java.util.Calendar.WEDNESDAY :
   				ch_week = "WEB";
   				break;
   			case java.util.Calendar.THURSDAY :
   				ch_week = "THU";
   				break;
   			case java.util.Calendar.FRIDAY :
   				ch_week = "FRI";
   				break;
   			case java.util.Calendar.SATURDAY :
   				ch_week = "SAT";
   				break;
   		}
   		return ch_week;
   	}

   	public static int daysBetween(String from, String to)
   		throws ParseException {
   		return daysBetween(from, to, "yyyyMMdd");
   	}

   	public static int daysBetween(String from, String to, String format)
   		throws ParseException {
   		//java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
   		java.util.Date d1 = check(from, format);
   		java.util.Date d2 = check(to, format);
   		long duration = d2.getTime() - d1.getTime();
   		return (int) (duration / (1000 * 60 * 60 * 24)); // seconds in 1 day
   	}

   	public static int yearsBetween(String from, String to)
   		throws ParseException {
   		return yearsBetween(from, to, "yyyyMMdd");
   	}

   	public static int yearsBetween(String from, String to, String format)
   		throws ParseException {
   		return (int) (daysBetween(from, to, format) / 365);
   	}

   	public static String addDays(String s, int day) throws ParseException {
   		return addDays(s, day, "yyyyMMdd");
   	}

   	public static String addDays(String s, int day, String format)
   		throws ParseException {
   		java.text.SimpleDateFormat formatter =	new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
   		java.util.Date date = check(s, format);
   		date.setTime(date.getTime() + ((long) day * 1000 * 60 * 60 * 24));
   		return formatter.format(date);
   	}

   	public static String addMonths(String s, int month) throws ParseException {
   		return addMonths(s, month, "yyyyMMdd");
   	}

   	public static String addMonths(String s, int addMonth, String format)
   		throws ParseException {
   		java.text.SimpleDateFormat formatter =	new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
   		java.util.Date date = check(s, format);
   		java.text.SimpleDateFormat yearFormat =	new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
   		java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
   		java.text.SimpleDateFormat dayFormat = new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);
   		int year = Integer.parseInt(yearFormat.format(date));
   		int month = Integer.parseInt(monthFormat.format(date));
   		int day = Integer.parseInt(dayFormat.format(date));
   		month += addMonth;
   		if (addMonth > 0) {
   			while (month > 12) {
   				month -= 12;
   				year += 1;
   			}
   		} else {
   			while (month <= 0) {
   				month += 12;
   				year -= 1;
   			}
   		}
   		java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
   		java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
   		String tempDate = String.valueOf(fourDf.format(year))	+ String.valueOf(twoDf.format(month)) + String.valueOf(twoDf.format(day));
   		java.util.Date targetDate = null;
   		try {
   			targetDate = check(tempDate, "yyyyMMdd");
   		} catch (java.text.ParseException pe) {
   			day = lastDay(year, month);
   			tempDate = String.valueOf(fourDf.format(year)) + String.valueOf(twoDf.format(month)) + String.valueOf(twoDf.format(day));
   			targetDate = check(tempDate, "yyyyMMdd");
   		}
   		return formatter.format(targetDate);
   	}

   	public static String addYears(String s, int year) throws ParseException {
   		return addYears(s, year, "yyyyMMdd");
   	}

   	public static String addYears(String s, int year, String format)
   		throws ParseException {
   		java.text.SimpleDateFormat formatter =	new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
   		java.util.Date date = check(s, format);
   		date.setTime(date.getTime() + ((long) year * 1000 * 60 * 60 * 24 * (365)));
   		return formatter.format(date);
   	}

   	//months between two date strings
   	public static int monthsBetween(String from, String to)
   		throws ParseException {
   		return monthsBetween(from, to, "yyyyMMdd");
   	}

   	//months between two date strings with user defined format.
   	//int 날짜 형식이 맞고, 존재하는 날짜일 때 2개 일자 사이의개월수  리턴
   	//형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
   	public static int monthsBetween(String from, String to, String format)
   		throws ParseException {
   		//java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat (format, java.util.Locale.KOREA);
   		java.util.Date fromDate = check(from, format);
   		java.util.Date toDate = check(to, format);
   		// if two date are same, return 0.
   		if (fromDate.compareTo(toDate) == 0)
   			return 0;
   		java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
   		java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
   		java.text.SimpleDateFormat dayFormat = new java.text.SimpleDateFormat("dd", java.util.Locale.KOREA);
   		int fromYear = Integer.parseInt(yearFormat.format(fromDate));
   		int toYear = Integer.parseInt(yearFormat.format(toDate));
   		int fromMonth = Integer.parseInt(monthFormat.format(fromDate));
   		int toMonth = Integer.parseInt(monthFormat.format(toDate));
   		int fromDay = Integer.parseInt(dayFormat.format(fromDate));
   		int toDay = Integer.parseInt(dayFormat.format(toDate));
   		int result = 0;
   		result += ((toYear - fromYear) * 12);
   		result += (toMonth - fromMonth);
   		if (((toDay - fromDay) < 0) ) {
   			result += fromDate.compareTo(toDate); // ceil�� floor�� ȿ��
   		}
   		if (((toDay - fromDay) > 0))
   			result += toDate.compareTo(fromDate);
   		return result;
   	}

   	//그달의 마지말 날을 구함
   	public static String lastDayOfMonth(String src) throws ParseException {
   		return lastDayOfMonth(src, "yyyyMMdd");
   	}

   	//그달의 마지말 날을 구함
   	//String 날짜 형식이 맞고, 존재하는 날짜일 때 그달의 마지말 날을 구함
   	//형식이 잘못 되었거나 존재하지 않는 날짜: java.text.ParseException 발생
   	public static String lastDayOfMonth(String src, String format)
   		throws ParseException {
   		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format, java.util.Locale.KOREA);
   		java.util.Date date = check(src, format);
   		java.text.SimpleDateFormat yearFormat = new java.text.SimpleDateFormat("yyyy", java.util.Locale.KOREA);
   		java.text.SimpleDateFormat monthFormat = new java.text.SimpleDateFormat("MM", java.util.Locale.KOREA);
   		int year = Integer.parseInt(yearFormat.format(date));
   		int month = Integer.parseInt(monthFormat.format(date));
   		int day = lastDay(year, month);
   		java.text.DecimalFormat fourDf = new java.text.DecimalFormat("0000");
   		java.text.DecimalFormat twoDf = new java.text.DecimalFormat("00");
   		String tempDate = String.valueOf(fourDf.format(year))
   								+ String.valueOf(twoDf.format(month))
   								+ String.valueOf(twoDf.format(day));
   		java.util.Date targetDate = check(tempDate, "yyyyMMdd");
   		return formatter.format(targetDate);
   	}

   	//해당년도,월의 마직막 일자를 구하기
   	private static int lastDay(int year, int month)
   		throws java.text.ParseException {
   		int day = 0;
   		switch (month) {
   			case 1 :
   			case 3 :
   			case 5 :
   			case 7 :
   			case 8 :
   			case 10 :
   			case 12 :
   				day = 31;
   				break;
   			case 2 :
   				if ((year % 4) == 0) {
   					if ((year % 100) == 0 && (year % 400) != 0) {
   						day = 28;
   					} else {
   						day = 29;
   					}
   				} else {
   					day = 28;
   				}
   				break;
   			default :
   				day = 30;
   		}
   		return day;
   	}

   	//현제일자를 YYYYMMDD 형식으로 리턴
   	public static String getTodayYear() {
   		Calendar p_date = Calendar.getInstance();
   		int Year = p_date.get(Calendar.YEAR);
   		int Month = p_date.get(Calendar.MONTH) + 1;
   		int Day = p_date.get(Calendar.DATE);
   		return Year
   					+ ((Month < 10) ? "0" + Month : "" + Month)
   					+ ((Day < 10) ? "0" + Day : "" + Day);
   	}

   	public static String getTodayYearType1Conv() {
   		Calendar p_date = Calendar.getInstance();
   		int Year = p_date.get(Calendar.YEAR);
   		int Month = p_date.get(Calendar.MONTH) + 1;
   		int Day = p_date.get(Calendar.DATE);
   		return Year + " - " + Month + " - " + Day; //+"-";
   	}

   	public static String getTodayYearType2Conv() {
   		Calendar p_date = Calendar.getInstance();
   		int Year = p_date.get(Calendar.YEAR);
   		int Month = p_date.get(Calendar.MONTH) + 1;
   		int Day = p_date.get(Calendar.DATE);
   		return Year + ". " + Month + ". " + Day;
   	}

   	//현제일자를 YYYY-MM-DD 형식으로 리턴
   	public static String getTodayYearStr() {
   		Calendar p_date = Calendar.getInstance();
   		int Year = p_date.get(Calendar.YEAR);
   		int Month = p_date.get(Calendar.MONTH) + 1;
   		int Day = p_date.get(Calendar.DATE);
   		return Year
   					+ "-"
   					+ ((Month < 10) ? "0" + Month : "" + Month)
   					+ "-"
   					+ ((Day < 10) ? "0" + Day : "" + Day);
   	}

   	//YYYYMMDD -> YYYYMM
   	public static String getTodayYearMonth(String yyyymmdd) {
   		String Year = StringUtilz.substr(yyyymmdd, 0, 4);
   		String Month = StringUtilz.substr(yyyymmdd, 4, 6);
   		return Year + Month;
   	}

   	//YYYY.MM.DD -> YYYYMMDD
   	public static String getYearMonType1Conv(String yyyymmdd) {
   		String Year = StringUtilz.substr(yyyymmdd, 0, 4);
   		String Month = StringUtilz.substr(yyyymmdd, 5, 7);
   		String Day = StringUtilz.substr(yyyymmdd, 8, 10);
   		return Year + "" + Month + "" + Day;
   	}

   	//YYYYMM -> YYYY-MM
   	public static String getYearMonDayConversion(String yyyymmddStr) {
   		String Year = StringUtilz.substr(yyyymmddStr, 0, 4);
   		String Month = StringUtilz.substr(yyyymmddStr, 4, 6);
   		return Year + " - " + Month; // + " - ";
   	}

   	//YYYY.MM.DD -> YYYYMMDD
   	public static String getYearMonType2Conv(String yyyymmdd) {
   		String Year = StringUtilz.substr(yyyymmdd, 0, 4);
   		String Month = StringUtilz.substr(yyyymmdd, 4, 6);
   		String Day = StringUtilz.substr(yyyymmdd, 6, 8);
   		return Year
   					+ " - "
   					+ StringUtilz.strToInt(Month)
   					+ " - "
   					+ StringUtilz.strToInt(Day);
   	}

   	//YYYYMMDD -> YYYY-MM-DD
   	public static String getYearMonthDay(String yyyymmdd) {
   		String Year = StringUtilz.substr(yyyymmdd, 0, 4);
   		String Month = StringUtilz.substr(yyyymmdd, 4, 6);
   		String Day = StringUtilz.substr(yyyymmdd, 6, 8);
   		return Year + "-" + Month + "-" + Day;
   	}

   	//YYYYMMDD -> YYYY-MM-DD
   	public static String getYearMonthDayDelimiter(	String yyyymmdd,
   													String delimiter) {
   		String Year = StringUtilz.substr(yyyymmdd, 0, 4);
   		String Month = StringUtilz.substr(yyyymmdd, 4, 6);
   		String Day = StringUtilz.substr(yyyymmdd, 6, 8);
   		return Year + delimiter + Month + delimiter + Day;
   	}

   	//달의 마지막일자 구하기
   	public static int getMonthMaxday(int year, int month) {
   		switch (month) {
   			case 1 :
   			case 3 :
   			case 5 :
   			case 7 :
   			case 8 :
   			case 10 :
   			case 12 :
   				return (31);
   			case 4 :
   			case 6 :
   			case 9 :
   			case 11 :
   				return (30);
   			default :
   				if (((year % 4 == 0) && (year % 100 != 0))
   					|| (year % 400 == 0))
   					return (29);
   				else
   					return (28);
   		}
   	}

   	//년,월,일 8자리 보정 -> YYYYMMDD
   	public static String dateZero(int yyyy, int mm, int dd) {
   		String formatStr = "";
   		formatStr =	yyyy
   						+ ((mm < 10) ? "0" + mm : "" + mm)
   						+ ((dd < 10) ? "0" + dd : "" + dd);
   		return formatStr;
   	}

   	//년도 4자리 보정
   	public static String yearZero(int yyyy) {
   		String formatStr = "";
   		formatStr = "" + yyyy;
   		return formatStr;
   	}

   	//월 2자리 보정
   	public static String monthZero(int mm) {
   		String formatStr = "";
   		formatStr = ((mm < 10) ? "0" + mm : "" + mm);
   		return formatStr;
   	}

   	//일 2자리 보정
   	public static String dayZero(int dd) {
   		String formatStr = "";
   		formatStr = ((dd < 10) ? "0" + dd : "" + dd);
   		return formatStr;
   	}

   	//현제년도 구하기
   	public static String getDefaultYear() {
   		Calendar p_date = Calendar.getInstance();
   		int Year = p_date.get(Calendar.YEAR);
   		return yearZero(Year);
   	}

   	//현제월 구하기
   	public static String getDefaultMonth() {
   		Calendar p_date = Calendar.getInstance();
   		int Month = p_date.get(Calendar.MONTH) + 1;
   		return monthZero(Month);
   	}

   	//현제일 구하기
   	public static String getDefaultDay() {
   		Calendar p_date = Calendar.getInstance();
   		int Day = p_date.get(Calendar.DATE);
   		return monthZero(Day);
   	}

   	public static String num2zero(int num) {
   		String formatStr = "";
   		formatStr = ((num < 10) ? "0" + num : "" + num);
   		return formatStr;
   	}

   	//현재 시각을 얻는다.
   	public static String getNow() {
   		Calendar insCalendar = Calendar.getInstance();
   		String mstrHour = String.valueOf(insCalendar.get(Calendar.HOUR_OF_DAY));
   		String mstrMin = String.valueOf(insCalendar.get(Calendar.MINUTE));
   		String mstrSec = String.valueOf(insCalendar.get(Calendar.SECOND));
   		if (mstrHour.length() == 1) {
   			mstrHour = "0" + mstrHour;
   		}
   		if (mstrMin.length() == 1) {
   			mstrMin = "0" + mstrMin;
   		}
   		if (mstrSec.length() == 1) {
   			mstrSec = "0" + mstrSec;
   		}
   		return mstrHour + mstrMin + mstrSec;
   	}

   	//현재 요일을 얻는다.
   	//SUNDAY=1 ~ SATURDAY=7 까지의 숫자반환
   	public static int getCurrentDay() {
   		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
   	}

   	//오늘날짜의 년도, 월, 일을 24시간 형식에 맞게 반환합니다
   	public static String getDate() {
   		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
   		String dateStr = sdf.format(new Date());
   		return dateStr;
   	}

   	//오늘날짜의 년도를 반환합니다
   	public static String getYear() {
   		String dateStr = getDate();
   		StringTokenizer st = new StringTokenizer(dateStr, ".");
   		return st.nextToken();
   	}

   	//오늘날짜의 월을 반환합니다
   	public static String getMonth() {
   		String dateStr = getDate();
   		StringTokenizer st = new StringTokenizer(dateStr, ".");
   		st.nextToken();
   		return st.nextToken();
   	}

   	//오늘날짜의 일을 반환합니다
   	public static String getDay() {
   		String dateStr = getDate();
   		StringTokenizer st = new StringTokenizer(dateStr, ".");
   		st.nextToken();
   		st.nextToken();
   		return st.nextToken();
   	}

   	//현재 시간을 2자리로 리턴합니다
   	public static String getHour() {
   		SimpleDateFormat sdf = new SimpleDateFormat("kk");
   		return sdf.format(new Date());
   	}

   	//현재 분을 2자리로 리턴합니다
   	public static String getMinutes() {
   		SimpleDateFormat sdf = new SimpleDateFormat("mm");
   		return sdf.format(new Date());
   	}

   	//현재 초를 2자리로 리턴합니다
   	public static String getSeconds() {
   		SimpleDateFormat sdf = new SimpleDateFormat("ss");
   		return sdf.format(new Date());
   	}

   	//현재 시간의 밀리초를 리턴합니다
   	public static String getMilliSeconds() {
   		SimpleDateFormat sdf = new SimpleDateFormat("SSS");
   		return sdf.format(new Date());
   	}

   	/**
   	 * @title	 초를 분형식으로 리턴합니다
   	 * @param
   	 * @return	 S 형식의 밀리초
   	 */
   	public static String getMin_form(int ss) {
   		String hour = null;
   		String minute = null;
   		String seconds = null;
   		String hhmmss = null;
   		int temp_ss = 0;
   		hour = String.valueOf(ss / 3600);
   		temp_ss = ss % 3600;
   		minute = String.valueOf(temp_ss / 60);
   		seconds = String.valueOf(temp_ss % 60);
   		if (hour.length() == 1)
   			hour = "0" + hour;
   		if (minute.length() == 1)
   			minute = "0" + minute;
   		if (seconds.length() == 1)
   			seconds = "0" + seconds;
   		hhmmss = hour + ":" + minute + ":" + seconds;
   		return hhmmss;
   	}

   	public static String getNowYear() {
   		Calendar calendar = Calendar.getInstance();
   		return (new Integer(calendar.get(1))).toString();
   	}

   	public static String getNowMonth() {
   		Calendar calendar = Calendar.getInstance();
   		return (new Integer(calendar.get(2) + 1)).toString();
   	}

   	public static String getNowDay() {
   		Calendar calendar = Calendar.getInstance();
   		return (new Integer(calendar.get(5))).toString();
   	}

   	public static String getOneWeekBeforeYear() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(5, -7);
   		return (new Integer(calendar.get(1))).toString();
   	}

   	public static String getOneWeekBeforeMonth() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(5, -7);
   		return (new Integer(calendar.get(2) + 1)).toString();
   	}

   	public static String getOneWeekBeforeDay() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(5, -7);
   		return (new Integer(calendar.get(5))).toString();
   	}

   	public static String getOneMonthBeforeYear() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(2, -1);
   		return (new Integer(calendar.get(1))).toString();
   	}

   	public static String getOneMonthBeforeMonth() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(2, -1);
   		return (new Integer(calendar.get(2) + 1)).toString();
   	}

   	public static String getOneMonthBeforeDay() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(2, -1);
   		return (new Integer(calendar.get(5))).toString();
   	}

   	public static String getOneMonthAfterMonth() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(2, 1);
   		return (new Integer(calendar.get(2) + 1)).toString();
   	}

   	public static String getEndDay() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(5, -7);
   		return (new Integer(calendar.get(5))).toString();
   	}

   	public static String getThreeMonthBeforeYear() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(2, -3);
   		return (new Integer(calendar.get(1))).toString();
   	}

   	public static String getThreeMonthBeforeMonth() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(2, -3);
   		return (new Integer(calendar.get(2) + 1)).toString();
   	}

   	public static String getThreeMonthBeforeDay() {
   		Calendar calendar = Calendar.getInstance();
   		calendar.add(2, -3);
   		return (new Integer(calendar.get(5))).toString();
   	}

   	/**
   	 * @title	 달의 영문이름을 가져오자
   	 * @param
   	 * @return
   	 */
   	public static String getEngMonth(int month) {
   		String selMonth = "";
   		switch (month) {
   			case 1 :
   				selMonth = "January";
   				break;
   			case 2 :
   				selMonth = "February";
   				break;
   			case 3 :
   				selMonth = "March";
   				break;
   			case 4 :
   				selMonth = "April";
   				break;
   			case 5 :
   				selMonth = "May";
   				break;
   			case 6 :
   				selMonth = "June";
   				break;
   			case 7 :
   				selMonth = "July";
   				break;
   			case 8 :
   				selMonth = "August";
   				break;
   			case 9 :
   				selMonth = "September";
   				break;
   			case 10 :
   				selMonth = "October";
   				break;
   			case 11 :
   				selMonth = "November";
   				break;
   			case 12 :
   				selMonth = "December";
   				break;
   		}
   		return selMonth;
   	}

   	 public static String getToday() {
   		 Calendar insCalendar = Calendar.getInstance();
   		 String strToday = getDate( insCalendar );
   		 return strToday;
   	}

   	public static String getDate( Calendar insCalendar ) {
           String strMonth = "0" + String.valueOf( insCalendar.get( Calendar.MONTH ) + 1)
                 					,strDay   = "0" + String.valueOf( insCalendar.get( Calendar.DATE  ) )
                 					,strDate  = String.valueOf( insCalendar.get( Calendar.YEAR  ) ) +
                 					strMonth.substring( strMonth.length() - 2 )         +
                 					strDay.substring( strDay.length() - 2 );
           return strDate;
       }

   	/**
   	 * @title	 	'HH24MISS' 형식을 '오전HH시MI분SS초' 형식으로 바꾼다
   	 *
   	 * @param		바꿀 시각 String
   	 * 				변환 타입: 	'HAN' : '오전/후 HH시MIN분SS초'
   	 * 							'ENG' : 'AM/PM HH:MIN:SS'
   	 * 							'ETC' : 'HH24:MIN:SS'
   	 * 				초 표시여부:''        : 초 표시됨
   	 * 							 NO_SEC'  : 초 표시 생략
   	 * @return 	바뀐 시각 String
   	 */
   	public static String convTime(	String strTime,
       								String strType,
       								String strNeedSec ) {
           String	strConverted	= null
                 		,strAM		= null
                 		,strPM		= null
                 		,strHour	= null
                 		,strMin		= null
                 		,strSec		= null ;
           int iSecEndPos = 6;
           if ( strType.equals( "HAN" ) ) {
               strAM   = "오전";
               strPM   = "오후";
               strHour = "시";
               strMin  = "분";
               strSec  = "초";
           } else if ( strType.equals( "ENG" ) ) {
               strAM = "AM";
               strPM = "PM";
               strHour = strMin = ":";
               strSec = "";
           } else {
               strAM = "";
               strPM = "";
               strHour = strMin = ":";
               strSec = "";
           }

           if ( strNeedSec.equals( "NO_SEC" ) ) {
               iSecEndPos = 4;
               strSec = "";
               if ( !strType.equals( "HAN" ) )
                   strMin = "";
           }

           if ( strTime.length() == 6 ) {
           	//오전이거나 HH24 포맷이면 그냥 변환
               if ( strTime.substring( 0, 2 ).compareTo( "12" ) < 0 || strAM.equals( "" ) )
                   if ( strAM.equals( "" ) )
                   	// 앞에 0이 붙도록 변환
                       strConverted = strAM + strTime.substring( 0, 2 );
                   else
                   	// 앞에 0 제거
                       strConverted = strAM + String.valueOf(Integer.parseInt( strTime.substring( 0, 2 ) ) );
               //오후면 12를 빼서 변환
               else {
                   if ( strTime.substring( 0, 2 ).equals( "12" ) )
                       strConverted = strPM +"12";
                   else
                       strConverted = strPM + String.valueOf(Integer.parseInt( strTime.substring( 0, 2 ) ) - 12 );
               }
               strConverted = 	strConverted +
               						strHour +
               						strTime.substring( 2, 4 ) +
               						strMin  +
               						strTime.substring( 4, iSecEndPos ) +
               						strSec;
           } else
               strConverted = "&nbsp";
           return strConverted;
       }

       public static String convTime( String strTime, String strType ) {
           return convTime( strTime, strType, "NO_SEC" );
       }
       public static String convTime( String strTime ) {
           return convTime( strTime, "ETC", "NO_SEC" );
       }

       /**
        * add months
        *
        * @param s String
        * @param addMonth int
        * @param format String
        * @return Date
        */
       public static Date addMonths_US(String s, int addMonth, String format) throws Exception {
           SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.US);
           Date date = formatter.parse(s);
           SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.US);
           SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.US);
           SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.US);
           int year = Integer.parseInt(yearFormat.format(date));
           int month = Integer.parseInt(monthFormat.format(date));
           int day = Integer.parseInt(dayFormat.format(date));
           month += addMonth;
           if (addMonth > 0) {
               while (month > 12) {
                   month -= 12;
                   year += 1;
               }
           } else {
               while (month <= 0) {
                   month += 12;
                   year -= 1;
               }
           }
           DecimalFormat fourDf = new DecimalFormat("0000");
           DecimalFormat twoDf = new DecimalFormat("00");
           String tempDate = String.valueOf(fourDf.format(year)) +
           						String.valueOf(twoDf.format(month)) +
           						String.valueOf(twoDf.format(day));
           Date targetDate = formatter.parse(tempDate);
           if (!formatter.format(targetDate).equals(tempDate)) {
               day = lastDay(year, month);
               tempDate = String.valueOf(fourDf.format(year)) +
               				String.valueOf(twoDf.format(month)) +
               				String.valueOf(twoDf.format(day));
               targetDate = formatter.parse(tempDate);
           }
           return targetDate;
       }

       /**
        * add day
        * month, day string. ex) calDate("20060607", 1) --> 20060608
        *
        * @return String
        */
       public static String calDate(String date, int addDay){
       	Calendar cal = Calendar.getInstance();
       	long   millis;
           int  year   = Integer.parseInt(date.substring(0,4));
           int  month  = Integer.parseInt(date.substring(4,6));
           int  day   = Integer.parseInt(date.substring(6,8));
           long termMillis = addDay * 1000 * 60 * 60 * 24 ;
           cal.set(year, month-1, day);
           millis    = cal.getTimeInMillis() + termMillis;
           cal.setTimeInMillis(millis);
           return (new SimpleDateFormat("yyyyMMdd").format(cal.getTime()));
       }

       public static boolean isFloat(String str) {
   		for (int i = 0; i < str.length(); i++) {
   			if ((str.charAt(i) < '0' || str.charAt(i) > '9') && str.charAt(i) == '-' && str.charAt(i) == '.')
   				return false;
   		}
   		return true;
   	}

   	public static int strToInt(String num) {
   		int intNumber = 0;
   		if (num == null) {
   			num = "0";
   		}
   		try {
   			intNumber = Integer.parseInt(num);
   		} catch (NumberFormatException e) {
   		}

   		return intNumber;
   	}

   	public static int strToInt(String num, int def) {

   		int intNumber = 0;

   		if (num == null) {
   			num = def + "";
   		}

   		try {
   			intNumber = Integer.parseInt(num);
   		} catch (NumberFormatException e) {
   		}

   		return intNumber;
   	}

   	public static double strToDouble(String num) {
   		double doubleNumber = 0;

   		if (num==null || num.equals("0"))
   			num = "0.0";

   		try {
   			doubleNumber = Double.parseDouble(num);
   		} catch (NumberFormatException e) {
   			throw e;
   		}

   		return doubleNumber;
   	}

   	public static float strToFloat(String num) {
   		float floatNumber = 0;

   		if (num==null || num.equals("0"))
   			num = "0.0";

   		try {
   			floatNumber = Float.parseFloat(num);
   		} catch (NumberFormatException e) {
   			throw e;
   		}

   		return floatNumber;
   	}

   	/**
   	 * Make Ago Date (오늘 날짜를 포함하는 전일 구하는 매서드)
   	 * @param MaD (일/주/월, "D"/"W"/"M")
   	 * @param agoInt
   	 ****파람값 예시***************
   	 * 3일전 : makeAgoDate("D",3)
   	 * 1주전 : makeAgoDate("W",1)
   	 * 1달전 : makeAgoDate("M",1)
   	 * 3달전 : makeAgoDate("M",3)
   	 * 6달전 : makeAgoDate("M",6)
   	 *****************************
   	 * @return
   	 */
   	public static String makeAgoDate(String MaD, int agoInt) {
   		//
   		if ( MaD.indexOf("D") != -1 ) {
   			agoInt = agoInt - 1 ;
   		} else if ( MaD.indexOf("W") != -1 ) {
   			agoInt = agoInt * 7 - 1 ;
   		} else if ( MaD.indexOf("M") != -1 ) {
   			agoInt = agoInt ;
   		}

   		Calendar cal = Calendar.getInstance(Locale.KOREA);

   		String toDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

   		int date = 0;

   		//월인 경우 해당 월의 계산 일수를 구하는 로직
   		if("M".equalsIgnoreCase(MaD)) {
   			for(int i = 1 ; i <= agoInt ; i ++ ) {
   				int years = Integer.parseInt(toDate.substring(0,4));
   				int month = Integer.parseInt(toDate.substring(4,6))+1 -i ;

   				cal.set(years,month,1);

   				cal.add(Calendar.DAY_OF_MONTH, -1);

   				date += Integer.parseInt(new SimpleDateFormat("dd").format(cal.getTime()));
   			}
   		}else {
   			date = agoInt;
   		}

   		//현재 날짜
   		Calendar agoCal = Calendar.getInstance(Locale.KOREA);
   		agoCal.set(Integer.parseInt(toDate.substring(0,4))
   				,Integer.parseInt(toDate.substring(4,6)) -1
   				,Integer.parseInt(toDate.substring(6,8))
   		);

   		agoCal.add(Calendar.DAY_OF_MONTH, Integer.parseInt("-" + date));

   		return new SimpleDateFormat("yyyy-MM-dd").format(agoCal.getTime());
   	}
}
