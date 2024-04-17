package com.example.myapplication;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GetMostIntensive {
    public static int index=-1;
    public static void getMostIntensive(){
        Comparator<ToDoItem> comp=new TensionComparator();
        PriorityQueue<ToDoItem> queue=new PriorityQueue<>(comp);
        queue.addAll(ToDoAdapter.getInstance().getItems());
        if(!queue.isEmpty()){
            int index=ToDoAdapter.getInstance().getItems().indexOf(queue.peek());
            GetMostIntensive.index=index;
            ToDoAdapter.getInstance().sethilightindex(index);
        }
    }
    public static void getMostIntensive(boolean only){
        Comparator<ToDoItem> comp=new TensionComparator();
        PriorityQueue<ToDoItem> queue=new PriorityQueue<>(comp);
        queue.addAll(ToDoAdapter.getInstance().getItems());
        if(!queue.isEmpty()){
            int index=ToDoAdapter.getInstance().getItems().indexOf(queue.peek());
            GetMostIntensive.index=index;
            if(!only){
                ToDoAdapter.getInstance().sethilightindex(index);
            }
            else{
                ToDoAdapter.getInstance().onlySetHightIndex(index);
            }

        }
    }
}
