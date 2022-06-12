package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.LicenseByIdCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.LicenseController;
import team2.mobileapp.gplx.Retrofit.models.License;
import team2.mobileapp.gplx.Volley.model.Answer;
import team2.mobileapp.gplx.Volley.model.CheckRadioButton;
import team2.mobileapp.gplx.Volley.model.Question;
import team2.mobileapp.gplx.Volley.model.dto.DtoQuestionSet;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity implements LicenseByIdCallBackListener {
    LicenseController licenseController;
    ArrayList<CheckRadioButton> checkList = new ArrayList<>();
    DtoQuestionSet dto;
    License license;
    int rightAns, wrongAns = 0;
    ImageView iv_result;
    TextView tv_result_show, tv_true_percent, tv_false_percent;
    RelativeLayout result_layout, correct, incorrect;
    Button btn_view_again, btn_result_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_result);

        InitialVariable();
//        VariableGlobal.SetNavigationBar(ResultActivity.this);

        checkList = (ArrayList<CheckRadioButton>) getIntent().getSerializableExtra("History");
        dto = (DtoQuestionSet) getIntent().getSerializableExtra("Dto");
        UpdateScore(dto, checkList);

        String licenseId = dto.getQuestionSet().getLicenseId();
        licenseController = new LicenseController(this);
        licenseController.startFetchingLicenseById(licenseId);

        btn_view_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ResultActivity.this, )
                finish();
            }
        });
        btn_result_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, A1_TestActivity.class);
                intent.putExtra("License", license);
                startActivity(intent);
            }
        });
    }

    private void ShowLayout() {
        int total = rightAns + wrongAns;
        final float scale = getResources().getDisplayMetrics().density;
        tv_true_percent.setText((rightAns*100/total) + "%");
        tv_false_percent.setText((wrongAns*100/total) + "%");
        if(license != null){
            if(license.getName().equals("A1")){
                int minPx = 24;
                int rightPx = (int) (minPx * rightAns)/(2)+9*rightAns;
                int wrongPx = (int) (minPx * wrongAns)/(2)+9*wrongAns;
                correct.getLayoutParams().height = rightPx;
                incorrect.getLayoutParams().height = wrongPx;
                if(rightAns < 21){
                    iv_result.setImageResource(R.drawable.result_failed);
                    result_layout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
                    tv_result_show.setText("Không đạt");
                }
                else{
                    iv_result.setImageResource(R.drawable.result_pass);
                    result_layout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_main)));
                    tv_result_show.setText("Đạt");
                }
            }
            if(license.getName().equals("A2")){
                int minPx = 24;
                int rightPx = (int) (minPx * rightAns)/(2)+9*rightAns;
                int wrongPx = (int) (minPx * wrongAns)/(2)+9*wrongAns;
                correct.getLayoutParams().height = rightPx;
                incorrect.getLayoutParams().height = wrongPx;
                if(rightAns < 23){
                    iv_result.setImageResource(R.drawable.result_failed);
                    result_layout.setBackgroundColor(Color.RED);
                    tv_result_show.setText("Không đạt");
                }else{
                    iv_result.setImageResource(R.drawable.result_pass);
                    result_layout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_main)));
                    tv_result_show.setText("Đạt");
                }
            }
            if(license.getName().equals("B1")){
                int minPx = 20;
                int rightPx = (int) (minPx * rightAns)/(2)+9*rightAns;
                int wrongPx = (int) (minPx * wrongAns)/(2)+9*wrongAns;
                correct.getLayoutParams().height = rightPx;
                incorrect.getLayoutParams().height = wrongPx;
                if(rightAns < 27){
                    iv_result.setImageResource(R.drawable.result_failed);
                    result_layout.setBackgroundColor(Color.RED);
                    tv_result_show.setText("KHÔNG ĐẠT");
                }
                else{
                    iv_result.setImageResource(R.drawable.result_pass);
                    result_layout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_main)));
                    tv_result_show.setText("ĐẠT");
                }
            }
            if(license.getName().equals("B2")){
                int minPx = 17;
                int rightPx = (int) (minPx * rightAns)/(2)+9*rightAns;
                int wrongPx = (int) (minPx * wrongAns)/(2)+9*wrongAns;
                correct.getLayoutParams().height = rightPx;
                incorrect.getLayoutParams().height = wrongPx;
                if(rightAns < 32){
                    iv_result.setImageResource(R.drawable.result_failed);
                    result_layout.setBackgroundColor(Color.RED);
                    tv_result_show.setText("Không đạt");
                }
                else{
                    iv_result.setImageResource(R.drawable.result_pass);
                    result_layout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.green_main)));
                    tv_result_show.setText("Đạt");
                }
            }
        }
    }

    private void InitialVariable() {
        result_layout = findViewById(R.id.layout_result);
        iv_result = findViewById(R.id.iv_result);
        tv_result_show = findViewById(R.id.tv_result_show);
        tv_true_percent = findViewById(R.id.tv_true_percent);
        tv_false_percent = findViewById(R.id.tv_false_percent);
        correct = findViewById(R.id.value_correct);
        incorrect = findViewById(R.id.value_incorrect);
        btn_view_again = findViewById(R.id.btn_view_again);
        btn_result_next = findViewById(R.id.btn_result_next);
    }

    private void UpdateScore(DtoQuestionSet dto, ArrayList<CheckRadioButton> checkList) {
        List<Question> questions = dto.getQuestList();
        List<Answer> answers = dto.getAnsList();
        for(int i = 0; i < dto.getQuestList().size(); i++){
            boolean flag = false;
            for(CheckRadioButton item : checkList){
                if(item.getQuestionId().equals(questions.get(i).getId()) && item.getAnswerId().equals(answers.get(i).getId())){
                    if(item.getAnswerIndex() == answers.get(i).getResult()){
                        flag = true;
                        rightAns++;
                    }
                }
            }
            if(!flag) wrongAns++;
        }
    }

    @Override
    public void onFetchProgress(License license) {
        this.license = license;
        ShowLayout();
    }

    @Override
    public void onFetchComplete(String message) {

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