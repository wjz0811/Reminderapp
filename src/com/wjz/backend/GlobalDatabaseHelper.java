package com.wjz.backend;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.wjz.models.reminders.Reminders;
import com.wjz.models.testimonies.Testimony;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * This is a database helper that is shared by the different model classes
 */
public class GlobalDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_PATH = "/data/data/com.wjz/databases/";
    public static final String DATABASE_NAME = "reminderapp.db3";
    public static final int DATABASE_VERSION = 1;
    private static GlobalDatabaseHelper gHelper;
    private Context context;

    private GlobalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        if (!databaseExists()) {
            try {
                this.getReadableDatabase();
                copyDataBase();
                this.close();
            } catch (IOException ioException) {
                //database not in /assets
            }
        }
        else
          this.getWritableDatabase();
        Reminders.onCreate(this.getWritableDatabase());
    }

    public static GlobalDatabaseHelper getInstance(Context context) {
        gHelper = new GlobalDatabaseHelper(context);
        return gHelper;
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    // check if database is already in the app's directory
    // Source: http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
    private boolean databaseExists() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
        }
        if(null!=checkDB){
            checkDB.close();
            return true;
        }
        return false;
    }

    // copies reminderapp.db from /assets to app's data folder
    // Source: http://www.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = null;
        myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase dn, int oldVersion,
                          int newVersion) {
        Reminders.onUpgrade(dn, oldVersion, newVersion);
    }
}
