package com.example.ashwanigupta.sharethefare.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ashwanigupta.sharethefare.Bill;
import com.example.ashwanigupta.sharethefare.Friend;
import com.example.ashwanigupta.sharethefare.R;

import java.util.ArrayList;

/**
 * Created by ashwani gupta on 13-02-2017.
 */

public class FriendExpenseAdapter extends BaseAdapter {

    ArrayList<Friend> friendList= new ArrayList<>();
    Bill currentBill;
    Context c;

    public FriendExpenseAdapter(ArrayList<Friend> friendList, Context c, Bill currentBill) {
        this.friendList = friendList;
        this.c = c;
        this.currentBill=currentBill;
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Friend getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater li= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ExpenseHolder eHolder;


        if(convertView==null)
        {

            convertView=li.inflate(R.layout.simple_list2,null);

            eHolder=new ExpenseHolder();
            eHolder.tvNameExp=(TextView)convertView.findViewById(R.id.tvNameExp);
            eHolder.tvResultExp=(TextView)convertView.findViewById(R.id.tvResultExp);
            eHolder.btnAddedExp=(Button) convertView.findViewById(R.id.btnAddExp);
            convertView.setTag(eHolder);

        }
        else
        {
            eHolder=(ExpenseHolder) convertView.getTag();
        }

        eHolder.tvNameExp.setText(friendList.get(position).getName());
        if(friendList.get(position).clicked==0)
            eHolder.tvResultExp.setText("");
        else
            eHolder.tvResultExp.setText("Added");


        eHolder.btnAddedExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(friendList.get(position).clicked==0)
                {
                    eHolder.tvResultExp.setText("ADDED");
                    currentBill.didExpense.add(getItem(position));
                    friendList.get(position).clicked=1;

                }
                else if(friendList.get(position).clicked==1 )
                {
                    eHolder.tvResultExp.setText("");
                    currentBill.didExpense.remove(currentBill.didExpense.size()-1);
                    friendList.get(position).clicked=0;
                }



            }
        });

        return convertView;
    }

    static class ExpenseHolder
    {
        TextView tvNameExp, tvResultExp;
        Button btnAddedExp;
    }
}
