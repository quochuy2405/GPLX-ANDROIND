package team2.mobileapp.gplx.Retrofit.callbacks;

import team2.mobileapp.gplx.Retrofit.dto.VerificationCode;
import team2.mobileapp.gplx.Retrofit.models.Account;

public interface ForgotPassCallBackListener {
    void onFetchForgotPassProgress(VerificationCode code);
    void onFetchComplete(String message);
}
