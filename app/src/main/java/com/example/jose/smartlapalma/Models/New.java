package com.example.jose.smartlapalma.Models;

public class New {

    public static final String titleNewKey = "title";
    public static final String descriptionNewKey = "description";
    public static final String textNewKey = "text";
    public static final String dateNewKey = "date";

    private String mTitle;
    private String mDescription;
    private String mText;
    private String mDate;

    public New (String title, String description, String text, String date){
        mTitle = title;
        mDescription = description;
        mText = text;
        mDate = date;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmText() {
        return mText;
    }

    public String getmDate() {
        return mDate;
    }
}
