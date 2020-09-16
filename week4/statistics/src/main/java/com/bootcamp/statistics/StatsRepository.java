package com.bootcamp.statistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface StatsRepository extends Repository<RequestInfoVO, Integer> {

    @Query(value = "SELECT COUNT(ri.requestID) FROM RequestInfoVO ri WHERE ri.createDate LIKE :date%")
    int getCountByDate(String date);

    @Query("SELECT COUNT(ri.requestID) FROM RequestInfoVO ri, UserVO user WHERE ri.userID = user.userID AND ri.createDate LIKE :date% AND user.team = :team")
    int getOrganCntByDate(String team, String date);

    @Query("SELECT ri FROM RequestInfoVO ri WHERE ri.createDate LIKE :date%")
    List<RequestInfoVO> getListByDate(String date);
}
