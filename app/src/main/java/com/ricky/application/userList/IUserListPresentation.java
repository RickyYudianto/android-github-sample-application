package com.ricky.application.userList;

import com.ricky.application.utils.webservice.models.User;

public interface IUserListPresentation {

    interface view {
        void onLoadUserList(User[] userList);
        void onErrorLoadUserList(String message);
    }

    interface presenter {
        void loadUserList();
    }

}
