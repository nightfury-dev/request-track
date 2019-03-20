package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.common.RequestStatus;
import framgia.co.edu.ftrr.dto.response.DivStatistic;
import framgia.co.edu.ftrr.service.DashboardService;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ExcelExportUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExcelExportUtils.class);

    private static final String TEMPLATE_FILE = "ExcelsTemplate/ExportTemplate.xlsx";
    private static final Integer DIVISION_COLUM = 0;
    private static final Integer REQUEST_TIME_COLUM = 1;
    private static final Integer OFFICE_COLUM = 2;
    private static final Integer SKILL_COLUM = 3;
    private static final Integer QUANTITY_COLUM = 4;
    private static final Integer STATUS_COLUM = 5;
    private static final Integer RESULT_COLUM = 6;
    private static final Integer INTERVIEW_COLUM = 7;
    private static final Integer TRAINEE_JOIN_DIV_COLUM = 8;

    @Autowired
    DashboardService dashboardService;

    public ByteArrayInputStream exportExcel(Date fromDate) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XSSFWorkbook xssfWorkbook = readTemplate();
            processExcel(xssfWorkbook, dashboardService.getStatisticDataFollowDiv(fromDate));
            xssfWorkbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            logger.error("Error in exportExcel:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void processExcel(XSSFWorkbook xssfWorkbook, Map<String, List<DivStatistic>> data) {
        XSSFSheet rows = xssfWorkbook.getSheetAt(0);
        Integer index = 1;
        for (Map.Entry<String, List<DivStatistic>> entry : data.entrySet()) {
            if (entry.getValue().size() == 0) continue;

            Row row = rows.createRow(index);
            row.createCell(DIVISION_COLUM, CellType.STRING).setCellValue(entry.getKey());
            index++;

            List<DivStatistic> divStatistics = entry.getValue();

            for (DivStatistic divStatistic : divStatistics) {
                row = rows.createRow(index);
                row.createCell(REQUEST_TIME_COLUM).setCellValue(divStatistic.getRequestTime().toString());
                row.createCell(SKILL_COLUM).setCellValue(divStatistic.getSkill());
                row.createCell(QUANTITY_COLUM).setCellValue(divStatistic.getQuantity());
                row.createCell(STATUS_COLUM).setCellValue(divStatistic.getStatus());
                row.createCell(RESULT_COLUM).setCellValue(divStatistic.getResult());
                row.createCell(INTERVIEW_COLUM).setCellValue(divStatistic.getInterview());
                index++;
                if (divStatistic.getStatus() == RequestStatus.DONE.getValue()) {
                    for (String traineeJoinDiv : divStatistic.getTraineesJoinDiv()) {
                        row = rows.createRow(index);
                        row.createCell(TRAINEE_JOIN_DIV_COLUM).setCellValue(traineeJoinDiv);
                        index++;
                    }
                }
            }
        }

    }

    private XSSFWorkbook readTemplate() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(TEMPLATE_FILE).getFile());
            FileInputStream inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            return workbook;
        } catch (IOException e) {
            logger.error("Error in readTemplate:" + e.getMessage());
            return null;
        }
    }
}
