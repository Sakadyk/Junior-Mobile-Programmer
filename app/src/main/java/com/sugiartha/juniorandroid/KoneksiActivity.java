package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class KoneksiActivity extends AppCompatActivity {

    private LinearLayout toastLayout; // Menyimpan referensi ke layout toast
    private View rootLayout; // Menyimpan referensi ke layout root
    private ProgressBar progressBar; // Menyimpan referensi ke progress bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koneksi);

        // Mendapatkan referensi ke elemen UI yang diperlukan
        toastLayout = findViewById(R.id.toastLayout);
        rootLayout = findViewById(R.id.rootLayout);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE); // Awalnya tersembunyi

        // Menambahkan listener klik pada tombol "Check"
        Button btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE); // Menampilkan loading spinner

                // Mengecek koneksi jaringan
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnected()) {
                    // Jika terhubung ke jaringan
                    progressBar.setVisibility(View.VISIBLE); // Menampilkan loading spinner

                    // Menampilkan toast yang terhubung
                    Toast connectedToast = Toast.makeText(getApplicationContext(), "Anda terhubung ke " + netInfo.getTypeName() + " " + netInfo.getSubtypeName(), Toast.LENGTH_SHORT);
                    connectedToast.setGravity(Gravity.CENTER, 0, 0);
                    ImageView toastImage = (ImageView) toastLayout.getChildAt(0);
                    toastImage.setImageResource(R.drawable.ic_connected);
                    connectedToast.show();

                    Toast.makeText(KoneksiActivity.this, "You are connected to " + netInfo.getTypeName() + " " + netInfo.getSubtypeName(), Toast.LENGTH_SHORT).show();

                    rootLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.connected_background));
                } else {
                    // Jika tidak terhubung ke jaringan
                    Toast disconnectedToast = Toast.makeText(getApplicationContext(), "Anda tidak memiliki koneksi.", Toast.LENGTH_SHORT);
                    disconnectedToast.setGravity(Gravity.CENTER, 0, 0);
                    ImageView toastImage = (ImageView) toastLayout.getChildAt(0);
                    toastImage.setImageResource(R.drawable.ic_disconnected);
                    disconnectedToast.show();

                    Toast.makeText(KoneksiActivity.this, "You don't have connection.", Toast.LENGTH_SHORT).show();

                    rootLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.disconnected_background));

                    // Menampilkan prompt dan tombol "Tutup"
                    AlertDialog.Builder alert = new AlertDialog.Builder(KoneksiActivity.this);
                    alert.setTitle("Tidak Ada Koneksi Internet");
                    alert.setMessage("Silakan periksa koneksi internet anda dan coba lagi");
                    alert.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish(); // Menutup aktivitas dan keluar dari aplikasi
                        }
                    });
                    alert.setCancelable(false);
                    alert.show();
                }
            }
        });

        // Memeriksa koneksi jaringan saat activity dibuka
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Jika terhubung ke jaringan saat activity dibuka
            progressBar.setVisibility(View.INVISIBLE); // Menyembunyikan loading spinner
            rootLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.connected_background));
            Toast.makeText(getApplicationContext(), "Terhubung Dengan Internet", Toast.LENGTH_LONG).show();
        } else {
            // Jika tidak terhubung ke jaringan saat activity dibuka
            rootLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.disconnected_background));

            // Menampilkan prompt dan tombol "Tutup"
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Tidak Ada Koneksi Internet");
            alert.setMessage("Silakan periksa koneksi internet anda dan coba lagi");
            alert.setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish(); // Menutup aktivitas dan keluar dari aplikasi
                }
            });
            alert.setCancelable(false);
            alert.show();
        }
    }
}





