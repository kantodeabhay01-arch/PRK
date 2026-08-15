package com.priyanka.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash_activity extends AppCompatActivity {

    ImageView ivimage;

            TextView tvEdu,tvReach,tvSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash_activity);

        ivimage = findViewById(R.id.ivAppLogo);
        tvEdu = findViewById(R.id.tvEdu);
        tvReach = findViewById(R.id.tvReach);
        tvSlogan = findViewById(R.id.tvTagline);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(splash_activity.this,Login_Activity.class);
                startActivity(i);

            }
        },3000);

    }
}