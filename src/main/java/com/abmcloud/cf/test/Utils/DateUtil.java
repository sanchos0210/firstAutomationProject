package com.abmcloud.cf.test.Utils;

import com.abmcloud.cf.test.Driver.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    private DateFormat df;
    Calendar calendar;

    public DateUtil() {
        df = new SimpleDateFormat("dd.MM.yyyy");
        calendar = Calendar.getInstance();
    }

    public String getTomorrowDate() {
        df = new SimpleDateFormat("d");
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = calendar.getTime();
        String tomorrowDay = df.format(tomorrow);
        return tomorrowDay;
    }

    public String getTodayDate() {
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        return getDate(today, "dd");
    }

    public String getDate(Date date, String format) {
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        df = new SimpleDateFormat(format);

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String soughtDate = df.format(date);
        return soughtDate;
    }

    public List<String> getMonthsInYearFullDate(Constants requiredYear) {
        List<String> months = new ArrayList<>();
        df = new SimpleDateFormat("MMMM");
        DateFormat yy = new SimpleDateFormat("yyyy г.");
        int year = calendar.get(Calendar.YEAR);
        switch (requiredYear) {
            case THIS_YEAR: break;       //for current year
            case LAST_YEAR: {             //for last year
                year = year - 1;
                break;
            }
        }
        calendar.set(year, 0, 1);
        int currentYear = calendar.get(Calendar.YEAR);
        while (calendar.get(Calendar.YEAR) == currentYear) {
            Date date = calendar.getTime();
            String s = df.format(date).toLowerCase() + " " + yy.format(date);
            months.add(s);
            calendar.add(Calendar.MONTH, 1);
        }
        return months;
    }

    public List<String> getDaysInMonthFullDates(Constants requiredMonth) {
        List<String> daysInMonth = new ArrayList<>();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        switch(requiredMonth) {
            case THIS_MONTH: break;        //for current month
            case LAST_MONTH: {     //for last month
                month = month - 1;
                break;
            }
        }
        calendar.set(year, month, 1);
        int currentMonth = calendar.get(Calendar.MONTH);
        while (calendar.get(Calendar.MONTH) == currentMonth) {
            Date date = calendar.getTime();
            String s = df.format(date);
            daysInMonth.add(s);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysInMonth;
    }

    public String getTodayFullDate() {
        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        return getDate(today, "dd.MM.yyyy");
    }

    public String getTomorrowFullDate() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = calendar.getTime();
        String s = df.format(tomorrow);
        return s;
    }

    public String getYesterdayFullDate() {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();
        String s = df.format(yesterday);
        return s;
    }

    public List<String> getDefaultPeriodForCalendar() {
        List<String> daysInWeek = new ArrayList<>();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int startDay = calendar.get(Calendar.DAY_OF_MONTH) - 1;
        int endDay = startDay + 6;
        calendar.set(year, month, startDay);
        while (calendar.get(Calendar.DAY_OF_MONTH) <= endDay) {
            Date date = calendar.getTime();
            String s = df.format(date);
            daysInWeek.add(s);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysInWeek;
    }

    public List<String> getSetDefaultPeriodForCalendar() {
        List<String> daysInWeek = new ArrayList<>();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int startDay = calendar.get(Calendar.DAY_OF_MONTH) - 3;
        int endDay = startDay + 13;
        calendar.set(year, month, startDay);
        while (calendar.get(Calendar.DAY_OF_MONTH) <= endDay) {
            Date date = calendar.getTime();
            String s = df.format(date);
            daysInWeek.add(s);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return daysInWeek;
    }

//    public List<String> getWeeksForDefaultPeriod() {
//        List<String> weeks = new ArrayList<>();
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int startDay = calendar.get(Calendar.DAY_OF_MONTH) - 1;
//        int endDay = startDay + 6;
//        calendar.set(year, month, startDay);
//        Set<Integer> weeksInPeriod = new HashSet<>();
//        while (calendar.get(Calendar.DAY_OF_MONTH) <= endDay) {
//            weeksInPeriod.add(calendar.get(Calendar.WEEK_OF_YEAR));
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        while(weeksInPeriod.iterator().hasNext()) {
//            calendar.set(Calendar.WEEK_OF_YEAR, weeksInPeriod.iterator().next());
//            calendar.set(Calendar.DAY_OF_YEAR, calendar.getFirstDayOfWeek());
//            String week = df.format(calendar.getTime());
//            calendar.add(Calendar.WEEK_OF_YEAR, 1);
//            calendar.set(Calendar.DAY_OF_YEAR, calendar.getFirstDayOfWeek());
//            week = week + df.format(calendar.getTime());
//            weeks.add(week);
//        }
//        return weeks;
//    }

    public List<String> getThisYear() {
        List<String> thisYear = new ArrayList<>();
        df = new SimpleDateFormat("yyyy г.");
        thisYear.add(df.format(calendar.getTime()));
        return thisYear;
    }
}