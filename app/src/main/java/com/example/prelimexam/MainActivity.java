package com.example.prelimexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Components
        EditText editTextValueX = (EditText) findViewById(R.id.editTextValueX);
        EditText editTextXmin = (EditText) findViewById(R.id.editTextValueXmin);
        EditText editTextXmax = (EditText) findViewById(R.id.editTextValueXmax);
        EditText editTextPartial = (EditText) findViewById(R.id.editTextPartial);
        EditText editTextSaturation = (EditText) findViewById(R.id.editTextSaturation);

        Button buttonNorm = (Button) findViewById(R.id.btnNorm);
        Button buttonRH = (Button) findViewById(R.id.btnRelativeHumidity);

        TextView textViewNorm = (TextView) findViewById(R.id.textViewNorm);
        TextView textViewRH = (TextView) findViewById(R.id.textViewRH) ;

        buttonNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double x = Double.parseDouble(editTextValueX.getText().toString());
                    double xmin = Double.parseDouble(editTextXmin.getText().toString());
                    double xmax = Double.parseDouble(editTextXmax.getText().toString());

                    double norm = norm(x, xmin, xmax);

                    Log.i("INFO: ", String.valueOf(x));
                    Log.i("INFO: ", String.valueOf(xmin));
                    Log.i("INFO: ", String.valueOf(xmax));

                    textViewNorm.setText(String.valueOf(norm));
                }
                catch (Exception e){
                    Log.i("ERROR: ", e.getMessage().toString());

                    textViewNorm.setText(e.getMessage().toString());
                }
            }
        });

        buttonRH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double partial = Double.parseDouble(editTextPartial.getText().toString());
                    double saturation = Double.parseDouble(editTextSaturation.getText().toString());

                    double rh = rel(partial, saturation);

                    Log.i("INFO: ", String.valueOf(partial));
                    Log.i("INFO: ", String.valueOf(saturation));

                    textViewRH.setText(String.valueOf(rh));

                }
                catch (Exception e){
                    Log.i("ERROR: ", e.getMessage().toString());

                    textViewRH.setText(e.getMessage().toString());
                }
            }
        });

    }
    private double norm(double x, double xmin, double xmax){
        return (x - xmin) / (xmax - xmin);
    }

    private double rel(double partial, double saturation){
        return (partial / saturation) * 100;
    }
}