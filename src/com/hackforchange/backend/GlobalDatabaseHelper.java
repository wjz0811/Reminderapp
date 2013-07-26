package com.hackforchange.backend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hackforchange.models.reminders.Reminders;
/*
 * This is a database helper that is shared by the different model classes
 */
public class GlobalDatabaseHelper extends SQLiteOpenHelper {
  public static final String DATABASE_PATH = "/volunteer/volunteer.db";
  public static final String DATABASE_NAME = "volunteer.db";
  public static final int DATABASE_VERSION = 1;
  private static GlobalDatabaseHelper gHelper;

  private GlobalDatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public static GlobalDatabaseHelper getInstance(Context context){
    gHelper = new GlobalDatabaseHelper(context);
    return gHelper;
  }

  // Method is called during creation of the database
  @Override
  public void onCreate(SQLiteDatabase volunteer) {
    Reminders.onCreate(volunteer);
  }

  // Method is called during an upgrade of the database,
  @Override
  public void onUpgrade(SQLiteDatabase volunteer, int oldVersion,
                        int newVersion) {
    Reminders.onUpgrade(volunteer, oldVersion, newVersion);
  }
}
