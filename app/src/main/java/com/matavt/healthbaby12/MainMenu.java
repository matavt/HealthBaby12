package com.matavt.healthbaby12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import io.reactivex.Observable;


import java.util.Objects;

public class MainMenu extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView welcome;
    Toolbar toolbar;
    FragmentManager fragMan;
    FragmentTransaction fragTran;
    public static HealthBabyDB hbDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Health Baby");
        welcome = findViewById(R.id.name);
        hbDB = HealthBabyDB.getInstance(MainMenu.this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account !=null) {
            String accountName = account.getDisplayName();
            welcome.setText("Welcome: " + accountName);
        }

        loadHomeMenu();
    }

//    add the custom toolbar to the app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

//    Handler for the toolbar actions.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ab_logout:
                signOutMethod();
                return true;
            case R.id.ab_home:
                loadHomeMenu();
            case R.id.ab_records:
                loadRecords();
            case R.id.ab_sync:
                loadSync();
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    void signOutMethod(){
        gsc.signOut().addOnCompleteListener(task -> {
            finish();
            startActivity(new Intent(MainMenu.this,GoogleSignin.class));
        });
    }

    void loadHomeMenu(){
        fragMan = getSupportFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.fragmentFrame, new Home()).setReorderingAllowed(true);
        fragTran.commit();
    }

    void loadRecords(){
        //to be implemented
    }

    void loadSync(){
        //to be implemented
    }
}