package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

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

import pl.droidsonroids.gif.GifImageView;
import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.ChangePassCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.AccountController;
import team2.mobileapp.gplx.Retrofit.dto.ChangePassword;
import team2.mobileapp.gplx.Retrofit.models.Account;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;

public class SetNewPasswordActivity extends AppCompatActivity implements ChangePassCallBackListener {

    AccountController accountController;
    EditText et_new_password, et_confirm_password;
    Button btn_accept;
    String email;
    GifImageView gifDone;
    RelativeLayout relativeLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_set_new_password);

        accountController = new AccountController(SetNewPasswordActivity.this);
        email = getIntent().getStringExtra("Email");

        InitialVariable();
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
                if(et_new_password.getText().toString().isEmpty()){
                    VariableGlobal.showToast(SetNewPasswordActivity.this, "Nh???p m???t kh???u m???i");
                }
                else
                {  if(et_new_password.getText().toString().length()<8)
                {
                    VariableGlobal.showToast(SetNewPasswordActivity.this, "M???t kh???u ph???i t???i thi???u 8 k?? t???");
                }
                    else

                    if(et_confirm_password.getText().toString().isEmpty()){
                        VariableGlobal.showToast(SetNewPasswordActivity.this, "X??c nh???n m???t kh???u");
                    }
                    else if(!et_new_password.getText().toString().equals(et_confirm_password.getText().toString())){
                        VariableGlobal.showToast(SetNewPasswordActivity.this, "M???t kh???u x??c nh???n kh??ng kh???p");
                    }
                    else {
                        ChangePassword changePassword = new ChangePassword(et_new_password.getText().toString());
                        accountController.ChangePassword(email,changePassword);
                    }
                }

            }
        });
    }
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if(view  !=null){
            InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public void InitialVariable() {
        et_new_password = findViewById(R.id.set_new_pass);
        et_confirm_password = findViewById(R.id.confirm_set_new_pass);
        btn_accept = findViewById(R.id.btn_send_new_pass);
        gifDone= findViewById(R.id.gif_done_setpass);
        relativeLayout=findViewById(R.id.layout_setnewpass);
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

    @Override
    public void onFetchChangePassProgress(Account account) {
        if(account!=null)
        {    gifDone.setVisibility(View.VISIBLE);
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(500); // for 500 ms
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent login = new Intent(SetNewPasswordActivity.this,LoginActivity.class);
                    login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(login);
                }
            }, 3000);

        }
        else{
            VariableGlobal.showToast(SetNewPasswordActivity.this, "Th???t b???i");
        }

    }

    @Override
    public void onFetchComplete(String message) {

    }
}