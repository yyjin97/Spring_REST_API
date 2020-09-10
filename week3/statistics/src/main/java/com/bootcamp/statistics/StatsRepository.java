package com.bootcamp.statistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface StatsRepository extends Repository<RequestInfoVO, Integer> {

    @Query(value = "SELECT COUNT(ri.requestID) FROM RequestInfoVO ri  WHERE ri.createDate LIKE :year%")
    int getCountByYear(String year);
}
