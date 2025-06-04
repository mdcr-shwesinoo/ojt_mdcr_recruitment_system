package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DBConfig;
import model.ApplicantModel;
import model.ApplicantModel.ApplicantStatus;

public class ApplicantController {

	public boolean insertApplicants(ApplicantModel applicant) {
		final String sql = "INSERT INTO applicant "
				+ "(applicant_name, applicant_nrc, father_name, dob_ofnrc, gender, marital_status, address, phone_no, education, major,degree_type, mail, mark, status) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, applicant.getApplicantName());
			stmt.setString(2, applicant.getApplicantNRC());
			stmt.setString(3, applicant.getFatherName());
			stmt.setDate(4, java.sql.Date.valueOf(applicant.getDobofNRC())); // converting LocalDate to java.sql.Date
			stmt.setString(5, applicant.getGender());
			stmt.setString(6, applicant.getMaritalStatus());
			stmt.setString(7, applicant.getAddress());
			stmt.setString(8, applicant.getPhoneNo());
			stmt.setString(9, applicant.getEducation());
			stmt.setString(10, applicant.getMajor());
			stmt.setString(11, applicant.getDegreeType());
			stmt.setString(12, applicant.getMail());
			stmt.setInt(13, applicant.getMark());
			stmt.setString(14, applicant.getStatus().name());

			int rowsInserted = stmt.executeUpdate();
			return rowsInserted > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}
	
	public ApplicantModel getApplicantsDetails(int id) {
		final String sql = "SELECT * FROM applicant WHERE applicant_id=?";
		ApplicantModel applicantModel=new ApplicantModel();

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				applicantModel.setApplicantID(id);
				applicantModel.setApplicantName(rs.getString("applicant_name"));
				applicantModel.setApplicantNRC(rs.getString("applicant_nrc"));
				applicantModel.setFatherName(rs.getString("father_name"));
				applicantModel.setDobofNRC(rs.getDate("dob_ofnrc").toLocalDate());
				applicantModel.setGender(rs.getString("gender"));
				applicantModel.setMaritalStatus(rs.getString("marital_status"));
				applicantModel.setAddress(rs.getString("address"));
				applicantModel.setPhoneNo(rs.getString("phone_no"));
				applicantModel.setEducation(rs.getString("education"));
				applicantModel.setMajor(rs.getString("major"));
				applicantModel.setDegreeType(rs.getString("degree_type"));
				applicantModel.setMail(rs.getString("mail"));
				applicantModel.setMark(rs.getInt("mark"));
				applicantModel.setStatus(ApplicantStatus.valueOf(rs.getString("status")));

			

			}
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}

		return applicantModel;

	}

	public List<ApplicantModel> getPendingApplicants() {
		final String sql = "SELECT * FROM applicant WHERE status='PENDING'";
		List<ApplicantModel> applicants = new ArrayList<>();

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ApplicantModel applicantModel = new ApplicantModel();
				applicantModel.setApplicantID(rs.getInt("applicant_id"));
				applicantModel.setApplicantName(rs.getString("applicant_name"));
				applicantModel.setApplicantNRC(rs.getString("applicant_nrc"));
				applicantModel.setFatherName(rs.getString("father_name"));
				applicantModel.setDobofNRC(rs.getDate("dob_ofnrc").toLocalDate());
				applicantModel.setGender(rs.getString("gender"));
				applicantModel.setMaritalStatus(rs.getString("marital_status"));
				applicantModel.setAddress(rs.getString("address"));
				applicantModel.setPhoneNo(rs.getString("phone_no"));
				applicantModel.setEducation(rs.getString("education"));
				applicantModel.setMajor(rs.getString("major"));
				applicantModel.setDegreeType(rs.getString("degree_type"));
				applicantModel.setMail(rs.getString("mail"));
				applicantModel.setMark(rs.getInt("mark"));
				applicantModel.setStatus(ApplicantStatus.valueOf(rs.getString("status")));

				applicants.add(applicantModel);

			}
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}

		return applicants;

	}

	public List<ApplicantModel> getAcceptedApplicants() {
		final String sql = "SELECT * FROM applicant WHERE status='ACCEPT'";
		List<ApplicantModel> applicants = new ArrayList<>();

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ApplicantModel applicantModel = new ApplicantModel();

				applicantModel.setApplicantName(rs.getString("applicant_name"));
				applicantModel.setApplicantNRC(rs.getString("applicant_nrc"));
				applicantModel.setFatherName(rs.getString("father_name"));
				applicantModel.setDobofNRC(rs.getDate("dob_ofnrc").toLocalDate());
				applicantModel.setGender(rs.getString("gender"));
				applicantModel.setMaritalStatus(rs.getString("marital_status"));
				applicantModel.setAddress(rs.getString("address"));
				applicantModel.setPhoneNo(rs.getString("phone_no"));
				applicantModel.setEducation(rs.getString("education"));
				applicantModel.setMajor(rs.getString("major"));
				applicantModel.setDegreeType(rs.getString("degree_type"));
				applicantModel.setMail(rs.getString("mail"));
				applicantModel.setMark(rs.getInt("mark"));
				applicantModel.setStatus(ApplicantStatus.valueOf(rs.getString("status")));

				applicants.add(applicantModel);

			}
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}

		return applicants;

	}

	public List<ApplicantModel> getRejectedApplicants() {
		final String sql = "SELECT * FROM applicant WHERE status='REJECT'";
		List<ApplicantModel> applicants = new ArrayList<>();

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ApplicantModel applicantModel = new ApplicantModel();

				applicantModel.setApplicantName(rs.getString("applicant_name"));
				applicantModel.setApplicantNRC(rs.getString("applicant_nrc"));
				applicantModel.setFatherName(rs.getString("father_name"));
				applicantModel.setDobofNRC(rs.getDate("dob_ofnrc").toLocalDate());
				applicantModel.setGender(rs.getString("gender"));
				applicantModel.setMaritalStatus(rs.getString("marital_status"));
				applicantModel.setAddress(rs.getString("address"));
				applicantModel.setPhoneNo(rs.getString("phone_no"));
				applicantModel.setEducation(rs.getString("education"));
				applicantModel.setMajor(rs.getString("major"));
				applicantModel.setDegreeType(rs.getString("degree_type"));
				applicantModel.setMail(rs.getString("mail"));
				applicantModel.setMark(rs.getInt("mark"));
				applicantModel.setStatus(ApplicantStatus.valueOf(rs.getString("status")));

				applicants.add(applicantModel);

			}
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}

		return applicants;

	}
	
	
	
	

	public boolean updateApplicantStatus(String applicantNRC, ApplicantStatus status) {
		System.out.println(applicantNRC);
		String sql = "UPDATE applicant SET status = 'ACCEPT' WHERE applicant_nrc = ?";

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, applicantNRC); // Set NRC correctly

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean updateApplicantStatus1(String applicantNRC, ApplicantStatus status) {
		String sql = "UPDATE applicant SET status = 'REJECT' WHERE applicant_nrc = ?";

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setString(1, applicantNRC); // Set NRC correctly

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	
	public List<ApplicantModel> searchApplicantsByGender(String gender) {
		final String sql = "SELECT * FROM applicant WHERE gender=? AND status = 'PENDING'";
		List<ApplicantModel> applicants = new ArrayList<>();

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, gender);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ApplicantModel applicantModel = new ApplicantModel();

				applicantModel.setApplicantID(rs.getInt("applicant_id"));
				applicantModel.setApplicantName(rs.getString("applicant_name"));
				applicantModel.setApplicantNRC(rs.getString("applicant_nrc"));
				applicantModel.setFatherName(rs.getString("father_name"));
				applicantModel.setDobofNRC(rs.getDate("dob_ofnrc").toLocalDate());
				applicantModel.setGender(rs.getString("gender"));
				applicantModel.setMaritalStatus(rs.getString("marital_status"));
				applicantModel.setAddress(rs.getString("address"));
				applicantModel.setPhoneNo(rs.getString("phone_no"));
				applicantModel.setEducation(rs.getString("education"));
				applicantModel.setMajor(rs.getString("major"));
				applicantModel.setDegreeType(rs.getString("degree_type"));
				applicantModel.setMail(rs.getString("mail"));
				applicantModel.setMark(rs.getInt("mark"));
				applicantModel.setStatus(ApplicantStatus.valueOf(rs.getString("status")));

				applicants.add(applicantModel);

			}
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}

		return applicants;
	}
	
	
	public List<ApplicantModel> searchApplicantsByName(String name) {
		final String sql = "SELECT * FROM applicant WHERE LOWER(applicant_name) LIKE ? AND status = 'PENDING'";
		List<ApplicantModel> applicants = new ArrayList<>();

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ApplicantModel applicantModel = new ApplicantModel();

				applicantModel.setApplicantID(rs.getInt("applicant_id"));
				applicantModel.setApplicantName(rs.getString("applicant_name"));
				applicantModel.setApplicantNRC(rs.getString("applicant_nrc"));
				applicantModel.setFatherName(rs.getString("father_name"));
				applicantModel.setDobofNRC(rs.getDate("dob_ofnrc").toLocalDate());
				applicantModel.setGender(rs.getString("gender"));
				applicantModel.setMaritalStatus(rs.getString("marital_status"));
				applicantModel.setAddress(rs.getString("address"));
				applicantModel.setPhoneNo(rs.getString("phone_no"));
				applicantModel.setEducation(rs.getString("education"));
				applicantModel.setMajor(rs.getString("major"));
				applicantModel.setDegreeType(rs.getString("degree_type"));
				applicantModel.setMail(rs.getString("mail"));
				applicantModel.setMark(rs.getInt("mark"));
				applicantModel.setStatus(ApplicantStatus.valueOf(rs.getString("status")));

				applicants.add(applicantModel);

			}
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}

		return applicants;
	}
	
	public List<ApplicantModel> searchApplicantsByGenderAndName(String gender, String name) {
		final String sql = "SELECT * FROM applicant WHERE gender=? AND LOWER(applicant_name) LIKE ? AND status = 'PENDING'";
		List<ApplicantModel> applicants = new ArrayList<>();

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, gender);
			stmt.setString(2, "%" + name.toLowerCase() + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ApplicantModel applicantModel = new ApplicantModel();

				applicantModel.setApplicantID(rs.getInt("applicant_id"));
				applicantModel.setApplicantName(rs.getString("applicant_name"));
				applicantModel.setApplicantNRC(rs.getString("applicant_nrc"));
				applicantModel.setFatherName(rs.getString("father_name"));
				applicantModel.setDobofNRC(rs.getDate("dob_ofnrc").toLocalDate());
				applicantModel.setGender(rs.getString("gender"));
				applicantModel.setMaritalStatus(rs.getString("marital_status"));
				applicantModel.setAddress(rs.getString("address"));
				applicantModel.setPhoneNo(rs.getString("phone_no"));
				applicantModel.setEducation(rs.getString("education"));
				applicantModel.setMajor(rs.getString("major"));
				applicantModel.setDegreeType(rs.getString("degree_type"));
				applicantModel.setMail(rs.getString("mail"));
				applicantModel.setMark(rs.getInt("mark"));
				applicantModel.setStatus(ApplicantStatus.valueOf(rs.getString("status")));

				applicants.add(applicantModel);

			}
		} catch (Exception e) {
			e.printStackTrace(); // or use a logger
		}

		return applicants;
	}
	
	public boolean updateApplicants(ApplicantModel applicant, int id) {
		final String sql = "UPDATE applicant SET "
			    + "applicant_name = ?, "
			    + "applicant_nrc = ?, "
			    + "father_name = ?, "
			    + "dob_ofnrc = ?, "
			    + "gender = ?, "
			    + "marital_status = ?, "
			    + "address = ?, "
			    + "phone_no = ?, "
			    + "education = ?, "
			    + "major = ?, "
			    + "degree_type = ?, "
			    + "mail = ?, "
			    + "mark = ?, "
			    + "status = ? "
			    + "WHERE applicant_id = ?";
		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			//System.out.println("Updating applicant with ID: " + applicant.getApplicantID());
			stmt.setString(1, applicant.getApplicantName());
			stmt.setString(2, applicant.getApplicantNRC());
			stmt.setString(3, applicant.getFatherName());
			stmt.setDate(4, java.sql.Date.valueOf(applicant.getDobofNRC())); // converting LocalDate to java.sql.Date
			stmt.setString(5, applicant.getGender());
			stmt.setString(6, applicant.getMaritalStatus());
			stmt.setString(7, applicant.getAddress());
			stmt.setString(8, applicant.getPhoneNo());
			stmt.setString(9, applicant.getEducation());
			stmt.setString(10, applicant.getMajor());
			 String degreeType = applicant.getDegreeType();
		        if (degreeType == null || degreeType.trim().isEmpty()) {
		            // Set a default value if degree_type is null or empty (e.g., "Not Specified")
		            stmt.setString(11, "Not Specified");
		        } else {
		            stmt.setString(11, degreeType);
		        }
			stmt.setString(12, applicant.getMail());
			stmt.setInt(13, applicant.getMark());
			stmt.setString(14, applicant.getStatus().name());
			stmt.setInt(15, id);

			int updateRow = stmt.executeUpdate();
			return updateRow > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isDuplicateNRC(String data) {
		boolean duplicate = false;

		final String sql = "SELECT * FROM applicant WHERE applicant_nrc=?";

		try (Connection con = DBConfig.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, data);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				duplicate = true;
			} else {
				duplicate = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return duplicate;
	}

	public static void main(String[] args) {
		DBConfig.getConnection();

	}
}
