package com.wjz.models.reminders;

import android.database.sqlite.SQLiteDatabase;

/**
 * ********************************************************************************************************************
 * Models the representation of activity reminders
 * ********************************************************************************************************************
 */
public class Reminders {

    // Instance properties
    private int id;
    private long remindTime;
    private int _interval;

    // Database table
    public static final String REMINDERS_TABLE = "reminders";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_INTERVAL = "_interval"; // foreign key referencing Activity.id
    public static final String COLUMN_UPDATED = "updated"; //when this reminder was last modified
    public static final String COLUMN_REMINDTIME = "remindtime";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table if not exists "
            + REMINDERS_TABLE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_INTERVAL + " integer not null, "
            + COLUMN_UPDATED + " integer not null default (strftime('%s','now')), "
            + COLUMN_REMINDTIME + " integer not null"

            //foreign key constraint. Make sure to delete the reminders if the activity that owns it is deleted
            + ");";

    // used to create the table
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // used to upgrade the table
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        database.execSQL("drop table if exists " + REMINDERS_TABLE);
        onCreate(database);
    }

    // Getters and Setters follow
    public int getInterval() {
        return _interval;
    }

    public void setInterval(int _interval) {
        this._interval = _interval;
    }

    public long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(long remindTime) {
        this.remindTime = remindTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reminders() {
        super();
    }


}
