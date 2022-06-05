package team2.mobileapp.gplx.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.CheckRadioButton;
import team2.mobileapp.gplx.Volley.model.dto.DtoQuestionSet;
import team2.mobileapp.gplx.Volley.service.TestService;

public class TestActivity extends AppCompatActivity {

    private TextView tvPositionQuestion, tvTotalQuestion, tvQuestion;
    private ProgressBar determinateBar;
    private ObjectAnimator progressAnimator;
    private RadioButton rdAnswer1, rdAnswer2, rdAnswer3, rdAnswer4, rdAnswer5;
    private Button btnNext, btnPrev;
    private ImageView ivQuestion;
    private RadioGroup rgAnswer;
    boolean mSlideState;
    private DrawerLayout mDrawerLayout;
    private GridLayout layoutQuestionBar;
    private FloatingActionButton myFab;
    private int totalQuestion=0;
    private final int[] historyID = {-1};
    private final int [] historyProgressBarValue = {0};
    private final int[] i = {0};
    private final ArrayList<CheckRadioButton> checkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        setContentView(R.layout.activity_test);

        InitialVariables();

        final TestService testService = new TestService(TestActivity.this);

        // Đang hardcode để test
        String questionSetId = getIntent().getStringExtra("QuestionSetId");
        Log.i("QuestionSetId", questionSetId);

        ShowTest(testService, questionSetId);
        myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // If the navigation drawer is not open then open it, if its already open then close it.
                if(!mDrawerLayout.isDrawerOpen(GravityCompat.END)) mDrawerLayout.openDrawer(GravityCompat.END);
                else mDrawerLayout.closeDrawer(GravityCompat.END);
            }
        });
        historyProgressBarValue[0]=determinateBar.getProgress();



    }
    private void ProgressAnimation(int end){

        ValueAnimator animator = ValueAnimator.ofInt(historyProgressBarValue[0], end);

        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                determinateBar.setProgress((Integer) animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // start your activity here
            }
        });
        animator.start();
        historyProgressBarValue[0]=end;
    }
    // Khi chọn câu trả lời thì nó sẽ lưu lại vào checkList
    private void CheckedRadioButton(DtoQuestionSet dto, int index) {
        rgAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                int radioButtonID = rgAnswer.getCheckedRadioButtonId();
                View radioButton = rgAnswer.findViewById(radioButtonID);
                int idx = rgAnswer.indexOfChild(radioButton);
                RadioButton checkedRadioButton = null;
                String answerValue = "";
                checkedRadioButton = (RadioButton) findViewById(checkedId);
                if((Integer) checkedId != -1 && historyID[0]!=checkedId){

                    answerValue = checkedRadioButton.getText().toString();

                }

                // check hết checkList nếu xuất questionId trong checkList nghĩa là câu này đc trả lời rồi
                boolean flag = false;
                for(int j = 0; j < checkList.size(); j++){
                    if(checkList.get(j).getQuestionId().equals(dto.getQuestList().get(index).getId())){
                        Log.i("CheckList I", String.valueOf(index));
                        flag = true;
                        break;
                    }
                }


                if(!flag){

                    if(checkedRadioButton != null && answerValue != "" && index == i[0]) {
                        TextView textview= (TextView)layoutQuestionBar.getChildAt(index);
                        textview.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.yellow)));
                        AddtoCheckList(idx, answerValue, dto, index);
                    }
                }
                else {
                    if(checkedRadioButton != null && answerValue != "") {
                        UpdateCheckList(idx, dto, index);
                    }
                }

            }
        });
    }

    private void UpdateCheckList(int idx, DtoQuestionSet dto, int i) {
        for(int j = 0; j < checkList.size(); j++) {
            if(checkList.get(j).getQuestionId().equals(dto.getQuestList().get(i).getId())){
                checkList.get(j).setAnswerIndex(idx);
            }
        }
    }

    private void UpdateHistory(String questionId) {
        try {
            boolean flag = true;
            for (CheckRadioButton item : checkList) {
                if (item.getQuestionId().equals(questionId)) {
                    flag = false;
                    RadioButton radioButton = (RadioButton) rgAnswer.getChildAt(item.getAnswerIndex());
                    radioButton.setChecked(true);

                    break;
                }

            }

            if (flag ) {
                ResetRadioButton();
            }
        } catch(Exception ex){
            Log.i("Error", ""+ex.getMessage());
        }

    }

    private void AddtoCheckList(int idx, String answerValue, DtoQuestionSet dto, int i) {
        CheckRadioButton checkRadioButton = new CheckRadioButton();
        checkRadioButton.setQuestionId(dto.getQuestList().get(i).getId());
        checkRadioButton.setQuestionIndex(i);
        checkRadioButton.setAnswerId(dto.getAnsList().get(i).getId());
        checkRadioButton.setAnswerValue(answerValue);
        checkRadioButton.setAnswerIndex(idx);
        checkList.add(checkRadioButton);
    }

    public void ShowTest(TestService testService, String questionSetId) {
        testService.GetByQuestionSet(questionSetId, new TestService.GetByQuestionSetCallBack() {
            @Override
            public void onError(String message) {
                Toast.makeText(TestActivity.this, "Error Babe!!!", Toast.LENGTH_LONG);
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(DtoQuestionSet dto) {

                totalQuestion = dto.getQuestList().size();
                tvTotalQuestion.setText("" + dto.getQuestionSet().get().getQuantity());

                UpdateQuestion(dto, totalQuestion, i[0]);
                CheckedRadioButton(dto, i[0]);
                for (int k = 1; k <=totalQuestion; k++) {
                    TextView question = new TextView(new ContextThemeWrapper(TestActivity.this, R.style.QuestionButton), null, 0);
                    question.setText(String.valueOf(k));
                    final int num=k;
                    question.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            i[0]=num-1;
                            myFab.callOnClick();
                            UpdateQuestion(dto, totalQuestion, i[0]);
                            CheckedRadioButton(dto, i[0]);
                        }
                    });
                    layoutQuestionBar.addView(question);
                }
                // khi bấm next
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i[0]++;
                        // trường hợp câu cuối, bấm chấm điểm
                        if(i[0] == totalQuestion){
                            Log.i("CheckList size", String.valueOf(checkList.size()));
                            Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                            intent.putExtra("History", checkList);
                            TestActivity.this.finish();
                            startActivity(intent);
                        }
                        // Trường hợp đang thi
                        else {
                            // Update lại câu hỏi
                            UpdateQuestion(dto, totalQuestion, i[0]);
                            CheckedRadioButton(dto, i[0]);
                        }
                    }
                });
                // Khi bấm prev
                btnPrev.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i[0]--;
                        // Update lại câu hỏi
                        UpdateQuestion(dto, totalQuestion, i[0]);
                        CheckedRadioButton(dto, i[0]);

                    }
                });

            }
        });
    }

    // Hàm hiển thị câu hỏi và câu trả lời
    private void UpdateQuestion(DtoQuestionSet dto, int totalQuestion, int i) {
        UpdateHistory(dto.getQuestList().get(i).getId());
        // Trường hợp câu 1
        if(i == 0){
            btnPrev.setVisibility(View.INVISIBLE);
        }
        // Trường hợp câu cuối
        else if(i == totalQuestion-1){
            btnNext.setText("Chấm điểm");
        }
        else{
            btnNext.setVisibility(View.VISIBLE);
            btnPrev.setVisibility(View.VISIBLE);
        }
        String photo = dto.getQuestList().get(i).getPhoto();
        // Khi nào có hình thì mở ra
//        if(!photo.isEmpty()){
//            String uri = photo.substring(0, photo.length() - 4);
//            int imageResource = getResources().getIdentifier(uri, "drawable", getPackageName());
//            Drawable res = getResources().getDrawable(imageResource);
//            iv_question.setImageDrawable(res);
//        }
        int index = dto.getQuestList().get(i).getIndex();
        String[] ansList = dto.getAnsList().get(i).getAnswerList();
        int numberOfAns = ansList.length;
        ProgressAnimation(index * 100 / totalQuestion);
//        determinateBar.setProgress();
        tvPositionQuestion.setText("" + dto.getQuestList().get(i).getIndex());
        tvQuestion.setText(dto.getQuestList().get(i).getQuery());

        switch (numberOfAns) {
            case 2:
                rdAnswer1.setText(ansList[0]);
                rdAnswer2.setText(ansList[1]);
                rdAnswer1.setVisibility(View.VISIBLE);
                rdAnswer2.setVisibility(View.VISIBLE);
                rdAnswer3.setVisibility(View.INVISIBLE);
                rdAnswer4.setVisibility(View.INVISIBLE);
                rdAnswer5.setVisibility(View.INVISIBLE);
                break;
            case 3:
                rdAnswer1.setText(ansList[0]);
                rdAnswer2.setText(ansList[1]);
                rdAnswer3.setText(ansList[2]);
                rdAnswer1.setVisibility(View.VISIBLE);
                rdAnswer2.setVisibility(View.VISIBLE);
                rdAnswer3.setVisibility(View.VISIBLE);
                rdAnswer4.setVisibility(View.INVISIBLE);
                rdAnswer5.setVisibility(View.INVISIBLE);
                break;
            case 4:
                rdAnswer1.setText(ansList[0]);
                rdAnswer2.setText(ansList[1]);
                rdAnswer3.setText(ansList[2]);
                rdAnswer4.setText(ansList[3]);
                rdAnswer1.setVisibility(View.VISIBLE);
                rdAnswer2.setVisibility(View.VISIBLE);
                rdAnswer3.setVisibility(View.VISIBLE);
                rdAnswer4.setVisibility(View.VISIBLE);
                rdAnswer5.setVisibility(View.INVISIBLE);
                break;
            case 5:
                rdAnswer1.setText(ansList[0]);
                rdAnswer2.setText(ansList[1]);
                rdAnswer3.setText(ansList[2]);
                rdAnswer4.setText(ansList[3]);
                rdAnswer5.setText(ansList[4]);
                rdAnswer1.setVisibility(View.VISIBLE);
                rdAnswer2.setVisibility(View.VISIBLE);
                rdAnswer3.setVisibility(View.VISIBLE);
                rdAnswer4.setVisibility(View.VISIBLE);
                rdAnswer5.setVisibility(View.VISIBLE);
            default:
                break;
        }
    }

    private void ResetRadioButton() {
        rgAnswer.clearCheck();
    }

    public void InitialVariables() {
        tvPositionQuestion = findViewById(R.id.tv_positionQuestion);
        tvTotalQuestion = findViewById(R.id.tv_totalQuestion);
        tvQuestion = findViewById(R.id.tv_question);
        determinateBar = findViewById(R.id.determinateBar);
        rdAnswer1 = findViewById(R.id.rd_answer1);
        rdAnswer2 = findViewById(R.id.rd_answer2);
        rdAnswer3 = findViewById(R.id.rd_answer3);
        rdAnswer4 = findViewById(R.id.rd_answer4);
        rdAnswer5 = findViewById(R.id.rd_answer5);
        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);
        ivQuestion = findViewById(R.id.iv_question);
        rgAnswer = findViewById(R.id.rg_answer);
        layoutQuestionBar= findViewById(R.id.layout_question_bar);
        mDrawerLayout=findViewById(R.id.drawer_test);
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
}