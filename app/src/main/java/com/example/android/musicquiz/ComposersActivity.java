package com.example.android.musicquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class ComposersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composers);

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

        CheckBox option1 = (CheckBox) findViewById(R.id.check1);
        CheckBox option2 = (CheckBox) findViewById(R.id.check2);
        CheckBox option3 = (CheckBox) findViewById(R.id.check3);
        CheckBox option4 = (CheckBox) findViewById(R.id.check4);
        CheckBox option5 = (CheckBox) findViewById(R.id.check5);
        if (!option1.isChecked() && option2.isChecked() &&
                option3.isChecked() && !option4.isChecked() && option5.isChecked()) {
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
