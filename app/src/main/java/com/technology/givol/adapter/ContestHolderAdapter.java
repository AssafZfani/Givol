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
import com.technology.givol.model.ContestHolderModel;

import java.util.ArrayList;

public class ContestHolderAdapter extends RecyclerView.Adapter<ContestHolderAdapter.ViewHolder> {

    /* renamed from: android reason: collision with root package name */
    private ArrayList<ContestHolderModel> f94android;
    private Context context;

    public ContestHolderAdapter(Context context2, ArrayList<ContestHolderModel> android2) {
        this.f94android = android2;
        this.context = context2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contest_holder_rec_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.name_1.setText(((ContestHolderModel) this.f94android.get(i)).getName_1());
        viewHolder.name_2.setText(((ContestHolderModel) this.f94android.get(i)).getName_2());
        Picasso.with(this.context).load(((ContestHolderModel) this.f94android.get(i)).getAndroid_image_url()).resize(240, 120).into(viewHolder.imageView);
    }

    public int getItemCount() {
        return this.f94android.size();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ImageView imageView;
        /* access modifiers changed from: private */
        public TextView name_1;
        /* access modifiers changed from: private */
        public TextView name_2;

        public ViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.contest_profile_image);
            this.name_1 = (TextView) view.findViewById(R.id.contest_name_1);
            this.name_2 = (TextView) view.findViewById(R.id.contest_name_2);
        }
    }
}
