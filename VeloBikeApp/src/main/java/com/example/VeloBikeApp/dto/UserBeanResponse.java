package com.example.VeloBikeApp.dto;

import com.example.VeloBikeApp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserBeanResponse {
    //   private UserBeanToCreate userToCreate;
    private User userToCreate;
    private String message;
}
