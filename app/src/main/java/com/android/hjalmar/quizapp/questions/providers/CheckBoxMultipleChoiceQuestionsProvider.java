package com.android.hjalmar.quizapp.questions.providers;

import com.android.hjalmar.quizapp.questions.CheckBoxMultipleChoiceQuestion;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class CheckBoxMultipleChoiceQuestionsProvider extends AbstractQuestionsProvider<CheckBoxMultipleChoiceQuestion> {

    private static CheckBoxMultipleChoiceQuestionsProvider ourInstance = null;

    public static synchronized CheckBoxMultipleChoiceQuestionsProvider getInstance() {
        if (ourInstance == null) {
            ourInstance = new CheckBoxMultipleChoiceQuestionsProvider();
        }
        return ourInstance;
    }

    private CheckBoxMultipleChoiceQuestionsProvider() {
    }

}
