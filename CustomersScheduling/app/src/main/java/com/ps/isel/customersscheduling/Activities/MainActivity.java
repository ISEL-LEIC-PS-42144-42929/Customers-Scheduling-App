package com.ps.isel.customersscheduling.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.ps.isel.customersscheduling.R;


public class MainActivity extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getWindow().getDecorView().getLayoutDirection() == View.LAYOUT_DIRECTION_LTR)
        {      //RTL to LTR
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }

        //   userEmail = getIntent().getStringExtra("userEmail");
        authenticationCode();

    }

    public void authenticationCode()
    {
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = firebaseAuth -> {
            if(firebaseAuth.getCurrentUser() == null)
            {
                startActivity(new Intent(this, SignInActivity.class));
            }
        };
    }

}
