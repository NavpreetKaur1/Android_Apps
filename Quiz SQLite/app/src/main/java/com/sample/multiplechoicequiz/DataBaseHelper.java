package com.sample.multiplechoicequiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by xcode on 2018-04-08.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    // Database Name
    public static String DATABASE_NAME = "questionBank.db";
    // Current version of database
    private static final int DATABASE_VERSION = 1;
    // Name of table
    private static final String TABLE_QUESTION = "QuestionBank";
    // All fields used in database table
    private static final String KEY_ID = "id";
    private static final String QUESTION = "question";
    private static final String OPTION1 = "option1";
    private static final String OPTION2 = "option2";
    private static final String OPTION3 = "option3";
    private static final String OPTION4 = "option4";
    private static final String ANSWER = "answer";


    public static String TAG = "my_tag";

    // Client Table Create Query in this string
    private static final String CREATE_TABLE_CLIENT = "CREATE TABLE "
            + TABLE_QUESTION + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT,"
            + OPTION1 + " TEXT," + OPTION2 + " TEXT, " + OPTION3 + " TEXT," +
            OPTION4 + " TEXT, " + ANSWER + " TEXT );";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CLIENT); // drop table if exists
        onCreate(db);
    }

        public long addInitialQuestion(QuestionBank question) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(QUESTION, question.getQuestion());
        values.put(OPTION1, question.getChoice(0));
        values.put(OPTION2, question.getChoice(1));
        values.put(OPTION3, question.getChoice(2));
        values.put(OPTION4, question.getChoice(3));
        values.put(ANSWER, question.getAnswer());

        long insert = db.insert(TABLE_QUESTION, null, values);

        return insert;

    }

    public List<QuestionBank> getAllQuestions() {
        List<QuestionBank> questionArrayList = new ArrayList<QuestionBank>();

        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                QuestionBank question = new QuestionBank();

                String questText = c.getString(c.getColumnIndex(QUESTION));
                question.setQuestion(questText);

                String choice1Text = c.getString(c.getColumnIndex(OPTION1));
                question.setChoice(0, choice1Text);

                String choice2Text = c.getString(c.getColumnIndex(OPTION2));
                question.setChoice(1, choice2Text);

                String choice3Text = c.getString(c.getColumnIndex(OPTION3));
                question.setChoice(2, choice3Text);

                String choice4Text = c.getString(c.getColumnIndex(OPTION4));
                question.setChoice(3, choice4Text);


                String answerText = c.getString(c.getColumnIndex(ANSWER));
                question.setAnswer(answerText);
                // adding to Clients list
                questionArrayList.add(question);
            } while (c.moveToNext());
            //Randomize Questions
            Collections.shuffle(questionArrayList);
        }
        return questionArrayList;
    }
}
