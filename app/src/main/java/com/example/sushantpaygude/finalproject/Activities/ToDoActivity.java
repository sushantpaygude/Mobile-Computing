package com.example.sushantpaygude.finalproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sushantpaygude.finalproject.Adapters.ToDoRecyclerViewAdapter;
import com.example.sushantpaygude.finalproject.R;
import com.example.sushantpaygude.finalproject.Utils.TinyDB;
import com.example.sushantpaygude.finalproject.Utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;

public class ToDoActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView todoRecyclerView;
    private EditText textTodo;
    private Button buttonTodo;
    TinyDB tinyDB;
    private ToDoRecyclerViewAdapter toDoRecyclerViewAdapter;
    //private HashMap<String, String> todoHashMap = new HashMap<String, String>();
    private ArrayList<String> todoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        tinyDB = new TinyDB(getApplicationContext());
        todoArrayList = tinyDB.getListString(Utilities.TO_DO_LIST_STRING);

        todoRecyclerView = findViewById(R.id.todoRecyclerView);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(todoRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        todoRecyclerView.addItemDecoration(dividerItemDecoration);
        toDoRecyclerViewAdapter = new ToDoRecyclerViewAdapter(todoArrayList,this);
        todoRecyclerView.setAdapter(toDoRecyclerViewAdapter);
        textTodo = findViewById(R.id.textTodo);
        textTodo.addTextChangedListener(textWatcher);
        buttonTodo = findViewById(R.id.buttonTodo);
        buttonTodo.setVisibility(View.INVISIBLE);
        buttonTodo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonTodo:
//                todoArrayList = tinyDB.getListString(Utilities.TO_DO_LIST_STRING);

                todoArrayList.add(textTodo.getText().toString());
                tinyDB.putListString(Utilities.TO_DO_LIST_STRING, todoArrayList);
                Log.e("ARRay", ":" + tinyDB.getListString(Utilities.TO_DO_LIST_STRING));

                toDoRecyclerViewAdapter.notifyDataSetChanged();
                break;
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            buttonTodo.setVisibility(View.VISIBLE);
            if (charSequence.length() == 0) {
                buttonTodo.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
