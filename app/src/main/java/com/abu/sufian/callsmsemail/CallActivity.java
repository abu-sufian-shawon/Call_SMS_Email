package com.abu.sufian.callsmsemail;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CallActivity extends AppCompatActivity {
    private EditText edtCall;
    private FloatingActionButton ftnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        edtCall = findViewById(R.id.edtCall);
        ftnCall = findViewById(R.id.ftnCall);

        ftnCall.setOnClickListener(v -> {
            String number = edtCall.getText().toString();

            if (number.isEmpty()) {
                edtCall.requestFocus();
                edtCall.setError("Empty");
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + number));
                        startActivity(callIntent);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 2);
                    }
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle("Make Phone Call");
        edtCall.requestFocus();
    }
}