package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.WorkfollowDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkfollowService {
    WorkfollowDTO findById(Integer id);

    List<WorkfollowDTO> findAll();
}
