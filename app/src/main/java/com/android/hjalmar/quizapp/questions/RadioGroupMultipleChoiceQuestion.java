package com.android.hjalmar.quizapp.questions;

import java.util.Collections;
import java.util.List;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class RadioGroupMultipleChoiceQuestion extends AbstractMultipleChoiceQuestion {

    private String correctAnswer;

    /**
     * Constructs a radio group multiple choice question with the specified parameters.
     *
     * @param score         The score of the question
     * @param text          The text of the question
     * @param correctAnswer The correct answer of the question
     * @param wrongAnswers  The wrong answers of the question
     */
    public RadioGroupMultipleChoiceQuestion(int score, String text, String correctAnswer, List<String> wrongAnswers) {
        super(score, text, Collections.singletonList(correctAnswer), wrongAnswers);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean hasMultipleRightAnswers() {
        return false;
    }

    @Override
    public int evaluateAnswer(List<String> userAnswers) {
        if (userAnswers.size() != 1) {
            throw new IllegalArgumentException("AbstractMultipleChoiceQuestion allows only one single correct answer");
        }
        String userAnswer = userAnswers.get(0);
        return correctAnswer.equalsIgnoreCase(userAnswer) ? getScore() : 0;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
