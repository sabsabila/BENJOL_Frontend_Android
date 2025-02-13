package com.benjolteam.benjol.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.benjolteam.benjol.contract.LoginContract;
import com.benjolteam.android_example.databinding.ActivityLoginBinding;
import com.benjolteam.benjol.interactor.LoginInteractor;
import com.benjolteam.benjol.presenter.LoginPresenter;
import com.benjolteam.benjol.util.UtilProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    private LoginContract.Presenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this, new LoginInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();
    }

    private void initView(){
        binding.baseLayout.pageTitle.setText("Login");
        binding.baseLayout.backButton.setVisibility(View.GONE);
        binding.loginButton.setOnClickListener(this);
        binding.createAccountButton.setOnClickListener(this);
    }

    @Override
    public void startLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginSuccess() {
        finish();
        startActivity(new Intent(this, DashboardActivity.class));
    }

    @Override
    public void register() {
        finish();
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.loginButton.getId()){
            onButtonLoginClick();
        }
        if(v.getId() == binding.createAccountButton.getId()){
            onButtonRegisterClick();
        }
    }

    public void onButtonLoginClick(){
        presenter.login(binding.email.getText().toString(), binding.password.getText().toString());
    }

    public void onButtonRegisterClick(){
        presenter.register();
    }
}
