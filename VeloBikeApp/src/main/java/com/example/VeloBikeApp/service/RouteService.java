package com.example.VeloBikeApp.service;

import com.example.VeloBikeApp.dto.RouteBean;
import com.example.VeloBikeApp.dto.RouteResponse;
import com.example.VeloBikeApp.model.Route;
import com.example.VeloBikeApp.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {

    RouteResponse addRoute(User user, RouteBean route);

    Route getRouteById(Integer id);




//    Route getRouteById(Integer idRoute);
//
//    List<Route> getAllRoute();
//

//
//    Route updateRoute(Route route);
//
//    void deleteRoute(Integer idRoute);

}
