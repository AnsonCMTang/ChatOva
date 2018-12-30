package com.example.chatova.chatova3;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        Message message = new Message("hi",true);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<Message> messages = new ArrayList<Message>();

        ArrayAdapter<Message> itemsAdapter =
                new ArrayAdapter<Message>(this, android.R.layout.simple_list_item_1, messages);

        listView.setAdapter(itemsAdapter);

        editText = (EditText) findViewById(R.id.editText);
    }

    public void SendMessage(View view){
        String message = editText.getText().toString();
        if (message.length()>0){

            editText.getText().clear();
        }
    }
}
