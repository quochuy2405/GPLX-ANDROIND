package team2.mobileapp.gplx.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;
import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.ForgotPassCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.AccountController;
import team2.mobileapp.gplx.Retrofit.dto.VerificationCode;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPassCallBackListener {
    AccountController accountController;
    Button btnSendEmail;
    EditText etEmail;
    RelativeLayout relativeLayout;
    GifImageView loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_forgot_password);

        InitialVariable();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });
        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                String Email = etEmail.getText().toString();
                if (Email.isEmpty()) {
                    VariableGlobal.showToast(ForgotPasswordActivity.this, "Hãy nhập Email");
                } else {
                    if (VariableGlobal.validateEmail(Email)) {

                        accountController = new AccountController(ForgotPasswordActivity.this);
                        accountController.CheckEmail(etEmail.getText().toString());

                    } else {
                        VariableGlobal.showToast(ForgotPasswordActivity.this, "Email sai định dạng");
                    }
                }
            }
        });
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public void InitialVariable() {
        etEmail = findViewById(R.id.edit_email_forgot_pass);
        btnSendEmail = findViewById(R.id.btn_forgot_pass);
        relativeLayout = findViewById(R.id.forgot_layouts);
        loading=findViewById(R.id.loading);
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
        loading.setVisibility(View.GONE);
        if(code!=null){
            VariableGlobal.verificationCode = code;
            Intent intent = new Intent(ForgotPasswordActivity.this, VerifyActivity.class);
            intent.putExtra("Email", etEmail.getText().toString());
            startActivity(intent);
        }
        else{
            VariableGlobal.showToast(ForgotPasswordActivity.this, "Gửi mã thất bại!!!");
        }

    }

    @Override
    public void onFetchComplete(String message) {
    }

    @Override
    public void onFetchCheckEmailProgress(int code) {
        if(code==200 ){
            loading.setVisibility(View.VISIBLE);
            accountController = new AccountController(ForgotPasswordActivity.this);
            accountController.ForgotPassword(etEmail.getText().toString());
        }
        else{
            VariableGlobal.showToast(ForgotPasswordActivity.this, "Email chưa được đăng ký");
        }
    }
}