package com.technology.givol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technology.givol.adapter.ContestHolderAdapter;
import com.technology.givol.model.ContestHolderModel;

import java.util.ArrayList;

public class ContestHolderActivity extends AppCompatActivity implements View.OnClickListener {
    private final int[] android_image_urls = {R.drawable.pizza, R.drawable.pizza, R.drawable.pizza, R.drawable.pizza,
            R.drawable.pizza, R.drawable.pizza};
    String[] name_1 = new String[]{"Maria Mari", "Shoaib opu", "Zakir Rayhan", "Maria Mari", "Shoaib opu", "Zakir Rayhan"};
    String[] name_2 = new String[]{"155", "155", "155", "155", "155", "155"};
    ImageView imageViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_holder);
        initViews();

    }

    private void initViews() {
        imageViewHolder = (ImageView) findViewById(R.id.imageViewHolder);
        imageViewHolder.setOnClickListener(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycer_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new ContestHolderAdapter(getApplicationContext(), prepareData()));
    }

    private ArrayList<ContestHolderModel> prepareData() {
        ArrayList<ContestHolderModel> android_version = new ArrayList<>();
        for (int i = 0; i < this.name_1.length; i++) {
            ContestHolderModel androidVersion = new ContestHolderModel();
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
            case R.id.imageViewHolder:
                startActivity(new Intent(getApplicationContext(), PersonalInfoActivity.class));
                finish();
                break;
        }
    }
}
