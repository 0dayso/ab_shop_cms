package net.rytong.utils;

public class ASCII {
	
	public static String asciiToString(String str){//ASCII转换为字符串

		StringBuilder sb = new StringBuilder();
		String[]chars=str.split("\\|");
        for(int i=0;i<chars.length;i++){ 
            sb.append((char)Integer.parseInt(chars[i]));
        }
        return sb.toString();
	}
	 public static String stringToASCII(String str){//字符串转换为ASCII码

		  char[]chars=str.toCharArray(); 
		  StringBuilder sb = new StringBuilder("");
		  for(int i=0;i<chars.length;i++){//输出结果
		      sb.append((int)chars[i]).append("|");
		  }
		  return sb.toString();
	 }

}
