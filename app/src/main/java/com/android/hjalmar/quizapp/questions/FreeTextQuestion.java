package com.android.hjalmar.quizapp.questions;

import java.util.List;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class FreeTextQuestion extends AbstractQuestion {

    private String answer;

    /**
     * Constructs a free text question with the specified parameters.
     *
     * @param score  The score of the question
     * @param text   The text of the question
     * @param answer The correct answer of the question
     */
    public FreeTextQuestion(int score, String text, String answer) {
        super(score, text);
        if (answer.isEmpty()) {
            throw new IllegalArgumentException("FreeTextQuestion must have a non empty correct answer");
        }
        this.answer = answer;
    }

    @Override
    public boolean isMultipleChoice() {
        return false;
    }

    @Override
    public int evaluateAnswer(List<String> userAnswers) {
        if (userAnswers.size() != 1) {
            throw new IllegalArgumentException("FreeTextQuestion allows only one single correct answer");
        }
        String userAnswer = userAnswers.get(0).trim();
        return answer.equalsIgnoreCase(userAnswer) ? getScore() : 0;
    }

    public String getAnswer() {
        return answer;
    }
}
