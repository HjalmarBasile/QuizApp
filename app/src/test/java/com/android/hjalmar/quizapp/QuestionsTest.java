package com.android.hjalmar.quizapp;

import com.android.hjalmar.quizapp.questions.CheckBoxMultipleChoiceQuestion;
import com.android.hjalmar.quizapp.questions.FreeTextQuestion;
import com.android.hjalmar.quizapp.questions.RadioGroupMultipleChoiceQuestion;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by hjalmar
 * On 13/03/2018.
 */

public class QuestionsTest {

    @Test
    public void checkBoxMultipleChoiceQuestionTest() {
        CheckBoxMultipleChoiceQuestion checkBoxMultipleChoiceQuestion = new CheckBoxMultipleChoiceQuestion(1, "text", Arrays.asList("a", "b"), Arrays.asList("c", "d"));
        Assert.assertTrue(checkBoxMultipleChoiceQuestion.isMultipleChoice());
        Assert.assertTrue(checkBoxMultipleChoiceQuestion.hasMultipleRightAnswers());
        Assert.assertEquals(1, checkBoxMultipleChoiceQuestion.getScore());
        Assert.assertEquals("text", checkBoxMultipleChoiceQuestion.getText());
        Assert.assertEquals(4, checkBoxMultipleChoiceQuestion.getNumberOfChoices());
    }

    @Test
    public void radioGroupMultipleChoiceQuestionTest() {
        RadioGroupMultipleChoiceQuestion radioGroupMultipleChoiceQuestion = new RadioGroupMultipleChoiceQuestion(1, "text", "a", Arrays.asList("b", "c", "d"));
        Assert.assertTrue(radioGroupMultipleChoiceQuestion.isMultipleChoice());
        Assert.assertFalse(radioGroupMultipleChoiceQuestion.hasMultipleRightAnswers());
        Assert.assertEquals(1, radioGroupMultipleChoiceQuestion.getScore());
        Assert.assertEquals("text", radioGroupMultipleChoiceQuestion.getText());
        Assert.assertEquals("a", radioGroupMultipleChoiceQuestion.getCorrectAnswer());
        Assert.assertEquals(4, radioGroupMultipleChoiceQuestion.getNumberOfChoices());
    }

    @Test
    public void freeTextQuestionTest() {
        FreeTextQuestion freeTextQuestion = new FreeTextQuestion(1, "text", "answer");
        Assert.assertFalse(freeTextQuestion.isMultipleChoice());
        Assert.assertEquals(1, freeTextQuestion.getScore());
        Assert.assertEquals("text", freeTextQuestion.getText());
        Assert.assertEquals("answer", freeTextQuestion.getAnswer());
    }

}
