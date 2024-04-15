package com.example.myapplication;
import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Item {
    protected Date date=new Date();
    @SuppressLint("SimpleDateFormat")
    transient protected SimpleDateFormat simpleformatter=new SimpleDateFormat("yyyy-MM-dd");
}
