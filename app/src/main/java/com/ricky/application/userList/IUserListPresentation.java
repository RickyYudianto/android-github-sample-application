package com.ricky.application.userList;

import com.ricky.application.utils.webservice.models.User;

import java.util.List;

public interface IUserListPresentation {

    interface view {
        void onLoadUserList(List<User> userList);
        void onErrorLoadUserList(String message);
    }

    interface presenter {
        void loadUserList(int pagination);
    }

}
