package com.android.hjalmar.quizapp.questions.providers;

import com.android.hjalmar.quizapp.questions.FreeTextQuestion;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public class FreeTextQuestionsProvider extends AbstractQuestionsProvider<FreeTextQuestion> {

    private static FreeTextQuestionsProvider ourInstance = null;

    public static synchronized FreeTextQuestionsProvider getInstance() {
        if (ourInstance == null) {
            ourInstance = new FreeTextQuestionsProvider();
        }
        return ourInstance;
    }

    private FreeTextQuestionsProvider() {
    }

}
