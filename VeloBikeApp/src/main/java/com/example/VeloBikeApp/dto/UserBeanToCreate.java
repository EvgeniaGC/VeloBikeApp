package com.example.VeloBikeApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBeanToCreate {
    private String email;
    private String pass;
    private String repeatedPass;
    private String keyWord;
    private String telephoneNumber;
    private String country;
}
