package com.example.chatova.chatova3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {

    /**
     * A list which stores all messages.
     */
    private List<Message> Messages = new ArrayList<Message>();

    /**
     * The context in which the adapter is in.
     */
    Context context;

    MessageAdapter(Context context) {
        this.context = context;
    }

    /**
     * Add a new Message to the adapter.
     */
    public void Add(Message message) {
        Messages.add(message);
        notifyDataSetChanged();
    }

    /**
     * Set to a new list list of messages. Previous messages are deleted.
     */
    public void SetMessages(List<String> messages, boolean itsmemario) {
        Messages.clear();
        for (String message : messages) {
            Messages.add(new Message(message, itsmemario));
        }
        notifyDataSetChanged();
    }

    /**
     * Add new messages to the adapter. Previous messages are kept.
     */
    public void AddAll(List<String> messages, boolean itsmemario) {
        for (String message : messages) {
            Messages.add(new Message(message, itsmemario));
        }
        notifyDataSetChanged();
    }

    /**
     * Returns the number of messages.
     */
    @Override
    public int getCount() {
        return Messages.size();
    }

    /**
     * Returns the nth message stored in this adapter.
     */
    @Override
    public Message getItem(int n) {
        return Messages.get(n);
    }

    /**
     * Returns the id of the nth message, which is n.
     */
    @Override
    public long getItemId(int n) {
        return n;
    }


    /**
     * Updates the UI such that messages are shown.
     */
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        // Get message
        Message message = getItem(i);

        // Set message.
        if (message.isItsmemario()) {
            convertView = messageInflater.inflate(R.layout.self_message, null);
            convertView.setTag(new MarioMessageViewHolder(message, convertView));
        } else {
            convertView = messageInflater.inflate(R.layout.other_message, null);
            convertView.setTag(new NotMarioMessageViewHolder(message, convertView));
        }
        return convertView;
    }

    /**
     * A wrapper of all components required for a luigi message.
     */
    class MarioMessageViewHolder {
        TextView MessageBody;

        private MarioMessageViewHolder(Message message, View convertView) {
            MessageBody = (TextView) convertView.findViewById(R.id.message_body);
            MessageBody.setText(message.getText());
        }
    }

    /**
     * A wrapper of all components required for a mario message.
     */
    class NotMarioMessageViewHolder {
        View Avatar;
        TextView Name;
        TextView MessageBody;

        private NotMarioMessageViewHolder(Message message, View convertView) {
            Avatar = (View) convertView.findViewById(R.id.avatar);
            Name = (TextView) convertView.findViewById(R.id.name);
            MessageBody = (TextView) convertView.findViewById(R.id.message_body);
            MessageBody.setText(message.getText());
        }
    }
}
