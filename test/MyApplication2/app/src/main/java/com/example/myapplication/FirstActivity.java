package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ToDoAdapter adapter;
    private List<ToDoItem> toDoList; // 假设你的待办事项是一个字符串列表
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化待办事项数据列表
        toDoList = new ArrayList<>();
        // 添加一些数据到列表中
        toDoList.add(new ToDoItem());
        toDoList.add(new ToDoItem());
        toDoList.add(new ToDoItem());

        // 初始化 Adapter 并设置给 RecyclerView
        adapter = new ToDoAdapter(toDoList);
        recyclerView.setAdapter(adapter);
    }

}
