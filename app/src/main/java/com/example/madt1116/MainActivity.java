package com.example.madt1116;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //ListView
    ListView listView;

    //Data to be displayed into list
    String[] currencies = {
            "Vienas", "du trys", "keturi pernki"
    };
    String[] temp;

    TextView tvContent;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.tvContent = findViewById(R.id.tvContent);

        listView = findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                currencies
        );

        listView.setAdapter(adapter);

    }

    public void onBtnDownloadClick(View view) {
        this.tvContent.setText("Loading...");
        new DataLoader() {
            @Override
            public void onPostExecute(String results) {

                currencies = results.split(" ");

                //kazkaip ikelti info i listview neiseina adapteris nedaro savo darbo arba as jo nemoku iskviesti
                listView.setAdapter(adapter);   //CIA NEVEIKIA, 3h issvaisciau savo laika bandydamas sutaisyti ir 2h nakties nuejau miegot, man uzteko aciu turiu ka veikti gyvenime
                tvContent.setText("done");

                //tvContent.setText(currencies[0]);  //Testavimas
                //tvContent.setText(currencies[30]);

            }
        }.execute("anything");


    }
}

