package com.example.ashwanigupta.sharethefare.fragmentClass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ashwanigupta.sharethefare.Bill;
import com.example.ashwanigupta.sharethefare.R;

/**
 * Created by ashwani gupta on 16-03-2017.
 */

public class BillFragment extends Fragment {

    private static Bill b;


    public static BillFragment newInstance(Bill b){
        BillFragment bf = new BillFragment();
        bf.b=b;
        return bf;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview= inflater.inflate(R.layout.fragment_bill,container,false);

        TextView tvBillFragDes=(TextView) rootview.findViewById(R.id.billFragDes);
        TextView tvBillFrag2=(TextView) rootview.findViewById(R.id.billFrag2);
        TextView tvBillFragAm=(TextView) rootview.findViewById(R.id.billFragAm);
        TextView tvBillFrag3=(TextView) rootview.findViewById(R.id.billFrag3);
        TextView tvBillFragPaidBy=(TextView) rootview.findViewById(R.id.billFragPBy);
        TextView tvBillFrag4=(TextView) rootview.findViewById(R.id.billFrag4);
        TextView tvBillFrag5=(TextView) rootview.findViewById(R.id.billFrag5);
        TextView tvBillFragExpense=(TextView) rootview.findViewById(R.id.billFragExpense);

        tvBillFragDes.setText(b.getDescription());
        tvBillFragAm.setText(String.valueOf(b.getAmount()));
        tvBillFragPaidBy.setText(b.getPaidBy().getName());
        tvBillFragExpense.setText(String.valueOf(b.getAmount()));

        return rootview;
    }
}
