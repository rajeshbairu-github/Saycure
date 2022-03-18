package com.example.saycure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Search_Hospital_byLocation extends AppCompatActivity {

    ImageView iv_select_district,iv_select_location,iv_select_special,iv_search_bt;

    TextView tv_district;

    RelativeLayout rl_district,rl_location,rl_specialisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__hospital_by_location);


        iv_select_district=findViewById(R.id.s_district_bt);
        iv_select_location=findViewById(R.id.s_loc_bt);
        iv_select_special=findViewById(R.id.s_spec_bt);
        iv_search_bt=findViewById(R.id.search_icon);

        rl_district=findViewById(R.id.district_l);
        rl_location=findViewById(R.id.location_l);
        rl_specialisation=findViewById(R.id.specialisation_l);

        tv_district=findViewById(R.id.district_title);

        OnClickListener();

    }


    private void OnClickListener()
    {
        rl_district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDistrictDialog();
            }
        });

        rl_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_specialisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_select_district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDistrictDialog();
            }
        });

        iv_select_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_select_special.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        iv_search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Searching",Toast.LENGTH_SHORT).show();
            }
        });


    }

    String[] districts = {"India", "Brazil", "Argentina", "Portugal", "France", "England", "Italy","India", "Brazil", "Argentina", "Portugal", "France", "England", "Italy"};
    ArrayAdapter<String> adapter;
    ListView listView;

    AlertDialog dialog;

    public void SelectDistrictDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Select District");

        View rowList = getLayoutInflater().inflate(R.layout.popup_search, null);
        listView = rowList.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, districts);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        alertDialog.setView(rowList);
        dialog = alertDialog.create();
        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tv_district.setText(districts[position]);
                  dialog.dismiss();

                // Toast.makeText(getApplicationContext(),names[position],Toast.LENGTH_SHORT).show();
            }
        });
    }



}