package com.deanalvero.upb.presenter;

import com.deanalvero.upb.Constant;
import com.deanalvero.upb.network.ILoginService;
import com.deanalvero.upb.view.LoginActivityView;

/**
 * Created by dean on 09/08/16.
 */
public class LoginActivityPresenter {

    private LoginActivityView view;
    private ILoginService service;

    public LoginActivityPresenter(LoginActivityView view, ILoginService service){
        this.view = view;
        this.service = service;
    }

    public void register(String username, String password){
        view.showProgressDialog();
        service.register(username, password, new ILoginService.RegisterCallback() {
            @Override
            public void onSuccess() {
                view.hideProgressDialog();
                view.showMessage(Constant.CODE_SUCCESS_REGISTER);
            }

            @Override
            public void onError(int messageId) {
                view.hideProgressDialog();
                view.showErrorMessage(messageId);
            }

            @Override
            public void onFailure() {
                view.hideProgressDialog();
                view.showErrorMessage(Constant.CODE_ERROR_GENERIC);
            }
        });
    }

    public void login(String username, String password){
        view.showProgressDialog();
        service.login(username, password, new ILoginService.LoginCallback() {
            @Override
            public void onSuccess() {
                view.hideProgressDialog();
                view.showMessage(Constant.CODE_SUCCESS_LOGIN);
            }

            @Override
            public void onError(int messageId) {
                view.hideProgressDialog();
                view.showErrorMessage(messageId);
            }

            @Override
            public void onFailure() {
                view.hideProgressDialog();
                view.showErrorMessage(Constant.CODE_ERROR_GENERIC);
            }
        });
    }
}
