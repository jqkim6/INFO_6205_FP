package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class FirstAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_add);

        Button button1 = (Button) findViewById(R.id.nextButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> taskitem=new ArrayList<>();
                String title=((TextInputEditText)findViewById(R.id.textInputTitle)).getText().toString();
                Log.d("a",title);
                String category=((TextInputEditText)findViewById(R.id.textInputCategory)).getText().toString();
                Log.d("b",category);
                String deadline=((TextInputEditText)findViewById(R.id.textInputDeadline)).getText().toString();
                Log.d("c",deadline);
                String workload=((TextInputEditText)findViewById(R.id.textInputWorkload)).getText().toString();
                Log.d("d",workload);
                taskitem.add(title);
                taskitem.add(category);
                taskitem.add(deadline);
                taskitem.add(workload);
                Intent intent = new Intent(FirstAddActivity.this, SecondAddActivity.class);
                intent.putStringArrayListExtra("TaskItem",taskitem);
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