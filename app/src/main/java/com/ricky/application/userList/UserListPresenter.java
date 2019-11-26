package com.ricky.application.userList;

import com.ricky.application.base.BaseAbstractPresenter;
import com.ricky.application.utils.webservice.ApiUtils;
import com.ricky.application.utils.webservice.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListPresenter extends BaseAbstractPresenter<UserListView>
        implements IUserListPresentation.presenter {

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void loadUserList() {
        ApiUtils.getAPIService().getUserList().enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                if (response.isSuccessful()) {
                    view.onLoadUserList(response.body());
                } else {
                    view.onErrorLoadUserList(response.message());
                }
            }

            @Override
            public void onFailure(Call<User[]> call, Throwable t) {
                view.onErrorLoadUserList(t.getMessage());
            }
        });
    }
}
