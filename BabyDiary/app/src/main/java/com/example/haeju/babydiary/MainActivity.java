package com.example.haeju.babydiary;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    CustomAdapter adapter;

    String[] vdate = new String[]{
            "12/1",  "12/2",  "12/3",  "12/4"
    };
    String[] vinfo = new String[]{
            "Apple Pie",  "Banana Bread",  "Cupcake",  "Donut"
    };
    int[] vicon = new int[]{
            R.raw.icon10, R.raw.icon11, R.raw.icon15, R.raw.icon16
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        ListView listView = (ListView)findViewById(R.id.listView1);
        adapter = new CustomAdapter(this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), vicon[position]);
                Intent intent = new Intent(getApplicationContext(), ClickActivity.class);
                intent.setAction("MyAction");
                intent.putExtra("date", vdate[position]);
                intent.putExtra("info", vinfo[position]);
                intent.putExtra("icon", bitmap);
                startActivity(intent);

            }
        });

    }
    //
    public class CustomAdapter extends BaseAdapter {
        Context my_context;

        CustomAdapter(Context context) { my_context = context; }
        @Override
        public Object getItem(int position) { return null; }
        @Override
        public long getItemId(int position) { return 0; }
        @Override
        public int getCount() { return vicon.length; }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListViewHolder viewHolder;
            //
            if (convertView == null) {
                LayoutInflater inflater=(LayoutInflater)my_context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row, parent, false);
                //
                viewHolder = new ListViewHolder();
                viewHolder.iconView = (ImageView)convertView.findViewById(R.id.vicon1);
                viewHolder.numView = (TextView)convertView.findViewById(R.id.vnum1);
                viewHolder.codeView = (TextView)convertView.findViewById(R.id.vcode1);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ListViewHolder)convertView.getTag();
            }
            //
            viewHolder.iconView.setImageResource(vicon[position]);
            viewHolder.numView.setText(vdate[position]);
            viewHolder.codeView.setText(vinfo[position]);
            //
            return convertView;
        }
        //
        private class ListViewHolder {
            ImageView iconView;
            TextView numView;
            TextView codeView;
        };
    }
}