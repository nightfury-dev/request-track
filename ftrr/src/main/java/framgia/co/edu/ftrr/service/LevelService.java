package framgia.co.edu.ftrr.service;

import framgia.co.edu.ftrr.entity.Level;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface LevelService {
    Map<String, Level> getMapLevel();
}
