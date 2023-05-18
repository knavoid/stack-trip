package com.ssafy.enjoytrip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private int userCode;

    private String email;

    private String password;

    private String name;

}
