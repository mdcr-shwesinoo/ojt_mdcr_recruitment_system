package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConfig;
import model.UserAccountModel;

public class UserAccountController {

	// DB Connection
	public static Connection con = null;

	static {
		DBConfig dbConfig = new DBConfig();
		con = dbConfig.getConnection();

	}

	// Data insert for user account
	public int insert(UserAccountModel userAccountModel) {
		int result = 0;
		String sql = "insert into mdcrrecruitment.useraccount ( user_name, user_email, user_password, user_role) values(?,?,?,?)";

		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
//			ps.setString(1, userAccountModel.getUserid());
			ps.setString(1, userAccountModel.getName());
			ps.setString(2, userAccountModel.getEmail());
			ps.setString(3, userAccountModel.getPassword());
			ps.setString(4, "User");

			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String searchUserAccount(UserAccountModel userModel) {
		String result = "";
		String sql = "SELECT user_email,user_password,user_role FROM mdcrrecruitment.useraccount WHERE user_email = ? and user_password = ?";
		PreparedStatement ps;
		try {
			String password = "";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, userModel.getEmail());
			ps.setString(2, userModel.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String email = rs.getString("user_email");
				password = rs.getString("user_password");
				String role = rs.getString("user_role");
				if (email.equals(userModel.getEmail()) && password.equals(userModel.getPassword())) {
					return role;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean emailExists(String email) {
		boolean exist = false;
		String sql = "select * from mdcrrecruitment.useraccount where user_email=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				exist = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}

	public boolean checkPassword(String email, String password) {
		String sql = "SELECT user_password FROM mdcrrecruitment.useraccount WHERE user_email=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				String user_password = rs.getString("user_password");

				if (user_password.equals(password)) {
					return true;
				} else {
					return false;
				}

				// Option B (RECOMMENDED): Hashed password comparison using bcrypt, etc.
				// if (BCrypt.checkpw(password, storedPassword)) {
				// return "Login successful";
				// } else {
				// return "Incorrect password";
				// }

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Duplicate for Email Test
	public boolean isduplicate(UserAccountModel userModel) throws SQLException {
		boolean duplicate = false;
		System.out.println("Email=====");
		System.out.println(userModel.getEmail());
		String sql = "select * from mdcrrecruitment.useraccount where user_email=?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, userModel.getEmail());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			duplicate = true;
		} else {
			duplicate = false;
		}
		return duplicate;
	}
	
	public int getID(String email) throws SQLException {
		int id = 0;
		String sql = "select user_id from mdcrrecruitment.useraccount where user_email=?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			id = rs.getInt("user_id");
			return id;
		} 
		return id;
	}
	
	public UserAccountModel findById(int id) {
		String sql = "SELECT * from useraccount where user_id = ?";

		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, id);

			var rs = ps.executeQuery();
			if(rs.next()) {
				UserAccountModel model = new UserAccountModel();
				model.setUserid(rs.getInt("user_id"));
				model.setName(rs.getString("user_name"));
				model.setEmail(rs.getString("user_email"));
				model.setPassword(rs.getString("user_password"));
				model.setRole(rs.getString("user_role"));
				return model;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
