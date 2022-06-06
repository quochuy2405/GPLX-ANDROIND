package team2.mobileapp.gplx.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import team2.mobileapp.gplx.R;

public class BoardingActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setTimeOut();

    }
   private void setTimeOut(){
       Handler handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(BoardingActivity.this,SplashActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
           }
       }, 3000);
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