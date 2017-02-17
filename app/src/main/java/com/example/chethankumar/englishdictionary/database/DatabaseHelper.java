package com.example.chethankumar.englishdictionary.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.chethankumar.englishdictionary.model.Bookmark;
import com.example.chethankumar.englishdictionary.model.History;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suneesh on 3/26/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Dictionary";

    private static final String TABLE_BOOKMARK = "Bookmark";
    private static final String KEY_BOOK_ID = "book_id";
    private static final String KEY_BOOK_NAME = "book_name";
    private static final String KEY_BOOK_MEANING = "book_meaning";

    private static final String TABLE_HISTORY = "History";
    private static final String KEY_HISTORY_ID = "history_id";
    private static final String KEY_HISTORY_NAME = "history_name";
    private static final String KEY_HISTORY_MEANING = "history_meaning";

    private static final String CREATE_TABLE_BOOKMARK = "CREATE TABLE "
            + TABLE_BOOKMARK + "(" + KEY_BOOK_ID + " INTEGER PRIMARY KEY," + KEY_BOOK_NAME + " TEXT," + KEY_BOOK_MEANING + " TEXT" + ")";
    private static final String CREATE_TABLE_HISTORY = "CREATE TABLE "
            + TABLE_HISTORY + "(" + KEY_HISTORY_ID + " INTEGER PRIMARY KEY," + KEY_HISTORY_NAME + " TEXT," + KEY_HISTORY_MEANING + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOKMARK);
        db.execSQL(CREATE_TABLE_HISTORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }

    public void createBookmark(Bookmark todo) {
        Boolean flag = false;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BOOK_NAME, todo.get_name());
        values.put(KEY_BOOK_MEANING, todo.get_meaning());
        // insert row
        db.insert(TABLE_BOOKMARK, null, values);
        List<Bookmark> list = getAllBookmarks();
        for(int i = 0; i < list.size(); i++){
            if(!list.isEmpty()){
                if(list.get(i).get_name().trim().equals(todo.get_name().trim())){
                    flag = true;
                    break;
                }
                else{
                    flag = false;
                }
            }
        }
        if(flag == false) {
            db.insert(TABLE_BOOKMARK, null, values);
        }
        if(flag == true) {
            deleteBook(todo.get_name().trim());
            db.insert(TABLE_BOOKMARK, null, values);
        }

        System.out.println("successfully" + todo.get_name());
        System.out.println("successfully");
    }

    public List<Bookmark> getAllBookmarks() {
        List<Bookmark> todos = new ArrayList<Bookmark>();
        String selectQuery = "SELECT  * FROM " + TABLE_BOOKMARK;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Bookmark td = new Bookmark();
                td.set_id(c.getInt((c.getColumnIndex(KEY_BOOK_ID))));
                td.set_name((c.getString(c.getColumnIndex(KEY_BOOK_NAME))));
                td.set_meaning(c.getString(c.getColumnIndex(KEY_BOOK_MEANING)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public void deleteBook(String st) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOKMARK,
                KEY_BOOK_NAME + " = ?",
                new String[]{st});
        System.out.println("Delete device name successfully " + st);
    }
    public void createHistory(History todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_HISTORY_NAME, todo.get_name());
        values.put(KEY_HISTORY_MEANING, todo.get_meaning());
        // insert row
        db.insert(TABLE_HISTORY, null, values);

        System.out.println("successfully history" + todo.get_name());
    }

    public List<History> getAllHistory() {
        List<History> todos = new ArrayList<History>();
        String selectQuery = "SELECT  * FROM " + TABLE_HISTORY;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                History td = new History();
                td.set_id(c.getInt((c.getColumnIndex(KEY_HISTORY_ID))));
                td.set_name((c.getString(c.getColumnIndex(KEY_HISTORY_NAME))));
                td.set_meaning(c.getString(c.getColumnIndex(KEY_HISTORY_MEANING)));
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }
}
