package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.icu.text.SimpleDateFormat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> implements ItemTouchHelperAdapter {
    private ArrayList<ToDoItem> toDoItems; // 用于存放 ToDo items 的数据列表
    private final static ToDoAdapter inst=new ToDoAdapter(new ArrayList<ToDoItem>());
    private Context context;
    private RecyclerView rv;
    private int highlightedIndex = -1;
    public ToDoAdapter(Context context, ArrayList<ToDoItem> toDoItems) {
        this.context = context;
        this.toDoItems = toDoItems;
    }


    // 提供一个合适的构造函数（取决于数据的类型）
    public void setRV(RecyclerView view){
        this.rv=view;
    }
    public static ToDoAdapter getInstance(Context context) {
        inst.context = context; // 确保 context 总是更新为最新
        return inst;
    }
    public static ToDoAdapter getInstance() {
        return inst;
    }
    public ToDoAdapter(ArrayList<ToDoItem> toDoItems) {
        this.toDoItems = toDoItems;
    }

    public void setItems(ArrayList<ToDoItem> toDoItems){
        this.toDoItems = toDoItems;
    }
    public ArrayList<ToDoItem> getItems(){
        return this.toDoItems;
    }
    @Override
    public void onItemDragStarted() {

    }
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(toDoItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(toDoItems, i, i - 1);
            }
        }
        if(highlightedIndex!=-1){
            if(fromPosition==highlightedIndex){
                highlightedIndex=toPosition;
            }
            else if (fromPosition<highlightedIndex && toPosition>=highlightedIndex ){
                highlightedIndex-=1;
            }
            else if (fromPosition >highlightedIndex && toPosition<=highlightedIndex) {
                highlightedIndex+=1;
            }
        }
        setInvisibleRecursively(rv);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_items, parent, false);
        return new ToDoViewHolder(itemView);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void sethilightindex(int i){
        this.highlightedIndex=i;
        notifyDataSetChanged();
    }
    public void onlySetHightIndex(int i){
        this.highlightedIndex=i;
    }

    // 替换视图的内容（由布局管理器调用）
    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        // 绑定数据
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.setIntrinsicWidth(18);   // 设置宽度
        drawable.setIntrinsicHeight(18);  // 设置高度
        ToDoItem item = toDoItems.get(position);
        holder.textView.setText(item.getTitle());
        holder.textView2.setText(item.getDeadline());
        if(!item.getDeadline().equals("Daily")&&!item.getDeadline().equals("Complete")) {
            Date cur = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                boolean isBefore = sdf.parse(item.getDeadline()).before(cur) && (!sdf.parse(item.getDeadline()).equals(cur));
                if(isBefore){
                    holder.textView2.setTextColor(Color.RED);
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        switch (item.getWorkload()){
            case "Light":
                drawable.getPaint().setColor(Color.GREEN);
                break;
            case "Medium":
                drawable.getPaint().setColor(Color.YELLOW);
                break;
            case "Heavy":
                drawable.getPaint().setColor(Color.RED);
                break;
            default:
                drawable.getPaint().setColor(Color.TRANSPARENT);
                break;
        }

        holder.circleView.setBackground(drawable);
        if (item.getDeadline().equals("Complete")){
            holder.circleView.setBackground(ContextCompat.getDrawable(context,R.drawable.check));
        }
        // 设置长按监听器来显示删除按钮
        holder.itemView.setOnLongClickListener(v -> {
            holder.deleteButton.setVisibility(View.VISIBLE);
            return true;
        });
        if (position == highlightedIndex) {
            holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.highlighted_background));
        } else {
            holder.itemView.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.normal_background));
        }

        // 设置单击监听器
        holder.itemView.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition(); // 获取当前的位置
            if (adapterPosition != RecyclerView.NO_POSITION) {
                if (holder.deleteButton.getVisibility() == View.VISIBLE) {
                    holder.deleteButton.setVisibility(View.GONE);
                } else {
                    Intent intent = new Intent(context, ShowDetailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("EXTRA_TODO_ITEM_TEXT", toDoItems.get(adapterPosition).getTitle());
                    intent.putExtra("EXTRA_CATEGORY", toDoItems.get(adapterPosition).getCategory());
                    intent.putExtra("EXTRA_DEADLINE", toDoItems.get(adapterPosition).getDeadline());
                    intent.putExtra("EXTRA_WORKLOAD", toDoItems.get(adapterPosition).getWorkload());
                    intent.putExtra("EXTRA_CONTENT", toDoItems.get(adapterPosition).getContent());
                    intent.putExtra("POS",String.valueOf(adapterPosition));
                    context.startActivity(intent);
                }
            }
        });

        // 删除按钮的点击事件
        holder.deleteButton.setOnClickListener(v -> {
            int adapterPosition = holder.getAdapterPosition(); // 获取当前的位置

            if (adapterPosition != RecyclerView.NO_POSITION) {
                ArrayList<ToDoItem> oldlist=toDoItems;
                holder.deleteButton.setVisibility(View.GONE);
                toDoItems.remove(adapterPosition);
                notifyItemRemoved(adapterPosition);
                notifyItemRangeChanged(adapterPosition, toDoItems.size());
                setInvisibleRecursively(this.rv);
                if(highlightedIndex!=-1) {
                    int preindex=highlightedIndex;
                    GetMostIntensive.getMostIntensive(true);
                    notifyItemChanged(preindex);
                    notifyItemChanged(highlightedIndex);

                }
            }
        });
    }
    public int gethilightindex(){
        return this.highlightedIndex;
    }
    public void setInvisibleRecursively(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0, count = group.getChildCount(); i < count; i++) {
                View child = group.getChildAt(i);
                setInvisibleRecursively(child);
            }
        } else if (view instanceof Button && view.getId() == R.id.button_todo_delete) {
            view.setVisibility(View.GONE);
        }
    }



    // 返回数据集的大小（由布局管理器调用）
    public void addTask(ToDoItem item){
        this.toDoItems.add(item);
    }
    @Override
    public int getItemCount() {
        return toDoItems.size();
    }

    // 提供对视图的引用（复杂数据项可能需要提供视图的每个部分）
    // 你提供视图的所有组件的访问器是自定义的 ViewHolder
    public static class ToDoViewHolder extends RecyclerView.ViewHolder {
        // 在这里你声明 item 视图的所有组件
        public TextView textView2;
        public TextView textView;
        public TextView circleView;
        public Button deleteButton;
        public ToDoViewHolder(View itemView) {
            super(itemView);
            // 这里你初始化视图组件
            textView = itemView.findViewById(R.id.textView_todo_item);
            textView2=itemView.findViewById(R.id.ListShowDeadline);
            circleView=itemView.findViewById(R.id.circle);
            refreshbutton();
        }
        public void refreshbutton(){
            deleteButton = itemView.findViewById(R.id.button_todo_delete);
            deleteButton.setVisibility(View.GONE); // 初始化时不可见
        }
    }
}

