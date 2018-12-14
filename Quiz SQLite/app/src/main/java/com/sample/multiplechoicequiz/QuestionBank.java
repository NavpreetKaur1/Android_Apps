package com.sample.multiplechoicequiz;

public class QuestionBank {

    private String question;
    private String[] choice = new String [4];
    private String answer;

    public QuestionBank() {

    }

    public QuestionBank(String question, String[] choices, String answer) {
        this.question = question;
        this.choice[0] = choices[0];
        this.choice[1] = choices[1];
        this.choice[2] = choices[2];
        this.choice[3] = choices[3];
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoice(int i) {
        return choice[i];
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setChoice(int i, String choice) {
        this.choice[i] = choice;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
//    // Three ArrayList fields to store the Questions, Options and the Correct Answers
//    private ArrayList<String> textQuestions;
//    private ArrayList<List<String>> multipleChoice;
//    private  ArrayList<String> mCorrectAnswers;
//
//
//    public QuestionBank(ArrayList<String> textQuestions, ArrayList<List<String>> multipleChoice, ArrayList<String> correctAnswers) {
//        this.textQuestions = textQuestions;
//        this.multipleChoice = multipleChoice;
//        this.mCorrectAnswers = correctAnswers;
//    }
//
//    // method returns number of questions
//    public int getLength() {
//        return textQuestions.size();
//    }
//
//    // method returns question from array textQuestions[] based on array index
//    public String getQuestion(int a) {
//        String question = textQuestions.get(a);
//        return question;
//    }
//
//    public String getChoice(int index, int num) {
//        String choice0 = multipleChoice.get(index).get(num - 1);
//        return choice0;
//    }
//
//    public String getCorrectAnswer(int a) {
//        String answer = mCorrectAnswers.get(a);
//        return answer;
//    }
}