package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.dto.request.WorkfollowDTO;
import framgia.co.edu.ftrr.entity.Workfollow;
import framgia.co.edu.ftrr.repository.WorkfollowRepository;
import framgia.co.edu.ftrr.service.StepService;
import framgia.co.edu.ftrr.service.WorkfollowService;
import framgia.co.edu.ftrr.util.WorkfollowUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WorkfollowServiceImpl implements WorkfollowService {
    private static final Logger logger = LoggerFactory.getLogger(WorkfollowServiceImpl.class);

    @Autowired
    WorkfollowRepository workfollowRepository;

    @Autowired
    StepService stepService;

    @Override
    public WorkfollowDTO findById(Integer id) {
        try {
            Workfollow workfollow = workfollowRepository.findById(id).get();
            WorkfollowDTO workfollowDTO = WorkfollowUtils.workfollowToWorkfollowDTO(workfollow);

            if (StringUtils.isNotBlank(workfollow.getSteps())) {
                workfollowDTO.setListStep(stepService.getListStepByIds(workfollow.getSteps()));
            }

            return workfollowDTO;
        } catch (Exception e) {
            logger.error("Error in findById " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<WorkfollowDTO> findAll() {
        try {
            List<WorkfollowDTO> workfollowDTOs = new ArrayList<>();
            List<Workfollow> workfollows = workfollowRepository.findAll();
            for (Workfollow workfollow : workfollows) {
                WorkfollowDTO workfollowDTO = WorkfollowUtils.workfollowToWorkfollowDTO(workfollow);
                if (StringUtils.isNotBlank(workfollow.getSteps())) {
                    workfollowDTO.setListStep(stepService.getListStepByIds(workfollow.getSteps()));
                }
                workfollowDTOs.add(workfollowDTO);
            }
            return workfollowDTOs;
        } catch (Exception e) {
            logger.error("Error in findById " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
