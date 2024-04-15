package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;


import java.io.FileOutputStream;
import java.util.ArrayList;

public class SecondAddActivity extends AppCompatActivity {
    ArrayList<String> curtask=null;
    @Override
    public void onStop() {
        super.onStop();
        ItemManager.getInstance(getApplicationContext()).saveItems();
    }
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
                String content=((TextInputEditText)findViewById(R.id.textInputContent)).getText().toString();
                curtask.add(content);
                ToDoItem newitem=new ToDoItem();
                //设置新item内容
                newitem.setAll(curtask.get(0),content,curtask.get(2),curtask.get(1),curtask.get(3));
                //
                ToDoAdapter.getInstance(getApplicationContext()).addTask(newitem);
                Intent intent = new Intent(SecondAddActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton button2=findViewById(R.id.imageButton4);
        button2.setOnClickListener(v->{
            Intent inten=new Intent(SecondAddActivity.this, FirstAddActivity.class);
            startActivity(inten);
        });
    }
}