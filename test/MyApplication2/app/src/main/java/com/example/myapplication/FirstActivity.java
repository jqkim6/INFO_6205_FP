package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        FloatingActionButton button1 = findViewById(R.id.floatingActionButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FirstAddActivity.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化待办事项数据列表
        recyclerView.setAdapter(ToDoAdapter.getInstance());

    }

}
