package com.nadershamma.apps.androidfunwithflags;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import com.nadershamma.apps.lifecyclehelpers.DIAS_QuizViewModel;

public class DIAS_ResultsDialogFragment extends DialogFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final DIAS_QuizViewModel DIASQuizViewModel = ViewModelProviders.of(getActivity()).get(DIAS_QuizViewModel.class);
        int totalGuesses = DIASQuizViewModel.getTotalGuesses();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(
                getString(R.string.results, totalGuesses, (1000 / (double) totalGuesses)));

        builder.setPositiveButton(R.string.reset_quiz, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    DIAS_MainActivityFragment quizFragment = (DIAS_MainActivityFragment) getParentFragment();
                    try{
                        quizFragment.resetQuiz();
                    }catch (Exception e){
                        Log.e(DIASQuizViewModel.getTag(),"Unable to call resetQuiz()", e);
                    }
                }
                catch (Exception e){
                    Log.e(DIASQuizViewModel.getTag(),"Unable to get ActivityMainFragment", e);
                }
            }
        });
        return builder.create();
    }
}
