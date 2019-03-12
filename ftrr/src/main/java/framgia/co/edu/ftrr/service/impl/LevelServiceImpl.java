package framgia.co.edu.ftrr.service.impl;

import framgia.co.edu.ftrr.entity.Level;
import framgia.co.edu.ftrr.repository.LevelRepository;
import framgia.co.edu.ftrr.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    LevelRepository levelRepository;

    @Override
    public Map<String, Level> getMapLevel() {

        List<Level> levels = levelRepository.findAll();
        Map<String, Level> levelMap = levels.stream()
                .collect(Collectors.toMap(Level::getName, Level -> Level, (oldValue, newValue) -> oldValue));
        return levelMap;
    }
}
