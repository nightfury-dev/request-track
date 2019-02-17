package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.common.RequestStatus;
import framgia.co.edu.ftrr.dto.request.RequestDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@Component
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    @Value("${error.division.blank}")
    private String divisionBlankError;
    @Value("${error.language.blank}")
    private String languageBlankError;
    @Value("${error.quantity.numeric}")
    private String quantityNumericError;
    @Value("${error.deadline.date_format}")
    private String deadlineFormatError;

    private File convertMultipartFileToFile(MultipartFile multipartFile, String uploadRootPath) {
        try {
            File uploadRootDir = new File(uploadRootPath);

            // Create folder upload if not exist
            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdir();
            }

            File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + multipartFile.getOriginalFilename());
            Files.write(serverFile.toPath(), multipartFile.getBytes());

            return serverFile;
        } catch (Exception e) {
            logger.error("Error in convertMultipartFileToFile: " + e.getMessage());
            return null;
        }
    }

    public List<RequestDTO> listRequestFromExcel(MultipartFile multipartFile, String uploadRootPath) {
        try {
            List<RequestDTO> requests = new ArrayList<>();
            Iterator<Row> iterator = getRows(multipartFile, uploadRootPath);

            iterator.next(); // Ignore title of file Excel
            while (iterator.hasNext()) {
                requests.add(mapRowExcelToRequest(iterator.next()));
            }

            return requests;
        } catch (Exception e) {
            logger.error("Error in listRequestFromExcel: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private RequestDTO mapRowExcelToRequest(Row row) {
        try {
            RequestDTO request = new RequestDTO();
            request.setDivision(row.getCell(0).getStringCellValue());
            request.setLanguage(row.getCell(1).getStringCellValue());
            request.setQuantity((int) row.getCell(2).getNumericCellValue());
            request.setDeadline(row.getCell(3).getDateCellValue());
            request.setStatus(RequestStatus.IN_PROGRESS);

            return request;
        } catch (Exception e) {
            logger.error("Error in mapRowExcelToRequest: " + e.getMessage());
            return null;
        }
    }

    public JSONObject checkImportRequestTrainees(MultipartFile multipartFile, String uploadRootPath) {
        JSONObject results = new JSONObject();
        Map<Integer, JSONObject> mapError = new HashMap<>();
        int rowNum = 0; // Row number without title's sheet
        try {
            Iterator<Row> iterator = getRows(multipartFile, uploadRootPath);

            iterator.next(); // Ignore title of file Excel
            while (iterator.hasNext()) {
                JSONObject errors = checkRowRequestTrainees(iterator.next());
                if (!errors.isEmpty()) {
                    JSONObject cols = new JSONObject(); // All error in row across each column
                    cols.put("cols", errors);
                    mapError.put(rowNum, cols);
                }
                rowNum++;
            }

            JSONObject rows = new JSONObject();// All error in sheet across each row
            // If file import hasn't errors
            if (mapError.isEmpty())
                return null;

            rows.put("rows", mapError);
            results.put("errors", rows);
            return results;
        } catch (Exception e) {
            logger.error("Error in checkImportRequestTrainees: " + e.getMessage());
            Map<Integer, String> mapException = new HashMap<>();
            mapException.put(rowNum, e.getMessage());
            results.put("exception", mapException);
            return results;
        }
    }

    private Iterator<Row> getRows(MultipartFile multipartFile, String uploadRootPath) {
        FileInputStream fis = null;
        Workbook workbook = null;
        try {
            fis = new FileInputStream(convertMultipartFileToFile(multipartFile, uploadRootPath));
            workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            return sheet.iterator();
        } catch (Exception e) {
            logger.error("Error in getRows: " + e.getMessage());
            return Collections.emptyIterator();
        } finally {
            try {
                fis.close();
                workbook.close();
            } catch (IOException e) {
                logger.error("Error when close file: " + e.getMessage());
            }
        }
    }

    private JSONObject checkRowRequestTrainees(Row row) {
        JSONObject errors = new JSONObject();
        int cell = 0;
        try {
            // Check field division
            if (!isNotNull(row, cell++))
                errors.put(cell - 1, divisionBlankError);

            // Check field language
            if (!isNotNull(row, cell++))
                errors.put(cell - 1, languageBlankError);

            // Check field quantity
            if (!isNumeric(row, cell++))
                errors.put(cell - 1, quantityNumericError);

            // Check field deadline
            if (!isValidDate(row, cell++))
                errors.put(cell - 1, deadlineFormatError);

            return errors;
        } catch (Exception e) {
            logger.error("Error in checkRowRequestTrainees at row " +  row.getRowNum() +
                    ", cell " + cell + ": " + e.getMessage());
            errors.put(cell, "Exception: " + e.getMessage());
            return errors;
        }
    }

    private boolean isNotNull(Row row, int cell) {
        try {
            row.getCell(cell).getStringCellValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidDate(Row row, int cell) {
        try {
            row.getCell(cell).getDateCellValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isNumeric(Row row, int cell) {
        try {
            row.getCell(cell).getNumericCellValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
