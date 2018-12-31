package com.example.chatova.chatova3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import Backend.MessageUpdateThread;
import Backend.MessageRequest;

public class MainActivity extends AppCompatActivity {

    private EditText messageText;
    private MessageAdapter messageAdapter;
    private ListView messageListView;
    private MessageUpdateThread messageUpdateThread;
    private List<String> Messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Initialising the components of the page.
        messageText = (EditText) findViewById(R.id.editText);
        messageListView = (ListView) findViewById(R.id.listView);
        messageAdapter = new MessageAdapter(this);
        Messages = new ArrayList<String>();


        // Initial messages that show when logged on
        try{
            messageUpdateThread = new MessageUpdateThread(messageListView, messageAdapter);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        messageListView.setAdapter(messageAdapter);
    }

    /**
     * Sends a message.
     */
    protected void SendMessage(View view){
        String message = messageText.getText().toString();
        if (message.length()>0){
            try{
                // Upload message to database
                MessageRequest messageRequest = new MessageRequest(this);
                messageRequest.SendMessage(message);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
            messageText.getText().clear();
        }
    }
}
