package com.example.VeloBikeApp.service.impl;


import com.example.VeloBikeApp.model.Route;
import com.example.VeloBikeApp.repository.RouteRepository;
import com.example.VeloBikeApp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository repository;

    @Override
    public Route createRoute(Route route) {
        Route routeToSave = new Route();
        routeToSave.setStartPoint(route.getStartPoint());
        routeToSave.setFinishPoint(route.getFinishPoint());
                routeToSave.setDistance(route.getDistance());
                routeToSave.setTimeHour(route.getTimeHour());
//                routeToSave.

        return repository.save(route);
    }

//    @Override
//    public Route getRouteById(Integer idRoute) {
//        return repository.findById(idRoute).orElse(null);
//    }
//
//    @Override
//    public List<Route> getAllRoute() {
//        return repository.findAll();
//    }
//
//    @Override
//    public Route updateRoute(Route route) {
//        repository.delete(getRouteById(route.getIdRoute()));
//        return repository.save(route);
//    }
//
//    @Override
//    public void deleteRoute(Integer idRoute) {
//        Route route = getRouteById(idRoute);
//        repository.delete(route);
//    }
}
