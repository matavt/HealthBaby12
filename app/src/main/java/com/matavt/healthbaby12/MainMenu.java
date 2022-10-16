package com.matavt.healthbaby12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class MainMenu extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView welcome, childDetails;
    Toolbar toolbar;
    FragmentManager fragMan;
    FragmentTransaction fragTran;
    public static HealthBabyDB hbDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Health Baby");
        welcome = findViewById(R.id.name);
        hbDB = HealthBabyDB.getInstance(MainMenu.this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount account = com.google.android.gms.auth.api.signin.GoogleSignIn
                .getLastSignedInAccount(this);
        if(account !=null) {
            User.getInstance().setUserName(account.getDisplayName());
            welcome.setText(MessageFormat.format("Welcome: {0}", User.getInstance().getUserName()));
        }
        childDetails = findViewById(R.id.ChildName);
        final Disposable child_frag = hbDB.daoChild().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        list -> {
                            try {
                                EntityChild child = list.get(0);
                                User.getInstance().setChildName(child.getName());
                                User.getInstance().setChildDoB(child.getDate());
                                childDetails.setClickable(false);
                                GregorianCalendar childDob = child.getDate();
                                String StringDate = DateFunctions.createStringFromDate(
                                        childDob.get(Calendar.YEAR), childDob.get(Calendar.MONTH) + 1,
                                        childDob.get(Calendar.DAY_OF_MONTH));
                                childDetails.setText(String.format("%s Born: %s", child.getName(), StringDate));
                                getSupportFragmentManager().beginTransaction().remove(Objects.requireNonNull(getSupportFragmentManager().findFragmentByTag("CHILD_FRAG"))).commit();
                            } catch (Exception e) {
                                //do nothing.
                            }
                        });
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
                return true;
            case R.id.ab_records:
                loadRecords();
                return true;
            case R.id.ab_clearData:
                loadReset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void signOutMethod(){
        gsc.signOut().addOnCompleteListener(task -> {
            finish();
            startActivity(new Intent(MainMenu.this, GoogleSignIn.class));
        });
    }

    void loadHomeMenu(){
        fragMan = getSupportFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.fragmentFrame, new Home()).setReorderingAllowed(true);
        fragTran.commit();
    }

    void loadRecords(){
        fragMan = getSupportFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.fragmentFrame, new Records()).setReorderingAllowed(true);
        fragTran.commit();
    }

    void loadReset(){
         Completable.fromAction(() -> hbDB.clearAllTables())
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe();
        signOutMethod();

    }

    public void addChild(View view) {
        fragMan = getSupportFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.childFrame, new AddChild(), "CHILD_FRAG").setReorderingAllowed(true);
        fragTran.commit();
    }
}