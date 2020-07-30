package com.technology.givol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technology.givol.adapter.ContestFinalAdapter;
import com.technology.givol.model.ContestFinalModel;

import java.util.ArrayList;

public class ContestFinalActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_NAME = "GivolLoginPref";
    private final int[] android_image_urls = {R.drawable.pizza, R.drawable.pizza, R.drawable.pizza, R.drawable.pizza,
            R.drawable.pizza, R.drawable.pizza, R.drawable.cycle};
    String[] name_1 = new String[]{"Maria Mari", "Shoaib opu", "Zakir Rayhan", "Maria Mari", "Shoaib opu", "Zakir Rayhan", "Shoaib opu"};
    String[] name_2 = new String[]{"205", "155", "155", "155", "155", "155", "155"};
    String[] name_3 = new String[]{"1", "2", "3", "4", "5", "6", "7"};
    ImageView imageViewFinal;
    Button collect_prize_btn;
    String user_id_per, cat_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_final);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        user_id_per = settings.getString("USER_ID", "");
        System.out.println("USER_ID" + user_id_per);
        Bundle bundle2 = getIntent().getExtras();
        if (bundle2 != null) {
            cat_id = bundle2.getString("CATEGORY_ID");
            System.out.println("CAT_ID" + cat_id);
        }
        initViews();
    }

    private void initViews() {
        imageViewFinal = (ImageView) findViewById(R.id.imageViewFinal);
        imageViewFinal.setOnClickListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycer_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new ContestFinalAdapter(getApplicationContext(), prepareData()));
        collect_prize_btn = (Button) findViewById(R.id.collect_prize_btn);
        collect_prize_btn.setOnClickListener(this);
    }

    private ArrayList<ContestFinalModel> prepareData() {
        ArrayList<ContestFinalModel> android_version = new ArrayList<>();
        for (int i = 0; i < this.name_1.length; i++) {
            ContestFinalModel androidVersion = new ContestFinalModel();
            androidVersion.setSeries_id(name_3[i]);
            androidVersion.setName_1(this.name_1[i]);
            androidVersion.setName_2(this.name_2[i]);
            androidVersion.setAndroid_image_url(this.android_image_urls[i]);
            android_version.add(androidVersion);
        }
        return android_version;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewFinal:
                startActivity(new Intent(getApplicationContext(), PersonalInfoActivity.class));
                finish();
                break;
            case R.id.collect_prize_btn:
                startActivity(new Intent(getApplicationContext(), ContestResultActivity.class));
                finish();
                break;
        }
    }
}