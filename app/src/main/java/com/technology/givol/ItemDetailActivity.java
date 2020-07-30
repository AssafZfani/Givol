package com.technology.givol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ItemDetailActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView givol_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        givol_arrow = (ImageView) findViewById(R.id.givol_arrow);
        givol_arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.givol_arrow:
                Intent intent = new Intent(getApplicationContext(), NavActivity.class);
                startActivity(intent);
                break;
        }
    }
}
