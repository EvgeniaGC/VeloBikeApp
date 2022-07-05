package com.example.VeloBikeApp.service.impl;

import com.example.VeloBikeApp.dto.UserBeanResponse;
import com.example.VeloBikeApp.model.User;
import com.example.VeloBikeApp.repository.UserRepository;
import com.example.VeloBikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.example.VeloBikeApp.mapper.UserMapper.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public User getUserById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }


    // TODO: _______________________________
    @Override
    public UserBeanResponse regUser(User userToCreate) {
        UserBeanResponse response;
        User userFromDB = repository.findUserByEmail(userToCreate.getEmail());

        User savedUser = null;
        if (userFromDB == null) {
            if (userToCreate.getPass().equals(userToCreate.getRepeatedPass())) {
                // UserBeanToCreate userToSave = mapToUserBeanToCreateFromUser(userFromDB);
                User userToSave = new User();
                userToSave.setEmail(userToCreate.getEmail());
                userToSave.setPass(userToCreate.getPass());
                userToSave.setRepeatedPass(userToCreate.getRepeatedPass());
                userToSave.setKeyWord(userToCreate.getKeyWord());
                userToSave.setTelephoneNumber(userToCreate.getTelephoneNumber());
                userToSave.setCountry(userToCreate.getCountry());

                savedUser = repository.save(userToSave);

                response = mapToUserBeanResponseFromUser(savedUser);
                response.setMessage("You have successfully registered");
            } else {
                response = new UserBeanResponse(null, "Check your password. 'Password' and 'repeated password' don't match.");
            }
        } else {
            response = new UserBeanResponse(null, "User with such email has already existed.");
        }
        //User user = mapToUserFromUserBeanToCreate(userToCreate);
        return response;
    }

    @Override
    public UserBeanResponse logIn(User userToLogIn) {
        UserBeanResponse response = new UserBeanResponse();
        User userFromDB = repository.findUserByEmail(userToLogIn.getEmail());
        if (userFromDB != null) {
            if (userFromDB.getPass().equals(userToLogIn.getPass())) {
                response.setUserToCreate(userFromDB);
            } else {
                response.setMessage("Wrong password.");
            }
        } else {
            response.setMessage("User with such email doesn't exist.");
        }
        return response;
    }

    @Override
    public UserBeanResponse editMe(User userToEdit) {
        UserBeanResponse response;
        User userFromDB = repository.findUserByEmail(userToEdit.getEmail()); //byID
        Integer userId = userFromDB.getId();

        deleteUser(userId);

        userFromDB.setName(userToEdit.getName());
        userFromDB.setAge(userToEdit.getAge());
        userFromDB.setCountry(userToEdit.getCountry());
        userFromDB.setMale(userToEdit.getMale());
        userFromDB.setLevelOfActivity(userToEdit.getLevelOfActivity());
        userFromDB.setWeight(userToEdit.getWeight());
        userFromDB.setHeight(userToEdit.getHeight());

        if (userFromDB.getHeight() != 0 && userFromDB.getWeight() != 0) {
            double index = userFromDB.getHeight() / userFromDB.getWeight() / userFromDB.getWeight();
            userFromDB.setImt(index);
        }

        User editedUser = repository.save(userFromDB);

        response = mapToUserBeanResponseFromUser(editedUser);
        response.setMessage("You have successfully edit your data");
        return response;
    }


//    @Override
//    public User createUser(UserBeanToCreate userToCreate) {
//        User user = new User();
//
//
//        return user;
//    }

    @Override
    public User updateUser(User user) {
        // TODO: ?????????????????
        repository.delete(getUserById(user.getId()));
        return repository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        repository.delete(user);
    }

}
