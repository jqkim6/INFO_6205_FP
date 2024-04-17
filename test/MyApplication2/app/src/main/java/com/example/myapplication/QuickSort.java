package com.example.myapplication;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class QuickSort {
    private static WorkloadComparator workloadComparator = new WorkloadComparator();
    public static void sort(List<ToDoItem> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
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
    public static int compare(String o1,String o2){
        if(o1.equals("Daily")){
            if (o2.equals("Daily")){
                return 0;
            }
            if(o2.equals("Complete")){
                return -1;
            }
            return 1;
        }
        if(o1.equals("Complete")){
            if (o2.equals("Complete")){
                return 0;
            }
            return 1;
        }
        if(o2.equals("Daily")|| o2.equals("Complete")){
            return -1;
        }
        return o1.compareTo(o2);
    }
    public static int compare(ToDoItem item1, ToDoItem item2) {
        int result = compare(item1.getDeadline(),item2.getDeadline());
        if (result == 0) {
            result = workloadComparator.compare(item1.getWorkload(), item2.getWorkload());
        }
        if (result == 0) {
            result = item1.getTitle().compareTo(item2.getTitle());
        }
        return result;
    }
}
