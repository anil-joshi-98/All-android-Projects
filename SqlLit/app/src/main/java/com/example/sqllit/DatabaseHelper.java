package com.example.sqllit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "ANIL_JOSHI";
    private static final String TABLE_NAME = "ANIL";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "EXTRA";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EXTRA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String NAME, String EXTRA) {
        SQLiteDatabase sq = getWritableDatabase();
        ContentValues vl = new ContentValues();
        vl.put(COL_2, NAME);
        vl.put(COL_3, EXTRA);

        long var = sq.insert(TABLE_NAME, null, vl);
        if(var == -1) {
            return false;
        } else
            return true;
    }

    public Cursor getData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID='" + id + " '";
        return db.rawQuery(query, null);
    }

    public Integer delete(String id) {
        SQLiteDatabase sq = getWritableDatabase();
        return sq.delete(TABLE_NAME, "ID=?", new String[]{id});
    }

    public int deleteAll(){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,null,null);
    }
    public Cursor showAll() {
        SQLiteDatabase sq=getWritableDatabase();
        Cursor cursor=sq.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
}
