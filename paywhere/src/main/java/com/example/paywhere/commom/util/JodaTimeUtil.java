/**
 * FileName: JodaTimeUtil
 * Author:   haichaoyang3
 * Date:     2019/6/28 15:42
 * Description: time util
 * History:
 */
package com.example.paywhere.commom.util;

import com.jc.demo.springbootdemo.commom.exception.MyException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * Description:〈time util〉
 *
 * @author haichaoyang3
 * @since 1.0.0
 */
public class JodaTimeUtil {
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JodaTimeUtil.class);

    public static String dateToStr(Date date) {
        return dateToStr(date, STANDARD_FORMAT);
    }

    public static String dateToStr(Date date, String format) {
        if (Objects.isNull(date)) {
            return null;
        }
        format = StringUtils.isEmpty(format) ? STANDARD_FORMAT : format;
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }


    public static Date strToDate(String timeStr) {
        return strToDate(timeStr, STANDARD_FORMAT);
    }

    /**
     * string类型 -> date类型
     *
     * @param timeStr
     * @param format  自定义日期格式
     * @return
     */

    public static Date strToDate(String timeStr, String format) {
        if (StringUtils.isEmpty(timeStr)) {
            return null;
        }
        format = StringUtils.isEmpty(format) ? STANDARD_FORMAT : format;
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime;
        try {
            dateTime = dateTimeFormatter.parseDateTime(timeStr);
        } catch (Exception e) {
            return null;
        }
        return dateTime.toDate();
    }

    public static Boolean isTimeExpired(Date date) {
        String timeStr = JodaTimeUtil.dateToStr(date);
        return isBeforeNow(timeStr);
    }

    public static Boolean isTimeExpired(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return true;
        }
        return isBeforeNow(timeStr);
    }

    private static Boolean isBeforeNow(String timeStr) {

        DateTimeFormatter format = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime;
        try {
            dateTime = DateTime.parse(timeStr, format);
        } catch (Exception e) {
            log.error("isBeforeNow error:timeStr{}{}", timeStr, e);
            throw new MyException("时间格式化异常了");
        }
        return dateTime.isBeforeNow();
    }

    public static Date plusDay(Date date, int days) {
        return plusOrMinusDays(date, days, 0);
    }

    public static Date minusDay(Date date, int days) {
        return plusOrMinusDays(date, days, 1);
    }

    /**
     * @param date
     * @param days
     * @param type 0:加天数 1:减天数
     * @return
     */

    private static Date plusOrMinusDays(Date date, int days, Integer type) {
        if (Objects.isNull(date)) {
            return null;
        }
        DateTime dateTime = new DateTime(date);
        if (Objects.equals(0, type)) {
            dateTime = dateTime.plus(days);
        } else {
            dateTime = dateTime.minus(days);
        }
        return dateTime.toDate();
    }

    public static Date minusMinutes(Date date, int minutes) {
        return plusorMinusMinutes(date, minutes, 1);
    }

    public static Date plusMinutes(Date date, int minutes) {
        return plusorMinusMinutes(date, minutes, 0);
    }

    /**
     * @param date
     * @param minutes
     * @param type
     * @return
     */
    private static Date plusorMinusMinutes(Date date, int minutes, Integer type) {
        if (Objects.isNull(date)) {
            return null;
        }
        DateTime dateTime = new DateTime(date);
        if (Objects.equals(0, type)) {
            dateTime = dateTime.plusMinutes(minutes);
        } else {
            dateTime = dateTime.minusMinutes(minutes);
        }
        return dateTime.toDate();
    }

    public static Date minusMonths(Date date, int months) {
        return plusOrMinusMonths(date, months, 1);
    }

    public static Date plusMonths(Date date, int months) {
        return plusOrMinusMonths(date, months, 0);
    }

    /**
     * @param date
     * @param months
     * @param type
     * @return
     */
    private static Date plusOrMinusMonths(Date date, int months, Integer type) {
        if (Objects.isNull(date)) {
            return null;
        }
        DateTime dateTime = new DateTime(date);
        if (Objects.equals(0, type)) {
            dateTime = dateTime.plusMonths(months);
        } else {
            dateTime = dateTime.minusMonths(months);
        }
        return dateTime.toDate();
    }

    public static Boolean isBetweenStartAndEndTime(Date target, Date startTime, Date endTime) {
        if (Objects.isNull(target) || Objects.isNull(startTime) || Objects.isNull(endTime)) {
            throw new MyException("参数不能为空");
        }
        DateTime dateTime = new DateTime(target);
        return dateTime.isAfter(startTime.getTime()) && dateTime.isBefore(endTime.getTime());
    }


    public static void main(String[] args) {
       /* DateTime dateTime = new DateTime();
        dateTime = dateTime.withDayOfWeek(7);
        Date start=null,end = null;
        if (!isTimeExpired(dateTime.toDate())){
            start = dateTime.minusDays(14).toDate();
            end = dateTime.minusDays(7).toDate();
        }else {
            start = dateTime.minusDays(7).toDate();
            end = dateTime.toDate();
        }
        System.out.println(start+"======================"+end);
        System.out.println(new DateTime()+"======================"+dateTime);*/
        DateTime dateTime = new DateTime();
        dateTime = dateTime.withDayOfWeek(7);
        Date start = null, end = null;
        if (!dateTime.isBeforeNow()) {
            start = dateTime.minusDays(14).toLocalDate().toDate();
            end = dateTime.minusDays(7).toLocalDate().toDate();
        } else {
            start = dateTime.minusDays(7).toLocalDate().toDate();
            end = dateTime.toLocalDate().toDate();
        }
        System.out.println(start);
        System.out.println(end);
    }

}