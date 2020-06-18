package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class LoginActivity extends AppCompatActivity {
    EditText mTextMail;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    DatabaseHelper db;
    CheckBox checkBox;




//    private void writeToFile(String data,Context context) {
//        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("user.txt", Context.MODE_PRIVATE));
//            outputStreamWriter.write(data);
//            outputStreamWriter.close();
//        }
//        catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//    }
//
//    private String readFromFile(Context context) {
//
//        String ret = "";
//
//        try {
//            InputStream inputStream = context.openFileInput("user.txt");
//
//            if ( inputStream != null ) {
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String receiveString = "";
//                StringBuilder stringBuilder = new StringBuilder();
//
//                while ( (receiveString = bufferedReader.readLine()) != null ) {
//                    stringBuilder.append("\n").append(receiveString);
//                }
//
//                inputStream.close();
//                ret = stringBuilder.toString();
//            }
//        }
//        catch (FileNotFoundException e) {
//            Log.e("login activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.e("login activity", "Can not read file: " + e.toString());
//        }
//
//        return ret;
//    }


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
