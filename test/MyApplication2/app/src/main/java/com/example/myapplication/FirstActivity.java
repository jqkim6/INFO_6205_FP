package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ToDoAdapter adapter;
    private List<ToDoItem> toDoList; // 假设你的待办事项是一个字符串列表
    private ItemManager itemManager;

    @Override
    public void onStop() {
        super.onStop();
        ItemManager.getInstance(getApplicationContext()).saveItems();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        itemManager = ItemManager.getInstance(getApplicationContext());
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
        ToDoAdapter adapter = ToDoAdapter.getInstance();
        recyclerView.setAdapter(adapter);

// 设置 ItemTouchHelper
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Task list");
        ImageButton hamburgerIcon = findViewById(R.id.imageButton); // 汉堡图标的ID
        hamburgerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });
    }
    private void showPopupMenu(View view) {
        // 创建PopupMenu实例
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // 处理菜单项的点击事件
                if (item.getItemId() == R.id.action1) {
                    return true;
                } else if (item.getItemId() == R.id.action2) {
                    return true;
                }
                return false;
            }
            });
        // 显示菜单
        popupMenu.show();
    }

}
