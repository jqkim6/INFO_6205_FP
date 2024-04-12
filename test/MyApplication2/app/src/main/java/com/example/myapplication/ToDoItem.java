package com.example.myapplication;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ToDoItem extends Item{
    private String title="default title";
    private String content="default content";
    private String deadline=simpleformatter.format(date);
    private String category="none";
    private String workload="light";
    public ToDoItem(String s){
        content=s;
    }
    public ToDoItem(){};
    public void setAll(String title,String content,String deadline,String category,String workload){
        this.title=title;
        this.content=content;
        this.deadline=deadline;
        this.category=category;
        this.workload=workload;
    }

    public String getTitle() {
        return title;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getCategory() {
        return category;
    }

    public String getWorkload() {
        return workload;
    }
}
