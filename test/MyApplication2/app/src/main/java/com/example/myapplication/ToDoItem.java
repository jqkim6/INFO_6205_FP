package com.example.myapplication;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ToDoItem extends Item {
    private String title = "default title";
    private String content = "default content";
    private Date date = new Date();  // 创建一个Date对象，表示当前时间
    private SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private String deadline = simpleFormatter.format(date);
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