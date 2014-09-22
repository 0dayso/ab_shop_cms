package net.rytong.service;

import java.util.List;

import net.rytong.entity.Employee;
import net.rytong.entity.User;


public interface IUserService {
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
    public List<Employee> userLogin(String username, String password);
    
    /**
     * 用户注册
     * @param employee
     */
    public void userReg(Employee employee);
    
    /**
     * 用户修改密码
     */
    public Employee userEdit(Employee employee);
    
    /**
     * 通过id得到用户(employee对象)
     * @param empId
     * @return
     */
    public Employee getEmpById(Long empId);
   
    /**
     * 得到所有用户
     */
    public List<User> getAllUsers();
    
    /**
     * 修改用户信息
     */
    public User updateUser(User user);
    
    /**
     * 查询不为空的用户
     */
    public List<User> findNotNullUser(Long beginDate, Long endDate);
    public void updateMileageCardUser(User user);
   
    /**
	 * 按注册时间查询用户
	 */
	public List<User> findByOptTime(Long optTimeBeginDate,Long optTimeEndDate);
	
	public List<User> findByPhone(String phone);
	
	public List<User> findByBookingTime(Long beginDate,Long endDate);
	
	public List<User> findByUserCode(String userCode);
   
}
