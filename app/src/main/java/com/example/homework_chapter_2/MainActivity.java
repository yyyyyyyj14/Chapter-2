package com.example.homework_chapter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_exercise_1).setOnClickListener(this);
        findViewById(R.id.btn_exercise_2).setOnClickListener(this);
        findViewById(R.id.btn_exercise_3).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_exercise_1:
                startActivity(new Intent(this, ExerciseOneActivity.class));
                break;
            case R.id.btn_exercise_2:
                startActivity(new Intent(this, ExerciseTwoActivity.class));
                break;
            case R.id.btn_exercise_3:
                startActivity(new Intent(this, ExerciseThreeActivity.class));
                break;
        }
    }
}
