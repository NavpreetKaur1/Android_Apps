package com.sample.multiplechoicequiz;

import java.util.*;
import android.content.Context;
/**
 * Created by xcode on 2018-04-08.
 */

public class QuestionBank2 {

    List<QuestionBank> list = new ArrayList<>();
    DataBaseHelper dataBaseHelper;

    public int getLength() {return list.size();}

    public String getQuestion(int a) {return list.get(a).getQuestion();}

    public String getChoice(int index, int num) {return list.get(index).getChoice(num-1);}

    public String getCorrectAnswer(int a) {return list.get(a).getAnswer();}

    public void initQuestions(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
        list = dataBaseHelper.getAllQuestions();

        if(list.isEmpty()) {
            dataBaseHelper.addInitialQuestion(new QuestionBank("1. When did Google acquire Android? ", new String[] {"2001", "2003", "2004", "2005"}, "2005"));
            dataBaseHelper.addInitialQuestion(new QuestionBank("2. What is the name of build toolkit for Android Studio?", new String[] {"JVM", "Gradle", "Dalvik", "HAXM"}, "Gradel"));
            dataBaseHelper.addInitialQuestion(new QuestionBank("3. What widget can replace any use of radio buttons?", new String[] {"Toggle", "Spinner", "Switch", "CheckBox"}, "Spinner"));
            dataBaseHelper.addInitialQuestion(new QuestionBank("4. What is the most recent Android OS ?", new String[] {"Oreo", "Nougat", "Marshmallow", "Octopus"}, "Oreo"));

            list = dataBaseHelper.getAllQuestions();
        }
    }
}
