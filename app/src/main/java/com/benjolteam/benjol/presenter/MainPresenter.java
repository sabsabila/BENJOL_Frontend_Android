package com.benjolteam.benjol.presenter;

import com.benjolteam.benjol.contract.MainContract;
import com.benjolteam.benjol.interactor.MainInteractor;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private MainInteractor interactor;

    public MainPresenter(MainContract.View view, MainInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void checkIsUserLogin() {
        if(interactor.isUserLogin()){
            view.whenUserLogin();
        }
        else {
            view.whenUserNotLogin();
        }
    }
}
