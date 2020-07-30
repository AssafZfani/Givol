package com.technology.givol;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashAvtivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT = 3000;
    //private static int RC_SIGN_IN = 111;
//    String termsAndConditions = "https://example.com/terms.html",
//            privacyPolicy = "https://example.com/privacy.html"; //TODO: Add TAC and Policy links
//    private static final String TAG = SplashAvtivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_avtivity);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashAvtivity.this,
                        MainActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
        // Choose authentication providers
       /* List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
         */
        //Gives user if logged else null


       /* You can use above line to check user login or use u
       ser.getUID for other details.
       To signOut Use
       FirebaseAuth.getInstance().signOut();
       *  */

       /* if (user == null) {
            //User Not Found TODO:SignIN
            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setLogo(R.drawable.givol)
                            .setIsSmartLockEnabled(false)// Set logo drawable
                            .setTosAndPrivacyPolicyUrls(termsAndConditions, privacyPolicy)
                            .build(),
                    RC_SIGN_IN);
        } else {
            //User Found TODO:GoTo home
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                   UpdateUI();
                }
            }, SPLASH_SCREEN_TIME_OUT);
        }*/


    }

   /* private void UpdateUI() {
        Intent intent=new Intent(SplashAvtivity.this,CurrentLocationActivity.class);
        startActivity(intent);
        finish();
    }*/

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
                IdpResponse response = IdpResponse.fromResultIntent(data);

                if (resultCode == RESULT_OK) {
                    // Successfully signed in
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user!=null){

                        Log.e(TAG, Objects.requireNonNull(user.getDisplayName()));
                        Log.e(TAG, Objects.requireNonNull(user.getEmail()));
                        Log.e(TAG,user.getUid());

                        UpdateUI();
                    }

                } else {
                    // Sign in failed. If response is null the user canceled the
                    // sign-in flow using the back button. Otherwise check
                    // response.getError().getErrorCode() and handle the error.
                    // ...
                }
        }
    }*/
}
