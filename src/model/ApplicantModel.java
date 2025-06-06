package model;

import java.time.LocalDate;

public class ApplicantModel {
	private int applicantID;
	private String applicantName;
	private String applicantNRC;
	private String fatherName;
	private LocalDate dobofNRC;
	private String gender;
	private String maritalStatus;
	private String address;
	private String phoneNo;
	private String education;
	private String major;
	private String mail;
	private String degreeType;
	private int mark;
	private ApplicantStatus status;
	
	public enum ApplicantStatus{
		PENDING, ACCEPT, REJECT
	}

	public int getApplicantID() {
		return applicantID;
	}

	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getApplicantNRC() {
		return applicantNRC;
	}

	public void setApplicantNRC(String applicantNRC) {
		this.applicantNRC = applicantNRC;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public LocalDate getDobofNRC() {
		return dobofNRC;
	}

	public void setDobofNRC(LocalDate dobofNRC) {
		this.dobofNRC = dobofNRC;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public ApplicantStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicantStatus status) {
		this.status = status;
	}
	
	

}
