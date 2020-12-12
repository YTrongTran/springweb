package com.learncode.Mahoa;


import org.springframework.security.crypto.bcrypt.BCrypt;

public class Mahoa {
	
	
	public static String giaiMd5(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public static boolean checkMd5(String p1, String p2) {
		return BCrypt.checkpw(p1, p2);
	}
}
