package com.example.myapplication;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ToDoItem extends Item {
    private String title = "default title";
    private String content = "default content";
    private String deadline = super.simpleformatter.format(date);
    private String category = "none";
    private String workload = "light";

    // 构造函数用于只有内容的情况
    public ToDoItem(String content) {
        this.content = content;
    }

    // 无参数的构造函数
    public ToDoItem() {}

    // 设置所有属性的方法
    public void setAll(String title, String content, String deadline, String category, String workload) {
        this.title = title;
        this.content = content;
        this.deadline = deadline;
        this.category = category;
        this.workload = workload;
        if (Objects.equals(deadline, "")){
            this.deadline="Daily";
        }
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
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