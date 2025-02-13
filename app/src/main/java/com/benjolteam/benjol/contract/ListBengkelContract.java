package com.benjolteam.benjol.contract;

import com.benjolteam.benjol.callback.RequestCallback;
import com.benjolteam.benjol.model.Bengkel;

import java.util.List;

public interface ListBengkelContract {
    interface View {
        void startLoading();
        void endLoading();
        void showError(String message);
        void setBengkel(List<Bengkel> bengkel);
        void setSearchresult(List<Bengkel> bengkel);
    }

    interface presenter {
        void setBengkel();
        void setSearchResult(String keyword);
    }

    interface Interactor {
        void requestBengkel(RequestCallback<List<Bengkel>> requestCallback);
        void searchBengkel(String keyword, RequestCallback<List<Bengkel>> requestCallback);
    }
}
