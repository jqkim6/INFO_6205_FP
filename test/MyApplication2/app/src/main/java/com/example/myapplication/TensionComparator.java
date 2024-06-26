package com.example.myapplication;
import android.annotation.SuppressLint;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.util.Log;

import java.text.ParseException;
import java.util.Comparator;
import java.util.HashMap;

public class TensionComparator  implements Comparator <ToDoItem>{
    @Override
    public int compare(ToDoItem o1, ToDoItem o2) {
            HashMap<String, Double> map=new HashMap<>();
            double wk1;
            double wk2;
            map.put("Light",0.5);
            map.put("Medium",1.5);
            map.put("Heavy",3.0);
            map.put("Daily",0.2);
            map.put("",1.0);
            Calendar date1 = Calendar.getInstance();
            Calendar date2 = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            switch (o1.getDeadline()){
                case "Daily":
                    try {
                        date1.setTime(sdf.parse("9999-12-30"));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    wk1= map.get(o1.getWorkload())/7.0;
                    break;
                case "Complete":
                    try {
                        date1.setTime(sdf.parse("9999-12-30"));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    wk1=-100.0;
                    break;
                default:
                    try {
                        date1.setTime(sdf.parse(o1.getDeadline()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    wk1=map.get(o1.getWorkload()) /5.0;
                    break;
            }
        switch (o2.getDeadline()){
            case "Daily":
                try {
                    date2.setTime(sdf.parse("9999-12-30"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                wk2= map.get(o2.getWorkload())/7.0;
                break;
            case "Complete":
                try {
                    date2.setTime(sdf.parse("9999-12-30"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                wk2=-100.0;
                break;
            default:
                try {
                    date2.setTime(sdf.parse(o2.getDeadline()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                wk2=map.get(o2.getWorkload()) /5.0;
                break;
        }
            Log.i("Date", date1.toString());
            long daysBetween1 = daysBetween(Calendar.getInstance(),date1);
            long daysBetween2=daysBetween(Calendar.getInstance(),date2);
            double r1= -((double) 1 /(double)daysBetween1+ wk1);
            double r2= -((double) 1 /(double)daysBetween2+ wk2);
            if (r1<r2){
                return -1;

            } else if (r1>r2) {
                return 1;
            }
            return 0;
    }
    public static long daysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return (end - start) / (24 * 60 * 60 * 1000);
    }
}
