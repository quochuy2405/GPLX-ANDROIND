package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.QuestionSetCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.QuestionSetController;
import team2.mobileapp.gplx.Retrofit.models.License;
import team2.mobileapp.gplx.Retrofit.models.QuestionSet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;

public class GroupTestActivity extends AppCompatActivity implements QuestionSetCallBackListener {
    QuestionSetController questionSetController;
    ArrayList<QuestionSet> sets;
    License license;
    RelativeLayout btn_set_1, btn_set_2, btn_set_3, btn_set_4, btn_set_5, btn_set_6, btn_set_7, btn_set_8,
            btn_set_9, btn_set_10, btn_set_11, btn_set_12, btn_set_13, btn_set_14, btn_set_15, btn_set_16, btn_set_17,
            btn_set_18, btn_set_19, btn_set_20;
    TextView tv_title_set, tv_set_1, tv_set_2, tv_set_3, tv_set_4, tv_set_5, tv_set_6, tv_set_7, tv_set_8
            , tv_set_9, tv_set_10, tv_set_11, tv_set_12, tv_set_13, tv_set_14, tv_set_15, tv_set_16, tv_set_17
            , tv_set_18, tv_set_19, tv_set_20, tv_total_1, tv_total_2, tv_total_3, tv_total_4, tv_total_5, tv_total_6, tv_total_7, tv_total_8
            , tv_total_9, tv_total_10, tv_total_11, tv_total_12, tv_total_13, tv_total_14, tv_total_15, tv_total_16, tv_total_17
            , tv_total_18, tv_total_19, tv_total_20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_test);

        license = (License) getIntent().getSerializableExtra("License");
        InitialVariable();

        questionSetController = new QuestionSetController(this);
        questionSetController.startFetching(license.getId());

    }

    private void InitialVariable() {
        btn_set_1 = findViewById(R.id.btn_set_1);
        btn_set_2 = findViewById(R.id.btn_set_2);
        btn_set_3 = findViewById(R.id.btn_set_3);
        btn_set_4 = findViewById(R.id.btn_set_4);
        btn_set_5 = findViewById(R.id.btn_set_5);
        btn_set_6 = findViewById(R.id.btn_set_6);
        btn_set_7 = findViewById(R.id.btn_set_7);
        btn_set_8 = findViewById(R.id.btn_set_8);
        btn_set_9 = findViewById(R.id.btn_set_9);
        btn_set_10 = findViewById(R.id.btn_set_10);
        btn_set_11 = findViewById(R.id.btn_set_11);
        btn_set_12 = findViewById(R.id.btn_set_12);
        btn_set_13 = findViewById(R.id.btn_set_13);
        btn_set_14 = findViewById(R.id.btn_set_14);
        btn_set_15 = findViewById(R.id.btn_set_15);
        btn_set_16 = findViewById(R.id.btn_set_16);
        btn_set_17 = findViewById(R.id.btn_set_17);
        btn_set_18 = findViewById(R.id.btn_set_18);
        btn_set_19 = findViewById(R.id.btn_set_19);
        btn_set_20 = findViewById(R.id.btn_set_20);
        tv_title_set = findViewById(R.id.tv_title_set);
        tv_set_1 = findViewById(R.id.tv_set_1);
        tv_set_2 = findViewById(R.id.tv_set_2);
        tv_set_3 = findViewById(R.id.tv_set_3);
        tv_set_4 = findViewById(R.id.tv_set_4);
        tv_set_5 = findViewById(R.id.tv_set_5);
        tv_set_6 = findViewById(R.id.tv_set_6);
        tv_set_7 = findViewById(R.id.tv_set_7);
        tv_set_8 = findViewById(R.id.tv_set_8);
        tv_set_9 = findViewById(R.id.tv_set_9);
        tv_set_10 = findViewById(R.id.tv_set_10);
        tv_set_11 = findViewById(R.id.tv_set_11);
        tv_set_12 = findViewById(R.id.tv_set_12);
        tv_set_13 = findViewById(R.id.tv_set_13);
        tv_set_14 = findViewById(R.id.tv_set_14);
        tv_set_15 = findViewById(R.id.tv_set_15);
        tv_set_16 = findViewById(R.id.tv_set_16);
        tv_set_17 = findViewById(R.id.tv_set_17);
        tv_set_18 = findViewById(R.id.tv_set_18);
        tv_set_19 = findViewById(R.id.tv_set_19);
        tv_set_20 = findViewById(R.id.tv_set_20);
        tv_total_1 = findViewById(R.id.tv_total_1);
        tv_total_2 = findViewById(R.id.tv_total_2);
        tv_total_3 = findViewById(R.id.tv_total_3);
        tv_total_4 = findViewById(R.id.tv_total_4);
        tv_total_5 = findViewById(R.id.tv_total_5);
        tv_total_6 = findViewById(R.id.tv_total_6);
        tv_total_7 = findViewById(R.id.tv_total_7);
        tv_total_8 = findViewById(R.id.tv_total_8);
        tv_total_9 = findViewById(R.id.tv_total_9);
        tv_total_10 = findViewById(R.id.tv_total_10);
        tv_total_11 = findViewById(R.id.tv_total_11);
        tv_total_12 = findViewById(R.id.tv_total_12);
        tv_total_13 = findViewById(R.id.tv_total_13);
        tv_total_14 = findViewById(R.id.tv_total_14);
        tv_total_15 = findViewById(R.id.tv_total_15);
        tv_total_16 = findViewById(R.id.tv_total_16);
        tv_total_17 = findViewById(R.id.tv_total_17);
        tv_total_18 = findViewById(R.id.tv_total_18);
        tv_total_19 = findViewById(R.id.tv_total_19);
        tv_total_20 = findViewById(R.id.tv_total_20);

    }

    @Override
    public void onFetchProgress(ArrayList<QuestionSet> questionSets) {
        sets = questionSets;
        Log.d("Sets", sets.toString());
        Log.d("SetSize", String.valueOf(sets.size()));
        ShowSet(sets);

    }

    private void SetOnClickType(RelativeLayout layout, String questionSetId) {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupTestActivity.this, TestActivity.class);
                Log.i("QuestionSetId", questionSetId);
                intent.putExtra("QuestionSetId", questionSetId);
                startActivity(intent);
            }
        });
    }

    private void ShowSet(ArrayList<QuestionSet> sets) {
        HideSet();
        int setSize = sets.size();
        switch (setSize){
            case 8:
                btn_set_1.setVisibility(View.VISIBLE);
                tv_set_1.setText(sets.get(0).getName());
                btn_set_2.setVisibility(View.VISIBLE);
                tv_set_2.setText(sets.get(1).getName());
                btn_set_3.setVisibility(View.VISIBLE);
                tv_set_3.setText(sets.get(2).getName());
                btn_set_4.setVisibility(View.VISIBLE);
                tv_set_4.setText(sets.get(3).getName());
                btn_set_5.setVisibility(View.VISIBLE);
                tv_set_5.setText(sets.get(4).getName());
                btn_set_6.setVisibility(View.VISIBLE);
                tv_set_6.setText(sets.get(5).getName());
                btn_set_7.setVisibility(View.VISIBLE);
                tv_set_7.setText(sets.get(6).getName());
                btn_set_8.setVisibility(View.VISIBLE);
                tv_set_8.setText(sets.get(7).getName());
                tv_total_1.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_2.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_3.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_4.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_5.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_6.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_7.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_8.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                SetOnClickType(btn_set_1, sets.get(0).getId());
                SetOnClickType(btn_set_2, sets.get(1).getId());
                SetOnClickType(btn_set_3, sets.get(2).getId());
                SetOnClickType(btn_set_4, sets.get(3).getId());
                SetOnClickType(btn_set_5, sets.get(4).getId());
                SetOnClickType(btn_set_6, sets.get(5).getId());
                SetOnClickType(btn_set_7, sets.get(6).getId());
                SetOnClickType(btn_set_8, sets.get(7).getId());
                break;
            case 17:
                btn_set_1.setVisibility(View.VISIBLE);
                tv_set_1.setText(sets.get(0).getName());
                btn_set_2.setVisibility(View.VISIBLE);
                tv_set_2.setText(sets.get(1).getName());
                btn_set_3.setVisibility(View.VISIBLE);
                tv_set_3.setText(sets.get(2).getName());
                btn_set_4.setVisibility(View.VISIBLE);
                tv_set_4.setText(sets.get(3).getName());
                btn_set_5.setVisibility(View.VISIBLE);
                tv_set_5.setText(sets.get(4).getName());
                btn_set_6.setVisibility(View.VISIBLE);
                tv_set_6.setText(sets.get(5).getName());
                btn_set_7.setVisibility(View.VISIBLE);
                tv_set_7.setText(sets.get(6).getName());
                btn_set_8.setVisibility(View.VISIBLE);
                tv_set_8.setText(sets.get(7).getName());
                btn_set_9.setVisibility(View.VISIBLE);
                tv_set_9.setText(sets.get(8).getName());
                btn_set_10.setVisibility(View.VISIBLE);
                tv_set_10.setText(sets.get(9).getName());
                btn_set_11.setVisibility(View.VISIBLE);
                tv_set_11.setText(sets.get(10).getName());
                btn_set_12.setVisibility(View.VISIBLE);
                tv_set_12.setText(sets.get(11).getName());
                btn_set_13.setVisibility(View.VISIBLE);
                tv_set_13.setText(sets.get(12).getName());
                btn_set_14.setVisibility(View.VISIBLE);
                tv_set_14.setText(sets.get(13).getName());
                btn_set_15.setVisibility(View.VISIBLE);
                tv_set_15.setText(sets.get(14).getName());
                btn_set_16.setVisibility(View.VISIBLE);
                tv_set_16.setText(sets.get(15).getName());
                btn_set_17.setVisibility(View.VISIBLE);
                tv_set_17.setText(sets.get(16).getName());
                tv_total_1.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_2.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_3.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_4.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_5.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_6.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_7.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_8.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_9.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_10.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_11.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_12.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_13.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_14.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_15.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_16.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_17.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                SetOnClickType(btn_set_1, sets.get(0).getId());
                SetOnClickType(btn_set_2, sets.get(1).getId());
                SetOnClickType(btn_set_3, sets.get(2).getId());
                SetOnClickType(btn_set_4, sets.get(3).getId());
                SetOnClickType(btn_set_5, sets.get(4).getId());
                SetOnClickType(btn_set_6, sets.get(5).getId());
                SetOnClickType(btn_set_7, sets.get(6).getId());
                SetOnClickType(btn_set_8, sets.get(7).getId());
                SetOnClickType(btn_set_9, sets.get(8).getId());
                SetOnClickType(btn_set_10, sets.get(9).getId());
                SetOnClickType(btn_set_11, sets.get(10).getId());
                SetOnClickType(btn_set_12, sets.get(11).getId());
                SetOnClickType(btn_set_13, sets.get(12).getId());
                SetOnClickType(btn_set_14, sets.get(13).getId());
                SetOnClickType(btn_set_15, sets.get(14).getId());
                SetOnClickType(btn_set_16, sets.get(15).getId());
                SetOnClickType(btn_set_17, sets.get(16).getId());
                break;
            case 18:
                btn_set_1.setVisibility(View.VISIBLE);
                tv_set_1.setText(sets.get(0).getName());
                btn_set_2.setVisibility(View.VISIBLE);
                tv_set_2.setText(sets.get(1).getName());
                btn_set_3.setVisibility(View.VISIBLE);
                tv_set_3.setText(sets.get(2).getName());
                btn_set_4.setVisibility(View.VISIBLE);
                tv_set_4.setText(sets.get(3).getName());
                btn_set_5.setVisibility(View.VISIBLE);
                tv_set_5.setText(sets.get(4).getName());
                btn_set_6.setVisibility(View.VISIBLE);
                tv_set_6.setText(sets.get(5).getName());
                btn_set_7.setVisibility(View.VISIBLE);
                tv_set_7.setText(sets.get(6).getName());
                btn_set_8.setVisibility(View.VISIBLE);
                tv_set_8.setText(sets.get(7).getName());
                btn_set_9.setVisibility(View.VISIBLE);
                tv_set_9.setText(sets.get(8).getName());
                btn_set_10.setVisibility(View.VISIBLE);
                tv_set_10.setText(sets.get(9).getName());
                btn_set_11.setVisibility(View.VISIBLE);
                tv_set_11.setText(sets.get(10).getName());
                btn_set_12.setVisibility(View.VISIBLE);
                tv_set_12.setText(sets.get(11).getName());
                btn_set_13.setVisibility(View.VISIBLE);
                tv_set_13.setText(sets.get(12).getName());
                btn_set_14.setVisibility(View.VISIBLE);
                tv_set_14.setText(sets.get(13).getName());
                btn_set_15.setVisibility(View.VISIBLE);
                tv_set_15.setText(sets.get(14).getName());
                btn_set_16.setVisibility(View.VISIBLE);
                tv_set_16.setText(sets.get(15).getName());
                btn_set_17.setVisibility(View.VISIBLE);
                tv_set_17.setText(sets.get(16).getName());
                btn_set_18.setVisibility(View.VISIBLE);
                tv_set_18.setText(sets.get(17).getName());
                tv_total_1.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_2.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_3.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_4.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_5.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_6.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_7.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_8.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_9.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_10.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_11.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_12.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_13.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_14.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_15.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_16.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_17.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_18.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                SetOnClickType(btn_set_1, sets.get(0).getId());
                SetOnClickType(btn_set_2, sets.get(1).getId());
                SetOnClickType(btn_set_3, sets.get(2).getId());
                SetOnClickType(btn_set_4, sets.get(3).getId());
                SetOnClickType(btn_set_5, sets.get(4).getId());
                SetOnClickType(btn_set_6, sets.get(5).getId());
                SetOnClickType(btn_set_7, sets.get(6).getId());
                SetOnClickType(btn_set_8, sets.get(7).getId());
                SetOnClickType(btn_set_9, sets.get(8).getId());
                SetOnClickType(btn_set_10, sets.get(9).getId());
                SetOnClickType(btn_set_11, sets.get(10).getId());
                SetOnClickType(btn_set_12, sets.get(11).getId());
                SetOnClickType(btn_set_13, sets.get(12).getId());
                SetOnClickType(btn_set_14, sets.get(13).getId());
                SetOnClickType(btn_set_15, sets.get(14).getId());
                SetOnClickType(btn_set_16, sets.get(15).getId());
                SetOnClickType(btn_set_17, sets.get(16).getId());
                SetOnClickType(btn_set_18, sets.get(17).getId());
                break;
            case 20:
                btn_set_1.setVisibility(View.VISIBLE);
                tv_set_1.setText(sets.get(0).getQuantity());
                btn_set_2.setVisibility(View.VISIBLE);
                tv_set_2.setText(sets.get(1).getQuantity());
                btn_set_3.setVisibility(View.VISIBLE);
                tv_set_3.setText(sets.get(2).getQuantity());
                btn_set_4.setVisibility(View.VISIBLE);
                tv_set_4.setText(sets.get(3).getQuantity());
                btn_set_5.setVisibility(View.VISIBLE);
                tv_set_5.setText(sets.get(4).getQuantity());
                btn_set_6.setVisibility(View.VISIBLE);
                tv_set_6.setText(sets.get(5).getQuantity());
                btn_set_7.setVisibility(View.VISIBLE);
                tv_set_7.setText(sets.get(6).getQuantity());
                btn_set_8.setVisibility(View.VISIBLE);
                tv_set_8.setText(sets.get(7).getQuantity());
                btn_set_9.setVisibility(View.VISIBLE);
                tv_set_9.setText(sets.get(8).getQuantity());
                btn_set_10.setVisibility(View.VISIBLE);
                tv_set_10.setText(sets.get(9).getQuantity());
                btn_set_11.setVisibility(View.VISIBLE);
                tv_set_11.setText(sets.get(10).getQuantity());
                btn_set_12.setVisibility(View.VISIBLE);
                tv_set_12.setText(sets.get(11).getQuantity());
                btn_set_13.setVisibility(View.VISIBLE);
                tv_set_13.setText(sets.get(12).getQuantity());
                btn_set_14.setVisibility(View.VISIBLE);
                tv_set_14.setText(sets.get(13).getQuantity());
                btn_set_15.setVisibility(View.VISIBLE);
                tv_set_15.setText(sets.get(14).getQuantity());
                btn_set_16.setVisibility(View.VISIBLE);
                tv_set_16.setText(sets.get(15).getQuantity());
                btn_set_17.setVisibility(View.VISIBLE);
                tv_set_17.setText(sets.get(16).getQuantity());
                btn_set_18.setVisibility(View.VISIBLE);
                tv_set_18.setText(sets.get(17).getQuantity());
                btn_set_19.setVisibility(View.VISIBLE);
                tv_set_19.setText(sets.get(18).getQuantity());
                btn_set_20.setVisibility(View.VISIBLE);
                tv_set_20.setText(sets.get(19).getQuantity());
                tv_total_1.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_2.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_3.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_4.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_5.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_6.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_7.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_8.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_9.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_10.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_11.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_12.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_13.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_14.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_15.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_16.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_17.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_18.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_19.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                tv_total_20.setText("Gồm " + sets.get(0).getQuantity() + " câu");
                SetOnClickType(btn_set_1, sets.get(0).getId());
                SetOnClickType(btn_set_2, sets.get(1).getId());
                SetOnClickType(btn_set_3, sets.get(2).getId());
                SetOnClickType(btn_set_4, sets.get(3).getId());
                SetOnClickType(btn_set_5, sets.get(4).getId());
                SetOnClickType(btn_set_6, sets.get(5).getId());
                SetOnClickType(btn_set_7, sets.get(6).getId());
                SetOnClickType(btn_set_8, sets.get(7).getId());
                SetOnClickType(btn_set_9, sets.get(8).getId());
                SetOnClickType(btn_set_10, sets.get(9).getId());
                SetOnClickType(btn_set_11, sets.get(10).getId());
                SetOnClickType(btn_set_12, sets.get(11).getId());
                SetOnClickType(btn_set_13, sets.get(12).getId());
                SetOnClickType(btn_set_14, sets.get(13).getId());
                SetOnClickType(btn_set_15, sets.get(14).getId());
                SetOnClickType(btn_set_16, sets.get(15).getId());
                SetOnClickType(btn_set_17, sets.get(16).getId());
                SetOnClickType(btn_set_18, sets.get(17).getId());
                SetOnClickType(btn_set_19, sets.get(18).getId());
                SetOnClickType(btn_set_20, sets.get(19).getId());
                break;
        }

    }

    private void HideSet() {
        btn_set_1.setVisibility(View.GONE);
        btn_set_2.setVisibility(View.GONE);
        btn_set_3.setVisibility(View.GONE);
        btn_set_4.setVisibility(View.GONE);
        btn_set_5.setVisibility(View.GONE);
        btn_set_6.setVisibility(View.GONE);
        btn_set_7.setVisibility(View.GONE);
        btn_set_8.setVisibility(View.GONE);
        btn_set_9.setVisibility(View.GONE);
        btn_set_10.setVisibility(View.GONE);
        btn_set_11.setVisibility(View.GONE);
        btn_set_12.setVisibility(View.GONE);
        btn_set_13.setVisibility(View.GONE);
        btn_set_14.setVisibility(View.GONE);
        btn_set_15.setVisibility(View.GONE);
        btn_set_16.setVisibility(View.GONE);
        btn_set_17.setVisibility(View.GONE);
        btn_set_18.setVisibility(View.GONE);
        btn_set_19.setVisibility(View.GONE);
        btn_set_20.setVisibility(View.GONE);
    }

    @Override
    public void onFetchComplete(String message) {

    }
}