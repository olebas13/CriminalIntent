package com.olebas.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment {

    public final static String EXTRA_TIME = "com.olebas.criminalintent.time";

    private final static String ARG_DATE = "date";

    private TimePicker mTimePicker;
    private Calendar mCalendar;

    private Button mTimePickerOkButton;

    public static TimePickerFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        View v = inflater.inflate(R.layout.dialog_time, container, false);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }

        mTimePickerOkButton = (Button) v.findViewById(R.id.dialog_time_ok_button);
        mTimePickerOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour, minute;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = mTimePicker.getHour();
                    minute = mTimePicker.getMinute();
                } else {
                    hour = mTimePicker.getCurrentHour();
                    minute = mTimePicker.getCurrentMinute();
                }
                mCalendar.set(Calendar.HOUR_OF_DAY, hour);
                mCalendar.set(Calendar.MINUTE, minute);

                sendResult(Activity.RESULT_OK, mCalendar.getTime());
            }
        });
        return v;
    }

    private void sendResult(int resultCode, Date date) {
        Intent data = new Intent();
        data.putExtra(EXTRA_TIME, date);

        if (getTargetFragment() == null) {
            Activity hostingActivity = getActivity();
            hostingActivity.setResult(resultCode, data);
            hostingActivity.finish();
        } else {
            dismiss();
            getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, data);
        }
    }
}
