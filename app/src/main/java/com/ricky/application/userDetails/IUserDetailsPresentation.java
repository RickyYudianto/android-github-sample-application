package com.ricky.application.userDetails;

import com.ricky.application.utils.webservice.models.Repository;
import com.ricky.application.utils.webservice.models.User;

import java.util.List;

public interface IUserDetailsPresentation {

    interface view {
        void onLoadUserDetails(User user);
        void onLoadUserRepos(List<Repository> repositoryList);
        void onErrorLoadUser(String message);
        void onErrorLoadRepositoryList(List<Repository> repositoryList, String message);
        void allReposLoaded();
    }

    interface presenter {
        void loadUserDetails(String login);
        void loadUserRepos(String login, int page, boolean refreshAll);
    }

}
