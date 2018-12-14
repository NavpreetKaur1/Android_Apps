package ca.sheridancollege.gomezri.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String ANSWERED_QUESTIONS = "answered";
    private static final String CHEATED_QUESTIONS = "cheated";
    private static final int REQUEST_CODE_CHEAT = 0;

    private Button trueButton;
    private Button falseButton;
    private Button cheatButton;
    private ImageButton prevButton;
    private ImageButton nextButton;

    private TextView questionTextView;

    private Question[] questionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    // List of Integers to track state of answers: not answered, not correct, and correct.
    // (Initially used a Boolean array, but savedInstance only supports boolean)
    private ArrayList<Integer> answerBank = new ArrayList<>(Collections.nCopies(questionBank.length, -1));
    private ArrayList<Integer> cheatedQuestions = new ArrayList<>(Collections.nCopies(questionBank.length, 0));

    private int currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt(KEY_INDEX, 0);
            answerBank = savedInstanceState.getIntegerArrayList(ANSWERED_QUESTIONS);
            cheatedQuestions = savedInstanceState.getIntegerArrayList(CHEATED_QUESTIONS);
        }

        questionTextView = findViewById(R.id.question_text_view);
        questionTextView.setOnClickListener(__ -> {
            currentQuestion = (currentQuestion + 1) % questionBank.length;
            updateQuestion();
        });

        trueButton = findViewById(R.id.true_button);
        trueButton.setOnClickListener(__ -> checkAnswer(true));

        falseButton = findViewById(R.id.false_button);
        falseButton.setOnClickListener(__ -> checkAnswer(false));

        prevButton = findViewById(R.id.prev_button);
        prevButton.setOnClickListener(__ -> {
            // Java's modulus implementation is questionable, hence the following code.
            // In Java8, Math.floorMod produces the desired behavior:
            //   Math.floorMod(-1, 5) = 4 |vs| -1 % 5 = -1
            // But for the sake of maximum compatibility I'm keeping this.
            currentQuestion = ((currentQuestion - 1) % questionBank.length + questionBank.length) % questionBank.length;
            updateQuestion();
        });

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(__ -> {
            currentQuestion = (currentQuestion + 1) % questionBank.length;
            updateQuestion();
        });

        cheatButton = findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(__ -> {
            Log.d(TAG, "Starting cheat activity");

            boolean answerIsTrue = questionBank[currentQuestion].isAnswerTrue();
            Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
            startActivityForResult(intent, REQUEST_CODE_CHEAT);
        });

        updateQuestion();
    }

    private void updateQuestion() {
        int question = questionBank[currentQuestion].getTextResId();
        questionTextView.setText(question);
        toggleButtons();
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = questionBank[currentQuestion].isAnswerTrue();
        int messageResId;

        if (cheatedQuestions.get(currentQuestion).equals(1)) {
            messageResId = R.string.judgment_toast;
        } else if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        // Record answer and toggle buttons
        answerBank.set(currentQuestion, userPressedTrue == answerIsTrue ? 1 : 0);
        toggleButtons();

        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();

        if (!answerBank.contains(-1)) {
            int numQuestions = answerBank.size();
            int numCorrect = 0;
            for (Integer answer : answerBank) {
                numCorrect = numCorrect + answer;
            }

            double score = (double) numCorrect / numQuestions;
            int percentage = (int) (score * 100);

            Toast doneToast = Toast.makeText(this,
                    String.format("Your score: %d%% (%d/%d)", percentage, numCorrect, numQuestions),
                    Toast.LENGTH_SHORT);
            doneToast.setGravity(Gravity.TOP, 0, 200);
            doneToast.show();
        }
    }

    // Disable buttons for questions that have been answered
    private void toggleButtons() {
        trueButton.setEnabled(answerBank.get(currentQuestion) == -1);
        falseButton.setEnabled(answerBank.get(currentQuestion) == -1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            cheatedQuestions.set(currentQuestion, CheatActivity.wasAnswerShown(data) ? 1 : 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, currentQuestion);
        savedInstanceState.putIntegerArrayList(ANSWERED_QUESTIONS, answerBank);
        savedInstanceState.putIntegerArrayList(CHEATED_QUESTIONS, cheatedQuestions);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}
