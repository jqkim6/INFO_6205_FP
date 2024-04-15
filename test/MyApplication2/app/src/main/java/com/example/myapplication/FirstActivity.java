package com.example.myapplication;
import java.util.Collections;
import java.util.Comparator;

import android.annotation.SuppressLint;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
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
        ToDoAdapter adapter = ToDoAdapter.getInstance(this);
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
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Collections.sort(ToDoAdapter.getInstance(getApplicationContext()).getItems(), new Comparator<ToDoItem>() {
                    @Override
                    public int compare(ToDoItem item1, ToDoItem item2) {
                        // 首先按照 deadline 排序
                        int result = item1.getDeadline().compareTo(item2.getDeadline());
                        // 如果 deadline 相同，则按照 workload 排序
                        if (result == 0) {
                            Map<String, Double> dictionary = new HashMap<>();
                            dictionary.put("Light", 0.5);
                            dictionary.put("Medium", 1.5);
                            dictionary.put("Heavy", 3.0);
                            result = Double.compare(dictionary.get(item1.getWorkload()), dictionary.get(item2.getWorkload()));
                        }
                        return result;
                    }
                });
                ToDoAdapter.getInstance(getApplicationContext()).notifyDataSetChanged();
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
