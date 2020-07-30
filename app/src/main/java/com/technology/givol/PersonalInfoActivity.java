package com.technology.givol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.codesgood.views.JustifiedTextView;
import com.technology.givol.adapter.PersonalExpiredAdapter;
import com.technology.givol.adapter.PersonalInfoAdapter;
import com.technology.givol.model.PersonalInfoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.technology.givol.R.id;
import static com.technology.givol.R.layout;

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PREFS_NAME = "GivolLoginPref";
    ImageView per_back_img;
    TextView header_user_name, header_user_email, user_name, user_email;
    RecyclerView recyclerView;
    RecyclerView recycer_view1;
    String cover_url = "http://findnearby.biz/contest-app-new/contest-cover-images/";
    String result_img_url;
    ProgressDialog progressDialog;
    String user_id_per, user_password_per;
    TextView pass_per_txt;
    TextView per_pass_show, per_phone_no, per_date_birth, per_total_earn;
    JustifiedTextView per_address;
    ImageView image_update, personal_info_update;
    String email_per, user_name_per, simple_logout;
    private PersonalInfoAdapter active_recyclerAdapter;
    private PersonalExpiredAdapter expired_recyclerAdapter;
    private ArrayList<PersonalInfoModel> listdata;
    private ArrayList<PersonalInfoModel> listdata1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_personal_info);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        user_id_per = settings.getString("USER_ID", "");
        email_per = settings.getString("EMAIL", "");
        user_name_per = settings.getString("USER_NAME", "");
        simple_logout = settings.getString("LOG_OUT", "");
        //user_password_per=settings.getString("PASSWORD","");
//        header_user_name.setText(user_name_per);
        //  header_user_email.setText(email_per);

        Log.d("EMAIL", email_per);
        Log.d("USER_NAME", user_name_per);
        Log.d("SIMPLE", simple_logout);
        initViews();

    }

    private void initViews() {
        header_user_name = (TextView) findViewById(R.id.header_user_name);
        header_user_email = (TextView) findViewById(R.id.header_user_email);
        header_user_name.setText(user_name_per);
        header_user_email.setText(email_per);
        user_name = (TextView) findViewById(id.user_name);
        user_email = (TextView) findViewById(id.user_email);
        user_name.setText(user_name_per);
        user_email.setText(email_per);
        pass_per_txt = (TextView) findViewById(R.id.pass_per_txt);
        per_pass_show = (TextView) findViewById(R.id.per_pass_show);
        per_pass_show.setOnClickListener(this);
        per_address = (JustifiedTextView) findViewById(R.id.per_address);
        per_phone_no = (TextView) findViewById(R.id.per_phone_no);
        per_date_birth = (TextView) findViewById(R.id.per_date_birth);
        per_total_earn = (TextView) findViewById(R.id.per_total_earn);
        image_update = (ImageView) findViewById(R.id.image_update);
        personal_info_update = (ImageView) findViewById(R.id.personal_info_update);
        recyclerView = (RecyclerView) findViewById(id.recycer_view);
        recycer_view1 = (RecyclerView) findViewById(id.recycer_view1);
        recyclerView.setHasFixedSize(true);
        recycer_view1.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recycer_view1.setLayoutManager(layoutManager1);
        loadContestList(user_id_per);
        per_back_img = (ImageView) findViewById(id.per_back_img);
        per_back_img.setOnClickListener(this);
        image_update.setOnClickListener(this);
        personal_info_update.setOnClickListener(this);
//        adapter.setOnItemClickListener(new PersonalInfoAdapter.OnItemClickListener1() {
//            public void onItemClick(View itemView, int position) {
//               startActivity(new Intent(getApplicationContext(), ContestHolderActivity.class));
//            }
//        });
//        adapter1.setOnItemClickListener(new PersonalInfoAdapter.OnItemClickListener1() {
//            public void onItemClick(View itemView, int position) {
//                startActivity(new Intent(getApplicationContext(),ContestFinalActivity.class));
//            }
//        });
    }


    public void onClick(View v) {
        if (v.getId() == id.per_back_img) {
            startActivity(new Intent(this, NavActivity.class));
            finish();
        } else if (v.getId() == id.per_pass_show) {
            showPassword(per_pass_show);
        }
//        else if(v.getId()== id.image_update)
//        {
//            startActivity(new Intent(this, UserProfileUpdateActivity.class));
//            finish();
//        }
        else if (v.getId() == id.personal_info_update) {
            startActivity(new Intent(this, UserProfileUpdateActivity.class));
            finish();
        }
    }


    private void loadContestList(final String user_id_per1) {

        listdata = new ArrayList<>();
        listdata1 = new ArrayList<>();
        progressDialog = new ProgressDialog(PersonalInfoActivity.this, R.style.CustomDialog);
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

                                user_name.setText(first_name);

                                user_email.setText(email);
                                pass_per_txt.setText(password);
                                pass_per_txt.setTransformationMethod(new AsteriskPasswordTransformationMethod());
                                per_address.setText(address);
                                per_phone_no.setText(phone_no);
                                per_date_birth.setText(birth_date);
                                per_total_earn.setText(total_earn);
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
                            active_recyclerAdapter = new PersonalInfoAdapter(PersonalInfoActivity.this, listdata, new PersonalInfoAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(PersonalInfoModel item) {
                                    String cat_id = item.getActive_title();
                                    Intent intent = new Intent(PersonalInfoActivity.this, ContestHolderActivity.class);
                                    intent.putExtra("CATEGORY_ID", cat_id);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            expired_recyclerAdapter = new PersonalExpiredAdapter(PersonalInfoActivity.this, listdata1, new PersonalExpiredAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(PersonalInfoModel item) {
                                    String cat_id = item.getExpired_title();
                                    String cat_id_1 = item.getExpired_contest_id();
                                    Intent intent = new Intent(PersonalInfoActivity.this, ContestFinalActivity.class);

                                    intent.putExtra("CATEGORY_NAME", cat_id);
                                    intent.putExtra("CATEGORY_ID", cat_id_1);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                            recyclerView.setAdapter(active_recyclerAdapter);
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
                        Toast.makeText(PersonalInfoActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

        //creating a request queue
        Volley.newRequestQueue(PersonalInfoActivity.this).add(stringRequest);

        //adding the string request to request queue
        // requestQueue.add(stringRequest);
    }

    public void showPassword(View v) {

        TextView showHideBtnText = (TextView) findViewById(R.id.per_pass_show);

        if (showHideBtnText.getText().toString().equals("Show")) {
            pass_per_txt.setTransformationMethod(null);
            showHideBtnText.setText("Hide");
        } else {
            pass_per_txt.setTransformationMethod(new AsteriskPasswordTransformationMethod());
            showHideBtnText.setText("Show");
        }


    }

}

