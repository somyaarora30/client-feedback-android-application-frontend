package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;

public class thankyou extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startActivity = new Intent(thankyou.this, Login.class);
                startActivity(startActivity);
                finish();
            }
        }, 6000);
    }


}
