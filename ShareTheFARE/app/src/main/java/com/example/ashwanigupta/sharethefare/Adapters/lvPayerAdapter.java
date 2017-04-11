package com.example.ashwanigupta.sharethefare.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ashwanigupta.sharethefare.Friend;
import com.example.ashwanigupta.sharethefare.R;

import java.util.ArrayList;

/**
 * Created by ashwani gupta on 13-02-2017.
 */

public class lvPayerAdapter extends BaseAdapter {

    Context c;
    ArrayList<Friend> friendlist= new ArrayList<>();

    public lvPayerAdapter(Context c, ArrayList<Friend> friendlist) {
        this.c = c;
        this.friendlist = friendlist;
    }

    @Override
    public int getCount() {
        return friendlist.size();
    }

    @Override
    public Friend getItem(int position) {
        return friendlist.get(position);
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
            eHolder.tvResultExp=(TextView) convertView.findViewById(R.id.tvResultExp);
            eHolder.btnAddedExp=(Button) convertView.findViewById(R.id.btnAddExp);
            convertView.setTag(eHolder);

        }
        else
        {
            eHolder=(ExpenseHolder) convertView.getTag();
        }

        eHolder.tvNameExp.setText(friendlist.get(position).getName());

        eHolder.btnAddedExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<friendlist.size();i++)
                {
                    if(friendlist.get(i).flag==1)
                    {
                        friendlist.get(i).flag=0;
                    }
                }

                friendlist.get(position).flag=1;

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
