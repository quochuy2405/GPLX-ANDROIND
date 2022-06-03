package team2.mobileapp.gplx.Retrofit.controllers;

import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team2.mobileapp.gplx.Retrofit.api.RestAPIManager;
import team2.mobileapp.gplx.Retrofit.callbacks.QuestionSetCallBackListener;
import team2.mobileapp.gplx.Retrofit.models.QuestionSet;

public class QuestionSetController {
    private QuestionSetCallBackListener questionSetCallBackListener;
    private RestAPIManager restAPIManager;
    private String message;

    public QuestionSetController(QuestionSetCallBackListener listener){
        questionSetCallBackListener = listener;
        restAPIManager = new RestAPIManager();
    }

    public void startFetching(String id) {
        restAPIManager.getQuestionSetAPI().GetQuestionSetByLicense(id).enqueue(new Callback<ArrayList<QuestionSet>>() {
            @Override
            public void onResponse(Call<ArrayList<QuestionSet>> call, Response<ArrayList<QuestionSet>> response) {
                try {
                    message = response.code() == 200 ? "Successfully" : "Error";
                    ArrayList<QuestionSet> sets = response.body();
                    questionSetCallBackListener.onFetchProgress(sets);
                } catch (Exception ex){
                    Log.d("Error", ex.getMessage());
                }
                questionSetCallBackListener.onFetchComplete(message);
            }

            @Override
            public void onFailure(Call<ArrayList<QuestionSet>> call, Throwable t) {
                Log.d("FAIL", "Failed");
            }
        });
    }
}
