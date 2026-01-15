package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button addButton;
    Button deleteButton;
    Button confirmButton;
    SearchView searchView;
    LinearLayout searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);
        confirmButton = findViewById(R.id.confirm_button);
        searchView = findViewById(R.id.search);
        searchBar = findViewById(R.id.search_popup);
        searchBar.setVisibility(View.INVISIBLE);

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        addButton.setOnClickListener(v -> {
            searchBar.setVisibility(View.VISIBLE);
        });

        deleteButton.setOnClickListener(v -> {
            int position = cityList.getCheckedItemPosition();
            if (position == ListView.INVALID_POSITION) {
                return;
            }

            dataList.remove(position);
            cityList.clearChoices();
            cityAdapter.notifyDataSetChanged();
        });

        confirmButton.setOnClickListener(v -> {
            searchBar.setVisibility(View.GONE);

            String query = searchView.getQuery().toString();
            if (!query.isEmpty()) {
                dataList.add(query);
                cityAdapter.notifyDataSetChanged();
                searchView.setQuery("", false);
            }
        });
    }
}