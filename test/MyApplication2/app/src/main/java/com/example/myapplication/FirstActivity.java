package com.example.myapplication;
import java.util.Collections;
import java.util.Comparator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FirstActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    public void onStop() {
        super.onStop();
        ItemManager.getInstance(getApplicationContext()).saveItems();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        ItemManager.getInstance(getApplicationContext());
        FloatingActionButton button1 = findViewById(R.id.floatingActionButton);
        ImageButton button2=findViewById(R.id.imageButton2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, FirstAddActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, HomePage.class);
                startActivity(intent);
            }
        });
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 初始化待办事项数据列表
        ToDoAdapter adapter = ToDoAdapter.getInstance(getApplicationContext());
        recyclerView.setAdapter(adapter);
        ToDoAdapter.getInstance(getApplicationContext()).setRV(recyclerView);
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator != null) {
            animator.setAddDuration(500);
            animator.setRemoveDuration(500);
        }
        recyclerView.setItemAnimator(animator);
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
                if (item.getItemId() == R.id.action1) {
                    ArrayList<ToDoItem> oldlist=new ArrayList<>(ToDoAdapter.getInstance().getItems());
                    boolean cal= ToDoAdapter.getInstance().gethilightindex() != -1;
                    ArrayList <ToDoItem> items=ToDoAdapter.getInstance(getApplicationContext()).getItems();
                    QuickSort.sort(items,0,items.size()-1);
                    setInvisibleRecursively(findViewById(R.id.recycler_view));
                    if(cal){
                        GetMostIntensive.getMostIntensive(true);
                    }
                    //ToDoAdapter.getInstance().notifyDataSetChanged();
                    DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ToDoDiffCallback(oldlist, ToDoAdapter.getInstance().getItems()));
                    diffResult.dispatchUpdatesTo(ToDoAdapter.getInstance());

                    return true;
                } else if (item.getItemId() == R.id.action2) {
                    setInvisibleRecursively(recyclerView);
                    if(ToDoAdapter.getInstance().gethilightindex()!=-1){
                        ToDoAdapter.getInstance().sethilightindex(-1);
                        return true;
                    }
                    GetMostIntensive.getMostIntensive();
                    return true;
                }
                return false;
            }
            });
        // 显示菜单
        popupMenu.show();
    }
    public void setInvisibleRecursively(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0, count = group.getChildCount(); i < count; i++) {
                View child = group.getChildAt(i);
                setInvisibleRecursively(child);
            }
        } else if (view instanceof Button && view.getId() == R.id.button_todo_delete) {
            view.setVisibility(View.GONE);
        }
    }
}
