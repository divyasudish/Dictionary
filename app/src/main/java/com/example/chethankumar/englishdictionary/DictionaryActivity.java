package com.example.chethankumar.englishdictionary;

/**
 * Created by Chethan Kumar on 2017-02-04.
 */

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chethankumar.englishdictionary.database.DatabaseHelper;
import com.example.chethankumar.englishdictionary.model.Bookmark;
import com.example.chethankumar.englishdictionary.model.History;

import java.util.List;
import java.util.Locale;
public class DictionaryActivity extends AppCompatActivity {
    private TextView wordMeaning;
    private TextToSpeech convertToSpeech;
    private ImageView imageBut;
    private Boolean clicked;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int dictionaryId = bundle.getInt("DICTIONARY_ID");
        String dictionaryword = bundle.getString("DICTIONARY_WORD");
        int id = dictionaryId + 1;
        dictionaryId ++;
        final TextView word = (TextView)findViewById(R.id.word);
        wordMeaning = (TextView)findViewById(R.id.dictionary);
        Button textToSpeech = (Button)findViewById(R.id.button);
        imageBut = (ImageView) findViewById(R.id.bookmarkImage);
        DbBackend dbBackend = new DbBackend(DictionaryActivity.this);
        db = new DatabaseHelper(getApplicationContext());
        // QuizObject allQuizQuestions = dbBackend.getQuizById(id);
        QuizObject allQuizQuestions = dbBackend.getQuizByWord(dictionaryword);
        word.setText(allQuizQuestions.getWord());
        wordMeaning.setText(allQuizQuestions.getDefinition());
        textToSpeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String convertTextToSpeech = wordMeaning.getText().toString();
                convertToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            convertToSpeech.setLanguage(Locale.US);
                            convertToSpeech.speak(convertTextToSpeech, TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                });
            }
        });
        List<Bookmark> allTags = db.getAllBookmarks();
        System.out.println("Size of " + allTags.size());
        for (Bookmark tag : allTags) {
            if (word.getText().toString().trim().equals(tag.get_name())) {
                //Toast.makeText(getApplicationContext(), "Name or Username already exist", Toast.LENGTH_LONG).show();
                clicked = new Boolean(true);
                System.out.println("Size of  inside word" + allTags.size());
                break;
            }
            else {
                clicked = new Boolean(false);
                System.out.println("Size of else inside" + allTags.size());
            }
        }
        if(allTags.isEmpty()) {
            System.out.println("Size of empty" + allTags.size());
            clicked = new Boolean(false);
        }
        imageBut.setTag(clicked);
        System.out.println("Boolean value of but " + (Boolean)imageBut.getTag());
        if( ((Boolean)imageBut.getTag())==false ){
            imageBut.setImageResource(R.drawable.ic_bookmark_border);
            //imageBut.setTag(new Boolean(true));
        }
        else if((Boolean)imageBut.getTag() == true) {
            imageBut.setImageResource(R.drawable.ic_bookmark);
            //imageBut.setTag(new Boolean(false));
        }

        imageBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside click " + (Boolean)imageBut.getTag());
                if( ((Boolean)imageBut.getTag())==false ){
                    imageBut.setImageResource(R.drawable.ic_bookmark);
                    imageBut.setTag(new Boolean(true));
                    Toast.makeText(getApplicationContext(), "Bookmark added", Toast.LENGTH_SHORT).show();
                    db.createBookmark(new Bookmark(word.getText().toString().trim(), wordMeaning.getText().toString().trim()));

                }
                else if((Boolean)imageBut.getTag() == true) {
                    imageBut.setImageResource(R.drawable.ic_bookmark_border);
                    imageBut.setTag(new Boolean(false));
                    Toast.makeText(getApplicationContext(), "Bookmark removed", Toast.LENGTH_SHORT).show();
                    db.deleteBook(word.getText().toString().trim());
                }
            }
        });
        db.createHistory(new History(word.getText().toString().trim(), wordMeaning.getText().toString().trim()));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dictionary, menu);
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
    @Override
    protected void onPause() {
        if(convertToSpeech != null){
            convertToSpeech.stop();
            convertToSpeech.shutdown();
        }
        super.onPause();
    }
}