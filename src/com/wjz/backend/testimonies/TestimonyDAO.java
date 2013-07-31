package com.wjz.backend.testimonies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.wjz.backend.GlobalDatabaseHelper;
import com.wjz.models.testimonies.Testimony;

import java.util.ArrayList;

/*
 * DAO object to update/delete/add activities
 */
public class TestimonyDAO {
    private GlobalDatabaseHelper opener;
    private SQLiteDatabase readDatabase;
    private SQLiteDatabase writeDatabase;

    public TestimonyDAO(Context context) {
        this.opener = GlobalDatabaseHelper.getInstance(context);
        this.readDatabase = opener.getReadableDatabase();
        this.writeDatabase = opener.getWritableDatabase();
        this.writeDatabase.execSQL("PRAGMA foreign_keys=ON"); // make sure to turn foreign keys constraints on
        closeDB();
    }

    private void openDB() {
        if (!readDatabase.isOpen()) {
            readDatabase = opener.getReadableDatabase();
        }
    }

    private void closeDB() {
        if (readDatabase.isOpen()) {
            readDatabase.close();
        }
    }

    public ArrayList<Testimony> getAllTestimonies() {
        openDB();
        ArrayList<Testimony> output = null;
        String[] columnsToRead = new String[5];
        columnsToRead[0] = Testimony.COLUMN_ID;
        columnsToRead[1] = Testimony.COLUMN_NAME;
        columnsToRead[2] = Testimony.COLUMN_COUNTRY;
        columnsToRead[3] = Testimony.COLUMN_TESTIMONY;
        columnsToRead[4] = Testimony.COLUMN_IMAGEPATH;
        Cursor returnData = readDatabase.query(Testimony.TESTIMONIES_TABLE, columnsToRead, null, null, null, null, null);
        output = extractTestimonies(returnData);
        Log.e("burra", output.size()+"");
        closeDB();
        return output;
    }

    private ArrayList<Testimony> extractTestimonies(Cursor returnData) {
        // The output ArrayList is initialized
        ArrayList<Testimony> output = new ArrayList<Testimony>();
        // Move the counter to the first item in the return data
        returnData.moveToFirst();
        int count = 0;
        // While there are still values in the return data
        while (!returnData.isAfterLast()) {
            // Add the new Testimony to the ArrayList
            Testimony testimony = new Testimony();
            testimony.setId(Integer.parseInt(returnData.getString(0)));
            testimony.setName(returnData.getString(1));
            testimony.setCountry(returnData.getString(2));
            testimony.setTestimony(returnData.getString(3));
            testimony.setImagePath(returnData.getString(4));

            output.add(count, testimony);

            // Advance the Cursor
            returnData.moveToNext();
            // Advance the counter
            count++;
        }
        // Return the ArrayList
        return output;
    }

    /*public Testimony getTestimoniesWithId(int id) {
        openDB();
        String[] columnsToRead = new String[4];
        columnsToRead[0] = Testimony.COLUMN_ID;
        columnsToRead[1] = Testimony.COLUMN_NAME;
        columnsToRead[2] = Testimony.COLUMN_COUNTRY;
        columnsToRead[3] = Testimony.COLUMN_TESTIMONY;
        String whereClause = Testimony.COLUMN_ID + '=' + id;
        Cursor returnData = readDatabase.query(Testimony.TESTIMONIES_TABLE, columnsToRead,
                whereClause, null, null, null, null);
        returnData.moveToFirst();
        Testimony a = new Testimony();
        a.setName(returnData.getString(0));
        a.setCountry(returnData.getString(1));
        a.setTestimony(returnData.getString(2));
        closeDB();
        // Return the constructed Testimony object
        return a;
    }

    public int addTestimonies(Testimony testimony) {
        openDB();
        ContentValues newValue = new ContentValues(3);
        newValue.put(Testimony.COLUMN_NAME, testimony.getName());
        newValue.put(Testimony.COLUMN_COUNTRY, testimony.getCountry());
        newValue.put(Testimony.COLUMN_TESTIMONY, testimony.getTestimony());

        // Insert the item into the database
        writeDatabase.insert(Testimony.TESTIMONIES_TABLE, null, newValue);

        // return the id of the activity just created. This will be used as the foreign key for the reminders table
        Cursor returnData = readDatabase.rawQuery("select seq from sqlite_sequence where name=?", new String[]{Testimony.TESTIMONIES_TABLE});
        int retVal = -1;
        if (returnData != null && returnData.moveToFirst()) {
            retVal = returnData.getInt(0);
            closeDB();
        }
        return retVal;
    }

    public void updateTestimonies(Testimony testimony) {
        openDB();
        ContentValues newValue = new ContentValues(8);
        newValue.put(Testimony.COLUMN_NAME, testimony.getName());
        newValue.put(Testimony.COLUMN_COUNTRY, testimony.getCountry());
        newValue.put(Testimony.COLUMN_TESTIMONY, testimony.getTestimony());
        String whereClause = Testimony.COLUMN_ID + '=' + testimony.getId();
        // Update the item into the database
        writeDatabase.update(Testimony.TESTIMONIES_TABLE, newValue, whereClause, null);
        closeDB();
    }

    public int deleteTestimonies(int id) {
        openDB();
        String whereClause = Testimony.COLUMN_ID + '=' + id;
        // Return the total number of rows removed
        int numItemsDeleted = writeDatabase.delete(Testimony.TESTIMONIES_TABLE, whereClause, null);
        closeDB();
        return numItemsDeleted;
    }*/
}
