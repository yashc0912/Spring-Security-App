package com.yash.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.client.entity.User;
import com.yash.client.entity.VerificationToken;
import com.yash.client.event.RegistrationCompleteEvent;
import com.yash.client.model.PasswordModel;
import com.yash.client.model.UserModel;
import com.yash.client.service.UserService;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class ResgitrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {

        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
            user,
            applicationUrl(request)
                    ));
                    return "Success";
                }

        
        @GetMapping("/verifyRegistration")        
        public String verifyRegistration(@RequestParam("token") String token){

            String result = userService.validateVerificationToken(token);
            if(result.equalsIgnoreCase("valid")){
                return "User Verified Successfuly";
            }
            return "Bad User";
        }

                    @PostMapping("/resetPassword")
            public ResponseEntity<Map<String, String>> resetPassword(@RequestBody PasswordModel passwordModel, HttpServletRequest request) {
                User user = userService.findUserByEmail(passwordModel.getEmail());
                
                if (user == null) {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "User not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }

                String token = UUID.randomUUID().toString();
                userService.createPasswordResetTokenForUser(user, token);
                
                String resetUrl = passwordResetTokenMail(user, applicationUrl(request), token);

                // Returning JSON response instead of plain text
                Map<String, String> response = new HashMap<>();
                response.put("resetUrl", resetUrl);
                return ResponseEntity.ok(response);
            }
        @PostMapping("/savePassword")
        public String savePassword(@RequestParam("token") String token,
                                   @RequestBody PasswordModel passwordModel) {
            String result = userService.validatePasswordResetToken(token);
            if(!result.equalsIgnoreCase("valid")) {
                return "Invalid Token";
            }
            Optional<User> user = userService.getUserByPasswordResetToken(token);
            if(user.isPresent()) {
                userService.changePassword(user.get(), passwordModel.getNewPassword());
                return "Password Reset Successfully";
            } else {
                return "Invalid Token";
            }
        }
        
        private String passwordResetTokenMail(User user, String applicationUrl, String token) {
            String url = applicationUrl + "/savePassword?token=" + token;

                //sendVerificationEmail()
                log.info("Clink the link to verify your account: {}",url);
                return url;
        }

                
                        @GetMapping("/resendVerifyToken")
        public String resendVerificationtoken(@RequestParam("token") String oldToken,
                                                HttpServletRequest request){

                VerificationToken verificationToken 
                                                    = userService.generateNewVerificationToken(oldToken);
                User user = verificationToken.getUser();
                resendVerificationTokenMail(user,applicationUrl(request),verificationToken);
                                return "Verification Link Sent";
                                                                }
                            
            private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
          
                String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();

                //sendVerificationEmail()
                log.info("Clink the link to verify your account: {}",url);

        }

                
                                private String applicationUrl(HttpServletRequest request) {
                 
                    return "http://" + 
                            request.getServerName() +
                             ":" + 
                             request.getServerPort() + 
                             request.getContextPath();                }


}
