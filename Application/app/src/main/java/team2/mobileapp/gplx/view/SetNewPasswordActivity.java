package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.ChangePassCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.AccountController;
import team2.mobileapp.gplx.Retrofit.dto.ChangePassword;
import team2.mobileapp.gplx.Volley.model.Account;
import team2.mobileapp.gplx.Volley.service.AuthenService;

public class SetNewPasswordActivity extends AppCompatActivity {

    AccountController accountController;
    EditText et_new_password, et_confirm_password;
    Button btn_accept;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_set_new_password);

        final AuthenService authenService = new AuthenService(this);
        email = getIntent().getStringExtra("Email");

        InitialVariable();

        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_new_password.getText().toString().isEmpty()){
                    Toast.makeText(SetNewPasswordActivity.this, "Please enter new password", Toast.LENGTH_SHORT).show();
                }
                else if(et_confirm_password.getText().toString().isEmpty()){
                    Toast.makeText(SetNewPasswordActivity.this, "Please confirm new password", Toast.LENGTH_SHORT).show();
                }
                else if(!et_new_password.getText().toString().equals(et_confirm_password.getText().toString())){
                    Toast.makeText(SetNewPasswordActivity.this, "New password and confirm password must be the same", Toast.LENGTH_SHORT).show();
                }
                else {
                    ChangePassword changePassword = new ChangePassword(et_new_password.getText().toString());
                    authenService.ChangePassword(email, changePassword, new AuthenService.ChangePasswordCallBack() {
                        @Override
                        public void onError(String message) {

                        }

                        @Override
                        public void onResponse(Account account) {
                            Intent intent = new Intent(SetNewPasswordActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }


    public void InitialVariable() {
        et_new_password = findViewById(R.id.set_new_pass);
        et_confirm_password = findViewById(R.id.confirm_set_new_pass);
        btn_accept = findViewById(R.id.btn_send_new_pass);
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

    }  @Override
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