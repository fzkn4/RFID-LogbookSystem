package com.example.loginsystemfinal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class sample {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.getMonth() + " " + localDate.getDayOfMonth() + ", " + localDate.getYear());
        DateFormat formatter = new SimpleDateFormat("hh:mm:ss a");
        String time = formatter.format(new Date());
        System.out.println(time);
    }
}
