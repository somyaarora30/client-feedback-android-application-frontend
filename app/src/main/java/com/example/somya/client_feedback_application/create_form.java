package com.example.somya.client_feedback_application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


/**
 * Created by Somya on 08-02-2016.
 */
public class create_form extends Activity implements OnItemSelectedListener, AdapterView.OnItemClickListener {

   // private AutoCompleteTextView edttxt3new = null;
    private AutoCompleteTextView edttxt2i = null;
    private AutoCompleteTextView edttxt2ii = null;
    private AutoCompleteTextView edttxt6 = null;
    private AutoCompleteTextView edttxt4i = null;
    private AutoCompleteTextView edttxt4ii = null;
    private AutoCompleteTextView edt4 = null;
    private AutoCompleteTextView edttxt6i = null;
    private AutoCompleteTextView edttxt6ii = null;
    private AutoCompleteTextView edttxt70 =null;
    private AutoCompleteTextView edttxt16i=null;
    private AutoCompleteTextView edttxt16ii=null;
    private AutoCompleteTextView edttxt17=null;
    private AutoCompleteTextView edttxt17i=null;
    private AutoCompleteTextView edttxt17ii=null;
    private AutoCompleteTextView edttxt20i=null;
    private AutoCompleteTextView edttxt20ii=null;
    private AutoCompleteTextView edttxt22=null;
    private AutoCompleteTextView edttxt22i=null;
    private AutoCompleteTextView edttxt22ii=null;
    private AutoCompleteTextView edttxt18=null;
    private AutoCompleteTextView edttxt18i=null;
    private AutoCompleteTextView edttxt18ii =null;
    private AutoCompleteTextView edttxt120=null;
    private AutoCompleteTextView edttxt121 =null;
    private AutoCompleteTextView edttxt2 =null;

    private AutoCompleteTextView edttxt200=null;

    private AutoCompleteTextView edttxt2210=null;
    private AutoCompleteTextView edttxt220=null;
    private AutoCompleteTextView edttxt221=null;

    private String evtdt;

    private int array_id[]=new int[40];
    private String values[] = new String[40];
    Context context = create_form.this;




    SessionManagement session;
    HashMap<String,String> user;
    String id;

    private String evtloc;

    private AutoCompleteTextView edttxt1 = null;
    private AutoCompleteTextView edt1 = null;
    private AutoCompleteTextView edt3 = null;
    private AutoCompleteTextView edttxt9 = null;
    private AutoCompleteTextView edttxt10 = null;
    private AutoCompleteTextView edttxt12 = null;
    private AutoCompleteTextView edttxt20 = null;
    private AutoCompleteTextView edttxt21 = null;
    private AutoCompleteTextView edttxt450 = null;
    private AutoCompleteTextView edttxt451 = null;
    private AutoCompleteTextView edttxt452 = null;
    private AutoCompleteTextView edttxt453 = null;
    private AutoCompleteTextView edttxt454 = null;



    //These values show in autocomplete

    private EditText edttxt,edttxt7;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Spinner spinner;
    private String item;
    private EditText eventdate, orgname, edt2,edttxt4,edttxt45;


    protected void onCreate(Bundle savedInstanceState)

    {
        Button btn7;
        final ImageButton btn1, btn2, btn3, btn4, btn5, btn6;
        final TextView txt1, txt2, q1, m1;

        session = new SessionManagement(this);
        user= session.getUserDetails();
        id= user.get("id");

        final TableLayout t1, t2, t3;
        final TableRow row1, row2, row7, row10, row2i, row2ii, row4i, row4ii, row6i, row6ii;
//        final String str1[] = {
//                "Vinay Agarwal", "Somya Arora", "Richa K", "Vijay Muthu",
//                "Neelesh M", "Chandran Krishnan", "Vinod Nair"
//        };

//        final String str2[] = {
//                "Welcome,Orientation and IBM Client Center Walk Through", "IBM Research Overview and Use Cases",
//                "Smarter City-Water Management,Security,Accessible Way",
//                "Predictive Maintenance and Quality",
//                "Watson Analytics", "Mobility in Retail", "IBM Command Center", "IBM Cloud",
//                "Overall,I am satisfied with this briefing"};
        new PostAsync2().execute();
        new PostAsync3().execute();
        new PostAsync4().execute();
        new PostAsync5().execute();


//        final String str3[] = {
//                "Would you return for another IBM briefing?", "Would you recommend an IBM briefing to your colleagues?",
//        };

//        final String str4[] = {
//                "We thank you for visiting IBM and we value your opinion.Please provide us your feedback", "Please share with us any additional " +
//                "comments or thoughts about this program",
//        };
        final String evtdt, organame;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_form);


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        findViewsById();
        setDateTimeField();


        txt1 = (TextView) findViewById(R.id.t1);
        txt2 = (TextView) findViewById(R.id.textView3);
        q1 = (TextView) findViewById(R.id.q1);
        m1 = (TextView) findViewById(R.id.m1);
        edttxt1 = (AutoCompleteTextView) findViewById(R.id.edttxt4);//topic1
        edt1 = (AutoCompleteTextView) findViewById(R.id.edttxt2);//topic2

        edttxt2 = (AutoCompleteTextView) findViewById(R.id.edttxt3);//cca11
        edttxt2i = (AutoCompleteTextView) findViewById(R.id.edttxt2i);//cca12
        edttxt2ii = (AutoCompleteTextView) findViewById(R.id.edttxt2ii);//cca13

        edttxt6 = (AutoCompleteTextView) findViewById(R.id.edttxt6);//cca21
        edttxt4i = (AutoCompleteTextView) findViewById(R.id.edttxt4i);//cca22
        edttxt4ii = (AutoCompleteTextView) findViewById(R.id.edttxt4ii);//cca23

        edt4 = (AutoCompleteTextView) findViewById(R.id.edttxt7);//cca31
        edttxt6i = (AutoCompleteTextView) findViewById(R.id.edttxt6i);//cca32
        edttxt6ii = (AutoCompleteTextView) findViewById(R.id.edttxt6ii);//cca33

        edttxt70 = (AutoCompleteTextView) findViewById(R.id.edttxt70);//cca41
        edttxt16i = (AutoCompleteTextView) findViewById(R.id.edttxt16i);//cca42
        edttxt16ii = (AutoCompleteTextView) findViewById(R.id.edttxt16ii);//cca43

        edttxt17 = (AutoCompleteTextView) findViewById(R.id.edttxt17);//cca51
        edttxt17i = (AutoCompleteTextView) findViewById(R.id.edttxt17i);//cca52
        edttxt17ii = (AutoCompleteTextView) findViewById(R.id.edttxt17ii);//cca53

        edttxt20 = (AutoCompleteTextView) findViewById(R.id.edttxt20);//cca61
        edttxt20i = (AutoCompleteTextView) findViewById(R.id.edttxt20i);//cca62
        edttxt20ii = (AutoCompleteTextView) findViewById(R.id.edttxt20ii);//cca63

        edttxt22 = (AutoCompleteTextView) findViewById(R.id.edttxt22);//cca71
        edttxt22i = (AutoCompleteTextView) findViewById(R.id.edttxt22i);//cca72
        edttxt22ii = (AutoCompleteTextView) findViewById(R.id.edttxt22ii);//cca73

        edttxt18 = (AutoCompleteTextView) findViewById(R.id.edttxt18);//cca81
        edttxt18i = (AutoCompleteTextView) findViewById(R.id.edttxt18i);//cca82
        edttxt18ii = (AutoCompleteTextView) findViewById(R.id.edttxt18ii);//cca83

        edt3 = (AutoCompleteTextView) findViewById(R.id.edttxt45);//topic3

        //edttxt3 = (AutoCompleteTextView) findViewById(R.id.edttxt3);

        //edttxt6 = (AutoCompleteTextView) findViewById(R.id.edttxt6);




        edttxt70 = (AutoCompleteTextView) findViewById(R.id.edttxt70);
        edttxt16i = (AutoCompleteTextView) findViewById(R.id.edttxt16i);
        edttxt16ii = (AutoCompleteTextView) findViewById(R.id.edttxt16ii);
        edttxt17 = (AutoCompleteTextView) findViewById(R.id.edttxt17);
        edttxt17i = (AutoCompleteTextView) findViewById(R.id.edttxt17i);
        edttxt17ii = (AutoCompleteTextView) findViewById(R.id.edttxt17ii);
        edttxt20 = (AutoCompleteTextView) findViewById(R.id.edttxt20);
        edttxt20i = (AutoCompleteTextView) findViewById(R.id.edttxt20i);
        edttxt20ii = (AutoCompleteTextView) findViewById(R.id.edttxt20ii);
        edttxt22 = (AutoCompleteTextView) findViewById(R.id.edttxt22);
        edttxt22i = (AutoCompleteTextView) findViewById(R.id.edttxt22i);
        edttxt22ii = (AutoCompleteTextView) findViewById(R.id.edttxt22ii);
        edttxt18 = (AutoCompleteTextView) findViewById(R.id.edttxt18);
        edttxt18i = (AutoCompleteTextView) findViewById(R.id.edttxt18i);
        edttxt18ii = (AutoCompleteTextView) findViewById(R.id.edttxt18ii);

        edttxt9 = (AutoCompleteTextView) findViewById(R.id.edttxt9);//q1
        edttxt10 = (AutoCompleteTextView) findViewById(R.id.edttxt10);//q2
        edttxt121 = (AutoCompleteTextView) findViewById(R.id.edttxt121);//q3
        edttxt120 = (AutoCompleteTextView) findViewById(R.id.edttxt120);//q4
        edttxt12 = (AutoCompleteTextView) findViewById(R.id.edttxt12);//q5





        edttxt450 = (AutoCompleteTextView) findViewById(R.id.edttxt450);//topic4
        edttxt451 = (AutoCompleteTextView) findViewById(R.id.edttxt451);//topic5
        edttxt453 = (AutoCompleteTextView) findViewById(R.id.edttxt453);//topic6
        edttxt454 = (AutoCompleteTextView) findViewById(R.id.edttxt454);//topic7
        edttxt452 = (AutoCompleteTextView) findViewById(R.id.edttxt452);//topic8

        edttxt200 = (AutoCompleteTextView) findViewById(R.id.edttxt200);//m1
        edttxt21 = (AutoCompleteTextView) findViewById(R.id.edttxt21);//m2
        edttxt2210 = (AutoCompleteTextView) findViewById(R.id.edttxt2210);//m3
        edttxt220 = (AutoCompleteTextView) findViewById(R.id.edttxt220);//m4
        edttxt221 = (AutoCompleteTextView) findViewById(R.id.edttxt221);//m5



        eventdate = (EditText) findViewById(R.id.to_eventdate);
        orgname = (EditText) findViewById(R.id.orgname);

        t1 = (TableLayout) findViewById(R.id.table1);
        t2 = (TableLayout) findViewById(R.id.table2);
        t3 = (TableLayout) findViewById(R.id.table3);

        row1 = (TableRow) findViewById(R.id.row1);
        row2 = (TableRow) findViewById(R.id.row2);
        row2i = (TableRow) findViewById(R.id.row2i);
        row2ii = (TableRow) findViewById(R.id.row2ii);
        row4i = (TableRow) findViewById(R.id.row4i);
        row4ii = (TableRow) findViewById(R.id.row4ii);
        row6i = (TableRow) findViewById(R.id.row6i);
        row6ii = (TableRow) findViewById(R.id.row6ii);
        row7 = (TableRow) findViewById(R.id.row7);
        row10 = (TableRow) findViewById(R.id.row10);
        t1.setColumnStretchable(0, true);
        t1.setColumnStretchable(1, true);
        t2.setColumnStretchable(0, true);
        t2.setColumnStretchable(1, true);
        t3.setColumnStretchable(0, true);
        t3.setColumnStretchable(1, true);
        t1.setColumnShrinkable(0, true);
        t1.setColumnShrinkable(1, true);
        t2.setColumnShrinkable(0, true);
        t2.setColumnShrinkable(1, true);
        t3.setColumnShrinkable(0, true);
        t3.setColumnShrinkable(1, true);


        btn7 = (Button) findViewById(R.id.btn7);


//        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str1);
//
//        edttxt3.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt3.setAdapter(adapter);
//        edttxt3.setOnItemSelectedListener(this);
//        edttxt3.setOnItemClickListener(this);
//
//        edttxt2i.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt2i.setAdapter(adapter);
//        edttxt2i.setOnItemSelectedListener(this);
//        edttxt2i.setOnItemClickListener(this);
//
//        edttxt2ii.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt2ii.setAdapter(adapter);
//        edttxt2ii.setOnItemSelectedListener(this);
//        edttxt2ii.setOnItemClickListener(this);
//
//        edttxt6.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt6.setAdapter(adapter);
//        edttxt6.setOnItemSelectedListener(this);
//        edttxt6.setOnItemClickListener(this);
//
//        edttxt4i.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt4i.setAdapter(adapter);
//        edttxt4i.setOnItemSelectedListener(this);
//        edttxt4i.setOnItemClickListener(this);
//
//        edttxt4ii.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt4ii.setAdapter(adapter);
//        edttxt4ii.setOnItemSelectedListener(this);
//        edttxt4ii.setOnItemClickListener(this);
//
//        edt4.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edt4.setAdapter(adapter);
//        edt4.setOnItemSelectedListener(this);
//        edt4.setOnItemClickListener(this);
//
//        edttxt6i.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt6i.setAdapter(adapter);
//        edttxt6i.setOnItemSelectedListener(this);
//        edttxt6i.setOnItemClickListener(this);
//
//        edttxt6ii.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt6ii.setAdapter(adapter);
//        edttxt6ii.setOnItemSelectedListener(this);
//        edttxt6ii.setOnItemClickListener(this);





//        adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str3);
//
//        edttxt9.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt9.setAdapter(adapter3);
//        edttxt9.setOnItemSelectedListener(this);
//        edttxt9.setOnItemClickListener(this);
//        edttxt10.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt10.setAdapter(adapter3);
//        edttxt10.setOnItemSelectedListener(this);
//        edttxt10.setOnItemClickListener(this);
//        edttxt12.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt12.setAdapter(adapter3);
//        edttxt12.setOnItemSelectedListener(this);
//        edttxt12.setOnItemClickListener(this);

//        adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str4);
//
//        edttxt20.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt20.setAdapter(adapter4);
//        edttxt20.setOnItemSelectedListener(this);
//        edttxt20.setOnItemClickListener(this);
//        edttxt21.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt21.setAdapter(adapter4);
//        edttxt21.setOnItemSelectedListener(this);
//        edttxt21.setOnItemClickListener(this);
//        edttxt22.setThreshold(1);
//        //Set adapter to AutoCompleteTextView
//        edttxt22.setAdapter(adapter4);
//        edttxt22.setOnItemSelectedListener(this);
//        edttxt22.setOnItemClickListener(this);
//
//
//        array_id[0] = edttxt1.generateViewId();
//        array_id[1] = edttxt2.generateViewId();
//        array_id[2] = edttxt2i.generateViewId();
//        array_id[3] = edttxt2ii.generateViewId();
//
//        array_id[4] =  edt1.generateViewId();
//        array_id[5] = edt2.generateViewId();
//        array_id[6] = edttxt4i .generateViewId();
//        array_id[7] = edttxt4ii.generateViewId();
//
//        array_id[8] = edt3.generateViewId();
//        array_id[9] = edt4.generateViewId();
//        array_id[10] = edttxt6i.generateViewId();
//        array_id[11] = edttxt6ii .generateViewId();




//        btn1.setOnClickListener(new View.OnClickListener() {
//            int i = t1.getChildCount();
//            int j = 0;
//
//            @Override
//            public void onClick(View v) {
//                int id;
//                i = t1.getChildCount();
//                j = t1.getChildCount() / 4;
//                j++;
//
//                if (i < 40) {
//                    TableRow row1 = new TableRow(create_form.this);
//                    TextView txt1 = new TextView(create_form.this);
//                    AutoCompleteTextView newedittext = new AutoCompleteTextView(create_form.this);
//
//
//
//                    txt1.setText("Topic " + j);
//                    txt1.setTextColor(Color.parseColor("#000000"));
//
//                    txt1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//                    txt1.setTypeface(Typeface.SERIF);
//
//                    Resources r = getResources();
//                    float px1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 130, r.getDisplayMetrics());
//                    float px2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 260, r.getDisplayMetrics());
//
//                    txt1.setWidth((int) px1);
//
//                    newedittext.setWidth((int) px2);
//
//                    TableRow row2 = new TableRow(create_form.this);
//                    TableRow row2i = new TableRow(create_form.this);
//                    TableRow row2ii = new TableRow(create_form.this);
//
//                    TextView txt2 = new TextView(create_form.this);
//                    AutoCompleteTextView newedittext1 = new AutoCompleteTextView(create_form.this);
//                    AutoCompleteTextView newedittext2 = new AutoCompleteTextView(create_form.this);
//                    AutoCompleteTextView newedittext3 = new AutoCompleteTextView(create_form.this);
//
//                    array_id[i-1]=newedittext.generateViewId();
//                    array_id[i]=newedittext1.generateViewId();
//                    array_id[i+1]=newedittext2.generateViewId();
//                    array_id[i+2]=newedittext3.generateViewId();
//                    txt2.setWidth((int) px1);
//                    newedittext1.setWidth((int) px2);
//                    newedittext2.setWidth((int) px2);
//                    newedittext3.setWidth((int) px2);
//
//                    txt2.setText("Client Center Advocate");
//                    txt2.setTextColor(Color.parseColor("#000000"));
//                    txt2.setTypeface(Typeface.SERIF);
//
//                    row1.addView(txt1);
//                    row1.addView(newedittext);
//                    row2.addView(txt2);
//                    row2.addView(newedittext1);
//                    row2i.addView(newedittext2);
//                    row2i.setGravity(Gravity.RIGHT);
//                    row2ii.addView(newedittext3);
//                    row2ii.setGravity(Gravity.RIGHT);
//                    t1.addView(row1);
//                    t1.addView(row2);
//                    t1.addView(row2i);
//                    t1.addView(row2ii);
//                    i++;
//
//                    newedittext.setThreshold(1);
//                    //Set adapter to AutoCompleteTextView
//                    newedittext.setAdapter(adapter2);
//                    newedittext.setOnItemSelectedListener(create_form.this);
//                    newedittext.setOnItemClickListener(create_form.this);
//
//                    newedittext1.setThreshold(1);
//                    //Set adapter to AutoCompleteTextView
//                    newedittext1.setAdapter(adapter);
//                    newedittext1.setOnItemSelectedListener(create_form.this);
//                    newedittext1.setOnItemClickListener(create_form.this);
//
//                    newedittext2.setThreshold(1);
//                    //Set adapter to AutoCompleteTextView
//                    newedittext2.setAdapter(adapter);
//                    newedittext2.setOnItemSelectedListener(create_form.this);
//                    newedittext2.setOnItemClickListener(create_form.this);
//
//                    newedittext3.setThreshold(1);
//                    //Set adapter to AutoCompleteTextView
//                    newedittext3.setAdapter(adapter);
//                    newedittext3.setOnItemSelectedListener(create_form.this);
//                    newedittext3.setOnItemClickListener(create_form.this);
//
//
//
//                    Log.d("array_id", Arrays.toString(array_id));
//
//                } else {
//                    Toast toast = Toast.makeText(create_form.this, "More Topics can not be added",
//                            Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                }
//            }
//
//
//        });
//
//        btn2.setOnClickListener(new View.OnClickListener() {
//            int k = t1.getChildCount();
//
//            @Override
//            public void onClick(View v) {
//                k = t1.getChildCount();
//                if (k < 4) {
//                    Toast toast = Toast.makeText(
//                            create_form.this,
//                            "All fields deleted",
//                            Toast.LENGTH_LONG
//                    );
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                } else {
//                    TableRow lastRow = (TableRow) t1.getChildAt(t1.getChildCount() - 1);
//                    TableRow secondlastRow = (TableRow) t1.getChildAt(t1.getChildCount() - 2);
//                    TableRow thirdlastRow = (TableRow) t1.getChildAt(t1.getChildCount() - 3);
//                    TableRow fourthlastRow = (TableRow) t1.getChildAt(t1.getChildCount() - 4);
//
//                    t1.removeView(lastRow);
//                    t1.removeView(secondlastRow);
//                    t1.removeView(thirdlastRow);
//                    t1.removeView(fourthlastRow);
//                    k = k - 4;
//                }
//
//            }
//
//
//        });
//
//        btn3.setOnClickListener(new View.OnClickListener() {
//            int j = t2.getChildCount();
//            int k = 0;
//
//            @Override
//            public void onClick(View v) {
//                j = t2.getChildCount();
//                k = j;
//                if (j < 6) {
//                    TableRow row7 = new TableRow(create_form.this);
//                    TextView q1 = new TextView(create_form.this);
//                    AutoCompleteTextView edttxt9 = new AutoCompleteTextView(create_form.this);
//                    q1.setText("Question " + k);
//                    q1.setTextColor(Color.parseColor("#000000"));
//                    q1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//                    q1.setTypeface(Typeface.SERIF);
//                    row7.addView(q1);
//                    row7.addView(edttxt9);
//                    t2.addView(row7);
//                    j++;
//                    adapter3 = new ArrayAdapter<String>(create_form.this, android.R.layout.simple_dropdown_item_1line, str3);
//
//                    edttxt9.setThreshold(1);
//                    //Set adapter to AutoCompleteTextView
//                    edttxt9.setAdapter(adapter2);
//                    edttxt9.setOnItemSelectedListener(create_form.this);
//                    edttxt9.setOnItemClickListener(create_form.this);
//                    k++;
//                } else {
//                    Toast toast = Toast.makeText(create_form.this, "More Questions can not be added",
//                            Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                }
//
//            }
//
//        });
//        btn4.setOnClickListener(new View.OnClickListener() {
//            int k = t2.getChildCount();
//
//            @Override
//            public void onClick(View v)
//
//            {
//                k = t2.getChildCount();
//                if (k < 2) {
//                    Toast toast = Toast.makeText(
//                            create_form.this,
//                            "All fields deleted",
//                            Toast.LENGTH_LONG
//                    );
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                } else {
//                    TableRow lastRow = (TableRow) t2.getChildAt(t2.getChildCount() - 1);
//
//                    t2.removeView(lastRow);
//                    k--;
//                }
//
//
//            }
//        });
//
//        btn5.setOnClickListener(new View.OnClickListener() {
//            int i = t3.getChildCount();
//            int k = 0;
//
//            @Override
//            public void onClick(View v) {
//                i = t3.getChildCount();
//                k = i;
//                if (i < 6) {
//                    TableRow row10 = new TableRow(create_form.this);
//                    TextView m1 = new TextView(create_form.this);
//                    AutoCompleteTextView edttxt20 = new AutoCompleteTextView(create_form.this);
//                    m1.setText("Message " + k);
//                    m1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//                    m1.setTextColor(Color.parseColor("#000000"));
//
//                    m1.setTypeface(Typeface.SERIF);
//                    txt1.setTextColor(Color.parseColor("#000000"));
//
//
//                    row10.addView(m1);
//                    row10.addView(edttxt20);
//
//                    t3.addView(row10);
//                    i++;
//
//                    adapter4 = new ArrayAdapter<String>(create_form.this, android.R.layout.simple_dropdown_item_1line, str4);
//
//                    edttxt20.setThreshold(1);
//                    //Set adapter to AutoCompleteTextView
//                    edttxt20.setAdapter(adapter4);
//                    edttxt20.setOnItemSelectedListener(create_form.this);
//                    edttxt20.setOnItemClickListener(create_form.this);
//                    k++;
//                } else {
//                    Toast toast = Toast.makeText(create_form.this, "More Messages can not be added",
//                            Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                }
//
//            }
//
//        });
//        btn6.setOnClickListener(new View.OnClickListener() {
//            int k = t3.getChildCount();
//
//            @Override
//            public void onClick(View v) {
//                k = t3.getChildCount();
//                if (k < 2) {
//                    Toast toast = Toast.makeText(create_form.this, "All fields deleted", Toast.LENGTH_LONG);
//                    ViewGroup group = (ViewGroup) toast.getView();
//                    TextView messageTextView = (TextView) group.getChildAt(0);
//                    messageTextView.setTextSize(20);
//                    messageTextView.setTypeface(Typeface.SERIF);
//                    toast.show();
//                } else {
//                    TableRow lastRow = (TableRow) t3.getChildAt(t3.getChildCount() - 1);
//
//                    t3.removeView(lastRow);
//                    k--;
//                }
//
//
//            }
//        });



        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eventdate.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(create_form.this, "Enter the event date",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else if (orgname.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(create_form.this, "Enter the organisation name",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else if (item.matches("")) {
                    Toast toast = Toast.makeText(create_form.this, "Select the location",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
               }
                    else if (edttxt1.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(create_form.this, "Enter topic name",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else if (edttxt2.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(create_form.this, "Enter employee name",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else if (edt1.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(create_form.this, "Enter topic name",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else if (edttxt6.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(create_form.this, "Enter employee name",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else if (edt3.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(create_form.this, "Enter topic name",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                } else if (edt4.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(create_form.this, "Enter employee name",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();
                }
                    else {



                    //calling postasync
                    String event_date = eventdate.getText().toString();
                    String event_loc = item;
                    String org_name = orgname.getText().toString();
                    String t1=edttxt1.getText().toString();
                    String t2=edt1.getText().toString();
                    String t3=edt3.getText().toString();
                    String t4=edttxt450.getText().toString();
                    String t5=edttxt451.getText().toString();
                    String t6=edttxt453.getText().toString();
                    String t7=edttxt454.getText().toString();
                    String t8=edttxt452.getText().toString();

                    String q1=edttxt9.getText().toString();
                    String q2=edttxt10.getText().toString();
                    String q3=edttxt121.getText().toString();
                    String q4=edttxt120.getText().toString();
                    String q5=edttxt12.getText().toString();

                    String m1=edttxt200.getText().toString();
                    String m2=edttxt21.getText().toString();
                    String m3=edttxt2210.getText().toString();
                    String m4=edttxt220.getText().toString();
                    String m5=edttxt221.getText().toString();


                    String cca11=edttxt2.getText().toString();
                    String cca12=edttxt2i.getText().toString();
                    String cca13=edttxt2ii.getText().toString();

                    String cca21=edttxt6.getText().toString();
                    String cca22=edttxt4i.getText().toString();
                    String cca23=edttxt4ii.getText().toString();

                    String cca31=edt4.getText().toString();
                    String cca32=edttxt6i.getText().toString();
                    String cca33=edttxt6ii.getText().toString();

                    String cca41=edttxt70.getText().toString();
                    String cca42=edttxt16i.getText().toString();
                    String cca43=edttxt16ii.getText().toString();

                    String cca51=edttxt17.getText().toString();
                    String cca52=edttxt17i.getText().toString();
                    String cca53=edttxt17ii.getText().toString();

                    String cca61=edttxt20.getText().toString();
                    String cca62=edttxt20i.getText().toString();
                    String cca63=edttxt20ii.getText().toString();

                    String cca71=edttxt22.getText().toString();
                    String cca72=edttxt22i.getText().toString();
                    String cca73=edttxt22ii.getText().toString();

                    String cca81=edttxt18.getText().toString();
                    String cca82=edttxt18i.getText().toString();
                    String cca83=edttxt18ii.getText().toString();


                    String loginid = id;
                    Log.d("Login ID", loginid);


                    new PostAsync().execute(event_date, event_loc, org_name,t1,t2,t3,t4,t5,t6,t7,t8,q1,q2,q3,q4,q5,m1,m2,m3,m4,m5,cca11,cca12,cca13,cca21,cca22,cca23,cca31,cca32,cca33,cca41,cca42,cca43,cca51,cca52,cca53,cca61,cca62,cca63,cca71,cca72,cca73,cca81,cca82,cca83,loginid);
                     // new PostAsync().execute(event_date, event_loc, org_name,t1,t2,t3,q1,q2,q3,m1,m2,cca11,cca12,cca13,cca21,cca22,cca23,cca31,cca32,cca33,loginid);


                    Toast toast = Toast.makeText(create_form.this, "Form has been created",
                            Toast.LENGTH_LONG);
                    ViewGroup group = (ViewGroup) toast.getView();
                    TextView messageTextView = (TextView) group.getChildAt(0);
                    messageTextView.setTextSize(20);
                    messageTextView.setTypeface(Typeface.SERIF);
                    toast.show();

                    Intent intent = new Intent(
                            create_form.this,Login.class);
                    startActivity(intent);
                }


            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this, R.array.location_array, R.layout.spinner_item);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);




    }

    class PostAsync extends AsyncTask<String, String, JSONObject> {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/createform.php";

        private static final String TAG_SUCCESS = "success";
        private static final String TAG_MESSAGE = "message";


        @Override
        protected void onPreExecute() {

            Log.d("preexxec","starting");
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            try {

                HashMap<String, String> params = new HashMap<>();
                params.put("event_date", args[0]);
                params.put("event_location", args[1]);
                params.put("organisation_name", args[2]);
                params.put("topic1", args[3]);
                params.put("topic2", args[4]);
                params.put("topic3", args[5]);
                params.put("topic4", args[6]);
                params.put("topic5", args[7]);
                params.put("topic6", args[8]);
                params.put("topic7", args[9]);
                params.put("topic8", args[10]);
//                params.put("topic9", args[11]);
//                params.put("topic10", args[12]);
                params.put("question1", args[11]);
                params.put("question2", args[12]);
                params.put("question3", args[13]);
                params.put("question4", args[14]);
                params.put("question5", args[15]);
                params.put("message1", args[16]);
                params.put("message2", args[17]);
                params.put("message3", args[18]);
                params.put("message4", args[19]);
                params.put("message5", args[20]);
                params.put("cca11", args[21]);
                params.put("cca12", args[22]);
                params.put("cca13", args[23]);
                params.put("cca21", args[24]);
                params.put("cca22", args[25]);
                params.put("cca23", args[26]);
                params.put("cca31", args[27]);
                params.put("cca32", args[28]);
                params.put("cca33", args[29]);
                params.put("cca41", args[30]);
                params.put("cca42", args[31]);
                params.put("cca43", args[32]);
                params.put("cca51", args[33]);
                params.put("cca52", args[34]);
                params.put("cca53", args[35]);
                params.put("cca61", args[36]);
                params.put("cca62", args[37]);
                params.put("cca63", args[38]);
                params.put("cca71", args[39]);
                params.put("cca72", args[40]);
                params.put("cca73", args[41]);
                params.put("cca81", args[42]);
                params.put("cca82", args[43]);
                params.put("cca83", args[44]);
//                params.put("cca91", args[47]);
//                params.put("cca92", args[48]);
//                params.put("cca93", args[49]);
//                params.put("cca101", args[50]);
//                params.put("cca102", args[51]);
//                params.put("cca103", args[52]);
                params.put("login_id", args[45]);

                Log.d("request", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        LOGIN_URL, "POST", params);
               // Log.d("JSON from php",json.toString());
                if (json != null) {
                    //Log.d("JSON result",json.toString());
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
            Log.d("postexxec","starting");
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }

            else if (json != null) {
                {
                    String Role="";
                    JSONArray data= null;
                    try {
                        data = json.getJSONArray("success");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    success = json.getInt(TAG_SUCCESS);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                Toast.makeText(create_form.this, "Form can not be created",
                        Toast.LENGTH_LONG).show();

                if (success == 1) {
                    Log.d("Success", message);
                } else {
                    Log.d("Failure", message);
                }
            }
        }

    }

    class PostAsync2 extends AsyncTask<String, String, JSONObject> implements OnItemSelectedListener, AdapterView.OnItemClickListener {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/topic_array_populate.php";


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


                    JSONArray data = null;
                    ArrayAdapter<String> adapter2;
                    try {
                        data = json.getJSONArray("details");
                        String array_topic_names[]=new String[data.length()];
                       // String array_topic_names[]=new String[5];

                        Log.d("JSON result",json.toString());
                        for(int i=0;i<data.length();i++) {
                            JSONObject obj = data.getJSONObject(i);
                            Log.d("obj_value", obj.toString());
                            array_topic_names[i]=obj.toString().substring(15).replace("}","").replace("\"","");
                            Log.d("array_topic_names", array_topic_names[i]);

                            adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, array_topic_names);

                            edttxt1.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt1.setAdapter(adapter2);
                            edttxt1.setOnItemSelectedListener(this);
                            edttxt1.setOnItemClickListener(this);

                            edt1.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edt1.setAdapter(adapter2);
                            edt1.setOnItemSelectedListener(this);
                            edt1.setOnItemClickListener(this);

                            edt3.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edt3.setAdapter(adapter2);
                            edt3.setOnItemSelectedListener(this);
                            edt3.setOnItemClickListener(this);

                            edttxt450.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt450.setAdapter(adapter2);
                            edttxt450.setOnItemSelectedListener(this);
                            edttxt450.setOnItemClickListener(this);

                            edttxt451.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt451.setAdapter(adapter2);
                            edttxt451.setOnItemSelectedListener(this);
                            edttxt451.setOnItemClickListener(this);

                            edttxt453.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt453.setAdapter(adapter2);
                            edttxt453.setOnItemSelectedListener(this);
                            edttxt453.setOnItemClickListener(this);

                            edttxt454.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt454.setAdapter(adapter2);
                            edttxt454.setOnItemSelectedListener(this);
                            edttxt454.setOnItemClickListener(this);


                            edttxt452.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt452.setAdapter(adapter2);
                            edttxt452.setOnItemSelectedListener(this);
                            edttxt452.setOnItemClickListener(this);

                        }





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }

    class PostAsync3 extends AsyncTask<String, String, JSONObject> implements OnItemSelectedListener, AdapterView.OnItemClickListener {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/cca_array_populate.php";


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


                    JSONArray data = null;
                    ArrayAdapter<String> adapter;
                    try {
                        data = json.getJSONArray("details");
                        String array_topic_names[]=new String[data.length()];
                        // String array_topic_names[]=new String[5];

                        Log.d("JSON result",json.toString());
                        for(int i=0;i<data.length();i++) {
                            JSONObject obj = data.getJSONObject(i);
                            Log.d("obj_value", obj.toString());
                            array_topic_names[i]=obj.toString().substring(13).replace("}","").replace("\"","");
                            Log.d("cca_names", array_topic_names[i]);

                            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, array_topic_names);

                            edttxt2.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edttxt2.setAdapter(adapter);
            edttxt2.setOnItemSelectedListener(this);
            edttxt2.setOnItemClickListener(this);

            edttxt2i.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edttxt2i.setAdapter(adapter);
            edttxt2i.setOnItemSelectedListener(this);
            edttxt2i.setOnItemClickListener(this);

            edttxt2ii.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edttxt2ii.setAdapter(adapter);
            edttxt2ii.setOnItemSelectedListener(this);
            edttxt2ii.setOnItemClickListener(this);

            edttxt6.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edttxt6.setAdapter(adapter);
            edttxt6.setOnItemSelectedListener(this);
            edttxt6.setOnItemClickListener(this);

            edttxt4i.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edttxt4i.setAdapter(adapter);
            edttxt4i.setOnItemSelectedListener(this);
            edttxt4i.setOnItemClickListener(this);

            edttxt4ii.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edttxt4ii.setAdapter(adapter);
            edttxt4ii.setOnItemSelectedListener(this);
            edttxt4ii.setOnItemClickListener(this);

            edt4.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edt4.setAdapter(adapter);
            edt4.setOnItemSelectedListener(this);
            edt4.setOnItemClickListener(this);

            edttxt6i.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edttxt6i.setAdapter(adapter);
            edttxt6i.setOnItemSelectedListener(this);
            edttxt6i.setOnItemClickListener(this);

            edttxt6ii.setThreshold(1);
            //Set adapter to AutoCompleteTextView
            edttxt6ii.setAdapter(adapter);
            edttxt6ii.setOnItemSelectedListener(this);
            edttxt6ii.setOnItemClickListener(this);

                            edttxt70.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt70.setAdapter(adapter);
                            edttxt70.setOnItemSelectedListener(this);
                            edttxt70.setOnItemClickListener(this);

                            edttxt16i.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt16i.setAdapter(adapter);
                            edttxt16i.setOnItemSelectedListener(this);
                            edttxt16i.setOnItemClickListener(this);

                            edttxt16ii.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt16ii.setAdapter(adapter);
                            edttxt16ii.setOnItemSelectedListener(this);
                            edttxt16ii.setOnItemClickListener(this);

                            edttxt17.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt17.setAdapter(adapter);
                            edttxt17.setOnItemSelectedListener(this);
                            edttxt17.setOnItemClickListener(this);

                            edttxt17i.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt17i.setAdapter(adapter);
                            edttxt17i.setOnItemSelectedListener(this);
                            edttxt17i.setOnItemClickListener(this);

                            edttxt17ii.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt17ii.setAdapter(adapter);
                            edttxt17ii.setOnItemSelectedListener(this);
                            edttxt17ii.setOnItemClickListener(this);

                            edttxt20.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt20.setAdapter(adapter);
                            edttxt20.setOnItemSelectedListener(this);
                            edttxt20.setOnItemClickListener(this);

                            edttxt20i.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt20i.setAdapter(adapter);
                            edttxt20i.setOnItemSelectedListener(this);
                            edttxt20i.setOnItemClickListener(this);

                            edttxt20ii.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt20ii.setAdapter(adapter);
                            edttxt20ii.setOnItemSelectedListener(this);
                            edttxt20ii.setOnItemClickListener(this);

                            edttxt22.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt22.setAdapter(adapter);
                            edttxt22.setOnItemSelectedListener(this);
                            edttxt22.setOnItemClickListener(this);

                            edttxt22i.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt22i.setAdapter(adapter);
                            edttxt22i.setOnItemSelectedListener(this);
                            edttxt22i.setOnItemClickListener(this);

                            edttxt22ii.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt22ii.setAdapter(adapter);
                            edttxt22ii.setOnItemSelectedListener(this);
                            edttxt22ii.setOnItemClickListener(this);

                            edttxt18.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt18.setAdapter(adapter);
                            edttxt18.setOnItemSelectedListener(this);
                            edttxt18.setOnItemClickListener(this);

                            edttxt18i.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt18i.setAdapter(adapter);
                            edttxt18i.setOnItemSelectedListener(this);
                            edttxt18i.setOnItemClickListener(this);

                            edttxt18ii.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt18ii.setAdapter(adapter);
                            edttxt18ii.setOnItemSelectedListener(this);
                            edttxt18ii.setOnItemClickListener(this);



                        }





                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }


    class PostAsync4 extends AsyncTask<String, String, JSONObject> implements OnItemSelectedListener, AdapterView.OnItemClickListener {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/question_array_populate.php";


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


                    JSONArray data = null;
                    ArrayAdapter<String> adapter3;
                    try {
                        data = json.getJSONArray("details");
                        String array_topic_names[]=new String[data.length()];
                        // String array_topic_names[]=new String[5];

                        Log.d("JSON result",json.toString());
                        for(int i=0;i<data.length();i++) {
                            JSONObject obj = data.getJSONObject(i);
                            Log.d("obj_value", obj.toString());
                            array_topic_names[i]=obj.toString().substring(14).replace("}","").replace("\"","");
                            Log.d("question_names", array_topic_names[i]);

                            adapter3 = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, array_topic_names);



                            edttxt9.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt9.setAdapter(adapter3);
                            edttxt9.setOnItemSelectedListener(this);
                            edttxt9.setOnItemClickListener(this);
                            edttxt10.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt10.setAdapter(adapter3);
                            edttxt10.setOnItemSelectedListener(this);
                            edttxt10.setOnItemClickListener(this);
                            edttxt121.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt121.setAdapter(adapter3);
                            edttxt121.setOnItemSelectedListener(this);
                            edttxt121.setOnItemClickListener(this);

                            edttxt120.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt120.setAdapter(adapter3);
                            edttxt120.setOnItemSelectedListener(this);
                            edttxt120.setOnItemClickListener(this);
                            edttxt12.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt12.setAdapter(adapter3);
                            edttxt12.setOnItemSelectedListener(this);
                            edttxt12.setOnItemClickListener(this);



                        }
                        //adapter2.notifyDataSetChanged();




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }



    class PostAsync5 extends AsyncTask<String, String, JSONObject> implements OnItemSelectedListener, AdapterView.OnItemClickListener {

        JSONParser jsonParser = new JSONParser();

        private ProgressDialog pDialog;

        private static final String LOGIN_URL = "http://10.0.3.2/message_array_populate.php";


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


                    JSONArray data = null;
                    ArrayAdapter<String> adapter4;
                    try {
                        data = json.getJSONArray("details");
                        String array_topic_names[]=new String[data.length()];
                        // String array_topic_names[]=new String[5];

                        Log.d("JSON result",json.toString());
                        for(int i=0;i<data.length();i++) {
                            JSONObject obj = data.getJSONObject(i);
                            Log.d("obj_value", obj.toString());
                            array_topic_names[i]=obj.toString().substring(12).replace("}","").replace("\"","");
                            Log.d("msg_names", array_topic_names[i]);

                            adapter4 = new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, array_topic_names);



                            edttxt21.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt21.setAdapter(adapter4);
                            edttxt21.setOnItemSelectedListener(this);
                            edttxt21.setOnItemClickListener(this);

                            edttxt200.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt200.setAdapter(adapter4);
                            edttxt200.setOnItemSelectedListener(this);
                            edttxt200.setOnItemClickListener(this);

                            edttxt2210.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt2210.setAdapter(adapter4);
                            edttxt2210.setOnItemSelectedListener(this);
                            edttxt2210.setOnItemClickListener(this);

                            edttxt220.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt220.setAdapter(adapter4);
                            edttxt220.setOnItemSelectedListener(this);
                            edttxt220.setOnItemClickListener(this);

                            edttxt221.setThreshold(1);
                            //Set adapter to AutoCompleteTextView
                            edttxt221.setAdapter(adapter4);
                            edttxt221.setOnItemSelectedListener(this);
                            edttxt221.setOnItemClickListener(this);


                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    }




    private void findViewsById() {
        edttxt = (EditText) findViewById(R.id.to_eventdate);
        edttxt.setInputType(InputType.TYPE_NULL);
        edttxt.requestFocus();

    }

    private void setDateTimeField() {
        // edttxt.setOnClickListener(create_form.this);
        edttxt.setOnClickListener(new View.OnClickListener() {
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
                edttxt.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
