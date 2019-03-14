package framgia.co.edu.ftrr.util;


import framgia.co.edu.ftrr.dto.request.WorkfollowDTO;
import framgia.co.edu.ftrr.entity.Workfollow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WorkfollowUtils {
    private static final Logger logger = LoggerFactory.getLogger(WorkfollowUtils.class);

    public static WorkfollowDTO workfollowToWorkfollowDTO(Workfollow workfollow) {
        try {
            WorkfollowDTO workfollowDTO = new WorkfollowDTO();
            BeanUtils.copyProperties(workfollow, workfollowDTO);
            return workfollowDTO;
        } catch (Exception e) {
            logger.error("Error in workfollowToWorkfollowDTO: " + e.getMessage());
            return null;
        }
    }

    public static List<WorkfollowDTO> listWorkfollowToListWorkfollowDTO(List<Workfollow> workfollows) {
        try {
            return workfollows.stream().map(workfollow -> workfollowToWorkfollowDTO(workfollow)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listWorkfollowToListWorkfollowDTO: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static Workfollow workfollowDTOToWorkfollow(WorkfollowDTO workfollowDTO) {
        try {
            Workfollow workfollow = new Workfollow();
            BeanUtils.copyProperties(workfollowDTO, workfollow);
            return workfollow;
        } catch (Exception e) {
            logger.error("Error in workfollowDTOToWorkfollow: " + e.getMessage());
            return null;
        }
    }

    public static List<Workfollow> listWorkfollowDTOToListWorkfollow(List<WorkfollowDTO> workfollowDTOs) {
        try {
            return workfollowDTOs.stream().map(workfollowDTO -> workfollowDTOToWorkfollow(workfollowDTO)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error in listWorkfollowDTOToListWorkfollow: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
