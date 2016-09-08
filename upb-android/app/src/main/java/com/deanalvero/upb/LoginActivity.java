package com.deanalvero.upb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deanalvero.upb.network.LoginServiceRetrofitImpl;
import com.deanalvero.upb.network.UPBRetrofitService;
import com.deanalvero.upb.presenter.LoginActivityPresenter;
import com.deanalvero.upb.utils.MessageHelper;
import com.deanalvero.upb.utils.UIHelper;
import com.deanalvero.upb.view.LoginActivityView;

/**
 * Created by dean on 09/08/16.
 */
public class LoginActivity extends AppCompatActivity implements LoginActivityView {

    private ProgressDialog mProgressDialog;
    private LoginActivityPresenter mLoginActivityPresenter;
    private EditText mEditTextUsername, mEditTextPassword;
    private CoordinatorLayout mCoordinatorLayout;

    private static final String KEY_IP = "KEY_IP";
    private static final String KEY_PORT = "KEY_PORT";
    private String mIP = null;
    private String mPort = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupOnCreate();
    }

    public static Intent newIntent(Context context, String ip, String port){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(KEY_IP, ip);
        intent.putExtra(KEY_PORT, port);
        return intent;
    }

    private void setupOnCreate() {
        mEditTextUsername = (EditText) findViewById(R.id.editText_username);
        mEditTextPassword = (EditText) findViewById(R.id.editText_password);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra(KEY_IP)){
                mIP = intent.getStringExtra(KEY_IP);
            }

            if (intent.hasExtra(KEY_PORT)){
                mPort = intent.getStringExtra(KEY_PORT);
            }
        }

        mLoginActivityPresenter = new LoginActivityPresenter(this, new LoginServiceRetrofitImpl(UPBRetrofitService.createUPBRetrofitService(mIP, mPort)));

        findViewById(R.id.button_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginActivityPresenter.register(
                        mEditTextUsername.getText().toString(),
                        mEditTextPassword.getText().toString()
                );

                UIHelper.hideKeyboard(LoginActivity.this);
            }
        });

        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginActivityPresenter.login(
                        mEditTextUsername.getText().toString(),
                        mEditTextPassword.getText().toString()
                );

                UIHelper.hideKeyboard(LoginActivity.this);
            }
        });
    }


    @Override
    public void showProgressDialog() {
        showProgressDialog(R.string.loading);
    }

    public void showProgressDialog(int messageId) {
        mProgressDialog = ProgressDialog.show(this, null, getResources().getString(messageId), true);
    }

    @Override
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(int messageId) {
        showMessageView(messageId);
    }

    @Override
    public void showErrorMessage(int messageId) {
        showMessageView(messageId);
    }

    private void showMessageView(int messageId){
        Toast.makeText(LoginActivity.this, MessageHelper.getStringId(messageId), Toast.LENGTH_SHORT).show();
        Snackbar.make(mCoordinatorLayout, MessageHelper.getStringId(messageId), Snackbar.LENGTH_LONG).show();
    }
}
