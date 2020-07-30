package com.technology.givol.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.technology.givol.R;
import com.technology.givol.model.Data;

import java.util.List;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {
    private List<Data> listdata;
    private Activity activity;
    private Context context;

    public RecyclerAdapter1(Activity activity, List<Data> listdata) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public RecyclerAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mImage.setImageResource(listdata.get(position).getThubnail());
        holder.id.setText(listdata.get(position).getId());
        holder.judul.setText(listdata.get(position).getJudul());
        holder.harga.setText(listdata.get(position).getHarga());
        final ViewHolder x = holder;
        Picasso.with(activity)
                .load(listdata.get(position).getThubnail())
                .into(holder.thumbnail);
        //holder.id.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id, judul, harga;
        private ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);

            id = (TextView) v.findViewById(R.id.amount);
            judul = (TextView) v.findViewById(R.id.participants);
            harga = (TextView) v.findViewById(R.id.title);
            thumbnail = (ImageView) v.findViewById(R.id.image);
        }
    }

}
