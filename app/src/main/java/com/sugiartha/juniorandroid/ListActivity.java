package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {

    private ListView lvItem;
    private String[] namanegara = new String[]{
            "Indonesia", "Malaysia", "Singapore",
            "Italia", "Inggris", "Belanda",
            "Argentina", "Chile",
            "Mesir", "Uganda", "Thailand", "Filipina",
            "Vietnam", "Amerika", "China", "Jepang",
            "Korea"};

    private int[] bendera = new int[]{
            R.drawable.indonesia, R.drawable.malaysia, R.drawable.singapore,
            R.drawable.italia, R.drawable.inggris, R.drawable.belanda,
            R.drawable.argentina, R.drawable.chile,
            R.drawable.mesir, R.drawable.uganda,R.drawable.thailand,
            R.drawable.filipina, R.drawable.vietnam, R.drawable.amerika,
            R.drawable.china, R.drawable.jepang, R.drawable.korea};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setTitle("ListView Sederhana");

        lvItem = (ListView) findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListActivity.this,
                android.R.layout.simple_list_item_1, android.R.id.text1, namanegara);

        lvItem.setAdapter(adapter);

        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = getLayoutInflater();
                View toastLayout = inflater.inflate(R.layout.toast_layout,
                        (ViewGroup) findViewById(R.id.toast_root));

                ImageView imageView = toastLayout.findViewById(R.id.toast_image);
                TextView textView = toastLayout.findViewById(R.id.toast_text);

                imageView.setImageResource(bendera[position]);
                textView.setText("Memilih : " + namanegara[position]);

                Toast toast = new Toast(ListActivity.this);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastLayout);
                toast.show();
            }
        });
    }
}


