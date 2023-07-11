package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class StorageActivity extends AppCompatActivity {

    private Button Internal, External;
    private EditText InputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        InputData = findViewById(R.id.input_data);
        Internal = findViewById(R.id.save_internal);
        External = findViewById(R.id.save_external);

        Internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setData = InputData.getText().toString();
                // Digunakan untuk membuat dan menulis File/Data pada Penyimpanan
                FileOutputStream fileOutputStream;
                try {
                    // Mendapatkan path ke folder Documents
                    File documentsFolder = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        documentsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    }
                    // Membuat direktori Documents jika belum ada
                    if (!documentsFolder.exists()) {
                        documentsFolder.mkdirs();
                    }
                    // Membuat berkas baru di folder Documents
                    File file = new File(documentsFolder, "DataSaya.txt");
                    // Membuat file output stream untuk menulis data ke berkas
                    fileOutputStream = new FileOutputStream(file);
                    // Menulis data baru dan mengkonversinya ke dalam bentuk byte
                    fileOutputStream.write(setData.getBytes());
                    // Menutup FileOutputStream
                    fileOutputStream.close();

                    Toast.makeText(getApplicationContext(), "Data Disimpan di Documents", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        External.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setData = InputData.getText().toString();
                // Digunakan untuk membuat dan menulis File/Data pada Penyimpanan
                FileOutputStream fileOutputStream;
                try {
                    // Memeriksa apakah penyimpanan eksternal tersedia untuk menulis
                    if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                        // Mendapatkan path ke folder Documents di penyimpanan eksternal
                        File externalDocumentsFolder = new File(Environment.getExternalStorageDirectory(), "Documents");
                        // Membuat direktori Documents jika belum ada
                        if (!externalDocumentsFolder.exists()) {
                            externalDocumentsFolder.mkdirs();
                        }
                        // Membuat berkas baru di folder Documents penyimpanan eksternal
                        File file = new File(externalDocumentsFolder, "DataSaya.txt");
                        // Membuat file output stream untuk menulis data ke berkas
                        fileOutputStream = new FileOutputStream(file);
                        // Menulis data baru dan mengkonversinya ke dalam bentuk byte
                        fileOutputStream.write(setData.getBytes());
                        // Menutup FileOutputStream
                        fileOutputStream.close();

                        Toast.makeText(getApplicationContext(), "Data Disimpan di Penyimpanan Eksternal", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Penyimpanan Eksternal Tidak Tersedia", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}