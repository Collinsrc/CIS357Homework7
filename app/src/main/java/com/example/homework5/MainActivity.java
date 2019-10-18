package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    public static final int CHOICES = 1;
    private String defaultFromDistance = "Yards";
    private String defaultToDistance = "Meters";
    private String defaultFromVolume = "Liters";
    private String defaultToVolume = "Gallons";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI(findViewById(R.id.parent));
        EditText fromText = (EditText) findViewById(R.id.FromField);
        EditText toText = (EditText) findViewById(R.id.ToField);
        TextView From = (TextView) findViewById(R.id.FromUnits);
        TextView To = (TextView) findViewById(R.id.ToUnits);
        From.setText(defaultFromDistance);
        To.setText(defaultToDistance);

        Button ClearButton = (Button) findViewById(R.id.ClearButton);
        ClearButton.setOnClickListener( v-> {
            fromText.getText().clear();
            toText.getText().clear();
        });
        Button ModeButton = findViewById(R.id.ModeButton);
        ModeButton.setOnClickListener( v-> {
            if(From.getText().equals("Yards")|| From.getText().equals("Meters")|| From.getText().equals("Miles")){
                From.setText(defaultFromVolume);
                To.setText(defaultToVolume);
            }
            else{
                From.setText(defaultFromDistance);
                To.setText(defaultToDistance);
            }
        });


        Button CalculateButton = findViewById(R.id.CalculateButton);
        CalculateButton.setOnClickListener(v ->{
            UnitsConverter.LengthUnits FromUnitsLength = UnitsConverter.LengthUnits.Meters;
            UnitsConverter.LengthUnits ToUnitsLength = UnitsConverter.LengthUnits.Meters;
            UnitsConverter.VolumeUnits FromUnitsVolume = UnitsConverter.VolumeUnits.Liters;
            UnitsConverter.VolumeUnits ToUnitsVolume = UnitsConverter.VolumeUnits.Liters;

            Double ConvertedValue = 0.0;

            if(From.getText().equals("Yards")){
                FromUnitsLength = UnitsConverter.LengthUnits.Yards;
            } else if (From.getText().equals("Meters")){
                FromUnitsLength = UnitsConverter.LengthUnits.Meters;
            } else {
                FromUnitsLength = UnitsConverter.LengthUnits.Miles;
            }

            if(To.getText().equals("Yards")){
                ToUnitsLength = UnitsConverter.LengthUnits.Yards;
            } else if (To.getText().equals("Meters")){
                ToUnitsLength = UnitsConverter.LengthUnits.Meters;
            } else {
                ToUnitsLength = UnitsConverter.LengthUnits.Miles;
            }

            if(From.getText().equals("Liters")){
                FromUnitsVolume = UnitsConverter.VolumeUnits.Liters;
            } else if (From.getText().equals("Quarts")){
                FromUnitsVolume = UnitsConverter.VolumeUnits.Quarts;
            } else {
                FromUnitsVolume = UnitsConverter.VolumeUnits.Gallons;
            }

            if(To.getText().equals("Liters")){
                ToUnitsVolume = UnitsConverter.VolumeUnits.Liters;
            } else if (To.getText().equals("Quarts")){
                ToUnitsVolume = UnitsConverter.VolumeUnits.Quarts;
            } else {
                ToUnitsVolume = UnitsConverter.VolumeUnits.Gallons;
            }


//            if(From.getText().equals("Yards")|| From.getText().equals("Meters")|| From.getText().equals("Miles")){
//                if(From.getText().equals("")){
//                    Double input = Double.parseDouble(To.getText().toString());
//                    UnitsConverter.convert(input, FromUnitsLength , UnitsConverter.LengthUnits.Meters);
//                    fromText.setText();
//                }
//            }

            if(From.getText().equals("Yards")|| From.getText().equals("Meters")|| From.getText().equals("Miles")){
                if(fromText.getText().toString().equals("")){
                    Double input = Double.parseDouble(toText.getText().toString());
                    ConvertedValue = UnitsConverter.convert(input, ToUnitsLength, FromUnitsLength);
                    fromText.setText(ConvertedValue.toString());
                }
                else {
                    Double input = Double.parseDouble(fromText.getText().toString());
                    ConvertedValue = UnitsConverter.convert(input, FromUnitsLength, ToUnitsLength);
                    toText.setText(ConvertedValue.toString());
                }
            }

            if(From.getText().equals("Liters")|| From.getText().equals("Gallons")|| From.getText().equals("Quarts")){
                if(fromText.getText().toString().equals("")){
                    Double input = Double.parseDouble(toText.getText().toString());
                    ConvertedValue = UnitsConverter.convert(input, ToUnitsVolume, FromUnitsVolume);
                    fromText.setText(ConvertedValue.toString());
                }
                else {
                    Double input = Double.parseDouble(fromText.getText().toString());
                    ConvertedValue = UnitsConverter.convert(input, FromUnitsVolume, ToUnitsVolume);
                    toText.setText(ConvertedValue.toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_toSettings){
            TextView From = (TextView) findViewById(R.id.FromUnits);
            TextView To = (TextView) findViewById(R.id.ToUnits);
            Intent intent = new Intent(MainActivity.this, Settings.class);
            intent.putExtra("FromChoice", From.getText() );
            intent.putExtra("ToChoice", To.getText() );
            startActivityForResult(intent, CHOICES);
            return true;
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == CHOICES) {
            TextView From = (TextView) findViewById(R.id.FromUnits);
            TextView To = (TextView) findViewById(R.id.ToUnits);
            From.setText(data.getStringExtra("FromChoice"));
            To.setText(data.getStringExtra("ToChoice"));
        }

    }

    public static void hideSoftKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if(view != null) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(MainActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
}
