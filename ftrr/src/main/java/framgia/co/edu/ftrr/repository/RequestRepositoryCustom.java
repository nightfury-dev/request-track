package framgia.co.edu.ftrr.repository;

import framgia.co.edu.ftrr.entity.Request;

import java.util.List;

public interface RequestRepositoryCustom {
    List<Request> search(String division, Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth);
}
