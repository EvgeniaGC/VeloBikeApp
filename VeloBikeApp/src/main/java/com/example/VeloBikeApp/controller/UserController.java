package com.example.VeloBikeApp.controller;

import com.example.VeloBikeApp.dto.UserBeanResponse;
import com.example.VeloBikeApp.dto.UserBeanToCreate;
import com.example.VeloBikeApp.model.Route;
import com.example.VeloBikeApp.model.User;
import com.example.VeloBikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/index")
    public String getWelcomePage(Model model) {
        return "welcome";
    }

    @GetMapping("/registerUser")
    public String addUserPage(User userToCreate) {
        return "signin";
    }


    @PostMapping("/signin")
    public String signInUser(@Valid User userToCreate,
                             BindingResult result,
                             Model model) {
        UserBeanResponse response = service.regUser(userToCreate);
        String message = response.getMessage();
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "regResponse";
    }

    @GetMapping("/logInUser")
    public String logInUserPage(User userToLogIn) {
        return "login";
    }

    @PostMapping("/login")
    public String enterUser(@Valid User enteredUser,
                            BindingResult result,
                            Model model) {
        UserBeanResponse response = service.logIn(enteredUser);
        String message = response.getMessage();
        if (message != null) {
            model.addAttribute("errorMessage", message);
            return "login";
        }
        model.addAttribute("user", service.getUserByEmail(response.getUserToCreate().getEmail()));
        return "mainPage";
    }


    //TODO:
    // @PostMapping("/login")
    //    public String enterUser(@Valid User enteredUser,
    //                            BindingResult result,
//                            Model model) {
//        UserBeanResponse response = service.logIn(enteredUser);
//        String message = response.getMessage();
//        if (message == null) {
//            //    User userFromDB=
//            model.addAttribute("name", service.getUserByEmail(response.getUserToCreate().getEmail()).getName());
//            return "mainPage";
//        } else {
//            model.addAttribute("errorMessage", message);
//            return "errorLogInPage";
//        }
//        }


//    @GetMapping("/editAboutMe")
//    public String editAboutUserPage(@Valid User userToEdit, Model model) {
//        User userByEmail= service.getUserByEmail(userToEdit.getEmail());
//        model.addAttribute("user", userByEmail);
//        return "edit_about_user";
//    }

//    @GetMapping("/editAboutMe")
//    public String editAboutUserPage(User user) {
//        return "editMe";
//    }


    @GetMapping("/editAboutMe/{email}")
    public String editAboutUserPage(@PathVariable String email, Model model) {
        User user = service.getUserByEmail(email);
        model.addAttribute("user", user);
        return "editMe";
    }

    @GetMapping("/addNewRoute")
    public String addRoutePage(Route routeToAdd) {
        return "addRoute";
    }


//TODO:
//    @GetMapping("/editAboutMe")
//    public String editAboutUser(User userToEdit){
//        return "edit_about_user";
//    }
//    @PostMapping("/edit_about_user")
//    public String enterAboutUser(@Valid User editUser,
//                                BindingResult result,
//                                Model model){
//        return "editMe";
//    }
//    @GetMapping("/addNewRoute")
//    public String addRoute(Route routeToAdd){
//        return "add_route";
//    }
//    @PostMapping("/add_route")
//    public String enterRoute(@Valid Route route,
//                                BindingResult result,
//                                Model model){
//        return "addRoute";
//    }


//    @PostMapping("/registerUser")
//    public UserBeanResponse registerUser(@RequestBody UserBeanToCreate user) {
//        return service.registerUser(user);
//    }

//    @GetMapping("/getUser/{id}")
//    public User getUserById(@PathVariable(name = "id") Integer id) {
//        return service.getUserById(id);
//    }
//
//    @DeleteMapping("/deleteUser/{id}")
//    public void deleteUser(@PathVariable(name = "id") Integer id) {
//        service.deleteUser(id);
//    }
//
//    @PutMapping("/updateUser")
//    public User updateUSer(@RequestBody User user) {
//        return service.updateUser(user);
//    }
}
