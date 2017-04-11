package com.example.ashwanigupta.sharethefare.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashwanigupta.sharethefare.Adapters.FriendExpenseAdapter;
import com.example.ashwanigupta.sharethefare.Bill;
import com.example.ashwanigupta.sharethefare.Friend;
import com.example.ashwanigupta.sharethefare.R;
import com.example.ashwanigupta.sharethefare.database.ShareDbHelper;
import com.example.ashwanigupta.sharethefare.database.Shares;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class NewExpense extends AppCompatActivity {

    Friend obj;
    int number=0;
    int idNo=0;
    TextView tvExpenseTop, tvBillName, tvPaidBy;
    EditText etTotalExpense, etBillName;
    Button btnSaveExpense, btnPaidBy;
    ListView lvToAddExp;
    ArrayList<Friend> friendlist = new ArrayList<>();
    ArrayList<Bill> bills = new ArrayList<>();
    Bill billObj;
    Gson gson=new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_expense);

        tvExpenseTop = (TextView) findViewById(R.id.tvExpenseTop);
        tvBillName = (TextView) findViewById(R.id.tvBillName);
        tvPaidBy = (TextView) findViewById(R.id.tvPaidBy);

        etTotalExpense = (EditText) findViewById(R.id.etTotalExpense);
        etBillName = (EditText) findViewById(R.id.etBillName);
        btnSaveExpense = (Button) findViewById(R.id.btnSaveExpense);
        btnPaidBy = (Button) findViewById(R.id.btnPaidBy);

        lvToAddExp = (ListView) findViewById(R.id.lvToAddExp);

        final ShareDbHelper dbHelper=new ShareDbHelper(this);
        friendlist = Shares.getAllFriends(dbHelper.getReadableDatabase());

        btnPaidBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewExpense.this, Payer.class);
                Bundle b=new Bundle();
                b.putString("friendlist", gson.toJson(friendlist));
                i.putExtras(b);
                startActivityForResult(i, 104);
            }
        });


        billObj = new Bill(idNo,etBillName.getText().toString(),Float.parseFloat(etTotalExpense.getText().toString()),0);
        bills.add(billObj);

        //in case no payer is selected..then the first friend is the default payer
        obj = friendlist.get(0);
        billObj.setPaidBy(friendlist.get(0));

        FriendExpenseAdapter frndExpAdapter = new FriendExpenseAdapter(friendlist, this, bills.get(bills.size() - 1));
        lvToAddExp.setAdapter(frndExpAdapter);

        btnSaveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(NewExpense.this, "Payer set to" + billObj.getPaidBy().getName(), Toast.LENGTH_SHORT).show();

                int size = bills.get(bills.size() - 1).didExpense.size();

                bills.get(bills.size() - 1).getPaidBy().setTotal(-(Float.parseFloat(etTotalExpense.getText().toString())));
                Log.d("paid by", "onClick: "+bills.get(bills.size() - 1).getPaidBy().getName());
                Log.d("paid by", "onClick: "+bills.get(bills.size() - 1).getPaidBy().getTotal());

                //friendlist.get(number).setTotal(-(Float.parseFloat(etTotalExpense.getText().toString())));

                float onEach = (Float.parseFloat(etTotalExpense.getText().toString()))/ size;

                Log.d("on each", "onClick: "+onEach);
                bills.get(bills.size() - 1).setExpenseOnEach(onEach);
                //billObj.setExpenseOnEach(onEach);

                // for(int i=0;i<billObj.didExpense.size()-1;i++)
                for (int i = 0; i < bills.get(bills.size() - 1).didExpense.size(); i++)
                {
                    Log.d("before", "onClick: "+ bills.get(bills.size() - 1).didExpense.get(i).getTotal());
                    bills.get(bills.size() - 1).didExpense.get(i).setTotal(onEach);
                    Log.d("before", "onClick: "+ bills.get(bills.size() - 1).didExpense.get(i).getTotal());


                }


                for (int i = 0; i < friendlist.size(); i++)
                {
                    if(friendlist.get(i).clicked==1)
                    {
                        friendlist.get(i).clicked = 0;
                    }
                }

//                 for(int j=0;j< bills.get(bills.size()-1).didExpense.size();j++)
//                    {
//                        int y=bills.get(bills.size()-1).didExpense.get(j).getId();
//                        friendlist.get(y-2).updateTotal(bills.get(bills.size()-1).didExpense.get(j).getTotal());
//                    }


                Toast.makeText(NewExpense.this, "Expense Saved", Toast.LENGTH_SHORT).show();

                Gson gson = new Gson();

                Intent i = new Intent();
                Bundle b = new Bundle();
                b.putString("list", gson.toJson(friendlist));
                b.putString("billlist", gson.toJson(bills));


                i.putExtras(b);
                setResult(RESULT_OK, i);
                finish();


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 104 && resultCode == RESULT_OK)
        {
            for(int i=0;i<friendlist.size();i++)
            {
                friendlist.get(i).flag=0;
            }
            number = data.getExtras().getInt("number");
            bills.get(bills.size()-1).setPaidBy(friendlist.get(number));

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
