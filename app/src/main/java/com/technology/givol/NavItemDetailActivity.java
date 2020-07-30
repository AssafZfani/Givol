package com.technology.givol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.codesgood.views.JustifiedTextView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.technology.givol.adapter.Adapter_drawer;
import com.technology.givol.adapter.SliderAdapter;
import com.technology.givol.adapter.ViewPagerAdapterdot;
import com.technology.givol.model.SliderUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class NavItemDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_NAME = "GivolLoginPref";
    DrawerLayout drawerLayout;
    ListView drawerList;
    ListView drawerList1;
    LinearLayout drawerView;
    ImageView imageMenu;
    ImageView imageMenu1;
    LinearLayout linear_header;
    String[] titlesArray;
    String[] titlesArray1;
    Adapter_drawer adapter_drawer;
    Adapter_drawer adapter_drawer1;
    Fragment mFragment = null;
    ImageView givol_arrow_back;
    // ViewPager viewPager;
    LinearLayout sliderDotspanel;
    ViewPagerAdapterdot viewPagerAdapter;
    List<SliderUtils> sliderImg;
    RequestQueue rq;
    TextView title_txt, amount_txt, participant_out_txt, count_down_txt;
    JustifiedTextView description_txt;
    Button user_participant_btn;
    String c_value;
    Context context;
    String user_id1, email_nav_item_detail;
    TextView email_nav_detail, user_name_nav_detail;
    String user_name_nav_item, simple_logout;
    ViewPager viewPager;
    TabLayout indicator;
    String terms_value;
    TextView view_terms_conditions;
    //CheckBox chk_term_condition;
    TextView active_participant_txt;
    JustifiedTextView justi_bussiness_address, justi_bussiness_phone,
            bussiness_justi_about_giveaway, justi_bussiness_terms;
    ImageView justi_bussiness_facebook, justi_bussiness_hours;
    LinearLayout linear_long_title;
    TextView long_title_txt;
    String title;
    ImageView shareImageId, business_logo_img;
    String play_store_link = "https://play.google.com/store?hl=en";
    String business_logo_append;
    String country_name_det;
    TextView city_text;
    String lat;
    String lng;
    ScrollView scroll_view_nav;
    SupportMapFragment mapFragment;
    private int dotscount;
    private ImageView[] dots;
    private GoogleApiClient mGoogleApiClient;

    //FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_item_detail);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        user_id1 = settings.getString("USER_ID", "");
        email_nav_item_detail = settings.getString("EMAIL", "");
        city_text = (TextView) findViewById(R.id.city_text);
        //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        user_name_nav_item = settings.getString("USER_NAME", "");
        simple_logout = settings.getString("LOG_OUT", "");
        Bundle bundle = getIntent().getExtras();
        c_value = bundle.getString("CATEGORY_ID");
        country_name_det = bundle.getString("COUNTRY_NAME");
        city_text.setText(country_name_det);
        Toast.makeText(NavItemDetailActivity.this, "Category_id" + c_value, Toast.LENGTH_SHORT).show();
        //  Bundle data = new Bundle();//create bundle instance
        // data.putString("key_value",c_value);
//        FragmentManager mFragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        BlankFragment blankFragment=new BlankFragment();
//       // blankFragment.setArguments(data);
//        fragmentTransaction.replace(R.id.frame_layout_1,blankFragment).commit();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerView = (LinearLayout) findViewById(R.id.drawerView);
        drawerList = (ListView) findViewById(R.id.drawerList);
        drawerList1 = (ListView) findViewById(R.id.drawerList1);
        imageMenu = (ImageView) findViewById(R.id.imageMenu);
        imageMenu1 = (ImageView) findViewById(R.id.imageMenu1);
        givol_arrow_back = (ImageView) findViewById(R.id.givol_arrow_back);
        linear_header = (LinearLayout) findViewById(R.id.linear_header);
        titlesArray = getResources().getStringArray(R.array.drawertitles);
        titlesArray1 = getResources().getStringArray(R.array.drawertitles1);
        adapter_drawer = new Adapter_drawer(this, this.titlesArray);
        adapter_drawer1 = new Adapter_drawer(this, this.titlesArray1);
        drawerList.setAdapter(adapter_drawer);
        drawerList1.setAdapter(adapter_drawer1);
        imageMenu.setOnClickListener(this);
        givol_arrow_back.setOnClickListener(this);

        // loadContestDetail();
        rq = CustomVolleyRequest.getInstance(this).getRequestQueue();
        // viewPager = (ViewPager) findViewById(R.id.viewPager);
        title_txt = (TextView) findViewById(R.id.title_txt);
        linear_long_title = (LinearLayout) findViewById(R.id.linear_long_title);
        this.long_title_txt = (TextView) findViewById(R.id.long_title_txt);
        shareImageId = (ImageView) findViewById(R.id.shareImageId);
        shareImageId.setOnClickListener(this);
        amount_txt = (TextView) findViewById(R.id.amount_txt);
        business_logo_img = (ImageView) findViewById(R.id.business_logo_img);
        participant_out_txt = (TextView) findViewById(R.id.participant_out_txt);
        count_down_txt = (TextView) findViewById(R.id.count_down_txt);
        description_txt = (JustifiedTextView) findViewById(R.id.description_txt);
        active_participant_txt = (TextView) findViewById(R.id.active_participant_txt);
        user_participant_btn = (Button) findViewById(R.id.user_participant_btn);
        // user_participant_btn.setClickable(false);
        user_participant_btn.setOnClickListener(this);

        // chk_term_condition=(CheckBox)findViewById(R.id.chk_term_condition);
        // chk_term_condition.setOnCheckedChangeListener(this);
        justi_bussiness_address = (JustifiedTextView) findViewById(R.id.justi_bussiness_address);
        bussiness_justi_about_giveaway = (JustifiedTextView) findViewById(R.id.bussiness_justi_about_giveaway);
        justi_bussiness_hours = (ImageView) findViewById(R.id.justi_bussiness_hours);
        justi_bussiness_phone = (JustifiedTextView) findViewById(R.id.justi_bussiness_phone);
        justi_bussiness_facebook = (ImageView) findViewById(R.id.justi_bussiness_facebook);
        justi_bussiness_terms = (JustifiedTextView) findViewById(R.id.justi_bussiness_terms);
        // sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        indicator = (TabLayout) findViewById(R.id.indicator);
        //view_terms_conditions=(TextView)findViewById(R.id.view_terms_conditions);
        loadContestDetail(c_value);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        drawerLayout.closeDrawer(drawerView);
                        break;


                    case 1:
                        Intent intent_contest = new Intent(getApplicationContext(), ActiveContestActivity.class);
                        intent_contest.putExtra("COUNTRY_NAME", NavItemDetailActivity.this.country_name_det);
                        intent_contest.putExtra("CATEGORY_ID", NavItemDetailActivity.this.c_value);
                        intent_contest.putExtra("ACTIVITY_NO", "2");
                        NavItemDetailActivity.this.startActivity(intent_contest);
                        NavItemDetailActivity.this.finish();

                    case 2:
                        Intent intent_contest_1 = new Intent(getApplicationContext(), ContestFinishActivity.class);
                        intent_contest_1.putExtra("COUNTRY_NAME", country_name_det);
                        intent_contest_1.putExtra("CATEGORY_ID", c_value);
                        intent_contest_1.putExtra("ACTIVITY_NO", "2");
                        startActivity(intent_contest_1);
                        finish();

                }

            }
        });

        drawerList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        //  textHomeBar.setText("Home");
                        // mFragment = new BlankFragment();
                        break;
                    case 1:
                        Intent intent_term = new Intent(getApplicationContext(), ExpandListActivity.class);
                        intent_term.putExtra("COUNTRY_NAME", NavItemDetailActivity.this.country_name_det);
                        intent_term.putExtra("CATEGORY_ID", NavItemDetailActivity.this.c_value);
                        intent_term.putExtra("ACTIVITY_NO", "2");
                        NavItemDetailActivity.this.startActivity(intent_term);
                        NavItemDetailActivity.this.finish();
                        finish();
                        break;

                    case 2:
                        Intent intent_privacy = new Intent(getApplicationContext(), PriPolicyActivity.class);
                        intent_privacy.putExtra("COUNTRY_NAME", NavItemDetailActivity.this.country_name_det);
                        intent_privacy.putExtra("CATEGORY_ID", NavItemDetailActivity.this.c_value);
                        intent_privacy.putExtra("ACTIVITY_NO", "2");
                        NavItemDetailActivity.this.startActivity(intent_privacy);
                        NavItemDetailActivity.this.finish();
                        finish();
                        break;
                    case 3:

                        if (simple_logout.equalsIgnoreCase("Google")) {
                            signOut();
                        } else if (simple_logout.equalsIgnoreCase("Simple")) {
                            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.remove("LOG_OUT");
                            editor.remove("logged");
                            editor.commit();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Logged Out" + settings.getString("LOGOUT", ""), Toast.LENGTH_SHORT).show();
                        }
                }

//                if (mFragment != null) {
//                    FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    mFragmentTransaction.replace(R.id.frame_layout_1, mFragment);
//                    mFragmentTransaction.addToBackStack(null);
//                    mFragmentTransaction.commit();
//                }

                /* Closing Drawer*/
                drawerLayout.closeDrawer(drawerView);
//                if (position == 0) {
//                    mFragment = new BlankFragment();
//                } else if (position == 1) {
//                    startActivity(new Intent(getApplicationContext(), ExpandListActivity.class));
//                    finish();
//                }
//                else if (position == 2) {
//                    startActivity(new Intent(getApplicationContext(), PriPolicyActivity.class));
//                    finish();
//                }
                // NavActivity.this.drawerLayout.closeDrawer((View) NavActivity.this.drawerView);
            }
        });
        email_nav_detail = (TextView) findViewById(R.id.email_nav_detail);
        email_nav_detail.setText(email_nav_item_detail);
        user_name_nav_detail = (TextView) findViewById(R.id.user_name_nav_detail);
        user_name_nav_detail.setText(user_name_nav_item);
        imageMenu1.setOnClickListener(this);
        linear_header.setOnClickListener(this);
        ImageView ivMapTransparent = (ImageView) findViewById(R.id.ivMapTransparent);
        scroll_view_nav = (ScrollView) findViewById(R.id.scroll_view_nav);
        ivMapTransparent.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == 0) {
                    NavItemDetailActivity.this.scroll_view_nav.requestDisallowInterceptTouchEvent(true);
                    return false;
                } else if (action == 1) {
                    NavItemDetailActivity.this.scroll_view_nav.requestDisallowInterceptTouchEvent(false);
                    return true;
                } else if (action != 2) {
                    return true;
                } else {
                    NavItemDetailActivity.this.scroll_view_nav.requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            }
        });

    }

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(drawerView)) {
            drawerLayout.closeDrawer(drawerView);
        } else {
            finish();
        }
    }

    /* Toggle Menu*/
    public void toggleMenu() {
        if (drawerLayout.isDrawerOpen(drawerView)) {
            drawerLayout.closeDrawer(drawerView);
        } else {
            drawerLayout.openDrawer(drawerView);
        }
    }

    public void onClick(View v) {
        // System.out.println("VALUE"+String.valueOf(v.getId()));
        switch (v.getId()) {

            case R.id.imageMenu /*2131296416*/:
                toggleMenu();
                return;
            case R.id.imageMenu1 /*2131296417*/:
                onBackPressed();
                break;
            case R.id.linear_header /*2131296438*/:
                startActivity(new Intent(this, PersonalInfoActivity.class));
                break;
            case R.id.givol_arrow_back:
                Intent intent = new Intent(this, NavActivity.class);
                // intent.putExtra("ALL_CAT","all Category");
                startActivity(new Intent(this, CurrentLocationActivity.class));
                break;
            case R.id.user_participant_btn:
                //System.out.println("VALUE");
                userParticipant(user_id1);
                break;
            case R.id.shareImageId:
                //shareDetail();
                shareItem(business_logo_append, title, play_store_link);
                break;
//            case R.id.view_terms_conditions:
//                if(!terms_value.isEmpty())
//                {
//                ViewDialogTerms alertDialoge = new ViewDialogTerms();
//                alertDialoge.showDialog(NavItemDetailActivity.this,"Terms and Conditions",terms_value);
//                }
//                break;
//            case R.id.chk_term_condition:
//                if(chk_term_condition.isChecked())
//                {
//                    Log.d("BUTTON","BUTTON ENABLED");
//                    user_participant_btn.setEnabled(true);
//                   //userParticipant(user_id1);
//                }
//                else
//                {
//                    Log.d("BUTTON","BUTTON DISABLED");
//                }
//                break;
//
////

            default:
                return;
        }

    }


    //  private void loadContestDetail(final String c_value1) {
    private void loadContestDetail(final String c_value1) {
        final String server_url = "http://findnearby.biz/contest-app-new/contest-cover-images/";
        final String str2 = "";
        // Toast.makeText(NavItemDetailActivity.this,"Val1"+c_value1,Toast.LENGTH_SHORT).show();
        //  listdata=new ArrayList<>();
        String url = "http://findnearby.biz/contest-app-new/apis/contest-detail.php?ukey=4nouFcZzeu0pB01PREnk02RmbWbtwYMZ&id=" + Integer.parseInt(c_value1);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        // progressBar.setVisibility(View.INVISIBLE);
                        sliderImg = new ArrayList<>();
                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            String success = obj.getString("success");
                            // String message=obj.getString("message");
                            if (success.equalsIgnoreCase("true")) {
                                //we have the array named hero inside the object
                                //so here we are getting that json array
                                JSONObject data = obj.getJSONObject("data");
                                //now looping through all the elements of the json array

                                String cover = data.getString("cover");
                                String cover2 = data.getString("cover2");
                                String cover3 = data.getString("cover3");
                                String cover4 = data.getString("cover4");
                                String result_img_url = cover.equalsIgnoreCase("null") ? "null" : server_url + cover;
                                String result_img_url1 = cover2.equalsIgnoreCase("null") ? "null" : server_url + cover2;
                                String result_img_url12 = cover3.equalsIgnoreCase("null") ? "null" : server_url + cover3;
                                String result_img_url13 = cover4.equalsIgnoreCase("null") ? "null" : server_url + cover4;
                                String[] final_result = new String[]{result_img_url, result_img_url1, result_img_url12, result_img_url13};

                                for (int i = 0; i < final_result.length; i++) {
                                    // Toast.makeText(this,"FINAL  "+final_result[i],Toast.LENGTH_SHORT).show();
                                    if (final_result[i].equals("null")) {
                                        continue;
                                    }
                                    SliderUtils sliderUtils = new SliderUtils();
                                    sliderUtils.setSliderImageUrl(final_result[i]);
                                    Log.d("VALUE", final_result[i]);
//                                    sliderUtils.setSliderImageUrl(result_img_url1);
//                                    sliderUtils.setSliderImageUrl(result_img_url12);
//                                    sliderUtils.setSliderImageUrl(result_img_url13);
                                    sliderImg.add(sliderUtils);
                                }
                                viewPager.setAdapter(new SliderAdapter(NavItemDetailActivity.this, sliderImg));
                                indicator.setupWithViewPager(viewPager, true);
                                Timer timer = new Timer();
                                timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
                                title = data.getString("title");
                                String long_title = data.getString("long_title");

                                String amount = data.getString("amount");
                                String participant = data.getString("participant");
                                String end_date = data.getString("end_date");
                                String description = data.getString("description");
                                String business_logo = data.getString("business_logo");
                                String about_giveaway = data.getString("about_giveaway");
                                String active_participant = data.getString("active_participant");
                                String business_address = data.getString("business_address");
                                String zipcode = data.getString("business_zipcode").equalsIgnoreCase(str2) ? "90210" : data.getString("business_zipcode");
                                System.out.println("ZIP_CODE" + zipcode);
                                loadLatLon(zipcode);

                                business_address = business_address.replace("\r", "").replace("\n", "");
                                final String business_hours_open = data.getString("business_website_url");
                                final String business_facebook_url = data.getString("business_facebook_url");
                                // business_facebook_url=business_facebook_url.replace(".","");
                                // String business_hours_close=data.getString("business_hours_close");
                                String business_phone = data.getString("business_phone");
                                terms_value = data.getString("terms");
                                title_txt.setText(title);
                                String str14 = " ";
                                if (long_title.equalsIgnoreCase("null")) {
                                    long_title_txt.setText(str14);

                                } else {
                                    linear_long_title.setVisibility(View.VISIBLE);
                                    long_title_txt.setText(long_title);
                                }
                                String sb5 = "http://findnearby.biz/contest-app-new/";

                                business_logo_append = sb5 + business_logo;

                                Glide.with(NavItemDetailActivity.this).load(business_logo_append).
                                        error((int) R.drawable.heart_pizza).into(business_logo_img);
                                amount_txt.setText(amount);
                                participant_out_txt.setText(participant);
                                startCountDown(end_date);
                                description_txt.setText(description);
                                active_participant_txt.setText(active_participant + "/");
                                justi_bussiness_terms.setText(terms_value);
                                bussiness_justi_about_giveaway.setText(about_giveaway);
                                justi_bussiness_address.setText(business_address);
                                // justi_bussiness_hours.setText(business_hours_open);
                                justi_bussiness_hours.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                        intent.setData(Uri.parse(business_hours_open));
                                        startActivity(intent);
                                    }
                                });

                                //justi_bussiness_facebook.setText(business_facebook_url);
                                justi_bussiness_phone.setText(business_phone);
                                justi_bussiness_facebook.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                        intent.setData(Uri.parse(business_facebook_url));
                                        startActivity(intent);
                                    }
                                });

                            } else {
                                Toast.makeText(NavItemDetailActivity.this, "NO DATA", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(NavItemDetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        Volley.newRequestQueue(NavItemDetailActivity.this).add(stringRequest);

        //adding the string request to request queue
        // requestQueue.add(stringRequest);
    }

   /* @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

//        if(!terms_value.isEmpty())
//        {
//            ViewDialogTerms alertDialoge = new ViewDialogTerms();
//            alertDialoge.showDialog(NavItemDetailActivity.this,"Terms and Conditions",terms_value);
//        }
//        break;

            if(isChecked && !terms_value.isEmpty())
            {
                ViewDialogTerms alertDialoge = new ViewDialogTerms();
            alertDialoge.showDialog(NavItemDetailActivity.this,"Terms and Conditions",terms_value);
                user_participant_btn.setEnabled(true);

            }
            else
            {
                user_participant_btn.setEnabled(false);
            }


    }*/

    public void startCountDown(String contest_end_dt) {
        Date end_date = null;
        try {
            end_date = new SimpleDateFormat("yyyy-MM-dd").parse(contest_end_dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date today = new Date();
        long diff = end_date.getTime() - today.getTime();
        int numOfDays = (int) (diff / (1000 * 60 * 60 * 24));
        // int hours = (int) (diff / (1000 * 60 * 60));
        int hours = (int) (diff / (1000 * 60 * 60)) + numOfDays * 24;
        int minutes = (int) (diff / (1000 * 60));
        int seconds = (int) (diff / (1000));
        new CountDownTimer(diff, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                /* converting the milliseconds into days, hours, minutes and seconds and displaying it in textviews             */

//                count_down_txt.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))+":"+(TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished)))+":"
//                        +(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)))+":"+(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                count_down_txt.setText(TimeUnit.HOURS.toDays(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)) * 24 + (TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished))) + ":"
                        + (TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))) + ":" + (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                // hours_1.setText((TimeUnit.MILLISECONDS.toHours(millisUntilFinished) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millisUntilFinished)))+"");

                // mins_1.setText((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)))+"");

                // seconds_1.setText((TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))+"");
            }

            @Override

            public void onFinish() {
                /*            clearing all fields and displaying countdown finished message             */

                count_down_txt.setText("Finished");
                // hours_1.setText("");
                // mins_1.setText("");
                // seconds_1.setText("");
            }
        }.start();
    }

    private void userParticipant(final String user_id2) {
        String url_user = "http://findnearby.biz/contest-app-new/apis/participate-in-contest.php";
        // final String username = etUname.getText().toString().trim();
        // final String password = etPass.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_user,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");
                            if (success.equalsIgnoreCase("true")) {
                                ViewDialog alertDialoge = new ViewDialog();
                                alertDialoge.showDialog(NavItemDetailActivity.this, "SUCCESSFULLY PARTICIPATED");
//
                                // Toast.makeText(NavItemDetailActivity.this,"SUCCESSFULLY PARTICIPATED",Toast.LENGTH_LONG).show();

                            } else {
                                ViewDialog alertDialoge = new ViewDialog();
                                alertDialoge.showDialog(NavItemDetailActivity.this, message);
                                //Toast.makeText(NavItemDetailActivity.this,message,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NavItemDetailActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("ukey", "9Eo3cI5qiPX6uZRv9jZWHhmvJfWNmHtP");
                params.put("user_id", user_id2);
                params.put("contest_id", c_value);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.remove("LOG_OUT");
                        //editor.remove("logged");
                        editor.commit();
                        finish();
                        // finish();
                        Toast.makeText(getApplicationContext(), "Logged Out" + settings.getString("LOGOUT", ""), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                });


    }

    public void loadLatLon(String address) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://maps.googleapis.com/maps/api/geocode/json?address=");
        sb.append(address);
        sb.append("&key=AIzaSyD17PAD9jptbbiCqFhJgu5wxHNWn2NXRCo");
        //https://maps.googleapis.com/maps/api/geocode/json?address=184101&key=AIzaSyD17PAD9jptbbiCqFhJgu5wxHNWn2NXRCo
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + "AIzaSyD17PAD9jptbbiCqFhJgu5wxHNWn2NXRCo";
        Volley.newRequestQueue(this).add(new StringRequest(0, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONArray results = new JSONObject(response).getJSONArray("results");
                    if (results.length() > 0) {
                        JSONObject location = results.getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
                        NavItemDetailActivity.this.lat = location.getString("lat");
                        NavItemDetailActivity.this.lng = location.getString("lng");
                        Log.d("LATITUDE", NavItemDetailActivity.this.lat);
                        Log.d("LONGITUDE", NavItemDetailActivity.this.lng);
                        NavItemDetailActivity.this.mapFragment.getMapAsync(new OnMapReadyCallback() {
                            public void onMapReady(GoogleMap googleMap) {
                                GoogleMap myMap2 = googleMap;
                                LatLng address = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                                myMap2.addMarker(new MarkerOptions().position(address));
//                                Marker TP = myMap2.addMarker(new MarkerOptions()
//                                        .position(address).title("TutorialsPoint"));
                                myMap2.moveCamera(CameraUpdateFactory.newLatLng(address));
                                myMap2.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NavItemDetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void shareTextUrl() {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, play_store_link);
        try {
            startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            //ToastHelper.MakeShortText("Whatsapp have not been installed.");
        }
    }

    private void shareDetail() {
        // Uri imageUri = Uri.parse("android.resource://" + getPackageName()
        //  + "/drawable/" + "ic_launcher");
//        Uri imageUri=Uri.parse(business_logo_append);
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_TEXT, play_store_link+"\n"+title);
//        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//        shareIntent.setType("image/jpeg");
//        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        startActivity(Intent.createChooser(shareIntent, "send"));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_eye_pass);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "ic_launcher_round", null);
        Uri imageUri = Uri.parse(business_logo_append);
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.putExtra(Intent.EXTRA_TEXT, play_store_link + "\n" + title);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent, "Share"));
    }

    public void shareItem(String image_logo, final String business_name_s, final String play_store_value) {
        Picasso.with(getApplicationContext()).load(image_logo).into((Target) new Target() {
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Intent i = new Intent("android.intent.action.SEND");
                i.setType("image/*");

                String str = "\n";

                i.putExtra("android.intent.extra.TEXT", business_name_s + "\n" + play_store_value);
                i.putExtra("android.intent.extra.STREAM", NavItemDetailActivity.this.getLocalBitmapUri(bitmap));
                NavItemDetailActivity.this.startActivity(Intent.createChooser(i, "Share Image"));
            }

            public void onBitmapFailed(Drawable errorDrawable) {
            }

            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        try {
            File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            StringBuilder sb = new StringBuilder();
            sb.append("share_image_");
            sb.append(System.currentTimeMillis());
            sb.append(".png");
            File file = new File(externalFilesDir, sb.toString());
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            return Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            NavItemDetailActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < sliderImg.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}






