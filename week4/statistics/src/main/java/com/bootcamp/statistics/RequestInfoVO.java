package com.bootcamp.statistics;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "requestinfo")
@Getter @Setter
public class RequestInfoVO {

    @Id
    private int requestID;

    @Column(name="requestcode")
    private String requestCode;

    @Column(name = "user_id")
    private String userID;

    @Column(name="createdate")
    private String createDate;

}
