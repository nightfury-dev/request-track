package framgia.co.edu.ftrr.repository.impl;

import framgia.co.edu.ftrr.entity.Request;
import framgia.co.edu.ftrr.repository.RequestRepositoryCustom;
import framgia.co.edu.ftrr.util.DatetimeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class RequestRepositoryImpl implements RequestRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Request> search(String division, Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth) {
        return getQuery(division, fromYear, fromMonth, toYear, toMonth).getResultList();
    }

    private Query getQuery(String division, Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth) {
        String query = "SELECT a.* FROM requests a";
        boolean hasDivision = false;

        // division is not blank
        if (StringUtils.isNotBlank(division)) {
            hasDivision = true;
            query += " WHERE a.division = :division";
        }

        if (hasDivision) {
            query += " AND " + getDateQuery(fromYear, fromMonth, toYear, toMonth);
        } else {
            query += " WHERE " + getDateQuery(fromYear, fromMonth, toYear, toMonth);
        }
        query += " ORDER BY a.status, a.created_at";

        if (hasDivision) {
            return em.createNativeQuery(query, Request.class).setParameter("division", division);
        }
        return em.createNativeQuery(query, Request.class);
    }

    private String getDateQuery(Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth) {
        String dateQuery = "";

        // get request in current month
        if (fromYear == null && toYear == null) {
            dateQuery += "year(a.created_at) = year(current_date()) AND month(a.created_at) = month(current_date())";
        }

        // get request in month
        if (fromYear != null && toYear == null) {
            dateQuery += "year(a.created_at) = " + fromYear + " AND month(a.created_at) = " + fromMonth;
        }

        // get request between two month
        if (fromYear != null && toYear != null) {
            String fromDate = "'" + fromYear + "-" + fromMonth + "-01'";
            String toDate = "'" + DatetimeUtils.getNextMonth(toYear, toMonth) + "-01'";
            dateQuery += "a.created_at >= " + fromDate + " AND a.created_at < " + toDate;
        }

        return dateQuery;
    }

}
