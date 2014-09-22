package net.rytong.utils;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class EscapeUtil {
	private static LinkedHashMap<String, String> US_CITY_CODE_NAME = null;
	
	public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);

        for (i = 0; i < src.length(); i++) {

            j = src.charAt(i);

            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }
	
	public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

	/**
	 * 针对国家名称 分离出字母 保留汉字
	 * @param orgStr
	 * @return
	 */
	public static String separateLetter(String orgStr){
//		String str = "SIERRALE ONE塞拉利昂";
		Pattern p = Pattern.compile("([\\w ]+)([\u4e00-\u9fa5]+)");
		Matcher ma = p.matcher(orgStr);
		if(ma.find()){
			return ma.group(2);
		}
		return "";
	}
	
	public static void init1(){
		US_CITY_CODE_NAME = new LinkedHashMap<String,String>();
		US_CITY_CODE_NAME.put("AL", "Alabama亚拉巴马");
		US_CITY_CODE_NAME.put("AK", "Alaska阿拉斯加");
		US_CITY_CODE_NAME.put("AZ", "Arizona亚力桑那");
		US_CITY_CODE_NAME.put("AR", "Arkansas阿肯色");
		US_CITY_CODE_NAME.put("CA", "California加利福尼亚");
		US_CITY_CODE_NAME.put("CO", "Colorado科罗拉多");
		US_CITY_CODE_NAME.put("CT", "Connecticut康涅狄格");
		US_CITY_CODE_NAME.put("DE", "Delaware特拉华");
		US_CITY_CODE_NAME.put("FL", "Florida佛罗里达");
		US_CITY_CODE_NAME.put("GA", "Georgia佐治亚");
		US_CITY_CODE_NAME.put("HI", "Hawaii夏威夷");
		US_CITY_CODE_NAME.put("ID", "Idaho爱达荷");
		US_CITY_CODE_NAME.put("IL", "Illinois伊利诺斯");
		US_CITY_CODE_NAME.put("IN", "Indiana印第安纳");
		US_CITY_CODE_NAME.put("IA", "Iowa依阿华");
		US_CITY_CODE_NAME.put("KS", "Kansas堪萨斯");
		US_CITY_CODE_NAME.put("KY", "Kentucky肯塔基");
		US_CITY_CODE_NAME.put("LA", "Louisiana路易斯安那");
		US_CITY_CODE_NAME.put("ME", "Maine缅因");
		US_CITY_CODE_NAME.put("MD", "Maryland马里兰");
		US_CITY_CODE_NAME.put("MA", "Massachusetts马萨诸塞（麻省）");
		US_CITY_CODE_NAME.put("MI", "Michigan密歇根");
		US_CITY_CODE_NAME.put("MN", "Minnesota明尼苏达");
		US_CITY_CODE_NAME.put("MS", "Mississippi密西西比");
		US_CITY_CODE_NAME.put("MO", "Missouri密苏里");
		US_CITY_CODE_NAME.put("MI", "Montana蒙大拿");
		US_CITY_CODE_NAME.put("NE", "Nebraska内布拉斯加");
		US_CITY_CODE_NAME.put("NV", "Nevada内华达");
		US_CITY_CODE_NAME.put("NH", "New Hampshire新罕布什尔");
		US_CITY_CODE_NAME.put("NJ", "New Jersey新泽西");
		US_CITY_CODE_NAME.put("NM", "New Mexico新墨西哥");
		US_CITY_CODE_NAME.put("NY", "New York纽约");
		US_CITY_CODE_NAME.put("NC", "North Carolina北卡罗来纳");
		US_CITY_CODE_NAME.put("ND", "North Dakota北达科他");
		US_CITY_CODE_NAME.put("OH", "Ohio俄亥俄");
		US_CITY_CODE_NAME.put("OK", "Oklahoma俄克拉荷马");
		US_CITY_CODE_NAME.put("OR", "Oregon俄勒冈");
		US_CITY_CODE_NAME.put("PA", "Pennsylvania宾夕法尼亚");
		US_CITY_CODE_NAME.put("RI", "Rhode Island罗德岛");
		US_CITY_CODE_NAME.put("SC", "South Carolina南卡罗来纳");
		US_CITY_CODE_NAME.put("SD", "South Dakota南达科他");
		US_CITY_CODE_NAME.put("TN", "Tennessee田纳西");
		US_CITY_CODE_NAME.put("TX", "Texas得克萨斯");
		US_CITY_CODE_NAME.put("UI", "Utah犹他");
		US_CITY_CODE_NAME.put("VI", "Vermont佛蒙特");
		US_CITY_CODE_NAME.put("VA", "Virginia弗吉尼亚");
		US_CITY_CODE_NAME.put("WA", "Washington华盛顿");
		US_CITY_CODE_NAME.put("WV", "West Virginia西弗吉尼亚");
		US_CITY_CODE_NAME.put("WI", "Wisconsin威斯康星");
		US_CITY_CODE_NAME.put("WY", "Wyoming怀俄明");
	}
	/**
	 * 根据美国城市代号返回对应的省份名称
	 * @param code
	 * @return
	 */
	public static String getCityNameByCode(String code){
		if(US_CITY_CODE_NAME==null){
			init1();
		}
		if(!StringUtils.isNotBlank(code)){
			return "";
		}
		String name = US_CITY_CODE_NAME.get(code);
		return name==null?"":name;
	}
}
