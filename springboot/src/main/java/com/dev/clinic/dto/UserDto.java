package com.dev.clinic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String lastName;
    private String firstName;
    private String phone;
    private String sex;
    private String username;
    private String avatar;

}

