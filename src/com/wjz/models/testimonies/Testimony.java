package com.wjz.models.testimonies;

import android.database.sqlite.SQLiteDatabase;

/**
 * ********************************************************************************************************************
 * Models the representation of an activity
 * ********************************************************************************************************************
 */
public class Testimony {
    // Instance properties
    private int id;
    private String name;
    private String country;
    private String testimony;
    private String imagePath;

    // Database table
    public static final String TESTIMONIES_TABLE = "testimonies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGEPATH = "imagepath";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_TESTIMONY = "testimony";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table if not exists "
            + TESTIMONIES_TABLE
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_IMAGEPATH + " text not null, "
            + COLUMN_COUNTRY + " text not null, "
            + COLUMN_TESTIMONY + " text not null"
            + ");";

    // used to create the table
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // used to upgrade the table
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        database.execSQL("drop table if exists " + TESTIMONIES_TABLE);
        onCreate(database);
    }

    // Getters and Setters follow
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Testimony() {
        super();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTestimony() {
        return testimony;
    }

    public void setTestimony(String testimony) {
        this.testimony = testimony;
    }
}
