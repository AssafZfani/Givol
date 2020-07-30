package com.technology.givol.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.technology.givol.R;
import com.technology.givol.model.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private ArrayList<Data> listdata;
    //private Activity activity;
    private Context context;

    public RecyclerAdapter(Context context, ArrayList<Data> listdata, OnItemClickListener listener) {
        this.listdata = listdata;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rec_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(listdata.get(position), listener);
        //  holder.thumbnail.setImageResource(Integer.parseInt(listdata.get(position).getThubnail()));
        holder.id.setText(listdata.get(position).getId());
        holder.judul.setText(listdata.get(position).getJudul());
        holder.harga.setText(listdata.get(position).getHarga());
        startCountDown(listdata.get(position).getEnd_date(), holder.time_text);
        final ViewHolder x = holder;
        Glide.with(context)
                .load(listdata.get(position).getThubnail())
                .error(R.drawable.heart_pizza).into(holder.thumbnail);
        //holder.id.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public void startCountDown(String contest_end_dt, final TextView time_counter) {
        Date end_date = null;
        try {
            end_date = new SimpleDateFormat("yyyy-MM-dd").parse(contest_end_dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date today = new Date();
        long diff = end_date.getTime() - today.getTime();
        //int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        int hours = (int) (diff / (1000 * 60 * 60)) + numOfDays * 24;
        int minutes = (int) (diff / (1000 * 60));
        int seconds = (int) (diff / (1000));
        new CountDownTimer(diff, 1000) {

            @Override

            public void onTick(long millisUntilFinished) {
                /*            converting the milliseconds into days, hours, minutes and seconds and displaying it in textviews             */

//                time_counter.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))+":"+(TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished)))+":"
//                        +(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)))+":"+(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));


                time_counter.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) * 24 + (TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))) + ":"
                        + (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                // hours_1.setText((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished)))+"");

                // mins_1.setText((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)))+"");

                // seconds_1.setText((TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))+"");
            }

            @Override

            public void onFinish() {
                /*            clearing all fields and displaying countdown finished message             */

                time_counter.setText("Finished");
                // hours_1.setText("");
                // mins_1.setText("");
                // seconds_1.setText("");
            }
        }.start();
    }

    public interface OnItemClickListener {
        void onItemClick(Data item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id, judul, harga, time_text;
        private ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);

            id = (TextView) v.findViewById(R.id.amount);
            judul = (TextView) v.findViewById(R.id.participants);
            harga = (TextView) v.findViewById(R.id.title);
            thumbnail = (ImageView) v.findViewById(R.id.image);
            time_text = (TextView) v.findViewById(R.id.time_text);
        }

        public void bind(final Data item, final OnItemClickListener listener) {
            // name.setText(item.name);
            //  Picasso.with(itemView.getContext()).load(item.imageUrl).into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }


}
