package com.example.ashwanigupta.sharethefare.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.ashwanigupta.sharethefare.Adapters.BillListAdapter;
import com.example.ashwanigupta.sharethefare.Bill;
import com.example.ashwanigupta.sharethefare.R;
import com.example.ashwanigupta.sharethefare.database.Bills;
import com.example.ashwanigupta.sharethefare.database.ShareDbHelper;
import com.example.ashwanigupta.sharethefare.database.Shares;
import com.example.ashwanigupta.sharethefare.fragmentClass.BillFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class BillView extends AppCompatActivity {


    ArrayList<Bill> bills= new ArrayList<>();
    ShareDbHelper dbHelper;
    ListView lvBills;
    static FragmentManager fragMan;
    static FragmentTransaction fragTxn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_view);

        dbHelper=new ShareDbHelper(this);
        lvBills=(ListView)findViewById(R.id.lvBills);
        bills= Bills.getAllBills(dbHelper.getReadableDatabase());

        BillListAdapter bla=new BillListAdapter(this, bills);
        lvBills.setAdapter(bla);

        fragMan=getSupportFragmentManager();

    }

    public static void setFragment(Bill b) {

        BillFragment bf=BillFragment.newInstance(b);
        fragTxn=fragMan.beginTransaction();
        fragTxn.replace(R.id.fragContainer,bf);
        fragTxn.commit();
    }
}
