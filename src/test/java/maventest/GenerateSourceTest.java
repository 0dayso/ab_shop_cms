package maventest;

import java.io.File;
import java.io.IOException;

import net.rytong.utils.FileUtil;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class GenerateSourceTest extends BaseTest {
	private static final String BASE_PACK = "net.rytong";
	private static final String BASE_PATH = System.getProperty("user.dir") + "\\src\\main\\java\\net\\rytong\\";
	private static String[] paths = {"entity", "dao", "jpa", "service", "impl", "action"};
	
	@Test
	public void testGenClass() throws IOException {
		String distEntityName = "LotteryBuyNumber", distTableName = "lottery_buy_number";
		for (int i = 0; i < paths.length; i++) {
			String mobanName = getMobanName("EntityTemplate", i);
			String className = getMobanName(distEntityName, i);
			String entityS = FileUtil.getFileContent("moban/" + mobanName + ".moban");
			entityS = entityS.replaceAll("EntityTemplate", distEntityName); 
			entityS = entityS.replaceAll("entity_template", distTableName);
			entityS = entityS.replaceAll("net.rytong", BASE_PACK);
			FileUtils.writeStringToFile(new File(BASE_PATH + paths[i] + "\\" + className + ".java"), entityS, "UTF-8");
		}
	}

	private String getMobanName(String moban, int i) {
		String name = moban;
		switch (i) {
		case 1:
			name = "I" + name + "DAO";
			break;
		case 2:
			name = name + "DAO";
			break;
		case 3:
			name = "I" + name + "Service";
			break;
		case 4:
			name = name + "ServiceImpl";
			break;
		case 5:
			name = name + "Action";
			break;
		default:
			break;
		}
		return name;
	}
}
