package com.example.chethankumar.englishdictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chethankumar.englishdictionary.adapter.BookmarkAdapter;
import com.example.chethankumar.englishdictionary.database.DatabaseHelper;
import com.example.chethankumar.englishdictionary.model.Bookmark;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    ListView listView;
    List<Bookmark> list;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        db = new DatabaseHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.list);
        list = db.getAllBookmarks();
        if(list.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Please bookmark and check this page", Toast.LENGTH_SHORT).show();
        }
        listView.setAdapter(new BookmarkAdapter(getApplicationContext(), list));
    }
}
