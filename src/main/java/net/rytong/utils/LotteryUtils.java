package net.rytong.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LotteryUtils {
	static List<String> list1 = new ArrayList<String>();
	static List<String> list2 = new ArrayList<String>();

	public static void main(String[] args) throws Exception {
//		try {
//			String[] bs = new String[]{"01","02","03","04","05","06","07","08","09","10",
//									   "11","12","13","14","15","16","17","18","19","20",
//									   "21","22","23","24","25","26","27","28","29","30",
//									   "31","32","33","34","35"
//									  };
//			String[] bsx = new String[]{"10", "15", "35", "26", "04"};
//			m1();
//			m2();
//			ArrayList<String> bCombine = getArrangeOrCombine(null, subNum(bs, bsx), 5, true);
//			System.out.println("Total b:"+bCombine.size());
//			for (int i = 0; i < bCombine.size(); i++) {
//				if (list1.contains(bCombine.get(i)) || list2.contains(bCombine.get(i))) {
//					bCombine.remove(i);
//				}
//			}
//			for (int i = 0; i < bCombine.size(); i++) {
//				if ("1622272931".equals(bCombine.get(i))) {
//					System.out.println("Hit");
//					break;
//				}
//			}
//			System.out.println("Total f:"+bCombine.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		String ball = "17 20 21 26 32";
		List<String> list = getSperated(ball);
		System.out.println("Total f:"+list.size());
	}
	
	private static void m1() throws Exception {
		String[] m1s = new String[]{
					"06 07 09 11 17 19 26 27 29",
					"09 11 12 13 33",
					"11 12 13 32 33",
					
				};
		for (int i = 0; i < m1s.length; i++) {
			ArrayList<String> bCombine = getArrangeOrCombine(null, m1s[i].split(" "), 5, true);
			for (int j = 0; j < bCombine.size(); j++) {
				list1.add(bCombine.get(j));
			}
		}
	}
	
	public static List<String> getSperated(String str) throws Exception {
		int indexS = str.indexOf("(");
		if (indexS < 0) {
			return getSperatedWithOutS(str);
		} else {
			return getSperatedWithS(str);
		}
	}
	
	
	
	private static List<String> getSperatedWithOutS(String s) throws Exception {
		List<String> list = new ArrayList<String>();
		ArrayList<String> bCombine = getArrangeOrCombine(null, s.split(" "), 5, false);
		for (int j = 0; j < bCombine.size(); j++) {
			list.add(bCombine.get(j));
		}
		return list;
	}
	
	private static List<String> getSperatedWithS(String s) throws Exception {
		List<String> list = new ArrayList<String>();
		int k1 = s.indexOf("(");
		int k2 = s.indexOf(")");
		String[] b1 = s.substring(k1 + 1, k2).split(" ");
		String[] f1 = s.substring(k2 + 2).split(" ");
		
		ArrayList<String> bCombine = getArrangeOrCombine(b1, f1, 5 - b1.length, false);
		for (int j = 0; j < bCombine.size(); j++) {
			list.contains(bCombine.get(j));
		}
		return list;
	}
	
	private static void m2() throws Exception {
		String[] m2s = new String[]{
					"(11 12 13) 01 02 03",
					"(11 12 13) 01 02 03 04 05 06 07 08 09 10 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35",
					"(06 07 17 26) 01 02 03 04 05 08 09 10 11 12 13 14 15 16 18 19 20 21 22 23 24 25 27 28 29 30 31 32 33 34 35",
					"(02 03 12 13) 01 04 05 06 07 08 09 10 11 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35",
					"(32 33 34 35) 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31",
					"(03 04 05 06) 01 02 07 08 09 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35",
					"(13 26 27 28) 01 02 03 04 05 06 07 08 09 10 11 12 14 15 16 17 18 19 20 21 22 23 24 25 29 30 31 32 33 34 35",
					"(09 10 20 30) 01 02 03 04 05 06 07 08 11 12 13 14 15 16 17 18 19 21 22 23 24 25 26 27 28 29 31 32 33 34 35",
				};
		for (int i = 0; i < m2s.length; i++) {
			String s = m2s[i];
			int k1 = s.indexOf("(");
			int k2 = s.indexOf(")");
			String[] b1 = s.substring(k1 + 1, k2).split(" ");
			String[] f1 = s.substring(k2 + 2).split(" ");
			
			ArrayList<String> bCombine = getArrangeOrCombine(b1, f1, 5 - b1.length, true);
			for (int j = 0; j < bCombine.size(); j++) {
				list2.contains(bCombine.get(j));
			}
		}
	}
	
	public static String[] subNum(String[] f1, String[] f2) {
		List<String> f11 = Arrays.asList(f1);
		String[] r = new String[f1.length - f2.length];
		Map<String, String> map = new HashMap<String,String>();
		
		for (String str : f2) {
			map.put(str, str);
		}
		
		for (int i = 0,j = 0; i < f11.size(); i++) {
			if (!map.containsKey(f11.get(i))) {
				r[j++] = f11.get(i);
			}
		}
		return r;
	}
	
	public static String[] addNum(String[] f1, String[] f2) {
		String[] r = new String[f1.length + f2.length];
		
		int j = 0;
		for (int i = 0; i < f1.length; i++) {
			r[j++] = f1[i];
		}
		for (int i = 0; i < f2.length; i++) {
			r[j++] = f2[i];
		}
		return r;
	}
	
	public static ArrayList<String> getArrangeOrCombineAndType(String[] args000, String[] args,int n) throws Exception{
		if (args.length<=0) {
			throw new Exception("array.length<=0");
		}
		if (n>args.length) {
			throw new Exception(" n>array.length");
		}
		Combination comb = new Combination();
		comb.mn(args, n);

		ArrayList<String> arrangeList = new ArrayList<String>();
		for (int i = 0; i < comb.getCombList().size(); i++) {
			String[] list = comb.getCombList().get(i).split(",");
			if (args000 != null) {
				list = addNum(list, args000);
			}
			Arrays.sort(list);
			
			String type = "0";
			int a1 = Integer.valueOf(list[0]);
			int a2 = Integer.valueOf(list[1]);
			int a3 = Integer.valueOf(list[2]);
			int a4 = Integer.valueOf(list[3]);
			int a5 = Integer.valueOf(list[4]);
			
			if (a5 - a1 <= 7) { // 过渡连号
				type = "1";
			}
			
			int sum = a1 + a2 + a3 + a4 + a5;
			if (sum < 50 || sum > 130) {
				type = "2";
			}
			
			int[] aiii = new int[]{2,3,4,7,10};
			boolean isF = getF(aiii, a1, a2, a3, a4, a5);
			if (isF) {
				type = "3";
			}
			int a115 = a1 % 5;
			int a125 = a2 % 5;
			int a135 = a3 % 5;
			int a145 = a4 % 5;
			int a155 = a5 % 5;
			
			if (a115 == a125 && a115 == a135 && a115 == a145 && a115 == a155) {
				type = "4";
			}
			
			int e1 = Integer.valueOf(list[0].substring(1));
			int e2 = Integer.valueOf(list[1].substring(1));
			int e3 = Integer.valueOf(list[2].substring(1));
			int e4 = Integer.valueOf(list[3].substring(1));
			int e5 = Integer.valueOf(list[4].substring(1));
			
			int em = e1 + e2 + e3 + e4 + e5;
			if (em < 8 || em > 33) {
				type = "5";
			}
			
			//排除4连号
			int s111 = a4 - a1;
			int s211 = a5 - a2;
			if (s111 == 3 || s211 == 3) {
				type = "6";
			}
			
			//排除3连号
			int s1111 = a3 - a1;
			int s2111 = a4 - a2;
			int s3111 = a5 - a3;
			
			if (s1111 == 2 || s2111 == 2 || s3111 == 2) {
				type = "7";
			}
			
			//期号同尾超1
			int e1000000 = 0, e1000000f = 0;
			String s1000000 = e1 + "," + e2 + "," + e3 + "," + e4 + "," + e5;
			String[] s1000000a = s1000000.split(",");
			for (int ik = 0; ik < s1000000a.length; ik++) {
				if (s1000000a[ik].equals(e1000000 + "")) {
					e1000000f++;
				}
			}
			if (e1000000f > 1) {
				type = "8";
			}
			
			//有两组邻号
			if (a2 - a1 == 1 && a4 - a3 == 1) {
				type = "9";
			}
			if (a2 - a1 == 1 && a5 - a4 == 1) {
				type = "9";
			}
			if (a3 - a2 == 1 && a5 - a4 == 1) {
				type = "9";
			}
			
			arrangeList.add(com(list) + "_" + type);
		}
		return arrangeList;
	}
	
	public static ArrayList<String> getArrangeOrCombine(String[] args000, String[] args,int n, boolean flag) throws Exception{
		if (args.length<=0) {
			throw new Exception("array.length<=0");
		}
		if (n>args.length) {
			throw new Exception(" n>array.length");
		}
		Combination comb = new Combination();
		comb.mn(args, n);

		ArrayList<String> arrangeList = new ArrayList<String>();
		for (int i = 0; i < comb.getCombList().size(); i++) {
			String[] list = comb.getCombList().get(i).split(",");
			if (args000 != null) {
				list = addNum(list, args000);
			}
			Arrays.sort(list);
			if (flag) {
				int a1 = Integer.valueOf(list[0]);
				int a2 = Integer.valueOf(list[1]);
				int a3 = Integer.valueOf(list[2]);
				int a4 = Integer.valueOf(list[3]);
				int a5 = Integer.valueOf(list[4]);
				
				
				
				if (a5 - a1 <= 8) { // 过渡连号
					continue;
				}
				
				int sum = a1 + a2 + a3 + a4 + a5;
				if (sum < 50 || sum > 130) {
					continue;
				}
				
				int[] aiii = new int[]{2,3,4,7,10};
				boolean isF = getF(aiii, a1, a2, a3, a4, a5);
				if (isF) {
					continue;
				}
				
				
				int a115 = a1 % 5;
				int a125 = a2 % 5;
				int a135 = a3 % 5;
				int a145 = a4 % 5;
				int a155 = a5 % 5;
				
				if (a115 == a125 && a115 == a135 && a115 == a145 && a115 == a155) {
					continue;
				}
				
				
				int e1 = Integer.valueOf(list[0].substring(1));
				int e2 = Integer.valueOf(list[1].substring(1));
				int e3 = Integer.valueOf(list[2].substring(1));
				int e4 = Integer.valueOf(list[3].substring(1));
				int e5 = Integer.valueOf(list[4].substring(1));
				
				int em = e1 + e2 + e3 + e4 + e5;
				if (em < 8 || em > 33) {
					continue;
				}
				
				//排除4连号
				int s111 = a4 - a1;
				int s211 = a5 - a2;
				if (s111 == 3 || s211 == 3) {
					continue;
				}
				
				//排除3连号
				int s1111 = a3 - a1;
				int s2111 = a4 - a2;
				int s3111 = a5 - a3;
				
				if (s1111 == 2 || s2111 == 2 || s3111 == 2) {
					continue;
				}
				
				//期号同尾超1
				int e1000000 = 0, e1000000f = 0;
				String s1000000 = e1 + "," + e2 + "," + e3 + "," + e4 + "," + e5;
				String[] s1000000a = s1000000.split(",");
				for (int ik = 0; ik < s1000000a.length; ik++) {
					if (s1000000a[ik].equals(e1000000 + "")) {
						e1000000f++;
					}
				}
				if (e1000000f > 1) {
					continue;
				}
				
				//有两组邻号
				if (a2 - a1 == 1 && a4 - a3 == 1) {
					continue;
				}
				if (a2 - a1 == 1 && a5 - a4 == 1) {
					continue;
				}
				if (a3 - a2 == 1 && a5 - a4 == 1) {
					continue;
				}
				
				arrangeList.add(com(list));
			} else {
				arrangeList.add(com(list));
			}
		}
		return arrangeList;
	}
	
	public static boolean getF(int[] aiii, int a1, int a2, int a3, int a4, int a5) {
		for (int ik = 0; ik < aiii.length; ik++) {
			if (a1 % aiii[ik] == 0 && a2 % aiii[ik] == 0 && a3 % aiii[ik] == 0 && a4 % aiii[ik] == 0 && a1 % aiii[ik] == 0) {
				return true;
			}
			if (a1 % aiii[ik] == 1 && a2 % aiii[ik] == 1 && a3 % aiii[ik] == 1 && a4 % aiii[ik] == 1 && a1 % aiii[ik] == 1) {
				return true;
			}
		}
		return false;
	}
	
	public static String com(String[] strs) {
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < strs.length; i++) {      
			s.append(strs[i]).append(",");
		}
		return s.deleteCharAt(s.length() - 1).toString();
	}
}

class Combination {
	
	private ArrayList<String> combList= new ArrayList<String>();

	public void mn(String[] array, int n) {
		int m = array.length;
		if (m < n)
			throw new IllegalArgumentException("Error   m   <   n");
		BitSet bs = new BitSet(m);
		for (int i = 0; i < n; i++) {
			bs.set(i, true);
		}
		do {
			printAll(array, bs);
		} while (moveNext(bs, m));

	}
	/**
	 * 1、start 第一个true片段的起始位，end截止位
	 * 2、把第一个true片段都置false
	 * 3、数组从0下标起始到以第一个true片段元素数量减一为下标的位置都置true
	 * 4、把第一个true片段end截止位置true
	 * 
	 * @param bs 数组是否显示的标志位
	 * @param m 数组长度
	 * @return boolean 是否还有其他组合
	 */
	private boolean moveNext(BitSet bs, int m) {
		int start = -1;
		while (start < m)
			if (bs.get(++start))
				break;
		if (start >= m)
			return false;

		int end = start;
		while (end < m)
			if (!bs.get(++end))
				break;
		if (end >= m)
			return false;
		for (int i = start; i < end; i++)
			bs.set(i, false);
		for (int i = 0; i < end - start - 1; i++)
			bs.set(i);
		bs.set(end);
		return true;
	}
	
	/**
	 * 输出生成的组合结果
	 * 
	 * @param array 数组
	 * @param bs 数组元素是否显示的标志位集合
	 */
	private void printAll(String[] array, BitSet bs) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++)
			if (bs.get(i)) {
				sb.append(array[i]).append(',');
			}
		sb.setLength(sb.length() - 1);
		combList.add(sb.toString());
	}
	
	public ArrayList<String> getCombList() {
		return combList;
	}
}