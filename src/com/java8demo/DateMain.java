package com.java8demo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by dsherric on 10/16/15.
 */
public class DateMain {
    public static void main(String[] args) {
        // Yuck!
        java.util.Date badDate1 = new java.util.Date(115, 9, 16);
        System.out.println(badDate1.toString());
        System.out.println(badDate1.getYear() + " " + badDate1.getMonth());
        java.util.Date badDate2 = new java.util.Date();
        System.out.println(badDate2.toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(dateFormat.format(badDate2));

        System.out.println();

        // Ooohh pretty
        LocalDate date1 = LocalDate.of(2015, 10, 16);
        System.out.println(date1);
        System.out.println(date1.getYear() + " " + date1.getMonth().toString() + " " + date1.getDayOfWeek().toString());

        LocalDateTime dateTime1 = LocalDateTime.now();
        System.out.println(dateTime1.toString());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm MMMM dd, yyyy", Locale.FRANCE);
        System.out.println(dateTime1.format(dateTimeFormatter));
    }

}
