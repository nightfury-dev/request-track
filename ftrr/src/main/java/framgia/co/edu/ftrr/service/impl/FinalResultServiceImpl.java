package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.entity.FinalResult;
import framgia.co.edu.ftrr.repository.FinalResultRepository;
import framgia.co.edu.ftrr.service.FinalResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FinalResultServiceImpl implements FinalResultService {
    @Autowired
    FinalResultRepository finalResultRepository;

    @Override
    public List<FinalResult> loadFinalResultsByDivAndResult(Integer div, Integer result) {
        try {
            return finalResultRepository.loadFinalResultsByDivAndResult(div, result);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
