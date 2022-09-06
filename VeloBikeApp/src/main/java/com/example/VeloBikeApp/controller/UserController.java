package com.example.VeloBikeApp.controller;

import com.example.VeloBikeApp.dto.RouteBean;
import com.example.VeloBikeApp.dto.RouteResponse;
import com.example.VeloBikeApp.dto.UserBeanResponse;
import com.example.VeloBikeApp.model.Route;
import com.example.VeloBikeApp.model.User;
import com.example.VeloBikeApp.service.RouteService;
import com.example.VeloBikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    @GetMapping("/")
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
        UserBeanResponse response = userService.regUser(userToCreate);
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
        UserBeanResponse response = userService.logIn(enteredUser);
        String message = response.getMessage();
        if (message != null) {
            model.addAttribute("errorMessage", message);
            return "login";
        }
        User user = userService.getUserByEmail(response.getUserToCreate().getEmail());
        Double allDistance = userService.findAllDistance(user);
        Double kcal = roundDoubleBy2(45.0 * user.getWeight() / 10 * allDistance / 10);
        Double savedFuel = roundDoubleBy2(allDistance / 100 * 7);
        model.addAttribute("user", user);
        model.addAttribute("allDistance", allDistance);
        model.addAttribute("kcal", kcal);
        model.addAttribute("savedFuel", savedFuel);
        return "mainPage";
    }

    @GetMapping("/editAboutMe/{email}")
    public String editAboutUserPage(@PathVariable String email, Model model) {
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "editMe";
    }

    @PostMapping("/editMe/{userId}")
    public String enterAboutUser(@PathVariable(name = "userId") Integer userId,
                                 User user,
                                 Model model) {
        user.setId(userId);
        UserBeanResponse response = userService.editMe(user);
        String message = response.getMessage();
        if (message != null) {
            model.addAttribute("message", message);
            return "editMe";
        } else {
            model.addAttribute("message", "Your changes doesn't save!!! Try again.");
            return "editMe";
        }
    }

    @GetMapping("/editSettings/{email}")
    public String editSettingsPage(@PathVariable String email, Model model) {
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "editSet";
    }

    @PostMapping("/editSet/{userId}")
    public String edirSettings(@PathVariable(name = "userId") Integer userId,
                               User user,
                               Model model) {
        user.setId(userId);
        UserBeanResponse response = userService.editSet(user);
        String message = response.getMessage();
        if (message != null) {
            model.addAttribute("message", message);
            return "editSet";
        } else {
            model.addAttribute("message", "Your changes doesn't save!!! Try again.");
            return "editSet";
        }
    }

    @GetMapping("/addNewRoute/{email}")
    public String addRoutePage(@PathVariable String email, Model model) {
        User user = userService.getUserByEmail(email);
        RouteBean route = new RouteBean();
        route.setUserId(user.getId());
        model.addAttribute("route", route);
        model.addAttribute("user", user);
        return "addRoute";
    }

    @PostMapping("/addRoute/{userId}")
    public String addRoute(@PathVariable(name = "userId") Integer userId,
                           RouteBean route,
                           Model model) {

        User user = userService.getUserById(userId);

        RouteResponse response = routeService.addRoute(user, route);

        String message = response.getMessage();
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "addRoute";
    }

    // todo:
//    @GetMapping("/backToMainPage/{userId}")
//    public String goBackToMainPage(Model model,
//                                   @PathVariable(name = "userId") Integer userId) {
//        User userById = service.getUserById(userId);
//        model.addAttribute("user", userById);
//        return "redirect:/login";
//    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUserPage(@PathVariable Integer id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("messageDelete", "Your account had been deleted");
        return "welcome";
    }

    @GetMapping("/openSettings/{email}")
    public String openSettingsPage(@PathVariable String email, Model model) {
        User user = userService.getUserByEmail(email);
        model.addAttribute("user", user);
        return "settings";
    }

    double roundDoubleBy2(Double number) {
        number *= 100;
        Double num = Double.valueOf(Math.round(number));
        return num / 100;
    }
}