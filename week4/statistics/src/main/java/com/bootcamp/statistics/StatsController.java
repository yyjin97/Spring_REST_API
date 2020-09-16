package com.bootcamp.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.HashMap;

@RestController
@RequestMapping("/login/*")
public class StatsController {

    @Autowired
    private StatsRepository statsRepository;

    @GetMapping(value = "/{yearMonth}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> getDayStats(@PathVariable("yearMonth") String yearMonth, String day) {
        HashMap hashMap = new HashMap();
        int cnt = 0;

        ResponseEntity<HashMap<String, Object>> result = null;

        if((day != null && Integer.parseInt(day) < 0) || Integer.parseInt(yearMonth) < 0) {
            hashMap.put("returnCode", 400);
            hashMap.put("returnMessage", "Invalid Params");
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMap);
            return result;
        }
        else if(day == null) {
            cnt = statsRepository.getCountByDate(yearMonth);
        }
        else {
            cnt = statsRepository.getCountByDate(yearMonth + day);
            hashMap.put("day", day);
        }
        hashMap.put("totCnt", cnt);
        hashMap.put("year", yearMonth);
        hashMap.put("returnCode", 200);
        hashMap.put("returnMessage", "success");

        result = ResponseEntity.status(HttpStatus.OK).body(hashMap);
        return result;
    }

    @GetMapping(value = "/{organization}/{yearMonth}")
    public ResponseEntity<HashMap<String, Object>> getOrganCnt(@PathVariable("organization") String organization, @PathVariable("yearMonth") String yearMonth) {
        HashMap hashMap = new HashMap();
        int cnt = 0;

        ResponseEntity<HashMap<String, Object>> result = null;

        if(Integer.parseInt(yearMonth) < 0) {
            hashMap.put("returnCode", 400);
            hashMap.put("returnMessage", "Invalid Params");
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hashMap);
            return result;
        }

        cnt = statsRepository.getOrganCntByDate(organization, yearMonth);
        hashMap.put("totCnt", cnt);
        hashMap.put("year", yearMonth);
        hashMap.put("returnCode", 200);
        hashMap.put("returnMessage", "success");

        result = ResponseEntity.status(HttpStatus.OK).body(hashMap);
        return result;
    }

}
