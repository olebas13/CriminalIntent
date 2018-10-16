package com.olebas.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;

public class TimePickerActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_TIME = "com.olebas.criminalintent.crime_time";

    public static Intent newIntent(Context packageContext, Date date) {
        Intent intent = new Intent(packageContext, TimePickerActivity.class);
        intent.putExtra(EXTRA_CRIME_TIME, date);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        Date date = (Date) getIntent().getSerializableExtra(EXTRA_CRIME_TIME);
        return TimePickerFragment.newInstance(date);
    }
}
