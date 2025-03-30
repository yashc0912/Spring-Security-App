package com.yash.client.service;

import java.util.Optional;

import com.yash.client.entity.User;
import com.yash.client.entity.VerificationToken;
import com.yash.client.model.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    Optional<User> getUserByPasswordResetToken(String token);

    boolean checkIfValidOldPassword(User user, String oldPassword);
}
