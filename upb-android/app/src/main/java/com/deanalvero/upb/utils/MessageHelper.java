package com.deanalvero.upb.utils;

import com.deanalvero.upb.Constant;
import com.deanalvero.upb.R;

/**
 * Created by dean on 09/08/16.
 */
public class MessageHelper {

    public static int getStringId(int messageId){
        switch (messageId){
            case Constant.CODE_SUCCESS_REGISTER:
                return R.string.success_register;

            case Constant.CODE_ERROR_USER_TAKEN:
                return R.string.error_user_taken;

            case Constant.CODE_ERROR_USER_AUTH:
                return R.string.error_user_auth;

            case Constant.CODE_SUCCESS_LOGIN:
                return R.string.success_login;

            default:
                return R.string.error_generic;
        }
    }
}
