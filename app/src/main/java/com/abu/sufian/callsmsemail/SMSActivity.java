package com.abu.sufian.callsmsemail;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {
    private EditText edtTo, edtMessage;
    private Button btnSendSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_m_s);

        edtTo = findViewById(R.id.edtTo);
        edtMessage = findViewById(R.id.edtMessage);
        btnSendSMS = findViewById(R.id.btnSendSMS);

        btnSendSMS.setOnClickListener(v -> {
            String to = edtTo.getText().toString().trim();
            String message = edtMessage.getText().toString().trim();

            if (to.isEmpty()) {
                edtTo.requestFocus();
                edtTo.setError("Empty");
            } else if (message.isEmpty()) {
                edtMessage.requestFocus();
                edtMessage.setError("Empty");
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        sent(to, message);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        setTitle("Send SMS");
        edtTo.requestFocus();
    }

    private void sent(String to, String message) {
        SmsManager manager = SmsManager.getDefault();
        try {
            manager.sendTextMessage(to, null, message, null, null);
            Toast.makeText(this, "Message Send", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send Message", Toast.LENGTH_SHORT).show();
        }
    }
}