package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.api.SMSAPI;

public class VerifyActivity extends AppCompatActivity {

    EditText et_verify_code;
    Button btn_verify_code;
    SMSAPI sms = new SMSAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

//        et_verify_code = findViewById(R.id.et_verify_code);
//        btn_verify = findViewById(R.id.btn_verify);
//
//        Intent setNewPass = new Intent(this, SetNewPasswordActivity.class);
//
//        Verify(setNewPass);
    }

//    private void Verify(Intent setNewPass) {
//        btn_verify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(et_verify_code.getText().toString().isEmpty())
//                    Toast.makeText(VerifyActivity.this, "Please enter verification code", Toast.LENGTH_LONG).show();
//                else{
//                    startActivity(setNewPass);
//                }
//            }
//        });
//    }
}