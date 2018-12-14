package com.sample.multiplechoicequiz;

/**
 * Created by xcode on 2018-03-31.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighScoreActivity extends AppCompatActivity {

    private static final String HIGH_SCORE_KEY = "highscore";
    private static final String CURRENT_SCORE_KEY = "current_score";
    private static final String HIGH_SCORE =
            "Current High Score:";
    private static final String CURRENT_SCORE =
            "Your Score:";

    private TextView mCongratulations;
    private TextView mHighScoreTextView;
    private TextView mCurrentScoreTextView;

    private Button mRestartButton;

    private int mHighScore;
    private int mCurrentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        if (savedInstanceState != null) {
            mHighScore = savedInstanceState.getInt(HIGH_SCORE_KEY, 0);
            mCurrentScore = savedInstanceState.getInt(CURRENT_SCORE_KEY, 0);
        } else {
            mHighScore = getIntent().getIntExtra(HIGH_SCORE, 0);
            mCurrentScore = getIntent().getIntExtra(CURRENT_SCORE, 0);
        }

        mCongratulations = findViewById(R.id.congratulations);
        mCongratulations.setVisibility(mCurrentScore > mHighScore ? View.VISIBLE : View.INVISIBLE);

        mHighScoreTextView = findViewById(R.id.high_score);
        mHighScoreTextView.setText(mHighScore + "");

        mCurrentScoreTextView = findViewById(R.id.current_score);
        mCurrentScoreTextView.setText(mCurrentScore + "");

        mRestartButton = findViewById(R.id.restart_button);
        mRestartButton.setOnClickListener(__ -> {
            Intent intent = new Intent(HighScoreActivity.this, QuizActivity.class);
            startActivity(intent);
        });
    }

    public static Intent newIntent(Context packageContext, int highScore, int currentScore) {
        Intent intent = new Intent(packageContext, HighScoreActivity.class);
        intent.putExtra(HIGH_SCORE, highScore);
        intent.putExtra(CURRENT_SCORE, currentScore);

        return intent;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(HIGH_SCORE_KEY, mHighScore);
        savedInstanceState.putInt(CURRENT_SCORE_KEY, mCurrentScore);
    }
}
