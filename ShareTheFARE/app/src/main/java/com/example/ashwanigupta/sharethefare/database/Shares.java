package com.example.ashwanigupta.sharethefare.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ashwanigupta.sharethefare.Friend;

import java.util.ArrayList;

import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.COMMA;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.LBR;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.RBR;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.SEMI;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_AI;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_BOOLEAN;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_INT;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_PK;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_TEXT;

/**
 * Created by ashwani gupta on 13-02-2017.
 */

public class Shares {

    public static final String TABLE_NAME="friend";


    public interface Columns
    {
        String ID="friend_id";
        String NAME="friend_name";
        String TOTAL="friend_total";
        String IMAGE="friend_image";
    }

    public static final String CMD_TABLE_CREATE =  "CREATE TABLE " + TABLE_NAME +
            LBR +
            Columns.ID + TYPE_INT + TYPE_PK + TYPE_AI + COMMA +
            Columns.NAME + TYPE_TEXT +COMMA+
            Columns.TOTAL+ TYPE_INT +COMMA+
            Columns.IMAGE+ TYPE_TEXT+
            RBR + SEMI;

    public static boolean addFriend(String name, String img,SQLiteDatabase db) {

        if(db.isReadOnly())
        {
            return false;
        }

        ContentValues taskObj= new ContentValues();
        taskObj.put(Columns.NAME,name);
        taskObj.put(Columns.TOTAL, 0);
        taskObj.put(Columns.IMAGE,img);

        db.insert(TABLE_NAME,null,taskObj);
        db.close();
        return true;

    }

    public static boolean updateTotals(int Id, SQLiteDatabase db,float p)
    {
        if(db.isReadOnly())
        {
            return false;
        }

        ContentValues toDoObj= new ContentValues();
        toDoObj.put(Columns.TOTAL,p );
        String whereClause=Columns.ID + " =?";
        db.update(TABLE_NAME, toDoObj,whereClause,new String[]
                {
                        String.valueOf(Id)
                });
        //db.query(TABLE_NAME,new String[]{Columns.ID, Columns.NAME, Columns.TOTAL},null,null,null,null,null);
        return true;


    }


    public static ArrayList<Friend> getAllFriends(SQLiteDatabase db)
    {
        String[] PROJECTION={
                Columns.ID, Columns.NAME,Columns.TOTAL
        };

        Cursor cur= db.query(TABLE_NAME, PROJECTION,null, null,null, null,null);  //selecting all

        ArrayList<Friend> friendlist=new ArrayList<>();
        cur.moveToFirst();
        int idIndex=cur.getColumnIndex(Columns.ID);
        int nameIndex= cur.getColumnIndex(Columns.NAME);
        int totalIndex=cur.getColumnIndex(Columns.TOTAL);

        while(cur.moveToNext())
        {
            friendlist.add(new Friend(cur.getInt(idIndex), cur.getString(nameIndex),cur.getFloat(totalIndex)));
        }
        cur.close();
        return  friendlist;
    }
}
