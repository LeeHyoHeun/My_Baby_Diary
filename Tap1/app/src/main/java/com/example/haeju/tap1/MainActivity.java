package com.example.haeju.tap1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnFragment1;
    Button btnFragment2;
    Button btnFragment3;
    Button btnFragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFragment1 = (Button) findViewById(R.id.btn_fragment1);
        btnFragment2 = (Button) findViewById(R.id.btn_fragment2);
        btnFragment3 = (Button) findViewById(R.id.btn_fragment3);
        btnFragment4 = (Button) findViewById(R.id.btn_fragment4);

        btnFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new FragmentActivity1()).commit();
            }
        });
        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new FragmentActivity2()).commit();
            }
        });
        btnFragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new FragmentActivity3()).commit();
            }
        });
        btnFragment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_frame, new FragmentActivity4()).commit();
            }
        });

    }
}