package com.example.ashwanigupta.sharethefare.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ashwanigupta.sharethefare.Bill;
import com.example.ashwanigupta.sharethefare.Friend;

import java.util.ArrayList;

import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.COMMA;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.LBR;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.RBR;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.SEMI;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_AI;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_INT;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_PK;
import static com.example.ashwanigupta.sharethefare.database.ShareDbHelper.Consts.TYPE_TEXT;

/**
 * Created by ashwani gupta on 14-02-2017.
 */

public class Bills {

    public static final String TABLE_NAME_2="bills";


    public interface Columns2
    {
        String BILL_ID="bill_id";
        String DESCRIPTION="bill_desc";
        String AMOUNT="bill_amount";
        String PAYER="bill_payer";
        String ExpenseOnEach="bill_onEach";
    }

    public static final String CMD_TABLE_CREATE_BILL =  "CREATE TABLE " + TABLE_NAME_2 +
            LBR +
            Columns2.BILL_ID + TYPE_INT + TYPE_PK + TYPE_AI + COMMA +
            Columns2.DESCRIPTION + TYPE_TEXT +COMMA+
            Columns2.AMOUNT+ TYPE_INT +COMMA+
            Columns2.PAYER+ TYPE_TEXT+ COMMA+
            Columns2.ExpenseOnEach+ TYPE_INT+
            RBR + SEMI;

    public static boolean addBill(String desc,float a,String p, Float e ,SQLiteDatabase db) {

        if(db.isReadOnly())
        {
            return false;
        }

        ContentValues taskObj= new ContentValues();
        taskObj.put(Columns2.DESCRIPTION,desc);
        taskObj.put(Columns2.AMOUNT, a);
        taskObj.put(Columns2.PAYER, p);
        taskObj.put(Columns2.ExpenseOnEach, e);

        db.insert(TABLE_NAME_2,null,taskObj);
        db.close();
        return true;

    }

    public static ArrayList<Bill> getAllBills(SQLiteDatabase db)
    {
        String[] PROJECTION={
                Columns2.BILL_ID, Columns2.DESCRIPTION, Columns2.AMOUNT, Columns2.PAYER,Columns2.ExpenseOnEach
        };

        Cursor cur= db.query(TABLE_NAME_2, PROJECTION,null, null,null, null,null);  //selecting all

        ArrayList<Bill> billlist=new ArrayList<>();
        cur.moveToFirst();
        int idIndex=cur.getColumnIndex(Columns2.BILL_ID);
        int nameIndex= cur.getColumnIndex(Columns2.DESCRIPTION);
        int totalIndex=cur.getColumnIndex(Columns2.AMOUNT);
        int clickedIndex=cur.getColumnIndex(Columns2.PAYER);
        int flagIndex=cur.getColumnIndex(Columns2.ExpenseOnEach);

        while(cur.moveToNext())
        {
            billlist.add(new Bill(cur.getInt(idIndex), cur.getString(nameIndex),cur.getFloat(totalIndex),cur.getFloat(flagIndex)));
        }
        cur.close();
        return  billlist;
    }
}
