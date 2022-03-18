package com.example.saycure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> hospitalId=new ArrayList<Integer>();
    ArrayList<String> hospitalName=new ArrayList<String>();
    ArrayList<String> hospitalLocation=new ArrayList<String>();

    RelativeLayout right_rl,left_rl;
    AutoCompleteTextView actv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        left_rl=findViewById(R.id.left_rl);
        right_rl=findViewById(R.id.right_rl);

        setAutoCompleteTextView();

        getHospitals();
       // initDialog();

       right_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAutoCompleteTextViewFullWidth();
            }
        });


        actv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeAutoCompleteTextViewFullWidth();
            }
        });
    }

    private void makeAutoCompleteTextViewFullWidth()
    {

        Intent intent=new Intent(this,Search_Hospitals.class);
        intent.putStringArrayListExtra("hospitalNames",hospitalName);
        startActivity(intent);

       // initDialog();
       // alertDialog.show();

       // textsetter();

       // createExampleList();
       //  initRecyclerview();

       // LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(0, 0, 0f);
       // left_rl.setLayoutParams(param);

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
                tv.setText(s.toString());
              //  Toast.makeText(getApplicationContext(),s.toString(),Toast.LENGTH_SHORT).show();
                    //filter(s.toString());
            }
        });

    }

    private void filter(String text) {
        ArrayList<SearchItem> filteredList = new ArrayList<>();
        for (SearchItem item : mSearchList) {

            if (item.getMname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }

        }
        adapter_search.filterList(filteredList);
    }

    private ArrayList<SearchItem> mSearchList;

    private void createExampleList() {

        mSearchList = new ArrayList<>();

        for(int i=0;i<hospitalName.size();i++)
        {
            mSearchList.add(new SearchItem(hospitalName.get(i)));
        }

        Toast.makeText(getApplicationContext(),"Example",Toast.LENGTH_SHORT).show();

    }

    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    Adapter_search adapter_search;

    private void initRecyclerview() {

       /* mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        adapter_search = new Adapter_search(this,mSearchList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter_search);*/

    }

    private void setAutoCompleteTextView() {

       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,hospitalName);

        //Getting the instance of AutoCompleteTextView
         actv =  (AutoCompleteTextView)findViewById(R.id.search_h_d);
       // actv.setThreshold(2);//will start working from first character
       // actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
       // actv.setTextColor(Color.RED);
    }

    private AlertDialog.Builder alertDialog;
    View view;
    EditText et_search;

    TextView tv;

    private void initDialog() {

        alertDialog = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.popup_search, null);
        alertDialog.setView(view);

        et_search=findViewById(R.id.search_h_d);
        tv=findViewById(R.id.text);

        //  et_age=(EditText) view.findViewById(R.id.edt_itemAge);
        //  et_size=(EditText) view.findViewById(R.id.edt_itemSize);

//        radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup);
        //radioButton=(RadioButton) view.findViewById()


       /* alertDialog.setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (add) {
                            add = false;
                            if (!edt_item.getText().toString().isEmpty()) {
                                adapter.addItem(edt_item.getText().toString());
                            }
                            dialog.dismiss();
                        } else {
                            //   Toast.makeText(getApplicationContext(),"Edit",Toast.LENGTH_SHORT).show();
                            name.set(edit_position, edt_item.getText().toString());

                            age.set(edit_position,et_age.getText().toString());
                            size.set(edit_position,et_size.getText().toString());

                            int select=radioGroup.getCheckedRadioButtonId();
                            radioButton=(RadioButton) view.findViewById(select);
                            String text=radioButton.getText().toString();

                            if(text.equals("Male"))
                            {
                                gender.set(edit_position,"male");
                            }
                            else {
                                gender.set(edit_position,"female");
                            }

                            adapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        (dialog, id) -> {
                            adapter.notifyDataSetChanged();
                            dialog.cancel();
                        });*/
    }

    private void getHospitals()
    {
        String url="http://saycure.co.in/saycure_getHospitals.php";

        StringRequest stringRequest=new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  progressDialog.dismiss();

                       try {
                            JSONObject jsonObject;
                            JSONArray jsonArray;

                            jsonObject=new JSONObject(response);
                            jsonArray=jsonObject.getJSONArray("server_response");
                            int count=0;

                            while(count<jsonArray.length())
                            {
                                JSONObject JO=jsonArray.getJSONObject(count);

                                hospitalId.add(JO.getInt("hospitalId"));
                                hospitalName.add(JO.getString("hospitalName"));
                                hospitalLocation.add(JO.getString("hospitalLocation"));

                                count++;
                            }

                          //  Toast.makeText(getApplicationContext(),hospitalName.get(0),Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                           Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } );

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }



}