package com.example.VeloBikeApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;

    @Transient
    @Column(name = "repeatedPass")
    private String repeatedPass;

    @Column(name = "keyWord")
    private String keyWord;

    @Column(name = "telephoneNumber")
    private String telephoneNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "male")
    private String male;

    @Column(name = "level_activity")
    private String levelOfActivity;

    @Column(name = "weight")
    private int weight; // kg

    @Column(name = "height")
    private int height; // sm

    @Column(name = "imt")
    private double imt; // imt = weight(kg) / (height(m)^2)

    @OneToMany
    @Column(name = "route")
    private List<Route> listOfRout;


    // TODO: delete this constructor ???
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
