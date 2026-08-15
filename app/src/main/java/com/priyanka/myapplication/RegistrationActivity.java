package com.priyanka.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.priyanka.myapplication.comman.Urls;

import cz.msebera.android.httpclient.Header;

import org.json.JSONException;
import org.json.JSONObject;
public class RegistrationActivity extends AppCompatActivity {

    boolean doubleTap = false;
    EditText etRegistrationName, etRegistrationMobileNo, etRegistrationEmail,
            etRegistrationUsername, etRegistrationPassword, etRegistrationConfirmPassword;
    Button btnRegistration;

    TextView tvLoginUser;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);

        etRegistrationName = findViewById(R.id.etRegistrationName);
        etRegistrationMobileNo = findViewById(R.id.etRegistrationMobileNo);
        etRegistrationEmail = findViewById(R.id.etRegistrationEmail);
        etRegistrationUsername = findViewById(R.id.etRegistrationUsername);
        etRegistrationPassword = findViewById(R.id.etRegistrationPassword);
        etRegistrationConfirmPassword = findViewById(R.id.etRegistrationConfirmPassword);

        btnRegistration = findViewById(R.id.btnRegistration);

        tvLoginUser = findViewById(R.id.tvLoginUser);

        tvLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etRegistrationName.getText().toString();
                String mobile = etRegistrationMobileNo.getText().toString();
                String email = etRegistrationEmail.getText().toString();
                String username = etRegistrationUsername.getText().toString();
                String password = etRegistrationPassword.getText().toString();
                String confirmPassword = etRegistrationConfirmPassword.getText().toString();

                if (name.isEmpty()) {
                    etRegistrationName.setError("Please enter name");
                } else if (mobile.isEmpty()) {
                    etRegistrationMobileNo.setError("Please enter mobile number");
                } else if (mobile.length() != 10) {
                    etRegistrationMobileNo.setError("Mobile number length must be 10");
                } else if (email.isEmpty()) {
                    etRegistrationEmail.setError("Please enter Email Id");
                } else if (!email.contains("@") || !email.contains(".com")) {
                    etRegistrationEmail.setError("Please enter valid Email Id");
                } else if (username.isEmpty()) {
                    etRegistrationUsername.setError("Please enter username");
                } else if (username.length() < 8) {
                    etRegistrationUsername.setError("Username must be at least 8 characters");
                } else if (!username.matches(".*[A-Z].*")) {
                    etRegistrationUsername.setError("Username must contain 1 uppercase letter");
                } else if (!username.matches(".*[a-z].*")) {
                    etRegistrationUsername.setError("Username must contain 1 lowercase letter");
                } else if (!username.matches(".*[0-9].*")) {
                    etRegistrationUsername.setError("Username must contain 1 number");
                } else if (!username.matches(".*[@,#,$,%,&,*].*")) {
                    etRegistrationUsername.setError("Username must contain 1 special symbol");
                } else if (password.isEmpty()) {
                    etRegistrationPassword.setError("Please enter Password");
                } else if (password.length() < 8) {
                    etRegistrationPassword.setError("Password must be at least 8 characters");
                } else if (!password.matches(".*[A-Z].*")) {
                    etRegistrationPassword.setError("Password must contain 1 uppercase letter");
                } else if (!password.matches(".*[a-z].*")) {
                    etRegistrationPassword.setError("Password must contain 1 lowercase letter");
                } else if (!password.matches(".*[0-9].*")) {
                    etRegistrationPassword.setError("Password must contain 1 number");
                } else if (!password.matches(".*[@,#,$,%,&,*].*")) {
                    etRegistrationPassword.setError("Password must contain 1 special symbol");
                } else if (!password.equals(confirmPassword)) {
                    etRegistrationConfirmPassword.setError("Passwords do not match");
                } else
                {
//                    Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
//                    finish(); // Go back to Login
//                    Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                    finish();

                    progressDialog = new ProgressDialog(RegistrationActivity.this);
                    progressDialog.setTitle("Registration");
                    progressDialog.setMessage("Please wait");
                    progressDialog.show();
                    registerUser();

                }
            }
        });
    }

    private void registerUser() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("name", etRegistrationName.getText().toString());
        params.put("mobile", etRegistrationMobileNo.getText().toString());
        params.put("email", etRegistrationEmail.getText().toString());
        params.put("username", etRegistrationUsername.getText().toString());
        params.put("password", etRegistrationPassword.getText().toString());

        client.post(Urls.registerUserAPI, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                progressDialog.dismiss();
                try {
                    String status = response.getString("success");
                    String message = response.getString("message");
                    if (status.equals("1")) {
                        Toast.makeText(RegistrationActivity.this, "Registration Success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(RegistrationActivity.this, "JSON Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                progressDialog.dismiss();

                String errorMsg = (throwable != null) ? throwable.getMessage() : "Unknown Error";
                Toast.makeText(RegistrationActivity.this, "Network Error: " + errorMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this, "Server Error: " + responseString, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

