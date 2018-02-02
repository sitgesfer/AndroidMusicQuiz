package com.example.android.musicquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

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

        CheckBox option1 = (CheckBox) findViewById(R.id.checkBox);
        CheckBox option2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox option3 = (CheckBox) findViewById(R.id.checkBox3);
        if (option1.isChecked() && option2.isChecked() && !option3.isChecked()) {
            result = "correct";
        }
        return result;
    }

    public String checkAnswer2() {
        String result = "incorrect";

        RadioButton option2 = (RadioButton) findViewById(R.id.radio2);

        if (option2.isChecked()) {
            result = "correct";
        }
        return result;
    }

    @Override
    public void finish() {
        // Prepare data intent
        String answer1 = checkAnswer1();
        String answer2 = checkAnswer2();
        Intent data = new Intent();
        data.putExtra("answer1", answer1);
        data.putExtra("answer2", answer2);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }
}
