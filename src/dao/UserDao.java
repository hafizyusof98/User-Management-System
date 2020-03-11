package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionManager;
import model.UserBean;

public class UserDao {
	static Connection con = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement st = null;
	static int id;
	static String name;
	static String email;
	static String country;
	static String password;
	
	public UserDao() {}
	
	public UserBean login(UserBean userB) {
		id = userB.getId();
		password = userB.getPassword();
		
		String sql = "select * from usertable where id = '" + id + "' and password = '" + password + "' ";
		
		try {
			con = ConnectionManager.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			boolean more = rs.next();
			
			if(more) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				userB.setId(id);
				userB.setName(name);
				userB.setValid(true);
			}
			else {
				userB.setValid(false);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) 
				try{
					rs.close();
				}catch (Exception e) {}
				rs = null;
			if(st != null)
				try {
					st.close();
				}catch(Exception e) {}
				st = null;
			if(con != null)
				try {
					con.close();
				}catch(Exception e) {}
			con = null;
		}
		return userB;
	}
	public void addUser(UserBean user) throws SQLException{
		name = user.getName();
		email = user.getEmail();
		country = user.getCountry();
		
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement("INSERT INTO USERTABLE (name,email,country) VALUES (?,?,?)");
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,country);
			ps.executeUpdate();
		}
		catch(Exception ex) {
			System.out.println("failed:An exception occured!" + ex);
		}
		
		finally {
			if (ps != null){
				try {
					ps.close();
				} catch (Exception e) {
					ps = null;
				}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e_) {
					con = null;
				}

		}
	}
}	
}
	public UserBean getUserByID(int Id) {
		UserBean userB = new UserBean();
		String sql = "select * from usertable where id = ?";
		try {
			con = ConnectionManager.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, Id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				userB.setId(rs.getInt("id"));
				userB.setName(rs.getString("name"));
				userB.setEmail(rs.getString("email"));
				userB.setCountry(rs.getString("country"));
			}
		}catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return userB;
	}
	
	public List<UserBean> getAllUser(){
		List<UserBean> userL = new ArrayList<UserBean>();
		String sql = "select * from usertable";
		
		try {
			con = ConnectionManager.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				UserBean userB = new UserBean();
				userB.setId(rs.getInt("id"));
				userB.setName(rs.getString("name"));
				userB.setEmail(rs.getString("email"));
				userB.setCountry(rs.getString("country"));
				
				userL.add(userB);
			}
			
		}catch (SQLException e) {
            e.printStackTrace();
	}
		return userL;
	}
	
	public UserBean userExistance(UserBean userB) {
		int id = userB.getId();
		
		String sql = "select * from usertable where id = '" + id + "'";
		
		try {
            con = ConnectionManager.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            boolean more = rs.next();
            
            if(more) {
            	int idU = Integer.parseInt(rs.getString("id"));
            	userB.setId(idU);
            	userB.setValid(true);
            }
            else if(!more) {
            	userB.setValid(false);
            }
		}catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);

            
	}
		finally {
			if(rs!=null) {
			try{
				rs.close();
			}catch(Exception e) {}
			rs= null;
			}
			
			if(st!=null) {
				try {
					st.close();
				}catch(Exception e) {}
			st = null;
			}	
			
			if(con!=null) {
				try {
					con.close();
				}catch(Exception e) {}
				con = null;
				}
	}
		return userB;
	}
	
	public void updateUser(UserBean userB) {
		int id = userB.getId();
		String name = userB.getName();
		String email = userB.getEmail();
		String country = userB.getCountry();
		
		String sql = "UPDATE usertable set name = '" + name + "',email = '" + email + "',country = '" + country + "' where id = '" + id + "'";
		
		try {
			con = ConnectionManager.getConnection();
			st = con.createStatement();
			st.executeQuery(sql);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(int id) {
		String sql = "delete from usertable where id = ?";
		try {
		con = ConnectionManager.getConnection();
		ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}