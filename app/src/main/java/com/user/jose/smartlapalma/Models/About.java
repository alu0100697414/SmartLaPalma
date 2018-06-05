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

import java.util.ArrayList;
import java.util.List;

public class About {

    public static final String mIntroTextKey = "intro";
    public static final String mQuestionKey = "question";
    public static final String mAnswerKey = "answer";

    private int mTotalNumber;

    private String mIntroText;

    private List<String> mQuestions;
    private List<String> mAnswers;

    public About (){
        mQuestions = new ArrayList<String>();
        mAnswers = new ArrayList<String>();
    }

    public void setmTotalNumber(int mTotalNumber) {
        this.mTotalNumber = mTotalNumber;
    }

    public void setmIntroText(String mIntroText) {
        this.mIntroText = mIntroText;
    }

    public void setmQuestion(String mQuestion) {
        this.mQuestions.add(mQuestion);
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswers.add(mAnswer);
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

