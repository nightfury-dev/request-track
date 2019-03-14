package framgia.co.edu.ftrr.util;

import framgia.co.edu.ftrr.common.RequestStatus;
import framgia.co.edu.ftrr.dto.request.RequestDTO;
import framgia.co.edu.ftrr.dto.request.ResultTrainingDTO;
import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import framgia.co.edu.ftrr.entity.Level;
import framgia.co.edu.ftrr.service.LevelService;
import framgia.co.edu.ftrr.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    LevelService levelService;
    @Autowired
    UserService userService;

    @Value("${error.division.blank}")
    private String divisionBlankError;
    @Value("${error.language.blank}")
    private String languageBlankError;
    @Value("${error.quantity.numeric}")
    private String quantityNumericError;
    @Value("${error.deadline.date_format}")
    private String deadlineFormatError;
    @Value("${error.format}")
    private String formatError;
    @Value("${error.notfound}")
    private String notFoundError;

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

    public List<ResultTrainingDTO> listResultTraingFromExcel(MultipartFile multipartFile, String uploadRootPath) {
        try {
            List<ResultTrainingDTO> resultTrainings = new ArrayList<>();
            Iterator<Row> iterator = getRows(multipartFile, uploadRootPath);
            iterator.next();
            iterator.next(); // Ignore title of file Excel
            while (iterator.hasNext()) {
                resultTrainings.add(mapRowExcelToTrainee(iterator.next()));
            }

            return resultTrainings;
        } catch (Exception e) {
            logger.error("Error in listResultTraingFromExcel: " + e.getMessage());
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
            request.setStatus(RequestStatus.WAITING.getValue());

            return request;
        } catch (Exception e) {
            logger.error("Error in mapRowExcelToRequest: " + e.getMessage());
            return null;
        }
    }

    private ResultTrainingDTO mapRowExcelToTrainee(Row row) {
        try {
            ResultTrainingDTO resultTraining = new ResultTrainingDTO();
            TraineeDTO trainee = new TraineeDTO();

            trainee.setName(row.getCell(0).getStringCellValue());
            trainee.setEmail(row.getCell(1).getStringCellValue());
            trainee.setLanguage(row.getCell(2).getStringCellValue());
            trainee.setOffice(row.getCell(3).getStringCellValue());
            trainee.setTrainerName(row.getCell(4).getStringCellValue());
            trainee.setStartedTime(row.getCell(5).getDateCellValue());
            trainee.setLevelName(row.getCell(6).getStringCellValue());

            resultTraining.setPoint((int) row.getCell(7).getNumericCellValue());
            resultTraining.setResult(row.getCell(8).getStringCellValue());
            resultTraining.setTrainee(TraineeUtils.traineeDTOToTrainee(trainee));
            return resultTraining;
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

    public JSONObject checkImportTrainee(MultipartFile multipartFile, String uploadRootPath) {
        JSONObject results = new JSONObject();
        Map<Integer, JSONObject> mapError = new HashMap<>();
        int rowNum = 1; // Row number without title's sheet
        Map<String, Level> levelMap = levelService.getMapLevel();
        try {
            Iterator<Row> iterator = getRows(multipartFile, uploadRootPath);

            iterator.next(); // Ignore title of file Excel
            iterator.next();
            while (iterator.hasNext()) {
                JSONObject errors = checkRowTrainee(iterator.next(), levelMap);
                if (!errors.isEmpty()) {
                    JSONObject cols = new JSONObject();// All error in row across each column
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
            logger.error("Error in checkImportTrainee: " + e.getMessage());
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
            if (isNull(row, cell++))
                errors.put(cell - 1, divisionBlankError);

            // Check field language
            if (isNull(row, cell++))
                errors.put(cell - 1, languageBlankError);

            // Check field quantity
            if (!isNumeric(row, cell++))
                errors.put(cell - 1, quantityNumericError);

            // Check field deadline
            if (!isValidDate(row, cell++))
                errors.put(cell - 1, deadlineFormatError);

            return errors;
        } catch (Exception e) {
            logger.error("Error in checkRowRequestTrainees at row " + row.getRowNum() +
                    ", cell " + cell + ": " + e.getMessage());

            errors.put(cell, "Exception: " + e.getMessage());
            return errors;
        }
    }

    private JSONObject checkRowTrainee(Row row, Map<String, Level> levelMap) {
        JSONObject errors = new JSONObject();
        int cell = 0;
        String language = "";
        String trainerName;
        try {
            // Check field name
            if (isNull(row, cell++))
                errors.put(cell - 1, formatError);

            // Check field email
            if (isNull(row, cell++))
                errors.put(cell - 1, formatError);

            // Check field language
            if (isNull(row, cell++))
                errors.put(cell - 1, formatError);
            else {
                language = row.getCell(cell - 1).getStringCellValue();
            }

            // Check field office
            if (isNull(row, cell++))
                errors.put(cell - 1, formatError);

            // Check field trainer name
            if (isNull(row, cell++))
                errors.put(cell - 1, formatError);
            {
                if (StringUtils.isNotBlank(language)) {
                    trainerName = row.getCell(cell - 1).getStringCellValue();
                    if (!userService.existsTrainer(trainerName, language)) {
                        errors.put(cell - 1, notFoundError);
                    }
                }
            }


            // Check field time start
            if (!isValidDate(row, cell++))
                errors.put(cell - 1, formatError);

            // Check field level
            if (isNull(row, cell++))
                errors.put(cell - 1, formatError);
            else {
                if (!levelMap.containsKey(row.getCell(cell - 1).getStringCellValue())) {
                    errors.put(cell - 1, notFoundError);
                }
            }

            // Check field point
            if (!isNumeric(row, cell++))
                errors.put(cell - 1, formatError);

            // Check field trainer review
            if (isNull(row, cell++))
                errors.put(cell - 1, formatError);

            return errors;
        } catch (Exception e) {
            logger.error("Error in checkRowTrainee at row " + row.getRowNum() +
                    ", cell " + cell + ": " + e.getMessage());
            errors.put(cell, "Exception: " + e.getMessage());
            return errors;
        }
    }

    private boolean isNull(Row row, int cell) {
        try {
            row.getCell(cell).getStringCellValue();
            return false;
        } catch (Exception e) {
            return true;
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