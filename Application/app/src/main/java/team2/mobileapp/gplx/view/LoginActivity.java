package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.dto.LoginResponse;
import team2.mobileapp.gplx.Volley.service.AuthenService;

public class LoginActivity extends AppCompatActivity {
    EditText et_username, et_password;
    Button btn_login;
    TextView tv_forgot_pass, tv_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_username = findViewById(R.id.et_username_login);
        et_password = findViewById(R.id.et_password_login);
        btn_login = findViewById(R.id.btn_login);
        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);
        tv_signup = findViewById(R.id.tv_signup);

        Intent tutorial = new Intent(this, TutorialActivity.class);
        Intent forgotPass = new Intent(this, ForgotPasswordActivity.class);
        Intent signup = new Intent(this, SignupActivity.class);

        final AuthenService authenService = new AuthenService(this);

        Login(authenService, tutorial);

        ForgotPass(forgotPass);

        Signup(signup);

    }
    private void Signup(Intent signup) {
        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear all stack and add activity new task
                signup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(signup);
            }
        });
    }

    private void ForgotPass(Intent forgotPass) {
        tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(forgotPass);
            }
        });
    }

    private void ResetInput() {
        et_username.setText("");
        et_password.setText("");
    }

    private void Login(AuthenService authenService, Intent tutorial) {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Username",et_username.getText().toString());
                Log.d("Pass",et_password.getText().toString());
                if (et_username.getText().toString().isEmpty() && et_password.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Please enter username and password", Toast.LENGTH_LONG).show();
                else if (et_username.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_LONG).show();
                else if (et_password.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_LONG).show();
                else {
                    System.out.println(et_username.getText().toString() + " " + et_password.getText().toString());
                    authenService.Login(et_username.getText().toString(), et_password.getText().toString(), new AuthenService.LoginCallBack() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(LoginResponse loginResponse) {
                            //Toast.makeText(SignInActivity.this, loginResponse.toString(), Toast.LENGTH_LONG).show();
                            Log.i("Login response",loginResponse.toString());

                            //clear all stack and add activity new task
                            tutorial.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(tutorial);
                        }
                    });
                }
            }
        });
    }
}