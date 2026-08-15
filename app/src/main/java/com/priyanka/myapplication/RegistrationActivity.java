package com.priyanka.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText etName, etEmail, etMobile;
    EditText etPassword, etConfirmPassword, etCity;

    RadioGroup rgRole;
    RadioButton rbLearner, rbVolunteer;

    CheckBox cbTerms;
    Button btnRegister;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration2);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etCity = findViewById(R.id.etCity);

        rgRole = findViewById(R.id.rgRole);
        rbLearner = findViewById(R.id.rbLearner);
        rbVolunteer = findViewById(R.id.rbVolunteer);

        cbTerms = findViewById(R.id.cbTerms);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> registerUser());

        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(
                    RegistrationActivity.this,
                    LoginActivity.class
            );
            startActivity(intent);
        });
    }

    private void registerUser() {

        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        String city = etCity.getText().toString().trim();

        int selectedRoleId = rgRole.getCheckedRadioButtonId();

        if (TextUtils.isEmpty(name)) {
            etName.setError("Enter your name");
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Enter your email");
            etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            etMobile.setError("Enter mobile number");
            etMobile.requestFocus();
            return;
        }

        if (mobile.length() != 10) {
            etMobile.setError("Enter a valid 10-digit mobile number");
            etMobile.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter password");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            etPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            etConfirmPassword.requestFocus();
            return;
        }

        if (selectedRoleId == -1) {
            Toast.makeText(
                    this,
                    "Please select Learner or Volunteer",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        String role;

        if (selectedRoleId == R.id.rbVolunteer) {
            role = "Volunteer";
        } else {
            role = "Learner";
        }

        if (TextUtils.isEmpty(city)) {
            etCity.setError("Enter your city");
            etCity.requestFocus();
            return;
        }

        if (!cbTerms.isChecked()) {
            Toast.makeText(
                    this,
                    "Please accept Terms & Conditions",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        Toast.makeText(
                this,
                "Registration Successful!",
                Toast.LENGTH_LONG
        ).show();

        Intent intent = new Intent(
                RegistrationActivity.this,
                LoginActivity.class
        );

        startActivity(intent);
        finish();
    }
}