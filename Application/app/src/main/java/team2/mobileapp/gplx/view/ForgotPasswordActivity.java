package team2.mobileapp.gplx.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.api.SMSAPI;

import android.os.Bundle;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    Button btn_send_email;
    EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        et_email = findViewById(R.id.edit_email_forgot_pass);
        btn_send_email = findViewById(R.id.btn_forgot_pass);
        btn_send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(ForgotPasswordActivity.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
       

                }
            }
        });
//
//        btn_send_email = findViewById(R.id.btn_send_email);
//        et_email = findViewById(R.id.et_email);
//        Intent verfify = new Intent(this, VerifyActivity.class);
//
//
//        btn_send_email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(verfify);
//            }
//        });

    }


}