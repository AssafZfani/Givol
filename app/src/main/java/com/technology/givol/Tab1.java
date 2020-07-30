package com.technology.givol;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.technology.givol.adapter.RecyclerAdapter;
import com.technology.givol.adapter.RecyclerAdapter1;
import com.technology.givol.model.Data;
import com.technology.givol.model.RecDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tab1 extends Fragment {
    List<RecDataModel> arrayList;
    List<Data> listdata;
    //List<Data>tempList;
    Context context;
    RecyclerView firstRecyclerView;
    boolean includeEdge = false;
    //ArrayList personImages;
    RecyclerView secondRecyclerView;
    int spacing = 50;
    int spanCount = 2;
    RecyclerAdapter customAdapter;
    String server_url = "http://findnearby.biz/contest-app/contest-cover-images/" + "f78b5107ee228fe3797589c538b3d741.png";
    RecyclerAdapter1 recyclerAdapter1;
    NavActivity activity;
    //  ArrayList<Integer> personImages =new ArrayList<Integer>(R.drawable.heart_pizza, R.drawable.heart_pizza, R.drawable.heart_pizza);
    // int[] per
    String myDataFromActivity;
    private String[] country = {"Category", "Participants", "End Time"};
    private Spinner spCountry, spCountry1, spCountry2;
    private View view;
    // private RequestQueue mRequestQueue1;
    //private StringRequest mStringRequest1;
    private GridLayoutManager gridLayoutManager;

    public Tab1() {

    }

    public static Fragment newInstance() {
        Tab1 fragment = new Tab1();
        return fragment;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {

            view = inflater.inflate(R.layout.tab_1, container, false);

            // activity = (NavActivity) getActivity();
            // myDataFromActivity = activity.getMyData();
            // int index_va=activity.getMyIndex();
            // Toast.makeText(getActivity().getApplicationContext(),"VALUE:"+index_va+myDataFromActivity,Toast.LENGTH_SHORT).show();
            firstRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
            firstRecyclerView.setHasFixedSize(true);
            gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
            firstRecyclerView.setLayoutManager(gridLayoutManager);
            // tempList = new ArrayList<Data>();
            // listdata.clear();


            loadHeroList(myDataFromActivity);
            recyclerAdapter1 = new RecyclerAdapter1(activity, listdata);
            firstRecyclerView.setAdapter(recyclerAdapter1);
            recyclerAdapter1.notifyDataSetChanged();

            // secondRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_1);
            // GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            //firstRecyclerView.addItemDecoration(new GridSpacingItemDecoration(this.spanCount, this.spacing, this.includeEdge));
            //  firstRecyclerView.setLayoutManager(gridLayoutManager);
//            DataAdapter customAdapter = new DataAdapter(context,arrayList);
//            firstRecyclerView.setAdapter(customAdapter);
            //   GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this.context, 2);
            //  secondRecyclerView.addItemDecoration(new GridSpacingItemDecoration(this.spanCount, this.spacing, this.includeEdge));
            // secondRecyclerView.setLayoutManager(gridLayoutManager1);
            // secondRecyclerView.setAdapter(new DataAdapter(context,arrayList));
            // prepareMovieData();

//            customAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, int i) {
//                    // Intent intent=new Intent(getActivity().getBaseContext(),ItemDetailActivity.class);
//                    Intent intent=new Intent(getActivity().getBaseContext(),NavItemDetailActivity.class);
//                    startActivity(intent);
//                }
//            });
            spCountry = (Spinner) view.findViewById(R.id.spCountry);
            //  spCountry.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,country));

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                    getActivity().getBaseContext(), R.layout.spinner_item, country);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
            spCountry.setAdapter(spinnerArrayAdapter);
            spCountry1 = (Spinner) view.findViewById(R.id.spCountry1);
            //  spCountry.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,country));

            ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                    getActivity().getBaseContext(), R.layout.spinner_item, country);
            spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item);
            spCountry1.setAdapter(spinnerArrayAdapter1);
            spCountry2 = (Spinner) view.findViewById(R.id.spCountry2);
            //  spCountry.setAdapter(new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,country));

            ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(
                    getActivity().getBaseContext(), R.layout.spinner_item, country);
            spinnerArrayAdapter2.setDropDownViewResource(R.layout.spinner_item);
            spCountry2.setAdapter(spinnerArrayAdapter2);


        } else {
            ((ViewGroup) view.getParent()).removeView(view);
        }


        //  View view = LayoutInflater.from(getContext()).inflate(R.layout.tab_1, container, false);
        return view;
    }

    private void prepareMovieData() {
        String str = "$55";
        arrayList.add(new RecDataModel(str));
        arrayList.add(new RecDataModel(str));
        arrayList.add(new RecDataModel(str));
        arrayList.add(new RecDataModel(str));
    }


//    private void loadHeroList() {
//        //getting the progressbar
//        //final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
//
//        //making the progressbar visible
//       // progressBar.setVisibility(View.VISIBLE);
//
//        //creating a string request to send request to the url
//        String url = "http://findnearby.biz/contest-app/apis/contests_by_conditions.php?ukey=St40LV9smSI0IFjy7vOt0N1yxzMfQV2i&category="+"PROFESSIONALS";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //hiding the progressbar after completion
//                       // progressBar.setVisibility(View.INVISIBLE);
//
//
//                        try {
//                            //getting the whole json object from the response
//                            JSONObject obj = new JSONObject(response);
//
//                            //we have the array named hero inside the object
//                            //so here we are getting that json array
//                            JSONArray data = obj.getJSONArray("data");
//
//                            //now looping through all the elements of the json array
//                            for (int i = 0; i < data.length(); i++) {
//                                //getting the json object of the particular index inside the array
//                                JSONObject child_data = data.getJSONObject(i);
//
//                                //creating a hero object and giving them the values from json object
//                                RecDataModel recDataModel = new RecDataModel(child_data.getString("cover"),child_data.getString("title"),child_data.getString("amount"),child_data.getString("participant"));
//
//
//                                arrayList.add(recDataModel);
//                            }
//                            Log.d("LIST",arrayList.toString());
//
//                            customAdapter = new RecyclerAdapter( context,arrayList);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //displaying the error in toast if occurrs
//                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        //creating a request queue
//        Volley.newRequestQueue(getActivity()).add(stringRequest);
//
//        //adding the string request to request queue
//       // requestQueue.add(stringRequest);
//    }


    public void loadHeroList(final String category1) {
        listdata = new ArrayList<Data>();
        //listdata.clear();
        //getting the progressbar
        //final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        // progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        String url = "http://findnearby.biz/contest-app/apis/contests_by_conditions.php?ukey=St40LV9smSI0IFjy7vOt0N1yxzMfQV2i&category=" + category1;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        // progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            String success = obj.getString("success");
                            if (success.equalsIgnoreCase("true")) {
                                //listdata.clear();
                                JSONArray data = obj.getJSONArray("data");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < data.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject child_data = data.getJSONObject(i);
                                    Data item = new Data();
                                    item.setId(child_data.getString("title"));
                                    item.setJudul(child_data.getString("participant"));
                                    item.setHarga(child_data.getString("amount"));
                                    item.setThubnail(child_data.getString(("cover")));
                                    listdata.add(item);

                                    //
                                }
                                recyclerAdapter1.notifyDataSetChanged();
                            } else if (success.equalsIgnoreCase("false")) {
                                Toast.makeText(getActivity(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                            //we have the array named hero inside the object
                            //so here we are getting that json array


                            //recyclerAdapter1.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);

        //adding the string request to request queue
        // requestQueue.add(stringRequest);
    }


}
