package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.entity.Interview;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class ImportInterviewExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelExportUtils.class);

    private static final Integer ID_COLUM = 0;
    private static final Integer CONTENT_COLUM = 10;
    private static final Integer RESULT_COLUM = 11;

    public static Iterator<Row> readExcelMultipartFile(MultipartFile multipartFile) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
            return workbook.getSheetAt(0).iterator();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Interview importInterviewFromRow(Row row) {
        try {
            Interview interview = new Interview();

            interview.setId((int) row.getCell(ID_COLUM).getNumericCellValue());
            interview.setContent(row.getCell(CONTENT_COLUM).getStringCellValue());
            interview.setResult(row.getCell(RESULT_COLUM).getStringCellValue());

            return interview;
        } catch (Exception e) {
            throw e;
        }
    }
}
