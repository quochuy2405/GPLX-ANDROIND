package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;
import team2.mobileapp.gplx.Volley.model.dto.LoginResponse;
import team2.mobileapp.gplx.Volley.service.AuthenService;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvForgotPass, tvSignup;
    RelativeLayout checkOutFocusLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_login);

        InitialVariable();

        Intent tutorial = new Intent(this, TutorialActivity.class);
        Intent forgotPass = new Intent(this, ForgotPasswordActivity.class);
        Intent signup = new Intent(this, SignupActivity.class);

        final AuthenService authenService = new AuthenService(this);

        Login(authenService, tutorial);

        ForgotPass(forgotPass);

        Signup(signup);
        checkOutFocusLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });
    }

    private void InitialVariable() {
        etUsername = findViewById(R.id.et_username_login);
        etPassword = findViewById(R.id.et_password_login);
        btnLogin = findViewById(R.id.btn_login);
        tvForgotPass = findViewById(R.id.tv_forgot_pass);
        tvSignup = findViewById(R.id.tv_signup);
        checkOutFocusLogin = findViewById(R.id.check_out_focus_login);
    }

    private void Signup(Intent signup) {
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(signup);
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

    private void ForgotPass(Intent forgotPass) {
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(forgotPass);
            }
        });
    }

    private void ResetInput() {
        etUsername.setText("");
        etPassword.setText("");
    }

    private void Login(AuthenService authenService, Intent tutorial) {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                Log.d("Username", etUsername.getText().toString());
                Log.d("Pass", etPassword.getText().toString());
                if (etUsername.getText().toString().isEmpty() && etPassword.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Hãy nhập tên đăng nhập và mật khẩu", Toast.LENGTH_LONG).show();
                else if (etUsername.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Hãy nhập tên đăng nhập", Toast.LENGTH_LONG).show();
                else if (etPassword.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Hãy nhập mật khẩu", Toast.LENGTH_LONG).show();
                else {
                    System.out.println(etUsername.getText().toString() + " " + etPassword.getText().toString());
                    authenService.Login(etUsername.getText().toString(), etPassword.getText().toString(), new AuthenService.LoginCallBack() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(LoginResponse loginResponse) {
                            VariableGlobal.idUser=loginResponse.getId();
                            //clear all stack and add activity new task
                            tutorial.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(tutorial);
                        }
                    });
                }
            }
        });
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
}