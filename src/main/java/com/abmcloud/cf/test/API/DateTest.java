package com.abmcloud.cf.test.API;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {


    public static void main(String[] args) {

        DateFormat df = new SimpleDateFormat("MMMM");
        DateFormat yy = new SimpleDateFormat("yyyy г.");
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        c.set(year, 0, 1);
        int currentYear = c.get(Calendar.YEAR);
        while (c.get(Calendar.YEAR) == currentYear) {
            Date date = c.getTime();
            String s = df.format(date).substring(0, 1).toUpperCase() + df.format(date).substring(1) + " " + yy.format(date);
            System.out.println(s);
            c.add(Calendar.MONTH, 1);
        }
    }
}