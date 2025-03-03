package com.yash.client.service;

import com.yash.client.entity.User;
import com.yash.client.entity.VerificationToken;
import com.yash.client.model.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);


}
