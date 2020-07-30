package com.technology.givol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.technology.givol.NavActivity;
import com.technology.givol.R;
import com.technology.givol.model.RecDataModel;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public OnItemClickListener listener;
    Context context;
    //  ArrayList personImages;
    // ArrayList personNames;
    List<RecDataModel> recDataModels;
    private NavActivity activity;

    public DataAdapter(Context context2, List<RecDataModel> recDataModels2) {
        this.context = context2;
        this.recDataModels = recDataModels2;
        // this.personImages = personImages2;
    }

    public void setOnItemClickListener(OnItemClickListener listener2) {
        this.listener = listener2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        RecDataModel recDataModel = (RecDataModel) this.recDataModels.get(i);
        //  viewHolder.image.setImageResource(Integer.parseInt(recDataModel.getAndroid_image_url()));
        Picasso.with(activity)
                .load(recDataModel.getAndroid_image_url())
                .into(viewHolder.image);
        viewHolder.amount.setText(recDataModel.getAmount());
        viewHolder.participants.setText(recDataModel.getParticipants());
        viewHolder.title.setText(recDataModel.getTitle());
    }

    public int getItemCount() {
        return this.recDataModels.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        //  public ImageView imageview;
        /* access modifiers changed from: private */
        public TextView amount;
        ImageView image;
        private TextView participants;
        private TextView title;

        public ViewHolder(final View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            amount = (TextView) itemView.findViewById(R.id.amount);
            participants = (TextView) itemView.findViewById(R.id.participants);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (DataAdapter.this.listener != null) {
                        int position = ViewHolder.this.getAdapterPosition();
                        if (position != -1) {
                            DataAdapter.this.listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }
}
