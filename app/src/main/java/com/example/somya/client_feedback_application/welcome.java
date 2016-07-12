package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class welcome extends Activity implements OnItemSelectedListener, AdapterView.OnItemClickListener {

    private Button Next;
    private ImageButton imageButtonBack;
    private EditText name2;
    private EditText eventdate;
    private AutoCompleteTextView orgname2;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ArrayAdapter<String> adapter;


    private Spinner spinner;
    private Spinner spinner2;
    private String item;
    private String item2;
    private int TID = 0;
    private String formid="";
    private String welcome_value="";



    SessionManagement session;
    HashMap<String, String> user;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        session = new SessionManagement(this);
        user = session.getUserDetails();
        id = user.get("id");

        imageButtonBack = (ImageButton) findViewById(R.id.imageButtonBack);
        orgname2 = (AutoCompleteTextView) findViewById(R.id.orgname2);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        Next = (Button) findViewById(R.id.Next);
        name2 = (EditText) findViewById(R.id.name2);
        eventdate = (EditText) findViewById(R.id.to_eventdate);

        spinner.setOnItemSelectedListener(this);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        findViewsById();
        setDateTimeField();

        final String str1[] = {"Hcl", "Wipro", "Nagarro", "Hindustan Times", "HP", "Aon Hewitt"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, str1);
        orgname2.setThreshold(1);
        orgname2.setAdapter(adapter);
        orgname2.setOnItemSelectedListener(this);
        orgname2.setOnItemClickListener(this);

        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.location_array, R.layout.spinner_item);
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner2.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> dataAdapter2 = ArrayAdapter.createFromResource(this, R.array.client_type_array, R.layout.spinner_item);
        dataAdapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (name2.getText().toString().equals("")) {
//                    Toast toast = Toast.makeText(welcome.this, "Enter your name",
//                            Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                }
//                else if (item2.equals("")) {
//                    Toast toast = Toast.makeText(welcome.this, "Select type",
//                            Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                }
//                else if (eventdate.getText().toString().equals("")) {
//                    Toast toast = Toast.makeText(welcome.this, "Select event date",
//                            Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                }
//                else if (item.equals("")) {
//                    Toast toast = Toast.makeText(welcome.this, "Select location",
//                            Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                }
//                else if (orgname2.getText().toString().equals("")) {
//                    Toast toast = Toast.makeText(welcome.this, "Enter organisation name",
//                            Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                }
//
//
//
//                else {

                    //calling postasync
                    String client_name = name2.getText().toString();
                    String client_type = item2;
                    String event_date = eventdate.getText().toString();
                    String org_name = orgname2.getText().toString();
                    String event_loc = item;

                    String loginid = id;
                    Log.d("Login ID", loginid);

                    new PostAsync().execute(client_name, client_type, event_date, org_name, event_loc, loginid);

                    new PostAsync2().execute(event_date,event_loc,org_name);

                    Log.d("formid",formid);




                    Log.d("date..",eventdate.getText().toString());
                    Log.d("location..",item);
                    Log.d("org..",orgname2.getText().toString());
                    Log.d("welcomevaluebutnclick",welcome_value);

                    Intent intent1 = new Intent(welcome.this,Feedback_FormT12.class);

                    intent1.putExtra("event_date",eventdate.getText().toString());
//                    //intent1.putExtra("formid",formid);
                intent1.putExtra("event_location",item);
                intent1.putExtra("organisation_name",orgname2.getText().toString());
//
                  startActivityForResult(intent1,1);

               // }
            }
        });

        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, Login.class);
                startActivity(intent);


            }
        });
    }

    class PostAsync extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/client_welcome.php";

        private static final String TAG_SUCCESS = "successs";
        private static final String TAG_MESSAGE = "message";
        //private static final String TAG_TID = "transactionID";//from php


        @Override
        protected void onPreExecute() {

            Log.d("preexec", "starting");
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                params.put("client_name3", args[0]);
                params.put("client_type3", args[1]);
                params.put("event_date3", args[2]);
                params.put("organisation_name3", args[3]);
                params.put("event_location3", args[4]);
                params.put("login_id", args[5]);

                Log.d("reqpostasyncwelcome", "starting");

                json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);
                Log.d("JSONfromphp pawelcome", json.toString());
                if (json != null) {
                    Log.d("JSON result pawelcome", json.toString());
                    return json;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(JSONObject json) {

            int success = 0;

            String message = "";
            Log.d("postexxec pawelcome", "starting");
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                try {

                    //Log.d("date..",eventdate.getText().toString());

//

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(welcome.this, "Details can not be submitted",
                        Toast.LENGTH_LONG).show();

                if (success == 1) {
                    Log.d("Success pawelcome", message);

                } else {
                    Log.d("Failure pawelcome", message);
                }
            }
        }

    }


    class PostAsync2 extends AsyncTask<String, String, JSONObject> {



        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;

        private ProgressDialog pDialog;

       private static final String LOGIN_URL = "http://10.0.3.2/postasync2_welcome.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec postexec2","start");
            pDialog = new ProgressDialog(welcome.this);
            pDialog.setMessage("Attempting...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                params.put("event_date", args[0]);
                params.put("event_location", args[1]);
                params.put("organisation_name", args[2]);

                Log.d("request pa2welcome", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result pa2welcome",json.toString());
                    return json;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject json) {

            int success = 0;
            String message = "";




            Log.d("postexxec pa2welcome","starting");
            if (pDialog != null && pDialog.isShowing())
            {
                pDialog.dismiss();
            }

            if (json != null)
            {
                JSONArray data= null;
                JSONObject data2=null;


                Log.d("request pa2welcome", "json response from http request = " + json.toString());
                try {
                        data = json.getJSONArray("details");
                        JSONObject obj=data.getJSONObject(0);
                        formid=obj.getString("formID");

                        data2=json.getJSONObject("welcome");
                        JSONObject obj2=data2.getJSONObject("welcome");
                        welcome_value=obj2.getString("welcome");


                    Log.d("formid value",formid);
                    Log.d("welcome value",welcome_value);


                    } catch (JSONException e) {
                        e.printStackTrace();
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

        switch (parent.getId()) {
            case R.id.spinner:
                item = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinner2:
                item2 = parent.getItemAtPosition(position).toString();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "You selected nothing", Toast.LENGTH_LONG).show();

    }
}
