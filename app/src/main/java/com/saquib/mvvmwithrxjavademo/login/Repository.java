package com.saquib.mvvmwithrxjavademo.login;

import com.google.gson.JsonElement;
import com.saquib.mvvmwithrxjavademo.utils.ApiCallInterface;

import io.reactivex.Observable;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call login api
     * */
    public Observable<JsonElement> executeLogin(String mobileNumber, String password) {
        return apiCallInterface.login(mobileNumber, password);
    }

}
