package com.technology.givol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.technology.givol.adapter.PersonalExpiredAdapter;
import com.technology.givol.model.PersonalInfoModel;

import java.util.ArrayList;

public class WinningContestActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "GivolLoginPref";
    String c_value;
    TextView city_name_txt;
    String country_name_policy;
    String cover_url = "http://findnearby.biz/contest-app-new/contest-cover-images/";
    ImageView givol_arrow_back;
    String policy_activty_no;
    String policy_contest_id;
    ProgressDialog progressDialog;
    RecyclerView recycer_view1;
    String result_img_url;
    String user_id_per;
    private PersonalExpiredAdapter expired_recyclerAdapter;
    private ArrayList<PersonalInfoModel> listdata1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_contest);

        this.user_id_per = getSharedPreferences("GivolLoginPref", 0).getString("USER_ID", "");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.country_name_policy = bundle.getString("COUNTRY_NAME");
            this.policy_activty_no = bundle.getString("ACTIVITY_NO");
            this.policy_contest_id = bundle.getString("CATEGORY_ID");
        }
        initViews();
    }

    private void initViews() {
        this.givol_arrow_back = (ImageView) findViewById(R.id.givol_arrow_back);
        this.city_name_txt = (TextView) findViewById(R.id.city_name_txt);
        this.city_name_txt.setText(this.country_name_policy);
        this.givol_arrow_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = "CATEGORY_ID";
                String str2 = "COUNTRY_NAME";
                if (WinningContestActivity.this.policy_activty_no.equalsIgnoreCase("2")) {
                    Intent intent1 = new Intent(WinningContestActivity.this.getApplicationContext(), NavItemDetailActivity.class);
                    intent1.putExtra(str2, WinningContestActivity.this.country_name_policy);
                    intent1.putExtra(str, WinningContestActivity.this.policy_contest_id);
                    WinningContestActivity.this.startActivity(intent1);
                    WinningContestActivity.this.finish();
                } else if (WinningContestActivity.this.policy_activty_no.equalsIgnoreCase("1")) {
                    Intent intent = new Intent(WinningContestActivity.this.getApplicationContext(), NavActivity.class);
                    intent.putExtra(str2, WinningContestActivity.this.country_name_policy);
                    intent.putExtra(str, WinningContestActivity.this.policy_contest_id);
                    WinningContestActivity.this.startActivity(intent);
                    WinningContestActivity.this.finish();
                }
            }
        });
    }

}
