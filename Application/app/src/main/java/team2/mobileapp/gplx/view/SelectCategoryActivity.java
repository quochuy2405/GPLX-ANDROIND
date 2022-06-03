package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.LicenseCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.LicenseController;
import team2.mobileapp.gplx.Retrofit.models.License;

import android.os.Bundle;

import java.util.ArrayList;

public class SelectCategoryActivity extends AppCompatActivity implements LicenseCallBackListener {

//    BottomNavigationItemView btn_home, btn_menu, btn_noti, btn_profile;
    LicenseController licenseController;
    RelativeLayout btn_a1, btn_a2, btn_b1, btn_b2;
    ArrayList<License> licenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        InitialVariable();

        licenseController = new LicenseController(this);
        licenseController.startFetching();



    }

    private void InitialVariable() {
        btn_a1 = findViewById(R.id.btn_a1);
        btn_a2 = findViewById(R.id.btn_a2);
        btn_b1 = findViewById(R.id.btn_b1);
        btn_b2 = findViewById(R.id.btn_b2);
    }

    @Override
    public void onFetchProgress(ArrayList<License> licenses) {
        if(!licenses.isEmpty()){
            this.licenses = licenses;
            btn_a1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectCategoryActivity.this, A1_TestActivity.class);
                    intent.putExtra("License", licenses.get(0));
                    startActivity(intent);
                }
            });

            btn_a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectCategoryActivity.this, A1_TestActivity.class);
                    intent.putExtra("License", licenses.get(1));
                    startActivity(intent);
                }
            });

            btn_b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectCategoryActivity.this, A1_TestActivity.class);
                    intent.putExtra("License", licenses.get(2));
                    startActivity(intent);
                }
            });

            btn_b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectCategoryActivity.this, A1_TestActivity.class);
                    intent.putExtra("License", licenses.get(3));
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onFetchComplete(String message) {
        Log.d("onFetchComplete", message);
    }
}