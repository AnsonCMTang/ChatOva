package com.example.chatova.chatova3;

/**
 * A wrapper for a message.
 */
class Message {
    private String Text;
    private boolean itsmemario;

    Message(String text, boolean itsmemario) {
        this.Text = text;
        this.itsmemario = itsmemario;
    }

    /**
     * Get the text stored in this message.
     */
    String getText() {
        return Text;
    }

    /**
     * IS IT MARIO? OR IS YO MAMA?
     */
    boolean isItsmemario() {
        return itsmemario;
    }
}
