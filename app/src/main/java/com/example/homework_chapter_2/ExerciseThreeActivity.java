package com.example.homework_chapter_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

public class ExerciseThreeActivity extends AppCompatActivity implements MessageAdapter.ListItemClickListener{

    private static final String TAG = "Exercise_3";
    private static final int NUM_LIST_ITEMS = 100;

    private MessageAdapter mAdapter;
    private RecyclerView mNumbersListView;

    private Toast mToast;
    private List<MessageBean> messages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_3);
        mNumbersListView = findViewById(R.id.rv_numbers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNumbersListView.setLayoutManager(layoutManager);

        mNumbersListView.setHasFixedSize(true);

        mAdapter = new MessageAdapter(this,this);//创建适配器

        mNumbersListView.setAdapter(mAdapter);

        InputStream assetInput = null;
        try {
            assetInput = getAssets().open("data.xml");
            messages = PullParser.pull2xml(assetInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {//点击事件实现
        Log.d(TAG, "onListItemClick: ");
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "行 #" + clickedItemIndex + " 被点击";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();

        Intent intent = new Intent(this, MessageDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", messages.get(clickedItemIndex).getTitle());
        bundle.putString("tag", messages.get(clickedItemIndex).getHashTag());
        bundle.putInt("line", clickedItemIndex);
        intent.putExtra("user", bundle);
        startActivity(intent);
    }
}
