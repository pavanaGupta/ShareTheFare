package com.example.ashwanigupta.sharethefare.Activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ashwanigupta.sharethefare.R;

import java.io.FileNotFoundException;

public class NewFriend extends AppCompatActivity {

    TextView tvTopMain, tvEnterName,tvImage;
    EditText etGetName;
    Button btnSave;
    ImageView ivFriend;
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);

        tvTopMain=(TextView) findViewById(R.id.tvTopMain);
        tvEnterName=(TextView) findViewById(R.id.tvEnterName);
        tvImage=(TextView) findViewById(R.id.tvImage);

        ivFriend=(ImageView)findViewById(R.id.ivFriend);
        etGetName=(EditText) findViewById(R.id.etGetName);
        btnSave=(Button) findViewById(R.id.btnSave);


        ivFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 103);

            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent();
                if((etGetName.getText()==null)|| (selectedImage==null))
                {
                    Log.d("noinfo" +
                            " ", "onClick: Set the information first");
                    Toast.makeText(NewFriend.this, "Set the information first",Toast.LENGTH_SHORT);
                }
                else
                {
                    i.putExtra("name", etGetName.getText().toString());
                    i.putExtra("image", selectedImage.toString());
                    setResult(RESULT_OK, i);
                    finish();
                }

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==103 && resultCode==RESULT_OK && null!=data)
        {
            selectedImage = data.getData();

            Log.d("result", "onActivityResult: "+ data.getData().toString());

            try {
                ivFriend.setImageBitmap(BitmapFactory
                        .decodeStream(getContentResolver().openInputStream(data.getData())));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        else {
            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }




        super.onActivityResult(requestCode, resultCode, data);
    }

}
