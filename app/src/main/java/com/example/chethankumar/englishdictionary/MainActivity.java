package com.example.chethankumar.englishdictionary;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    TextView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        testView =( TextView )findViewById(R.id.zaire_text_view);
    }

    public void showZaire( View view)
    {
        String button_test;
        button_test =((Button) view).getText().toString();
        if (button_test.equals("Text To Speech"))
        {
            Intent intent1= new Intent(this,SecondActivity.class);
            startActivity(intent1 );
        }

        else if (button_test.equals("Get The Meaning Of A Word"))
        {
            Intent intent= new Intent(this,ThirdActivity.class);
            startActivity(intent );
        }

        else if (button_test.equals("Speech To Text"))
        {
            Intent intent2= new Intent(this,FourthActivity.class);
            startActivity(intent2 );
        }
        else if (button_test.equals("Bookmarks"))
        {
            Intent intent2= new Intent(this,BookmarkActivity.class);
            startActivity(intent2 );
        }
        else if (button_test.equals("History"))
        {
            Intent intent2= new Intent(this,HistoryActivity.class);
            startActivity(intent2 );
        }

        //String message="this is my first apps";
        //testView.setText(message);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}