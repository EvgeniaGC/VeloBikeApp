package com.example.VeloBikeApp.service;

import com.example.VeloBikeApp.dto.UserBeanResponse;
import com.example.VeloBikeApp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserById (Integer id);

    User getUserByEmail (String email);

    List<User> getAllUser();

    UserBeanResponse regUser(User userToCreate);

//    UserBeanResponse registerUser(UserBeanToCreate user);

    User updateUser (User user);

    void deleteUser (Integer id);

    UserBeanResponse logIn (User userToLogIn);


}
