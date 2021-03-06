package com.example.kslovic.tasky;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {
    private static final String TAG = "Kristina";
    private ArrayList<Task> Tasks;
    public TaskAdapter(ArrayList<Task> tasks) { Tasks = tasks; }
    @Override
    public int getCount() { return this.Tasks.size(); }
    @Override
    public Task getItem(int position) { return Tasks.get(position); }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder taskViewHolder;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_task, parent, false);
            taskViewHolder = new ViewHolder(convertView);
            convertView.setTag(taskViewHolder);
        }
        else{
            taskViewHolder = (ViewHolder) convertView.getTag();
        }
        Task task = this.Tasks.get(position);
        taskViewHolder.tvTaskTitle.setText(task.gettTitle());
        taskViewHolder.tvTaskCategory.setText(task.gettCategory());
        taskViewHolder.ivTaskPriority.setBackgroundColor(task.gettPriority());;
        return convertView;
    }

    public static class ViewHolder {
        public TextView tvTaskTitle, tvTaskCategory;
        ImageView ivTaskPriority;
        public ViewHolder(View itemView) {
            this.tvTaskTitle = (TextView) itemView.findViewById(R.id.tvTaskTitle);
            this.tvTaskCategory = (TextView) itemView.findViewById(R.id.tvTaskCategory);
            this.ivTaskPriority = (ImageView) itemView.findViewById(R.id.ivTaskPrirority);
        }


    }
    public void deleteAt(int position) {
        Log.d(TAG,"deleted");
        Tasks.remove(position);
        notifyDataSetChanged();
    }

}
