package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShowDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //返回
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String text1 = getIntent().getStringExtra("EXTRA_TODO_ITEM_TEXT");
        // 日志输出
        Log.d("ShowDetailActivity", "Received text: " + text1);

        // 接收传递过来的数据
        String text = getIntent().getStringExtra("EXTRA_TODO_ITEM_TEXT");
        String category = getIntent().getStringExtra("EXTRA_CATEGORY");
        String deadline = getIntent().getStringExtra("EXTRA_DEADLINE");
        String workload = getIntent().getStringExtra("EXTRA_WORKLOAD");
        String content = getIntent().getStringExtra("EXTRA_CONTENT");

        // 将数据显示在界面上
        TextView textView = findViewById(R.id.textView_text);
        TextView categoryView = findViewById(R.id.textView_category);
        TextView deadlineView = findViewById(R.id.textView_deadline);
        TextView workloadView = findViewById(R.id.textView_workload);
        TextView contentView = findViewById(R.id.textView_content);

        textView.setText(text);
        categoryView.setText(category);
        deadlineView.setText(deadline);
        workloadView.setText(workload);
        contentView.setText(content);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 当向上箭头被点击时，结束当前Activity
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}