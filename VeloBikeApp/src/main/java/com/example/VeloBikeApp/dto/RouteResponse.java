package com.example.VeloBikeApp.dto;

import com.example.VeloBikeApp.model.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteResponse {
    private Route route;
    private String message;
}
