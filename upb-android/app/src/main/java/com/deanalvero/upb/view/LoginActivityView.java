package com.deanalvero.upb.view;

/**
 * Created by dean on 09/08/16.
 */
public interface LoginActivityView {

    void showProgressDialog();
//    void showProgressDialog(int messageId);
    void hideProgressDialog();

    void showMessage(int messageId);
    void showErrorMessage(int messageId);
}
