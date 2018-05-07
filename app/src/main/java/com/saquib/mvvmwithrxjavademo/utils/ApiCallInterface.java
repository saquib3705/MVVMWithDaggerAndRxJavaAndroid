package com.saquib.mvvmwithrxjavademo.utils;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ${Saquib} on 03-05-2018.
 */


public interface ApiCallInterface {

    @FormUrlEncoded
    @POST(Urls.LOGIN)
    Observable<JsonElement> login(@Field("mobile") String mobileNumber, @Field("password") String password);
}
