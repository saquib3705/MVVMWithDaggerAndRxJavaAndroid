package com.saquib.mvvmwithrxjavademo.login;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.saquib.mvvmwithrxjavademo.MyApplication;
import com.saquib.mvvmwithrxjavademo.R;
import com.saquib.mvvmwithrxjavademo.utils.ApiResponse;
import com.saquib.mvvmwithrxjavademo.utils.Constant;
import com.saquib.mvvmwithrxjavademo.utils.ViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${Saquib} on 03-05-2018.
 */
public class LoginActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;

    @BindView(R.id.phone_no)
    EditText phoneNo;

    @BindView(R.id.password)
    EditText password;

    LoginViewModel viewModel;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        progressDialog = Constant.getProgressDialog(this, "Please wait...");

        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        viewModel.loginResponse().observe(this, apiResponse -> consumeResponse(apiResponse));


    }


    @OnClick(R.id.login)
    void onLoginClicked() {
        if (isValid()) {
            if (!Constant.checkInternetConnection(this)) {
                Toast.makeText(LoginActivity.this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
            } else {
                viewModel.hitLoginApi(phoneNo.getText().toString(), password.getText().toString());
            }

        }
    }

    /*
     * method to validate $(mobile number) and $(password)
     * */
    private boolean isValid() {

        if (phoneNo.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this,getResources().getString(R.string.enter_valid_mobile), Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this,getResources().getString(R.string.enter_valid_password), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    /*
     * method to handle response
     * */
    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    /*
    * method to handle success response
    * */
    private void renderSuccessResponse(JsonElement response) {
        if (!response.isJsonNull()) {
            Log.d("response=", response.toString());
        } else {
            Toast.makeText(LoginActivity.this,getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }
}
