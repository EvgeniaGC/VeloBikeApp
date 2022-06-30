package com.example.VeloBikeApp.service.impl;


import com.example.VeloBikeApp.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

//    @Autowired
//    private RouteRepository repository;
//
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
//    public Route createRoute(Route route) {
//        return repository.save(route);
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
