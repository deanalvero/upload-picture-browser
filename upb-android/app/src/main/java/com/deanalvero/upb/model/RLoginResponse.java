package com.deanalvero.upb.model;

import java.util.HashMap;

/**
 * Created by dean on 09/08/16.
 */
public class RLoginResponse {

    private String resultCode;
    private HashMap<String, Object> user;

    public String getResultCode(){ return resultCode; }
    public HashMap<String, Object> getUser(){ return user; }
}
