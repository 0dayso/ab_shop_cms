package net.rytong.ws;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.rytong.dao.IParameterDAO;
import net.rytong.entity.Parameter;
import net.rytong.utils.FileUtil;
import net.rytong.utils.LotteryUtils;
import net.rytong.utils.TimeHelper;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LotteryDataUpdate {
	@Autowired
	private IParameterDAO iParameterDAO;
	protected Logger logger = LoggerFactory.getLogger(getClass().getName());
	private static String url = "jdbc:mysql://192.168.64.17:3306/travel";
	private static String drive = "com.mysql.jdbc.Driver";
	private static final int MAX_BATCH_NUM = 1000; 
	String threadName = "";
	private static String userName = "lpdb";
	private static String password = "";
	
	static {
		try {
			Class.forName(drive);
			String runtime = FileUtil.getProperties("customer.properties").getProperty("runtime");
			Properties p = null;
			if ("PRO".equals(runtime.toUpperCase())) {
				p = FileUtil.getProperties("vas/db_pro.properties");
			} else if ("LOC".equals(runtime.toUpperCase())) {
				p = FileUtil.getProperties("vas/db_loc.properties");
			} else {
				p = FileUtil.getProperties("vas/db_uat.properties");
			}
			url = (String)p.get("url");
			drive = (String)p.get("drive");
			userName = (String)p.get("userName");
			password = (String)p.get("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int synchroBetLotterys(String issue) { 
		int result = 0;
		if (LotteryDataGrab.FLAG == 0) {
			return result;
		}
		long start = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement insertState = null;
		List<String> ballList = new ArrayList<String>();
		//Map<String, String> lotteryMap = selectLotterysForUpdate("1", issue, 0, 0);
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String INSERT_HOTEL_SQL = "INSERT INTO t_bet_lottery_number(t_bet_number,t_issue,t_num,t_uid,t_source) VALUES(?, ?, ?, ?, ?)";
			insertState = conn.prepareStatement(INSERT_HOTEL_SQL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			int insertR = 0;
			String filePath = "D:\\lottery.txt";
			List<Parameter> listP = iParameterDAO.getParameterByCodeAndNameAndCustomer("SERVER", "LOTTERY_FILE_PATH", "0");
			if (listP != null && listP.size() > 0) {
				String s = listP.get(0).getValue();
				if (StringUtils.isBlank(s)) {
					filePath = s;
				}
			}
			FileInputStream stream = new FileInputStream(filePath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream,  "utf-8"));
			String lineTxt = null;
			while((lineTxt = bufferedReader.readLine()) != null){
				String[] s = lineTxt.trim().split("_");
				String betNumber = s[0];
				String uid = s[1];
				String num = s[2];
				List<String> list = LotteryUtils.getSperated(betNumber);
				if (list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						String str = list.get(i);
						if(!ballList.contains(str)) {
							ballList.add(str);
							String[] values = new String[] {str, issue, num, uid, "1"};
							putParam(insertState, values);
							insertState.addBatch();
							insertR++;
							if(insertR % MAX_BATCH_NUM == 0){
								insertState.executeBatch();
								conn.commit();
								insertR = 0;
								insertState.clearBatch();
								logger.info("Insert " + MAX_BATCH_NUM  + " lottery records for one batch");
					        }
							result++;
						}
					}
				}
	        }
			insertState.executeBatch();
			conn.commit();
			long end = System.currentTimeMillis();
			long s = ((end - start) / 1000);
			logger.info("Syschronize lottery records Completed Time:" + TimeHelper.getStrCurrentTime() + ",Total Cost Time:" + s + " Second");
		} catch (SQLException e) {
			logger.error("Insert lottery records error");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Insert lottery records error");
			e.printStackTrace();
		} finally {
			clossPrestStatement(insertState);
			clossConnection(conn);
		}
		LotteryDataGrab.setFlag(0);
		return result;
	}
	
	public void synchroHistoryLotterys(Map<String, String> map) { 
		long start = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement insertState = null;
		
		Map<String, String> haveMap = getHistoryInfoLotterys();
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String INSERT_HOTEL_SQL = "INSERT INTO t_history_lottery_number(t_issue, t_number) VALUES(?, ?)";
			insertState = conn.prepareStatement(INSERT_HOTEL_SQL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			int insertR = 0;
			for(Map.Entry<String, String> entry: map.entrySet()) {
				String key = entry.getKey();
				if(!haveMap.containsKey(key)) {
					String value = entry.getValue();
					String[] values = new String[] {key, value};
					putParam(insertState, values);
					insertState.addBatch();
					insertR++;
					if(insertR % MAX_BATCH_NUM == 0){
						insertState.executeBatch();
						conn.commit();
						insertR = 0;
						insertState.clearBatch();
						logger.info("Insert " + MAX_BATCH_NUM  + " lottery records for one batch");
			        }
				}
			}
			insertState.executeBatch();
			conn.commit();
			long end = System.currentTimeMillis();
			long s = ((end - start) / 1000);
			logger.info("Syschronize lottery records Completed Time:" + TimeHelper.getStrCurrentTime() + ",Total Cost Time:" + s + " Second");
		} catch (SQLException e) {
			logger.error("Insert lottery records error");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Insert lottery records error");
			e.printStackTrace();
		} finally {
			clossPrestStatement(insertState);
			clossConnection(conn);
		}
		LotteryDataGrab.setFlag(0);
	}
	
	public void LotteryLotteryBases(List<String> listF, List<String> listC) { 
		long start = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement updateState = null;
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String UPDATE_HOTEL_SQL = "UPDATE t_base_lottery_number SET t_type= ? where id = ?";
			updateState = conn.prepareStatement(UPDATE_HOTEL_SQL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			int insertR = 0, updateR = 0;
			
			for (int i = 0; i < listF.size(); i++) {
				updateState.setString(1, "-1");
				updateState.setString(2, listF.get(i));
				updateState.addBatch();
				updateR++;
				if(updateR % MAX_BATCH_NUM == 0){
					updateState.executeBatch();
					conn.commit();
					updateR = 0;
					insertR = 0;
					updateState.clearBatch();
					logger.info("Update " + MAX_BATCH_NUM  + " and Insert " + insertR  + " Lottery records for one batch");
		        }
			}		
			
			for (int i = 0; i < listC.size(); i++) {
				updateState.setString(1, "-2");
				updateState.setString(2, listF.get(i));
				updateState.addBatch();
				updateR++;
				if(updateR % MAX_BATCH_NUM == 0){
					updateState.executeBatch();
					conn.commit();
					updateR = 0;
					insertR = 0;
					updateState.clearBatch();
					logger.info("Update " + MAX_BATCH_NUM  + " and Insert " + insertR  + " Lottery records for one batch");
		        }
			}		
			updateState.executeBatch();
			conn.commit();
			long end = System.currentTimeMillis();
			long s = ((end - start) / 1000);
			logger.info("Syschronize Lottery records Completed Time:" + TimeHelper.getStrCurrentTime() + ",Total Cost Time:" + s + " Second");
		} catch (SQLException e) {
			logger.error("Insert or Update Lottery records error");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Insert or Update Lottery records error");
			e.printStackTrace();
		} finally {
			clossPrestStatement(updateState);
			clossConnection(conn);
		}
	}
	
	public int insertLotteryBuys(List<String> list, String issue) { 
		int result = 0;
		
		long start = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement insertState = null;
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String INSERT_HOTEL_SQL = "INSERT INTO t_lottery_buy_number(t_issue, t_number) VALUES(?, ?)";
			insertState = conn.prepareStatement(INSERT_HOTEL_SQL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			int insertR = 0;
			for (int i = 0; i < list.size(); i++) {
				result++;
				String[] values = new String[] {issue, list.get(i)};
				putParam(insertState, values);
				insertState.addBatch();
				insertR++;
				if(insertR % MAX_BATCH_NUM == 0){
					insertState.executeBatch();
					conn.commit();
					insertR = 0;
					insertState.clearBatch();
					logger.info("Insert " + MAX_BATCH_NUM  + " lottery records for one batch");
		        }
			}
			insertState.executeBatch();
			conn.commit();
			long end = System.currentTimeMillis();
			long s = ((end - start) / 1000);
			logger.info("Syschronize lottery records Completed Time:" + TimeHelper.getStrCurrentTime() + ",Total Cost Time:" + s + " Second");
		} catch (SQLException e) {
			logger.error("Insert lottery records error");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Insert lottery records error");
			e.printStackTrace();
		} finally {
			clossPrestStatement(insertState);
			clossConnection(conn);
		}
		LotteryDataGrab.setFlag(0);
		return result;
	}
	
	// 可能有多条重复记录
	public int deleteRepeatedLotterys(String issue) { 
		int result = 0;
		long start = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement updateState = null;
		for (;;) {
			List<String> list = this.getRepeatedLotterys(issue);
			if (list.size() == 0) {
				break;
			}
			result = result + list.size();
			try {
				conn = DriverManager.getConnection(url, userName, password);
				conn.setAutoCommit(false);
				final String UPDATE_HOTEL_SQL = "delete from t_bet_lottery_number where id = ?";
				updateState = conn.prepareStatement(UPDATE_HOTEL_SQL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
				int updateR = 0;
				
				for (int i = 0; i < list.size(); i++) {
					updateState.setString(1, list.get(i));
					updateState.addBatch();
					updateR++;
					if(updateR % MAX_BATCH_NUM == 0){
						updateState.executeBatch();
						conn.commit();
						updateR = 0;
						updateState.clearBatch();
						logger.info("Delete " + MAX_BATCH_NUM  + " Lottery records for one batch");
			        }
				}		
				int[] a = updateState.executeBatch();
				conn.commit();
				long end = System.currentTimeMillis();
				long s = ((end - start) / 1000);
				logger.info("Syschronize Lottery records Completed Time:" + TimeHelper.getStrCurrentTime() + ",Total Cost Time:" + s + " Second");
			} catch (SQLException e) {
				logger.error("Insert or Update Lottery records error");
				e.printStackTrace();
			} catch (Exception e) {
				logger.error("Insert or Update Lottery records error");
				e.printStackTrace();
			} finally {
				clossPrestStatement(updateState);
				clossConnection(conn);
			}
		}
		return result;
	}
	
	public int deleteRepeatedLotteryBuys(String issue) { 
		long start = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement updateState = null;
		List<String> list = this.getRepeatedLotteryBuys(issue);
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String UPDATE_HOTEL_SQL = "delete from t_lottery_buy_number where id = ?";
			updateState = conn.prepareStatement(UPDATE_HOTEL_SQL);
			int updateR = 0;
			
			for (int i = 0; i < list.size(); i++) {
				updateState.setLong(1, Long.valueOf(list.get(i)));
				updateState.addBatch();
				updateR++;
				if(updateR % MAX_BATCH_NUM == 0){
					updateState.executeBatch();
					conn.commit();
					updateR = 0;
					updateState.clearBatch();
					logger.info("Delete " + MAX_BATCH_NUM  + " Lottery records for one batch");
		        }
			}		
			int[] a = updateState.executeBatch();
			conn.commit();
			long end = System.currentTimeMillis();
			long s = ((end - start) / 1000);
			logger.info("Syschronize Lottery records Completed Time:" + TimeHelper.getStrCurrentTime() + ",Total Cost Time:" + s + " Second");
		} catch (SQLException e) {
			logger.error("Insert or Update Lottery records error");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Insert or Update Lottery records error");
			e.printStackTrace();
		} finally {
			clossPrestStatement(updateState);
			clossConnection(conn);
		}
		return list.size();
	}
	
	public Map<String, String> selectLotterysForUpdate(String site, String issue, int start, int size) { 
		Connection conn = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			String SELECT_HOTEL = "select id, t_uid, t_issue, t_bet_number from  t_bet_lottery_number" +
			                            " where t_source = '" + site + "' and t_issue = '" + issue + "' ";
			if (size != 0) {
				SELECT_HOTEL = SELECT_HOTEL + " limit " + start + "," + size;
			}
			prest = conn.prepareStatement(SELECT_HOTEL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  
			prest = conn.prepareStatement(SELECT_HOTEL);
	    	rs = prest.executeQuery();
	    	  
	        while (rs.next()){  
	        	result.put(rs.getString("id"), rs.getString("t_uid") + "_" + rs.getString("t_issue") + "_" + rs.getString("t_bet_number"));
	        }
	      } catch (Exception e) {
	    	  logger.error("Select Lottery for update records error");
	          e.printStackTrace();
	      } finally{
	    	  clossResultSet(rs);
	          clossPrestStatement(prest);
	          clossConnection(conn);  
	      }  
	      return result;
	}
	
	public int updateLotteryBases(final String sql) { 
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
	    	result = stmt.executeUpdate(sql);
	      } catch (Exception e) {
	    	  logger.error("Select Lottery for update records error");
	          e.printStackTrace();
	      } finally{
	    	  closeStatement(stmt);
	          clossConnection(conn);  
	      }  
	      return result;
	}
	
	public List<String> selectRemainLotterys(String site, String issue, int start, int size) { 
		Connection conn = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		List<String> result = new ArrayList<String>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String SELECT_HOTEL = "select t_number from t_base_lottery_number where t_base_lottery_number.t_type = '0' and " + 
								  "NOT EXISTS (select t_bet_number from t_bet_lottery_number " + 
								  "where t_bet_number = t_base_lottery_number.t_number and t_source = '" + site + 
								  "' and t_issue='" + issue + "') limit " + start + "," + size;
			prest = conn.prepareStatement(SELECT_HOTEL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  
			prest = conn.prepareStatement(SELECT_HOTEL);
	    	rs = prest.executeQuery();
	    	  
	        while (rs.next()){  
	        	result.add(rs.getString("t_number"));
	        }
	      } catch (Exception e) {
	    	  logger.error("Select Lottery for update records error");
	          e.printStackTrace();
	      } finally{
	    	  clossResultSet(rs);
	          clossPrestStatement(prest);
	          clossConnection(conn);  
	      }  
	      return result;
	}
	
	
	public Map<String, String> getIssueInfoLotterys(String site, String issue) { 
		Connection conn = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String SELECT_HOTEL = "select t_issue, t_issue_key from  t_lottery_issue" +
			                            " where t_source = '" + site + "' and t_issue = '" + issue + "'";
			prest = conn.prepareStatement(SELECT_HOTEL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  
			prest = conn.prepareStatement(SELECT_HOTEL);
	    	rs = prest.executeQuery();
	    	  
	        while (rs.next()){  
	        	result.put(rs.getString("t_issue"), rs.getString("t_issue_key"));
	        }
	      } catch (Exception e) {
	    	  logger.error("Select Lottery for update records error");
	          e.printStackTrace();
	      } finally{
	    	  clossResultSet(rs);
	          clossPrestStatement(prest);
	          clossConnection(conn);  
	      }  
	      return result;
	}
	
	public Map<String, String> getHistoryInfoLotterys() { 
		Connection conn = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String SELECT_HOTEL = "select t_issue, t_number from  t_history_lottery_number ";
			prest = conn.prepareStatement(SELECT_HOTEL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  
			prest = conn.prepareStatement(SELECT_HOTEL);
	    	rs = prest.executeQuery();
	    	  
	        while (rs.next()){  
	        	result.put(rs.getString("t_issue"), rs.getString("t_number"));
	        }
	      } catch (Exception e) {
	    	  logger.error("Select Lottery for update records error");
	          e.printStackTrace();
	      } finally{
	    	  clossResultSet(rs);
	          clossPrestStatement(prest);
	          clossConnection(conn);  
	      }  
	      return result;
	}
	
	public int revertLotteryBasesForUpdate() { 
		int result = 0;
		final String sql = "update t_base_lottery_number set t_type = 0 where t_type = '-1'";
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			stmt = conn.createStatement();
	    	result = stmt.executeUpdate(sql);
	      } catch (Exception e) {
	    	  logger.error("Select Lottery for update records error");
	          e.printStackTrace();
	      } finally{
	    	  closeStatement(stmt);
	          clossConnection(conn);  
	      }  
	      return result;
	}
	
	public List<String> getRepeatedLotterys(String issue) { 
		Connection conn = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		List<String> result = new ArrayList<String>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String SELECT_HOTEL = "select max(id) as id from t_bet_lottery_number where t_issue='" + issue + "' group by t_bet_number having count(*)>1 ";
			prest = conn.prepareStatement(SELECT_HOTEL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  
			prest = conn.prepareStatement(SELECT_HOTEL);
	    	rs = prest.executeQuery();
	    	  
	        while (rs.next()){  
	        	result.add(rs.getString("id"));
	        }
	      } catch (Exception e) {
	    	  logger.error("Select Lottery for update records error");
	          e.printStackTrace();
	      } finally{
	    	  clossResultSet(rs);
	          clossPrestStatement(prest);
	          clossConnection(conn);  
	      }  
	      return result;
	}
	
	public List<String> getRepeatedLotteryBuys(String issue) { 
		Connection conn = null;
		PreparedStatement prest = null;
		ResultSet rs = null;
		List<String> result = new ArrayList<String>();
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String SELECT_HOTEL = "select max(id) as id from t_lottery_buy_number where t_issue='" + issue + "' group by t_number having count(*)>1 ";
			prest = conn.prepareStatement(SELECT_HOTEL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			  
			prest = conn.prepareStatement(SELECT_HOTEL);
	    	rs = prest.executeQuery();
	    	  
	        while (rs.next()){  
	        	result.add(rs.getString("id"));
	        }
	      } catch (Exception e) {
	    	  logger.error("Select Lottery for update records error");
	          e.printStackTrace();
	      } finally{
	    	  clossResultSet(rs);
	          clossPrestStatement(prest);
	          clossConnection(conn);  
	      }  
	      return result;
	}
	
	private void putParam(PreparedStatement prest, String[] values) throws SQLException {
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				prest.setString(i + 1, values[i]);
			}
		}
	}
	
	public void clossConnection(Connection conn ){
	    try {
	    	if(conn != null){
	    		conn.close();
	    		conn = null;
          }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public void closeStatement(Statement s ){
	    try {
	    	if(s != null) {
	    		s.close();
	    		s = null;
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public void clossPrestStatement(PreparedStatement pstet ){
	    try {
	    	if(pstet != null) {
	    		pstet.close();
	    		pstet = null;
	    	}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}

	public void clossResultSet(ResultSet rs ){
	    try {
	    	if(rs != null) {
	    		rs.close();
	    		rs = null;
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		LotteryDataUpdate b = new LotteryDataUpdate();
		b.init();
	}
	
	public void init() {
		long start = System.currentTimeMillis();
		Connection conn = null;
		PreparedStatement insertState = null;
		
		try {
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
			final String INSERT_HOTEL_SQL = "INSERT INTO t_base_lottery_number(t_number,t_type) VALUES(?, ?)";
			insertState = conn.prepareStatement(INSERT_HOTEL_SQL, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			int insertR = 0;
			
			String[] bs = new String[]{"01","02","03","04","05","06","07","08","09","10",
									   "11","12","13","14","15","16","17","18","19","20",
									   "21","22","23","24","25","26","27","28","29","30",
									   "31","32","33","34","35"
									  };
			ArrayList<String> bCombine = LotteryUtils.getArrangeOrCombineAndType(null, bs, 5);
			System.out.println("Total b:"+bCombine.size());
			for (int i = 0; i < bCombine.size(); i++) {
				String str = bCombine.get(i);
				String number = str.split("_")[0];
				String type = str.split("_")[1];
				
				String[] values = new String[] {number, type};
				putParam(insertState, values);
				insertState.addBatch();
				insertR++;
				if(insertR % MAX_BATCH_NUM == 0){
					insertState.executeBatch();
					conn.commit();
					insertR = 0;
					insertState.clearBatch();
					logger.info("Insert " + MAX_BATCH_NUM  + " lottery records for one batch");
		        }
			}
			insertState.executeBatch();
			conn.commit();
			long end = System.currentTimeMillis();
			long s = ((end - start) / 1000);
			logger.info("Syschronize lottery records Completed Time:" + TimeHelper.getStrCurrentTime() + ",Total Cost Time:" + s + " Second");
		} catch (SQLException e) {
			logger.error("Insert lottery records error");
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Insert lottery records error");
			e.printStackTrace();
		} finally {
			clossPrestStatement(insertState);
			clossConnection(conn);
		}
	}

	public int[] setBuyLotterys(String site, String issue) {
		int[] result = new int[2];
		int size= 1000;
		int oneNum = 0;
		for (int i = 0; i < 250; i++) {
			int start = i * size;
			List<String> lotteryList = selectRemainLotterys(site, issue, start, size);
			int in = insertLotteryBuys(lotteryList, issue);
			oneNum = oneNum + in;
			if (lotteryList.size() < size) {
				break;
			}
		}
		int delNum = deleteRepeatedLotteryBuys(issue);
		result[0] = oneNum;
		result[1] = delNum;
		return result;
	}
}