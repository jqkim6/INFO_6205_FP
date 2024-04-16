package com.example.myapplication;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GetMostIntensive {
    public static void getMostIntensive(){
        Comparator<ToDoItem> comp=new TensionComparator();
        PriorityQueue<ToDoItem> queue=new PriorityQueue<>(comp);
        queue.addAll(ToDoAdapter.getInstance().getItems());
        if(!queue.isEmpty()){
            int index=ToDoAdapter.getInstance().getItems().indexOf(queue.peek());
            ToDoAdapter.getInstance().sethilightindex(index);
        }
    }
}