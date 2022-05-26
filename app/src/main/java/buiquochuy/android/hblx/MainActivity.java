package buiquochuy.android.hblx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Splash(View view){
        Intent intent = new Intent(this,Splash.class);
        startActivity(intent);
    }
    public void Boarding(View view){
        Intent intent = new Intent(this,Boarding.class);
        startActivity(intent);
    }
    public void Login(View view){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void SignUp(View view){
        Intent intent = new Intent(this,Signup.class);
        startActivity(intent);
    }
    public void Verify(View view){
        Intent intent = new Intent(this,Verify.class);
        startActivity(intent);
    }
    public void ForgotPass(View view){
        Intent intent = new Intent(this,ForgotPassWord.class);
        startActivity(intent);
    }
    public void SetNewPass(View view){
        Intent intent = new Intent(this,SetNewPassword.class);
        startActivity(intent);
    }
    public void History(View view){
        Intent intent = new Intent(this,History.class);
        startActivity(intent);
    }
    public void SelectCategory(View view){
        Intent intent = new Intent(this,SelectCategory.class);
        startActivity(intent);
    }
    public void Test(View view){
        Intent intent = new Intent(this,Test.class);
        startActivity(intent);
    }
    public void Profile(View view){
        Intent intent = new Intent(this,EditProfile.class);
        startActivity(intent);
    }
    public void A1_Test(View view){
        Intent intent = new Intent(this,A1_Test.class);
        startActivity(intent);
    }
    public void Result(View view){
        Intent intent = new Intent(this,Result.class);
        startActivity(intent);
    }
    public void NoticeBoard(View view){
        Intent intent = new Intent(this,NoticeBoard.class);
        startActivity(intent);
    }


}