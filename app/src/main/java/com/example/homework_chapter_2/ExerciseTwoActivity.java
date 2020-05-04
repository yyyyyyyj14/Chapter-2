package com.example.homework_chapter_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExerciseTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_2);
        int count=this.getAllChildViews(this.getWindow().getDecorView()).size();//计算顶级view包含view个数
        TextView tv=findViewById(R.id.tv_center);
        tv.setText(count+"");
    }

    private List<View> getAllChildViews(View view) {//计算顶级view包含view个数
        List<View> child = new ArrayList<View>();
        if (view instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View viewchild = vp.getChildAt(i);
                child.add(viewchild);
                child.addAll(getAllChildViews(viewchild));
            }
        }
        return child;
    }
}
