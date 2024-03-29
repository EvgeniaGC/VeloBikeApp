package com.example.VeloBikeApp.service;

import com.example.VeloBikeApp.dto.UserBeanResponse;
import com.example.VeloBikeApp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getUserById(Integer id);

    User getUserByEmail(String email);

    UserBeanResponse regUser(User userToCreate);

    UserBeanResponse logIn(User userToLogIn);

    UserBeanResponse editMe(User userToEdit);

    UserBeanResponse editSet(User userToEdit);

    void deleteUser(Integer id);

    Double findAllDistance(User user);

    Double findAllKcal(User user);

    List<User> getAllUser();
//    UserBeanResponse registerUser(UserBeanToCreate user);

    User updateUser(User user);


}
