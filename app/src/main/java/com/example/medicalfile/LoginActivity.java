package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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



public class LoginActivity extends AppCompatActivity {
    EditText mTextMail;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    CheckBox checkBox;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;


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

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mPreferences = getSharedPreferences("com.example.medicalfile", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();

//        String mail = mTextMail.getText().toString();
//        mEditor.putString(getString(R.string.mail), mail);
//        mEditor.commit();
//        mEditor.putString("key", "mitch");
//        mEditor.commit();

        String name = mPreferences.getString("key", "default");


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

                if (MySpinner.getSelectedItem().toString().equals("Medic"))
                {
                    Toast.makeText(LoginActivity.this, "You cant register as Medic", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);

                    startActivity(registerIntent);
                }
            }
        });
       mButtonLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String mail = mTextMail.getText().toString().trim();
               String pwd = mTextPassword.getText().toString().trim();

               if (MySpinner.getSelectedItem().toString().equals("Medic"))
               {
                     Boolean res = db.checkMedic(mail, pwd);

                     if(res == true) {

                         String mailuser = mTextMail.getText().toString();
                         mEditor.putString(getString(R.string.mail), mailuser);
                         mEditor.commit();


                         Intent MedicPage = new Intent(LoginActivity.this, MedicActivity.class);
                         startActivity(MedicPage);
                     }

               }

               if (MySpinner.getSelectedItem().toString().equals("Client"))
                   {
                       Boolean res = db.checkUser(mail, pwd);
                       if(res == true) {


                           String mailuser = mTextMail.getText().toString();
                           mEditor.putString(getString(R.string.mail), mailuser);
                           mEditor.commit();


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
