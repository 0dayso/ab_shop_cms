package net.rytong.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PublicUtils {

	public static String encryptMD5(String password) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0){
					i += 256;
				}
				if (i < 16){
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	
	public static String parseString(Object str){
		if(str == null){
			return "";
		}else{
			return String.format("%08d",str);
		}
	}
}
