package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class view_dateororgwise extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dateororgwise);

        Button datewiseview_button=(Button)findViewById(R.id.view_button);
        Button orgwiseview_button=(Button)findViewById(R.id.analyse_button);

        datewiseview_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_dateororgwise.this, viewreportdatewise.class);
                startActivity(intent);


            }
        });

        orgwiseview_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_dateororgwise.this, view_reportorgwise.class);
                startActivity(intent);


            }
        });
    }
}
