package com.example.loginsystemfinal;
import java.util.Date;
public class sample {
    public static void main(String[] args) {
        //getting local date
        Date udate = new java.util.Date();
        long l = udate.getTime();
        Date sdate = new java.sql.Date(l);
        System.out.println("SQL Date : "+ sdate);
    }
}
