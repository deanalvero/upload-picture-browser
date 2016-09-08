package com.deanalvero.upb.network;

/**
 * Created by dean on 09/08/16.
 */
public interface ILoginService {

    void register(String username, String password, RegisterCallback callback);
    void login(String username, String password, LoginCallback callback);

    interface RegisterCallback {
        void onSuccess();
        void onError(int messageId);
        void onFailure();
    }

    interface LoginCallback {
        void onSuccess();
        void onError(int messageId);
        void onFailure();
    }
}
