package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class InternalExternalActivity extends AppCompatActivity {

    public static final String FILENAME = "dts2022.txt";
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_external);

        Button save = findViewById(R.id.btnSave);
        Button read = findViewById(R.id.btnRead);
        Button delete = findViewById(R.id.btnDelete);
        Button clear = findViewById(R.id.btnClear);
        textBaca = findViewById(R.id.textBaca);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String setData = textBaca.getText().toString();

                try {
                    // Get the path to the Documents folder
                    File documentsFolder = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        documentsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    }
                    // Create the Documents directory if it doesn't exist
                    if (!documentsFolder.exists()) {
                        documentsFolder.mkdirs();
                    }
                    // Create a new file in the Documents folder
                    File file = new File(documentsFolder, "File.txt");
                    // Create a FileWriter to write data to the file
                    FileWriter fileWriter = new FileWriter(file);
                    // Write the data to the file
                    fileWriter.write(setData);
                    // Close the FileWriter
                    fileWriter.close();
                    Toast.makeText(getApplicationContext(), "File disimpan di Documents", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Get the path to the Documents folder
                    File documentsFolder = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        documentsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    }
                    // Create a new file object for "File.txt"
                    File file = new File(documentsFolder, "File.txt");
                    // Create a FileReader to read the contents of the file
                    FileReader fileReader = new FileReader(file);
                    // Create a BufferedReader to read text from the FileReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    // Read the text from the file
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append("\n");
                    }
                    // Close the FileReader and BufferedReader
                    bufferedReader.close();
                    fileReader.close();
                    // Set the read text to the textBaca EditText
                    textBaca.setText(stringBuilder.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Mendapatkan path ke folder Documents
                    File documentsFolder = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        documentsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
                    }
                    // Mendapatkan path file yang ingin dihapus
                    File file = new File(documentsFolder, "File.txt");
                    // Menghapus file jika ada
                    if (file.exists()) {
                        if (file.delete()) {
                            Toast.makeText(getApplicationContext(), "File berhasil dihapus", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Gagal menghapus file", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "File tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear the content of textBaca
                textBaca.setText("");
            }
        });

    }
}