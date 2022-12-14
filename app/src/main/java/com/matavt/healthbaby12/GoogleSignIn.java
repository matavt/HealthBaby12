/*
    This is the entry activity for the app. This handles google sign-in for the app.
    If an account is already signed into the app it load the MainMenu activity.
 */

package com.matavt.healthbaby12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class GoogleSignIn extends AppCompatActivity {

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_signin);
        signInButton = findViewById(R.id.google_sign_in);

        //The handlers for google sign in API
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this,gso);
        //checks with the API if an account is already signed in
        if(com.google.android.gms.auth.api.signin.GoogleSignIn
                .getLastSignedInAccount(this) != null){
            startMainMenu();
        }

        signInButton.setOnClickListener(view -> signIn());
    }

    //Starts the google sign in intent
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    //Retrieve the result from sign in and displays an error if there is one otherwise start the
    //MainMenu activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                startMainMenu();
            } catch (ApiException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"sign in error", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    //Load the MainMenu Activity.
    void startMainMenu(){
        finish();
        Intent intent = new Intent(GoogleSignIn.this, MainMenu.class);
        startActivity(intent);
    }
}