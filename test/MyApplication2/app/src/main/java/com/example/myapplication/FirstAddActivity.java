package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
                String category=((TextInputEditText)findViewById(R.id.textInputCategory)).getText().toString();
                String deadline=((EditText)findViewById(R.id.editTextDeadline)).getText().toString();
                String workload=((TextInputEditText)findViewById(R.id.textInputWorkload)).getText().toString();
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

    public void showDatePickerDialog(View view) {
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select deadline");
        final MaterialDatePicker<Long> datePicker = builder.build();

        datePicker.addOnPositiveButtonClickListener(selection -> {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String dateString = formatter.format(new Date(selection));
            ((EditText) findViewById(R.id.editTextDeadline)).setText(dateString);
        });

        datePicker.show(getSupportFragmentManager(), "datePicker");
    }
}