package com.technology.givol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserProfileUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PREFS_NAME = "GivolLoginPref";
    EditText first_name, last_name, phone_no, address, birthday_date;
    Button update_profile_btn;
    ImageView back_img;
    EditText passwordLogin;
    String f_name, l_name, u_phone_no, u_address, u_birthday_date;
    String URL_UPDATE = "http://findnearby.biz/contest-app/apis/update_user_profile.php";
    String user_id, update_email, update_user_name, simple_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_update);

        initView();
    }

    public void initView() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        user_id = settings.getString("USER_ID", "");
        update_email = settings.getString("EMAIL", "");
        update_user_name = settings.getString("USER_NAME", "");
        simple_logout = settings.getString("LOG_OUT", "");
        Log.d("ID", user_id);
        Log.d("EMAIL", update_email);
        Log.d("USER_NAME", update_user_name);

        // Log.d("ID",user_id);

        first_name = (EditText) findViewById(R.id.update_f_name);
        first_name.setText(update_user_name);
        last_name = (EditText) findViewById(R.id.update_l_name);
        phone_no = (EditText) findViewById(R.id.update_phone);
        address = (EditText) findViewById(R.id.update_address);
        birthday_date = (EditText) findViewById(R.id.email_edt_register);
        update_profile_btn = (Button) findViewById(R.id.update_usr_pro_btn);
        update_profile_btn.setOnClickListener(this);
        back_img = (ImageView) findViewById(R.id.back_img);
        back_img.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_usr_pro_btn:
                f_name = first_name.getText().toString().trim();
                l_name = last_name.getText().toString().trim();
                u_phone_no = phone_no.getText().toString().trim();
                u_address = address.getText().toString().trim();
                u_birthday_date = birthday_date.getText().toString().trim();

                updateUserProfileDetail(f_name, l_name, u_phone_no, u_address, u_birthday_date);
                break;

            case R.id.back_img:
                Intent intent = new Intent(UserProfileUpdateActivity.this, CurrentLocationActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    private void updateUserProfileDetail(final String first_name, final String last_name, final String phone_no, final String address, final String birthday_date) {


        RequestQueue queue = Volley.newRequestQueue(UserProfileUpdateActivity.this);

        final StringRequest sr = new StringRequest(Request.Method.POST, URL_UPDATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");


                    // Toast.makeText(getApplicationContext(), "Login"+id, Toast.LENGTH_SHORT).show();
                    //SharedPreferences settings = getSharedPreferences(PrefManager.PREF_NAME, MODE_PRIVATE); // 0 - for private mode
                    //SharedPreferences.Editor editor = settings.edit();
                    //Set "hasLoggedIn" to true
                    // editor.putBoolean("hasLoggedIn", true);
                    // Commit the edits!
                    // editor.commit();
                    if (success.equalsIgnoreCase("true")) {
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONObject user = data.getJSONObject("user");
                        // Toast.makeText(getApplicationContext(),"You "+message, Toast.LENGTH_SHORT).show();
                        String first_name = user.getString("first_name");
                        //  String =user.getString("email");
                        // ViewDialog1 alertDialoge = new ViewDialog1(getApplicationContext());
                        // alertDialoge.alertDialogShow(SignUpActivity.this, "SUCCESSFULLY REGISTERED");
                        ViewDialog alertDialoge = new ViewDialog();
                        alertDialoge.showDialog(UserProfileUpdateActivity.this, "SUCCESSFULLY UPDATED");
//                        Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
//                        // intent.putExtra("USER_NAME1",email1);
//                        startActivity(intent);
                        // pDialog.hide();

                    } else if (success.equalsIgnoreCase("false")) {
                        String message = jsonObject.getString("message");
                        ViewDialog alertDialoge = new ViewDialog();
                        alertDialoge.showDialog(UserProfileUpdateActivity.this, message);
                    }


                    // Toast.makeText(getApplicationContext(),"You are login successfully"+id, Toast.LENGTH_SHORT).show();
                    // Intent dashBoardIntent=new Intent(GoogleLoginActivity.this,BasicStartActivity.class);
                    //  startActivity(dashBoardIntent);
                    // finish();
                    //  pDialog.hide();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error.printStackTrace();
                Toast.makeText(UserProfileUpdateActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                //  progree_bar.setVisibility(View.GONE);


            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("first_name", first_name);
                params.put("last_name", last_name);
                params.put("phone", phone_no);
                params.put("address", address);
                params.put("bday", birthday_date);
                params.put("user_id", user_id);
                params.put("ukey", "GyuJCSARoDwUZ0QkXhjUspUfNDs4F9Er");
                return params;
            }


        };
        queue.add(sr);


    }

}
