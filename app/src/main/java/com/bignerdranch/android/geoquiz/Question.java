package com.bignerdranch.android.geoquiz;

public class Question {
    private int mTextResId; // question resource id (the string is defined in strings.xml
    private boolean mAnswerTrue; // question answer (true or false)

    public Question(int textResId, boolean answerTrue){
        this.mTextResId = textResId;
        this.mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
