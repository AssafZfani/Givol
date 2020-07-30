package com.technology.givol;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String URL_LOGIN = "http://findnearby.biz/contest-app-new/apis/user_login.php";
    public static final String PREFS_NAME = "GivolLoginPref";
    public static final String LOG_OUT_NAME = "Logout";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static int RC_SIGN_IN = 111;

    //private static final int RC_SIGN_IN = 007;
    String TAG_REPLACE = "HELLO";
    TextView move_sign_up_txt;
    TextInputEditText user_name_login;
    EditText passwordLogin;
    ImageButton password_show_hide;
    Button login_sign_in;
    Button google_sign_in_btn, btnFaceBook;
    String simpleEmail, simplePassword;
    String type_logout;
    String termsAndConditions = "https://example.com/terms.html",
            privacyPolicy = "https://example.com/privacy.html";
    private int passwordNotVisible = 1;
    private GoogleApiClient mGoogleApiClient;
    private ProgressDialog mProgressDialog;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail;
    private GoogleSignInAccount account;
    //TODO: Add TAC and Policy links
    //private static final String TAG = SplashAvtivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").equals("true")) {
            // Intent intent = new Intent(LoginActivity.this, BasicStartActivity.class);
            Intent intent = new Intent(MainActivity.this, CurrentLocationActivity.class);
            // intent.putExtra("USER_NAME",simpleEmail);
            startActivity(intent);
        }

        // progressBar = findViewById(R.id.progressBar);
        user_name_login = (TextInputEditText) findViewById(R.id.nameUserLogin);
        passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        login_sign_in = (Button) findViewById(R.id.login_sign_in);
        btnFaceBook = (Button) findViewById(R.id.btnFaceBook);
        move_sign_up_txt = (TextView) findViewById(R.id.move_sign_up_txt);
        move_sign_up_txt.setOnClickListener(this);
        login_sign_in.setOnClickListener(this);

        llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        google_sign_in_btn = (Button) findViewById(R.id.google_sign_in_btn);
        google_sign_in_btn.setOnClickListener(this);
        btnFaceBook.setOnClickListener(this);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_sign_in:
                simpleEmail = user_name_login.getText().toString().trim();
                simplePassword = passwordLogin.getText().toString().trim();
//                Toast.makeText(LoginActivity.this, "Email"+simpleEmail, Toast.LENGTH_SHORT).show();
//                Toast.makeText(LoginActivity.this, "Pass"+simplePassword, Toast.LENGTH_SHORT).show();
//                Toast.makeText(LoginActivity.this, "Device"+refreshedToken, Toast.LENGTH_SHORT).show();
                postNewLogin(simpleEmail, simplePassword);
                break;
            case R.id.move_sign_up_txt:
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.google_sign_in_btn:
                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.GoogleBuilder().build());
                LoginStatus(providers);
                break;
            case R.id.btnFaceBook:
                List<AuthUI.IdpConfig> providers1 = Arrays.asList(
                        new AuthUI.IdpConfig.FacebookBuilder().build());

                LoginStatus(providers1);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {

                    Log.e(TAG, Objects.requireNonNull(user.getDisplayName()));
                    Log.e(TAG, Objects.requireNonNull(user.getEmail()));
                    String user_email = user.getEmail();
                    String user_password = user.getUid();
                    Log.e(TAG, user.getUid());
                    postNewLogin1(user_email, user_password);

                    //UpdateUI();
                }

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    private void UpdateUI() {
        Intent intent = new Intent(MainActivity.this, CurrentLocationActivity.class);
        startActivity(intent);
        finish();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void postNewLogin(final String email1, final String password1) {
        type_logout = "Simple";
        // Toast.makeText(LoginActivity.this, "device "+devicetoken, Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(this, "plz enter your email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(this, "plz enter your password ", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


        final StringRequest sr = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
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
                        //Boolean.valueOf(message);
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONObject user = data.getJSONObject("user");

                        // Toast.makeText(getApplicationContext(),"You "+message, Toast.LENGTH_SHORT).show();
                        String id = user.getString("id");
                        String first_name = user.getString("first_name");
                        String email = user.getString("email");
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("logged", "true");
                        editor.putString("LOG_OUT", type_logout);
                        //editor.putString("USER_ID", id);
                        //editor.putString("USER_NAME", first_name);
                        //editor.putString("EMAIL", email);

                        // Toast.makeText(getApplicationContext(), "Login"+id, Toast.LENGTH_SHORT).show();
                        editor.commit();
                        // Toast.makeText(getApplicationContext(), "Successfull Login", Toast.LENGTH_SHORT).show();
                        // Toast.makeText(getApplicationContext(), "Login"+id, Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(LoginActivity.this, BasicStartActivity.class);
                        Intent intent = new Intent(MainActivity.this, CurrentLocationActivity.class);
                        intent.putExtra("USER_NAME1", email1);
                        startActivity(intent);
                        finish();
                        // pDialog.hide();
                    } else if (success.equalsIgnoreCase("false")) {
                        String message = jsonObject.getString("message");
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                //  progree_bar.setVisibility(View.GONE);


            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email1);
                params.put("password", password1);
                params.put("ukey", "e3ITezCb0oC4uBPLlFQUvgIJtAm81MqD");
                return params;
            }


        };
        queue.add(sr);

    }

    private void postNewLogin1(final String email1, final String password1) {

        // Toast.makeText(LoginActivity.this, "device "+devicetoken, Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(this, "plz enter your email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(this, "plz enter your password ", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


        final StringRequest sr = new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
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
                        //Boolean.valueOf(message);
                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONObject user = data.getJSONObject("user");
                        // Toast.makeText(getApplicationContext(),"You "+message, Toast.LENGTH_SHORT).show();
                        String id = user.getString("id");
                        String first_name = user.getString("first_name");
                        String email = user.getString("email");
                        UpdateUI();
                        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        //editor.putString("logged", "true");
                        // editor.putString("LOG_OUT", type_logout);
                        editor.putString("USER_ID", id);
                        editor.putString("USER_NAME", first_name);
                        editor.putString("EMAIL", email);
                        editor.commit();
//
                        Toast.makeText(getApplicationContext(), "login suceess", Toast.LENGTH_SHORT).show();

//                        //Toast.makeText(getApplicationContext(), "Successfull Login", Toast.LENGTH_SHORT).show();
//                        //Toast.makeText(getApplicationContext(), "Login"+id, Toast.LENGTH_SHORT).show();
//                        // Intent intent = new Intent(LoginActivity.this, BasicStartActivity.class);
//                        Intent intent = new Intent(MainActivity.this, CurrentLocationActivity.class);
//                        // intent.putExtra("USER_NAME1",email1);
//                        startActivity(intent);
                        // pDialog.hide();
                    } else if (success.equalsIgnoreCase("false")) {
                        String message = jsonObject.getString("message");
                        System.out.println("MESSAGE" + message);

                        if (message.equalsIgnoreCase("User_With_This_Email_Does_Not_Exit")) {
                            //Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                            Intent dashBoardIntent = new Intent(MainActivity.this, SignUpActivity.class);
                            startActivity(dashBoardIntent);
                            // finish();
                        }
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
                Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                //  progree_bar.setVisibility(View.GONE);


            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email1);
                params.put("password", password1);
                params.put("ukey", "e3ITezCb0oC4uBPLlFQUvgIJtAm81MqD");
                return params;
            }


        };
        queue.add(sr);

    }

//    private void handleSignInResult(GoogleSignInResult result) {
//        Log.e(TAG, "handleSignInResult:" + result.isSuccess());
//        if (result.isSuccess()) {
//            account = result.getSignInAccount();
//            Log.e(TAG, account.getDisplayName());
//            Log.e(TAG, account.getEmail());
//            Log.e(TAG, account.getId());
//            updateUI(true);
//        } else {
//            updateUI(false);
//        }
//    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//        if (opr.isDone()) {
//            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
//            // and the GoogleSignInResult will be available instantly. We can try and retrieve an
//            // authentication code.
//            Log.d(TAG, "Got cached sign-in");
//            GoogleSignInResult result = opr.get();
//            handleSignInResult(result);
//        } else {
//            // If the user has not previously signed in on this device or the sign-in has expired,
//            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
//            // single sign-on will occur in this branch.
//            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setMessage("Checking sign in state...");
//            progressDialog.show();
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
//                    progressDialog.dismiss();
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
//    }


//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
//        // be available.
//        Log.d(TAG, "onConnectionFailed:" + connectionResult);
//    }

//    private void showProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(this);
//            mProgressDialog.setMessage("loading");
//            mProgressDialog.setIndeterminate(true);
//        }
//
//        mProgressDialog.show();
//    }
//
//    private void hideProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.hide();
//        }
//    }

//    public void updateUI(boolean logged) {
////        if (logged) {
////
////            type_logout = "Google";
////            // intent.putExtra("LOGOUT",type_logout);
////            // SharedPreferences settings1 = getSharedPreferences(LOG_OUT_NAME, 0);
////            // SharedPreferences.Editor editor = settings1.edit();
////            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
////            SharedPreferences.Editor editor = settings.edit();
////            // editor.putString("logged", "true");
////            editor.putString("LOG_OUT", type_logout);
////            editor.putString("USER_ID", account.getId());
////            editor.putString("USER_NAME", account.getFamilyName());
////            editor.putString("EMAIL", account.getEmail());
////            //  editor.putString("LOGOUT",type_logout);
//////            editor.putString("GOOGLE_USR_ID",account.getId());
//////            editor.putString("GOOGLE_USR_NAME", account.getDisplayName());
//////            editor.putString("GOOGLE_EMAIL",account.getEmail());
//////            editor.putString("GOOGLE_IMG_URL", account.getPhotoUrl().toString());
////            editor.commit();
////            Intent intent = new Intent(MainActivity.this, CurrentLocationActivity.class);
//////            intent.putExtra("GOOGLE_USR_ID",account.getId());
//////            intent.putExtra("GOOGLE_USR_NAME", account.getDisplayName());
//////            intent.putExtra("GOOGLE_EMAIL",account.getEmail());
//////            intent.putExtra("GOOGLE_IMG_URL", account.getPhotoUrl().toString());
////            startActivity(intent);
////            finish();
////            // google_sign_in_btn.setVisibility(View.GONE);
//////           // btnSignOut.setVisibility(View.VISIBLE);
//////           // btnRevokeAccess.setVisibility(View.VISIBLE);
////            // llProfileLayout.setVisibility(View.VISIBLE);
////        }
////    }

    //    private void updateUI(boolean signedIn) {
//        if (signedIn) {
//
//            Intent main = new Intent(this, MainActivity.class);
//            main.putExtra("displayname", account.getDisplayName());
//            main.putExtra("imageurl", account.getPhotoUrl().toString());
//            startActivity(main);
//            finish();
//        } else {
//            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
//            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
//        }
//    }
    public void LoginStatus(List<AuthUI.IdpConfig> provider) {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(provider)
                        .setLogo(R.drawable.givol)
                        .setIsSmartLockEnabled(false)// Set logo drawable
                        .setTosAndPrivacyPolicyUrls(termsAndConditions, privacyPolicy)
                        .build(),
                RC_SIGN_IN);
    }
}
