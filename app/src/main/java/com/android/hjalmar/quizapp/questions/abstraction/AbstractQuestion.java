package com.android.hjalmar.quizapp.questions.abstraction;

import java.security.InvalidParameterException;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public abstract class AbstractQuestion implements IQuestion {

    private int score;
    private String text;

    /**
     * Constructs a questions with the specified parameters.
     *
     * @param score The score of the question
     * @param text  The text of the question
     */
    protected AbstractQuestion(int score, String text) {
        if (score <= 0) {
            throw new InvalidParameterException("AbstractQuestion must have a positive score");
        }
        if (text.isEmpty()) { /* If text is null a NullPointerException will be thrown by default */
            throw new InvalidParameterException("AbstractQuestion must have a non empty body text");
        }

        this.score = score;
        this.text = text;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getText() {
        return text;
    }

}
