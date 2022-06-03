package team2.mobileapp.gplx.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import team2.mobileapp.gplx.R;
import team2.mobileapp.gplx.Retrofit.callbacks.TestCallBackListener;
import team2.mobileapp.gplx.Retrofit.controllers.QuestionDetailsController;
import team2.mobileapp.gplx.Retrofit.dto.QuestionDetails;

public class QuestionViewListActivity extends AppCompatActivity implements TestCallBackListener {
    private QuestionDetailsController questionDetailsController;
    public QuestionListViewAdapter questionListViewAdapter;
    private ListView listView;
    private TextView tvTitleBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view_list);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        listView = (ListView) findViewById(R.id.lvAllQuestion);
        tvTitleBoard = (TextView) findViewById(R.id.tv_title_board);
        String title = getIntent().getExtras().getString("TITLE_QUESTION_LIST");
        String license = getIntent().getExtras().getString("LICENSE_QUESTION_LIST");
        String type = getIntent().getExtras().getString("TYPE_QUESTION_LIST");
        tvTitleBoard.setText(title.toUpperCase());
        questionDetailsController = new QuestionDetailsController(this);
        questionDetailsController.startFetching(license,type);
    }

    @Override
    public void onFetchProgress(ArrayList<QuestionDetails> trafficSigns) {
        if (!trafficSigns.isEmpty()) {

            questionListViewAdapter = new QuestionListViewAdapter(QuestionViewListActivity.this, R.layout.question_view_list_item, trafficSigns);
            listView.setAdapter(questionListViewAdapter);
        }

    }

    @Override
    public void onFetchComplete(String message) {
        Log.d("onFetchComplete", message);
    }
}