package com.example.VeloBikeApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteBean {

    private Integer userId;

    private String date;

    private String startPoint;

    private String endPoint;

}
