package framgia.co.edu.ftrr.util;


import framgia.co.edu.ftrr.dto.request.FinalResultDTO;
import framgia.co.edu.ftrr.entity.FinalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FinalResultUtils {

    private static final Logger logger = LoggerFactory.getLogger(FinalResultUtils.class);

    public static FinalResultDTO finalResultToFinalResultDTO(FinalResult finalResult) {
        try {
            FinalResultDTO finalResultDTO = new FinalResultDTO();
            BeanUtils.copyProperties(finalResult, finalResultDTO);
            return finalResultDTO;
        } catch (Exception e) {
            logger.error("Error in finalResultToFinalResultDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<FinalResultDTO> listFinalResultToListFinalResultDTO(List<FinalResult> finalResults) {
        try {
            return finalResults == null ? Collections.emptyList() :
                    finalResults.stream().map(finalResult -> finalResultToFinalResultDTO(finalResult)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listFinalResultToListFinalResultDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static FinalResult finalResultDTOToFinalResult(FinalResultDTO finalResultDTO) {
        try {
            FinalResult finalResult = new FinalResult();
            BeanUtils.copyProperties(finalResultDTO, finalResult);
            return finalResult;
        } catch (Exception e) {
            logger.error("Error in finalResultDTOToFinalResult: " + e.getMessage());
            return null;
        }
    }

    public static List<FinalResult> listFinalResultDTOToListFinalResult(List<FinalResultDTO> finalResultDTOs) {
        try {

            return finalResultDTOs == null ? Collections.emptyList() :
                    finalResultDTOs.stream().map(finalResultDTO -> finalResultDTOToFinalResult(finalResultDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listFinalResultDTOToListFinalResult: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
