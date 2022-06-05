package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.CheckRadioButton;

import android.os.Bundle;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    private ArrayList<CheckRadioButton> checkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_result);

//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//            if(extras == null) {
//                checkList = null;
//            } else {
//                checkList = extras.getParcelable("History");
//            }
//        } else {
//            checkList = (ArrayList<CheckRadioButton>) getIntent().getSerializableExtra("History");
//        }
        checkList = (ArrayList<CheckRadioButton>) getIntent().getSerializableExtra("History");

        checkList.toString();

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