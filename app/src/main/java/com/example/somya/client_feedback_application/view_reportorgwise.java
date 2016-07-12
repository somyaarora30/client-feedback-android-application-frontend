package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class view_reportorgwise extends Activity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog2;
    private SimpleDateFormat dateFormatter2;
    private Spinner spinner;
    private EditText from_eventdate,to_eventdate,orgname;
    private Button view_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reportorgwise);


        from_eventdate=(EditText)findViewById(R.id.from_eventdate);
        to_eventdate=(EditText)findViewById(R.id.to_eventdate);
        orgname=(EditText)findViewById(R.id.orgname);
        view_report=(Button) findViewById(R.id.view_report);


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        findViewsById();
        findViewsById2();
        setDateTimeField();
        setDateTimeField2();

        view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(view_reportorgwise.this,mailreport_orgwise.class);

                intent1.putExtra("from",from_eventdate.getText().toString());
                intent1.putExtra("to",to_eventdate.getText().toString());
                intent1.putExtra("orgnm",orgname.getText().toString());
                startActivityForResult(intent1,1);



            }
        });

    }

    private void findViewsById() {

        from_eventdate=(EditText)findViewById(R.id.from_eventdate);
        from_eventdate.setInputType(InputType.TYPE_NULL);
        from_eventdate.requestFocus();


    }

    private void findViewsById2() {

        to_eventdate=(EditText)findViewById(R.id.to_eventdate);
        to_eventdate.setInputType(InputType.TYPE_NULL);
        to_eventdate.requestFocus();

    }

    private void setDateTimeField() {

        from_eventdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                from_eventdate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    private void setDateTimeField2() {

        to_eventdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog2.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();


        datePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                to_eventdate.setText(dateFormatter2.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}
