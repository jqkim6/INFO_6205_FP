package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FirstAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_add);
        setupCategoryDropdown();
        setupWorkloadDropdown();

        Button button1 = (Button) findViewById(R.id.nextButton);
        if(getIntent().hasExtra("POS")){
            setSupportActionBar(findViewById(R.id.toolbar3));
            getSupportActionBar().setTitle("Edit Task");
            ToDoItem item=ToDoAdapter.getInstance().getItems().get(getIntent().getIntExtra("POS",0));
            ((TextInputEditText)findViewById(R.id.textInputTitle)).setText(item.getTitle());
            ((android.widget.AutoCompleteTextView)findViewById(R.id.autoCompleteCategory)).setText(item.getCategory(),false);
            ((android.widget.AutoCompleteTextView)findViewById(R.id.autoCompleteWorkload)).setText(item.getWorkload(),false);
            ((EditText)findViewById(R.id.editTextDeadline)).setText(item.getDeadline());
        }
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> taskitem=new ArrayList<>();
                String title=((TextInputEditText)findViewById(R.id.textInputTitle)).getText().toString();
                if (title.isEmpty()) {
                    // 显示错误信息
                    TextInputLayout textInputLayoutTitle = findViewById(R.id.textInputLayoutTitle);
                    textInputLayoutTitle.setError("Title is required");
                    return; // 阻止进一步的处理
                }

                // 如果title不为空，则清除错误信息
                TextInputLayout textInputLayoutTitle = findViewById(R.id.textInputLayoutTitle);
                textInputLayoutTitle.setError(null);
                String category=((android.widget.AutoCompleteTextView)findViewById(R.id.autoCompleteCategory)).getText().toString();
                String deadline=((EditText)findViewById(R.id.editTextDeadline)).getText().toString();
                String workload=((android.widget.AutoCompleteTextView)findViewById(R.id.autoCompleteWorkload)).getText().toString();
                taskitem.add(title);
                taskitem.add(category);
                taskitem.add(deadline);
                taskitem.add(workload);
                Intent intent = new Intent(FirstAddActivity.this, SecondAddActivity.class);
                intent.putStringArrayListExtra("TaskItem",taskitem);
                if(getIntent().hasExtra("POS")){
                    intent.putExtra("POS",getIntent().getIntExtra("POS",-1));
                }
                startActivity(intent);
            }
        });
        ImageButton button2=findViewById(R.id.imageButton3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstAddActivity.this, FirstActivity.class);
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
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // 月份从0开始，所以加1
                            String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            ((EditText) findViewById(R.id.editTextDeadline)).setText(date);
                        }
                    }, year, month, day);
            datePickerDialog.show();
    }




    private void setupCategoryDropdown() {
        String[] categories = new String[] {"Education", "Personal", "Work", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_menu_popup_item,
                categories
        );
        AutoCompleteTextView dropdownCategory = findViewById(R.id.autoCompleteCategory);
        dropdownCategory.setAdapter(adapter);
    }

    private void setupWorkloadDropdown() {
        String[] workloads = new String[] {"Light", "Medium", "Heavy"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.dropdown_menu_popup_item,
                workloads
        );
        AutoCompleteTextView dropdownWorkload = findViewById(R.id.autoCompleteWorkload);
        dropdownWorkload.setAdapter(adapter);
    }
}