package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Feedback_FormQ34 extends Activity {

    TextView question3,question4;
    RadioGroup radiogroup1;
    RadioGroup radiogroup2;
    RadioButton radiogroup1button1;
    RadioButton radiogroup1button2;
    RadioButton radiogroup2button1;
    RadioButton radiogroup2button2;
    TextView textView9,textView12;

    float answer3 = 0, answer4 = 0;
    String event_date,event_location,organisation_name;
    String answer3str,answer4str;

    private GestureDetectorCompat gestureDetectorCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_form_q34);
        gestureDetectorCompat = new GestureDetectorCompat(this, new My4thGestureListener());

        question3=(TextView)findViewById(R.id.question3);
        question4=(TextView)findViewById(R.id.question4);

        radiogroup1=(RadioGroup) findViewById(R.id.radiogroup1);
        radiogroup2=(RadioGroup) findViewById(R.id.radiogroup2);
        radiogroup1button1=(RadioButton)findViewById(R.id.radiogroup1button1);
        radiogroup1button2=(RadioButton)findViewById(R.id.radiogroup1button2);
        radiogroup2button1=(RadioButton)findViewById(R.id.radiogroup2button1);
        radiogroup2button2=(RadioButton)findViewById(R.id.radiogroup2button2);
        textView9=(TextView)findViewById(R.id.textView9);
        textView12=(TextView)findViewById(R.id.textView12);

        Intent intent2 = getIntent();
        event_date = intent2.getStringExtra("event_date");
        event_location = intent2.getStringExtra("event_location");
        organisation_name = intent2.getStringExtra("organisation_name");

        new PostAsync().execute(event_date,event_location,organisation_name);

        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if(checkedId == R.id.radiogroup1button1) {

                    Toast.makeText(Feedback_FormQ34.this, "YES",

                            Toast.LENGTH_SHORT).show();

                    Log.d("First Button value","Yes");
                    answer3=11;


                } else if(checkedId == R.id.radiogroup1button2) {

                    Toast.makeText(Feedback_FormQ34.this, "NO",

                            Toast.LENGTH_SHORT).show();
                    Log.d("First Button value","No");
                    answer3=00;

                }

                Log.d("answer3 value:",String.valueOf(answer3));
                Log.d("Calling:","PostAsync_Rating");
                answer3str= (String.valueOf(answer3));
                new PostAsync_Rating1().execute(answer3str,event_date,event_location,organisation_name);

            }



        });

        radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if(checkedId == R.id.radiogroup2button1) {

                    Toast.makeText(Feedback_FormQ34.this, "YES",

                            Toast.LENGTH_SHORT).show();
                    Log.d("First Button value","Yes");
                    answer4=11;

                } else if(checkedId == R.id.radiogroup2button2) {

                    Toast.makeText(Feedback_FormQ34.this, "NO",

                            Toast.LENGTH_SHORT).show();
                    Log.d("First Button value","No");
                    answer4=00;

                }
                Log.d("answer1 value:",String.valueOf(answer4));
                Log.d("Calling:","PostAsync_Rating");

                answer4str= (String.valueOf(answer4));
                new PostAsync_Rating2().execute(answer4str,event_date,event_location,organisation_name);

            }


        });




    }

    class PostAsync extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/client_feedbackQ34.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec","starting");
            pDialog = new ProgressDialog(Feedback_FormQ34.this);
            pDialog.setMessage(" ");
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


                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);

                if (json != null) {
                    Log.d("JSON result",json.toString());
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

            if (json != null) {
                {
                    Log.d("json", "not null");
                    String q3="";
                    String q4="";
                    JSONArray data= null;
                    try {
                        data = json.getJSONArray("details");

                        Log.w("app",data.toString() + " data output");

                        JSONObject obj=data.getJSONObject(0);
                        q3=obj.getString("question3");
                        q4=obj.getString("question4");

                        if(q3.equals("null")||q3.equals("")){
                            Intent intent= new Intent(Feedback_FormQ34.this,FeedbackFormComments.class);
                            startActivity(intent);

                        }
                        else if(q4.equals("null")||q4.equals(""))
                        {
                            question4.setText(q4);
                            textView12.setVisibility(View.GONE);
                            radiogroup1.setVisibility(View.GONE);

                        }
                        else if(!q3.equals("null")||!q3.equals("")){
                            question3.setText(q3);
                            question4.setText(q4);
                            //startT34
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    success = json.getInt(TAG_SUCCESS);
                    message = json.getString(TAG_MESSAGE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {

                Log.d("json", "null");
                Toast toast = Toast.makeText(
                        Feedback_FormQ34.this,
                        "unsuccessful",
                        Toast.LENGTH_LONG
                );
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(20);
                messageTextView.setTypeface(Typeface.SERIF);
                toast.show();

                if (success == 1) {
                    Log.d("Success", message);
                } else {
                    Log.d("Failure", message);
                }
            }
        }

    }

    class PostAsync_Rating1 extends AsyncTask<String, String, JSONObject> {



        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String RATING_URL = "http://10.0.3.2/client_answer34f.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec2", "starting");

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                params.put("answer3",args[0]);

                params.put("event_date", args[1]);
                params.put("event_location", args[2]);
                params.put("organisation_name", args[3]);



                JSONObject json = jsonParser.makeHttpRequest(
                        RATING_URL, "POST", params);


                if (json != null) {
                    Log.d("RATING result", json.toString());
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

            Log.d("post exec2", "starting");

            Log.d("json", json.toString());

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                {
                    Log.d("json", "not null");

                }
            }

        }


    }

    class PostAsync_Rating2 extends AsyncTask<String, String, JSONObject> {



        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String RATING_URL = "http://10.0.3.2/client_answer34s.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec2", "starting");

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();

                params.put("answer4",args[0]);

                params.put("event_date", args[1]);
                params.put("event_location", args[2]);
                params.put("organisation_name", args[3]);



                JSONObject json = jsonParser.makeHttpRequest(
                        RATING_URL, "POST", params);


                if (json != null) {
                    Log.d("RATING result", json.toString());
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

            Log.d("post exec2", "starting");

            Log.d("json", json.toString());

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if (json != null) {
                {
                    Log.d("json", "not null");

                }
            }

        }


    }

    class PostAsync2 extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();
        JSONObject json = null;

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/report_Q.php";

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
                params.put("question_name", args[1]);
                params.put("answer", args[2]);
                params.put("orgname", args[3]);


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



                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(Feedback_FormQ34.this, "json null",
                        Toast.LENGTH_LONG).show();

                if (success == 1) {
                    Log.d("Success", message);

                } else {
                    Log.d("Failure", message);
                }
            }
        }

    }



//    class PostAsync extends AsyncTask<String, String, JSONObject> {
//
//        JSONParser jsonParser = new JSONParser();
//
//        private ProgressDialog pDialog;
//
//        private static final String LOGIN_URL = "http://10.0.3.2/client_feedbackQ34.php";
//
//        private static final String TAG_SUCCESS = "success";
//        private static final String TAG_MESSAGE = "message";
//
//
//        @Override
//        protected void onPreExecute() {
//            Log.d("preexec","starting");
//            pDialog = new ProgressDialog(Feedback_FormQ34.this);
//            pDialog.setMessage(" ");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(true);
//            pDialog.show();
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... args) {
//
//            try {
//
//                HashMap<String, String> params = new HashMap<>();
//
//
//                JSONObject json = jsonParser.makeHttpRequest(
//                        LOGIN_URL, "POST", params);
//
//                if (json != null) {
//                    Log.d("JSON result",json.toString());
//                    return json;
//                }
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        protected void onPostExecute(JSONObject json) {
//
//            int success = 0;
//            String message = "";
//
//            Log.d("post exec", "starting");
//
//            Log.d("json", json.toString());
//            if (pDialog != null && pDialog.isShowing()) {
//                pDialog.dismiss();
//            }
//
//            if (json != null) {
//                {
//                    Log.d("json", "not null");
//                    String q3="";
//                    String q4="";
//                    JSONArray data= null;
//                    try {
//                        data = json.getJSONArray("details");
//                        JSONObject obj=data.getJSONObject(0);
//                        q3=obj.getString("question3");
//                        q4=obj.getString("question4");
//                        //session.createLoginSession(obj.getString("name"),obj.getString("loginid"));
//
//                        question3.setText(q3);
//                        question4.setText(q4);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                try {
//                    success = json.getInt(TAG_SUCCESS);
//                    message = json.getString(TAG_MESSAGE);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//            else {
//
//                Log.d("json", "null");
//                Toast toast = Toast.makeText(
//                        Feedback_FormQ34.this,
//                        "unsuccessful",
//                        Toast.LENGTH_LONG
//                );
//                ViewGroup group = (ViewGroup) toast.getView();
//                TextView messageTextView = (TextView) group.getChildAt(0);
//                messageTextView.setTextSize(20);
//                messageTextView.setTypeface(Typeface.SERIF);
//                toast.show();
//
//                if (success == 1) {
//                    Log.d("Success", message);
//                } else {
//                    Log.d("Failure", message);
//                }
//            }
//        }
//
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class My4thGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe right' action only

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

         /*
         Toast.makeText(getBaseContext(),
          event1.toString() + "\n\n" +event2.toString(),
          Toast.LENGTH_SHORT).show();
         */

            if(event2.getX() < event1.getX()){
                Toast.makeText(getBaseContext(),
                        "Swipe left - finish()",
                        Toast.LENGTH_SHORT).show();



                String q3=question3.getText().toString();
                String q4=question4.getText().toString();


                new PostAsync2().execute(event_date,q3,answer3str,organisation_name);
                new PostAsync2().execute(event_date,q4,answer4str,organisation_name);

                if(q4.equals("null")||q4.equals(""))
                {
                    Intent intent1 = new Intent(Feedback_FormQ34.this,FeedbackFormComments.class);
                    intent1.putExtra("event_date",event_date);
                    intent1.putExtra("event_location",event_location);
                    intent1.putExtra("organisation_name",organisation_name);

                    startActivityForResult(intent1,1);



                }
                else if(!q3.equals("null")||!q3.equals("")){
                    Intent intent1 = new Intent(Feedback_FormQ34.this,Feedback_FormQ5.class);
                    intent1.putExtra("event_date",event_date);
                    intent1.putExtra("event_location",event_location);
                    intent1.putExtra("organisation_name",organisation_name);

                    startActivityForResult(intent1,1);

                    //startT34
                }


            }

            return true;
        }
    }
}
