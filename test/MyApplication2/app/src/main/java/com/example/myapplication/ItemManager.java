package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private static ItemManager instance;
    private static final String FILE_NAME = "data.json";
    private final Gson gson=new Gson();
    private Context context;

    public static synchronized ItemManager getInstance(Context context) {
        if (instance == null) {
            instance = new ItemManager(context);
            ArrayList<ToDoItem> loadedItems=instance.loadItems();
            ToDoAdapter.getInstance(context.getApplicationContext()).setItems(loadedItems);
        }
        return instance;
    }
    public void changeContext(Context context){
        this.context=context;
    }
    public ItemManager(Context context) {
        this.context = context.getApplicationContext(); // 使用ApplicationContext来避免潜在的内存泄漏
    }
    public void saveItems() {
        String json = gson.toJson(ToDoAdapter.getInstance(context.getApplicationContext()).getItems());
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(json);
            writer.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ToDoItem> loadItems() {
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
            ArrayList<ToDoItem> loadedItems = gson.fromJson(isr, listType);
            isr.close();
            fis.close();
            return loadedItems != null ? loadedItems : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();  // 如果文件不存在，则返回一个新的空列表
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
