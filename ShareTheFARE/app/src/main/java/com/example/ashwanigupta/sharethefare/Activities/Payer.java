package com.example.ashwanigupta.sharethefare.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ashwanigupta.sharethefare.Adapters.lvPayerAdapter;
import com.example.ashwanigupta.sharethefare.Friend;
import com.example.ashwanigupta.sharethefare.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Payer extends AppCompatActivity {

    ListView lvPayerList;
    Button btnSave;
    ArrayList<Friend> friendlist;
    Gson gson=new Gson();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payer);

        lvPayerList=(ListView) findViewById(R.id.lvPayerList);
        btnSave= (Button) findViewById(R.id.btnSave);

        Intent i=getIntent();
        String flJson=i.getExtras().getString("friendlist");
        friendlist=gson.fromJson(flJson, new TypeToken<ArrayList<Friend>>(){}.getType());
        lvPayerAdapter lvp=new lvPayerAdapter(this, friendlist);
        lvPayerList.setAdapter(lvp);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i;
                for( i=0;i< friendlist.size();i++)
                {
                    if(friendlist.get(i).flag==1)
                    {
                        Intent in=new Intent();
                        Bundle b=new Bundle();
                        b.putInt("number", i);

                        in.putExtras(b);
                        setResult(RESULT_OK, in);
                        finish();

                    }
                }

                if(i==friendlist.size()-1 && friendlist.get(i).flag==0 )
                    Toast.makeText(Payer.this,"None Selected", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
