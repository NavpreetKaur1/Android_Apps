package com.sample.multiplechoicequiz;
import java.util.List;
import java.util.ArrayList;
public class QuestionBank {

    // Three ArrayList fields to store the Questions, Options and the Correct Answers
    private ArrayList<String> textQuestions;
    private ArrayList<List<String>> multipleChoice;
    private  ArrayList<String> mCorrectAnswers;


    public QuestionBank(ArrayList<String> textQuestions, ArrayList<List<String>> multipleChoice, ArrayList<String> correctAnswers) {
        this.textQuestions = textQuestions;
        this.multipleChoice = multipleChoice;
        this.mCorrectAnswers = correctAnswers;
    }

    // method returns number of questions
    public int getLength() {
        return textQuestions.size();
    }

    // method returns question from array textQuestions[] based on array index
    public String getQuestion(int a) {
        String question = textQuestions.get(a);
        return question;
    }

    public String getChoice(int index, int num) {
        String choice0 = multipleChoice.get(index).get(num - 1);
        return choice0;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers.get(a);
        return answer;
    }
}