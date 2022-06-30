package com.example.VeloBikeApp.repository;

import com.example.VeloBikeApp.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

}
