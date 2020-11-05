package com.abu.sufian.callsmsemail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnCall).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CallActivity.class));
        });

        findViewById(R.id.btnSMS).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SMSActivity.class));
        });

        findViewById(R.id.btnEmail).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EmailActivity.class));
        });
    }
}