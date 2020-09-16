package com.bootcamp.statistics;

import javax.servlet.http.HttpServletResponse;

public interface ExcelService {

    void getExcelDown(HttpServletResponse response, String date);
}
