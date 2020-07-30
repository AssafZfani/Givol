package com.technology.givol;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String URL_REGISTER = "http://findnearby.biz/contest-app-new/apis/user_register.php";
    public static final String PREFS_NAME = "GivolLoginPref";
    private static final String TAG = MainActivity.class.getSimpleName();
    private static int RC_SIGN_IN = 111;
    Button sign_upBtn;
    Button google_sign_up_btn, btnSignUpFaceBook;
    TextInputEditText email_edt_register;
    EditText edt_password_register;
    String f_name, l_name, e_mail, pass_word;
    String type_logout;
    TextView move_sign_up_txt;
    String termsAndConditions = "https://example.com/terms.html",
            privacyPolicy = "https://example.com/privacy.html";
    private GoogleSignInAccount account;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        sign_upBtn = (Button) findViewById(R.id.sign_upBtn);
        move_sign_up_txt = (TextView) findViewById(R.id.move_sign_up_txt);
        google_sign_up_btn = (Button) findViewById(R.id.google_sign_up_btn);
        btnSignUpFaceBook = (Button) findViewById(R.id.btnSignUpFaceBook);
        sign_upBtn.setOnClickListener(this);
        move_sign_up_txt.setOnClickListener(this);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
        btnSignUpFaceBook.setOnClickListener(this);
        google_sign_up_btn.setOnClickListener(this);
        // first_edt_register=(TextInputEditText)findViewById(R.id.first_edt_register);
        // lasr_edt_register=(TextInputEditText)findViewById(R.id.lasr_edt_register);
        email_edt_register = (TextInputEditText) findViewById(R.id.email_edt_register);
        edt_password_register = (EditText) findViewById(R.id.edt_password_register);
        edt_password_register.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Calculate password strength
                calculateStrength(editable.toString());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_upBtn:
//                Intent intent=new Intent(getApplicationContext(),CurrentLocationActivity.class);
//                startActivity(intent);
                // f_name=first_edt_register.getText().toString().trim();
                // l_name=lasr_edt_register.getText().toString().trim();
                e_mail = email_edt_register.getText().toString().trim();
                pass_word = edt_password_register.getText().toString().trim();
                postNewRegisterUser(e_mail, pass_word);
                break;

            case R.id.google_sign_up_btn:
                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.GoogleBuilder().build());
                LoginStatus(providers);
                break;
            case R.id.btnSignUpFaceBook:
                List<AuthUI.IdpConfig> providers1 = Arrays.asList(
                        new AuthUI.IdpConfig.FacebookBuilder().build());

                LoginStatus(providers1);
                break;
            case R.id.move_sign_up_txt:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
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
                    String user_name = user.getDisplayName();
                    String user_email = user.getEmail();
                    String user_password = user.getUid();
                    Log.e(TAG, user.getUid());
                    // FirebaseAuth.getInstance().signOut();

                    postNewRegisterUser1(user_email, user_password);

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

//    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }

//    private void handleSignInResult(GoogleSignInResult result) {
//        Log.e(TAG, "handleSignInResult:" + result.isSuccess());
//        if (result.isSuccess()) {
//            account = result.getSignInAccount();
//            Log.e(TAG,account.getDisplayName());
//            Log.e(TAG,account.getEmail());
//            Log.e(TAG,account.getId());
//            Log.e(TAG,account.getGivenName());
//            Log.e(TAG,account.getFamilyName());
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
////            final ProgressDialog progressDialog = new ProgressDialog(this);
////            progressDialog.setMessage("Checking sign in state...");
////            progressDialog.show();
//            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                @Override
//                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
//                   // progressDialog.dismiss();
//                    handleSignInResult(googleSignInResult);
//                }
//            });
//        }
//   }


//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
//        // be available.
//        Log.d(TAG, "onConnectionFailed:" + connectionResult);
//    }
//    public  void updateUI(boolean logged) {
//        if (logged) {
//
//            type_logout="Google";
//            // intent.putExtra("LOGOUT",type_logout);
//            // SharedPreferences settings1 = getSharedPreferences(LOG_OUT_NAME, 0);
//            // SharedPreferences.Editor editor = settings1.edit();
//            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//            SharedPreferences.Editor editor = settings.edit();
//            // editor.putString("logged", "true");
//            editor.putString("LOG_OUT",type_logout);
//            editor.putString("USER_ID",account.getId());
//            editor.putString("USER_NAME",account.getFamilyName());
//           // editor.putString("GIVEN_NAME",account.getGivenName());
//            editor.putString("EMAIL",account.getEmail());
//            //  editor.putString("LOGOUT",type_logout);
////            editor.putString("GOOGLE_USR_ID",account.getId());
////            editor.putString("GOOGLE_USR_NAME", account.getDisplayName());
////            editor.putString("GOOGLE_EMAIL",account.getEmail());
////            editor.putString("GOOGLE_IMG_URL", account.getPhotoUrl().toString());
//            editor.commit();
//            Intent intent=new Intent(SignUpActivity.this,UserProfileUpdateActivity.class);
////            intent.putExtra("GOOGLE_USR_ID",account.getId());
////            intent.putExtra("GOOGLE_USR_NAME", account.getDisplayName());
////            intent.putExtra("GOOGLE_EMAIL",account.getEmail());
////            intent.putExtra("GOOGLE_IMG_URL", account.getPhotoUrl().toString());
//            startActivity(intent);
//            finish();
//            // google_sign_in_btn.setVisibility(View.GONE);
////           // btnSignOut.setVisibility(View.VISIBLE);
////           // btnRevokeAccess.setVisibility(View.VISIBLE);
//            // llProfileLayout.setVisibility(View.VISIBLE);
//        }
    // }


    private void postNewRegisterUser(final String email1, final String password1) {
        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(this, "plz enter your email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(this, "plz enter your password ", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);


        final StringRequest sr = new StringRequest(Request.Method.POST, URL_REGISTER, new Response.Listener<String>() {
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
                        String id = user.getString("id");
                        String first_name = user.getString("first_name");
                        String email = user.getString("email");
                        // ViewDialog1 alertDialoge = new ViewDialog1(getApplicationContext());
                        // alertDialoge.alertDialogShow(SignUpActivity.this, "SUCCESSFULLY REGISTERED");
                        ViewDialog2 alertDialoge = new ViewDialog2(getApplicationContext());
                        alertDialoge.showDialog(SignUpActivity.this, "SUCCESSFULLY REGISTERED");

//                        Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
//                        // intent.putExtra("USER_NAME1",email1);
//                        startActivity(intent);
                        // pDialog.hide();

                    } else if (success.equalsIgnoreCase("false")) {
                        String message = jsonObject.getString("message");
                        if (message.equalsIgnoreCase("User_With_This_Email_Already_Exits")) {
                            ViewDialog alertDialoge = new ViewDialog();
                            alertDialoge.showDialog(SignUpActivity.this, "ALREADY REGISTERED PLEASE SIGN IN");
                            // Toast.makeText(getApplicationContext(), "ALREADY REGISTERED PLEASE SIGN IN", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(SignUpActivity.this, CurrentLocationActivity.class);
//                            // intent.putExtra("USER_NAME1",email1);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "SOME ERROR OCCURED", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SignUpActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                //  progree_bar.setVisibility(View.GONE);


            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("first_name", "null");
                params.put("last_name", "null");
                params.put("email", email1);
                params.put("password", password1);
                params.put("ukey", "hizZnFpugtwE2FPwlDuUNOT33bfeDmS5");
                return params;
            }


        };
        queue.add(sr);

    }

    private void postNewRegisterUser1(final String email1, final String password1) {
        if (TextUtils.isEmpty(email1)) {
            Toast.makeText(this, "plz enter your email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password1)) {
            Toast.makeText(this, "plz enter your password ", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);


        final StringRequest sr = new StringRequest(Request.Method.POST, URL_REGISTER, new Response.Listener<String>() {
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
                        String id = user.getString("id");
                        String first_name = user.getString("first_name");
                        String email = user.getString("email");
                        // ViewDialog1 alertDialoge = new ViewDialog1(getApplicationContext());
                        // alertDialoge.alertDialogShow(SignUpActivity.this, "SUCCESSFULLY REGISTERED");
                        ViewDialog3 alertDialoge = new ViewDialog3(getApplicationContext());
                        alertDialoge.showDialog(SignUpActivity.this, "SUCCESSFULLY REGISTERED");

//                        Intent intent=new Intent(SignUpActivity.this,MainActivity.class);
//                        // intent.putExtra("USER_NAME1",email1);
//                        startActivity(intent);
                        // pDialog.hide();

                    } else if (success.equalsIgnoreCase("false")) {
                        String message = jsonObject.getString("message");
                        if (message.equalsIgnoreCase("User_With_This_Email_Already_Exits")) {
                            // ViewDialog alertDialoge = new ViewDialog();
                            // alertDialoge.showDialog(SignUpActivity.this, "ALREADY REGISTERED PLEASE SIGN IN");
                            // ViewDialog3 alertDialoge1 = new ViewDialog3(getApplicationContext());
                            // alertDialoge1.showDialog(SignUpActivity.this, "ALREADY REGISTERED");

                            // Toast.makeText(getApplicationContext(), "ALREADY REGISTERED PLEASE SIGN IN", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, CurrentLocationActivity.class);
                            intent.putExtra("USER_NAME1", email1);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "SOME ERROR OCCURED", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(SignUpActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                //  progree_bar.setVisibility(View.GONE);


            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("first_name", "null");
                params.put("last_name", "null");
                params.put("email", email1);
                params.put("password", password1);
                params.put("ukey", "hizZnFpugtwE2FPwlDuUNOT33bfeDmS5");
                return params;
            }


        };
        queue.add(sr);

    }
//    public void postNewRegisterUser1(final String email1, final String password1, final String first_name1) {
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        final StringRequest sr = new StringRequest(Request.Method.POST,URL_REGISTER, new Response.Listener<String>() {
//            public void onResponse(String response) {
//                //String str = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    String success=jsonObject.getString("success");
////                    if (jsonObject.getString("success").equalsIgnoreCase(str)) {
////                        SignUpActivity.this.is_success_logged_in = true;
////                        JSONObject user = jsonObject.getJSONObject(ShareConstants.WEB_DIALOG_PARAM_DATA).getJSONObject("user");
////                        String id = user.getString("id");
//                        if (success.equalsIgnoreCase("true")) {
//                            JSONObject data = jsonObject.getJSONObject("data");
//                            JSONObject user=data.getJSONObject("user");
//                        String first_name = user.getString("first_name");
//                        String email = user.getString("email");
////                        Editor editor = SignUpActivity.this.getSharedPreferences("GivolLoginPref", 0).edit();
////                       // editor.putString("logged", str);
////                        editor.putString("USER_ID", id);
////                        editor.putString("USER_NAME", first_name);
////                        editor.putString("EMAIL", email);
////                        Context applicationContext = SignUpActivity.this.getApplicationContext();
////                        StringBuilder sb = new StringBuilder();
////                        sb.append("Register-true: ");
////                        sb.append(SignUpActivity.this.is_success_logged_in);
//                       Toast.makeText(SignUpActivity.this, "user_sign_up", Toast.LENGTH_SHORT).show();
////                        editor.commit();
//                           UpdateUI();
//                           // ViewDialog2 alertDialoge = new ViewDialog2(getApplicationContext());
//                          // alertDialoge.showDialog(SignUpActivity.this, "SUCCESSFULLY REGISTERED");
//                    }
//                        else if(success.equalsIgnoreCase("false"))
//                        {
//                            Toast.makeText(SignUpActivity.this, "not_sign_up", Toast.LENGTH_SHORT).show();
//                        }
////                    if (SignUpActivity.this.is_success_logged_in) {
////                        SignUpActivity.this.updateUI(true);
////                        return;
////                    }
////                    if (SignUpActivity.this.type_logout == "Google") {
////                        Auth.GoogleSignInApi.signOut(SignUpActivity.this.mGoogleApiClient);
////                    } else if (SignUpActivity.this.type_logout == "Facebook") {
////                        LoginManager.getInstance().logOut();
////                    }
////                    SignUpActivity.this.updateUI(false);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(SignUpActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            /* access modifiers changed from: protected */
//            public Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("first_name", first_name1);
//                params.put("last_name", "null");
//                params.put("email", email1);
//                params.put("password", password1);
//                params.put("ukey", "hizZnFpugtwE2FPwlDuUNOT33bfeDmS5");
//                return params;
//            }
//        };
//        queue.add(sr);
//        //return this.is_success_logged_in;
//    }

    private void calculateStrength(String passwordText) {
        int upperChars = 0, lowerChars = 0, numbers = 0,
                specialChars = 0, otherChars = 0, strengthPoints = 0;
        char c;

        int passwordLength = passwordText.length();

        if (passwordLength == 0) {
            //  tvPasswordStrength.setText("Invalid Password");
            //tvPasswordStrength.setBackgroundColor(Color.RED);
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
            return;
        }

        //If password length is <= 5 set strengthPoints=1
        if (passwordLength <= 5) {
            strengthPoints = 1;
        }
        //If password length is >5 and <= 10 set strengthPoints=2
        else if (passwordLength <= 10) {
            strengthPoints = 2;
        }
        //If password length is >10 set strengthPoints=3
        else
            strengthPoints = 3;
        // Loop through the characters of the password
        for (int i = 0; i < passwordLength; i++) {
            c = passwordText.charAt(i);
            // If password contains lowercase letters
            // then increase strengthPoints by 1
            if (c >= 'a' && c <= 'z') {
                if (lowerChars == 0) strengthPoints++;
                lowerChars = 1;
            }
            // If password contains uppercase letters
            // then increase strengthPoints by 1
            else if (c >= 'A' && c <= 'Z') {
                if (upperChars == 0) strengthPoints++;
                upperChars = 1;
            }
            // If password contains numbers
            // then increase strengthPoints by 1
            else if (c >= '0' && c <= '9') {
                if (numbers == 0) strengthPoints++;
                numbers = 1;
            }
            // If password contains _ or @
            // then increase strengthPoints by 1
            else if (c == '_' || c == '@') {
                if (specialChars == 0) strengthPoints += 1;
                specialChars = 1;
            }
            // If password contains any other special chars
            // then increase strengthPoints by 1
            else {
                if (otherChars == 0) strengthPoints += 2;
                otherChars = 1;
            }
        }

        if (strengthPoints <= 3) {
            Toast.makeText(this, "Password Strength:" + "LOW", Toast.LENGTH_SHORT).show();
            //tvPasswordStrength.setText("Password Strength : LOW");
            //tvPasswordStrength.setBackgroundColor(Color.RED);
        } else if (strengthPoints <= 6) {
            //  tvPasswordStrength.setText("Password Strength : MEDIUM");
            // tvPasswordStrength.setBackgroundColor(Color.YELLOW);
            Toast.makeText(this, "Password Strength:" + "MEDIUM", Toast.LENGTH_SHORT).show();
        } else if (strengthPoints <= 9) {
//            tvPasswordStrength.setText("Password Strength : HIGH");
//            tvPasswordStrength.setBackgroundColor(Color.GREEN);
            Toast.makeText(this, "Password Strength:" + "HIGH", Toast.LENGTH_SHORT).show();
        }
    }

    private void UpdateUI() {
        Intent intent = new Intent(SignUpActivity.this, CurrentLocationActivity.class);
        startActivity(intent);
        finish();
    }

    public void LoginStatus(List<AuthUI.IdpConfig> provider) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(provider)
                            .setLogo(R.drawable.givol)
                            .setIsSmartLockEnabled(false)// Set logo drawable
                            .setTosAndPrivacyPolicyUrls(termsAndConditions, privacyPolicy)
                            .build(),
                    RC_SIGN_IN);

        } else {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(this, "Please click again", Toast.LENGTH_SHORT).show();
        }

    }
}
