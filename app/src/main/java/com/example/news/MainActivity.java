package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.news.models.NewsApiResponse;
import com.example.news.models.news_Headlines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements  SelectListner, View.OnClickListener{

    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1, b2, b3, b4, b5, b6, b7;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching News Articles");
        dialog.show();

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching News Articles Of " + query);
                dialog.show();
                RequestManager manager = new RequestManager(MainActivity.this);
                manager.getNewsHeadlines(listner, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listner, "general", null);

    }

    private  final OnDataFetchListner<NewsApiResponse> listner = new OnDataFetchListner<NewsApiResponse>() {
        @Override
        public void onFetchData(List<news_Headlines> list, String message) {
            if (list.isEmpty()){
                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }else {
                showNews(list);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "An Error Occured..", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<news_Headlines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(news_Headlines headlines) {
//        startActivity(new Intent(MainActivity.this, DetailActivity.class)
//                .putExtra("data", headlines));
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("data", headlines);
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String catogory = button.getText().toString();
        dialog.setTitle("Fetching ews articles of " + catogory);
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listner, catogory, null);
    }
}