package com.example.mydentalapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydentalapp.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class MyRegisterPage extends AppCompatActivity {

    EditText username, password, confirmpassword, START_WEEK;
    Button register, login;
    MyDBHelp DB;
    int code;//FOR THE EMAIL VERIFICATION PART



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_register_page);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        confirmpassword = (EditText)findViewById(R.id.confirmpassword);
        register = (Button)findViewById(R.id.btnregister);
        login = (Button)findViewById(R.id.btnlogin);
        DB = new MyDBHelp(this);
        START_WEEK = (EditText)findViewById(R.id.st_week);

        //
        //
        //
//        Random random = new Random();
//        code = random.nextInt(8999)+1000;
//        EditText emailtxt = findViewById(R.id.email);
//        //find a host like 000webhost to place sendEmail.php in
//        String url = "";
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest = new Stringrequest(Request.Method.POST, url, new Response.Listener<String>(){
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(MyRegisterPage.this, response, Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error){
//                Toast.makeText(MyRegisterPage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email", emailtxt.getText().toString());
//                params.put("code", String.valueOf(code));
//                return params;
//            }
//
//        };
//
//        requestQueue.add(stringRequest);

        //stopped at 9.34
        //
        //

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String confpass = confirmpassword.getText().toString();
                String tempStr = START_WEEK.getText().toString();
                int START_WEEK_PREGNANCY = Integer.parseInt(tempStr);

                //check if user exists or not
                if(user.equals("")||pass.equals("")||confpass.equals(""))
                    Toast.makeText(MyRegisterPage.this, "Please enter values for all field", Toast.LENGTH_SHORT).show();
                else{
                    //else check if user exists or not
                    if(pass.equals(confpass)){
                        Boolean checkuser = DB.checkUsername(user);
                        //if user does not exist-> insert
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            //if data written to database show toast message
                            if(insert==true){
                                //
                                //email verification
                                //

                                //
                                //move calculation to home page and use start date as parameter?
                                //

//                                Toast.makeText(MyRegisterPage.this, START_WEEK_PREGNANCY, Toast.LENGTH_SHORT).show();
                                Toast.makeText(MyRegisterPage.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MyLoginActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(MyRegisterPage.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            //else when checkuser true
                            Toast.makeText(MyRegisterPage.this, "User already exists, please login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MyRegisterPage.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MyLoginActivity.class);
                startActivity(i);
            }
        });






    }




}