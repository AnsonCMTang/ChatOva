package com.example.chatova.chatova3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> items = new ArrayList<String>(Arrays.asList("HI", "BYE", "HI", "BYE", "HI", "BYE", "HI", "BYE", "HI", "BYE", "HI", "BYE"));

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        listView.setAdapter(itemsAdapter);
    }
}
