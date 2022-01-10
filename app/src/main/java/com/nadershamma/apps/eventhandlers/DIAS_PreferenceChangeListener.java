package com.nadershamma.apps.eventhandlers;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.widget.Toast;

import com.nadershamma.apps.androidfunwithflags.DIAS_MainActivity;
import com.nadershamma.apps.androidfunwithflags.R;

import java.util.Set;

public class DIAS_PreferenceChangeListener implements OnSharedPreferenceChangeListener {
    private DIAS_MainActivity DIASMainActivity;

    public DIAS_PreferenceChangeListener(DIAS_MainActivity DIASMainActivity) {
        this.DIASMainActivity = DIASMainActivity;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.DIASMainActivity.setPreferencesChanged(true);

        if (key.equals(this.DIASMainActivity.getREGIONS())) {
            this.DIASMainActivity.getQuizViewModel().setGuessRows(sharedPreferences.getString(
                    DIAS_MainActivity.CHOICES, null));
            this.DIASMainActivity.getQuizFragment().resetQuiz();
        } else if (key.equals(this.DIASMainActivity.getCHOICES())) {
            Set<String> regions = sharedPreferences.getStringSet(this.DIASMainActivity.getREGIONS(),
                    null);
            if (regions != null && regions.size() > 0) {
                this.DIASMainActivity.getQuizViewModel().setRegionsSet(regions);
                this.DIASMainActivity.getQuizFragment().resetQuiz();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                regions.add(this.DIASMainActivity.getString(R.string.default_region));
                editor.putStringSet(this.DIASMainActivity.getREGIONS(), regions);
                editor.apply();
                Toast.makeText(this.DIASMainActivity, R.string.default_region_message,
                        Toast.LENGTH_LONG).show();
            }
        }

        Toast.makeText(this.DIASMainActivity, R.string.restarting_quiz,
                Toast.LENGTH_SHORT).show();
    }
}
