package framgia.co.edu.ftrr.util;


import framgia.co.edu.ftrr.dto.request.StepDTO;
import framgia.co.edu.ftrr.entity.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(StepUtils.class);

    public static StepDTO stepToStepDTO(Step step) {
        try {
            StepDTO stepDTO = new StepDTO();
            BeanUtils.copyProperties(step, stepDTO);
            return stepDTO;
        } catch (Exception e) {
            logger.error("Error in stepToStepDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<StepDTO> listStepToListStepDTO(List<Step> steps) {
        try {
            return steps == null ? Collections.emptyList() :
                    steps.stream().map(step -> stepToStepDTO(step)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listStepToListStepDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static Step stepDTOToStep(StepDTO stepDTO) {
        try {
            Step step = new Step();
            BeanUtils.copyProperties(stepDTO, step);
            return step;
        } catch (Exception e) {
            logger.error("Error in stepDTOToStep: " + e.getMessage());
            return null;
        }
    }

    public static List<Step> listStepDTOToListStep(List<StepDTO> stepDTOs) {
        try {

            return stepDTOs == null ? Collections.emptyList() :
                    stepDTOs.stream().map(stepDTO -> stepDTOToStep(stepDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listStepDTOToListStep: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
