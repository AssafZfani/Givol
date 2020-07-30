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
import com.technology.givol.adapter.PersonalInfoAdapter;
import com.technology.givol.model.PersonalInfoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActiveContestActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "GivolLoginPref";
    /* access modifiers changed from: private */
    public PersonalInfoAdapter active_recyclerAdapter;
    /* access modifiers changed from: private */
    public ArrayList<PersonalInfoModel> listdata;
    String activ_value;
    Bundle bundle;
    String c_value;
    TextView city_name_txt;
    String country_name_det;
    String country_name_policy;
    String cover_url = "http://findnearby.biz/contest-app-new/contest-cover-images/";
    ImageView givol_arrow_back;
    String policy_activty_no;
    String policy_contest_id;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    String result_img_url;
    TextView sold_no_found;
    String user_id_per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_contest);

        this.user_id_per = getSharedPreferences("GivolLoginPref", 0).getString("USER_ID", "");
        Bundle bundle2 = getIntent().getExtras();
        if (bundle2 != null) {
            this.country_name_policy = bundle2.getString("COUNTRY_NAME");
            this.policy_activty_no = bundle2.getString("ACTIVITY_NO");
            this.policy_contest_id = bundle2.getString("CATEGORY_ID");
        }
        initViews();
    }

    private void initViews() {
        this.givol_arrow_back = (ImageView) findViewById(R.id.givol_arrow_back);
        this.city_name_txt = (TextView) findViewById(R.id.city_name_txt);
        this.city_name_txt.setText(this.country_name_policy);
        this.sold_no_found = (TextView) findViewById(R.id.sold_no_found);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycer_view);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        loadContestList(this.user_id_per);
        this.givol_arrow_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = "CATEGORY_ID";
                String str2 = "COUNTRY_NAME";
                if (ActiveContestActivity.this.policy_activty_no.equalsIgnoreCase("2")) {
                    Intent intent1 = new Intent(ActiveContestActivity.this.getApplicationContext(), NavItemDetailActivity.class);
                    intent1.putExtra(str2, ActiveContestActivity.this.country_name_policy);
                    intent1.putExtra(str, ActiveContestActivity.this.policy_contest_id);
                    ActiveContestActivity.this.startActivity(intent1);
                    ActiveContestActivity.this.finish();
                } else if (ActiveContestActivity.this.policy_activty_no.equalsIgnoreCase("1")) {
                    Intent intent = new Intent(ActiveContestActivity.this.getApplicationContext(), NavActivity.class);
                    intent.putExtra(str2, ActiveContestActivity.this.country_name_policy);
                    intent.putExtra(str, ActiveContestActivity.this.policy_contest_id);
                    ActiveContestActivity.this.startActivity(intent);
                    ActiveContestActivity.this.finish();
                }
            }
        });
    }

    private void loadContestList(final String user_id_per1) {

        listdata = new ArrayList<>();

        progressDialog = new ProgressDialog(ActiveContestActivity.this, R.style.CustomDialog);
        progressDialog.setMessage("Data Loading..");
        progressDialog.show();
        sold_no_found.setVisibility(View.GONE);
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
                                JSONArray active_contest = data.getJSONArray("active_contests");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < active_contest.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject child_data = active_contest.getJSONObject(i);
                                    PersonalInfoModel item = new PersonalInfoModel();
                                    item.setActive_contest_id(child_data.getString("id"));
                                    item.setActive_title(child_data.getString("title"));
                                    item.setActive_category(child_data.getString("category"));
                                    item.setActive_amount(child_data.getString("amount"));
                                    String cover = child_data.getString("cover");
                                    if (cover.equalsIgnoreCase("null")) {

                                        // result_img_url=cover_url+"f78b5107ee228fe3797589c538b3d741.png";
                                        // item.setActive_cover(result_img_url);
                                    } else if (!cover.equalsIgnoreCase("null")) {
                                        result_img_url = cover_url + cover;
                                        item.setActive_cover(result_img_url);
                                    }

                                    item.setActive_end_date(child_data.getString("end_date"));
                                    listdata.add(item);
                                    //  recyclerAdapter.notifyDataSetChanged();
                                }
//                                JSONArray expired_contest = data.getJSONArray("expired_contests");
//
//                                //now looping through all the elements of the json array
//                                for (int i = 0; i < expired_contest.length(); i++) {
//                                    //getting the json object of the particular index inside the array
//                                    JSONObject child_data1 = expired_contest.getJSONObject(i);
//                                    PersonalInfoModel item1 = new PersonalInfoModel();
//                                    item1.setExpired_contest_id(child_data1.getString("id"));
//                                    item1.setExpired_title(child_data1.getString("title"));
//                                    item1.setExpired_is_winner( child_data1.getBoolean("is_winner") ?"Winner":"No Win");
//                                    item1.setExpired_amount(child_data1.getString("amount"));
//                                    item1.setExpired_cover(cover_url+child_data1.getString("cover"));
//                                    item1.setExpired_end_date(child_data1.getString("end_date"));
//                                    listdata1.add(item1);
//                                    //  recyclerAdapter.notifyDataSetChanged();
//                                }


                            }
//                            else if(success.equalsIgnoreCase("false"))
//                            {
//                                Toast.makeText(PersonalInfoActivity.this,obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            }
                            else {
                                if (success.equalsIgnoreCase("false")) {
                                    ActiveContestActivity.this.sold_no_found.setVisibility(View.VISIBLE);
                                    ActiveContestActivity.this.sold_no_found.setText("No Data For this Contest");
                                }
                            }
                            active_recyclerAdapter = new PersonalInfoAdapter(ActiveContestActivity.this, listdata, new PersonalInfoAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(PersonalInfoModel item) {
                                    String cat_id = item.getActive_title();
                                    Intent intent = new Intent(ActiveContestActivity.this, ContestHolderActivity.class);
                                    intent.putExtra("CATEGORY_ID", cat_id);
                                    startActivity(intent);
                                    finish();
                                }
                            });
//                            expired_recyclerAdapter = new PersonalExpiredAdapter(PersonalInfoActivity.this, listdata1, new PersonalExpiredAdapter.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(PersonalInfoModel item) {
//                                    String cat_id=item.getExpired_title();
//                                    Intent intent=new Intent(PersonalInfoActivity.this,ContestFinalActivity.class);
//                                    intent.putExtra("CATEGORY_ID",cat_id);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                            });

                            recyclerView.setAdapter(active_recyclerAdapter);
                            //recycer_view1.setAdapter(expired_recyclerAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(ActiveContestActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

        //creating a request queue
        Volley.newRequestQueue(ActiveContestActivity.this).add(stringRequest);

        //adding the string request to request queue
        // requestQueue.add(stringRequest);
    }

//    private void loadContestList(String user_id_per1) {
//        this.listdata = new ArrayList<>();
//        this.progressDialog = new ProgressDialog(this, R.style.CustomDialog);
//        this.progressDialog.setMessage("Data Loading..");
//        this.progressDialog.show();
//        StringBuilder sb = new StringBuilder();
//        sb.append("http://findnearby.biz/contest-app-new/apis/user-detail.php?ukey=zcdVJ2VRKAEHNBK2B8JqMyITugtRUFqz&user_id=");
//        sb.append(user_id_per1);
//        Volley.newRequestQueue(this).add(new StringRequest(0, sb.toString(), new Response.Listener<String>() {
//            public void onResponse(String response) {
//                String birth_date;
//                JSONObject data;
//                String str;
//                String str2 = "null";
//                String str3 = "id";
//                try {
//                    ActiveContestActivity.this.progressDialog.dismiss();
//                    JSONObject obj = new JSONObject(response);
//                    String success = obj.getString("success");
//                    String str4 = "No Data For Contest";
//                    if (success.equalsIgnoreCase("true")) {
//                        ActiveContestActivity.this.sold_no_found.setVisibility(View.GONE);
//                        JSONObject data2 = obj.getJSONObject();
//                        JSONObject jsonUSER = data2.getJSONObject("user");
//                        String string = jsonUSER.getString(str3);
//                        String string2 = jsonUSER.getString("first_name");
//                        String string3 = jsonUSER.getString("email");
//                        String string4 = jsonUSER.getString("password");
//                        String string5 = jsonUSER.getString("address");
//                        String string6 = jsonUSER.getString("phone");
//                        String birth_date2 = jsonUSER.getString("bday");
//                        JSONObject jSONObject = obj;
//                        String string7 = jsonUSER.getString("total_earn");
//                        JSONArray active_contest = data2.getJSONArray("active_contests");
//                        int counter = active_contest.length();
//                        if (counter > 0) {
//                            int i = 0;
//                            while (i < counter) {
//                                JSONObject child_data = active_contest.getJSONObject(i);
//                                PersonalInfoModel item = new PersonalInfoModel();
//                                JSONArray active_contest2 = active_contest;
//                                JSONObject child_data2 = child_data;
//                                int counter2 = counter;
//                                String string8 = child_data2.getString(str3);
//                                String str5 = str3;
//                                PersonalInfoModel item2 = item;
//                                item2.setActive_contest_id(string8);
//                                item2.setActive_title(child_data2.getString("title"));
//                                item2.setActive_category(child_data2.getString("category"));
//                                item2.setActive_amount(child_data2.getString("amount"));
//                                String cover = child_data2.getString("cover");
//                                if (cover.equalsIgnoreCase(str2)) {
//                                    str = str2;
//                                    data = data2;
//                                    birth_date = birth_date2;
//                                } else if (!cover.equalsIgnoreCase(str2)) {
//                                    str = str2;
//                                    ActiveContestActivity activeContestActivity = ActiveContestActivity.this;
//                                    data = data2;
//                                    StringBuilder sb = new StringBuilder();
//                                    birth_date = birth_date2;
//                                    sb.append(ActiveContestActivity.this.cover_url);
//                                    sb.append(cover);
//                                    activeContestActivity.result_img_url = sb.toString();
//                                    item2.setActive_cover(ActiveContestActivity.this.result_img_url);
//                                } else {
//                                    str = str2;
//                                    data = data2;
//                                    birth_date = birth_date2;
//                                }
//                                item2.setActive_end_date(child_data2.getString(Param.END_DATE));
//                                ActiveContestActivity.this.listdata.add(item2);
//                                i++;
//                                counter = counter2;
//                                str2 = str;
//                                active_contest = active_contest2;
//                                str3 = str5;
//                                data2 = data;
//                                birth_date2 = birth_date;
//                            }
//                            int i2 = counter;
//                            JSONObject jSONObject2 = data2;
//                            String str6 = birth_date2;
//                        } else {
//                            int i3 = counter;
//                            JSONObject jSONObject3 = data2;
//                            String str7 = birth_date2;
//                            ActiveContestActivity.this.sold_no_found.setVisibility(0);
//                            ActiveContestActivity.this.sold_no_found.setText(str4);
//                        }
//                    } else {
//                        if (success.equalsIgnoreCase("false")) {
//                            ActiveContestActivity.this.sold_no_found.setVisibility(0);
//                            ActiveContestActivity.this.sold_no_found.setText(str4);
//                        }
//                    }
//                    ActiveContestActivity.this.active_recyclerAdapter = new PersonalInfoAdapter(ActiveContestActivity.this, ActiveContestActivity.this.listdata, new OnItemClickListener() {
//                        public void onItemClick(PersonalInfoModel item) {
//                            String active_title = item.getActive_title();
//                        }
//                    });
//                    ActiveContestActivity.this.recyclerView.setAdapter(ActiveContestActivity.this.active_recyclerAdapter);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new ErrorListener() {
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ActiveContestActivity.this, error.getMessage(), 0).show();
//                ActiveContestActivity.this.progressDialog.dismiss();
//            }
//        }));
//    }
}
