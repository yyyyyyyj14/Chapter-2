package com.example.homework_chapter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MessageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_3_detail);
        TextView mUserContent = findViewById(R.id.tv_num);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle user = extras.getBundle("user");
            if (user != null) {
                String title = user.getString("title");
                String tag = user.getString("tag");
                int line = user.getInt("line");
                mUserContent.setText(getResources().getString(R.string.user_content, title, tag, line));
            }
        }

    }
}
