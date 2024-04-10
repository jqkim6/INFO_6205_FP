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
}
