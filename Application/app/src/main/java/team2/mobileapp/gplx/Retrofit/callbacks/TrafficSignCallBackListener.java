package team2.mobileapp.gplx.Retrofit.callbacks;

import team2.mobileapp.gplx.Volley.model.TrafficSign;

public interface TrafficSignCallBackListener {
    void onFetchProgress(TrafficSign trafficSign);
    void onFetchComplete(String message);
}
