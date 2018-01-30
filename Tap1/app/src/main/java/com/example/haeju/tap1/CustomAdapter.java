package com.example.haeju.tap1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HAEJU on 2018-01-30.
 */

public class CustomAdapter extends BaseAdapter {

    Context my_context;
    String[] vdate;
    String[] vinfo;
    int[] vicon;

    CustomAdapter(Context context, String[] vdate, String[] vinfo, int[] vicon) {
        this.my_context = context;
        this.vdate = vdate;
        this.vinfo = vinfo;
        this.vicon = vicon;
    }
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
            viewHolder.numView = (TextView)convertView.findViewById(R.id.vnum);
            viewHolder.codeView = (TextView)convertView.findViewById(R.id.vcode);
            viewHolder.iconView = (ImageView)convertView.findViewById(R.id.vicon);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListViewHolder)convertView.getTag();
        }
        //
        viewHolder.numView.setText(vdate[position]);
        viewHolder.codeView.setText(vinfo[position]);
        viewHolder.iconView.setImageResource(vicon[position]);
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