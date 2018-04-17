package com.example.jose.smartlapalma.Models;

import java.util.HashMap;

/**
 * Created by Jose on 17/04/2018.
 *
 * Object where is saved the nformation displayed on the Contact screen.
 */

public class Contact {

    public static final String welcomeMessageKey = "welcome";
    public static final String textMessageKey = "text";
    public static final String emailKey = "email";
    public static final String lastMessageKey = "goodbye";

    private HashMap<String, String> mWelcomeMessage;
    private HashMap<String, String> mTextMessage;
    private HashMap<String, String> mEmail;
    private HashMap<String, String> mLastMessage;

    public Contact(){}

    public Contact(HashMap welcome, HashMap text, HashMap email, HashMap last){

        this.mWelcomeMessage = welcome;
        this.mTextMessage = text;
        this.mEmail = email;
        this.mLastMessage = last;
    }

    public void setmWelcomeMessage(HashMap mWelcomeMessage) {
        this.mWelcomeMessage = mWelcomeMessage;
    }

    public void setmTextMessage(HashMap mTextMessage) {
        this.mTextMessage = mTextMessage;
    }

    public void setmEmail(HashMap mEmail) {
        this.mEmail = mEmail;
    }

    public void setmLastMessage(HashMap mLastMessage) {
        this.mLastMessage = mLastMessage;
    }

    public HashMap getmWelcomeMessage() {
        return mWelcomeMessage;
    }

    public HashMap getmTextMessage() {
        return mTextMessage;
    }

    public HashMap getmEmail() {
        return mEmail;
    }

    public HashMap getmLastMessage() {
        return mLastMessage;
    }
}
