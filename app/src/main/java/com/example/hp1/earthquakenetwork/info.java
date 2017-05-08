package com.example.hp1.earthquakenetwork;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by hp 1 on 23-Feb-17.
 */
public class info {

    private String mag,loc,date,area;

    info(String umag, String uloc, String udate) {
        DecimalFormat df=new DecimalFormat("0.00");
        DecimalFormat df1=new DecimalFormat("0");
        float um= Float.parseFloat(umag);
        if(umag.equals("10")==true){
            mag=df1.format(um);



        }
        else {
            mag = df.format(um);
        }
        String[] parts =uloc.split(",");
        loc = parts[0];
        area=parts[1];

        long  time=Long.parseLong(udate);
        Date d=new Date(time);
        SimpleDateFormat sf=new SimpleDateFormat();
        String date=sf.format(d);

        this. date=date;

    }

    public double getmymag() {

        return Double.parseDouble(mag);
    }
    public String getmymag1() {

        return mag;
    }
    public String getmyloc() {

        return loc;

    }
    public String getmyate(){

        return date;
    }

    public String getarea(){

        return area;
    }



}
