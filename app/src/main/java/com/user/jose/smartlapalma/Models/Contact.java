package com.user.jose.smartlapalma.Models;

/**
 *
 * © José Ángel Concepción Sánchez
 All rights reserved. The total or partial reproduction of this work by any means or procedure,
 including printing, reprography, microfilm, computer processing or any other system, as well as
 the distribution of copies by rental or loan, is prohibited without the author's written
 authorization or the limits authorized by the Law on Intellectual Property.
 *
 */

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

    private String mWelcomeMessage;
    private String mTextMessage;
    private String mEmail;
    private String mLastMessage;

    public Contact(){}

    public Contact(String welcome, String text, String email, String last){

        this.mWelcomeMessage = welcome;
        this.mTextMessage = text;
        this.mEmail = email;
        this.mLastMessage = last;
    }

    public void setmWelcomeMessage(String mWelcomeMessage) {
        this.mWelcomeMessage = mWelcomeMessage;
    }

    public void setmTextMessage(String mTextMessage) {
        this.mTextMessage = mTextMessage;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmLastMessage(String mLastMessage) {
        this.mLastMessage = mLastMessage;
    }

    public String getmWelcomeMessage() {
        return mWelcomeMessage;
    }

    public String getmTextMessage() {
        return mTextMessage;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmLastMessage() {
        return mLastMessage;
    }
}
