package team2.mobileapp.gplx.Retrofit.callbacks;

import team2.mobileapp.gplx.Retrofit.dto.VerificationCode;

public interface ForgotPassCallBackListener {
    void onFetchForgotPassProgress(VerificationCode code);
    void onFetchComplete(String message);
    void onFetchCheckEmailProgress(int code);
}
