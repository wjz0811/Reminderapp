package com.wjz.views;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.*;
import com.wjz.R;
import com.wjz.backend.reminders.RemindersDAO;
import com.wjz.models.reminders.Reminders;
import com.wjz.reminders.NotificationService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmActivity extends Activity {
    static final int DATE_DIALOG = 0, TIME_DIALOG = 1;
    private int mYear, mMonth, mDay, mHour, mMinute, dayOfWeek;
    private EditText mondayTime, tuesdayTime, wednesdayTime, thursdayTime, fridayTime, saturdayTime, sundayTime;
    private CheckBox mondayCheckbox, tuesdayCheckbox, wednesdayCheckbox, thursdayCheckbox, fridayCheckbox, saturdayCheckbox, sundayCheckbox;
    private Button submitButton;
    private SharedPreferences sharedPreferences;
    private List<Reminders> remindersList;
    // used because we reuse the same listener for both fields

    protected Reminders r;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarmactivitylayout);
    }

    @Override
    public void onResume() {
        super.onResume();

        final EditText medicineNameText = (EditText) findViewById(R.id.medicineName);

        // pre-populate the fields with the reminder details from the DB
        // the user can then change them if he so desires
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String medicineName = sharedPreferences.getString("medicineName", null);
        medicineNameText.setText(medicineName);

        mondayTime = (EditText) findViewById(R.id.mondayTime);
        tuesdayTime = (EditText) findViewById(R.id.tuesdayTime);
        wednesdayTime = (EditText) findViewById(R.id.wednesdayTime);
        thursdayTime = (EditText) findViewById(R.id.thursdayTime);
        fridayTime = (EditText) findViewById(R.id.fridayTime);
        saturdayTime = (EditText) findViewById(R.id.saturdayTime);
        sundayTime = (EditText) findViewById(R.id.sundayTime);

        mondayCheckbox = (CheckBox) findViewById(R.id.mondayCheckBox);
        tuesdayCheckbox = (CheckBox) findViewById(R.id.tuesdayCheckBox);
        wednesdayCheckbox = (CheckBox) findViewById(R.id.wednesdayCheckBox);
        thursdayCheckbox = (CheckBox) findViewById(R.id.thursdayCheckBox);
        fridayCheckbox = (CheckBox) findViewById(R.id.fridayCheckBox);
        saturdayCheckbox = (CheckBox) findViewById(R.id.saturdayCheckBox);
        sundayCheckbox = (CheckBox) findViewById(R.id.sundayCheckBox);

        RemindersDAO rDao = new RemindersDAO(getApplicationContext());
        remindersList = rDao.getAllReminders();
        final SimpleDateFormat parser = new SimpleDateFormat("hh:mm aaa");
        for (Reminders r : remindersList) {
            Date d = new Date(r.getRemindTime());
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(r.getRemindTime());
            switch (c.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.MONDAY:
                    mondayCheckbox.setChecked(true);
                    mondayTime.setText(parser.format(d));
                    mondayTime.setTag(r.getId()); // will be used to update the reminder
                    break;
                case Calendar.TUESDAY:
                    tuesdayCheckbox.setChecked(true);
                    tuesdayTime.setText(parser.format(d));
                    tuesdayTime.setTag(r.getId()); // will be used to update the reminder
                    break;
                case Calendar.WEDNESDAY:
                    wednesdayCheckbox.setChecked(true);
                    wednesdayTime.setText(parser.format(d));
                    wednesdayTime.setTag(r.getId()); // will be used to update the reminder
                    break;
                case Calendar.THURSDAY:
                    thursdayCheckbox.setChecked(true);
                    thursdayTime.setText(parser.format(d));
                    thursdayTime.setTag(r.getId()); // will be used to update the reminder
                    break;
                case Calendar.FRIDAY:
                    fridayCheckbox.setChecked(true);
                    fridayTime.setText(parser.format(d));
                    fridayTime.setTag(r.getId()); // will be used to update the reminder
                    break;
                case Calendar.SATURDAY:
                    saturdayCheckbox.setChecked(true);
                    saturdayTime.setText(parser.format(d));
                    saturdayTime.setTag(r.getId()); // will be used to update the reminder
                    break;
                case Calendar.SUNDAY:
                    sundayCheckbox.setChecked(true);
                    sundayTime.setText(parser.format(d));
                    sundayTime.setTag(r.getId()); // will be used to update the reminder
                    break;
            }
        }

        // entering the reminder time
        mondayTime.setFocusableInTouchMode(false); // do this so the date picker opens up on the very first selection of the text field
        // not doing this means the first click simply focuses the text field
        mondayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mondayCheckbox.isChecked()) {
                    dayOfWeek = 1;
                    Bundle bundle = new Bundle();
                    bundle.putString("timetodisplay", mondayTime.getText().toString());
                    showDialog(TIME_DIALOG, bundle);
                }
            }
        });

        tuesdayTime.setFocusableInTouchMode(false); // do this so the date picker opens up on the very first selection of the text field
        // not doing this means the first click simply focuses the text field
        tuesdayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuesdayCheckbox.isChecked()) {
                    dayOfWeek = 2;
                    Bundle bundle = new Bundle();
                    bundle.putString("timetodisplay", tuesdayTime.getText().toString());
                    showDialog(TIME_DIALOG, bundle);
                }
            }
        });

        wednesdayTime.setFocusableInTouchMode(false); // do this so the date picker opens up on the very first selection of the text field
        // not doing this means the first click simply focuses the text field
        wednesdayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wednesdayCheckbox.isChecked()) {
                    dayOfWeek = 3;
                    Bundle bundle = new Bundle();
                    bundle.putString("timetodisplay", wednesdayTime.getText().toString());
                    showDialog(TIME_DIALOG, bundle);
                }
            }
        });

        thursdayTime.setFocusableInTouchMode(false); // do this so the date picker opens up on the very first selection of the text field
        // not doing this means the first click simply focuses the text field
        thursdayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thursdayCheckbox.isChecked()) {
                    dayOfWeek = 4;
                    Bundle bundle = new Bundle();
                    bundle.putString("timetodisplay", thursdayTime.getText().toString());
                    showDialog(TIME_DIALOG, bundle);
                }
            }
        });

        fridayTime.setFocusableInTouchMode(false); // do this so the date picker opens up on the very first selection of the text field
        // not doing this means the first click simply focuses the text field
        fridayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fridayCheckbox.isChecked()) {
                    dayOfWeek = 5;
                    Bundle bundle = new Bundle();
                    bundle.putString("timetodisplay", fridayTime.getText().toString());
                    showDialog(TIME_DIALOG, bundle);
                }
            }
        });

        saturdayTime.setFocusableInTouchMode(false); // do this so the date picker opens up on the very first selection of the text field
        // not doing this means the first click simply focuses the text field
        saturdayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saturdayCheckbox.isChecked()) {
                    dayOfWeek = 6;
                    Bundle bundle = new Bundle();
                    bundle.putString("timetodisplay", saturdayTime.getText().toString());
                    showDialog(TIME_DIALOG, bundle);
                }
            }
        });

        sundayTime.setFocusableInTouchMode(false); // do this so the date picker opens up on the very first selection of the text field
        // not doing this means the first click simply focuses the text field
        sundayTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sundayCheckbox.isChecked()) {
                    dayOfWeek = 7;
                    Bundle bundle = new Bundle();
                    bundle.putString("timetodisplay", sundayTime.getText().toString());
                    showDialog(TIME_DIALOG, bundle);
                }
            }
        });

        mondayCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mondayCheckbox.isChecked())
                    mondayTime.setText("");
            }
        });

        tuesdayCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tuesdayCheckbox.isChecked())
                    tuesdayTime.setText("");
            }
        });

        wednesdayCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!wednesdayCheckbox.isChecked())
                    wednesdayTime.setText("");
            }
        });

        thursdayCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!thursdayCheckbox.isChecked())
                    thursdayTime.setText("");
            }
        });

        fridayCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fridayCheckbox.isChecked())
                    fridayTime.setText("");
            }
        });

        saturdayCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!saturdayCheckbox.isChecked())
                    saturdayTime.setText("");
            }
        });

        sundayCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sundayCheckbox.isChecked())
                    sundayTime.setText("");
            }
        });

        submitButton = (Button) findViewById(R.id.submitbutton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medicineNameText.getText().toString().isEmpty())
                    return;

                // save medicine name that the user entered
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("medicineName", medicineNameText.getText().toString());
                editor.commit();

                // save reminders for this alarm to the reminders table
                RemindersDAO rDao = new RemindersDAO(getApplicationContext());

                if (mondayCheckbox.isChecked()) {
                    if (mondayTime.getText() != null) {
                        try {
                            Date date = parser.parse(mondayTime.getText().toString());
                            // the date object we just constructed has only two fields that are of interest to us: the hour and the
                            // minute of the day at which the alarm should be set. The other fields are junk for us (they are initialized
                            // to some 1970 date. Hence, in the Calendar object that we construct below, we only extract the hour and
                            // minute from the date object.
                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, date.getHours());
                            c.set(Calendar.MINUTE, date.getMinutes());
                            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                            r = new Reminders();
                            r.setRemindTime(c.getTimeInMillis());

                            if (mondayTime.getTag() != null) { // updating an existing reminder
                                r.setId((Integer) mondayTime.getTag()); // retrieve the id of this reminder
                                rDao.updateReminders(r, getApplicationContext());
                            } else { // add a new reminder
                                rDao.addReminders(r, getApplicationContext());
                            }
                        } catch (ParseException e) {
                        }
                    }
                } else { // box was unchecked, remove any associated reminder for this day
                    if (mondayTime.getTag() != null) {
                        int reminderid = (Integer) mondayTime.getTag();
                        rDao.deleteReminders(reminderid, getApplicationContext());
                        AlarmActivity.deleteAlarmsForReminder(getApplicationContext(), reminderid);
                    }
                }

                if (tuesdayCheckbox.isChecked()) {
                    if (tuesdayTime.getText() != null) {
                        try {
                            Date date = parser.parse(tuesdayTime.getText().toString());
                            // the date object we just constructed has only two fields that are of interest to us: the hour and the
                            // minute of the day at which the alarm should be set. The other fields are junk for us (they are initialized
                            // to some 1970 date. Hence, in the Calendar object that we construct below, we only extract the hour and
                            // minute from the date object.
                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, date.getHours());
                            c.set(Calendar.MINUTE, date.getMinutes());
                            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                            r = new Reminders();
                            r.setRemindTime(c.getTimeInMillis());

                            if (tuesdayTime.getTag() != null) { // updating an existing reminder
                                r.setId((Integer) tuesdayTime.getTag()); // retrieve the id of this reminder
                                rDao.updateReminders(r, getApplicationContext());
                            } else { // add a new reminder
                                rDao.addReminders(r, getApplicationContext());
                            }
                        } catch (ParseException e) {
                        }
                    }
                }
                else { // box was unchecked, remove any associated reminder for this day
                    if (tuesdayTime.getTag() != null) {
                        int reminderid = (Integer) tuesdayTime.getTag();
                        rDao.deleteReminders(reminderid, getApplicationContext());
                        AlarmActivity.deleteAlarmsForReminder(getApplicationContext(), reminderid);
                    }
                }

                if (wednesdayCheckbox.isChecked()) {
                    if (wednesdayTime.getText() != null) {
                        try {
                            Date date = parser.parse(wednesdayTime.getText().toString());
                            // the date object we just constructed has only two fields that are of interest to us: the hour and the
                            // minute of the day at which the alarm should be set. The other fields are junk for us (they are initialized
                            // to some 1970 date. Hence, in the Calendar object that we construct below, we only extract the hour and
                            // minute from the date object.
                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, date.getHours());
                            c.set(Calendar.MINUTE, date.getMinutes());
                            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                            r = new Reminders();
                            r.setRemindTime(c.getTimeInMillis());

                            if (wednesdayTime.getTag() != null) { // updating an existing reminder
                                r.setId((Integer) wednesdayTime.getTag()); // retrieve the id of this reminder
                                rDao.updateReminders(r, getApplicationContext());
                            } else { // add a new reminder
                                rDao.addReminders(r, getApplicationContext());
                            }
                        } catch (ParseException e) {
                        }
                    }
                }
                else { // box was unchecked, remove any associated reminder for this day
                    if (wednesdayTime.getTag() != null) {
                        int reminderid = (Integer) wednesdayTime.getTag();
                        rDao.deleteReminders(reminderid, getApplicationContext());
                        AlarmActivity.deleteAlarmsForReminder(getApplicationContext(), reminderid);
                    }
                }

                if (thursdayCheckbox.isChecked()) {
                    if (thursdayTime.getText() != null) {
                        try {
                            Date date = parser.parse(thursdayTime.getText().toString());
                            // the date object we just constructed has only two fields that are of interest to us: the hour and the
                            // minute of the day at which the alarm should be set. The other fields are junk for us (they are initialized
                            // to some 1970 date. Hence, in the Calendar object that we construct below, we only extract the hour and
                            // minute from the date object.
                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, date.getHours());
                            c.set(Calendar.MINUTE, date.getMinutes());
                            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                            r = new Reminders();
                            r.setRemindTime(c.getTimeInMillis());

                            if (thursdayTime.getTag() != null) { // updating an existing reminder
                                r.setId((Integer) thursdayTime.getTag()); // retrieve the id of this reminder
                                rDao.updateReminders(r, getApplicationContext());
                            } else { // add a new reminder
                                rDao.addReminders(r, getApplicationContext());
                            }
                        } catch (ParseException e) {
                        }
                    }
                }
                else { // box was unchecked, remove any associated reminder for this day
                    if (thursdayTime.getTag() != null) {
                        int reminderid = (Integer) thursdayTime.getTag();
                        rDao.deleteReminders(reminderid, getApplicationContext());
                        AlarmActivity.deleteAlarmsForReminder(getApplicationContext(), reminderid);
                    }
                }

                if (fridayCheckbox.isChecked()) {
                    if (fridayTime.getText() != null) {
                        try {
                            Date date = parser.parse(fridayTime.getText().toString());
                            // the date object we just constructed has only two fields that are of interest to us: the hour and the
                            // minute of the day at which the alarm should be set. The other fields are junk for us (they are initialized
                            // to some 1970 date. Hence, in the Calendar object that we construct below, we only extract the hour and
                            // minute from the date object.
                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, date.getHours());
                            c.set(Calendar.MINUTE, date.getMinutes());
                            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                            r = new Reminders();
                            r.setRemindTime(c.getTimeInMillis());

                            if (fridayTime.getTag() != null) { // updating an existing reminder
                                r.setId((Integer) fridayTime.getTag()); // retrieve the id of this reminder
                                rDao.updateReminders(r, getApplicationContext());
                            } else { // add a new reminder
                                rDao.addReminders(r, getApplicationContext());
                            }
                        } catch (ParseException e) {
                        }
                    }
                }
                else { // box was unchecked, remove any associated reminder for this day
                    if (fridayTime.getTag() != null) {
                        int reminderid = (Integer) fridayTime.getTag();
                        rDao.deleteReminders(reminderid, getApplicationContext());
                        AlarmActivity.deleteAlarmsForReminder(getApplicationContext(), reminderid);
                    }
                }

                if (saturdayCheckbox.isChecked()) {
                    if (saturdayTime.getText() != null) {
                        try {
                            Date date = parser.parse(saturdayTime.getText().toString());
                            // the date object we just constructed has only two fields that are of interest to us: the hour and the
                            // minute of the day at which the alarm should be set. The other fields are junk for us (they are initialized
                            // to some 1970 date. Hence, in the Calendar object that we construct below, we only extract the hour and
                            // minute from the date object.
                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, date.getHours());
                            c.set(Calendar.MINUTE, date.getMinutes());
                            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                            r = new Reminders();
                            r.setRemindTime(c.getTimeInMillis());

                            if (saturdayTime.getTag() != null) { // updating an existing reminder
                                r.setId((Integer) saturdayTime.getTag()); // retrieve the id of this reminder
                                rDao.updateReminders(r, getApplicationContext());
                            } else { // add a new reminder
                                rDao.addReminders(r, getApplicationContext());
                            }
                        } catch (ParseException e) {
                        }
                    }
                }
                else { // box was unchecked, remove any associated reminder for this day
                    if (saturdayTime.getTag() != null) {
                        int reminderid = (Integer) saturdayTime.getTag();
                        rDao.deleteReminders(reminderid, getApplicationContext());
                        AlarmActivity.deleteAlarmsForReminder(getApplicationContext(), reminderid);
                    }
                }

                if (sundayCheckbox.isChecked()) {
                    if (sundayTime.getText() != null) {
                        try {
                            Date date = parser.parse(sundayTime.getText().toString());
                            // the date object we just constructed has only two fields that are of interest to us: the hour and the
                            // minute of the day at which the alarm should be set. The other fields are junk for us (they are initialized
                            // to some 1970 date. Hence, in the Calendar object that we construct below, we only extract the hour and
                            // minute from the date object.
                            Calendar c = Calendar.getInstance();
                            c.set(Calendar.HOUR_OF_DAY, date.getHours());
                            c.set(Calendar.MINUTE, date.getMinutes());
                            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                            r = new Reminders();
                            r.setRemindTime(c.getTimeInMillis());

                            if (sundayTime.getTag() != null) { // updating an existing reminder
                                r.setId((Integer) sundayTime.getTag()); // retrieve the id of this reminder
                                rDao.updateReminders(r, getApplicationContext());
                            } else { // add a new reminder
                                rDao.addReminders(r, getApplicationContext());
                            }
                        } catch (ParseException e) {
                        }
                    }
                }
                else { // box was unchecked, remove any associated reminder for this day
                    if (sundayTime.getTag() != null) {
                        int reminderid = (Integer) sundayTime.getTag();
                        rDao.deleteReminders(reminderid, getApplicationContext());
                        AlarmActivity.deleteAlarmsForReminder(getApplicationContext(), reminderid);
                    }
                }

                finish();
            }
        });
    }

    protected TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    mHour = hourOfDay;
                    mMinute = minute;
                    String timeToDisplay = String.format("%02d:%02d %s", ((mHour / 12) > 0 ? ((mHour == 12) ? 12 : (mHour - 12)) : ((mHour == 0) ? 12 : mHour)), mMinute, ((mHour / 12) > 0 ? "PM" : "AM"));
                    switch (dayOfWeek) {
                        case 1:
                            mondayTime.setText(timeToDisplay); //sets the chosen date in the text view
                            break;
                        case 2:
                            tuesdayTime.setText(timeToDisplay); //sets the chosen date in the text view
                            break;
                        case 3:
                            wednesdayTime.setText(timeToDisplay); //sets the chosen date in the text view
                            break;
                        case 4:
                            thursdayTime.setText(timeToDisplay); //sets the chosen date in the text view
                            break;
                        case 5:
                            fridayTime.setText(timeToDisplay); //sets the chosen date in the text view
                            break;
                        case 6:
                            saturdayTime.setText(timeToDisplay); //sets the chosen date in the text view
                            break;
                        case 7:
                            sundayTime.setText(timeToDisplay); //sets the chosen date in the text view
                            break;
                    }
                    removeDialog(TIME_DIALOG); // remember to remove the dialog or onCreateDialog will NOT be called again! We need it to be called afresh
                    // each time the reminder time field is clicked because we prepopulate the date picker with different
                    // times in EditProjectActivity.java's overriden onCreateDialog
                    // http://stackoverflow.com/questions/2222648/change-the-contents-of-an-android-dialog-box-after-creation
                }
            };

    // the callback received when the user "sets" the date in the dialog
    protected DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    removeDialog(DATE_DIALOG); // remember to remove the dialog or onCreateDialog will NOT be called again! We need it to be called afresh
                    // each time either startDate or endDate is clicked because we prepopulate the date picker with different
                    // dates for startDate and endDate in EditProjectActivity.java's overriden onCreateDialog
                    // http://stackoverflow.com/questions/2222648/change-the-contents-of-an-android-dialog-box-after-creation
                }
            };

    @Override
    protected Dialog onCreateDialog(int id, Bundle bundle) {
        switch (id) {
            case DATE_DIALOG:
                // get the current date
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            case TIME_DIALOG:
                // get the prepopulated date
                DateFormat parser = new SimpleDateFormat("hh:mm aaa");
                Date date;
                c = Calendar.getInstance();
                String timeToDisplay = bundle.getString("timetodisplay");
                try {
                    date = parser.parse(timeToDisplay);
                    c.setTime(date);
                } catch (ParseException e) {
                } finally {
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);
                    return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, false);
                }
        }
        return null;
    }

    // remove all the alarms associated with a reminder
    // also used by AllActivitiesActivity and DisplayActivitiesActivity
    public static void deleteAlarmsForReminder(Context context, int reminderid) {
        Intent notifIntent = new Intent(context, NotificationService.class);
        notifIntent.putExtra("reminderid", reminderid); // not necessary for alarmManager.cancel to match pending intents but leaving it here anyway
        notifIntent.setAction(Intent.ACTION_VIEW); // unpredictable android crap again. without an action, the extras will NOT be sent!!
        PendingIntent pendingIntent = PendingIntent.getService(context, reminderid, notifIntent, PendingIntent.FLAG_UPDATE_CURRENT); // remember to distinguish between pendingintents using Reminders.id as the request code
        pendingIntent.cancel();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

}