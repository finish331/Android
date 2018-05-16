package com.example.user.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class secondActivity extends AppCompatActivity {
    private int itemNumber;
    private String temp;
    private ArrayList<String > result = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle = this.getIntent().getExtras();
        result = bundle.getStringArrayList("result");


        ListView list = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,result);

        list.setAdapter(adapter);
    }
}
