package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin_landing extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing);

        Button add_button = (Button) findViewById(R.id.add_button);
        Button delete_button = (Button) findViewById(R.id.delete_button);
        Button modify_button = (Button) findViewById(R.id.modify_button);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        admin_landing.this, create_form.class);
                startActivity(intent);

            }


        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        admin_landing.this, delete_visit_details.class);
                startActivity(intent);

            }


        });

        modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        admin_landing.this, modify_visit_details.class);
                startActivity(intent);

            }


        });
    }
}
