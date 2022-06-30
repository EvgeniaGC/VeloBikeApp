package com.example.VeloBikeApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.crypto.Data;

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

//    @Column(name = "data")
//    private Data data;

    @Column(name = "start")
    private String startPoint;

    @Column(name = "finish")
    private String finishPoint;

    @Column(name = "distance")
    private double distance; // km

    @Column(name = "hour")
    private int timeHour;

    @Column(name = "minute")
    private int timeMinute;

//    @OneToOne
//    @Column(name = "result")
//    private Result result;

}

