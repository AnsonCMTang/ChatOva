package Backend;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.ListView;

import com.example.chatova.chatova3.MessageAdapter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class creates a thread which repeatedly retrieves new messages from the database.
 */
public class MessageUpdateThread extends Activity implements Runnable, Closeable {

    private ListView ListView;
    private MessageAdapter MessageAdapter;
    private Thread messageThread;

    String ServerURL = "http://10.0.2.2:50248/api/message";

    /**
     * Constructor intialises the variables.
     * @param messageAdapter = The messageAdapter used for showing the messages.
     */
    public MessageUpdateThread(ListView listView, MessageAdapter messageAdapter)
    {
        ListView = listView;
        MessageAdapter = messageAdapter;
        messageThread = new Thread(this);
        messageThread.start();
    }

    /**
     * Ran when thread is started.
     */
    @Override
    public void run() {
        while(messageThread != null) {
            try {
                // Final, so the UI thread can use this list.
                final List<String> messages = RunGetMessages();

                // This is used to lock down the thread, so waiting can occur.
                synchronized (this) {

                    // Uses the UI thread to update the ListView UI.
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // Set messages in MessageAdapter.
                            MessageAdapter.SetMessages(messages, false);

                            // Select the last row so it will scroll into view...
                            ListView.setSelection(MessageAdapter.getCount() - 1);
                        }
                    });

                    wait(5000);
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                System.out.println("STOPPINGGGG!");
                messageThread = null;
            }
        }
    }

    /**
     * Get messages from the database.
     */
    private List<String> RunGetMessages() throws Exception {

        // Start connection
        URL myurl = new URL(String.format("%s?com=getmessages", ServerURL));
        HttpURLConnection con = (HttpURLConnection) myurl.openConnection();

        try {
            con.setRequestMethod("GET");

            // Get the reponse data.
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // Response data will be a string.
            String messageListString;

            // Stores the new messages.
            List<String> messages;

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

    @Override
    public void close() throws IOException {
        messageThread = null;
    }
}
