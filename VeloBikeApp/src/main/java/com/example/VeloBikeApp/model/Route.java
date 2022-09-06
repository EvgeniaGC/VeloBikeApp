package com.example.VeloBikeApp.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "route_table")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_route")
    private Integer idRoute;

    @Column(name = "id_user")
    private Integer userId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start")
    private String startPoint;

    @Column(name = "end")
    private String endPoint;

    @Column(name = "distance")
    private Double distance; // km

    @Column(name = "hour")
    private Integer timeHour;

    @Column(name = "minute")
    private Integer timeMinute;

    @Column(name = "kcal")
    private Double kcal;

    @Column(name = "saved_fuel")
    private Double savedFuel;

//    @OneToOne
//    @Column(name = "result")
//    private Result result;

}

