package com.android.hjalmar.quizapp.questions.providers;

import com.android.hjalmar.quizapp.questions.FreeTextQuestion;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class FreeTextQuestionsProvider extends AbstractQuestionsProvider<FreeTextQuestion> {

    private static final FreeTextQuestionsProvider ourInstance = new FreeTextQuestionsProvider();

    public static FreeTextQuestionsProvider getInstance() {
        return ourInstance;
    }

    private FreeTextQuestionsProvider() {
    }

}
