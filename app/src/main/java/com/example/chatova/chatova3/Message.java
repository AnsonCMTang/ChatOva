package com.example.chatova.chatova3;

public class Message {
    private String text;
    private boolean itsmemario;

    public Message(String text, boolean itsmemario) {
        this.text = text;
        this.itsmemario = itsmemario;
    }

    public String getText() {
        return text;
    }

    public boolean isItsmemario() {
        return itsmemario;
    }
}
