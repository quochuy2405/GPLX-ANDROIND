package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.nio.file.Files;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.models.License;


public class A1_TestActivity extends AppCompatActivity {
    TextView tv_license;
    RelativeLayout btn_random, btn_set, btn_board, btn_review_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1_test);
        InitialVariable();

        License license = (License) getIntent().getSerializableExtra("License");
        tv_license.setText("Hạng " + license.getName());

        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A1_TestActivity.this, GroupTestActivity.class);
                intent.putExtra("License", license);
                startActivity(intent);
            }
        });

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A1_TestActivity.this, GroupTestActivity.class);
                intent.putExtra("License", license);
                startActivity(intent);
            }
        });

        btn_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A1_TestActivity.this, NoticeBoardActivity.class);
                startActivity(intent);
            }
        });

        btn_review_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(A1_TestActivity.this, ReviewTestActivity.class);
                intent.putExtra("License", license);
                startActivity(intent);
            }
        });

    }

    private void InitialVariable() {
        tv_license = findViewById(R.id.text_choice_category);
        btn_random = findViewById(R.id.btn_radom);
        btn_set = findViewById(R.id.btn_set);
        btn_board = findViewById(R.id.btn_board);
        btn_review_question = findViewById(R.id.btn_review_question);
    }

}