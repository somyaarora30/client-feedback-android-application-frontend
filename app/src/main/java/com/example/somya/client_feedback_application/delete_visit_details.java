package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class delete_visit_details extends Activity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private Spinner spinner;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView orgname2;
    private EditText eventdate;
    private Button delete_button;
    private String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_visit_details);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        orgname2 = (AutoCompleteTextView) findViewById(R.id.orgname2);
        delete_button=(Button)findViewById(R.id.delete_button);

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


        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventdate.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(delete_visit_details.this, "Enter event date",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else {


                    //calling postasync

                    String event_date = eventdate.getText().toString();
                    String org_name = orgname2.getText().toString();
                    String event_loc = item;

                    new PostAsync().execute(event_date, event_loc,org_name);

                    Toast toast = Toast.makeText(delete_visit_details.this, "Visit details deleted",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();



                }
            }
        });
    }

    class PostAsync extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/delete_visit_details.php";

        private static final String TAG_SUCCESS = "successs";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {

            Log.d("preexec", "starting");
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();


                params.put("event_date", args[0]);

                params.put("event_location", args[1]);
                params.put("organisation_name", args[2]);


                Log.d("request", "starting");

                json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);
                Log.d("JSON from php", json.toString());
                if (json != null) {
                    Log.d("JSON result", json.toString());
                    return json;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(JSONObject jsoni) {

            int success = 0;

            String message = "";
            Log.d("postexxec", "starting");
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                try {
                    success = json.getInt(TAG_SUCCESS);
                    Log.d("Json","Not null");
                    if (success == 1) {
                        Log.d("Success", message);

                    } else {
                        Log.d("Failure", message);
                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(delete_visit_details.this, "Visit details can not be deleted",
                        Toast.LENGTH_LONG).show();

                if (success == 1) {
                    Log.d("Success", message);

                } else {
                    Log.d("Failure", message);
                }
            }
        }

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
