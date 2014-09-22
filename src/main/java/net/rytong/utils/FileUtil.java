package net.rytong.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import net.rytong.entity.Article;
import net.rytong.entity.Page;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	
	/**
	 * 读取配置文件中的某一项
	 * @param name
	 * @return
	 */
	public static synchronized String getProperties(String filename,String urlName){
		try {
            Properties properties = new Properties();
            InputStream file = FileUtil.class.getClassLoader().getResourceAsStream(filename);
            properties.load(file);
            return properties.getProperty(urlName);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;

	}
	
	/**
	 * 读取文件内容
	 * @param name
	 * @return
	 */
	public static String getFileContent(String filename){
		InputStream file = FileUtil.class.getClassLoader().getResourceAsStream(filename);
		return inputStream2String(file, "UTF-8");

	}
	
	public static String inputStream2String(InputStream is, String charset) {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
			return baos.toString(charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				baos = null;
			}
		}
		return null;
	}
	
	/**
	 * 读取
	 * @param name
	 * @return
	 */
	public static synchronized String readFile(String filename){
		String result = "";
		try {
			FileInputStream stream = new FileInputStream(filename);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream,  "utf-8"));
			String lineTxt = null;
			StringBuffer sb = new StringBuffer();
			while((lineTxt = bufferedReader.readLine()) != null){
	        	sb.append(lineTxt);
	        }
			result = sb.toString();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return result;

	}
	
	/**
	 * 替换
	 * @param name
	 * @return
	 */
	public static synchronized boolean replaceRegex(String filename, String regex, String replacement){
		try {
			String content = readFile(filename);
			content = content.replaceAll(regex, replacement);
			saveFile(filename, content);
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 替换
	 * @param name
	 * @return
	 */
	public static synchronized boolean replaceRegex(String filename, List<String> regexs, List<String> replacements){
		try {
			String content = readFile(filename);
			for (int i = 0; i < regexs.size(); i++) {
				content = content.replaceAll(regexs.get(i), replacements.get(i));
			}
			saveFile(filename, content);
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 保存
	 * @param name
	 * @return
	 */
	public static synchronized boolean saveFile(String filename, String content){
		try {
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			} 
			OutputStream fis = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fis, "utf-8");
			osw.write(content);
			osw.flush();
			osw.close();
		} catch (Exception e1) {
			return false;
		}
		return true;
	}
	
	public static boolean updatePage(String mainPage, String mobanPath, String articlePath, String distPath, Page page, List<Article> articles) {
		try {
			String content = readFile(mobanPath);
			String articleContent = readFile(articlePath);
			if (page != null) {
				int level = page.getLevel();
				String title = page.getTitle() != null ? page.getTitle() : "";
				String bgImage = page.getBgImage() != null ? page.getBgImage() : "";
				String url = page.getUrl() != null ? page.getUrl() : "";
				String titleBgColor = page.getTitleBgColor() != null ? page.getTitleBgColor() : ""; 
				String bannerImage = page.getBannerImage() != null ? page.getBannerImage() : "";
				
				content = content.replaceAll("\\{PAGE_MAIN\\}", url);
				content = content.replaceAll("\\{PAGE_TITLE\\}", String.valueOf(title));
				content = content.replaceAll("\\{PAGE_BGIMAGE\\}", String.valueOf(bgImage));
				content = content.replaceAll("\\{PAGE_TITLEBGCOLOR\\}", String.valueOf(titleBgColor));
				content = content.replaceAll("\\{PAGE_BANNERIMAGE\\}", String.valueOf(bannerImage));
				
				if (level != 1) {
					String index = "<a href=\"javascript:history.go(-1);\" class=\"back01\"><img src=\"./content_files/website_back01.gif\"></a>";
					index += "<a href=" + mainPage + " style=\"left:50px;\" class=\"back01\"><img src=\"./content_files/website_back02.gif\"></a>";
					content = content.replaceAll("\\{PAGE_NVA\\}", index);
				} else {
					content = content.replaceAll("\\{PAGE_NVA\\}", "");
				}
				
				if (articles != null && articles.size() > 0)  {
					StringBuffer sb = new StringBuffer();
					List<Article> list = new ArrayList<Article>(articles);
					for (int i = 0; i < list.size(); i++) {
						Article article = list.get(i);
						String beginStr = articleContent;
						beginStr = beginStr.replaceAll("\\{ARTICLE_BGCOLOR\\}", article.getBgColor());
						beginStr = beginStr.replaceAll("\\{ARTICLE_TYPE\\}", article.getStyle());
						Page atPage = article.getLinkPage();
						if (atPage == null) {
							beginStr = beginStr.replaceAll("\\{ARTICLE_LINK\\}", "");
						} else {
							String str = "<a style='" + article.getStyle() + "' href='" + atPage.getUrl() + "'><span>" + article.getTitle() + "</span></a>";
							beginStr = beginStr.replaceAll("\\{ARTICLE_LINK\\}", str);
						}
						sb.append(beginStr);
					}
					content = content.replaceAll("\\{PAGE_ARTICLE\\}", sb.toString());
				} else {
					content = content.replaceAll("\\{PAGE_ARTICLE\\}", "");
				}
			}
			saveFile(distPath, content);
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}	
	
	
	/**
	 * 读取配置文件
	 * @return
	 */
	public static synchronized Properties getProperties(String propFile){
		try {
            Properties properties = new Properties();
            InputStream file = FileUtil.class.getClassLoader().getResourceAsStream(propFile);
            properties.load(file);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * 用于记录东航手机值信息
	 * @param info
	 */
	public static synchronized void printToFile(String info){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date).toString();
		File file = new File("/usr/tmp/logs/" + dateStr + ".txt");
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			
			String readFileToString = FileUtils.readFileToString(file, "utf-8");
			String context = readFileToString + "\n" + info;
			FileUtils.writeStringToFile(file, context, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
