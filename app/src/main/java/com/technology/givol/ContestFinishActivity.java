package com.technology.givol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.technology.givol.adapter.PersonalExpiredAdapter;
import com.technology.givol.model.PersonalInfoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContestFinishActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "GivolLoginPref";
    /* access modifiers changed from: private */
    public PersonalExpiredAdapter expired_recyclerAdapter;
    /* access modifiers changed from: private */
    public ArrayList<PersonalInfoModel> listdata1;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_finish);
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
        this.recycer_view1 = (RecyclerView) findViewById(R.id.recycer_view1);
        this.recycer_view1.setHasFixedSize(true);
        this.recycer_view1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadContestList(this.user_id_per);
        this.givol_arrow_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = "CATEGORY_ID";
                String str2 = "COUNTRY_NAME";
                if (policy_activty_no.equalsIgnoreCase("2")) {
                    Intent intent1 = new Intent(getApplicationContext(), NavItemDetailActivity.class);
                    intent1.putExtra("COUNTRY_NAME", country_name_policy);
                    intent1.putExtra("CATEGORY_ID", policy_contest_id);
                    startActivity(intent1);
                    finish();
                } else if (policy_activty_no.equalsIgnoreCase("1")) {
                    Intent intent = new Intent(getApplicationContext(), NavActivity.class);
                    intent.putExtra("COUNTRY_NAME", country_name_policy);
                    intent.putExtra("CATEGORY_ID", policy_contest_id);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadContestList(final String user_id_per1) {

        //listdata=new ArrayList<>();
        listdata1 = new ArrayList<>();
        progressDialog = new ProgressDialog(ContestFinishActivity.this, R.style.CustomDialog);
        progressDialog.setMessage("Data Loading..");
        progressDialog.show();
        //getting the progressbar
        //final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        // progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        String url = "http://findnearby.biz/contest-app-new/apis/user-detail.php?ukey=zcdVJ2VRKAEHNBK2B8JqMyITugtRUFqz&user_id=" + user_id_per1;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        // progressBar.setVisibility(View.INVISIBLE);


                        try {
                            progressDialog.dismiss();
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            String success = obj.getString("success");
                            if (success.equalsIgnoreCase("true")) {
                                JSONObject data = obj.getJSONObject("data");
                                JSONObject jsonUSER = data.getJSONObject("user");
                                String user_id = jsonUSER.getString("id");
                                String first_name = jsonUSER.getString("first_name");
                                String email = jsonUSER.getString("email");
                                String password = jsonUSER.getString("password");
                                String address = jsonUSER.getString("address");
                                String phone_no = jsonUSER.getString("phone");
                                String birth_date = jsonUSER.getString("bday");
                                String total_earn = jsonUSER.getString("total_earn");

//                                user_name.setText(first_name);
//
//                                user_email.setText(email);
//                                pass_per_txt.setText(password);
//                                pass_per_txt.setTransformationMethod(new AsteriskPasswordTransformationMethod());
//                                per_address.setText(address);
//                                per_phone_no.setText(phone_no);
//                                per_date_birth.setText(birth_date);
//                                per_total_earn.setText(total_earn);
                                //we have the array named hero inside the object
                                //so here we are getting that json array
//                                JSONArray active_contest = data.getJSONArray("active_contests");
//
//                                //now looping through all the elements of the json array
//                                for (int i = 0; i < active_contest.length(); i++) {
//                                    //getting the json object of the particular index inside the array
//                                    JSONObject child_data = active_contest.getJSONObject(i);
//                                    PersonalInfoModel item = new PersonalInfoModel();
//                                    item.setActive_contest_id(child_data.getString("id"));
//                                    item.setActive_title(child_data.getString("title"));
//                                    item.setActive_category(child_data.getString("category"));
//                                    item.setActive_amount(child_data.getString("amount"));
//                                    String cover=child_data.getString("cover");
//                                    if(cover.equalsIgnoreCase("null"))
//                                    {
//
//                                        // result_img_url=cover_url+"f78b5107ee228fe3797589c538b3d741.png";
//                                        // item.setActive_cover(result_img_url);
//                                    }
//                                    else if(!cover.equalsIgnoreCase("null"))
//                                    {
//                                        result_img_url=cover_url+cover;
//                                        item.setActive_cover(result_img_url);
//                                    }
//
//                                    item.setActive_end_date(child_data.getString("end_date"));
//                                    listdata.add(item);
//                                    //  recyclerAdapter.notifyDataSetChanged();
//                                }
                                JSONArray expired_contest = data.getJSONArray("expired_contests");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < expired_contest.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject child_data1 = expired_contest.getJSONObject(i);
                                    PersonalInfoModel item1 = new PersonalInfoModel();
                                    item1.setExpired_contest_id(child_data1.getString("id"));
                                    item1.setExpired_title(child_data1.getString("title"));
                                    item1.setExpired_is_winner(child_data1.getBoolean("is_winner") ? "Winner" : "No Win");
                                    item1.setExpired_amount(child_data1.getString("amount"));
                                    item1.setExpired_cover(cover_url + child_data1.getString("cover"));
                                    item1.setExpired_end_date(child_data1.getString("end_date"));
                                    listdata1.add(item1);
                                    //  recyclerAdapter.notifyDataSetChanged();
                                }


                            }
//                            else if(success.equalsIgnoreCase("false"))
//                            {
//                                Toast.makeText(PersonalInfoActivity.this,obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            }
//                            active_recyclerAdapter = new PersonalInfoAdapter(PersonalInfoActivity.this, listdata, new PersonalInfoAdapter.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(PersonalInfoModel item) {
//                                    String cat_id=item.getActive_title();
//                                    Intent intent=new Intent(PersonalInfoActivity.this,ContestHolderActivity.class);
//                                    intent.putExtra("CATEGORY_ID",cat_id);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            });
                            expired_recyclerAdapter = new PersonalExpiredAdapter(ContestFinishActivity.this, listdata1, new PersonalExpiredAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(PersonalInfoModel item) {
                                    String cat_id = item.getExpired_title();
                                    Intent intent = new Intent(ContestFinishActivity.this, ContestFinalActivity.class);
                                    intent.putExtra("CATEGORY_ID", cat_id);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            // recyclerView.setAdapter(active_recyclerAdapter);
                            recycer_view1.setAdapter(expired_recyclerAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(ContestFinishActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

        //creating a request queue
        Volley.newRequestQueue(ContestFinishActivity.this).add(stringRequest);

        //adding the string request to request queue
        // requestQueue.add(stringRequest);
    }
}
