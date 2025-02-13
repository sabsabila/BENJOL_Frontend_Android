package com.benjolteam.benjol.interactor;

import com.benjolteam.benjol.api_response.ProgressServiceResponse;
import com.benjolteam.benjol.api_response.ResponseMessage;
import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.constant.ApiConstant;
import com.benjolteam.benjol.contract.ProgressServiceContract;
import com.benjolteam.benjol.util.SharedPreferencesUtil;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.List;

public class ProgressServiceInteractor implements ProgressServiceContract.Interactor {
    private SharedPreferencesUtil sharedPreferencesUtil;

    public ProgressServiceInteractor(SharedPreferencesUtil sharedPreferencesUtil) {
        this.sharedPreferencesUtil = sharedPreferencesUtil;
    }

    @Override
    public void requestProgressService(int id, final RequestCallback<List<String>> requestCallback) {
        AndroidNetworking.get(ApiConstant.BASE_URL + "/api/progress/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .build()
                .getAsObject(ProgressServiceResponse.class, new ParsedRequestListener<ProgressServiceResponse>() {
                    @Override
                    public void onResponse(ProgressServiceResponse response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess(response.progress);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to load data !");
                    }
                });
    }

    @Override
    public void requestCancelBooking(int id, final RequestCallback<String> requestCallback) {
        AndroidNetworking.put(ApiConstant.BASE_URL + "/api/bookingStatus/" + id)
                .addHeaders("Authorization", "Bearer " + sharedPreferencesUtil.getToken())
                .addBodyParameter("status", "canceled")
                .build()
                .getAsObject(ResponseMessage.class, new ParsedRequestListener<ResponseMessage>() {
                    @Override
                    public void onResponse(ResponseMessage response) {
                        if(response == null){
                            requestCallback.requestFailed("Null Response");
                        }
                        else {
                            requestCallback.requestSuccess("Booking canceled successfully");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        requestCallback.requestFailed("Failed to proceed !");
                    }
                });
    }
}
