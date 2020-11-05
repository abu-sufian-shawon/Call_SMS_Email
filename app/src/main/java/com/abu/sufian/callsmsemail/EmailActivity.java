package com.abu.sufian.callsmsemail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailActivity extends AppCompatActivity {
    private EditText edtMailTO, edtSubject, edtBodyText;
    private Button btnSendEMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        edtMailTO = findViewById(R.id.edtMailTO);
        edtSubject = findViewById(R.id.edtSubject);
        edtBodyText = findViewById(R.id.edtBodyText);
        btnSendEMail = findViewById(R.id.btnSendEMail);

        btnSendEMail.setOnClickListener(v -> {
            String to = edtMailTO.getText().toString().trim();
            String subject = edtSubject.getText().toString().trim();
            String message = edtBodyText.getText().toString().trim();

            if (to.isEmpty()) {
                edtMailTO.requestFocus();
                edtMailTO.setError("Empty");
            } else if (subject.isEmpty()) {
                edtSubject.requestFocus();
                edtSubject.setError("Empty");
            } else if (message.isEmpty()) {
                edtBodyText.requestFocus();
                edtBodyText.setError("Empty");
            } else {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "There is no application to send email", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        edtMailTO.requestFocus();
        setTitle("Compose Email");
    }
}