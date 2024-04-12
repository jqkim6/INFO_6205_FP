package com.example.myapplication;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ToDoItem {
    private String content="default content";

    public ToDoItem(String s){
        content=s;
    }
    public ToDoItem(){};
    public String getText() {
        return content;
    }
}
