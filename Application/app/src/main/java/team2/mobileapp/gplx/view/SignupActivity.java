package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import pl.droidsonroids.gif.GifImageView;
import team2.mobileapp.gplx.Volley.model.Account;
import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.dto.RegisterResponse;
import team2.mobileapp.gplx.Volley.service.AuthenService;

public class SignupActivity extends AppCompatActivity {

    EditText firstName, lastName, Email, userName, Password, confirmPassword;
    Button btnSignup;
    TextView textLogin;
    GifImageView gifDone;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_signup);
        InitialVariable();

        Intent login = new Intent(this, LoginActivity.class);
        final AuthenService authenService = new AuthenService(this);

        Signup(authenService, login);
        Login(login);
    }

    private void Login(Intent login) {
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear all stack and add activity new task
                login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(login);
            }
        });
    }

    private void InitialVariable() {
        firstName = findViewById(R.id.et_first_name_signup);
        lastName = findViewById(R.id.et_last_name_signup);
        Email = findViewById(R.id.et_email_signup);
        userName = findViewById(R.id.et_username_signup);
        Password = findViewById(R.id.et_password_signup);
        confirmPassword = findViewById(R.id.et_password_confirm_signup);

        btnSignup = findViewById(R.id.btn_signup);
        textLogin = findViewById(R.id.tv_login);
        gifDone = findViewById(R.id.gif_done);
    }

    private void Signup(AuthenService authenService, Intent login) {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (firstName.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this, "Please enter your first name", Toast.LENGTH_LONG).show();
                else if (lastName.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this, "Please enter your last name", Toast.LENGTH_LONG).show();
                else if (Email.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
                else if (userName.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this, "Please enter your username", Toast.LENGTH_LONG).show();
                else if (Password.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this, "Please enter your password", Toast.LENGTH_LONG).show();
                else if (confirmPassword.getText().toString().isEmpty())
                    Toast.makeText(SignupActivity.this, "Please enter your confirm password", Toast.LENGTH_LONG).show();
                else if (!Password.getText().toString().equals(confirmPassword.getText().toString()))
                    Toast.makeText(SignupActivity.this, "Please check your confirm password again!", Toast.LENGTH_LONG).show();
                else {
                    Account account = new Account();
                    account.setFirstName(firstName.getText().toString());
                    account.setLastName(lastName.getText().toString());
                    account.setEmail(Email.getText().toString());
                    account.setUsername(userName.getText().toString());
                    account.setPassword(Password.getText().toString());
                    authenService.Register(account, new AuthenService.SignupCallBack() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(SignupActivity.this, "Something wrong!!!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(RegisterResponse registerResponse) {
                            Log.i("Register response", registerResponse.toString());
                            if (registerResponse.getEmail().equals("Email") && registerResponse.getUsername().equals("Username"))
                                Toast.makeText(SignupActivity.this, "This email and username has already been taken", Toast.LENGTH_LONG).show();
                            else if (registerResponse.getEmail().equals("Email"))
                                Toast.makeText(SignupActivity.this, "This email has already been taken", Toast.LENGTH_LONG).show();
                            else if (registerResponse.getUsername().equals("Username"))
                                Toast.makeText(SignupActivity.this, "This username has already been taken", Toast.LENGTH_LONG).show();
                            else {
                                gifDone.setVisibility(View.VISIBLE);
                                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                if (vibrator.hasVibrator()) {
                                    vibrator.vibrate(500); // for 500 ms
                                }
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(login);
                                    }
                                }, 3000);
                            }
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