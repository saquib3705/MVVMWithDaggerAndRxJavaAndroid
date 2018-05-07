package com.saquib.mvvmwithrxjavademo.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.saquib.mvvmwithrxjavademo.login.Repository;
import com.saquib.mvvmwithrxjavademo.login.LoginViewModel;

import javax.inject.Inject;

/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
