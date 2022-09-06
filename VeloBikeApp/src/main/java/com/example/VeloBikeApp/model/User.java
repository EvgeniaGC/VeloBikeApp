package com.example.VeloBikeApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

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
    private Integer age;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;

    @Transient
    @Column(name = "repeatedPass")
    private String repeatedPass;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "telephoneNumber")
    private String telephoneNumber;

    @Column(name = "country")
    private String country;

    @Column(name = "male")
    private String male;

    @Column(name = "level_activity")
    private String levelOfActivity;

    @Column(name = "weight")
    private Integer weight; // kg

    @Column(name = "height")
    private Integer height; // sm

    @Column(name = "imt")
    private Double imt; // imt = weight(kg) / (height(m)^2)

    @OneToMany
 //   @JoinColumn(referencedColumnName = "id_route")
    @Column(name = "route")
    private List<Route> listOfRout;

}
