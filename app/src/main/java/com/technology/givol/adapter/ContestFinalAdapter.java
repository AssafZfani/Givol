package com.technology.givol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.technology.givol.R;
import com.technology.givol.model.ContestFinalModel;

import java.util.ArrayList;

public class ContestFinalAdapter extends RecyclerView.Adapter<ContestFinalAdapter.ViewHolder> {

    /* renamed from: android reason: collision with root package name */
    private ArrayList<ContestFinalModel> android;
    private Context context;

    public ContestFinalAdapter(Context context2, ArrayList<ContestFinalModel> android2) {
        this.android = android2;
        this.context = context2;
    }

    public ContestFinalAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ContestFinalAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contest_final_rec_item, viewGroup, false));
    }

    public void onBindViewHolder(ContestFinalAdapter.ViewHolder viewHolder, int i) {
        viewHolder.series_text.setText(android.get(i).getSeries_id());
        viewHolder.name_1.setText(android.get(i).getName_1());
        viewHolder.name_2.setText(android.get(i).getName_2());
        Picasso.with(this.context).load(android.get(i).getAndroid_image_url()).resize(240, 120).into(viewHolder.imageView);
    }

    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView series_text;
        public ImageView imageView;
        /* access modifiers changed from: private */
        public TextView name_1;
        /* access modifiers changed from: private */
        public TextView name_2;

        public ViewHolder(View view) {
            super(view);
            series_text = (TextView) view.findViewById(R.id.series_text);
            imageView = (ImageView) view.findViewById(R.id.contest_profile_image);
            name_1 = (TextView) view.findViewById(R.id.contest_name_1);
            name_2 = (TextView) view.findViewById(R.id.contest_name_2);
        }
    }
}
