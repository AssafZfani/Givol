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
import com.technology.givol.model.PersonalInfoModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PersonalExpiredAdapter extends RecyclerView.Adapter<PersonalExpiredAdapter.ViewHolder> {
    private final PersonalExpiredAdapter.OnItemClickListener listener;
    private ArrayList<PersonalInfoModel> listdata1;
    //private Activity activity;
    private Context context;

    public PersonalExpiredAdapter(Context context, ArrayList<PersonalInfoModel> listdata1, PersonalExpiredAdapter.OnItemClickListener listener) {
        this.listdata1 = listdata1;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PersonalExpiredAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.per_rec_item_1, parent, false);
        PersonalExpiredAdapter.ViewHolder vh = new PersonalExpiredAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PersonalExpiredAdapter.ViewHolder holder, int position) {
        holder.bind(listdata1.get(position), listener);
        //  holder.thumbnail.setImageResource(Integer.parseInt(listdata.get(position).getThubnail()));
        holder.expired_title.setText(listdata1.get(position).getExpired_title());
        holder.expired_is_winner.setText(listdata1.get(position).getExpired_is_winner());
        holder.expired_amount.setText(listdata1.get(position).getExpired_amount());
        startCountDown(listdata1.get(position).getExpired_end_date(), holder.expired_end_date);
        final PersonalExpiredAdapter.ViewHolder x = holder;
        Glide.with(context)
                .load(listdata1.get(position).getExpired_cover())
                .error(R.drawable.heart_pizza).into(holder.expired_cover_image);
        //holder.id.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return listdata1.size();
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
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        int hours = (int) (diff / (1000 * 60 * 60));
        int minutes = (int) (diff / (1000 * 60));
        int seconds = (int) (diff / (1000));
        new CountDownTimer(diff, 1000) {

            @Override

            public void onTick(long millisUntilFinished) {
                /*            converting the milliseconds into days, hours, minutes and seconds and displaying it in textviews             */

                time_counter.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) + ":" + (TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))) + ":"
                        + (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                // hours_1.setText((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished)))+"");

                // mins_1.setText((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)))+"");

                // seconds_1.setText((TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))+"");
            }

            @Override

            public void onFinish() {
                /*            clearing all fields and displaying countdown finished message             */

                time_counter.setText("END");
                // hours_1.setText("");
                // mins_1.setText("");
                // seconds_1.setText("");
            }
        }.start();
    }

    public interface OnItemClickListener {
        void onItemClick(PersonalInfoModel item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView expired_title, expired_is_winner, expired_amount, expired_end_date;
        private ImageView expired_cover_image;

        public ViewHolder(View v) {
            super(v);
            expired_cover_image = (ImageView) v.findViewById(R.id.expired_cover_image);
            expired_title = (TextView) v.findViewById(R.id.expired_title);
            expired_end_date = (TextView) v.findViewById(R.id.expired_end_date);
            expired_is_winner = (TextView) v.findViewById(R.id.expired_is_winner);
            expired_amount = (TextView) v.findViewById(R.id.expired_amount);


        }

        public void bind(final PersonalInfoModel item, final PersonalExpiredAdapter.OnItemClickListener listener) {
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
