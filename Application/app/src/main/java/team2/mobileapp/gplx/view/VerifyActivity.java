package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.api.SMSAPI;
import team2.mobileapp.gplx.Retrofit.callbacks.ForgotPassCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.AccountController;
import team2.mobileapp.gplx.Retrofit.dto.VerificationCode;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;

public class VerifyActivity extends AppCompatActivity implements ForgotPassCallBackListener {
    AccountController accountController;
    String email;
    EditText etVerifyCode;
    TextView tvResend;
    Button btnVerifyCode;

    RelativeLayout checkOutFocus;
    SMSAPI sms = new SMSAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_verify);
        InitialVariable();
        email = getIntent().getStringExtra("Email");

        checkOutFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });

        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                accountController = new AccountController(VerifyActivity.this);
                accountController.ForgotPassword(email);
            }
        });

        Intent setNewPass = new Intent(this, SetNewPasswordActivity.class);
        Verify(setNewPass);
    }

    private void Verify(Intent setNewPass) {
        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                if(etVerifyCode.getText().toString().isEmpty()){
                VariableGlobal.showToast(VerifyActivity.this, "Hãy nhập mã CODE");
                }
                else{
                    if(etVerifyCode.getText().toString().equals(VariableGlobal.verificationCode.getCode())){
                        setNewPass.putExtra("Email", email);
                        startActivity(setNewPass);
                    }
                    else {
                        VariableGlobal.showToast(VerifyActivity.this, "Mã xác nhận không đúng!!!");
                    }
                }
            }
        });
    }

    public void InitialVariable() {
        checkOutFocus = findViewById(R.id.body_verify);
        etVerifyCode = findViewById(R.id.et_verify_code);
        btnVerifyCode = findViewById(R.id.btn_verify);
        tvResend = findViewById(R.id.no_give_code);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if(view  !=null){
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

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

    @Override
    public void onFetchCheckEmailProgress(int code) {

    }



}