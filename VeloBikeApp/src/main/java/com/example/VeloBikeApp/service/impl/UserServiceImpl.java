package com.example.VeloBikeApp.service.impl;

import com.example.VeloBikeApp.dto.UserBeanResponse;
import com.example.VeloBikeApp.mapper.UserMapper;
import com.example.VeloBikeApp.model.Route;
import com.example.VeloBikeApp.model.User;
import com.example.VeloBikeApp.repository.UserRepository;
import com.example.VeloBikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

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
//        return repository.findUserByEmail(email)
//                .orElse(null);
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
                userToSave.setKeyword(userToCreate.getKeyword());
                userToSave.setTelephoneNumber(userToCreate.getTelephoneNumber());
                userToSave.setCountry(userToCreate.getCountry());
                userToSave.setWeight(userToCreate.getWeight() == null ? 0 : userToCreate.getWeight());
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
        User userFromDB = repository.findUserByEmail(userToEdit.getEmail().trim());

        userFromDB.setEmail(userToEdit.getEmail());
        userFromDB.setName(userToEdit.getName());
        userFromDB.setAge(userToEdit.getAge());
        userFromDB.setCountry(userToEdit.getCountry());
        userFromDB.setMale(userToEdit.getMale());
        userFromDB.setLevelOfActivity(userToEdit.getLevelOfActivity());
        userFromDB.setWeight(userToEdit.getWeight());
        userFromDB.setHeight(userToEdit.getHeight());
        if (userFromDB.getHeight() != 0 && userFromDB.getWeight() != 0) {
//            DecimalFormat df = new DecimalFormat("#.##");
//            time = Double.valueOf(df.format(time));
            double index = (double) userFromDB.getWeight() / userFromDB.getHeight() / userFromDB.getHeight() * 10000;
//            double indexR = Double.valueOf(new DecimalFormat("0.00").format(index));
            userFromDB.setImt(roundDoubleBy2(index));
        } else {
            userFromDB.setImt(0.0);
        }
        repository.save(userFromDB);
        response = mapToUserBeanResponseFromUser(userToEdit);
        response.setMessage("You have successfully edit your data");
        return response;
    }

    @Override
    public UserBeanResponse editSet(User userToEdit) {
        UserBeanResponse response;
        User userFromDB = repository.findUserByEmail(userToEdit.getEmail().trim());

        userFromDB.setEmail(userToEdit.getEmail());
        userFromDB.setPass(userToEdit.getPass());
        userFromDB.setKeyword(userToEdit.getKeyword());
        userFromDB.setTelephoneNumber(userToEdit.getTelephoneNumber());

        repository.save(userFromDB);
        response = mapToUserBeanResponseFromUser(userToEdit);
        response.setMessage("You have successfully edit your settings");
        return response;
    }

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

    @Override
    public Double findAllDistance(User user) {
        List<Route> listOfRoute = user.getListOfRout();
        Double allDistance = 0.0;
        for (Route route : listOfRoute) {
            allDistance += route.getDistance();
        }
        return allDistance;
    }

    @Override
    public Double findAllKcal(User user) {
        List<Route> listOfRoute = user.getListOfRout();
        Double kcal = 0.0;
        for (Route route : listOfRoute) {
            kcal += route.getKcal();
        }
        return kcal;
    }

    double roundDoubleBy2(Double number) {
        number *= 100;
        Double num = Double.valueOf(Math.round(number));
        return num / 100;
    }
}
