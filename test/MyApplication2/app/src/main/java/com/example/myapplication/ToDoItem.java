package com.example.myapplication;

public class ToDoItem {
    private String content="default content";

    public ToDoItem(String s){
        content=s;
    }
    public ToDoItem(){};
    public String getText() {
        return content;
    }

    public String getCategory() { return content;}

    public String getDeadline() {return content;}

    public String getWorkload() {return content; }

    public String getContent() { return content;}
}
