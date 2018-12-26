package com.example.chatova.chatova3;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class deals with sending new messages and getting messages from the server.
 */
public class Messages implements Runnable {

    public ArrayList<String> Items = new ArrayList<String>();
    private ListView ListView;
    private Context Context;

    /**
     * Constructor intialises the variables.
     * @param listView = The listView that will show the messages.
     * @param context = The page where listView is shown.
     */
    public Messages(ListView listView, Context context) {
        ListView = listView;
        Context = context;
    }

    /**
     * Run this to start the thread, which wil in turn call run();
     */
    public void use()
    {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Implemented from runnable.
     * This is called when thread is started (i.e. when use() is called.);
     */
    @Override
    public void run() {
        try {
            // Get the messages from the database.
            Items = GetMessages();

            // Put the messages on listview
            ArrayAdapter<String> itemsAdapter =
                    new ArrayAdapter<String>(Context, android.R.layout.simple_list_item_1, Items);
            ListView.setAdapter(itemsAdapter);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void SendPost(String message) throws Exception {

        // URL of server API
        String url = "http://10.0.2.2/api/message";

        // JSON string of message
        String urlParams = String.format("{\"message\":\"%s\"}", message);

        // Start connection
        URL myurl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
        System.out.println("AAAAA");

        try {
            // Set connection settings
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            // Send the message to server
            OutputStream os = con.getOutputStream();
            os.write(urlParams.getBytes());
            os.flush();

            // Read the response.
            Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            for (int c; (c = in.read()) >= 0;)
                System.out.print((char)c);

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            con.disconnect();
        }
    }

    private ArrayList<String> GetMessages() throws Exception {
        // URL of server API
        String url = "http://10.0.2.2:50248/api/message/lol";

        // Start connection
        URL myurl = new URL(url);
        HttpURLConnection con = (HttpURLConnection) myurl.openConnection();

        try {
            con.setRequestMethod("GET");

            // Get the reponse data.
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // Response data will be a string.
            String messageListString;

            // Stores the new messages.
            ArrayList<String> messages;

            // Response string is parsed into an ArrayList of messages.
            if ((messageListString = in.readLine()) != null) {
                messageListString = messageListString.substring(1, messageListString.length() - 1);
                messages = new ArrayList<String>(Arrays.asList(messageListString.split(",")));
            }
            else {
                // Stop reading the response data stream.
                in.close();
                throw new Exception("No messages found!");
            }

            return messages;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        finally {
            con.disconnect();
        }
    }
}
