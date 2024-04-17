package com.example.myapplication;

import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;

class ToDoDiffCallback extends DiffUtil.Callback {
    private final ArrayList<ToDoItem> oldList;
    private final ArrayList<ToDoItem> newList;

    public ToDoDiffCallback(ArrayList<ToDoItem> oldList, ArrayList<ToDoItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // Assuming each ToDoItem has a unique ID you can rely on
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ToDoItem oldItem = oldList.get(oldItemPosition);
        ToDoItem newItem = newList.get(newItemPosition);
        return oldItem.equals(newItem); // Make sure to override equals in ToDoItem
    }
}
