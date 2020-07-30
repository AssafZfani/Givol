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

public class PersonalInfoAdapter extends RecyclerView.Adapter<PersonalInfoAdapter.ViewHolder> {
    private final PersonalInfoAdapter.OnItemClickListener listener;
    private ArrayList<PersonalInfoModel> listdata;
    //private Activity activity;
    private Context context;

    public PersonalInfoAdapter(Context context, ArrayList<PersonalInfoModel> listdata, PersonalInfoAdapter.OnItemClickListener listener) {
        this.listdata = listdata;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PersonalInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.per_rec_item, parent, false);
        PersonalInfoAdapter.ViewHolder vh = new PersonalInfoAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PersonalInfoAdapter.ViewHolder holder, int position) {
        holder.bind(listdata.get(position), listener);
        //  holder.thumbnail.setImageResource(Integer.parseInt(listdata.get(position).getThubnail()));
        holder.active_title.setText(listdata.get(position).getActive_title());
        holder.active_category.setText(listdata.get(position).getActive_category());
        holder.active_amount.setText(listdata.get(position).getActive_amount());
        startCountDown(listdata.get(position).getActive_end_date(), holder.active_end_date);

        final PersonalInfoAdapter.ViewHolder x = holder;

        Glide.with(context)
                .load(listdata.get(position).getActive_cover())
                .error(R.drawable.heart_pizza).into(holder.active_cover_image);
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

                time_counter.setText("Finished");
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

        private TextView active_title, active_category, active_amount, active_end_date;
        private ImageView active_cover_image;

        public ViewHolder(View v) {
            super(v);
            active_cover_image = (ImageView) v.findViewById(R.id.active_cover_image);
            active_title = (TextView) v.findViewById(R.id.active_title);
            active_category = (TextView) v.findViewById(R.id.active_category);
            active_amount = (TextView) v.findViewById(R.id.active_amount);

            active_end_date = (TextView) v.findViewById(R.id.active_end_date);
        }

        public void bind(final PersonalInfoModel item, final PersonalInfoAdapter.OnItemClickListener listener) {
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
