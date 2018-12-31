package Backend;

import android.content.Context;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MessageRequest implements Runnable, Closeable {

    private Context context;
    private MessageRequestType messageRequestType;
    private String messageToSend;
    private Thread messageThread;

    String ServerURL = "http://10.0.2.2:50248/api/message";

    /**
     * Constructor intialises the variables.
     * @param targetContext = The page where listView is shown.
     */
    public MessageRequest(Context targetContext) {
        context = targetContext;
    }

    /**
     * Upload a message to database.
     */
    public void SendMessage(String message)
    {
        messageRequestType = MessageRequestType.Send;
        messageToSend = message;

        messageThread = new Thread(this);
        messageThread.start();
    }

    /**
     * Implemented from runnable.
     * This is called when thread is started (i.e. when use() is called.);
     */
    @Override
    public void run() {
        try {
            switch(messageRequestType) {
                case Send:
                    RunSendMessage(messageToSend);
                    break;
            }
            messageThread = null;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void RunSendMessage(String message) throws Exception {

        // JSON string of message
        String urlParams = String.format("{\"message\":\"%s\"}", message);

        // Start connection
        URL myurl = new URL(ServerURL);
        HttpURLConnection con = (HttpURLConnection) myurl.openConnection();

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

    @Override
    public void close() throws IOException {
        messageThread = null;
    }
}

