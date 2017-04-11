package com.example.ashwanigupta.sharethefare.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ashwani gupta on 13-02-2017.
 */

public class ShareDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="ShareTheFare.db";
    public static final int DB_VER=1;

    public interface Consts
    {
        String LBR=" ( ";
        String RBR=" ) ";
        String SEMI=" ; ";
        String COMMA=" , ";

        String TYPE_INT=" INTEGER ";
        String TYPE_PK=" PRIMARY KEY ";
        String TYPE_AI=" AUTOINCREMENT ";
        String TYPE_TEXT=" TEXT ";
        String TYPE_BOOLEAN="BOOLEAN";

    }

    public ShareDbHelper(Context context) {
        super(context,DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Shares.CMD_TABLE_CREATE);
        db.execSQL(Bills.CMD_TABLE_CREATE_BILL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
