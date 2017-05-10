package com.imp.beezy.keepingtabs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

/**
 * Created by bpeng on 2017-05-08.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Intent intent = new Intent();
        intent.putExtra("year", year);
        intent.putExtra("month", ++month);
        intent.putExtra("day", dayOfMonth);

        getTargetFragment().onActivityResult(newGoalFragment.REQ_DATE, RESULT_OK, intent);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
}
