package com.example.android.musicquiz;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int NOTES_REQUEST = 0;
    static final int INSTRUMENTS_REQUEST = 1;
    static final int VOICES_REQUEST = 2;
    static final int COMPOSERS_REQUEST = 3;
    int score = 0;
    int total = 4;
    int[] scores = {0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the Views that shows each category
        TextView notes = (TextView) findViewById(R.id.notes);
        TextView instruments = (TextView) findViewById(R.id.instruments);
        TextView voices = (TextView) findViewById(R.id.voices);
        TextView composers = (TextView) findViewById(R.id.composers);

        // Set a click listener on each View
        notes.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the notes View is clicked on.
            @Override
            public void onClick(View view) {
                Intent notesIntent = new Intent(MainActivity.this, NotesActivity.class);
                startActivityForResult(notesIntent, NOTES_REQUEST);
            }
        });

        instruments.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the instruments View is clicked on.
            @Override
            public void onClick(View view) {
                Intent instrumentsIntent = new Intent(MainActivity.this, InstrumentsActivity.class);
                startActivityForResult(instrumentsIntent, INSTRUMENTS_REQUEST);
            }
        });

        voices.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the voices View is clicked on.
            @Override
            public void onClick(View view) {
                Intent voicesIntent = new Intent(MainActivity.this, VoicesActivity.class);
                startActivityForResult(voicesIntent, VOICES_REQUEST);
            }
        });

        composers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the composers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent composersIntent = new Intent(MainActivity.this, ComposersActivity.class);
                startActivityForResult(composersIntent, COMPOSERS_REQUEST);
            }
        });
    }

    public void refreshScore() {
        Resources res = getResources();
        int tempScore = 0;

        for (int i=0; i < total; i++) {
            tempScore += scores[i];
        }
        Log.i("total", res.getString(R.string.current_score, tempScore, total));
        TextView currentScore = (TextView) findViewById(R.id.current_score);
        currentScore.setText(res.getString(R.string.current_score, tempScore, total));
    }

    public void restart(View view) {
        this.recreate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Resources res = getResources();
        boolean approved = false;
        TextView currentTest = new TextView(this);
        String currentText = "";

        if (requestCode == NOTES_REQUEST && resultCode == RESULT_OK) {
            currentTest = (TextView) findViewById(R.id.notes);
            currentText = res.getString(R.string.musical_notes);

            if (data.getExtras().getString("answer1").equals("correct") &&
                    data.getExtras().getString("answer2").equals("correct")) {
                approved = true;
            }
        } else if (requestCode == INSTRUMENTS_REQUEST && resultCode == RESULT_OK) {
            currentTest = (TextView) findViewById(R.id.instruments);
            currentText = res.getString(R.string.musical_instruments);
            if (data.getExtras().getString("answer1").equals("correct")) {
                approved = true;
            }
        } else if (requestCode == VOICES_REQUEST && resultCode == RESULT_OK) {
            currentTest = (TextView) findViewById(R.id.voices);
            currentText = res.getString(R.string.choir_voices);
            if (data.getExtras().getString("answer1").equals("correct")) {
                approved = true;
            }
        } else if (requestCode == COMPOSERS_REQUEST && resultCode == RESULT_OK) {
            currentTest = (TextView) findViewById(R.id.composers);
            currentText = res.getString(R.string.classical_composers);
            if (data.getExtras().getString("answer1").equals("correct")) {
                approved = true;
            }
        }

        if (approved) {
            String passed = res.getString(R.string.passed);
            String thisResult = res.getString(R.string.testresults, currentText, passed);
            currentTest.setText(thisResult);
            currentTest.setTextColor(Color.BLUE);
            scores[requestCode] = 1;
            Toast.makeText(this, res.getString(R.string.scoregood), Toast.LENGTH_SHORT).show();
        } else {
            String passed = res.getString(R.string.notpassed);
            String thisResult = res.getString(R.string.testresults, currentText, passed);
            currentTest.setText(thisResult);
            currentTest.setTextColor(Color.RED);
            scores[requestCode] = 0;
            Toast.makeText(this, res.getString(R.string.scorenot), Toast.LENGTH_SHORT).show();
        }

        refreshScore();
    }
}
