package com.example.saycure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search_Hospitals extends AppCompatActivity {

    EditText et_search;

    private ArrayList<SearchItem> mSearchList;

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    Adapter_search adapter_search;

    ArrayList<String> hospitalName=new ArrayList<String>();

   LinearLayout l_all,l_hospitals,l_doctors;
   View all,hospitals,doctors;

   RelativeLayout rl;

   TextView tv_searchByLocation;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__hospitals);

        rl=findViewById(R.id.main_layout);

        et_search=findViewById(R.id.search_h_d);

        l_all=findViewById(R.id.all_l);
        l_hospitals=findViewById(R.id.hospitals_l);
        l_doctors=findViewById(R.id.doctors_l);

        all=findViewById(R.id.all_line);
        hospitals=findViewById(R.id.hospitals_line);
        doctors=findViewById(R.id.doctors_line);

        tv_searchByLocation=findViewById(R.id.search_bylocation);

        hospitals.setVisibility(View.GONE);
        doctors.setVisibility(View.GONE);

        onClickListeners();

        hospitalName=getIntent().getStringArrayListExtra("hospitalNames");

     //   Toast.makeText(getApplicationContext(),hospitalName.get(0),Toast.LENGTH_SHORT).show();
        intent=new Intent(this,Search_Hospital_byLocation.class);


        textsetter();
        createExampleList();
        initRecyclerview();

    }

    private void onClickListeners()
    {

        l_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                all.setVisibility(View.VISIBLE);
                hospitals.setVisibility(View.GONE);
                doctors.setVisibility(View.GONE);

            }
        });

        l_hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                all.setVisibility(View.GONE);
                hospitals.setVisibility(View.VISIBLE);
                doctors.setVisibility(View.GONE);

            }
        });

        l_doctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                all.setVisibility(View.GONE);
                hospitals.setVisibility(View.GONE);
                doctors.setVisibility(View.VISIBLE);

            }
        });

        tv_searchByLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);

            }
        });
    }

    private void textsetter()
    {
        // Toast.makeText(getApplicationContext(),"Text Setter",Toast.LENGTH_SHORT).show();

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               // tv.setText(s.toString());
                //  Toast.makeText(getApplicationContext(),s.toString(),Toast.LENGTH_SHORT).show();
                filter(s.toString());
            }
        });

    }

    private void createExampleList() {

        try {
            mSearchList = new ArrayList<>();

            for (int i = 0; i < hospitalName.size(); i++) {
                mSearchList.add(new SearchItem(hospitalName.get(i)));
            }

            Toast.makeText(getApplicationContext(), "Example", Toast.LENGTH_SHORT).show();
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void initRecyclerview() {

        try {
            mRecyclerView = findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            adapter_search = new Adapter_search(this, mSearchList);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter_search);

        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    private void filter(String text) {
        ArrayList<SearchItem> filteredList = new ArrayList<>();
        for (SearchItem item : mSearchList) {

            if(item.getMname().toLowerCase().startsWith(text.toLowerCase())){
                filteredList.add(item);
            }

            /*if (item.getMname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }*/

        }
        adapter_search.filterList(filteredList);
    }


}

