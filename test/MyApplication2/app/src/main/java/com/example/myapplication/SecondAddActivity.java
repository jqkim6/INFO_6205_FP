package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SecondAddActivity extends AppCompatActivity {
    ArrayList<String> curtask=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curtask=getIntent().getStringArrayListExtra("TaskItem");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second_add);
        Button button1 = (Button) findViewById(R.id.finishButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDoItem newitem=new ToDoItem();
                //设置新item内容

                //
                ToDoAdapter.getInstance().addTask(newitem);
                Intent intent = new Intent(SecondAddActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}