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

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this,gso);
        if(com.google.android.gms.auth.api.signin.GoogleSignIn.getLastSignedInAccount(this) != null){
            startMainMenu();
        }

        signInButton.setOnClickListener(view -> signIn());
    }

    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

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

    void startMainMenu(){
        finish();
        Intent intent = new Intent(GoogleSignIn.this,MainMenu.class);
        startActivity(intent);
    }
}