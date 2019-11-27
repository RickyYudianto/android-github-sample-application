package com.ricky.application.userDetails;

import com.ricky.application.base.BaseAbstractPresenter;
import com.ricky.application.utils.webservice.ApiUtils;
import com.ricky.application.utils.webservice.models.Repository;
import com.ricky.application.utils.webservice.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsPresenter extends BaseAbstractPresenter<UserDetailsView>
        implements IUserDetailsPresentation.presenter {

    private List<Repository> repositoryList = new ArrayList<>();

    @Override
    public void loadUserDetails(String login) {
        ApiUtils.getAPIService().getUserDetails(login, "63b533fa84f0755efb87", "c7e5cba44e1878f433d5f3f14009bcff3e02a4fc").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    view.onLoadUserDetails(response.body());
                    loadUserRepos(login);
                } else {
                    view.onErrorLoadUser(response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.onErrorLoadUser(t.getMessage());
            }
        });
    }

    @Override
    public void loadUserRepos(String login) {
        ApiUtils.getAPIService().getUserRepos(login, "63b533fa84f0755efb87", "c7e5cba44e1878f433d5f3f14009bcff3e02a4fc").enqueue(new Callback<Repository[]>() {
            @Override
            public void onResponse(Call<Repository[]> call, Response<Repository[]> response) {
                if (response.isSuccessful()) {
                    repositoryList.addAll(Arrays.asList(response.body()));
                    view.onLoadUserRepos(repositoryList);
                } else {
                    view.onErrorLoadUser(response.message());
                }
            }

            @Override
            public void onFailure(Call<Repository[]> call, Throwable t) {
                view.onErrorLoadUser(t.getMessage());
            }
        });
    }
}