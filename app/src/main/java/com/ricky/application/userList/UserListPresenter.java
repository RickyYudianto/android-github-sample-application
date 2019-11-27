package com.ricky.application.userList;

import com.ricky.application.base.BaseAbstractPresenter;
import com.ricky.application.utils.webservice.ApiUtils;
import com.ricky.application.utils.webservice.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListPresenter extends BaseAbstractPresenter<UserListView>
        implements IUserListPresentation.presenter {

    private List<User> userList = new ArrayList<>();

    @Override
    public void onResume() {
        view.onResume();
    }

    @Override
    public void onPause() {
        view.onPause();
    }

    @Override
    public void onDestroy() {
        view.onDestroy();
    }

    @Override
    public void loadUserList(int lastId) {
        ApiUtils.getAPIService().getUserList(lastId, "63b533fa84f0755efb87", "c7e5cba44e1878f433d5f3f14009bcff3e02a4fc").enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                if (response.isSuccessful()) {
                    userList.addAll(Arrays.asList(response.body()));
                    view.onLoadUserList(userList);
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
