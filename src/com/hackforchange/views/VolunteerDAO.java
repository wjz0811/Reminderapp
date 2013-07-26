package com.hackforchange.views;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hackforchange.backend.GlobalDatabaseHelper;
import java.util.ArrayList;

/*
 * DAO object to update/delete/add activities
 */
public class VolunteerDAO {
  private GlobalDatabaseHelper opener;
  private SQLiteDatabase readDatabase;
  private SQLiteDatabase writeDatabase;

  public VolunteerDAO(Context context) {
    this.opener = GlobalDatabaseHelper.getInstance(context);
    this.readDatabase = opener.getReadableDatabase();
    this.writeDatabase = opener.getWritableDatabase();
    this.writeDatabase.execSQL("PRAGMA foreign_keys=ON"); // make sure to turn foreign keys constraints on
    closeDB();
  }

  private void openDB(){
    if(!readDatabase.isOpen()){
      readDatabase = opener.getReadableDatabase();
    }
    if(!writeDatabase.isOpen()){
      writeDatabase = opener.getWritableDatabase();
    }
  }

  private void closeDB(){
    if(readDatabase.isOpen()){
      readDatabase.close();
    }
    if(writeDatabase.isOpen()){
      writeDatabase.close();
    }
  }

  public ArrayList<Volunteer> getAllVolunteerForProjectId(int projectid) {
    openDB();
    ArrayList<Volunteer> output = null;
    String[] columnsToRead = new String[8];
    columnsToRead[0] = Volunteer.COLUMN_ID;
    columnsToRead[1] = Volunteer.COLUMN_NAME;
    columnsToRead[2] = Volunteer.COLUMN_COUNTRY;
    columnsToRead[3] = Volunteer.COLUMN_TESTIMONY;
    Cursor returnData = readDatabase.query(Volunteer.VOLUNTEER_TABLE, columnsToRead, null, null, null, null, null);
    output = extractVolunteer(returnData);
    closeDB();
    return output;
  }

  private ArrayList<Volunteer> extractVolunteer(Cursor returnData) {
    // The output ArrayList is initialized
    ArrayList<Volunteer> output = new ArrayList<Volunteer>();
    // Move the counter to the first item in the return data
    returnData.moveToFirst();
    int count = 0;
    // While there are still values in the return data
    while (!returnData.isAfterLast()) {
      // Add the new Activities to the ArrayList
      Volunteer a = new Volunteer();
      a.setId(Integer.parseInt(returnData.getString(4)));
      output.add(count, a);
      a.setName(returnData.getString(0));
      a.setCountry(returnData.getString(1));
      a.setTestimony(returnData.getString(2));
      
      // Advance the Cursor
      returnData.moveToNext();
      // Advance the counter
      count++;
    }
    // Return the ArrayList
    return output;
  }

  public Volunteer getVolunteerWithId(int id) {
    openDB();
    String[] columnsToRead = new String[9];
    columnsToRead[0] = Volunteer.COLUMN_ID;
    columnsToRead[1] = Volunteer.COLUMN_NAME;
    columnsToRead[2] = Volunteer.COLUMN_COUNTRY;
    columnsToRead[3] = Volunteer.COLUMN_TESTIMONY;   
    String whereClause = Volunteer.COLUMN_ID + '=' + id;
    Cursor returnData = readDatabase.query(Volunteer.VOLUNTEER_TABLE, columnsToRead,
      whereClause, null, null, null, null);
    returnData.moveToFirst();
    Volunteer a = new Volunteer();
    a.setName(returnData.getString(0));
    a.setCountry(returnData.getString(1));
    a.setTestimony(returnData.getString(2));
    closeDB();
    // Return the constructed Volunteer object
    return a;
  }

  public int addVolunteer(Volunteer volunteer) {
    openDB();
    ContentValues newValue = new ContentValues(8);
    newValue.put(Volunteer.COLUMN_NAME, volunteer.getName());
    newValue.put(Volunteer.COLUMN_COUNTRY, volunteer.getCountry());
    newValue.put(Volunteer.COLUMN_TESTIMONY, volunteer.getTestimony());

    // Insert the item into the database
    writeDatabase.insert(Volunteer.VOLUNTEER_TABLE, null, newValue);

    // return the id of the activity just created. This will be used as the foreign key for the reminders table
    Cursor returnData = readDatabase.rawQuery("select seq from sqlite_sequence where name=?", new String[]{Volunteer.VOLUNTEER_TABLE});
    int retVal = -1;
    if (returnData != null && returnData.moveToFirst()){
      retVal = returnData.getInt(0);
      closeDB();
    }
    return retVal;
  }

  public void updateVolunteer(Volunteer volunteer) {
    openDB();
    ContentValues newValue = new ContentValues(8);
    newValue.put(Volunteer.COLUMN_NAME, volunteer.getName());
    newValue.put(Volunteer.COLUMN_COUNTRY, volunteer.getCountry());
    newValue.put(Volunteer.COLUMN_TESTIMONY, volunteer.getTestimony());
    String whereClause = Volunteer.COLUMN_ID + '=' + volunteer.getId();
    // Update the item into the database
    writeDatabase.update(Volunteer.VOLUNTEER_TABLE, newValue, whereClause, null);
    closeDB();
  }

  public int deleteVolunteer(int id) {
    openDB();
    String whereClause = Volunteer.COLUMN_ID + '=' + id;
    // Return the total number of rows removed
    int numItemsDeleted = writeDatabase.delete(Volunteer.VOLUNTEER_TABLE, whereClause, null);
    closeDB();
    return numItemsDeleted;
  }
}
