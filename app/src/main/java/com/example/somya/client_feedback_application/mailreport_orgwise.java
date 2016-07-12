package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class mailreport_orgwise extends Activity {
    String event_date1="";
    String event_date2="";
    String orgname="";
    String mailto="";
    TableLayout t1, t2;
    TableRow row1, row2;
    TextView text21,text22,text41,text42;
    private EditText mailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailreport_orgwise);

        Intent intent2 = getIntent();

        String from = intent2.getStringExtra("from");
        String to = intent2.getStringExtra("to");
        String orgnm = intent2.getStringExtra("orgnm");

        t1=(TableLayout)findViewById(R.id.table1);
        t2=(TableLayout)findViewById(R.id.table2);
        row1=(TableRow)findViewById(R.id.row1);
        row2=(TableRow)findViewById(R.id.row2);

        mailID=(EditText)findViewById(R.id.mailID);

        Button mail_report=(Button)findViewById(R.id.mail_report);


        Log.d("from",from);
        Log.d("to",to);


        event_date1=from;
        event_date2=to;
        orgname=orgnm;

        new PostAsync().execute(event_date1,event_date2,orgname);
        new PostAsync2().execute(event_date1,event_date2,orgname);


        mail_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailto=mailID.getText().toString();
                Log.d("mailto",mailto);
               new PostAsync3().execute(event_date1,event_date2,orgname,mailto);
                new PostAsync4().execute(event_date1,event_date2,orgname,mailto);

                Toast toast = Toast.makeText(mailreport_orgwise.this, "Mail has been sent sucessfully",
                        Toast.LENGTH_LONG);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(20);
                messageTextView.setTypeface(Typeface.SERIF);
                toast.show();


                Intent intent = new Intent(
                        mailreport_orgwise.this,Login.class);
                startActivity(intent);


            }


        });
    }

    class PostAsync extends AsyncTask<String, String, JSONObject>{

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/report_topicnames2.php";


        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec", "starting");

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                params.put("event_date1", args[0]);
                params.put("event_date2", args[1]);
                params.put("orgname", args[2]);

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);


                if (json != null) {

                    Log.d("JSON result", json.toString());
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

            Log.d("post exec", "starting");

            Log.d("json", json.toString());

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null)
            {

                Log.d("json", "not null");
                JSONArray data = null;

                try {

                    t1.setColumnStretchable(0, true);
                    t1.setColumnStretchable(1, true);


                    data = json.getJSONArray("details");

                    String array_topic_names[]=new String[data.length()];
                    String array_scores[]=new String[data.length()];



                    Log.d("JSON result",json.toString());
                    Log.d("datalength", String.valueOf(data.length()));

                    for(int i=0;i<data.length();i++) {

                        JSONObject obj = data.getJSONObject(i);

                        Log.d("obj_value", obj.toString());

                        array_topic_names[i]=obj.getString("topic_name2");
                        array_scores[i]=obj.getString("avg(score2)");

                        Log.d("array_topic_names", array_topic_names[i]);
                        Log.d("array_scores", array_scores[i]);

                        TableRow row2 = new TableRow(mailreport_orgwise.this);
                        TextView text21 = new TextView(mailreport_orgwise.this);
                        TextView text22 = new TextView(mailreport_orgwise.this);

                        text21.setText(array_topic_names[i]);
                        text22.setText(array_scores[i]);

                        text21.setTextColor(Color.parseColor("#000000"));
                        text22.setTextColor(Color.parseColor("#000000"));

                        text21.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        text22.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

                        text21.setTypeface(Typeface.SERIF);
                        text22.setTypeface(Typeface.SERIF);

                        text22.setGravity(Gravity.RIGHT);
                        row2.setGravity(Gravity.CENTER);

                        row2.addView(text21);
                        row2.addView(text22);

                        t1.addView(row2);



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }


    }

    class PostAsync2 extends AsyncTask<String, String, JSONObject>{

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/report_questionnames2.php";


        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec", "starting");

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                params.put("event_date1", args[0]);
                params.put("event_date2", args[1]);
                params.put("orgname", args[2]);
                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);


                if (json != null) {

                    Log.d("JSON result", json.toString());
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

            Log.d("post exec", "starting");

            Log.d("json", json.toString());

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null)
            {

                Log.d("json", "not null");
                JSONArray data = null;

                try {


                    t2.setColumnStretchable(0, true);
                    t2.setColumnStretchable(1, true);

                    data = json.getJSONArray("details");

                    String array_question_names[]=new String[data.length()];
                    String array_answers[]=new String[data.length()];


                    Log.d("JSON result",json.toString());
                    Log.d("datalength", String.valueOf(data.length()));

                    for(int i=0;i<data.length();i++) {

                        JSONObject obj = data.getJSONObject(i);

                        Log.d("obj_value", obj.toString());

                        array_question_names[i]=obj.getString("question_name");
                        array_answers[i]=obj.getString("avg(answer)");

                        Log.d("array_question_names", array_question_names[i]);
                        Log.d("array_answers", array_answers[i]);

                        TableRow row4 = new TableRow(mailreport_orgwise.this);
                        TextView text41 = new TextView(mailreport_orgwise.this);
                        TextView text42 = new TextView(mailreport_orgwise.this);

                        text41.setText(array_question_names[i]);
                        text42.setText(array_answers[i]);

                        text41.setTextColor(Color.parseColor("#000000"));
                        text42.setTextColor(Color.parseColor("#000000"));

                        text41.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                        text42.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

                        text41.setTypeface(Typeface.SERIF);
                        text42.setTypeface(Typeface.SERIF);

                        text42.setGravity(Gravity.RIGHT);
                        row4.setGravity(Gravity.CENTER);

                        row4.addView(text41);
                        row4.addView(text42);

                        t2.addView(row4);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }


    }

    class PostAsync3 extends AsyncTask<String, String, JSONObject>{

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/mail4.php";


        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec", "starting");

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                params.put("event_date1", args[0]);
                params.put("event_date2", args[1]);
                params.put("orgname", args[2]);
                params.put("emailto", args[3]);

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);


                if (json != null) {

                    Log.d("JSON result", json.toString());
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

            Log.d("post exec", "starting");

            Log.d("json", json.toString());

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null)
            {

                Log.d("json", "not null");
                JSONArray data = null;

                try {

                    data = json.getJSONArray("details");

                    Log.d("JSON result",json.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }


    }

    class PostAsync4 extends AsyncTask<String, String, JSONObject>{

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/mail5.php";


        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec", "starting");

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                params.put("event_date1", args[0]);
                params.put("event_date2", args[1]);
                params.put("orgname", args[2]);
                params.put("emailto", args[3]);

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);


                if (json != null) {

                    Log.d("JSON result", json.toString());
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

            Log.d("post exec", "starting");

            Log.d("json", json.toString());

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null)
            {

                Log.d("json", "not null");
                JSONArray data = null;

                try {

                    data = json.getJSONArray("details");

                    Log.d("JSON result",json.toString());


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }


    }





}
