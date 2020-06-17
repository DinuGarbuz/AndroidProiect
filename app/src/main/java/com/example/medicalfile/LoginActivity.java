package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

import java.io.File;
import java.io.IOException;


public class LoginActivity extends AppCompatActivity {
    EditText mTextMail;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    CheckBox checkBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner MySpinner = (Spinner) findViewById(R.id.spinner) ;
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MySpinner.setAdapter(myAdapter);



        db = new DatabaseHelper(this);
        mTextMail = (EditText)findViewById(R.id.edittext_mail);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.textview_register);
        checkBox = (CheckBox)findViewById(R.id.checkbox_password);
        mTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    mTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    mTextPassword.setTransformationMethod((PasswordTransformationMethod.getInstance()));
                }
            }
        });
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);

                startActivity(registerIntent);
            }
        });
       mButtonLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String mail = mTextMail.getText().toString().trim();
               String pwd = mTextPassword.getText().toString().trim();
              Boolean res = db.checkUser(mail, pwd);
               if(res == true)
               {
//                   File f = new File("current_user.txt");
//
//                   if(f.exists()){
//                       f.delete();
//                       try {
//                           f.createNewFile();
//                       } catch (IOException e) {
//                           e.printStackTrace();
//                       }
//                   }


                   if (MySpinner.getSelectedItem().toString().equals("Medic"))
                   {

                       Intent MedicPage = new Intent(LoginActivity.this, MedicActivity.class);
                       startActivity(MedicPage);
                   }

                   if (MySpinner.getSelectedItem().toString().equals("Client"))
                   {

                       Intent ClientPage = new Intent(LoginActivity.this, ClientActivity.class);
                       startActivity(ClientPage);
                   }

               }
               else
               {
                   Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
               }
           }
       });



    }
}
