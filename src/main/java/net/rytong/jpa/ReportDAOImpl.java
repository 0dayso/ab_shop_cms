package net.rytong.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.rytong.dao.IReportDAO;
import net.rytong.vo.UserVisitCountVO;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author wuweihua
 * 
 */
@Transactional
@Component
public class ReportDAOImpl implements IReportDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<UserVisitCountVO> userVisitsReport(Long beginTime,
			Long endTime, int period) {
		String format = "%Y%m%d";
		switch (period) {
		case 1:
			format = "%H";
			break;
		case 2:
			format = "%Y%m%d";
			break;
		case 3:
			format = "%Y%m";
			break;
		case 4:
			format = "%Y";
			break;
		}
		StringBuffer sql = new StringBuffer("select DATE_FORMAT(t_visit_time,'").append(format).append("'), count(id) from t_visits ");
		sql.append(" where t_visit_time >= ").append(beginTime.toString()).append(" and t_visit_time <= ").append(endTime.toString());
		sql.append(" group by DATE_FORMAT(t_visit_time,'").append(format).append("') ");
		Query query = em.createNativeQuery(sql.toString());
		return query.getResultList();
	}

}
