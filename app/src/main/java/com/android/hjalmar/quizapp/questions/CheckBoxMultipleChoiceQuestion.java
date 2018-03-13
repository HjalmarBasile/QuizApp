package com.android.hjalmar.quizapp.questions;

import com.android.hjalmar.quizapp.questions.abstraction.AbstractMultipleChoiceQuestion;
import com.android.hjalmar.quizapp.utils.StringUtils;

import java.security.InvalidParameterException;
import java.util.List;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class CheckBoxMultipleChoiceQuestion extends AbstractMultipleChoiceQuestion {

    /**
     * Constructs a check box multiple choice question with the specified parameters.
     *
     * @param score          The score of the question
     * @param text           The text of the question
     * @param correctAnswers The correct answers of the question
     * @param wrongAnswers   The wrong answers of the question
     */
    public CheckBoxMultipleChoiceQuestion(int score, String text, List<String> correctAnswers, List<String> wrongAnswers) {
        super(score, text, correctAnswers, wrongAnswers);

        if (correctAnswers.size() <= 1) {
            throw new InvalidParameterException("CheckBoxMultipleChoiceQuestion must have at least two correct answers");
        }
    }

    @Override
    public boolean hasMultipleRightAnswers() {
        return true;
    }

    @Override
    public int evaluateAnswer(List<String> userAnswers) {
        return StringUtils.containSameElements(correctAnswers, userAnswers) ? getScore() : 0;
    }
}
