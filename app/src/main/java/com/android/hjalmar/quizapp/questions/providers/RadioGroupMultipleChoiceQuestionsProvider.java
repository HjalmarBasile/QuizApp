package com.android.hjalmar.quizapp.questions.providers;

import com.android.hjalmar.quizapp.questions.RadioGroupMultipleChoiceQuestion;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class RadioGroupMultipleChoiceQuestionsProvider extends AbstractQuestionsProvider<RadioGroupMultipleChoiceQuestion> {

    private static final RadioGroupMultipleChoiceQuestionsProvider ourInstance = new RadioGroupMultipleChoiceQuestionsProvider();

    public static RadioGroupMultipleChoiceQuestionsProvider getInstance() {
        return ourInstance;
    }

    private RadioGroupMultipleChoiceQuestionsProvider() {
    }

}
