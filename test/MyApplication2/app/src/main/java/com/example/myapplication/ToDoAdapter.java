package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    private List<ToDoItem> toDoItems; // 用于存放 ToDo items 的数据列表
    private Context context; // 上下文对象，用于界面跳转等操作
    // 提供一个合适的构造函数（取决于数据的类型）
    public ToDoAdapter(List<ToDoItem> toDoItems, Context context) {
        this.toDoItems = toDoItems;
        this.context = context;
    }

    // 创建新视图（由布局管理器调用）
    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 创建一个新的视图
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_items, parent, false);
        return new ToDoViewHolder(itemView);
    }

    // 替换视图的内容（由布局管理器调用）
    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        // - 获取元素数据
        ToDoItem item = toDoItems.get(position);
        // - 用该元素的数据替换视图的内容
        holder.textView.setText(item.getText());
        // 这里你可以设置更多视图的属性，如点击事件监听器等
        holder.itemView.setOnLongClickListener(v -> {
            // 显示删除按钮
            holder.deleteButton.setVisibility(View.VISIBLE);
            return true; // 返回 true 表示消费了事件
        });

        // 删除按钮的点击事件
        holder.deleteButton.setOnClickListener(v -> {
            // 执行删除操作
            toDoItems.remove(position);
            notifyItemRemoved(position);
        });

        //设置点击事件，用于界面跳转
        holder.itemView.setOnClickListener(v -> {
            //创建Intent来启动新的Activity（ShowDetailActivity）
            Intent intent = new Intent(context,ShowDetailActivity.class);
            //通过Intent传递数据，item的文本内容
            intent.putExtra("EXTRA_TODO_ITEM_TEXT",item.getText());
            intent.putExtra("EXTRA_CATEGORY", item.getCategory());
            intent.putExtra("EXTRA_DEADLINE", item.getDeadline());
            intent.putExtra("EXTRA_WORKLOAD", item.getWorkload());
            intent.putExtra("EXTRA_CONTENT", item.getContent());
            //使用上下文对象启动新的Activity
            context.startActivity(intent);
        });
    }

    // 返回数据集的大小（由布局管理器调用）
    @Override
    public int getItemCount() {
        return toDoItems.size();
    }

    // 提供对视图的引用（复杂数据项可能需要提供视图的每个部分）
    // 你提供视图的所有组件的访问器是自定义的 ViewHolder
    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        // 在这里你声明 item 视图的所有组件
        public TextView textView;
        public Button deleteButton;
        public ToDoViewHolder(View itemView) {
            super(itemView);
            // 这里你初始化视图组件
            textView = itemView.findViewById(R.id.textView_todo_item);
            refreshbutton();
        }
        public void refreshbutton(){
            deleteButton = itemView.findViewById(R.id.button_todo_delete);
            deleteButton.setVisibility(View.GONE); // 初始化时不可见
        }
    }
}

