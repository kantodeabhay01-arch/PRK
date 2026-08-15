package com.priyanka.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Forget_Password_Activity extends AppCompatActivity {

    EditText etUserName , etPassword , etConformPassword;
    Button btnChange;
    CheckBox cbShowPassword , cbHidePassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);



            etUserName=findViewById(R.id.etUserName);
            etPassword=findViewById(R.id.etPassword);
            etConformPassword=findViewById(R.id.etConformPassword);
            btnChange=findViewById(R.id.btnChange);
            cbHidePassword=findViewById(R.id.cbHidePassword);
            cbShowPassword=findViewById(R.id.cbShowPassword);

            btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnChange.animate().setDuration(100).scaleY(0.9f).scaleX(0.9f).withEndAction(
                            ()->btnChange.animate().scaleX(1).scaleY(1).setDuration(100));
                    if (etUserName.getText().toString().isEmpty()){
                        etUserName.setError("Please Enter Your UserName");
                    } else if (etUserName.getText().toString().length()<8) {
                        etUserName.setError("User Name must have 8 characters");
                    } else if (!etUserName.getText().toString().matches(".*[A-Z].*")) {
                        etUserName.setError("Your User Name Must have 1 Upper Case");
                    }else if (!etUserName.getText().toString().matches(".*[a-z].*")) {
                        etUserName.setError("Your User Name Must have 1 Lower Case");
                    }else if (!etUserName.getText().toString().matches(".*[0-9].*")) {
                        etUserName.setError("Your User Name Must have 1 Degit");
                    }else if (!etUserName.getText().toString().matches(".*[!,@,#,$,&,*].*")) {
                        etUserName.setError("User Name must have 1 Special Symbol");
                    } else if (etPassword.getText().toString().isEmpty()) {
                        etPassword.setError("Please Enter Your New Password");
                    } else if (etPassword.getText().toString().length()<8) {
                        etPassword.setError("Password must have 8 characters");
                    } else if (!etPassword.getText().toString().matches(".*[A-Z].*")) {
                        etPassword.setError("Password must have 1 Upper case");
                    }  else if (!etPassword.getText().toString().matches(".*[a-z].*")) {
                        etPassword.setError("Password must have 1 Lower case");
                    }else if (!etPassword.getText().toString().matches(".*[0-9].*")) {
                        etPassword.setError("Password must have 1 Degit");
                    }else if (!etPassword.getText().toString().matches(".*[!,@,#,$,&,*].*")) {
                        etPassword.setError("Password must have 1 Special Symbol");
                    } else if (etConformPassword.getText().toString().equals(etPassword)) {
                        etConformPassword.setError("Password and Conform Password must be Same");
                    }else {
                        progressDialog=new ProgressDialog(Forget_Password_Activity.this);
                        progressDialog.setTitle("Please Wait");
                        progressDialog.show();
                    }
                }
            });
            cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        etConformPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else {
                        etConformPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });
            cbHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else {
                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });
        }
    }
