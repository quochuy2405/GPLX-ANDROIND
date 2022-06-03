package team2.mobileapp.gplx.Volley.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    //    public void Login(String username, String password, LoginCallBack loginCallBack) {
//        String requestMapping = "/account/login";
//        String url = BASE_IP + requestMapping;
//
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                LoginResponse loginResponse = new LoginResponse();
//                try {
//                    JSONObject jsonObject = new JSONObject();
//                    loginResponse.setId(response.getString("Id"));
//                    loginResponse.setUsername(response.getString("Username"));
//                    loginResponse.setRoleId(response.getString("RoleId"));
//                    Log.d("Login Response", loginResponse.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                loginCallBack.onResponse(loginResponse);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                loginCallBack.onError(error.toString());
//            }
//        }) {
//
//            @Override
//            public String getBodyContentType() {
//                // as we are passing data in the form of url encoded
//                // so we are passing the content type below
//                return "application/x-www-form-urlencoded; charset=UTF-8";
//            }
////            @Override
////            public Map<String, String> getHeaders() throws AuthFailureError {
////                Map<String, String> params = new HashMap<String, String>();
////                params.put("Content-Type", "application/json");
////                return params;
////            }
//
//            @Override
//            protected Map<String, String> getParams() {
//                // below line we are creating a map for storing
//                // our values in key and value pair.
//                Map<String, String> params = new HashMap<String, String>();
//
//                Log.d("Username",username);
//                Log.d("Password",password);
//                // on below line we are passing our
//                // key and value pair to our parameters.
//                params.put("Username", username);
//                params.put("Password", password);
//
//                // at last we are returning our params.
//                return params;
//            }
//
//            @Override
//            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
//                int statusCode = response.statusCode;
//                return super.parseNetworkResponse(response);
//            }
//        };
//
////        queue.add(request);
//        MySingleton.getInstance(context).addToRequestQueue(request);
//    }
//    public void Login(String username, String password, LoginCallBack loginCallBack) {
//        try {
//            RequestQueue requestQueue = Volley.newRequestQueue(context);
//            String requestMapping = "/account/login";
//            String url = BASE_IP + requestMapping;
//            JSONObject jsonBody = new JSONObject();
//            jsonBody.put("Username", username);
//            jsonBody.put("Password", password);
//            final String mRequestBody = jsonBody.toString();
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.i("LOG_VOLLEY", response);
//                    loginCallBack.onResponse(new LoginResponse());
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("LOG_VOLLEY", error.toString());
//                    Toast.makeText(context, "Account invalid!", Toast.LENGTH_LONG).show();
//                }
//            }) {
//                @Override
//                public String getBodyContentType() {
//                    return "application/json; charset=utf-8";
//                }
//
//                @Override
//                public byte[] getBody() throws AuthFailureError {
//                    try {
//                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
//                    } catch (UnsupportedEncodingException uee) {
//                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
//                        return null;
//                    }
//                }
//
//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String responseString = "";
//                    if (response != null) {
//
//                        responseString = String.valueOf(response.statusCode);
//
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                }
//            };
//
////            requestQueue.add(stringRequest);
//            MySingleton.getInstance(context).addToRequestQueue(stringRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
    public void Login(String username, String password, LoginCallBack loginCallBack) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String requestMapping = "/account/login";
            String url = BASE_IP + requestMapping;
            JSONObject jsonBody = new JSONObject();
            System.out.println(username + " " + password);
            jsonBody.put("Username", username);
            jsonBody.put("Password", password);
            final String mRequestBody = jsonBody.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("LOG_VOLLEY", response.toString());
                    LoginResponse loginResponse = new LoginResponse();
                    try {
                        loginResponse.setRoleId(response.getString("RoleId"));
                        loginResponse.setUsername(response.getString("Username"));
                        loginResponse.setId(response.getString("Id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    loginCallBack.onResponse(loginResponse);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY", error.toString());
                    Toast.makeText(context, "Account invalid!", Toast.LENGTH_LONG).show();
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

//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String responseString = "";
//                    if (response != null) {
//
//                        responseString = String.valueOf(response.statusCode);
//
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                }
            };

//            requestQueue.add(stringRequest);
            MySingleton.getInstance(context).addToRequestQueue(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface SignupCallBack{
        void onError(String message);

        void onResponse(RegisterResponse registerResponse);
    }
    public void Register(Account account, SignupCallBack signupCallBack){
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String requestMapping = "/account/signup";
            String url = BASE_IP + requestMapping;
            JSONObject jsonBody = new JSONObject();
            System.out.println(account.toString());

            jsonBody.put("Firstname", account.getFirstName());
            jsonBody.put("Lastname", account.getLastName());
            jsonBody.put("Email", account.getEmail());
            jsonBody.put("Username", account.getUsername());
            jsonBody.put("Password", account.getPassword());

            final String mRequestBody = jsonBody.toString();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("LOG_VOLLEY", response.toString());
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
                    Log.e("LOG_VOLLEY", error.toString());
                    Toast.makeText(context, "Account invalid!", Toast.LENGTH_LONG).show();
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
//            requestQueue.add(stringRequest);
            MySingleton.getInstance(context).addToRequestQueue(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
