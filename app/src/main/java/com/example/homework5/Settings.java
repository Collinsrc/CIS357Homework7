package com.example.homework5;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private String FromChoice = "";
    private String ToChoice = "";
    private String FromFieldExact = "";
    private String ToFieldExact = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent Fields = getIntent();
        if(Fields.hasExtra("FromChoice") && Fields.hasExtra("ToChoice")){
            FromFieldExact = Fields.getStringExtra(("FromChoice"));
            ToFieldExact = Fields.getStringExtra(("ToChoice"));
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent();
                intent.putExtra("FromChoice", FromChoice);
                intent.putExtra("ToChoice", ToChoice);
                setResult(MainActivity.CHOICES ,intent);
                finish();
            }
        });

        TextView From = (TextView) findViewById(R.id.FromUnits);
        TextView To = (TextView) findViewById(R.id.ToUnits);
        From.setText(FromFieldExact);
        To.setText(ToFieldExact);


        if(FromFieldExact.equals("Yards") || FromFieldExact.equals("Meters") || FromFieldExact.equals("Miles")) {

            Spinner spinner = (Spinner) findViewById(R.id.spinner);


            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.distanceUnits, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            if(FromFieldExact.equals("Meters")){
                spinner.setSelection(1);
            }
            else if(FromFieldExact.equals("Miles")){
                spinner.setSelection(2);
            }
            else spinner.setSelection(0);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    FromChoice = (String) adapterView.getItemAtPosition(i);
                    From.setText(FromChoice);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.distanceUnits, android.R.layout.simple_spinner_item);

            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            if(ToFieldExact.equals("Meters")){
                spinner2.setSelection(1);
            }
            else if(ToFieldExact.equals("Miles")){
                spinner2.setSelection(2);
            }
            else spinner2.setSelection(0);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ToChoice = (String) adapterView.getItemAtPosition(i);
                    To.setText(ToChoice);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }
        else{
            Spinner spinner = (Spinner) findViewById(R.id.spinner);


            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.volumeUnits, android.R.layout.simple_spinner_item);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            if(FromFieldExact.equals("Gallons")){
                spinner.setSelection(1);
            }
            else if(FromFieldExact.equals("Quarts")){
                spinner.setSelection(2);
            }
            else spinner.setSelection(0);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    FromChoice = (String) adapterView.getItemAtPosition(i);
                    From.setText(FromChoice);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                    R.array.volumeUnits, android.R.layout.simple_spinner_item);

            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            if(ToFieldExact.equals("Gallons")){
                spinner2.setSelection(1);
            }
            else if(ToFieldExact.equals("Quarts")){
                spinner2.setSelection(2);
            }
            else spinner2.setSelection(0);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ToChoice = (String) adapterView.getItemAtPosition(i);
                    To.setText(ToChoice);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            From.setText(FromChoice);
            To.setText(ToChoice);
        }
        }


}
