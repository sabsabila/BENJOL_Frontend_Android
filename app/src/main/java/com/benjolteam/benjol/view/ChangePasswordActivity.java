package com.benjolteam.benjol.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.benjolteam.benjol.contract.ChangePasswordContract;
import com.benjolteam.android_example.databinding.ActivityEditPasswordBinding;
import com.benjolteam.benjol.interactor.ChangePasswordInteractor;
import com.benjolteam.benjol.presenter.ChangePasswordPresenter;
import com.benjolteam.benjol.util.UtilProvider;

import java.io.File;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordContract.View, View.OnClickListener{
    private ChangePasswordContract.Presenter presenter;
    private ActivityEditPasswordBinding binding;
    private String gender = "-";
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ChangePasswordPresenter(this, new ChangePasswordInteractor(UtilProvider.getSharedPreferencesUtil()));
        initView();

    }

    private void initView(){
        binding.baseLayout.pageTitle.setText("Change Password");
        binding.baseLayout.backButton.setOnClickListener(this);
        binding.saveButton.setOnClickListener(this);
        binding.navbar.homeButton.setOnClickListener(this);
        binding.navbar.profileButton.setOnClickListener(this);
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
    public void onClick(View v) {
        if(v.getId() == binding.baseLayout.backButton.getId()){
            onBackButtonClick();
        }
        if(v.getId() == binding.saveButton.getId()){
            onSaveButtonClick();
        }
        if(v.getId() == binding.navbar.homeButton.getId()){
            onHomeButtonClick();
        }
        if(v.getId() == binding.navbar.profileButton.getId()){
            onProfileClick();
        }
    }

    private void onHomeButtonClick() {
        finish();
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void onProfileClick() {
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    private void onSaveButtonClick() {
        String[] password = {binding.editOldPassword.getText().toString(), binding.editNewPassword.getText().toString()};
        presenter.savePassword(password);
    }

    public void onBackButtonClick(){
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changePasswordSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(this, ProfileActivity.class));
    }
}
