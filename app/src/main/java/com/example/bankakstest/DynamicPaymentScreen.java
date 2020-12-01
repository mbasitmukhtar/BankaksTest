package com.example.bankakstest;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bankakstest.Models.Fields;
import com.example.bankakstest.Models.TheResponse;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DynamicPaymentScreen extends AppCompatActivity {
    private static final String TAG = "DynamicPaymentScreen";

    int selectedOption;
    private RequestQueue mRequestQueue;
    int selectedMonthOption;
    ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_payment_screen);
        if (getIntent() != null) {
            selectedOption = getIntent().getIntExtra("selectedOption", 0);
            Log.d(TAG, "onCreate: " + selectedOption);
        }

//        Show progress dialog box
        progressDialog = new ProgressDialog(DynamicPaymentScreen.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar_layout);
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

//        create a request queue to send request and get json
        mRequestQueue = Volley.newRequestQueue(this);
        if (selectedOption != 0) {
            jsonParse();
        }

    }

//    function to get and parse json
    private void jsonParse() {
        String URL = "https://api-staging.bankaks.com/task/" + selectedOption;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "onResponse: " + response);
                Gson gson = new Gson();

//                parse json into java classes and variables
                TheResponse theResponse = gson.fromJson(String.valueOf(response), TheResponse.class);
                processJson(theResponse);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    private void processJson(TheResponse response) {

//        create a dynamic linear layout
        LinearLayout myLayout = new LinearLayout(this);
//        layout parameters for linear layout
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        myLayout.setOrientation(LinearLayout.VERTICAL);
        myLayout.setLayoutParams(parms);
        myLayout.setGravity(Gravity.CLIP_VERTICAL);

//        layout parameters for text inputs
        LinearLayout.LayoutParams tv1Params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1Params.setMargins(20, 20, 20, 20);

//        set screen title
        String screenTitle = response.getResult().getScreen_title();
        setTitle(screenTitle);
        
//        get number of fields and loop through number of fields
        int num_fields = response.getResult().getNumber_of_fields();
        for (int i = 0; i < num_fields; i++) {
//            handle the input text fields and dropdown fields
            if (response.getResult().getFields().get(i).getUi_type().getType().equals("textfield")) {
                EditText editText = createTextInput(response.getResult().getFields().get(i));
                myLayout.addView(editText, tv1Params);
            } else if (response.getResult().getFields().get(i).getUi_type().getType().equals("dropdown")) {
                Spinner spinner = createDropDown(response.getResult().getFields().get(i));
                myLayout.addView(spinner, tv1Params);
            }
        }

        Button proceedBtn = createProceedButton();
        myLayout.addView(proceedBtn, tv1Params);


        setContentView(myLayout);
        progressDialog.dismiss();
    }

//    function to create proceed button to submit the form
    private Button createProceedButton() {
        Button proceedBtn = new Button(this);
        proceedBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        proceedBtn.setText(R.string.DynamicScreen_proceed);
        proceedBtn.setTextColor(getResources().getColor(R.color.white));

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DynamicPaymentScreen.this, R.string.transactionDone, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        return proceedBtn;
    }

//    function to create edit text inputs
    private EditText createTextInput(final Fields fields) {
        final EditText editText = new EditText(this);
        editText.setHint(fields.getPlaceholder());

//        get the regular expression and match the pattern
//        p.s there was some issue with the regular expression that was in the JSON
//        so this is incomplete
        String regex = fields.getRegex();
        if (!regex.equals("")) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
//                    here regex was being matched

//                    boolean b = Pattern.matches(fields.getRegex(), editable);
//                    if (!b) {
//                        editText.setBackgroundColor(Color.RED);
//                    }
                }
            });

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String[] autofillHints = new String[]{fields.getHint_text()};
            editText.setAutofillHints(autofillHints);
        }
        return editText;
    }

//    function to create dropdown list
    private Spinner createDropDown(Fields fields) {
        Spinner spinner = new Spinner(this);
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < fields.getUi_type().getValues().size(); i++) {
            list.add(fields.getUi_type().getValues().get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                selectedMonthOption = pos + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return spinner;
    }

}