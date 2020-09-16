package com.bootcamp.statistics;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.stream.events.StartDocument;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class StatsRepositoryTest {

    @Autowired
    StatsRepository repository;

    @Test
    public void testGetList() {
        repository.getListByDate("2004").forEach(log::info);
    }
}