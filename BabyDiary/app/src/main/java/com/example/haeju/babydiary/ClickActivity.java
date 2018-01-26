package com.example.haeju.babydiary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HAEJU on 2018-01-09.
 */

public class ClickActivity extends AppCompatActivity {

    private  int img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);



        Intent intent = getIntent();



        ImageView vicon = (ImageView) findViewById(R.id.vicon);
        TextView vdate = (TextView) findViewById(R.id.vdate);
        TextView vinfo = (TextView) findViewById(R.id.vinfo);


        if(intent.getAction().equals("MyAction")) {
            Bitmap bitmap = (Bitmap)intent.getExtras().get("icon");
            vicon.setImageBitmap(bitmap);
        }
        //img = Integer.parseInt(intent.getStringExtra("icon"));
       // vicon.setImageResource(img);

        vdate.setText(intent.getStringExtra("date"));
        vinfo.setText(intent.getStringExtra("info"));

    }
}
