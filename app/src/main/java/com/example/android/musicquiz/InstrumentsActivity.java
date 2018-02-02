package com.example.android.musicquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InstrumentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruments);

        Button sendAnswer = findViewById(R.id.answer_button);

        // Set a click listener on submit answer button
        sendAnswer.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public String checkAnswer1() {
        String result = "incorrect";

        EditText answerInput = (EditText) findViewById(R.id.instrument_name);
        String answerText = answerInput.getText().toString().toLowerCase();
        Log.i("Instruments", answerText);

        if (answerText.equals("oboe")) {
            result = "correct";
        }
        return result;
    }

    @Override
    public void finish() {
        // Prepare data intent
        String answer1 = checkAnswer1();
        Intent data = new Intent();
        data.putExtra("answer1", answer1);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }
}
