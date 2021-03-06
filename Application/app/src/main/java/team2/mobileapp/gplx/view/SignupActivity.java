package team2.mobileapp.gplx.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView;
import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;
import team2.mobileapp.gplx.Volley.model.Account;
import team2.mobileapp.gplx.Volley.model.dto.RegisterResponse;
import team2.mobileapp.gplx.Volley.service.AuthenService;

public class SignupActivity extends AppCompatActivity {

    EditText  fullName, Email, userName, Password, confirmPassword;
    Button btnSignup;
    TextView textLogin;
    GifImageView gifDone;
    RelativeLayout checkOutFocusSignup;
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
        checkOutFocusSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });
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

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    private void InitialVariable() {
        fullName = findViewById(R.id.et_fullname);
        Email = findViewById(R.id.et_email_signup);
        userName = findViewById(R.id.et_username_signup);
        Password = findViewById(R.id.et_password_signup);
        confirmPassword = findViewById(R.id.et_password_confirm_signup);

        btnSignup = findViewById(R.id.btn_signup);
        textLogin = findViewById(R.id.tv_login);
        gifDone = findViewById(R.id.gif_done);
        checkOutFocusSignup = findViewById(R.id.check_out_focus_signup);

    }

    private void Signup(AuthenService authenService, Intent login) {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboard();
                if (fullName.getText().toString().isEmpty()) {
                    VariableGlobal.showToast(SignupActivity.this, "H??y nh???p T??n");
                } else if (Email.getText().toString().isEmpty()) {
                    VariableGlobal.showToast(SignupActivity.this, "H??y nh???p Email");
                } else {
                    if (!VariableGlobal.validateEmail(Email.getText().toString())) {
                        VariableGlobal.showToast(SignupActivity.this, "Email ?????nh d???ng sai");
                    } else if (userName.getText().toString().isEmpty()) {
                        VariableGlobal.showToast(SignupActivity.this, "H??y nh???p t??n ????ng nh???p");
                    } else {
                        if (Password.getText().toString().isEmpty()) {
                            VariableGlobal.showToast(SignupActivity.this, "H??y nh???p m???t kh???u");
                        } else {

                            if (Password.getText().toString().length() < 8) {
                                VariableGlobal.showToast(SignupActivity.this, "M???t kh???u ph???i d??i ??t nh???t 8 k?? t???");
                            } else if (confirmPassword.getText().toString().isEmpty()) {
                                VariableGlobal.showToast(SignupActivity.this, "H??y nh???p x??c nh???n m???t kh???u");
                            } else if (!Password.getText().toString().equals(confirmPassword.getText().toString())) {
                                VariableGlobal.showToast(SignupActivity.this, "H??y x??c nh???n l???i m???t kh???u");
                            } else {
                                Account account = new Account();
                                account.setFullName(fullName.getText().toString());
                                account.setEmail(Email.getText().toString());
                                account.setUsername(userName.getText().toString());
                                account.setPassword(Password.getText().toString());
                                authenService.Register(account, new AuthenService.SignupCallBack() {
                                    @Override
                                    public void onError(String message) {
                                        Toast.makeText(SignupActivity.this, "C?? l???i x???y ra!", Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onResponse(RegisterResponse registerResponse) {

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

                    }


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