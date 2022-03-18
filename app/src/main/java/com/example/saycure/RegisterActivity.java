package com.example.saycure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText et_first,et_last,et_dob,et_email,et_phone,et_address,et_district,et_town,et_locality;

    TextView tv_signin;

    String first,last,dob,email,phone,address,district,town,locality;

    Button bt_register;

    ImageView iv_male,iv_female;
    RadioButton rb_male,rb_female;

    Boolean isGenderChecked=false;
    int GenderValue=-1;

    View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       layout=findViewById(R.id.main_layout);

        et_first=findViewById(R.id.firstname);
        et_last=findViewById(R.id.lastname);
        et_dob=findViewById(R.id.dob);
        et_email=findViewById(R.id.email);
        et_phone=findViewById(R.id.phone);
        et_address=findViewById(R.id.address);
        et_district=findViewById(R.id.district);
        et_town=findViewById(R.id.town);
        et_locality=findViewById(R.id.locality);

        tv_signin=findViewById(R.id.signin);
        bt_register=findViewById(R.id.register);

        iv_male=findViewById(R.id.male);
        iv_female=findViewById(R.id.female);

        rb_male=findViewById(R.id.male_rb);
        rb_female=findViewById(R.id.female_rb);

        clickListeners();

    }

    private void clickListeners()
    {
        iv_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(),"Mal clicked",Toast.LENGTH_SHORT).show();

                isGenderChecked=true;
                GenderValue=1;

                rb_male.setChecked(true);

                if (rb_female.isChecked())
                {
                    rb_female.setChecked(false);
                }

            }
        });
        

        iv_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(getApplicationContext(),"Female Clicked",Toast.LENGTH_SHORT).show();

                isGenderChecked=true;
                GenderValue=2;

                rb_female.setChecked(true);

                if(rb_male.isChecked())
                {
                    rb_male.setChecked(false);
                }

            }
        });

      //  rb_male.setOnCheckedChangeListener(new View);

        rb_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isGenderChecked=true;
                GenderValue=1;

                if (rb_female.isChecked())
                {
                    rb_female.setChecked(false);
                }
            }
        });

        rb_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGenderChecked=true;
                GenderValue=2;

                if(rb_male.isChecked())
                {
                   rb_male.setChecked(false);
                }
            }
        });

        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkInptuFields())
                {
                   // Toast.makeText(getApplicationContext(),"Fields Missing",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    regsiterUser();
                }

            }
        });

         myCalendar = Calendar.getInstance();

         DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


         et_dob.setOnTouchListener(new View.OnTouchListener() {
             @Override
             public boolean onTouch(View v, MotionEvent event) {
                 Toast.makeText(getApplicationContext(),"Pick Date",Toast.LENGTH_SHORT).show();
                 return false;
             }
         });

        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    Calendar myCalendar;

    private void updateLabel() {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);


        et_dob.setText(sdf.format(myCalendar.getTime()));
    }

    private boolean checkInptuFields()
    {
        boolean errorState=false;

        first= Objects.requireNonNull(et_first.getText()).toString();
        last= Objects.requireNonNull(et_last.getText()).toString();
        dob= Objects.requireNonNull(et_dob.getText()).toString();
        email=et_email.getText().toString();
        phone= Objects.requireNonNull(et_phone.getText()).toString();
        address= Objects.requireNonNull(et_address.getText()).toString();
        district= Objects.requireNonNull(et_district.getText()).toString();
        town= Objects.requireNonNull(et_town.getText()).toString();
        locality= Objects.requireNonNull(et_locality.getText()).toString();


        if(first.equals(""))
        {
            et_first.setError("Missing");
            errorState=true;
        }

        if (last.equals(""))
        {
            et_last.setError("Missing");
            errorState=true;
        }

        if(dob.equals(""))
        {et_dob.setError("Missing");
            errorState=true;

        }

        if(email.equals(""))
        {
            et_email.setError("Missing");
            errorState=true;
        }

        if(phone.equals(""))
        { et_phone.setError("Missing");
            errorState=true;

        }

        if(address.equals(""))
        { et_address.setError("Missing");
            errorState=true;

        }

        if(district.equals(""))
        {
            et_district.setError("Missing");
            errorState=true;
        }

        if(town.equals(""))
        {
            et_town.setError("Missing");
            errorState=true;
        }

        if(locality.equals(""))
        {
            et_locality.setError("Missing");
            errorState=true;

        }

        if(!isGenderChecked)
        {  Snackbar.make(layout, "Please Select Gender", Snackbar.LENGTH_SHORT).show();
            errorState=true;
        }


        return errorState;
    }

    private void regsiterUser() {

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://saycure.co.in/saycure_registerPatient.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject=new JSONObject(response);

                            String error_value=jsonObject.getString("error");

                            if(error_value.equals("false"))
                            {
                                Toast.makeText(RegisterActivity.this,"Patinent Registration Successful",Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {


                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("firstname",first);
                params.put("lastname",last);
                params.put("dob",dob);
                params.put("email",email);
                params.put("phone",phone);
                if(GenderValue==1)
                {
                    params.put("gender","1");
                }else if(GenderValue==2)
                {
                    params.put("gender","2");
                }
                params.put("address",address);
                params.put("district",district);
                params.put("town",town);
                params.put("locality",locality);


                return  params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}