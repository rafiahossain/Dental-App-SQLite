package com.example.mydentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MyLoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin, btnregister;
    MyDBHelp DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);

        username = (EditText)findViewById(R.id.username1);
        password = (EditText)findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnlogin1);
        btnregister = (Button) findViewById(R.id.btnregister1);
        DB = new MyDBHelp(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                //check if any field empty
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(MyLoginActivity.this, "Please enter values for all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    //if username and password exists in database
                    if(checkuserpass==true){
                        Toast.makeText(MyLoginActivity.this, "Login sucessful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(MyLoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MyRegisterPage.class);
                startActivity(i);
            }
        });

    }
}