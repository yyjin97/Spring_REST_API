package com.bootcamp.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class StatsController {

    @Autowired
    private StatsRepository statsRepository;

    @GetMapping(value = "/sqlYearStatistic", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getYearStats(String year) {
        HashMap hashMap = new HashMap();

        ResponseEntity<HashMap<String, Object>> result = null;

        if(Integer.parseInt(year) < 0) {
            hashMap.put("returnCode", 400);
            hashMap.put("returnMessage", "Invalid Params");
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMap);
        }
        else {
            int cnt = statsRepository.getCountByYear(year);
            hashMap.put("totCnt", cnt);
            hashMap.put("year", year);
            hashMap.put("returnCode", 200);
            hashMap.put("returnMessage", "success");

            result = ResponseEntity.status(HttpStatus.OK).body(hashMap);
        }

        return result;
    }
}
