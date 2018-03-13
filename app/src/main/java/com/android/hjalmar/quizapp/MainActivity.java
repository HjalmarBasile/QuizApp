package com.android.hjalmar.quizapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.hjalmar.quizapp.questions.CheckBoxMultipleChoiceQuestion;
import com.android.hjalmar.quizapp.questions.FreeTextQuestion;
import com.android.hjalmar.quizapp.questions.RadioGroupMultipleChoiceQuestion;
import com.android.hjalmar.quizapp.questions.abstraction.AbstractQuestion;
import com.android.hjalmar.quizapp.questions.providers.CheckBoxMultipleChoiceQuestionsProvider;
import com.android.hjalmar.quizapp.questions.providers.FreeTextQuestionsProvider;
import com.android.hjalmar.quizapp.questions.providers.RadioGroupMultipleChoiceQuestionsProvider;
import com.android.hjalmar.quizapp.utils.JSONUtils;
import com.android.hjalmar.quizapp.utils.RandomPermutationUtils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    /* Number of selected questions for every possible kind */
    private final int NUMBER_OF_SELECTED_RADIO_GROUP_QUESTIONS = 4;
    private final int NUMBER_OF_SELECTED_CHECK_BOX_QUESTIONS = 3;
    private final int NUMBER_OF_SELECTED_FREE_TEXT_QUESTIONS = 2;
    private final int TOTAL_NUMBER_OF_QUESTIONS = NUMBER_OF_SELECTED_RADIO_GROUP_QUESTIONS + NUMBER_OF_SELECTED_CHECK_BOX_QUESTIONS + NUMBER_OF_SELECTED_FREE_TEXT_QUESTIONS;

    /* Lists of selected questions for every possible kind */
    private static List<RadioGroupMultipleChoiceQuestion> selectedRadioGroupQuestions;
    private static List<CheckBoxMultipleChoiceQuestion> selectedCheckBoxQuestions;
    private static List<FreeTextQuestion> selectedFreeTextQuestions;
    private static List<AbstractQuestion> allSelectedQuestions;

    /* Variables which store the current user status */
    private static int currentQuestionIndex = 0;
    private static int currentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* If it's first OnCreate call */
        if (savedInstanceState == null) {
            JSONUtils.parseQuestionsFromJSONs(this);
            selectRandomQuestions();
        }
        displayCurrentStatus();
    }

    /**
     * Selects random questions from the question providers.
     */
    private void selectRandomQuestions() {
        selectedRadioGroupQuestions = RadioGroupMultipleChoiceQuestionsProvider.getInstance().getRandomQuestions(NUMBER_OF_SELECTED_RADIO_GROUP_QUESTIONS);
        selectedCheckBoxQuestions = CheckBoxMultipleChoiceQuestionsProvider.getInstance().getRandomQuestions(NUMBER_OF_SELECTED_CHECK_BOX_QUESTIONS);
        selectedFreeTextQuestions = FreeTextQuestionsProvider.getInstance().getRandomQuestions(NUMBER_OF_SELECTED_FREE_TEXT_QUESTIONS);

        createRandomQuestionsList();
    }

    /**
     * Takes all of the selected questions and shuffles their order.
     */
    private void createRandomQuestionsList() {
        allSelectedQuestions = new ArrayList<>();
        allSelectedQuestions.addAll(selectedRadioGroupQuestions);
        allSelectedQuestions.addAll(selectedCheckBoxQuestions);
        allSelectedQuestions.addAll(selectedFreeTextQuestions);
        allSelectedQuestions = RandomPermutationUtils.getRandomPermutation(allSelectedQuestions);
    }

    /**
     * Displays current score, progress and question.
     */
    private void displayCurrentStatus() {
        displayCurrentScore();
        displayCurrentProgress();
        displayCurrentQuestion();
    }

    /**
     * Displays the current score.
     */
    private void displayCurrentScore() {
        TextView scoreTextView = findViewById(R.id.score);
        scoreTextView.setText(getString(R.string.score, currentScore));
    }

    /**
     * Displays the current progress.
     */
    private void displayCurrentProgress() {
        int currentProgress = (currentQuestionIndex < TOTAL_NUMBER_OF_QUESTIONS) ? currentQuestionIndex + 1 : TOTAL_NUMBER_OF_QUESTIONS;
        TextView progressTextView = findViewById(R.id.progress);
        progressTextView.setText(getString(R.string.progress, currentProgress, TOTAL_NUMBER_OF_QUESTIONS));
    }

    /**
     * Displays the current question.
     */
    private void displayCurrentQuestion() {
        int index = (currentQuestionIndex < TOTAL_NUMBER_OF_QUESTIONS) ? currentQuestionIndex : TOTAL_NUMBER_OF_QUESTIONS - 1;
        AbstractQuestion currentQuestion = allSelectedQuestions.get(index);
        if (currentQuestion instanceof RadioGroupMultipleChoiceQuestion) {
            fillRadioGroup((RadioGroupMultipleChoiceQuestion) currentQuestion);
        } else if (currentQuestion instanceof CheckBoxMultipleChoiceQuestion) {
            fillCheckBoxGroup((CheckBoxMultipleChoiceQuestion) currentQuestion);
        } else if (currentQuestion instanceof FreeTextQuestion) {
            fillFreeTextLayout((FreeTextQuestion) currentQuestion);
        } else {
            throw new RuntimeException("Unexpected question type: " + currentQuestion.getClass().toString());
        }
    }

    /**
     * Fills the RadioGroup of RadioButton(s) in the activity with the current question information and activates it.
     *
     * @param radioGroupQuestion The question to fill the RadioGroup with
     */
    private void fillRadioGroup(RadioGroupMultipleChoiceQuestion radioGroupQuestion) {
        TextView radioQuestionTextView = findViewById(R.id.radio_question_text);
        radioQuestionTextView.setText(radioGroupQuestion.getText());

        List<RadioButton> radioQuestionButtons = findRadioQuestionButtons();
        List<String> radioGroupAnswers = radioGroupQuestion.getPermutatedAnswers();
        if (radioGroupAnswers.size() != radioQuestionButtons.size()) {
            throw new InvalidParameterException("Answers number must match the number of RadioButtons in the activity");
        }
        for (int i = 0; i < radioQuestionButtons.size(); i++) {
            RadioButton radioButton = radioQuestionButtons.get(i);
            radioButton.setText(radioGroupAnswers.get(i));
        }

        RadioGroup radioGroupApp = findViewById(R.id.radio_group_app);
        radioGroupApp.setVisibility(View.VISIBLE);
    }

    /**
     * Finds the RadioButton(s) of the activity.
     *
     * @return The list of RadioButton(s) in the activity
     */
    private List<RadioButton> findRadioQuestionButtons() {
        List<RadioButton> radioQuestionButtons = new ArrayList<>();
        radioQuestionButtons.add((RadioButton) findViewById(R.id.radio_question_option_a));
        radioQuestionButtons.add((RadioButton) findViewById(R.id.radio_question_option_b));
        radioQuestionButtons.add((RadioButton) findViewById(R.id.radio_question_option_c));
        radioQuestionButtons.add((RadioButton) findViewById(R.id.radio_question_option_d));
        return radioQuestionButtons;
    }

    /**
     * Fills the RadioGroup of CheckBox(es) in the activity with the current question information and activates it.
     *
     * @param checkBoxQuestion The question to fill the RadioGroup with
     */
    private void fillCheckBoxGroup(CheckBoxMultipleChoiceQuestion checkBoxQuestion) {
        TextView checkBoxQuestionTextView = findViewById(R.id.check_box_question_text);
        checkBoxQuestionTextView.setText(checkBoxQuestion.getText());

        List<CheckBox> questionCheckBoxes = findQuestionCheckBoxes();
        List<String> checkBoxQuestionAnswers = checkBoxQuestion.getPermutatedAnswers();
        if (checkBoxQuestionAnswers.size() != questionCheckBoxes.size()) {
            throw new InvalidParameterException("Answers number must match the number of CheckBox-es in the activity");
        }
        for (int i = 0; i < questionCheckBoxes.size(); i++) {
            CheckBox checkBox = questionCheckBoxes.get(i);
            checkBox.setText(checkBoxQuestionAnswers.get(i));
        }

        RadioGroup checkBoxGroupApp = findViewById(R.id.check_box_group_app);
        checkBoxGroupApp.setVisibility(View.VISIBLE);
    }

    /**
     * Finds the CheckBox(es) of the activity.
     *
     * @return The list of CheckBox(es) in the activity
     */
    private List<CheckBox> findQuestionCheckBoxes() {
        List<CheckBox> questionCheckBoxes = new ArrayList<>();
        questionCheckBoxes.add((CheckBox) findViewById(R.id.check_box_question_option_a));
        questionCheckBoxes.add((CheckBox) findViewById(R.id.check_box_question_option_b));
        questionCheckBoxes.add((CheckBox) findViewById(R.id.check_box_question_option_c));
        questionCheckBoxes.add((CheckBox) findViewById(R.id.check_box_question_option_d));
        return questionCheckBoxes;
    }

    /**
     * Fills the free text linear layout with the current question information and activates it.
     *
     * @param freeTextQuestion The question to fill the LinearLayout with
     */
    private void fillFreeTextLayout(FreeTextQuestion freeTextQuestion) {
        TextView freeTextTextView = findViewById(R.id.free_text_text_view);
        freeTextTextView.setText(freeTextQuestion.getText());

        LinearLayout layout = findViewById(R.id.free_text_linear_layout);
        layout.setVisibility(View.VISIBLE);
    }

    /**
     * Submits the current question answer and updates the status of the app, invoked on button click.
     *
     * @param view The view that was clicked
     */
    public void submit(View view) {
        if (currentQuestionIndex < TOTAL_NUMBER_OF_QUESTIONS) {
            currentScore += evaluateAnswer();
            currentQuestionIndex++;
            if (currentQuestionIndex != TOTAL_NUMBER_OF_QUESTIONS) {
                setContentView(R.layout.activity_main);
                displayCurrentStatus();
            }
        }

        if (currentQuestionIndex == TOTAL_NUMBER_OF_QUESTIONS) {
            displayCurrentScore();
            displayFinalScoreToast();
        }
    }

    /**
     * Checks if the user's answer is correct.
     *
     * @return The score for the current question submission
     */
    private int evaluateAnswer() {
        AbstractQuestion currentQuestion = allSelectedQuestions.get(currentQuestionIndex);
        if (currentQuestion instanceof RadioGroupMultipleChoiceQuestion) {
            RadioGroup radioGroupApp = findViewById(R.id.radio_group_app);
            @IdRes int checkedId = radioGroupApp.getCheckedRadioButtonId();
            /* If the user didn't select any answer */
            if (checkedId < 0) {
                return 0;
            }
            RadioButton checkedRadioButton = findViewById(checkedId);
            String userAnswer = checkedRadioButton.getText().toString();
            return currentQuestion.evaluateAnswer(Collections.singletonList(userAnswer));
        } else if (currentQuestion instanceof CheckBoxMultipleChoiceQuestion) {
            List<String> userAnswers = getSelectedCheckBoxTexts();
            return currentQuestion.evaluateAnswer(userAnswers);
        } else if (currentQuestion instanceof FreeTextQuestion) {
            EditText freeTextEditText = findViewById(R.id.free_text_edit_text);
            String userAnswer = freeTextEditText.getText().toString();
            return currentQuestion.evaluateAnswer(Collections.singletonList(userAnswer));
        } else {
            throw new RuntimeException("Unexpected question type: " + currentQuestion.getClass().toString());
        }
    }

    /**
     * Finds the answers which the user submitted in case of a CheckBox multiple choice question.
     *
     * @return The list of answers selected by the user
     */
    List<String> getSelectedCheckBoxTexts() {
        List<String> selectedCheckBoxTexts = new ArrayList<>();

        List<CheckBox> questionCheckBoxes = findQuestionCheckBoxes();
        for (CheckBox checkBox : questionCheckBoxes) {
            if (checkBox.isChecked()) {
                selectedCheckBoxTexts.add(checkBox.getText().toString());
            }
        }

        return selectedCheckBoxTexts;
    }

    /**
     * Displays the final user score as a Toast.
     */
    private void displayFinalScoreToast() {
        int totalPossibleScore = findTotalPossibleScore();
        String percentage = getPercentageString(currentScore, totalPossibleScore);

        String text = getString(R.string.toast_text, currentScore, totalPossibleScore, percentage);
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * Finds the sum of all the selected questions scores.
     *
     * @return The total possible score
     */
    private int findTotalPossibleScore() {
        int total = 0;
        for (AbstractQuestion question : allSelectedQuestions) {
            total += question.getScore();
        }
        return total;
    }

    /**
     * Get a percentage formatted string.
     *
     * @param scored Total points scored
     * @param total  Total possible score
     * @return percentage string
     */
    private String getPercentageString(int scored, int total) {
        float percentage = 100.0f * ((float) scored) / ((float) total);
        return String.format(Locale.getDefault(), "%.2f%s", percentage, "%");
    }

}
