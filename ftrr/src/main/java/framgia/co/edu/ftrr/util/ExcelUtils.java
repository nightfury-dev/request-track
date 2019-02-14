package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.dto.request.RequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    private static File convertMultipartFileToFile(MultipartFile multipartFile, String uploadRootPath) {
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

    public static List<RequestDTO> listRequestFromExcel(MultipartFile multipartFile, String uploadRootPath) {
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

    private static RequestDTO mapRowExcelToRequest(Row row) {
        try {
            RequestDTO request = new RequestDTO();
            request.setDivision(row.getCell(0).getStringCellValue());
            request.setLanguage(row.getCell(1).getStringCellValue());
            request.setQuantity((int) row.getCell(2).getNumericCellValue());
            request.setDeadline(row.getCell(3).getDateCellValue());
            request.setStatus((byte) 0);

            return request;
        } catch (Exception e) {
            logger.error("Error in mapRowExcelToRequest: " + e.getMessage());
            return null;
        }
    }

    public static Map<Integer, List<String>> checkImportRequestTrainees(MultipartFile multipartFile, String uploadRootPath) {
        Map<Integer, List<String>> mapError = new HashMap<>();
        try {
            Iterator<Row> iterator = getRows(multipartFile, uploadRootPath);

            int rowNum = 0;
            iterator.next(); // Ignore title of file Excel
            while (iterator.hasNext()) {
                List<String> errors = checkRowRequestTrainees(iterator.next());
                if (!errors.isEmpty()) {
                    mapError.put(rowNum, errors);
                }
                rowNum++;
            }

            return mapError;
        } catch (Exception e) {
            logger.error("Error in checkImportRequestTrainees: " + e.getMessage());
            mapError.put(0, Arrays.asList("Exception: " + e.getMessage()));
            return mapError;
        }
    }

    private static Iterator<Row> getRows(MultipartFile multipartFile, String uploadRootPath) {
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

    private static List<String> checkRowRequestTrainees(Row row) {
        try {
            List<String> errors = new ArrayList<>();

            // Check field division
            if (!isNotNull(row, 0))
                errors.add("Field division must not blank");

            // Check field language
            if (!isNotNull(row, 1))
                errors.add("Field language must not blank");

            // Check field quantity
            if (!isNumeric(row, 2))
                errors.add("Field quantity must be numeric");

            // Check field deadline
            if (!isValidDate(row, 3))
                errors.add("Field deadline must be date with format: yyyy-MM-dd");

            return errors;
        } catch (Exception e) {
            logger.error("Error in checkRowRequestTrainees: " + e.getMessage());
            return Arrays.asList("Exception: " + e.getMessage());
        }
    }

    private static boolean isNotNull(Row row, int cell) {
        try {
            row.getCell(cell).getStringCellValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isValidDate(Row row, int cell) {
        try {
            row.getCell(cell).getDateCellValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isNumeric(Row row, int cell) {
        try {
            row.getCell(cell).getNumericCellValue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
