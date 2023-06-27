package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class LingkaranActivity extends AppCompatActivity {
    Button hitung;
    EditText jari2;
    TextView nilaiLuas, nilaiKel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lingkaran);

        hitung = (Button) findViewById(R.id.hitung);
        jari2 = (EditText) findViewById(R.id.jari2);
        nilaiLuas = (TextView) findViewById(R.id.nilaiLuas);
        nilaiKel = (TextView) findViewById(R.id.nilaiKel);

        hitung.setOnClickListener(new Button.OnClickListener() {
            @Override public void onClick(View v) {
                double jari = Double.parseDouble(jari2.getText().toString());
                double phi = 3.14;
                double luas = phi*jari*jari;
                double keliling = 2*phi*jari;
                nilaiLuas.setText(" " + Double.toString(luas));
                nilaiKel.setText(" " + Double.toString(keliling));
            }
        });

    }
}
