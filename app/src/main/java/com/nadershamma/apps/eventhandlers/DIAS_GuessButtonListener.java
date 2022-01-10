package com.nadershamma.apps.eventhandlers;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.nadershamma.apps.androidfunwithflags.DIAS_MainActivityFragment;
import com.nadershamma.apps.androidfunwithflags.R;
import com.nadershamma.apps.androidfunwithflags.DIAS_ResultsDialogFragment;
import com.nadershamma.apps.lifecyclehelpers.DIAS_QuizViewModel;

public class DIAS_GuessButtonListener implements OnClickListener {
    private DIAS_MainActivityFragment DIASMainActivityFragment;
    private Handler handler;

    public DIAS_GuessButtonListener(DIAS_MainActivityFragment DIASMainActivityFragment) {
        this.DIASMainActivityFragment = DIASMainActivityFragment;
        this.handler = new Handler();
    }

    @Override
    public void onClick(View v) {
        Button guessButton = ((Button) v);
        String guess = guessButton.getText().toString();
        String answer = this.DIASMainActivityFragment.getQuizViewModel().getCorrectCountryName();
        this.DIASMainActivityFragment.getQuizViewModel().setTotalGuesses(1);

        if (guess.equals(answer)) {
            this.DIASMainActivityFragment.getQuizViewModel().setCorrectAnswers(1);
            this.DIASMainActivityFragment.getAnswerTextView().setText(answer + "!");
            this.DIASMainActivityFragment.getAnswerTextView().setTextColor(
                    this.DIASMainActivityFragment.getResources().getColor(R.color.correct_answer));

            this.DIASMainActivityFragment.disableButtons();

            if (this.DIASMainActivityFragment.getQuizViewModel().getCorrectAnswers()
                    == DIAS_QuizViewModel.getFlagsInQuiz()) {
                DIAS_ResultsDialogFragment quizResults = new DIAS_ResultsDialogFragment();
                quizResults.setCancelable(false);
                try {
                    quizResults.show(this.DIASMainActivityFragment.getChildFragmentManager(), "Quiz Results");
                } catch (NullPointerException e) {
                    Log.e(DIAS_QuizViewModel.getTag(),
                            "GuessButtonListener: this.mainActivityFragment.getFragmentManager() " +
                                    "returned null",
                            e);
                }
            } else {
                this.handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                DIASMainActivityFragment.animate(true);
                            }
                        }, 2000);
            }
        } else {
            this.DIASMainActivityFragment.incorrectAnswerAnimation();
            guessButton.setEnabled(false);
        }
    }
}
