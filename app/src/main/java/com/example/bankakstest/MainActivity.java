package com.example.bankakstest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "MainActivity";

    int selectedOption = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSubmit = findViewById(R.id.btn_submit);
        Spinner dropdown = findViewById(R.id.dropdown);
        dropdown.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        dropdown.setAdapter(adapter);

        // listener for proceed button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DynamicPaymentScreen.class);
                intent.putExtra("selectedOption", selectedOption + 1);
                startActivity(intent);
            }
        });
    }

//    Methods for dropdown selection
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        selectedOption = pos;
        Log.d(TAG, "onItemSelected: " + selectedOption);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}