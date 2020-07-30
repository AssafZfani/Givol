package com.technology.givol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ContestResultActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_result);
        imageViewResult = (ImageView) findViewById(R.id.imageViewResult);
        imageViewResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewResult:
                startActivity(new Intent(getApplicationContext(), ContestFinalActivity.class));
                finish();
                break;
        }
    }
}
