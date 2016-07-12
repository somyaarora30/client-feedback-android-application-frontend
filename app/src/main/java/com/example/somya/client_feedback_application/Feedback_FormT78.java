package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Feedback_FormT78 extends Activity implements RatingBar.OnRatingBarChangeListener{

    TextView textbox1,textbox2;
    RatingBar ratingbar1,ratingbar2;
    TextView topicname7,topicname8;
    TextView textView9,textView12;


    int score7 = 0, score8 = 0;

    String event_date,event_location,organisation_name;
    String score7str,score8str;

    private GestureDetectorCompat gestureDetectorCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback__form_t78);

        textbox1=(TextView)findViewById(R.id.textbox1);
        textbox2=(TextView)findViewById(R.id.textbox2);
        ratingbar1=(RatingBar)findViewById(R.id.ratingbar1);
        ratingbar2=(RatingBar)findViewById(R.id.ratingbar2);
        ratingbar1.setOnRatingBarChangeListener(this);
        ratingbar2.setOnRatingBarChangeListener(this);
        topicname7=(TextView)findViewById(R.id.topicname3);
        topicname8=(TextView)findViewById(R.id.topicname4);
        textView9=(TextView)findViewById(R.id.textView9);
        textView12=(TextView)findViewById(R.id.textView12);

        gestureDetectorCompat = new GestureDetectorCompat(this, new My2ndGestureListener());


        Intent intent2 = getIntent();

        event_date = intent2.getStringExtra("event_date");
        event_location = intent2.getStringExtra("event_location");
        organisation_name = intent2.getStringExtra("organisation_name");

        new PostAsync().execute(event_date,event_location,organisation_name);




        Log.d("event_date",event_date);


    }

    class PostAsync extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/client_feedbackT78.php";


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

                params.put("event_date", args[0]);
                params.put("event_location", args[1]);
                params.put("organisation_name", args[2]);

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

            if (json != null) {
                {
                    Log.d("json", "not null");
                    String t7 = "";
                    String t8 = "";

                    JSONArray data = null;
                    try {
                        data = json.getJSONArray("details");
                        JSONObject obj = data.getJSONObject(0);
                        t7 = obj.getString("topic7");
                        t8 = obj.getString("topic8");

                        if(t7.equals("null")||t7.equals("")){
                            Intent intent= new Intent(Feedback_FormT78.this,Feedback_FormQ12.class);
                            startActivity(intent);

                        }
                        else if(t8.equals("null")||t8.equals(""))
                        {
                            topicname7.setText(t7);
                            textView12.setVisibility(View.GONE);
                            ratingbar2.setVisibility(View.GONE);
                            textbox2.setVisibility(View.GONE);
                            //start comments


                        }
                        else if(!t8.equals("null")||!t8.equals("")){
                            topicname7.setText(t7);
                            topicname8.setText(t8);
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

        }


    }

    class PostAsync_Rating extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String RATING_URL = "http://10.0.3.2/client_rating78.php";

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

                params.put("score7",args[0]);
                params.put("score8", args[1]);
                params.put("event_date", args[2]);
                params.put("event_location", args[3]);
                params.put("organisation_name", args[4]);

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

            Log.d("post exec", "starting");

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

        private static final String LOGIN_URL = "http://10.0.3.2/report_T1.php";

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

                params.put("event_date2", args[0]);
                params.put("topic_name2", args[1]);
                params.put("score2", args[2]);
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
                Toast.makeText(Feedback_FormT78.this, "json null",
                        Toast.LENGTH_LONG).show();

                if (success == 1) {
                    Log.d("Success", message);

                } else {
                    Log.d("Failure", message);
                }
            }
        }

    }


    @Override
    public void onRatingChanged(RatingBar ratingbar, float rating, boolean fromUser) {
        if (ratingbar == ratingbar1)
        {
            float i;
            i = ratingbar1.getRating();

            if (i == 1.0) {
                textbox1.setText("Strongly Disagree");
                textbox1.setTextColor(Color.parseColor("#990000"));
                textbox1.setTypeface(Typeface.SERIF);
            } else if (i == 2.0) {
                textbox1.setText("Disagree");
                textbox1.setTextColor(Color.parseColor("#996600"));
                textbox1.setTypeface(Typeface.SERIF);
            } else if (i == 3.0) {
                textbox1.setText("Neither Agree Nor Disagree");
                textbox1.setTextColor(Color.parseColor("#006666"));
                textbox1.setTypeface(Typeface.SERIF);
            } else if (i == 4.0) {
                textbox1.setText("Agree");
                textbox1.setTextColor(Color.parseColor("#0033cc"));
                textbox1.setTypeface(Typeface.SERIF);
            } else if (i == 5.0) {
                textbox1.setText("Strongly Agree");
                textbox1.setTextColor(Color.parseColor("#009933"));
                textbox1.setTypeface(Typeface.SERIF);
            }
            score7 = (int) i;

        }
        else if(ratingbar==ratingbar2)
        {
            float i;
            i=ratingbar2.getRating();

            if(i==1.0)
            {textbox2.setText("Strongly Disagree");
                textbox2.setTextColor(Color.parseColor("#990000"));
                textbox2.setTypeface(Typeface.SERIF);
            }

            else if(i==2.0)
            {textbox2.setText("Disagree");
                textbox2.setTextColor(Color.parseColor("#996600"));
                textbox2.setTypeface(Typeface.SERIF);}

            else if(i==3.0)
            {textbox2.setText("Neither Agree Nor Disagree");
                textbox2.setTextColor(Color.parseColor("#006666"));
                textbox2.setTypeface(Typeface.SERIF);}

            else if(i==4.0)
            {textbox2.setText("Agree");
                textbox2.setTextColor(Color.parseColor("#0033cc"));
                textbox2.setTypeface(Typeface.SERIF);}

            else if(i==5.0)
            {textbox2.setText("Strongly Agree");
                textbox2.setTextColor(Color.parseColor("#009933"));
                textbox2.setTypeface(Typeface.SERIF);}
            score8 = (int) i;
        }
        score7str= (String.valueOf(score7));
        score8str= (String.valueOf(score8));
        new PostAsync_Rating().execute(score7str,score8str,event_date,event_location,organisation_name);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class My2ndGestureListener extends GestureDetector.SimpleOnGestureListener {
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



                String t7=topicname7.getText().toString();
                String t8=topicname8.getText().toString();


                new PostAsync2().execute(event_date,t7,score7str,organisation_name);
                new PostAsync2().execute(event_date,t8,score8str,organisation_name);

                if(t8.equals("null")||t8.equals(""))
                {
                    Intent intent1 = new Intent(Feedback_FormT78.this,Feedback_FormQ12.class);
                    intent1.putExtra("event_date",event_date);
                    intent1.putExtra("event_location",event_location);
                    intent1.putExtra("organisation_name",organisation_name);

                    startActivityForResult(intent1,1);
                    //start comments


                }
                else if(!t7.equals("null")||!t7.equals("")){
                    Intent intent1 = new Intent(Feedback_FormT78.this,Feedback_FormQ12.class);
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

