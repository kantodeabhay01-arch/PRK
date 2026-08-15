package com.priyanka.myapplication;


import android.app.ProgressDialog;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
//import androidx.preference.PreferenceManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.priyanka.myapplication.comman.Urls;


import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    boolean doubleTap = false;
    EditText etLoginUsername;
    EditText etLoginPassword;
    CheckBox cbLoginShowHidePassword;
    AppCompatButton btnLogin;
    TextView tvLoginNewUser, tvForgetPassword;

//    ProgressDialog progressDialog;
//
//    SharedPreferences preferences;
//    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
//        editor = preferences.edit();

//        if(preferences.getBoolean("isLogin",false))
//        {
//            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
//            startActivity(i);
//            finish();
//
//        }

        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        tvForgetPassword = findViewById(R.id.tvForgetPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etLoginUsername.getText().toString();
                String password = etLoginPassword.getText().toString();

                if(username.isEmpty()) {
                    etLoginUsername.setError("Username is required");
                } else if (username.length() < 8) {
                    etLoginUsername.setError("Username must be 8 characters long");
                } else if (!username.matches(".*[A-Z].*")) {
                    etLoginUsername.setError("Username must contain 1 Upper case Letter");
                } else if (!username.matches(".*[a-z].*")) {
                    etLoginUsername.setError("Username must contain 1 Lower case Letter");
                } else if (!username.matches(".*[0-9].*")) {
                    etLoginUsername.setError("Username must contain 1 Number");
                } else if (!username.matches(".*[@,$,#,%,!].*")) {
                    etLoginUsername.setError("Username must contain 1 special symbol");
                } else if (password.isEmpty()) {
                    etLoginPassword.setError("Please enter password.");
                } else if (password.length() < 8) {
                    etLoginPassword.setError("Password must be at least 8 characters");
                } else if (!password.matches(".*[A-Z].*")) {
                    etLoginPassword.setError("Password must contain 1 Upper case Letter");
                } else if (!password.matches(".*[a-z].*")) {
                    etLoginPassword.setError("Password must contain 1 Lower case Letter");
                } else if (!password.matches(".*[0-9].*")) {
                    etLoginPassword.setError("Password must contain 1 Number");
                } else if (!password.matches(".*[@,$,#,%,!].*")) {
                    etLoginPassword.setError("Password must contain 1 special symbol");
                } else {
//                    Toast.makeText(LoginActivity.this, "Login Successful..!!", Toast.LENGTH_SHORT).show();
//
//                    editor.putBoolean("isLogin",true);
//                    editor.putString("username",username);
//                    editor.commit();
//                    // Optional: Navigate to MainActivity here
//                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
//                    startActivity(intent);
//                    finish();

//                    progressDialog = new ProgressDialog(LoginActivity.this);
//                    progressDialog.setTitle("Login User");
//                    progressDialog.setMessage("Please wait");
//                    progressDialog.setCanceledOnTouchOutside(true);
//                    progressDialog.show();

//                    loginUser();
                }
            }
        });

        cbLoginShowHidePassword = findViewById(R.id.cbLoginShowHidePassword);
        tvLoginNewUser = findViewById(R.id.tvLoginNewUser);

        cbLoginShowHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

//        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
//                startActivity(intent);
//            }
//        });
//
//
//        tvLoginNewUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

//    {
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//
//        params.put("username",etLoginUsername.getText().toString());
//        params.put("password",etLoginPassword.getText().toString());

//        client.post(Urls.loginUserAPI,params,new JsonHttpResponseHandler()
//                {
//                    @Override
//                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                        super.onSuccess(statusCode, headers, response);
//
//                        progressDialog.dismiss();
//
//                        try {
//
//                            String status = response.getString("success");
//                            String message = response.getString("message");
//
//                            if(status.equals("1"))
//                            {
//                                Toast.makeText(LoginActivity.this,message , Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
//                                editor.putBoolean("isLogin", true).commit();
//                                editor.putString("username", etLoginUsername.getText().toString()).commit();
//                                editor.commit();
//                                startActivity(intent);
//                                finishAffinity();
//
//                            }
//                            else
//                            {
//                                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                        super.onFailure(statusCode, headers, throwable, errorResponse);
//
//                        progressDialog.dismiss();
//                        Toast.makeText(LoginActivity.this,"Error :"+errorResponse,Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//        );
//    }

//    @Override
//    public void onBackPressed()
//    {
//
//        if (doubleTap)
//        {
//            finishAffinity();
//        }
//        else
//        {
//            Toast.makeText(LoginActivity.this,"Double tap to exit",Toast.LENGTH_SHORT).show();
//            doubleTap = true;
//
//            new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    doubleTap = false;
//                }
//            }, 2000);
//        }
//

}