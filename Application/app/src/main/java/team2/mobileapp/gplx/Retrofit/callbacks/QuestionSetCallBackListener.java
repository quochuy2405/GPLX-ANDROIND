package team2.mobileapp.gplx.Retrofit.callbacks;

import java.util.ArrayList;

import team2.mobileapp.gplx.Retrofit.models.QuestionSet;

public interface QuestionSetCallBackListener {
    void onFetchProgress(ArrayList<QuestionSet> questionSets);
    void onFetchComplete(String message);
}
