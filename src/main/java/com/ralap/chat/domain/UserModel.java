package com.ralap.chat.domain;

import lombok.Data;

/**
 * UserModel
 * @author: ralap 
 * @date: created at 2017/11/12 11:48
 */
@Data
public class UserModel {

    private int id;
    private String name;
    private String password;
    private int age;
    private String address;
    private String roles;

}
