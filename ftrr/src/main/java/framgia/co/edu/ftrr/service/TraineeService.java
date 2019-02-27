package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.dto.request.TraineeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TraineeService {
    List<TraineeDTO> loadTrainees();
}
