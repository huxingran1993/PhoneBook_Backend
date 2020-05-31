package com.phonebookbackend.services;

import com.phonebookbackend.model.User;

public interface UmsAdminService {
    User getAdminByUsername(String username);

    User register(User umsAdminParam);

    String login(String username, String password);

}
