package com.bootcamp.statistics;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@Log4j2
public class ExcelServiceImpl implements ExcelService{

    @Autowired
    private StatsRepository repository;

    @Override
    public void getExcelDown(HttpServletResponse response, String date) {
        List<RequestInfoVO> list = repository.getListByDate(date);

        try{
            //Excel Down 시작
            Workbook workbook = new HSSFWorkbook();
            //시트생성
            Sheet sheet = workbook.createSheet("Request Info");

            //행, 열, 열번호
            Row row = null;
            Cell cell = null;
            int rowNo = 0;

            // 테이블 헤더용 스타일
            CellStyle headStyle = workbook.createCellStyle();

            // 가는 경계선을 가집니다.
            headStyle.setBorderTop(BorderStyle.THIN);
            headStyle.setBorderBottom(BorderStyle.THIN);
            headStyle.setBorderLeft(BorderStyle.THIN);
            headStyle.setBorderRight(BorderStyle.THIN);

            // 배경색은 노란색입니다.
            headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
            headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // 데이터는 가운데 정렬합니다.
            headStyle.setAlignment(HorizontalAlignment.CENTER);

            // 데이터용 경계 스타일 테두리만 지정
            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setBorderTop(BorderStyle.THIN);
            bodyStyle.setBorderBottom(BorderStyle.THIN);
            bodyStyle.setBorderLeft(BorderStyle.THIN);
            bodyStyle.setBorderRight(BorderStyle.THIN);

            // 헤더 생성
            row = sheet.createRow(rowNo++);

            cell = row.createCell(0);
            cell.setCellStyle(headStyle);
            cell.setCellValue("번호");

            cell = row.createCell(1);
            cell.setCellStyle(headStyle);
            cell.setCellValue("코드");

            cell = row.createCell(2);
            cell.setCellStyle(headStyle);
            cell.setCellValue("사용자");

            cell = row.createCell(3);
            cell.setCellStyle(headStyle);
            cell.setCellValue("생성일");

            // 데이터 부분 생성
            for(RequestInfoVO ri : list) {

                row = sheet.createRow(rowNo++);
                cell = row.createCell(0);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(""+ri.getRequestID());
                cell = row.createCell(1);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(""+ri.getRequestCode());
                cell = row.createCell(2);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(""+ri.getUserID());
                cell = row.createCell(3);
                cell.setCellStyle(bodyStyle);
                cell.setCellValue(""+ri.getCreateDate());
            }

            // 컨텐츠 타입과 파일명 지정
            response.setContentType("ms-vnd/excel");
            response.setHeader("Content-Disposition", "attachment;filename=test.xls");

            // 엑셀 출력
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
