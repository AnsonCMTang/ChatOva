package com.example.chatova.chatova3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EditText messageText;
    private MessageAdapter messageAdapter;
    private ListView messageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Initialising the components of the page.
        messageText = (EditText) findViewById(R.id.editText);
        messageAdapter = new MessageAdapter(this);
        messageListView = (ListView) findViewById(R.id.listView);


        // Initial messages that show when logged on
        Message message1 = new Message("Yo mama so fat",true);
        Message message2 = new Message("Bye",false);

        messageAdapter.add(message1);
        messageAdapter.add(message2);

        messageListView.setAdapter(messageAdapter);
    }

    /**
     * Sends a message.
     */
    protected void SendMessage(View view){
        String message = messageText.getText().toString();
        if (message.length()>0){
            messageAdapter.add(new Message(message, true));

            messageText.getText().clear();
        }
    }
}
