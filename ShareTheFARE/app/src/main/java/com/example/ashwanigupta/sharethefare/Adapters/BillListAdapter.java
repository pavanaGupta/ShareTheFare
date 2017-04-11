package com.example.ashwanigupta.sharethefare.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ashwanigupta.sharethefare.Bill;
import com.example.ashwanigupta.sharethefare.R;

import java.util.ArrayList;

/**
 * Created by ashwani gupta on 14-02-2017.
 */

public class BillListAdapter extends BaseAdapter{

    Context c;
    ArrayList<Bill> bills=new ArrayList<>();

    public BillListAdapter(Context c, ArrayList<Bill> bills) {
        this.c = c;
        this.bills = bills;
    }

    @Override
    public int getCount() {
        return bills.size();
    }

    @Override
    public Bill getItem(int position) {
        return bills.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final BillHolder bHolder;


        if(convertView==null)
        {

            convertView=li.inflate(R.layout.simple_list3,null);

            bHolder=new BillHolder();
            bHolder.tvBill=(TextView)convertView.findViewById(R.id.tvBill);
            convertView.setTag(bHolder);

        }
        else
        {
            bHolder=(BillHolder) convertView.getTag();
        }

        String des=bills.get(position).getDescription();
        bHolder.tvBill.setText(des);

        bHolder.tvBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        return convertView;
    }

    static class BillHolder
    {
        TextView tvBill;
    }
}
