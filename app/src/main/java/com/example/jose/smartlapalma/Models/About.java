package com.example.jose.smartlapalma.Models;

import java.util.List;

public class About {

    public static final String mIntroTextKey = "intro";

    private int mTotalNumber;

    private String mIntroText;

    private List<String> mQuestions;
    private List<String> mAnswers;

    public About (){}

    public void setmTotalNumber(int mTotalNumber) {
        this.mTotalNumber = mTotalNumber;
    }

    public void setmIntroText(String mIntroText) {
        this.mIntroText = mIntroText;
    }

    public void setmQuestions(List<String> mQuestions) {
        this.mQuestions = mQuestions;
    }

    public void setmAnswers(List<String> mAnswers) {
        this.mAnswers = mAnswers;
    }

    public int getmTotalNumber() {
        return mTotalNumber;
    }

    public String getmIntroText() {
        return mIntroText;
    }

    public List<String> getmQuestions() {
        return mQuestions;
    }

    public List<String> getmAnswers() {
        return mAnswers;
    }
}

