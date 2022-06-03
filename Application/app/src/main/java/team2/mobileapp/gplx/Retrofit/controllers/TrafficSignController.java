package team2.mobileapp.gplx.Retrofit.controllers;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team2.mobileapp.gplx.Retrofit.api.RestAPIManager;
import team2.mobileapp.gplx.Retrofit.callbacks.TrafficSignCallBackListener;
import team2.mobileapp.gplx.Volley.model.TrafficSign;

public class TrafficSignController {
    private TrafficSignCallBackListener trafficSignCallbackListener;
    private RestAPIManager restApiManager;
    private String message;

    public TrafficSignController(TrafficSignCallBackListener listener) {
        trafficSignCallbackListener = listener;
        restApiManager = new RestAPIManager();
    }

    public void startFetching(String id) {
        restApiManager.getTrafficSignApi().getTrafficSign(id).enqueue(new Callback<TrafficSign>() {
            @Override
            public void onResponse(Call<TrafficSign> call, Response<TrafficSign> response) {
                try {
                    message = response.code() == 200 ? " Successfully" : " Error";
                    TrafficSign trafficSign = response.body();
                    trafficSignCallbackListener.onFetchProgress(trafficSign);

                } catch (Exception e) {
                    Log.d("Error:", e.getMessage());
                }
                trafficSignCallbackListener.onFetchComplete(message);
            }
            @Override
            public void onFailure(Call<TrafficSign> call, Throwable t) {
                Log.d("FAIL", "onResponse: True " );
            }
        });
    }
}
