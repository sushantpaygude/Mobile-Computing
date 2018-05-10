package com.example.sushantpaygude.finalproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sushantpaygude.finalproject.R;
import com.example.sushantpaygude.finalproject.Utils.TinyDB;
import com.example.sushantpaygude.finalproject.Utils.Utilities;

import java.util.ArrayList;

/**
 * Created by sushantpaygude on 5/9/18.
 */

public class ToDoRecyclerViewAdapter extends RecyclerView.Adapter<ToDoRecyclerViewAdapter.ToDoHolder> {

    private ArrayList<String> todoArrayList;
    private TinyDB tinyDB;

    public ToDoRecyclerViewAdapter(ArrayList<String> todoArrayList, Context context) {
        super();
        this.todoArrayList = todoArrayList;
        this.tinyDB = new TinyDB(context);
    }

    @Override
    public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_todo, parent, false);
        return new ToDoHolder(view);
    }


    @Override
    public void onBindViewHolder(ToDoHolder holder, final int position) {

        holder.textTodoItem.setText(todoArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    public class ToDoHolder extends RecyclerView.ViewHolder {

        private TextView textTodoItem;
        private ImageView imageRemoveItem;

        public ToDoHolder(View itemView) {
            super(itemView);
            textTodoItem = itemView.findViewById(R.id.textTodoItem);
            imageRemoveItem = itemView.findViewById(R.id.imageRemoveItem);
            imageRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ArrayList<String> arrayList = tinyDB.getListString(Utilities.TO_DO_LIST_STRING);
//                    arrayList.remove(getAdapterPosition());
//                    tinyDB.putListString(Utilities.TO_DO_LIST_STRING, arrayList);
                    todoArrayList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), todoArrayList.size());
                    tinyDB.putListString(Utilities.TO_DO_LIST_STRING, todoArrayList);

                }
            });
        }
    }
}
