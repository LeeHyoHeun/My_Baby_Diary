package com.example.haeju.tap1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by HAEJU on 2018-01-28.
 */

public class FragmentActivity1 extends Fragment {

    CustomAdapter adapter;
    ListView listView;

    String[] vdate;
    String[] vinfo;
    int[] vicon;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_photo, container, false);

        // Sample data
        vdate = new String[]{"12/1", "12/2", "12/3", "12/4"};
        vinfo = new String[]{"Apple Pie", "Banana Bread", "Cupcake", "Donut"};
        vicon = new int[]{R.raw.icon10, R.raw.icon11, R.raw.icon15, R.raw.icon16};

        listView = (ListView) view.findViewById(R.id.listView1);

        adapter = new CustomAdapter(getActivity(), vdate, vinfo, vicon);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(getActivity(), ClickActivity.class);
                intent.putExtra("date", vdate);
                intent.putExtra("info", vinfo);
                intent.putExtra("icon", vicon);
                intent.putExtra("position", position);
                startActivity(intent);

            }
        });
        return view;
    }
}


