package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.dto.response.DivStatistic;
import framgia.co.edu.ftrr.service.DashboardService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Component
public class ExcelExportUtils {
    
    private static final String TEMPLATE_FILE = "ExcelsTemplate/DivStatisticTemplate.xlsx";

    @Autowired
    DashboardService dashboardService;

    public XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public XSSFWorkbook readTemplate() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(TEMPLATE_FILE).getFile());
            // Đọc một file XSL.
            FileInputStream inputStream = new FileInputStream(file);

            // Đối tượng workbook cho file XSL.
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//            makeWorkbook(workbook, dashboardService.getStatisticDataFollowDiv(null));
            return workbook;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private void makeWorkbook(XSSFWorkbook sheets, Map<String, DivStatistic> divStatisticMap) {
        XSSFSheet rows = sheets.getSheetAt(0);
        Iterator<Row> rowIterator = rows.iterator();
        int rowIndex = 0;
        Row row;
        Cell cell;

        divStatisticMap.entrySet().forEach(entry -> {
            mapDivStatistic(rows, entry, rowIndex);
        });
    }

    private void mapDivStatistic(XSSFSheet rows, Map.Entry<String, DivStatistic> divStatistic, Integer index) {
        Row row = rows.createRow(index);
        Cell divisionCell = row.createCell(0, CellType.STRING);
        Cell requestCell = row.createCell(1, CellType.STRING);
        Cell languageCell;
        Cell languageQuantityCell;
        Cell traineeReviewQuantityCell = row.createCell(4, CellType.NUMERIC);
        Cell traineeJoinDivQuantityCell = row.createCell(5, CellType.NUMERIC);
        divisionCell.setCellValue(divStatistic.getKey());

//        requestCell.setCellValue(String.valueOf(divStatistic.getValue().getRequestQuantity().length));
//        traineeReviewQuantityCell.setCellValue(divStatistic.getValue().getTraineeReviews());
//        traineeJoinDivQuantityCell.setCellValue(divStatistic.getValue().getTraineeJoinDiv());
//        for (Map.Entry<String, Long> entry : divStatistic.getValue().getLanguageQuantity().entrySet()) {
//            if (row.getRowNum() != index) {
//                row = rows.createRow(index);
//            }
//            languageCell = row.createCell(2, CellType.STRING);
//            languageCell.setCellValue(entry.getKey());
//            languageQuantityCell = row.createCell(3, CellType.NUMERIC);
//            languageQuantityCell.setCellValue(entry.getValue());
//            index++;
//        }
    }

}
