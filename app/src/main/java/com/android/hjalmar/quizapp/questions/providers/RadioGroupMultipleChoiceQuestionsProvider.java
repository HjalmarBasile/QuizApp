package com.android.hjalmar.quizapp.questions.providers;

import com.android.hjalmar.quizapp.questions.RadioGroupMultipleChoiceQuestion;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class RadioGroupMultipleChoiceQuestionsProvider extends AbstractQuestionsProvider<RadioGroupMultipleChoiceQuestion> {

    private static RadioGroupMultipleChoiceQuestionsProvider ourInstance = null;

    public static synchronized RadioGroupMultipleChoiceQuestionsProvider getInstance() {
        if (ourInstance == null) {
            ourInstance = new RadioGroupMultipleChoiceQuestionsProvider();
        }
        return ourInstance;
    }

    private RadioGroupMultipleChoiceQuestionsProvider() {
    }

}
