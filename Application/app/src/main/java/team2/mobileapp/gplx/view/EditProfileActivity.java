package team2.mobileapp.gplx.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.AccountCallbackListener;
import team2.mobileapp.gplx.Retrofit.controllers.AccountController;
import team2.mobileapp.gplx.Retrofit.models.Account;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;

public class EditProfileActivity extends AppCompatActivity implements AccountCallbackListener {

    private EditText etFullName;
    private EditText etUsername;
    private EditText etEmail;
    private Button btnSave;
    private RelativeLayout checkOutFocus;
    private Account accountView;
    private AccountController accountController;
    private InputMethodManager inputMethodManager;
    private boolean isUpdated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);

        setContentView(R.layout.activity_edit_profile);
        VariableGlobal.SetNavigationBar(this);
        InitialVariables();

        try {
            accountController.startFetching(VariableGlobal.idUser);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        accountView.setFirstName(etFullName.getText().toString());
                        accountView.setUsername(etUsername.getText().toString());
                        accountController.updateAccount(accountView.getId(), accountView);
                    } catch (Exception e) {
                        Log.d("Error:", e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }

        checkOutFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
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

    private void InitialVariables() {
        accountController = new AccountController(this);
        etFullName = (EditText) findViewById(R.id.et_fullname);
        etUsername = (EditText) findViewById(R.id.et_username);
        etEmail = (EditText) findViewById(R.id.et_email_profile);
        btnSave = (Button) findViewById(R.id.btn_save);
        checkOutFocus= findViewById(R.id.check_out_focus);
    }

    @Override
    public void onFetchAccountProgress(Account account) {
        if (account != null) {
            this.accountView = account;
            etFullName.setText(account.getLastName() + " " + account.getFirstName());
            etUsername.setText(account.getUsername());
            etEmail.setText(account.getEmail());
        }
    }

    @Override
    public void onFetchComplete(String message) {
        Log.d("message",  message);
    }

}