package com.example.VeloBikeApp.controller;

import com.example.VeloBikeApp.model.Route;
import com.example.VeloBikeApp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RouteController {

    @Autowired
    private RouteService service;

    @PostMapping("/createRoute")
    public Route createRoute(@RequestBody Route route) {
        return service.createRoute(route);
    }



//    @GetMapping("/getRoute/{id}")
//    public Route getRouteById(@PathVariable(name = "idRoute") Integer idRoute) {
//        return service.getRouteById(idRoute);
//    }
//
//    @DeleteMapping("/deleteRoute/{idRoute}")
//    public void deleteRoute(@PathVariable(name = "idRoute") Integer idRoute) {
//        service.deleteRoute(idRoute);
//    }
//
//    @PutMapping("/updateRoute")
//    public Route updateRoute(@RequestBody Route route) {
//        return service.updateRoute(route);
//    }
}
