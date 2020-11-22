package com.zjl.pgsql.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Users {

    private Integer id;
    private String username;
    private String name;
    private Date reportTime;
}
