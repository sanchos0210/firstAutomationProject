package com.abmcloud.cf.test.Utils;

import com.abmcloud.cf.test.Driver.Constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtil {

    private DateFormat df;
    private Calendar calendar;

    public DateUtil() {
        df = new SimpleDateFormat("dd.MM.yyyy", new Locale("ru"));
        calendar = Calendar.getInstance();
    }

    public List<String> getMonthsInYearFullDate(Constants requiredYear) {
        List<String> months = new ArrayList<>();
        df = new SimpleDateFormat("MMMM", new Locale("ru"));
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

    public String getDate(Constants requiredDate, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        switch (requiredDate) {
            case TODAY: {
                break;
            }
            case YESTERDAY: {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                break;
            }
            case TOMORROW: {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                break;
            }
        }
        Date date = calendar.getTime();
        String s = dateFormat.format(date);
        return s;
    }

    public List<String> getPeriodInDays(int countOfDaysBeforeToday, int countOfDaysAfterToday) {
        List<String> soughtPeriod = new ArrayList<>();
        LocalDate date = LocalDate.now();
        LocalDate endDate = date.plusDays(countOfDaysAfterToday);
        date = date.minusDays(countOfDaysBeforeToday);
        do {
            String d = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            soughtPeriod.add(d);
            date = date.plusDays(1);
            if((date.getDayOfMonth() == endDate.getDayOfMonth()) && (date.getMonthValue() == endDate.getMonthValue())) {    //чтобы последняя дата попала в список
                d = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                soughtPeriod.add(d);                                                                                        //иначе метод будет возвращать список,
            }                                                                                                               //который не будет включать в себя последнюю дату
        } while((date.getDayOfMonth() != endDate.getDayOfMonth()) || (date.getMonthValue() != endDate.getMonthValue()));
        return soughtPeriod;
    }

    public List<String> getPeriodInDays(Constants requiredPeriod) {
        List<String> soughtPeriod = new ArrayList<>();
        LocalDate date = LocalDate.now();
        int numOfToday = date.getDayOfMonth();
        date = date.minusDays((numOfToday-1));  //получаем 1-е число текущего месяца
        LocalDate endDate = null;
        switch (requiredPeriod) {
            case THIS_MONTH: {
                endDate = date.plusMonths(1);
                break;
            }
            case LAST_MONTH: {
                endDate = date;
                date = date.minusMonths(1);
                 break;
            }
        }
        do {
            String d = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            soughtPeriod.add(d);
            date = date.plusDays(1);
            if((date.getDayOfMonth() == endDate.getDayOfMonth()) && (date.getMonthValue() == endDate.getMonthValue())) {    //чтобы последняя дата попала в список
                d = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                soughtPeriod.add(d);                                                                                        //иначе метод будет возвращать список,
            }                                                                                                               //который не будет включать в себя последнюю дату
        } while (date.getDayOfMonth() != endDate.getDayOfMonth());
        return soughtPeriod;
    }

    public List<String> getThisYear() {
        List<String> thisYear = new ArrayList<>();
        df = new SimpleDateFormat("yyyy г.");
        thisYear.add(df.format(calendar.getTime()));
        return thisYear;
    }
}