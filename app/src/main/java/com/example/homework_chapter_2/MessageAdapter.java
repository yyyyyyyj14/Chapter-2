package com.example.homework_chapter_2;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.List;

/**
 * 消息列表适配器
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.NumberViewHolder>{
    private static final String TAG = "Adapter";

    private int mNumberItems;

    private final ListItemClickListener mOnClickListener;

    private static int viewHolderCount;

    private List<MessageBean> messages;

    public MessageAdapter(ListItemClickListener listener, Context context) {

        mOnClickListener = listener;
        viewHolderCount = 0;
        try {
            InputStream assetInput = context.getAssets().open("data.xml");
            messages = PullParser.pull2xml(assetInput);
            mNumberItems = messages.size();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_item_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        numberViewHolder.bind(position);
        numberViewHolder.itemView.setBackgroundColor(Color.rgb(22,25,32));
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView viewHolderIndex;
        private final TextView listItemNumberView;
        private final TextView TimeView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_content);
            TimeView = (TextView) itemView.findViewById(R.id.tv_time);
            itemView.setOnClickListener(this);
            listItemNumberView.setTextColor(Color.rgb(255,255,255));
            viewHolderIndex.setTextColor(Color.rgb(128,128,128));
            TimeView.setTextColor(Color.rgb(128,128,128));

        }

        public void bind(int position) {
            listItemNumberView.setText(messages.get(position).getTitle());
            viewHolderIndex.setText(messages.get(position).getHashTag());
            TimeView.setText(messages.get(position).getTime());

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
