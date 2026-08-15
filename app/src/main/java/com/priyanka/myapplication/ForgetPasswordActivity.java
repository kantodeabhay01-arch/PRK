package com.priyanka.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.priyanka.myapplication.comman.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText etForgetPasswordUsername, etForgetPasswordNewPassword, etForgetPasswordConfirmPassword;
    Button btnForgetPasswordReset;
    TextView tvForgetPasswordBackToLogin, tvLoginUser;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        etForgetPasswordUsername = findViewById(R.id.etForgetPasswordUsername);
        etForgetPasswordNewPassword = findViewById(R.id.etForgetPasswordNewPassword);
        etForgetPasswordConfirmPassword = findViewById(R.id.etForgetPasswordConfirmPassword);
        btnForgetPasswordReset = findViewById(R.id.btnForgetPasswordReset);

        tvForgetPasswordBackToLogin = findViewById(R.id.tvForgetPasswordBackToLogin);
        tvLoginUser = findViewById(R.id.tvLoginUser);

        tvLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvForgetPasswordBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnForgetPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etForgetPasswordUsername.getText().toString().isEmpty())
                {
                    etForgetPasswordUsername.setError("Please enter username");
                } else if (etForgetPasswordUsername.getText().toString().length()<8)
                {
                    etForgetPasswordUsername.setError("Username must be greater then 8");
                }
                else if(etForgetPasswordNewPassword.getText().toString().isEmpty())
                {
                    etForgetPasswordNewPassword.setError("Please enter new password");
                } else if (etForgetPasswordNewPassword.getText().toString().length()<8)
                {
                    etForgetPasswordNewPassword.setError("New password must be greater then 8");
                }
                else if(etForgetPasswordConfirmPassword.getText().toString().isEmpty())
                {
                    etForgetPasswordConfirmPassword.setError("Please enter confirm password");
                } else if (etForgetPasswordConfirmPassword.getText().toString().length()<8)
                {
                    etForgetPasswordConfirmPassword.setError("Confirm password must be greater then 8");
                }
                else if (!etForgetPasswordNewPassword.getText().toString()
                        .equals(etForgetPasswordConfirmPassword.getText().toString())) {
                    etForgetPasswordConfirmPassword.setError("New password and confirm password doesn't match.");
                }
                else
                {
                    progressDialog = new ProgressDialog(ForgetPasswordActivity.this);
                    progressDialog.setTitle("Forget Password");
                    progressDialog.setMessage("Please Wait");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();

                    forgetPassword();
                }
            }
        });
    }

    private void forgetPassword()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        params.put("username",etForgetPasswordUsername.getText().toString());
        params.put("password",etForgetPasswordNewPassword.getText().toString());

        client.post(Urls.forgetPassword,params,new JsonHttpResponseHandler()
                {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        progressDialog.dismiss();
                        try {

                            String status = response.getString("success");
                            String message = response.getString("message");

                            if(status.equals("1"))
                            {
                                Toast.makeText(ForgetPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ForgetPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);

                        progressDialog.dismiss();
                        Toast.makeText(ForgetPasswordActivity.this,"Server error",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}