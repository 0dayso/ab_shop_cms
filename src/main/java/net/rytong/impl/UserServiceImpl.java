package net.rytong.impl;

import java.util.List;

import net.rytong.dao.IEmployeeDAO;
import net.rytong.dao.IUserDAO;
import net.rytong.entity.Employee;
import net.rytong.entity.User;
import net.rytong.service.IUserService;
import net.rytong.utils.PagingEnumerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements IUserService {
	@Autowired
	private IEmployeeDAO iEmployeeDAO;
	@Autowired
	private IUserDAO iUserDAO;

	/**
	 * 用户登录
	 */
	public List<Employee> userLogin(String username, String password) {
		return iEmployeeDAO.findByLoginPin(username, password);
	}

	/**
	 * 用户注册
	 */
	public void userReg(Employee employee) {
		iEmployeeDAO.save(employee);
	}

	/**
	 * 用户修改密码
	 */
	public Employee userEdit(Employee employee) {
		return iEmployeeDAO.update(employee);
	}

	public Employee getEmpById(Long empId) {
		return iEmployeeDAO.findById(empId);
	}

	/**
	 * 进行用户的模糊查询
	 */
	public PagingEnumerator<User> userMatch(String name, String gender, String mobile,
			String idNo, String email, String phone,String mileageCard,String mobileType,Long startDate,
			Long endDate,Long customer,String fromCompany, int pageIndex, int pageCount) {
		return iUserDAO.findByProperties(name, gender,null, mobile, idNo, email,
				phone, mileageCard, mobileType,startDate, endDate,customer,fromCompany, pageIndex, pageCount);
	}

	public User getUserById(Long userId) {
		return iUserDAO.findById(userId);
	}
	
	public User updateUser(User user){
		return iUserDAO.update(user);
	}

	@Override
	public List<User> getAllUsers() {
		return this.iUserDAO.findAll(null);
	}

	/**
     * 查询不为空的用户
     */
	@Override
	public List<User> findNotNullUser(Long beginDate, Long endDate) {
		return this.iUserDAO.findNotNullUser(beginDate, endDate);
	}

	@Override
	public void updateMileageCardUser(User user) {
	   iUserDAO.update(user);
	}

	@Override
	public List<User> findByOptTime(Long optTimeBeginDate, Long optTimeEndDate) {
		return this.iUserDAO.findByOptTime(optTimeBeginDate, optTimeEndDate);
	}
	
	public List<User> findByPhone(String phone){
		return iUserDAO.findByPhone(phone);
	}
	
	public List<User> findByBookingTime(Long beginDate,Long endDate){
		return iUserDAO.findByBookingTime(beginDate, endDate);
	}
	
	public List<User> findByUserCode(String userCode){
		return iUserDAO.findByUserCode(userCode);
	}
}
