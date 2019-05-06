package framgia.co.edu.ftrr.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import framgia.co.edu.ftrr.dto.request.StepDTO;
import framgia.co.edu.ftrr.repository.StepRepository;
import framgia.co.edu.ftrr.service.StepService;
import framgia.co.edu.ftrr.util.StepUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StepServiceImpl implements StepService {
    private static final Logger logger = LoggerFactory.getLogger(StepServiceImpl.class);

    @Autowired
    StepRepository stepRepository;

    @Override
    public StepDTO saveStep(StepDTO step) {
        try {
            return StepUtils.stepToStepDTO(stepRepository.save(StepUtils.stepDTOToStep(step)));
        } catch (Exception e) {
            logger.error("Error in saveStep" + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<StepDTO> getListStep() {
        try {
            return StepUtils.listStepToListStepDTO(stepRepository.findAll());
        } catch (Exception e) {
            logger.error("Error in getListStep" + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<StepDTO> getListStepByIds(String jsonIds) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Integer> ids = mapper.readValue(jsonIds, List.class);

            return StepUtils.listStepToListStepDTO(stepRepository.getListByIds(ids));
        } catch (Exception e) {
            logger.error("Error in getListStepByIds" + e.getMessage());
            return Collections.emptyList();
        }
    }
}
