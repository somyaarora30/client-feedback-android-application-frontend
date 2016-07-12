package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class modify_visit_details extends Activity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{
    private Spinner spinner;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView orgname2;
    private EditText eventdate;
    private Button modify_button;
    private String item;
    private String test1;
    private String test2;
    private String test3;
    private String Topic1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_visit_details);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        orgname2 = (AutoCompleteTextView) findViewById(R.id.orgname2);
        modify_button=(Button)findViewById(R.id.analyse_button);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        findViewsById();
        setDateTimeField();

        final String str1[] = {"Hcl", "Wipro", "Nagarro", "Hindustan Times", "HP", "Aon Hewitt"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, str1);
        orgname2.setThreshold(1);
        orgname2.setAdapter(adapter);
        orgname2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        orgname2.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.location_array, R.layout.spinner_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        modify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventdate.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(modify_visit_details.this, "Enter event date",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else {


                    Intent intent1 = new Intent(modify_visit_details.this,modify_create_form.class);

                    intent1.putExtra("evtdt",eventdate.getText().toString());
                    intent1.putExtra("evtloc",item);
                    intent1.putExtra("orgnm",orgname2.getText().toString());
                    startActivityForResult(intent1,1);


                }
            }
        });
    }

    private void findViewsById() {
        eventdate = (EditText) findViewById(R.id.to_eventdate);
        eventdate.setInputType(InputType.TYPE_NULL);
        eventdate.requestFocus();
    }

    private void setDateTimeField() {

        eventdate.setOnClickListener(new View.OnClickListener() {
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
                eventdate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
