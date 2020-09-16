package com.bootcamp.statistics;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j2
public class ExcelController {

    @Autowired
    private ExcelService service;

    @GetMapping("/")
    public String get() {
        return "excel";
    }

    @RequestMapping(value = "/excelDown", method = RequestMethod.POST)
    public void ExcelDown(HttpServletResponse response, String date){
        log.info("@@@@@@@@@@@@@@@ExcelDown START@@@@@@@@@@@@@@@");

        service.getExcelDown(response, date);

        log.info("@@@@@@@@@@@@@@@ExcelDown END@@@@@@@@@@@@@@@");

    }

}
