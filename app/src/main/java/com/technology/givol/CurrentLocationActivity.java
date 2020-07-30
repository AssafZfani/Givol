package com.technology.givol;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.technology.givol.adapter.CustomAdapter;
import com.technology.givol.model.CountryName;
import com.technology.givol.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CurrentLocationActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String LOG_OUT_NAME = "Logout";
    private static CustomAdapter adapter, adapter1, adapter2;
    Button btnCanada;
    Button btnUK;
    Button btnUSA;
    Button log_out;
    ArrayList<DataModel> dataModels;
    ArrayList<DataModel> dataModels1;
    ArrayList<DataModel> dataModels2;
    ListView listView;
    ListView listView1;
    ListView listView2;
    Button nextTxt;
    String log_out_g;
    // Search EditText
    EditText input_country_edit;
    String city_name;
    String query;
    // private String url = "http://findnearby.biz/contest-app/apis/all_categories.php?ukey=HA5TadtD3dF8lAwK0BS7OVOL2YiBl7ZZ";
    private GoogleApiClient mGoogleApiClient;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private ListView country_list;
    // Listview Adapter
    private LinearLayout llContainer;
    private ArrayList<CountryName> mProductArrayList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

//        SharedPreferences settings1 = getSharedPreferences(LOG_OUT_NAME, 0);
//        log_out_g = settings1.getString("LOGOUT", "");
//        Toast.makeText(CurrentLocationActivity.this,"LOG"+log_out_g,Toast.LENGTH_SHORT).show();
        nextTxt = (Button) findViewById(R.id.nextTxt);
        input_country_edit = (EditText) findViewById(R.id.input_country_edit);
        country_list = (ListView) findViewById(R.id.country_list_view);

        input_country_edit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Call back the Adapter with current character to Filter
                query = s.toString();
                //Toast.makeText(CurrentLocationActivity.this,"LOG"+query,Toast.LENGTH_SHORT).show();
                if (query.isEmpty()) {
                    country_list.setVisibility(View.GONE);
                } else {
                    myAdapter.getFilter().filter(query);
                    country_list.setVisibility(View.VISIBLE);
                }


                // vehicleinfodisplay.clear();
                // String search=s.toString();
                //  country_list.setVisibility(View.VISIBLE);


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // myAdapter.getFilter().filter(s.toString());
                // country_list.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

                query = s.toString();
                //Toast.makeText(CurrentLocationActivity.this,"LOG"+query,Toast.LENGTH_SHORT).show();
                if (query.isEmpty()) {
                    country_list.setVisibility(View.GONE);
                } else {
                    myAdapter.getFilter().filter(query);
                    country_list.setVisibility(View.VISIBLE);
                }

            }
        });
        // Adding items to listview


        listView1 = (ListView) findViewById(R.id.list_view1);
        listView = (ListView) findViewById(R.id.list_view);
        listView2 = (ListView) findViewById(R.id.list_view2);
        btnUSA = (Button) findViewById(R.id.btnUSA);
        btnUK = (Button) findViewById(R.id.btnUK);
        btnCanada = (Button) findViewById(R.id.btnCanada);
        // log_out=(Button)findViewById(R.id.log_out);
        //log_out.setOnClickListener(this);
        nextTxt.setOnClickListener(this);
        btnUSA.setOnClickListener(this);
        btnUK.setOnClickListener(this);
        btnCanada.setOnClickListener(this);
        dataModels = new ArrayList<>();
        dataModels.add(new DataModel("London,UK"));
        dataModels.add(new DataModel("Liverpool,UK"));
        dataModels.add(new DataModel("Manchester,UK"));
        dataModels.add(new DataModel("Arsenal,UK"));
        dataModels.add(new DataModel("Stamford,UK"));
        dataModels1 = new ArrayList<>();
        dataModels1.add(new DataModel("Los Angeles"));
        dataModels1.add(new DataModel("Newyork"));
        dataModels1.add(new DataModel("Miami"));
        dataModels1.add(new DataModel("Phoenix"));
        dataModels1.add(new DataModel("Chicago"));
        dataModels2 = new ArrayList<>();
        dataModels2.add(new DataModel("Vancouver"));
        dataModels2.add(new DataModel("Toronto"));
        dataModels2.add(new DataModel("Alberta"));
        dataModels2.add(new DataModel("Kelowna"));
        dataModels2.add(new DataModel("Prince George"));
        adapter1 = new CustomAdapter(dataModels1, getApplicationContext());
        adapter = new CustomAdapter(dataModels, getApplicationContext());
        adapter2 = new CustomAdapter(dataModels2, getApplicationContext());
        listView1.setAdapter(adapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel1 = dataModels1.get(position);
                Intent intent = new Intent(CurrentLocationActivity.this, NavActivity.class);
                intent.putExtra("COUNTRY_NAME", dataModel1.getName());
                startActivity(intent);
                //Toast.makeText(CurrentLocationActivity.this,"HELLO"+dataModel1.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DataModel dataModel = dataModels.get(position);
                Intent intent = new Intent(CurrentLocationActivity.this, NavActivity.class);
                intent.putExtra("COUNTRY_NAME", dataModel.getName());
                startActivity(intent);
            }
        });
        listView2.setAdapter(adapter2);
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DataModel dataModel = dataModels2.get(position);
                Intent intent = new Intent(CurrentLocationActivity.this, NavActivity.class);
                intent.putExtra("COUNTRY_NAME", dataModel.getName());
                startActivity(intent);

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
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
//    mProductArrayList.clear();
        sendAndRequestResponse1();
        myAdapter = new MyAdapter(getApplicationContext(), mProductArrayList);
        country_list.setAdapter(myAdapter);
        country_list.setVisibility(View.GONE);
        input_country_edit.clearFocus();
        input_country_edit.setText("");
        nextTxt.setVisibility(View.GONE);
        nextTxt.setEnabled(false);
        listView.setVisibility(View.GONE);
        listView1.setVisibility(View.GONE);
        listView2.setVisibility(View.GONE);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCanada:
                listView2.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                listView1.setVisibility(View.GONE);
                return;
            case R.id.btnUK:
                listView.setVisibility(View.VISIBLE);
                listView2.setVisibility(View.GONE);
                listView1.setVisibility(View.GONE);
                return;
            case R.id.btnUSA:
                listView1.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                listView2.setVisibility(View.GONE);
                return;
            case R.id.nextTxt:
                // signOut();
                //  startActivity(new Intent(getApplicationContext(), NavActivity.class));
                // finish();

                Intent intent = new Intent(CurrentLocationActivity.this, NavActivity.class);
                intent.putExtra("COUNTRY_NAME", city_name);
                startActivity(intent);
                //  input_country_edit.setText("");
                //input_country_edit.
                //finish();
                break;
//            case R.id.log_out:
//                FirebaseAuth.getInstance().signOut();
//                Intent intent1=new Intent(CurrentLocationActivity.this,MainActivity.class);
//               //intent1.putExtra("COUNTRY_NAME",city_name);
//                startActivity(intent1);
//                finish();
//                break;
            default:
                return;
        }
    }

    public void sendAndRequestResponse1() {
        mProductArrayList = new ArrayList<CountryName>();
        String url_country = "http://findnearby.biz/contest-app-new/apis/city_list.php?ukey=Mwmwfkrt6HDTTDI7BuyOtJTnHOx7bMna";
        //RequestQueue initialized
        mRequestQueue = Volley.newRequestQueue(this);
        //final ArrayList<String> list = new ArrayList<String>();
        //String Request initialized
        final String[] products;
        mStringRequest = new StringRequest(Request.Method.GET, url_country, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    JSONArray data = jsonObject.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject childData = data.getJSONObject(i);
                        //String category_name = childData.getString("name");
                        mProductArrayList.add(new CountryName(childData.getString("name")));


                        //Creating our pager adapter
                    }
//                    myAdapter = new MyAdapter(getApplicationContext(), mProductArrayList);
//                    country_list.setAdapter(myAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //  Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error :" + error.toString(), Toast.LENGTH_LONG).show();

            }
        });

        mRequestQueue.add(mStringRequest);
    }

    public class MyAdapter extends BaseAdapter implements Filterable {
        Context context;
        LayoutInflater inflater;
        private ArrayList<CountryName> mOriginalValues; // Original Values
        private ArrayList<CountryName> mDisplayedValues;    // Values to be displayed

        public MyAdapter(Context context, ArrayList<CountryName> mProductArrayList) {
            this.mOriginalValues = mProductArrayList;
            this.mDisplayedValues = mProductArrayList;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mDisplayedValues.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {

                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.country_list_item, null);
                holder.llContainer = (LinearLayout) convertView.findViewById(R.id.llContainer);
                holder.tvName = (TextView) convertView.findViewById(R.id.product_name);
                //holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvName.setText(mDisplayedValues.get(position).name);
            //holder.tvPrice.setText(mDisplayedValues.get(position).price+"");

            holder.llContainer.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    city_name = mDisplayedValues.get(position).name;

                    input_country_edit.setText(city_name);
                    nextTxt.setVisibility(View.VISIBLE);
                    nextTxt.setEnabled(true);
//                    Intent intent=new Intent(CurrentLocationActivity.this,NavActivity.class);
//                    intent.putExtra("COUNTRY_NAME",mDisplayedValues.get(position).name);
//                    startActivity(intent);
                }
            });

            return convertView;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    mDisplayedValues = (ArrayList<CountryName>) results.values; // has the filtered values
                    notifyDataSetChanged();  // notifies the data with new filtered values
                }

                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                    ArrayList<CountryName> FilteredArrList = new ArrayList<CountryName>();

                    if (mOriginalValues == null) {
                        mOriginalValues = new ArrayList<CountryName>(mDisplayedValues); // saves the original data in mOriginalValues
                    }

                    /********
                     *
                     *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                     *  else does the Filtering and returns FilteredArrList(Filtered)
                     *
                     ********/
                    if (constraint == null || constraint.length() == 0) {

                        // set the Original result to return
                        results.count = mOriginalValues.size();
                        results.values = mOriginalValues;
                    } else {
                        constraint = constraint.toString().toLowerCase();
                        for (int i = 0; i < mOriginalValues.size(); i++) {
                            String data = mOriginalValues.get(i).name;
                            if (data.toLowerCase().startsWith(constraint.toString())) {
                                //FilteredArrList.add(new CountryName(mOriginalValues.get(i).name,mOriginalValues.get(i).price));
                                FilteredArrList.add(new CountryName(mOriginalValues.get(i).name));
                            }
                        }
                        // set the Filtered result to return
                        results.count = FilteredArrList.size();
                        results.values = FilteredArrList;
                    }
                    return results;
                }
            };
            return filter;
        }

        private class ViewHolder {
            LinearLayout llContainer;
            TextView tvName, tvPrice;
        }
    }
}


