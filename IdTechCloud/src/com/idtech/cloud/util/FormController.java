package com.idtech.cloud.util;

public class FormController {
	
	public static boolean nameCheck(String name){
        return name.matches("^[a-zA-Z\\-\\d éèàçùëüïôäâêûîô@#&]+$");
    }
	public static boolean addressCheck(String address){
        return address.matches("^[a-zA-Z\\-\\d éèàçùëüïôäâêûîô@#&]+$");
    }
	public static boolean cityCheck(String city){
        return city.matches("^[a-zA-Z\\- éèàçùëüïôäâêûîô]+$");
    }
	public static boolean emailCheck(String email){
        return email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}");
    }
	public static boolean loginCheck(String login){
        return login.matches("^[a-zA-Z\\d]+$");
    }
	public static boolean passwordCheck(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$])[A-Za-z\\d!@#$]{8,}$");
    }
	public static boolean firstnameCheck(String firstname){
        return firstname.matches("^[a-zA-Z\\-éèàçùëüïôäâêûîô]+$");
    }
	public static boolean lastnameCheck(String lastname){
        return lastname.matches("^[a-zA-Z\\-éèàçùëüïôäâêûîô]+$");
    }
	public static boolean phoneCheck(String phone){
        return phone.matches("^0[1-9]([-. ]?[0-9]{2}){4}$");
    }
	public static boolean roleCheck(String role){
        return (role.matches("administrateur") || role.matches("utilisateur")) ;
    }
}
