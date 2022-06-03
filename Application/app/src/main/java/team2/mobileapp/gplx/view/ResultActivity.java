package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.CheckRadioButton;

import android.os.Bundle;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    ArrayList<CheckRadioButton> checkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}