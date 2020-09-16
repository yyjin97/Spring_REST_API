package com.bootcamp.statistics;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Getter @Setter
public class UserVO {

    @Id
    private int userID;

    private String username;

    private String team;
}
