package com.example.chethankumar.englishdictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chethankumar.englishdictionary.adapter.BookmarkAdapter;
import com.example.chethankumar.englishdictionary.adapter.HistoryAdapter;
import com.example.chethankumar.englishdictionary.database.DatabaseHelper;
import com.example.chethankumar.englishdictionary.model.History;

import java.util.Collections;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    DatabaseHelper db;
    ListView listView;
    List<History> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        db = new DatabaseHelper(getApplicationContext());
        listView = (ListView) findViewById(R.id.list);
        list = db.getAllHistory();
        Collections.reverse(list);
        if(list.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please search at least one word and check this page", Toast.LENGTH_SHORT).show();
        }
        listView.setAdapter(new HistoryAdapter(getApplicationContext(), list));
    }
}
