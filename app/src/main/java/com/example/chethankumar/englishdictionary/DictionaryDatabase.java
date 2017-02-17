package com.example.chethankumar.englishdictionary;

/**
 * Created by Chethan Kumar on 2017-02-04.
 */

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
public class DictionaryDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAMES = "flip";
    private static final int DATABASE_VERSION = 5;
    public DictionaryDatabase(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
    }
}