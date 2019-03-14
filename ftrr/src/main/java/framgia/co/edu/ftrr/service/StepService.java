package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.StepDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StepService {
    StepDTO saveStep(StepDTO step);

    List<StepDTO> getListStep();

    List<StepDTO> getListStepByIds(String jsonIds);
}
