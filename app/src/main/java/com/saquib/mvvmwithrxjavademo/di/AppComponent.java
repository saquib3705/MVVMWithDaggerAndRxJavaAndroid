package com.saquib.mvvmwithrxjavademo.di;


import com.saquib.mvvmwithrxjavademo.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(LoginActivity loginActivity);

}
