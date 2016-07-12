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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class Feedback_FormQ12 extends Activity  {

    TextView question1,question2;
    RadioGroup radiogroup1;
    RadioGroup radiogroup2;
    RadioButton radiogroup1button1;
    RadioButton radiogroup1button2;
    RadioButton radiogroup2button1;
    RadioButton radiogroup2button2;
    TextView textView9,textView12;

    float answer1 = 0, answer2 = 0;
    String event_date,event_location,organisation_name;
    String answer1str,answer2str;



    private GestureDetectorCompat gestureDetectorCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback__form_q12);



        gestureDetectorCompat = new GestureDetectorCompat(this, new My3rdGestureListener());

        question1=(TextView)findViewById(R.id.question1);
        question2=(TextView)findViewById(R.id.question2);
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

        radiogroup1.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {

            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if(checkedId == R.id.radiogroup1button1) {

                    Toast.makeText(Feedback_FormQ12.this, "YES",

                            Toast.LENGTH_SHORT).show();

                    Log.d("First Button value","Yes");
                    answer1=11;


                } else if(checkedId == R.id.radiogroup1button2) {

                    Toast.makeText(Feedback_FormQ12.this, "NO",

                            Toast.LENGTH_SHORT).show();
                    Log.d("First Button value","No");
                    answer1=00;

                }

                Log.d("answer1 value:",String.valueOf(answer1));
                Log.d("Calling:","PostAsync_Rating");
                answer1str= (String.valueOf(answer1));


                new PostAsync_Rating1().execute(answer1str,event_date,event_location,organisation_name);
            }



        });

        radiogroup2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if(checkedId == R.id.radiogroup2button1) {

                    Toast.makeText(Feedback_FormQ12.this, "YES",

                            Toast.LENGTH_SHORT).show();
        Log.d("First Button value","Yes");
        answer2=11;

                } else if(checkedId == R.id.radiogroup2button2) {

                    Toast.makeText(Feedback_FormQ12.this, "NO",

                            Toast.LENGTH_SHORT).show();
        Log.d("First Button value","No");
        answer2=00;

                }
        Log.d("answer2 value:",String.valueOf(answer2));
        Log.d("Calling:","PostAsync_Rating");
                answer2str= (String.valueOf(answer2));
                new PostAsync_Rating2().execute(answer2str,event_date,event_location,organisation_name);

            }


        });






    }


    class PostAsync extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/client_feedbackQ12.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            Log.d("preexec","starting");
            pDialog = new ProgressDialog(Feedback_FormQ12.this);
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
                    String q1="";
                    String q2="";
                    JSONArray data= null;
                    try {
                        data = json.getJSONArray("details");

                        JSONObject obj=data.getJSONObject(0);
                        q1=obj.getString("question1");
                        q2=obj.getString("question2");

                        if(q1.equals("null")||q1.equals("")){
                            Intent intent= new Intent(Feedback_FormQ12.this,FeedbackFormComments.class);
                            startActivity(intent);

                        }
                        else if(q2.equals("null")||q2.equals(""))
                        {
                            question1.setText(q1);
                            textView12.setVisibility(View.GONE);
                            radiogroup1.setVisibility(View.GONE);

                        }
                        else if(!q1.equals("null")||!q1.equals("")){
                            question1.setText(q1);
                            question2.setText(q2);
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
                        Feedback_FormQ12.this,
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

        private static final String RATING_URL = "http://10.0.3.2/client_answer12f.php";

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

                params.put("answer1",args[0]);
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

        private static final String RATING_URL = "http://10.0.3.2/client_answer12s.php";

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

                params.put("answer2",args[0]);

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
                Toast.makeText(Feedback_FormQ12.this, "json null",
                        Toast.LENGTH_LONG).show();

                if (success == 1) {
                    Log.d("Success", message);

                } else {
                    Log.d("Failure", message);
                }
            }
        }

    }





//    public void onRadioButtonClicked(View view) {
//        float i=0;
//        float j=0;
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.radiogroup1button1:
//                if (checked)
//                    i=11;//Yes
//                Log.d("First Button value","Yes");
//                Toast toast = Toast.makeText(Feedback_FormQ12.this, "yes",
//                        Toast.LENGTH_LONG);
//                ViewGroup group = (ViewGroup) toast.getView();
//                TextView messageTextView = (TextView) group.getChildAt(0);
//                messageTextView.setTextSize(20);
//                messageTextView.setTypeface(Typeface.SERIF);
//                toast.show();
//                break;
//            case R.id.radiogroup1button2:
//                if (checked)
//                    i=0;//No
//                Log.d("First Button value","No");
//                Toast toast1 = Toast.makeText(Feedback_FormQ12.this, "No",
//                        Toast.LENGTH_LONG);
//
//                toast1.show();
//                break;
//            case R.id.radiogroup2button1:
//                if (checked)
//                    j=11;//Yes
//                Log.d("Second Button value","Yes");
//                Toast.makeText(Feedback_FormQ12.this,
//                        "yes",
//                        Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.radiogroup2button2:
//                if (checked)//No
//                    j=0;
//                Log.d("Second Button value","No");
//                Toast.makeText(Feedback_FormQ12.this,
//                        "no",
//                        Toast.LENGTH_SHORT).show();
//                break;
//        }
//        answer1=i;
//        answer2=j;
//
//        new PostAsync_Rating().execute();
//    }






    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class My3rdGestureListener extends GestureDetector.SimpleOnGestureListener {
        //handle 'swipe right' action only

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {



            if(event2.getX() < event1.getX()){
                Toast.makeText(getBaseContext(),
                        "Swipe left - finish()",
                        Toast.LENGTH_SHORT).show();



                String q1=question1.getText().toString();
                String q2=question2.getText().toString();


                new PostAsync2().execute(event_date,q1,answer1str,organisation_name);
                new PostAsync2().execute(event_date,q2,answer2str,organisation_name);
                if(q2.equals("null")||q2.equals(""))
                {
                    Intent intent1 = new Intent(Feedback_FormQ12.this,FeedbackFormComments.class);
                    intent1.putExtra("event_date",event_date);
                    intent1.putExtra("event_location",event_location);
                    intent1.putExtra("organisation_name",organisation_name);

                    startActivityForResult(intent1,1);
                    //start comments


                }
                else if(!q1.equals("null")||!q1.equals("")){
                    Intent intent1 = new Intent(Feedback_FormQ12.this,Feedback_FormQ34.class);
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
