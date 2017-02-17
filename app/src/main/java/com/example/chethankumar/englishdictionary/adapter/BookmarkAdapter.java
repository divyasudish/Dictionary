package com.example.chethankumar.englishdictionary.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.chethankumar.englishdictionary.R;
import com.example.chethankumar.englishdictionary.database.DatabaseHelper;
import com.example.chethankumar.englishdictionary.model.Bookmark;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by divyashreenair on 27/4/16.
 */
public class BookmarkAdapter extends BaseAdapter {

    private Context mContext;
    private List<Bookmark> mBookList;
    private Intent mIntent;
    DatabaseHelper db;
    private List<String> list;

    public BookmarkAdapter(Context context, List<Bookmark> list) {
        this.mBookList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return this.mBookList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.adapter_list, null);
            viewHolder = new ViewHolder();
            db = new DatabaseHelper(mContext);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.meaning = (TextView) convertView.findViewById(R.id.meaning);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("OpenDbListAdapter ", "List View Clicked");
            }
        });
        viewHolder.name.setText(mBookList.get(position).get_name());
        viewHolder.meaning.setText(mBookList.get(position).get_meaning());
        return convertView;
    }

    class ViewHolder {
        public TextView name;
        public TextView meaning;
    }
}