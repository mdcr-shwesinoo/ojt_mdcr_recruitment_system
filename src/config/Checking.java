package config;

import controller.ApplicantController;

public class Checking {
	//public String nameFormat = "^[A-Za-z]+([A-Za-z]+)*$";

	public static boolean IsEmpty(String str) {
	    return str == null || str.trim().isEmpty();
	}
	
	public static boolean IsValidName(String name) {
		return name != null && name.trim().matches("[a-zA-Z  ]+");
	   
	}
	public static boolean IsValidEmail(String email) {
	   // String emailFormat = "A-Za-z0-9._%+-]+@[A-Za-z0-9._]+\\.[A-Za-z]{2,}$";
	    String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
	    return email.matches(emailRegex);
	}
	
	// Password Validation
	public boolean IsValidatePassword(String password) {
		String passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*#?&]).{7,}$";
		return password.matches(passwordRegex);
	}
	public static boolean IsValidPhone(String phone) {
	    String phoneFormat = "[0-9]{5,11}";
	    return phone.matches(phoneFormat);
	}
	
	public static boolean isValidAddress(String address) {
	    // Allow letters, digits, spaces, commas, dots, hyphens, and hashes (e.g., apt #)
	    String addressPattern = "^[a-zA-Z0-9\\s,\\.\\-#]+$";
	    return address.matches(addressPattern);
	}
	public static boolean isValidMark(String mark) {
	    // Regex: number without leading zero, 1 to 3 digits, digits 0-9 allowed except leading zero
	    String markFormat = "[1-9][0-9]{0,2}";

	    if (!mark.matches(markFormat)) {
	        return false;
	    }

	    try {
	        int value = Integer.parseInt(mark);
	        return value >= 240 && value <= 600;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public static boolean isNrcDuplicate(String nrc) {
		ApplicantController ac = new ApplicantController();
		return ac.isDuplicateNRC(nrc);
	}

	
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		

	}

}
