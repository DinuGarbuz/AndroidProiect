package com.example.medicalfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
    EditText mTextFirstname;
    EditText mTextLastname;
    EditText mTextMail;
    EditText mTextPhone;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    CheckBox checkBox;

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextFirstname = (EditText)findViewById(R.id.edittext_firstname);
        mTextLastname = (EditText)findViewById(R.id.edittext_lastname);
        mTextMail = (EditText)findViewById(R.id.edittext_mail);
        mTextPhone = (EditText)findViewById(R.id.edittext_phone);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        checkBox = findViewById(R.id.checkbox_password_register);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mPreferences = getSharedPreferences("com.example.medicalfile", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();

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
                String firstname = mTextFirstname.getText().toString().trim();
                String lastname = mTextLastname.getText().toString().trim();
                String mail = mTextMail.getText().toString().trim();
                String phone = mTextPhone.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pdw = mTextCnfPassword.getText().toString().trim();



                String mailVerific = mTextMail.getText().toString();
                Boolean res = db.checkMail(mailVerific);
                if(res == true)
                {
                    Toast.makeText(RegisterActivity.this, "Mail exists", Toast.LENGTH_SHORT).show();
                }
                else {


                    if (pwd.equals(cnf_pdw) && !pwd.isEmpty() && !cnf_pdw.isEmpty() && mail.contains("@") && mail.contains(".") && phone.matches("[0-9]+") && phone.length() == 10) {
                        long val = db.addUser(firstname, lastname, pwd, mail, phone);
                        if (val > 0) {

                            String mailuser = mTextMail.getText().toString();
                            mEditor.putString(getString(R.string.mail), mailuser);
                            mEditor.commit();

                            // Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                            Intent moveToLogin = new Intent(RegisterActivity.this, completareFisaActivity.class);
                            startActivity(moveToLogin);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                        }


                    }
                    if (!pwd.equals(cnf_pdw)) {
                        Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();
                    }

                    if (!mail.contains("@") || !mail.contains(".")) {
                        Toast.makeText(RegisterActivity.this, "Mail is invalid", Toast.LENGTH_SHORT).show();
                    }

                    if (!phone.contains("[0-9]+") && phone.length() != 10) {
                        Toast.makeText(RegisterActivity.this, "Phone number is invalid", Toast.LENGTH_SHORT).show();
                    }

                    if(pwd.isEmpty())
                    {
                        Toast.makeText(RegisterActivity.this, "password field is empty", Toast.LENGTH_SHORT).show();
                    }

                    if(cnf_pdw.isEmpty())
                    {
                        Toast.makeText(RegisterActivity.this, "confirm password field is empty", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
