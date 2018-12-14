package com.sample.multiplechoicequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private QuestionBank mQuestionLibrary;

    private static final String TAG = "QUIZ_ACTIVITY";
    private static final String CURRENT_SCORE_KEY = "CURRENT_SCORE";
    private static final String CURRENT_QUESTION_KEY = "CURRENT_QUESTION";

    private TextView mScoreView;   // view for current total score
    private TextView mQuestionView;  //current question to answer

    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView

    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private int mQuestionNumber = 0; // current question number


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(CURRENT_SCORE_KEY, 0);
            mQuestionNumber = savedInstanceState.getInt(CURRENT_QUESTION_KEY, 0);
        }
        // Load questions
        readFiles();
        // setup screen for the first question with four alternative to answer
        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice4 = (Button)findViewById(R.id.choice4);
        updateQuestion();
        // show current total score for the user
        updateScore(mScore);
    }

    private void updateQuestion() {
        // check if we are not outside array bounds for questions
        if (mQuestionNumber < mQuestionLibrary.getLength()) {
            // set the text for new question, and new 4 alternative to answer on four buttons
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber, 4));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);

            mQuestionNumber++;
        } else {
            Toast.makeText(QuizActivity.this, "It was the last question!", Toast.LENGTH_SHORT).show();
            mQuestionNumber++;
            checkScore();
        }
    }

    // show current total score for the user
    private void updateScore(int point) {
        mScoreView.setText("" + mScore+"/"+mQuestionLibrary.getLength());
    }

//    private void updateHighScore(int hPoint) {
//        mScoreView.setText("" + mScore+"/"+mQuestionLibrary.getLength());
//        // Increase question number a final time to prevent further actions
//        mQuestionNumber++;
//        checkScore();
//    }

    public void onClick(View view) {
        //all logic for all answers buttons in one method
        Button answer = (Button) view;

        if (mQuestionNumber > mQuestionLibrary.getLength()) {
            updateQuestion();
            return;
        }

        // if the answer is correct, increase the score
        if (answer.getText().equals(mAnswer)) {
            mScore = mScore + 1;
            Toast.makeText(QuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(QuizActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
        // show current total score for the user
        updateScore(mScore);
        // once user answer the question, we move on to the next one
        updateQuestion();
    }

    /*
     *This method checks the currect score, if mScore is higher than the previous highScore
     * If mScore is higher than highScore, it is stored in the SharedPreferences editor
     */
    private void checkScore() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.prefs_key), MODE_PRIVATE);
        int highScore = pref.getInt(getString(R.string.high_score_pref), 0);

        if (mScore > highScore) {
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt(getString(R.string.high_score_pref), mScore);
            editor.apply();
        }

        Intent intent = HighScoreActivity.newIntent(QuizActivity.this, highScore, mScore);
        startActivity(intent);
    }
/*
    private void SavePreferences(String key, int value){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        int strSavedScore = sharedPreferences.getInt("MEM1", 0);
        mScoreView.setText("" + mScore+"/"+mQuestionLibrary.getLength() + "Highest Score is " + strSavedScore);
        //mQuestionView.setText(strSavedScore);

    }
*/
    /*
    *Method saves question number and score for rotation data loss on rotation
    */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(CURRENT_SCORE_KEY, mScore);
        savedInstanceState.putInt(CURRENT_QUESTION_KEY, mQuestionNumber);
    }
    /*
     *The method reads the three text files stored inside the assets > data folder
     *The files are then stored into 3 seperate ArrayLists
     * After storing the files in the Array Lists they are sent to the questionBank constructor
     */
    private void readFiles() {
        //Three ArrayLists store the questions, options and the answers
        ArrayList<String> the_Questions = new ArrayList<>();
        ArrayList<List<String>> the_Options = new ArrayList<>();
        ArrayList<String> the_Answers = new ArrayList<>();

        // Read questions
        //InputStream input = getResources().openRawResource(R.raw.questions); not used
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("data/questions.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                the_Questions.add(line);
            }
        } catch (IOException e) {}

        // Read choices
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("data/choices.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                the_Options.add(Arrays.asList(line.split(", ?")));
            }
        } catch (IOException e) {}

        // Read answers
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("data/answers.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                the_Answers.addAll(Arrays.asList(line.split(", ?")));
            }
        } catch (IOException e) {}


        // Initialize question bank
        mQuestionLibrary = new QuestionBank(the_Questions, the_Options, the_Answers);
    }
}
