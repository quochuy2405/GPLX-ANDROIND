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

    EditText etVerifyCode;
    Button btnVerifyCode;
    SMSAPI sms = new SMSAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
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
@Override
public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
}

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        return super.moveTaskToBack(nonRoot);

    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}