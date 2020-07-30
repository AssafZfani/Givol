package com.technology.givol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.technology.givol.adapter.Adapter_drawer;
import com.technology.givol.adapter.RecyclerAdapter;
import com.technology.givol.adapter.ViewPagerAdapter;
import com.technology.givol.model.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NavActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_NAME = "GivolLoginPref";
    public static final String LOG_OUT_NAME = "Logout";
    Adapter_drawer adapter_drawer;
    Adapter_drawer adapter_drawer1;
    //TabAdapter adapter;
    String[] arraySpinner;
    String[] arraySpinner1;
    String[] arraySpinner2;
    DrawerLayout drawerLayout;
    ListView drawerList;
    ListView drawerList1;
    LinearLayout drawerView;
    ImageView imageMenu;
    ImageView imageMenu1;
    LinearLayout linear_header;
    Fragment mFragment = null;
    Spinner simpleSpinner1;
    Spinner simpleSpinner2;
    TextView textHomeBar;
    String[] titlesArray;
    String[] titlesArray1;
    String str = "Category1";
    String str2 = "Category2";
    String[] countryNames = {"Category", "Participants", "End Time"};
    TabLayout tabLayout;
    int no_of_categories = -1;
    ProgressDialog progressDialog;
    //ViewPagerAdapter adapter;
//    String category_name;
    String category_name1;
    String[] currencies = new String[]{"Participants", "1-10", "10-20", "21-30", "31-40", "41-50", "51-60", "61-70", "71-80", "81-90", "90+"};
    String[] end_time = new String[]{"End Time", "1 days", "1 weeks", "1 months", "More than 1 month"};
    Spinner spinner, spinner1, spinner2;
    int po;
    ViewPagerAdapter adapter;
    TextView custom_email_id, custom_user_name;
    String user_id_nav, email_nav, user_name_nav;
    String user_logout;
    String google_user_id, google_user_name, google_user_email, goole_img_url, simple_logout;
    String category = "all category";
    String country_value = null;
    Bundle bundle;
    String spi_1, spi_2, spi_3;
    String[] category_name_spin;
    //ArrayAdapter adapter3;
    String category_upper_result;
    TextView city_text;
    private ViewPager viewPager;
    private RecyclerAdapter recyclerAdapter, recyclerAdapter1;
    private RecyclerView recyclerView;
    private ArrayList<Data> listdata;
    private ArrayList<Data> tempList;
    private GridLayoutManager gridLayoutManager;
    private RequestQueue mRequestQueue, mRequestQueue1;
    private StringRequest mStringRequest, mStringRequest1;
    private String url = "http://findnearby.biz/contest-app-new/apis/all_categories.php?ukey=HA5TadtD3dF8lAwK0BS7OVOL2YiBl7ZZ";
    private String[] country = {"Category", "Participants", "End Time"};
    private Spinner spCountry, spCountry1, spCountry2;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        bundle = getIntent().getExtras();
        // Toast.makeText(NavActivity.this,"CATEGORY"+category, Toast.LENGTH_SHORT).show();


//         if(country_value!=null)
//         {
//             loadCityData(country_value);
//         }
//         else if(country_value==null)
//         {
//           Toast.makeText(NavActivity.this,"NoData",Toast.LENGTH_SHORT).show();
//         }


        progressDialog = new ProgressDialog(NavActivity.this, R.style.CustomDialog);
        progressDialog.setMessage("Data Loading..");
        progressDialog.show();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        user_id_nav = settings.getString("USER_ID", "");
        city_text = (TextView) findViewById(R.id.city_text);
        email_nav = settings.getString("EMAIL", "");
        user_name_nav = settings.getString("USER_NAME", "");
        simple_logout = settings.getString("LOG_OUT", "");

        // SharedPreferences settings1 = getSharedPreferences(LOG_OUT_NAME, 0);
//        log_out_g = settings1.getString("LOGOUT", "");
//        google_user_name = settings1.getString("GOOGLE_USR_NAME", "");
//        google_user_email = settings1.getString("GOOGLE_EMAIL", "");
        Toast.makeText(NavActivity.this, "USER_EMAIL" + user_id_nav, Toast.LENGTH_SHORT).show();

//        arraySpinner = new String[]{"Category", str, str2};
//        arraySpinner1 = new String[]{"Participants", str, str2};
//        arraySpinner2 = new String[]{"End Time", str, str2};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerView = (LinearLayout) findViewById(R.id.drawerView);
        drawerList = (ListView) findViewById(R.id.drawerList);
        drawerList1 = (ListView) findViewById(R.id.drawerList1);
        imageMenu = (ImageView) findViewById(R.id.imageMenu);
        imageMenu1 = (ImageView) findViewById(R.id.imageMenu1);

        linear_header = (LinearLayout) findViewById(R.id.linear_header);
        titlesArray = getResources().getStringArray(R.array.drawertitles);
        titlesArray1 = getResources().getStringArray(R.array.drawertitles1);
        // CustomListAdapter adapter = new CustomListAdapter(this, titlesArray);
        //  CustomListAdapter adapter1 = new CustomListAdapter(this, titlesArray1);
        adapter_drawer = new Adapter_drawer(getApplicationContext(), titlesArray);
        adapter_drawer1 = new Adapter_drawer(getApplicationContext(), titlesArray1);
        drawerList.setAdapter(adapter_drawer);
        drawerList1.setAdapter(adapter_drawer1);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(viewPager);
        sendAndRequestResponse();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        //   startCountDown(dob);
        //tempList = new ArrayList<Data>();
        //  listdata = new ArrayList<Data>();
        // loadHeroList(pro);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (bundle != null) {
                    country_value = bundle.getString("COUNTRY_NAME");
                    city_text.setText(country_value);
                    category_name_spin = new String[]{"All Category"};
                    ArrayAdapter adapter3 = new ArrayAdapter(NavActivity.this, R.layout.spinner_item, category_name_spin);
                    spinner.setAdapter(adapter3);

                    // Toast.makeText(NavActivity.this,"HELLO"+country_value, Toast.LENGTH_SHORT).show();
                    loadCityData(country_value);
                    bundle = null;
                }

//                    if(country_value!=null)
////                    {
////                        loadCityData(country_value);
////                        country_value=null;
////                        Toast.makeText(NavActivity.this,"HHHH",Toast.LENGTH_SHORT).show();
////                    }
                else {
                    category = tab.getText().toString();
                    String chars = capitalize(category);

                    // loadHeroList1(category);
                    category_name_spin = new String[]{chars};
                    ArrayAdapter adapter3 = new ArrayAdapter(NavActivity.this, R.layout.spinner_item, category_name_spin);
                    spinner.setAdapter(adapter3);
                    loadHeroList(category, country_value);
                    //Toast.makeText(NavActivity.this,"HELLO"+country_value, Toast.LENGTH_SHORT).show();
                }
//                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        ArrayAdapter adapter = new ArrayAdapter(NavActivity.this, R.layout.spinner_item, currencies);

        ArrayAdapter adapter1 = new ArrayAdapter(NavActivity.this, R.layout.spinner_item, end_time);
        // sendAndRequestResponse1();


        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            spinner.setPopupBackgroundResource(R.color.colorWhite);
            spinner1.setPopupBackgroundResource(R.color.colorWhite);
            spinner2.setPopupBackgroundResource(R.color.colorWhite);
        }
        imageMenu.setOnClickListener(this);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    drawerLayout.closeDrawer(drawerView);
                }

                if (position == 1) {
                    Intent intent_contest = new Intent(NavActivity.this.getApplicationContext(), ActiveContestActivity.class);
                    intent_contest.putExtra("COUNTRY_NAME", country_value);
                    intent_contest.putExtra("ACTIVITY_NO", "1");
                    NavActivity.this.startActivity(intent_contest);
                    NavActivity.this.finish();
                }
                if (position == 2) {
                    Intent intent_contest = new Intent(NavActivity.this.getApplicationContext(), ContestFinishActivity.class);
                    intent_contest.putExtra("COUNTRY_NAME", country_value);
                    intent_contest.putExtra("ACTIVITY_NO", "1");
                    NavActivity.this.startActivity(intent_contest);
                    NavActivity.this.finish();
                }
                if (position == 3) {
                    Intent intent_contest = new Intent(NavActivity.this.getApplicationContext(), WinningContestActivity.class);
                    intent_contest.putExtra("COUNTRY_NAME", country_value);
                    intent_contest.putExtra("ACTIVITY_NO", "1");
                    NavActivity.this.startActivity(intent_contest);
                    NavActivity.this.finish();
                }

            }
        });
        drawerList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0) {
                    mFragment = new Tab1();
                } else if (position == 1) {
                    startActivity(new Intent(getApplicationContext(), ExpandListActivity.class));
                    finish();
                } else if (position == 2) {
                    Intent pri_intent = new Intent(getApplicationContext(), PriPolicyActivity.class);
                    pri_intent.putExtra("COUNTRY_NAME", country_value);
                    pri_intent.putExtra("ACTIVITY_NO", "1");
                    startActivity(pri_intent);
                    finish();
                } else if (position == 3) {
//                    if(simple_logout.equalsIgnoreCase("Google"))
//                    {
//                        signOut();
//                    }
//                    else if(simple_logout.equalsIgnoreCase("Simple"))
//                    {
//                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//                    SharedPreferences.Editor editor = settings.edit();
//                    editor.remove("LOG_OUT");
//                    editor.remove("logged");
//                    editor.commit();
//                    finish();
//                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                       // Toast.makeText(getApplicationContext(),"Logged Out"+settings.getString("LOGOUT",""),Toast.LENGTH_SHORT).show();
//                    }

                    if (simple_logout.equalsIgnoreCase("Simple")) {
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.remove("LOG_OUT");
                        editor.remove("logged");
                        editor.commit();
                        finish();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Logged Out" + settings.getString("LOGOUT", ""), Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseAuth.getInstance().signOut();
                        Intent intent1 = new Intent(NavActivity.this, MainActivity.class);
                        // intent1.putExtra("COUNTRY_NAME",city_name);
                        startActivity(intent1);
                        finish();
                    }


                }
                drawerLayout.closeDrawer(drawerView);
            }
        });
        custom_email_id = (TextView) findViewById(R.id.custom_email_id);
        custom_email_id.setText(email_nav);
        custom_user_name = (TextView) findViewById(R.id.custom_user_name);
        custom_user_name.setText(user_name_nav);
        imageMenu1.setOnClickListener(this);
        linear_header.setOnClickListener(this);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                //Toast.makeText(NavActivity.this,"SPIN"+parentView.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
//                spi_1=parentView.getItemAtPosition(position).toString();
//               // spi_1=parentView.getItemAtPosition(0).toString();
//                spi_2=(spinner1.getSelectedItem()!=null)?spinner1.getSelectedItem().toString():"Participants";
//                spi_3=(spinner2.getSelectedItem()!=null)?spinner2.getSelectedItem().toString():"End Time";
//                loadDataWithSpinner(spi_1,spi_2,spi_3);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // your code here
//            }
//
//        });
        spinner1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        //spi_1=(spinner.getSelectedItem()!=null)?spinner.getSelectedItem().toString():"All Category";
                        spi_1 = (spinner.getSelectedItem() != null) ? spinner.getSelectedItem().toString() : "All Category";
                        spi_2 = parentView.getItemAtPosition(position).toString();
                        spi_3 = (spinner2.getSelectedItem() != null) ? spinner2.getSelectedItem().toString() : "End Time";


                        // Toast.makeText(NavActivity.this,"SPI2"+spi_2,Toast.LENGTH_SHORT).show();
                        // Toast.makeText(NavActivity.this,"SPIN3"+spi_3,Toast.LENGTH_SHORT).show();

                        loadDataWithSpinner(spi_1, spi_2, spi_3);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
                return false;
            }
        });
        spinner2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        //Toast.makeText(NavActivity.this,"SPIN"+parentView.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show();
                        spi_1 = (spinner.getSelectedItem() != null) ? spinner.getSelectedItem().toString() : "All Category";
                        spi_2 = (spinner1.getSelectedItem() != null) ? spinner1.getSelectedItem().toString() : "Participants";
                        spi_3 = parentView.getItemAtPosition(position).toString();
                        loadDataWithSpinner(spi_1, spi_2, spi_3);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
                return false;
            }
        });


    }

    //    @Override
//    protected void onStart() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//        mGoogleApiClient.connect();
//        super.onStart();
//    }
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
            default:
                return;
        }

    }

    private String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }

    public void sendAndRequestResponse() {


        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);

        //String Request initialized
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    tabLayout.addTab(tabLayout.newTab().setText("All Category"));
                    JSONArray data = jsonObject.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject childData = data.getJSONObject(i);
                        String category_name = childData.getString("name");
                        // setupViewPager(viewPager);
                        // ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
                        //adapter.addFragment(new BlankFragment(), category_name);
                        // adapter.addFragment(new TwoFragment(), "TWO");
                        //adapter.addFragment(new ThreeFragment(), "THREE");

                        tabLayout.addTab(tabLayout.newTab().setText(category_name));


                        //Creating our pager adapter
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //  Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error :" + error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });

        mRequestQueue.add(mStringRequest);

    }

//    public void sendAndRequestResponse1() {
//
//        //RequestQueue initialized
//        mRequestQueue = Volley.newRequestQueue(this);
//        final ArrayList<String> list = new ArrayList<String>();
//        //String Request initialized
//        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String success = jsonObject.getString("success");
//                    list.add("All Category");
//                    JSONArray data = jsonObject.getJSONArray("data");
//                    for (int i = 0; i < data.length(); i++) {
//                        JSONObject childData = data.getJSONObject(i);
//                        //String category_name = childData.getString("name");
//                        list.add(childData.getString("name"));
//
//
//                        //Creating our pager adapter
//                    }
//                    ArrayAdapter<String> spinnerMenu = new ArrayAdapter<String>(NavActivity.this,R.layout.spinner_item, list);
//
//                    spinner.setAdapter(spinnerMenu);
//
//
//
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//                //  Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), "Error :" + error.toString(), Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//        mRequestQueue.add(mStringRequest);
//    }


    private void loadHeroList(final String cat_value, final String country_value1) {

        listdata = new ArrayList<>();
        System.out.println("LIST_DATA" + listdata);

        final String imgae_base_url = "http://findnearby.biz/contest-app-new/contest-cover-images/";
        // String url = "http://findnearby.biz/contest-app/apis/contests_by_conditions.php?ukey=St40LV9smSI0IFjy7vOt0N1yxzMfQV2i&category="+cat_value;
        String url = "http://findnearby.biz/contest-app-new/apis/contests_by_city_and_category.php?ukey=zlTHyQcwcJofdUFiyUMyPrJCiEVdFLCo&city=" + country_value1 + "&category=" + cat_value;
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
                                //we have the array named hero inside the object
                                //so here we are getting that json array
                                JSONArray data = obj.getJSONArray("data");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < data.length(); i++) {

                                    //getting the json object of the particular index inside the array
                                    JSONObject child_data = data.getJSONObject(i);
                                    Data item = new Data();
                                    item.setCategory_id(child_data.getString("id"));
                                    item.setId(child_data.getString("amount"));
                                    item.setJudul(child_data.getString("participant"));
                                    item.setHarga(child_data.getString("title"));
                                    item.setThubnail(imgae_base_url + child_data.getString(("cover")));
                                    item.setEnd_date(child_data.getString("end_date"));
                                    listdata.add(item);

                                    //  recyclerAdapter.notifyDataSetChanged();
                                }

                                System.out.println("LIST_DATA1" + data.length());

                            } else if (success.equalsIgnoreCase("false")) {
                                Toast.makeText(NavActivity.this, "Number1" + obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                            recyclerAdapter = new RecyclerAdapter(NavActivity.this, listdata, new RecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Data item) {
                                    String cat_id = item.getCategory_id();
                                    Intent intent = new Intent(NavActivity.this, NavItemDetailActivity.class);
                                    intent.putExtra("CATEGORY_ID", cat_id);
                                    intent.putExtra("COUNTRY_NAME", country_value);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            recyclerView.setAdapter(recyclerAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(NavActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

        //creating a request queue
        Volley.newRequestQueue(NavActivity.this).add(stringRequest);

        //adding the string request to request queue
        // requestQueue.add(stringRequest);
    }

    public void loadDataWithSpinner(final String spin_1, final String spin_2, final String spin_3) {
        listdata = new ArrayList<>();

        String s_2, s_3;
        s_2 = spin_2;
        s_3 = spin_3;
        //  if(spin_1.equalsIgnoreCase("All Category"))
        //String spi_1="All Category",spi_2="Participants",spi_3="End Time";
        if (s_2.equalsIgnoreCase("Participants"))
            s_2 = "";
        if (s_3.equalsIgnoreCase("End Time")) {
            s_3 = "";
        }
        // System.out.println("VALUE",s);


        final String imgae_base_url = "http://findnearby.biz/contest-app-new/contest-cover-images/";
        // String url = "http://findnearby.biz/contest-app/apis/contests_by_conditions.php?ukey=St40LV9smSI0IFjy7vOt0N1yxzMfQV2i&category="+cat_value;
        String url = "http://findnearby.biz/contest-app-new/apis/search_tabs_data.php?ukey=rtXV47n1DLrN9HiEgmL0s2FbpWcACtYP&category=" + spin_1 + "&participant=" + s_2 + "&endTime=" + s_3;
        System.out.println("URL " + url);
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
                                //we have the array named hero inside the object
                                //so here we are getting that json array
                                JSONArray data = obj.getJSONArray("data");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < data.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject child_data = data.getJSONObject(i);
                                    Data item = new Data();
                                    item.setCategory_id(child_data.getString("id"));
                                    item.setId(child_data.getString("amount"));
                                    item.setJudul(child_data.getString("participant"));
                                    item.setHarga(child_data.getString("title"));
                                    item.setThubnail(imgae_base_url + child_data.getString(("cover")));
                                    item.setEnd_date(child_data.getString("end_date"));
                                    listdata.add(item);
                                    //  recyclerAdapter.notifyDataSetChanged();
                                }


                            } else if (success.equalsIgnoreCase("false")) {
                                Toast.makeText(NavActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                            recyclerAdapter = new RecyclerAdapter(NavActivity.this, listdata, new RecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Data item) {
                                    String cat_id = item.getCategory_id();
                                    Intent intent = new Intent(NavActivity.this, NavItemDetailActivity.class);
                                    intent.putExtra("CATEGORY_ID", cat_id);
                                    intent.putExtra("COUNTRY_NAME", country_value);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            recyclerView.setAdapter(recyclerAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(NavActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

        //creating a request queue
        Volley.newRequestQueue(NavActivity.this).add(stringRequest);

        //adding the string request to request queue
        // requestQueue.add(stringRequest);
    }


    private void loadCityData(final String cat_value) {

        listdata = new ArrayList<>();
        final String imgae_base_url = "http://findnearby.biz/contest-app-new/contest-cover-images/";
        String url = "http://findnearby.biz/contest-app-new/apis/search_by_city.php?ukey=CsRn7ktEJAzY0PL9e1hgo4lpwsLaZo5r&city=" + cat_value;

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
                                //we have the array named hero inside the object
                                //so here we are getting that json array
                                JSONArray data = obj.getJSONArray("data");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < data.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject child_data = data.getJSONObject(i);
                                    Data item = new Data();
                                    item.setCategory_id(child_data.getString("id"));
                                    item.setId(child_data.getString("amount"));
                                    item.setJudul(child_data.getString("participant"));
                                    item.setHarga(child_data.getString("title"));
                                    item.setThubnail(imgae_base_url + child_data.getString(("cover")));
                                    item.setEnd_date(child_data.getString("end_date"));
                                    listdata.add(item);
                                    //  recyclerAdapter.notifyDataSetChanged();
                                }


                            } else if (success.equalsIgnoreCase("false")) {
                                Toast.makeText(NavActivity.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                            recyclerAdapter = new RecyclerAdapter(NavActivity.this, listdata, new RecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Data item) {
                                    String cat_id = item.getCategory_id();
                                    //String cat_name=
                                    Intent intent = new Intent(NavActivity.this, NavItemDetailActivity.class);
                                    intent.putExtra("CATEGORY_ID", cat_id);
                                    intent.putExtra("COUNTRY_NAME", country_value);
                                    // intent.putExtra("CATEGORY_NAME",)
                                    startActivity(intent);
                                    finish();
                                }
                            });
                            recyclerView.setAdapter(recyclerAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(NavActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });

        //creating a request queue
        Volley.newRequestQueue(NavActivity.this).add(stringRequest);

        //adding the string request to request queue
        // requestQueue.add(stringRequest);
    }

//    private void signOut() {
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//                        SharedPreferences.Editor editor = settings.edit();
//                        editor.remove("LOG_OUT");
//                        //editor.remove("logged");
//                        editor.commit();
//                        finish();
//                       // finish();
//                        Toast.makeText(getApplicationContext(),"Logged Out"+settings.getString("LOGOUT",""),Toast.LENGTH_SHORT).show();
//                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
//                        startActivity(i);
//                        finish();
//                    }
//                });


    // }


}
