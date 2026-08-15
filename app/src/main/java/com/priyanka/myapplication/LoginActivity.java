package com.priyanka.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


public class LoginActivity extends AppCompatActivity {

    EditText etLoginUsername, etLoginPassword;
    CheckBox cbLoginShowHidePassword;
    AppCompatButton btnLogin;
    TextView tvLoginCreateAccount, tvForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        cbLoginShowHidePassword = findViewById(R.id.cbLoginShowHidePassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvLoginCreateAccount = findViewById(R.id.tvLoginCreateAccount);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = etLoginUsername.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();

                if (username.isEmpty()) {
                    etLoginUsername.setError("Username is required");

                } else if (username.length() < 8) {
                    etLoginUsername.setError("Username must be 8 characters long");

                } else if (!username.matches(".*[A-Z].*")) {
                    etLoginUsername.setError("Username must contain 1 uppercase letter");

                } else if (!username.matches(".*[a-z].*")) {
                    etLoginUsername.setError("Username must contain 1 lowercase letter");

                } else if (!username.matches(".*[0-9].*")) {
                    etLoginUsername.setError("Username must contain 1 number");

                } else if (!username.matches(".*[@$,#%!].*")) {
                    etLoginUsername.setError("Username must contain 1 special symbol");

                } else if (password.isEmpty()) {
                    etLoginPassword.setError("Password is required");

                } else if (password.length() < 8) {
                    etLoginPassword.setError("Password must be at least 8 characters");

                } else if (!password.matches(".*[A-Z].*")) {
                    etLoginPassword.setError("Password must contain 1 uppercase letter");

                } else if (!password.matches(".*[a-z].*")) {
                    etLoginPassword.setError("Password must contain 1 lowercase letter");

                } else if (!password.matches(".*[0-9].*")) {
                    etLoginPassword.setError("Password must contain 1 number");

                } else if (!password.matches(".*[@$,#%!].*")) {
                    etLoginPassword.setError("Password must contain 1 special symbol");

                } else {

                    SharedPreferences preferences =
                            getSharedPreferences("LoginPrefs", MODE_PRIVATE);

                    preferences.edit()
                            .putBoolean("isLogin", true)
                            .apply();

                    Intent intent = new Intent(LoginActivity.this, Home_Activity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });

        cbLoginShowHidePassword.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            @NonNull CompoundButton buttonView,
                            boolean isChecked) {

                        if (isChecked) {
                            etLoginPassword.setTransformationMethod(
                                    HideReturnsTransformationMethod.getInstance());
                        } else {
                            etLoginPassword.setTransformationMethod(
                                    PasswordTransformationMethod.getInstance());
                        }
                    }
                });

        tvLoginCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        LoginActivity.this,
                        RegistrationActivity.class
                );
                startActivity(intent);
            }
        });

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        LoginActivity.this,
                        ForgetPasswordActivity.class
                );
                startActivity(intent);
            }
        });
    }
}