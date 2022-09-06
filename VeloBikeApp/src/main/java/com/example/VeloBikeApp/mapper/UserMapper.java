package com.example.VeloBikeApp.mapper;


import com.example.VeloBikeApp.dto.UserBeanResponse;
import com.example.VeloBikeApp.dto.UserBeanToCreate;
import com.example.VeloBikeApp.model.User;

public class UserMapper {
    public static UserBeanToCreate mapToUserBeanToCreateFromUser(User user) {
        UserBeanToCreate createUserBean = new UserBeanToCreate();
        if (user != null) {
            createUserBean.setEmail(user.getEmail());
            createUserBean.setPass(user.getPass());
            createUserBean.setRepeatedPass(user.getRepeatedPass());
            createUserBean.setKeyWord(user.getKeyword());
            createUserBean.setTelephoneNumber(user.getTelephoneNumber());
            createUserBean.setCountry(user.getCountry());
        }
        return createUserBean;
    }

    public static User mapToUserFromUserBeanToCreate(UserBeanToCreate userBeanToCreate) {
        User user = new User();
        if (userBeanToCreate != null) {
            user.setEmail(userBeanToCreate.getEmail());
            user.setPass(userBeanToCreate.getPass());
            user.setRepeatedPass(userBeanToCreate.getRepeatedPass());
            user.setKeyword(userBeanToCreate.getKeyWord());
            user.setTelephoneNumber(userBeanToCreate.getTelephoneNumber());
            user.setCountry(userBeanToCreate.getCountry());
        }
        return user;
    }

    public static UserBeanResponse mapToUserBeanResponseFromUser(User user) {
        UserBeanResponse response = new UserBeanResponse();
        response.setUserToCreate(user);
        return response;
    }

    public static UserBeanResponse mapToUserBeanResponseFromUserBeanToCreate(UserBeanToCreate user) {
        UserBeanResponse response = new UserBeanResponse();
     //   response.setUserToCreate(user);
        response.setMessage("You have successfully registered");
        return response;
    }

//    public static UserBeanResponse mapToUserBeanResponseFromUser(User user) {
//        UserBeanResponse response = new UserBeanResponse();
//        response.setUserToCreate(mapToUserBeanToCreateFromUser(user));
//        response.setMessage("You have successfully registered");
//        return response;
//    }
//
//    public static User mapToUserFromUserBeanResponse(UserBeanResponse response){
//        User user = new User();
//
//        // TODO:
//
//        return user;
//    }
}
