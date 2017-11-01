package com.example.ken.navdrawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;


public class Landing extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;
    private static final int REQ_CODE = 9001;
    //private SignInButton SignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        findViewById(R.id.bn_login).setOnClickListener(this);

        //SignIn = (SignInButton) findViewById(R.id.bn_login);
        //SignIn.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        SignInButton signInButton = (SignInButton) findViewById(R.id.bn_login);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bn_login:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, REQ_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Result returned from lauching the Intent from GoogleSignIn
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            //Signed in Successfully, redirect to another Activity
            GoogleSignInAccount acct = result.getSignInAccount();
            signed_in_redirect();
        } else {
            //Signed out, do not show the redirected page
            not_signed_in_no_redirect();
        }
    }

    public void signed_in_redirect() {
        Intent intent = new Intent(Landing.this, MainActivity.class);
        startActivity(intent);

    }

    public void not_signed_in_no_redirect() {
        Intent intent = new Intent(Landing.this, Landing.class);
        startActivity(intent);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
