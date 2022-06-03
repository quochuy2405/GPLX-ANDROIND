package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team2.mobileapp.gplx.R;
import android.os.Bundle;
public class SplashActivity extends AppCompatActivity {

    Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btn_start = findViewById(R.id.btn_start);

        Intent toLogin = new Intent(SplashActivity.this, LoginActivity.class);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toLogin);
            }
        });
    }
}