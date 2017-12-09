package com.android.hjalmar.quizapp.questions.providers;

import com.android.hjalmar.quizapp.questions.CheckBoxMultipleChoiceQuestion;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class CheckBoxMultipleChoiceQuestionsProvider extends AbstractQuestionsProvider<CheckBoxMultipleChoiceQuestion> {

    private static final CheckBoxMultipleChoiceQuestionsProvider ourInstance = new CheckBoxMultipleChoiceQuestionsProvider();

    public static CheckBoxMultipleChoiceQuestionsProvider getInstance() {
        return ourInstance;
    }

    private CheckBoxMultipleChoiceQuestionsProvider() {
    }

}
