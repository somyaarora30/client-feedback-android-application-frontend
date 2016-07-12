package com.example.somya.client_feedback_application;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Login extends Activity {
    AutoCompleteTextView UsernameEt;
    EditText PasswordEt;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameEt = (AutoCompleteTextView) findViewById(R.id.email);
        PasswordEt = (EditText) findViewById(R.id.password);
        session = new SessionManagement(this);
    }

    class PostAsync extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/login2.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();
                params.put("email_id", args[0]);
                params.put("password", args[1]);

                Log.d("request", "starting");

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

            String test1 = UsernameEt.getText().toString();
            String test2 = test1.toLowerCase();
            boolean hasCapitalusername = !test1.equals(test2);

            String test3 = PasswordEt.getText().toString();
            String test4 = test3.toLowerCase();
            boolean hasCapitalpwd = !test3.equals(test4);

            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            if((UsernameEt.getText().toString().equals("")))
            { Toast toast = Toast.makeText(
                    Login.this,
                    "Enter email id",
                    Toast.LENGTH_LONG
            );
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(20);
                messageTextView.setTypeface(Typeface.SERIF);
                toast.show();}

            if((PasswordEt.getText().toString().equals("")))
            { Toast toast = Toast.makeText(
                    Login.this,
                    "Enter password",
                    Toast.LENGTH_LONG
            );
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(20);
                messageTextView.setTypeface(Typeface.SERIF);
                toast.show();}

            else if(hasCapitalusername==true)
                    {Toast toast = Toast.makeText(
                            Login.this,
                            "Enter email id in lowercase",
                            Toast.LENGTH_LONG
                    );
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextSize(20);
                        messageTextView.setTypeface(Typeface.SERIF);
                        toast.show();}

                    else if(hasCapitalpwd==true)
                    {Toast toast = Toast.makeText(
                            Login.this,
                            "Password is incorrect",
                            Toast.LENGTH_LONG
                    );
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextSize(20);
                        messageTextView.setTypeface(Typeface.SERIF);
                        toast.show();}

            else if (json != null) {
                {
                    String Role="";
                    JSONArray data= null;
                    try {
                        data = json.getJSONArray("details");
                        JSONObject obj=data.getJSONObject(0);
                        Role=obj.getString("role");
                        session.createLoginSession(obj.getString("name"),obj.getString("loginid"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if(Role.equals("client"))
                    { Intent intent = new Intent(Login.this,welcome.class);
                    startActivity(intent);}
                    else if(Role.equals("admin"))
                    { Intent intent = new Intent(Login.this,admin_landing.class);
                        startActivity(intent);}
                    else if(Role.equals("superadmin"))
                    { Intent intent = new Intent(Login.this,view_dateororgwise.class);
                        startActivity(intent);}

                    else
                    {Toast toast = Toast.makeText(
                            Login.this,
                            "Enter valid credentials",
                            Toast.LENGTH_LONG
                    );
                        ViewGroup group = (ViewGroup) toast.getView();
                        TextView messageTextView = (TextView) group.getChildAt(0);
                        messageTextView.setTextSize(20);
                        messageTextView.setTypeface(Typeface.SERIF);
                        toast.show();}
                }

                try {
                    success = json.getInt(TAG_SUCCESS);
                    message = json.getString(TAG_MESSAGE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                Toast toast = Toast.makeText(
                        Login.this,
                        "Login unsuccessful",
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

    public void OnLogin(View view) {

        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();

        new PostAsync().execute(username, password);

    }
}





