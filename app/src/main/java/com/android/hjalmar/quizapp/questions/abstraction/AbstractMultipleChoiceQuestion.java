package com.android.hjalmar.quizapp.questions.abstraction;

import com.android.hjalmar.quizapp.utils.RandomPermutationUtils;
import com.android.hjalmar.quizapp.utils.StringUtils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public abstract class AbstractMultipleChoiceQuestion extends AbstractQuestion {

    protected List<String> correctAnswers;
    private List<String> wrongAnswers;
    private List<String> permutatedAnswers;
    private int numberOfChoices;

    /**
     * Constructs a multiple choice question with the specified parameters.
     *
     * @param score          The score of the question
     * @param text           The text of the question
     * @param correctAnswers The correct answers of the question
     * @param wrongAnswers   The wrong answers of the question
     */
    public AbstractMultipleChoiceQuestion(int score, String text, List<String> correctAnswers, List<String> wrongAnswers) {
        super(score, text);
        if (correctAnswers.size() == 0) {
            throw new InvalidParameterException("AbstractMultipleChoiceQuestion must have at least one correct answer");
        }
        if (wrongAnswers.size() == 0) {
            throw new InvalidParameterException("AbstractMultipleChoiceQuestion must have at least one wrong answer");
        }
        if (StringUtils.hasEmptyArgument(correctAnswers)) {
            throw new InvalidParameterException("AbstractMultipleChoiceQuestion cannot contain empty correct answers");
        }
        if (StringUtils.hasEmptyArgument(wrongAnswers)) {
            throw new InvalidParameterException("AbstractMultipleChoiceQuestion cannot contain empty wrong answers");
        }

        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.permutatedAnswers = buildPermutatedAnswersList();
        this.numberOfChoices = permutatedAnswers.size();
    }

    /* Called just once in the constructor */
    private List<String> buildPermutatedAnswersList() {
        List<String> answers = new ArrayList<>();
        answers.addAll(correctAnswers);
        answers.addAll(wrongAnswers);
        return RandomPermutationUtils.getRandomPermutation(answers);
    }

    @Override
    public boolean isMultipleChoice() {
        return true;
    }

    /**
     * Checks if the question has more than one correct answer
     *
     * @return true if and only if the question has more than one correct answer
     */
    public abstract boolean hasMultipleRightAnswers();

    /**
     * Returns the total number of possible answers.
     *
     * @return the total number of choices
     */
    public int getNumberOfChoices() {
        return numberOfChoices;
    }

    /**
     * Returns the array constructed by the buildPermutatedAnswersList method
     *
     * @return the private array with permutated answers
     */
    public ArrayList<String> getPermutatedAnswers() {
        return (ArrayList<String>) permutatedAnswers;
    }

}
