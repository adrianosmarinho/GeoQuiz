package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;


    // some notes
    // AppCompatActivity is a subclass of Android’s Activity class that
    //provides compatibility support for older versions of Android.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * The onCreate(Bundle) method is called when an instance of the activity subclass is created. When an
         activity is created, it needs a UI to manage. To get the activity its UI, you call the following Activity
         method:
         public void setContentView(int layoutResID)*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // wiring up the Question TextView
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        // we need to get references for the widgets (wiring up the buttons)
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(false);
            }
        });

        // wiring up the Next Button
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // increments the question bank array index
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                // updates the question
                updateQuestion();

            }
        });

        // wiring up the Previous Button
        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // increments the question bank array index
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                // check if index is -1, then jumps back to mQuestionBank.length - 1 to loop
                if (mCurrentIndex < 0)
                    mCurrentIndex = mQuestionBank.length -1;
                // updates the question
                updateQuestion();

            }
        });
    }

    /**
     * Updates the question ("redraws" the GUI)
     */
    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    /**
     * Check if the answer of the user
     * @param userPressedTrue True if the user pressed the True Button, False otherwise
     */
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        }
        else{
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }


}
