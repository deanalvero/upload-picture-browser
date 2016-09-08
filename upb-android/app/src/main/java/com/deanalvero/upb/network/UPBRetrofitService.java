package com.deanalvero.upb.network;

import com.deanalvero.upb.model.RLoginResponse;
import com.deanalvero.upb.model.RRegisterResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by dean on 09/08/16.
 */
public class UPBRetrofitService {

//    private static final String BASE_URL = "http://192.168.0.183:3001";
    private static final String DEFAULT_IP = "192.168.0.183";
    private static final String DEFAULT_PORT = "3001";

    private UPBRetrofitService(){
    }

//    public static UPBApi createUPBRetrofitService(){
//        return createUPBRetrofitService(BASE_URL);
//    }

    public static UPBApi createUPBRetrofitService(String ip, String port){
        String serverIP = ip;
        String serverPort = port;
        if (serverIP == null) serverIP = DEFAULT_IP;
        if (serverPort == null) serverPort = DEFAULT_PORT;

        return createUPBRetrofitService(String.format("http://%s:%s", serverIP, serverPort));
    }

    public static UPBApi createUPBRetrofitService(String URL){
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL);

        return builder.build().create(UPBApi.class);
    }

    public interface UPBApi {

        @POST("/api/register")
        Call<RRegisterResponse> register(@Body HashMap<String, Object> body);

        @POST("/api/login")
        Call<RLoginResponse> login(@Body HashMap<String, Object> body);
    }
}
