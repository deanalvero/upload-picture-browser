package com.deanalvero.upb.network;

import android.util.Log;

import com.deanalvero.upb.Constant;
import com.deanalvero.upb.model.RLoginResponse;
import com.deanalvero.upb.model.RRegisterResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dean on 09/08/16.
 */
public class LoginServiceRetrofitImpl implements ILoginService {

    private static final String TAG = LoginServiceRetrofitImpl.class.getSimpleName();
    private UPBRetrofitService.UPBApi mUpbApi;

    public LoginServiceRetrofitImpl(UPBRetrofitService.UPBApi upbApi){
        this.mUpbApi = upbApi;
    }

    @Override
    public void register(String username, String password, final RegisterCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        Call<RRegisterResponse> call = mUpbApi.register(params);
        call.enqueue(new Callback<RRegisterResponse>() {
            @Override
            public void onResponse(Call<RRegisterResponse> call, Response<RRegisterResponse> response) {
                if (response.isSuccessful()){
                    RRegisterResponse rResponse = response.body();
                    String resultCode = rResponse.getResultCode();

                    if (resultCode.equals(Constant.SERVER_CODE_USER_ADDED)){
                        callback.onSuccess();
                    } else if (resultCode.equals(Constant.SERVER_CODE_USER_EXISTS)){
                        callback.onError(Constant.CODE_ERROR_USER_TAKEN);
                    } else {
                        callback.onFailure();
                    }

                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<RRegisterResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void login(String username, String password, final LoginCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        Call<RLoginResponse> call = mUpbApi.login(params);
        call.enqueue(new Callback<RLoginResponse>() {
            @Override
            public void onResponse(Call<RLoginResponse> call, Response<RLoginResponse> response) {
                if (response.isSuccessful()){
                    RLoginResponse rResponse = response.body();
                    String resultCode = rResponse.getResultCode();

                    if (resultCode.equals(Constant.SERVER_CODE_SUCCESS)){
                        callback.onSuccess();
                    } else if (resultCode.equals(Constant.SERVER_CODE_ERROR_LOGIN)){
                        callback.onError(Constant.CODE_ERROR_USER_AUTH);
                    } else {
                        callback.onFailure();
                    }

                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<RLoginResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
