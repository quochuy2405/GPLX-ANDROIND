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
import team2.mobileapp.gplx.Volley.model.Account;
import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.dto.RegisterResponse;
import team2.mobileapp.gplx.Volley.service.AuthenService;

public class SignupActivity extends AppCompatActivity {

    EditText et_first_name, et_last_name, et_email, et_username, et_password, et_confirm_password;
    Button btn_signup;
    TextView tv_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_first_name = findViewById(R.id.et_first_name_signup);
        et_last_name = findViewById(R.id.et_last_name_signup);
        et_email = findViewById(R.id.et_email_signup);
        et_username = findViewById(R.id.et_username_signup);
        et_password = findViewById(R.id.et_password_signup);
        et_confirm_password = findViewById(R.id.et_password_confirm_signup);

        btn_signup = findViewById(R.id.btn_signup);

        tv_login = findViewById(R.id.tv_login);

        Intent login = new Intent(this,LoginActivity.class);

        final AuthenService authenService = new AuthenService(this);

        Signup(authenService, login);

        Login(login);
    }

    private void Login(Intent login) {
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear all stack and add activity new task
                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(login);
            }
        });
    }

    private void Signup(AuthenService authenService, Intent login) {
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_first_name.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this,"Please enter your first name", Toast.LENGTH_LONG).show();
                else if(et_last_name.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this,"Please enter your last name", Toast.LENGTH_LONG).show();
                else if(et_email.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this,"Please enter your email", Toast.LENGTH_LONG).show();
                else if(et_username.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this,"Please enter your username", Toast.LENGTH_LONG).show();
                else if(et_password.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this,"Please enter your password", Toast.LENGTH_LONG).show();
                else if(et_confirm_password.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this,"Please enter your confirm password", Toast.LENGTH_LONG).show();
                else if(!et_password.getText().toString().equals(et_confirm_password.getText().toString()))
                    Toast.makeText(SignupActivity.this,"Please check your confirm password again!", Toast.LENGTH_LONG).show();
                else{
                    Account account = new Account();
                    account.setFirstName(et_first_name.getText().toString());
                    account.setLastName(et_last_name.getText().toString());
                    account.setEmail(et_email.getText().toString());
                    account.setUsername(et_username.getText().toString());
                    account.setPassword(et_password.getText().toString());
                    authenService.Register(account, new AuthenService.SignupCallBack() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(SignupActivity.this, "Something wrong!!!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(RegisterResponse registerResponse) {
                            Log.i("Register response",registerResponse.toString());
                            if(registerResponse.getEmail().equals("Email") && registerResponse.getUsername().equals("Username"))
                                Toast.makeText(SignupActivity.this, "This email and username has already been taken", Toast.LENGTH_LONG).show();
                            else if(registerResponse.getEmail().equals("Email"))
                                Toast.makeText(SignupActivity.this, "This email has already been taken", Toast.LENGTH_LONG).show();
                            else if(registerResponse.getUsername().equals("Username"))
                                Toast.makeText(SignupActivity.this, "This username has already been taken", Toast.LENGTH_LONG).show();
                            else {
                                Toast.makeText(SignupActivity.this, "Congrats!!!", Toast.LENGTH_LONG).show();
                                startActivity(login);
                            }
                        }
                    });
                }
            }
        });
    }
}