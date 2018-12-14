package ca.sheridancollege.gomezri.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String CHEATED = "CHEAT_SHOWN";

    private static final String EXTRA_ANSWER_IS_TRUE =
            "ca.sheridancollege.gomezri.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "ca.sheridancollege.gomezri.geoquiz.answer_shown";

    private boolean answerIsTrue;
    private boolean answerIsShown;

    private TextView answerTextView;
    private Button showAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        answerTextView = findViewById(R.id.answer_text_view);

        if (savedInstanceState != null) {
            answerIsShown = savedInstanceState.getBoolean(CHEATED, false);
            if (answerIsShown) getAnswer();
            setAnswerShownResult(answerIsShown);
        }

        showAnswerButton = findViewById(R.id.show_answer_button);
        showAnswerButton.setOnClickListener(__ -> {
            getAnswer();
            setAnswerShownResult(true);
        });
    }

    private void getAnswer() {
        if (answerIsTrue) {
            answerTextView.setText(R.string.true_button);
        } else {
            answerTextView.setText(R.string.false_button);
        }
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        answerIsShown = isAnswerShown;
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(CHEATED, answerIsShown);
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }
}
