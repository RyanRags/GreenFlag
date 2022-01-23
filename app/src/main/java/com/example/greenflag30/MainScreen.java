package com.example.greenflag30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private ArrayList<String> list1;
    private ArrayAdapter<String> adapter;
    private ListView lw;
    private ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        lw = findViewById(R.id.list_View);
        backbtn = findViewById(R.id.backbtn2);
        Intent list = getIntent();

        list1 = list.getStringArrayListExtra("Emails");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list1);
        lw.setAdapter(adapter);

        backbtn.setOnClickListener(view -> {
            finish();
        });
    }


}