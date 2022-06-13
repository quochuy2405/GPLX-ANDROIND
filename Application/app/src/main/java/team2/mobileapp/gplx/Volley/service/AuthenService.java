package team2.mobileapp.gplx.Volley.service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import team2.mobileapp.gplx.Retrofit.dto.ChangePassword;
import team2.mobileapp.gplx.VariableGlobal.VariableGlobal;
import team2.mobileapp.gplx.Volley.callback.MySingleton;
import team2.mobileapp.gplx.Volley.model.Account;
import team2.mobileapp.gplx.Volley.model.dto.LoginResponse;
import team2.mobileapp.gplx.Volley.model.dto.RegisterResponse;

public class AuthenService {
    public static final String BASE_IP = "http://10.0.2.2:8080/api";

    Context context;

    public AuthenService(Context context) {
        this.context = context;
    }

    public interface LoginCallBack {
        void onError(String message);

        void onResponse(LoginResponse loginResponse);
    }

    public void Login(String loginType, String password, LoginCallBack loginCallBack) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String requestMapping = "/account/login";
            String url = BASE_IP + requestMapping;
            JSONObject jsonBody = new JSONObject();
            System.out.println(loginType + " " + password);
            jsonBody.put("LoginType", loginType);
            jsonBody.put("Password", password);
            final String mRequestBody = jsonBody.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("LOG_VOLLEY", response.toString());
                    LoginResponse loginResponse = new LoginResponse();
                    try {
                        loginResponse.setRoleId(response.getString("RoleId"));
                        loginResponse.setUsername(response.getString("Username"));
                        loginResponse.setEmail(response.getString("Email"));
                        loginResponse.setId(response.getString("Id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    loginCallBack.onResponse(loginResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Activity activity = (Activity) context;
                    VariableGlobal.showToast(activity, "Đăng nhập thất bại!");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface SignupCallBack {
        void onError(String message);

        void onResponse(RegisterResponse registerResponse);
    }

    public void Register(Account account, SignupCallBack signupCallBack) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String requestMapping = "/account/signup";
            String url = BASE_IP + requestMapping;
            JSONObject jsonBody = new JSONObject();


            jsonBody.put("FullName", account.getFullName());
            jsonBody.put("Email", account.getEmail());
            jsonBody.put("Username", account.getUsername());
            jsonBody.put("Password", account.getPassword());

            final String mRequestBody = jsonBody.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    RegisterResponse registerResponse = new RegisterResponse();
                    try {
                        registerResponse.setEmail(response.getString("Email"));
                        registerResponse.setUsername(response.getString("Username"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    signupCallBack.onResponse(registerResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Activity activity = (Activity) context;
                    VariableGlobal.showToast(activity, "Không xác thực được tài khoản");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // ChangePassword
    public interface ChangePasswordCallBack {
        void onError(String message);

        void onResponse(Account account);
    }

    public void ChangePassword(String email, ChangePassword changePassword, ChangePasswordCallBack changePasswordCallBack) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String requestMapping = "/account/changepass/" + email;
            String url = BASE_IP + requestMapping;
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("NewPassword", changePassword.getNewPassword());

            final String mRequestBody = jsonBody.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Account account = new Account();
                    try {
                        account.setId(response.getString("id"));
                        account.setRoleId(response.getString("roleId"));
                        account.setUsername(response.getString("Username"));
                        account.setPassword(response.getString("Password"));
                        account.setEmail(response.getString("Email"));
                        account.setFullName(response.getString("FullName"));
                        account.setAvatar(response.getString("Avatar"));
                        account.setStatus(response.getString("Status"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    changePasswordCallBack.onResponse(account);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Activity activity = (Activity) context;
                    VariableGlobal.showToast(activity, "Lỗi Email");
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
            };
            MySingleton.getInstance(context).addToRequestQueue(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
