package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.ForgotPassCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.AccountController;
import team2.mobileapp.gplx.Retrofit.dto.VerificationCode;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPassCallBackListener {
    AccountController accountController;
    Button btnSendEmail;
    EditText etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_forgot_password);

        InitialVariable();

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEmail.getText().toString().isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                }
                else {
                    accountController = new AccountController(ForgotPasswordActivity.this);
                    accountController.ForgotPassword(etEmail.getText().toString());
                    Intent intent = new Intent(ForgotPasswordActivity.this, VerifyActivity.class);
                    intent.putExtra("Email", etEmail.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

    public void InitialVariable(){
        etEmail = findViewById(R.id.edit_email_forgot_pass);
        btnSendEmail = findViewById(R.id.btn_forgot_pass);
    }

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

    @Override
    public void onFetchForgotPassProgress(VerificationCode code) {
        VariableGlobal.verificationCode = code;
    }

    @Override
    public void onFetchComplete(String message) {

    }
}