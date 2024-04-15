package com.example.myapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickSort {

    public static void sort(List<ToDoItem> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);  // 递归排序左子数组
            sort(arr, pi + 1, high); // 递归排序右子数组
        }
    }

    private static int partition(List<ToDoItem> arr, int low, int high) {
        ToDoItem pivot = arr.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr.get(j), pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(List<ToDoItem> arr, int i, int j) {
        ToDoItem temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    public static int compare(ToDoItem item1, ToDoItem item2) {
        // 首先按照 deadline 排序
        int result = item1.getDeadline().compareTo(item2.getDeadline());
        // 如果 deadline 相同，则按照 workload 排序
        if (result == 0) {
            Map<String, Double> dictionary = new HashMap<>();
            dictionary.put("Light", -0.5);
            dictionary.put("Medium", -1.5);
            dictionary.put("Heavy", -3.0);
            dictionary.put("",-1.5);
            result = Double.compare(dictionary.get(item1.getWorkload()), dictionary.get(item2.getWorkload()));
        }
        if(result==0){
            result = item1.getTitle().compareTo(item2.getTitle()) ;
        }
        return result;
    }
}
