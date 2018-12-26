package com.example.chatova.chatova3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Get listView component by using its ID.
        ListView listView = (ListView) findViewById(R.id.listView);

        try {
            // Run the messages class to get messages from database and store it in ListView.
            Messages messages = new Messages(listView, this);
            messages.use();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
