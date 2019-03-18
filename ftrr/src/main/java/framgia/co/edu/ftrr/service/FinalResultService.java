package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.entity.FinalResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FinalResultService {
    List<FinalResult> loadFinalResultsByDivAndResult(Integer div, Integer result);
}
