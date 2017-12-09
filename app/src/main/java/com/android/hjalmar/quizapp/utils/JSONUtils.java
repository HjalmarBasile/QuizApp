package com.android.hjalmar.quizapp.utils;

import android.content.Context;
import android.support.annotation.RawRes;

import com.android.hjalmar.quizapp.R;
import com.android.hjalmar.quizapp.questions.CheckBoxMultipleChoiceQuestion;
import com.android.hjalmar.quizapp.questions.FreeTextQuestion;
import com.android.hjalmar.quizapp.questions.RadioGroupMultipleChoiceQuestion;
import com.android.hjalmar.quizapp.questions.providers.CheckBoxMultipleChoiceQuestionsProvider;
import com.android.hjalmar.quizapp.questions.providers.FreeTextQuestionsProvider;
import com.android.hjalmar.quizapp.questions.providers.RadioGroupMultipleChoiceQuestionsProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public final class JSONUtils {

    // Suppresses default constructor, ensuring non-instantiability.
    private JSONUtils() {
    }

    private enum RadioGroupQuestionsJSONTags {
        RADIO_GROUP_QUESTIONS("radio_group_multiple_choice_questions"), SCORE("score"), TEXT("text"),
        CORRECT_ANSWER("correct_answer"), WRONG_ANSWERS("wrong_answers");

        private final String tag;

        RadioGroupQuestionsJSONTags(String tag) {
            this.tag = tag;
        }

        public String tag() {
            return tag;
        }
    }

    private enum CheckBoxQuestionsJSONTags {
        CHECK_BOX_QUESTIONS("check_box_multiple_choice_questions"), SCORE("score"), TEXT("text"),
        CORRECT_ANSWERS("correct_answers"), WRONG_ANSWERS("wrong_answers");

        private final String tag;

        CheckBoxQuestionsJSONTags(String tag) {
            this.tag = tag;
        }

        public String tag() {
            return tag;
        }
    }

    private enum FreeTextQuestionsJSONTags {
        FREE_TEXT_QUESTIONS("free_text_questions"), SCORE("score"), TEXT("text"), ANSWER("answer");

        private final String tag;

        FreeTextQuestionsJSONTags(String tag) {
            this.tag = tag;
        }

        public String tag() {
            return tag;
        }
    }

    /* Reads JSON file and stores it into a String */
    private static String loadJSONFromAsset(Context context, @RawRes int id) {
        String json = null;
        try (InputStream inputStream = context.getResources().openRawResource(id)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int dataByte = inputStream.read();
            while (dataByte != -1) {
                byteArrayOutputStream.write(dataByte);
                dataByte = inputStream.read();
            }
            json = byteArrayOutputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    /* Parses res/raw/radio_group_multiple_choice_questions.json and stores questions found in the corresponding provider */
    private static void parseRadioGroupQuestionsJSON(Context context, @RawRes int id) throws JSONException {
        String jsonString = loadJSONFromAsset(context, id);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jArray = jsonObject.getJSONArray(RadioGroupQuestionsJSONTags.RADIO_GROUP_QUESTIONS.tag());

        List<RadioGroupMultipleChoiceQuestion> radioGroupQuestions = new ArrayList<>();
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jArrayEl = jArray.getJSONObject(i);
            int score = jArrayEl.getInt(RadioGroupQuestionsJSONTags.SCORE.tag());
            String text = jArrayEl.getString(RadioGroupQuestionsJSONTags.TEXT.tag());
            String correctAnswer = jArrayEl.getString(RadioGroupQuestionsJSONTags.CORRECT_ANSWER.tag());
            List<String> wrongAnswers = new ArrayList<>();
            JSONArray wrongAnswersJArray = jArrayEl.getJSONArray(RadioGroupQuestionsJSONTags.WRONG_ANSWERS.tag());
            for (int j = 0; j < wrongAnswersJArray.length(); j++) {
                wrongAnswers.add(wrongAnswersJArray.getString(j));
            }

            RadioGroupMultipleChoiceQuestion radioGroupQuestion = new RadioGroupMultipleChoiceQuestion(score, text, correctAnswer, wrongAnswers);
            radioGroupQuestions.add(radioGroupQuestion);
        }
        RadioGroupMultipleChoiceQuestionsProvider.getInstance().addQuestions(radioGroupQuestions);
    }

    /* Parses res/raw/check_box_multiple_choice_questions.json and stores questions found in the corresponding provider */
    private static void parseCheckBoxQuestionsJSON(Context context, @RawRes int id) throws JSONException {
        String jsonString = loadJSONFromAsset(context, id);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jArray = jsonObject.getJSONArray(CheckBoxQuestionsJSONTags.CHECK_BOX_QUESTIONS.tag());

        List<CheckBoxMultipleChoiceQuestion> checkBoxQuestions = new ArrayList<>();
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jArrayEl = jArray.getJSONObject(i);
            int score = jArrayEl.getInt(CheckBoxQuestionsJSONTags.SCORE.tag());
            String text = jArrayEl.getString(CheckBoxQuestionsJSONTags.TEXT.tag());

            List<String> correctAnswers = new ArrayList<>();
            JSONArray correctAnswersJArray = jArrayEl.getJSONArray(CheckBoxQuestionsJSONTags.CORRECT_ANSWERS.tag());
            for (int j = 0; j < correctAnswersJArray.length(); j++) {
                correctAnswers.add(correctAnswersJArray.getString(j));
            }

            List<String> wrongAnswers = new ArrayList<>();
            JSONArray wrongAnswersJArray = jArrayEl.getJSONArray(CheckBoxQuestionsJSONTags.WRONG_ANSWERS.tag());
            for (int j = 0; j < wrongAnswersJArray.length(); j++) {
                wrongAnswers.add(wrongAnswersJArray.getString(j));
            }

            CheckBoxMultipleChoiceQuestion checkBoxQuestion = new CheckBoxMultipleChoiceQuestion(score, text, correctAnswers, wrongAnswers);
            checkBoxQuestions.add(checkBoxQuestion);
        }
        CheckBoxMultipleChoiceQuestionsProvider.getInstance().addQuestions(checkBoxQuestions);
    }

    /* Parses res/raw/free_text_questions.json and stores questions found in the corresponding provider */
    private static void parseFreeTextQuestionsJSON(Context context, @RawRes int id) throws JSONException {
        String jsonString = loadJSONFromAsset(context, id);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jArray = jsonObject.getJSONArray(FreeTextQuestionsJSONTags.FREE_TEXT_QUESTIONS.tag);

        List<FreeTextQuestion> freeTextQuestions = new ArrayList<>();
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject jArrayEl = jArray.getJSONObject(i);
            int score = jArrayEl.getInt(FreeTextQuestionsJSONTags.SCORE.tag());
            String text = jArrayEl.getString(FreeTextQuestionsJSONTags.TEXT.tag());
            String answer = jArrayEl.getString(FreeTextQuestionsJSONTags.ANSWER.tag());
            FreeTextQuestion freeTextQuestion = new FreeTextQuestion(score, text, answer);
            freeTextQuestions.add(freeTextQuestion);
        }
        FreeTextQuestionsProvider.getInstance().addQuestions(freeTextQuestions);
    }

    /**
     * Parses all of the JSONs stored in res/raw and stores the Java objects in the corresponding Providers.
     *
     * @param context The context from which the method is invoked
     */
    public static void parseQuestionsFromJSONs(Context context) {
        try {
            parseRadioGroupQuestionsJSON(context, R.raw.radio_group_multiple_choice_questions);
            parseCheckBoxQuestionsJSON(context, R.raw.check_box_multiple_choice_questions);
            parseFreeTextQuestionsJSON(context, R.raw.free_text_questions);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
