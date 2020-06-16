package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextMail;
    EditText mTextPhone;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextMail = (EditText)findViewById(R.id.edittext_mail);
        mTextPhone = (EditText)findViewById(R.id.edittext_phone);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        checkBox = findViewById(R.id.checkbox_password_register);

        mTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mTextCnfPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    mTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mTextCnfPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

                else
                {
                    mTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mTextCnfPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String mail = mTextMail.getText().toString().trim();
                String phone = mTextPhone.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pdw = mTextCnfPassword.getText().toString().trim();

                if(pwd.equals(cnf_pdw))
                {
                   long val = db.addUser(user, pwd, mail, phone);
                   if(val > 0)
                   {
                       Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                       Intent moveToLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                       startActivity(moveToLogin);}
                   else
                   {
                       Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                   }


                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
