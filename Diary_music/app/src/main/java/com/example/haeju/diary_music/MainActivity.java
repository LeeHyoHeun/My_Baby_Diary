package com.example.haeju.diary_music;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Cursor currentCursor;
    //
    int COL_ID;
    int COL_ARTIST;
    int COL_TITLE;
    int COL_NAME;
    int COL_DATA;
    int COL_SIZE;
    //
    private ListAdapter adapter;
    private ListView listView;
    //private SeekBar seekBar;
    //
    int position_h;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        getAudioList();
        // get reference of list & adapter
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(itemClickListener);
        adapter = new ListAdapter(this);
        listView.setAdapter(adapter);
        //
        if (PlayerService.isRunning()) {
            ((Button)findViewById(R.id.stopPlayer)).setEnabled(true);
        } else {
            ((Button)findViewById(R.id.stopPlayer)).setEnabled(false);
        }
        //seekBar = (Seek)
    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
    //
    public void clickStop(View view) {
        Intent intent = new Intent(MainActivity.this, PlayerService.class);
        stopService(intent);
        ((Button)findViewById(R.id.stopPlayer)).setEnabled(false);
    }
    //
    public void clickPrev(View view) {
        if(position_h - 1 >= 0) {
            position_h--;
            currentCursor.moveToPosition(position_h);
            Intent intent = new Intent(MainActivity.this, PlayerService.class);
            currentCursor.moveToPosition(position_h);
            String audioPath = currentCursor.getString(COL_DATA);
            intent.putExtra("filename", audioPath);
            String audioName = currentCursor.getString(COL_NAME);
            intent.putExtra("name", audioName);
            String audioTitle = currentCursor.getString(COL_TITLE);
            intent.putExtra("title", audioTitle);

            startService(intent);

        }
    }
    //
    public void clickNext(View view) {
        if(position_h + 1 < listView.getCount()) {
            position_h++;
            currentCursor.moveToPosition(position_h);
            Intent intent = new Intent(MainActivity.this, PlayerService.class);
            currentCursor.moveToPosition(position_h);
            String audioPath = currentCursor.getString(COL_DATA);
            intent.putExtra("filename", audioPath);
            String audioName = currentCursor.getString(COL_NAME);
            intent.putExtra("name", audioName);
            String audioTitle = currentCursor.getString(COL_TITLE);
            intent.putExtra("title", audioTitle);

            startService(intent);
        }


    }
    // ///////////////////////////////////////////////////////////////////////////
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            currentCursor.moveToPosition(position);
            Intent intent = new Intent(MainActivity.this, PlayerService.class);
            currentCursor.moveToPosition(position);
            String audioPath = currentCursor.getString(COL_DATA);
            intent.putExtra("filename", audioPath);
            String audioName = currentCursor.getString(COL_NAME);
            intent.putExtra("name", audioName);
            String audioTitle = currentCursor.getString(COL_TITLE);
            intent.putExtra("title", audioTitle);
            position_h = position;
            startService(intent);
            ((Button)findViewById(R.id.stopPlayer)).setEnabled(true);
        }
    };
    // //////////////////////////////////////////////////////////////////////////////
    private void getAudioList()  {
        String[] proj = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };
        currentCursor = this.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);

        currentCursor.moveToFirst();
        //
        COL_ID = currentCursor.getColumnIndex(proj[0]);
        COL_ARTIST = currentCursor.getColumnIndex(proj[1]);
        COL_TITLE = currentCursor.getColumnIndex(proj[2]);
        COL_DATA = currentCursor.getColumnIndex(proj[3]);
        COL_NAME = currentCursor.getColumnIndex(proj[4]);
        COL_SIZE = currentCursor.getColumnIndex(proj[5]);
    }
    // ///////////////////////////////////////////////////////////////////////////////
    public class ListAdapter extends BaseAdapter {
        Context my_context;

        ListAdapter(Context context) {
            my_context = context;
        }

        @Override
        public Object getItem(int position) { return null;     }
        @Override
        public long getItemId(int position) { return 0;        }
        @Override
        public int getCount() { return currentCursor.getCount();  }
        //
        public View getView(int position, View convertView, ViewGroup parent) {
            ListViewHolder viewHolder;
            //
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater)
                        my_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row, parent, false);
                //
                viewHolder = new ListViewHolder();
                viewHolder.nameView = (TextView) convertView.findViewById(R.id.row_name);
                viewHolder.titleView = (TextView) convertView.findViewById(R.id.row_title);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ListViewHolder) convertView.getTag();
            }
            //
            currentCursor.moveToPosition(position);
            //
            viewHolder.nameView.setText(currentCursor.getString(COL_NAME));
            viewHolder.titleView.setText(currentCursor.getString(COL_TITLE));
            //
            return convertView;
        }
        //
        private class ListViewHolder {
            TextView titleView;
            TextView nameView;
        };
    }
}