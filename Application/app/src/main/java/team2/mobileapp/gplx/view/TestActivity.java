package team2.mobileapp.gplx.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Volley.model.CheckRadioButton;
import team2.mobileapp.gplx.Volley.model.dto.DtoQuestionSet;
import team2.mobileapp.gplx.Volley.service.TestService;

public class TestActivity extends AppCompatActivity {

    TextView tv_positionQuestion, tv_totalQuestion, tv_question;
    ProgressBar determinateBar;
    RadioButton rd_answer1, rd_answer2, rd_answer3, rd_answer4, rd_answer5;
    Button btn_next, btn_prev;
    ImageView iv_question;
    RadioGroup rg_answer;
    boolean mSlideState;
    DrawerLayout mDrawerLayout;
    GridLayout layoutQuestionBar;
    FloatingActionButton myFab;
    int totalQuestion=0;
    final int[] historyID = {-1};
    final int[] i = {0};
    final ArrayList<CheckRadioButton> checkList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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




    }

    // Khi chọn câu trả lời thì nó sẽ lưu lại vào checkList
    private void CheckedRadioButton(DtoQuestionSet dto, int index) {
        rg_answer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                int radioButtonID = rg_answer.getCheckedRadioButtonId();
                View radioButton = rg_answer.findViewById(radioButtonID);
                int idx = rg_answer.indexOfChild(radioButton);
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
                    RadioButton radioButton = (RadioButton) rg_answer.getChildAt(item.getAnswerIndex());
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
                tv_totalQuestion.setText("" + dto.getQuestionSet().get().getQuantity());

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
                btn_next.setOnClickListener(new View.OnClickListener() {
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
                btn_prev.setOnClickListener(new View.OnClickListener() {
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
            btn_prev.setVisibility(View.INVISIBLE);
        }
        // Trường hợp câu cuối
        else if(i == totalQuestion-1){
            btn_next.setText("Chấm điểm");
        }
        else{
            btn_next.setVisibility(View.VISIBLE);
            btn_prev.setVisibility(View.VISIBLE);
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
        determinateBar.setProgress(index * 100 / totalQuestion);
        tv_positionQuestion.setText("" + dto.getQuestList().get(i).getIndex());
        tv_question.setText(dto.getQuestList().get(i).getQuery());

        switch (numberOfAns) {
            case 2:
                rd_answer1.setText(ansList[0]);
                rd_answer2.setText(ansList[1]);
                rd_answer1.setVisibility(View.VISIBLE);
                rd_answer2.setVisibility(View.VISIBLE);
                rd_answer3.setVisibility(View.INVISIBLE);
                rd_answer4.setVisibility(View.INVISIBLE);
                rd_answer5.setVisibility(View.INVISIBLE);
                break;
            case 3:
                rd_answer1.setText(ansList[0]);
                rd_answer2.setText(ansList[1]);
                rd_answer3.setText(ansList[2]);
                rd_answer1.setVisibility(View.VISIBLE);
                rd_answer2.setVisibility(View.VISIBLE);
                rd_answer3.setVisibility(View.VISIBLE);
                rd_answer4.setVisibility(View.INVISIBLE);
                rd_answer5.setVisibility(View.INVISIBLE);
                break;
            case 4:
                rd_answer1.setText(ansList[0]);
                rd_answer2.setText(ansList[1]);
                rd_answer3.setText(ansList[2]);
                rd_answer4.setText(ansList[3]);
                rd_answer1.setVisibility(View.VISIBLE);
                rd_answer2.setVisibility(View.VISIBLE);
                rd_answer3.setVisibility(View.VISIBLE);
                rd_answer4.setVisibility(View.VISIBLE);
                rd_answer5.setVisibility(View.INVISIBLE);
                break;
            case 5:
                rd_answer1.setText(ansList[0]);
                rd_answer2.setText(ansList[1]);
                rd_answer3.setText(ansList[2]);
                rd_answer4.setText(ansList[3]);
                rd_answer5.setText(ansList[4]);
                rd_answer1.setVisibility(View.VISIBLE);
                rd_answer2.setVisibility(View.VISIBLE);
                rd_answer3.setVisibility(View.VISIBLE);
                rd_answer4.setVisibility(View.VISIBLE);
                rd_answer5.setVisibility(View.VISIBLE);
            default:
                break;
        }
    }

    private void ResetRadioButton() {
        rg_answer.clearCheck();
    }

    public void InitialVariables() {
        tv_positionQuestion = findViewById(R.id.tv_positionQuestion);
        tv_totalQuestion = findViewById(R.id.tv_totalQuestion);
        tv_question = findViewById(R.id.tv_question);
        determinateBar = findViewById(R.id.determinateBar);
        rd_answer1 = findViewById(R.id.rd_answer1);
        rd_answer2 = findViewById(R.id.rd_answer2);
        rd_answer3 = findViewById(R.id.rd_answer3);
        rd_answer4 = findViewById(R.id.rd_answer4);
        rd_answer5 = findViewById(R.id.rd_answer5);
        btn_next = findViewById(R.id.btn_next);
        btn_prev = findViewById(R.id.btn_prev);
        iv_question = findViewById(R.id.iv_question);
        rg_answer = findViewById(R.id.rg_answer);
        layoutQuestionBar= findViewById(R.id.layout_question_bar);
        mDrawerLayout=findViewById(R.id.drawer_test);
    }

}