package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private static final String FILE_NAME = "data.json";
    private Context context;
    private Gson gson;
    private List<ToDoItem> items;

    public ItemManager(Context context) {
        this.context = context;
        this.gson = new Gson();
        this.items = loadItems();  // 加载现有任务列表
    }

    public void addItem(ToDoItem item) {
        this.items.add(item);
        saveItems();  // 每次添加项后保存整个列表
    }

    private void saveItems() {
        String json = gson.toJson(this.items);
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

    private List<ToDoItem> loadItems() {
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            Type listType = new TypeToken<ArrayList<ToDoItem>>(){}.getType();
            List<ToDoItem> loadedItems = gson.fromJson(isr, listType);
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

    public List<ToDoItem> getItems() {
        return this.items;
    }
}
