package com.example.haeju.tap1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HAEJU on 2018-01-28.
 */

public class ClickActivity extends Activity {

    TextView txtvinfo;
    TextView txtvdate;
    ImageView imgvicon;
    String[] vdate;
    String[] vinfo;
    int[] vicon;
    int position;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        Intent intent = getIntent();

        position = intent.getExtras().getInt("position");
        vdate = intent.getStringArrayExtra("date");
        vinfo = intent.getStringArrayExtra("info");
        vicon = intent.getIntArrayExtra("icon");

        txtvdate = (TextView) findViewById(R.id.vdate);
        txtvinfo = (TextView) findViewById(R.id.vinfo);

        imgvicon = (ImageView) findViewById(R.id.vicon);

        txtvinfo.setText(vinfo[position]);
        txtvdate.setText(vdate[position]);
        imgvicon.setImageResource(vicon[position]);
    }
}
