package com.ricky.application.userDetails;

import com.ricky.application.utils.webservice.models.User;

public interface IUserDetailsPresentation {

    interface view {
        void onLoadUserDetails(User user);
        void onErrorLoadUser(String message);
    }

    interface presenter {
        void loadUserDetails(String login);
    }

}
