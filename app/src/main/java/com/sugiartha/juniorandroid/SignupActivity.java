package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Spinner spin;
    String spin_val;
    String[] gender = { "Laki-Laki", "Perempuan" };

    EditText etPassword;
    EditText etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spin = (Spinner) findViewById(R.id.spinner_id);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                spin_val = gender[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> spin_adapter = new ArrayAdapter<String>(SignupActivity.this, android.R.layout.simple_spinner_item, gender);
        spin.setAdapter(spin_adapter);

        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (password.isEmpty() || confirmPassword.isEmpty()) {
                    // Ada field yang kosong, tampilkan pesan kesalahan
                    Toast.makeText(SignupActivity.this, "Harap lengkapi semua field", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    // Konfirmasi password tidak cocok, tampilkan pesan kesalahan
                    Toast.makeText(SignupActivity.this, "Konfirmasi password tidak cocok", Toast.LENGTH_SHORT).show();
                } else {
                    // Lanjutkan dengan proses pendaftaran
                    // ...
                }
            }
        });
    }
}



