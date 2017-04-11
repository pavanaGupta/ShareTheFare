package com.example.ashwanigupta.sharethefare.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ashwanigupta.sharethefare.Adapters.FriendListAdapter;
import com.example.ashwanigupta.sharethefare.Bill;
import com.example.ashwanigupta.sharethefare.Friend;
import com.example.ashwanigupta.sharethefare.R;
import com.example.ashwanigupta.sharethefare.database.Bills;
import com.example.ashwanigupta.sharethefare.database.ShareDbHelper;
import com.example.ashwanigupta.sharethefare.database.Shares;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MA";
    ArrayList<Friend> friendlist;
    ArrayList<Bill> bills;
    RecyclerView rvList;
    Button btnFrndAdd, btnExpenseAdd, btnBillView;
    FriendListAdapter friendAdapter;
    ShareDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new ShareDbHelper(this);
        friendlist= Shares.getAllFriends(dbHelper.getReadableDatabase());
        bills=Bills.getAllBills(dbHelper.getReadableDatabase());

        rvList = (RecyclerView) findViewById(R.id.rvList);
        btnFrndAdd = (Button) findViewById(R.id.btnFrndAdd);
        btnExpenseAdd = (Button) findViewById(R.id.btnExpenseAdd);
        btnBillView = (Button) findViewById(R.id.btnBillView);

        btnFrndAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, NewFriend.class);
                startActivityForResult(i, 101);
            }
        });

        btnExpenseAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                friendlist=Shares.getAllFriends(dbHelper.getReadableDatabase());
                if (friendlist.size() != 0) {
                    Intent i = new Intent(MainActivity.this, NewExpense.class);
                    startActivityForResult(i, 102);

                } else
                    Toast.makeText(MainActivity.this, "No friend added.", Toast.LENGTH_SHORT).show();
            }
        });

        btnBillView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, BillView.class);
                startActivityForResult(i, 105);

            }
        });


        friendAdapter = new FriendListAdapter(friendlist, this);

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(friendAdapter);
        refreshFriends();
        rvList.setNestedScrollingEnabled(false);   //disables the scrolling of recycler view


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Gson gson=new Gson();
        if (resultCode == RESULT_OK) {
            if (requestCode == 101) {
                String name = data.getStringExtra("name");
                String image=data.getStringExtra("image");
                Shares.addFriend(name,image, dbHelper.getWritableDatabase());
                Log.d(TAG, "onActivityResult: yes started");
                refreshFriends();
                Log.d(TAG, "onActivityResult: yes started");

            }


            else if(requestCode==102)
            {
                String frndListJson = data.getExtras().getString("list");
                ArrayList<Friend> list= gson.fromJson(frndListJson, new TypeToken<ArrayList<Friend>>(){}.getType() );
                for(int i=0;i<list.size();i++)
                {
                    Log.d(TAG, "onActivityResult: "+list.get(i).getName()+" "+list.get(i).getTotal());
                    Log.d(TAG, "onActivityResult: "+friendlist.get(i).getName()+" "+friendlist.get(i).getTotal());

                    Shares.updateTotals(i+1,dbHelper.getWritableDatabase(),list.get(i).getTotal());
                }

                refreshFriends();
                for(int i=0;i<friendlist.size();i++)
                {
                    Log.d(TAG, "onActivityResult: "+friendlist.get(i).getName()+" "+friendlist.get(i).getTotal());

                }
                String billListJson =data.getExtras().getString("billlist");
                Log.d(TAG, billListJson);
                ArrayList<Bill> billlist=gson.fromJson(billListJson,new TypeToken<ArrayList<Bill>>(){}.getType());
                Bills.addBill(billlist.get(0).getDescription(),billlist.get(0).getAmount(),billlist.get(0).getPaidBy().getName(), billlist.get(0).getExpenseOnEach(),dbHelper.getWritableDatabase());
                refreshBills();
            }
        }


            super.onActivityResult(requestCode, resultCode, data);
        }


    void refreshFriends()
    {
        friendlist=Shares.getAllFriends(dbHelper.getReadableDatabase());
        Log.d(TAG, "refreshFriends: "+friendlist.size());
        for(int i=0;i<friendlist.size();i++)
        {
            Log.d(TAG, "refreshFriends: "+ friendlist.get(i).getName());
        }
        friendAdapter.updateFriends(friendlist);

    }

    void refreshBills()
    {
        bills=Bills.getAllBills(dbHelper.getReadableDatabase());

    }
}
