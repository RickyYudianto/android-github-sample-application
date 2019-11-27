package com.ricky.application.userList;

import com.ricky.application.base.BaseAbstractPresenter;
import com.ricky.application.utils.Constant;
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
    public void loadUserList(int lastId) {
        ApiUtils.getAPIService().getUserList(lastId, Constant.CLIENT_ID, Constant.CLIENT_SECRET).enqueue(new Callback<User[]>() {
            @Override
            public void onResponse(Call<User[]> call, Response<User[]> response) {
                if (response.isSuccessful()) {
                    User[] responses = response.body();
                    userList.addAll(Arrays.asList(responses));

                    if(responses.length < Constant.MAX_SIZE_PER_PAGE) view.allUsersLoaded();
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
