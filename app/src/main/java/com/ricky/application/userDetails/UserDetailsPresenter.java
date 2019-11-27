package com.ricky.application.userDetails;

import com.ricky.application.base.BaseAbstractPresenter;
import com.ricky.application.utils.Constant;
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
        ApiUtils.getAPIService().getUserDetails(login, Constant.CLIENT_ID, Constant.CLIENT_SECRET).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    view.onLoadUserDetails(response.body());
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
    public void loadUserRepos(String login, int page, boolean refreshAll) {
        ApiUtils.getAPIService().getUserRepos(login, page, Constant.CLIENT_ID, Constant.CLIENT_SECRET).enqueue(new Callback<Repository[]>() {
            @Override
            public void onResponse(Call<Repository[]> call, Response<Repository[]> response) {
                if (response.isSuccessful()) {
                    Repository[] responses = response.body();
                    if(refreshAll) repositoryList.clear();

                    repositoryList.addAll(Arrays.asList(responses));

                    if(responses.length < Constant.MAX_SIZE_PER_PAGE) view.allReposLoaded();
                    view.onLoadUserRepos(repositoryList);
                } else {
                    view.onErrorLoadRepositoryList(response.message());
                }
            }

            @Override
            public void onFailure(Call<Repository[]> call, Throwable t) {
                view.onErrorLoadRepositoryList(t.getMessage());
            }
        });
    }
}
