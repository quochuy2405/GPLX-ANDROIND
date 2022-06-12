package team2.mobileapp.gplx.Retrofit.controllers;

import android.util.Log;

import androidx.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team2.mobileapp.gplx.Retrofit.api.RestAPIManager;
import team2.mobileapp.gplx.Retrofit.callbacks.AccountCallbackListener;
import team2.mobileapp.gplx.Retrofit.callbacks.ChangePassCallBackListener;
import team2.mobileapp.gplx.Retrofit.callbacks.ForgotPassCallBackListener;
import team2.mobileapp.gplx.Retrofit.dto.ChangePassword;
import team2.mobileapp.gplx.Retrofit.dto.VerificationCode;
import team2.mobileapp.gplx.Retrofit.models.Account;

public class AccountController {
    private AccountCallbackListener accountCallbackListener;
    private ForgotPassCallBackListener forgotPassCallBackListener;
    private ChangePassCallBackListener changePassCallBackListener;
    private RestAPIManager restAPI;
    private String message;

    public AccountController(AccountCallbackListener accountCallbackListener) {
        this.accountCallbackListener = accountCallbackListener;
        this.restAPI = new RestAPIManager();
    }

    public AccountController(ForgotPassCallBackListener forgotPassCallBackListener) {
        this.forgotPassCallBackListener = forgotPassCallBackListener;
        this.restAPI = new RestAPIManager();
    }

    public AccountController(ChangePassCallBackListener changePassCallBackListener) {
        this.changePassCallBackListener = changePassCallBackListener;
        this.restAPI = new RestAPIManager();
    }

    public void startFetching(String id) {
        restAPI.getAccountAPI().getAccount(id).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                try {
                    message = response.code() == 200 ? "Successfully fetched" : "Failed to fetch";
                    Account account = response.body();

                    accountCallbackListener.onFetchAccountProgress(account);
                } catch (Exception e) {
                    Log.d("Error: ", e.getMessage());
                }
                accountCallbackListener.onFetchComplete(message);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.d("FAIL", "onResponse: True");
            }
        });
    }

    public void updateAccount(String id, Account account) {
        restAPI.getAccountAPI().updateAccount(id, account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                try {
                    Account account = response.body();
                    if (account != null && response.code() == 200) {
                        message = "Cập nhật thông tin cá nhân thành công";
                    }
                } catch (Exception e) {
                    message = "Có lỗi xảy ra";
                    Log.d("Error:", e.getMessage());
                }
                accountCallbackListener.onFetchComplete(message);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                message = "Có lỗi xảy ra";
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void ForgotPassword(String email) {
        restAPI.getAccountAPI().forgotPass(email).enqueue(new Callback<VerificationCode>() {
            @Override
            public void onResponse(Call<VerificationCode> call, Response<VerificationCode> response) {
                try {
                    message = response.code() == 200 ? "Successfully fetched" : "Failed to fetch";
                    VerificationCode code = response.body();
                    forgotPassCallBackListener.onFetchForgotPassProgress(code);
                } catch (Exception e) {
                    Log.d("Error: ", e.getMessage());
                }
                forgotPassCallBackListener.onFetchComplete(message);
            }

            @Override
            public void onFailure(Call<VerificationCode> call, Throwable t) {
                Log.d("FAIL", "onResponse: True");
            }
        });
    }
    public void CheckEmail(String email){

        restAPI.getAccountAPI().checkEmail(email).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                try {
                    message = response.code() == 200 ? "Successfully fetched" : "Failed to fetch";
                    int code=response.code();
                    forgotPassCallBackListener.onFetchCheckEmailProgress(code);
                } catch (Exception e) {
                    Log.d("Error: ", e.getMessage());
                }
                forgotPassCallBackListener.onFetchComplete(message);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("FAIL", t.getMessage());
            }
        });
    }

    public void ChangePassword(String email, ChangePassword password) {
        restAPI.getAccountAPI().changePass(email, password).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                try {
                    message = response.code() == 200 ? "Successfully fetched" : "Failed to fetch";

                    Account account = response.body();

                    changePassCallBackListener.onFetchChangePassProgress(account);
                } catch (Exception e) {
                    Log.d("Error: ", e.getMessage());
                }
                changePassCallBackListener.onFetchComplete(message);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.d("FAIL", "onResponse: True");
            }
        });
    }
}
