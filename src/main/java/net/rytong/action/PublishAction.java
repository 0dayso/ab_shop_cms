package net.rytong.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.rytong.service.IParameterService;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 日志查看
 * 
 * @author WuWeihua
 * @date 2010-11-24
 */
@Component
@Scope("prototype")
public class PublishAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IParameterService iParameterService;
	protected Logger logger = LoggerFactory.getLogger(getClass().getName());
	private File fileInput;
	private String fileInputContentType;
	private String fileInputFileName;
	private String commands;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	// 查询日志列表
	public String upload() throws Exception {
		//String savePath = ServletActionContext.getRequest().getRealPath("") + "/uploadFile/";
		//String extName = "";// 扩展名
		//String newFileName = "";// 新文件名
		//System.out.println("原文件名称：" + fileInputFileName);
		// 获取扩展名
		//if (fileInputFileName.lastIndexOf(".") > -1) {
		//	extName = fileInputFileName.substring(fileInputFileName.lastIndexOf("."));
		//}
		//String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());// 当前时间
		//newFileName = nowTime + extName;
		//System.out.println("保存的文件名称：" + savePath + newFileName);
		String path = iParameterService.getParameterByCodeAndNameAndCustomer("SERVER", "IMAGE_PATH", "0").get(0).getValue();
		fileInput.renameTo(new File(path + fileInputFileName));
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
        json.put("fileName", fileInputFileName);
 		response.setCharacterEncoding("utf-8");
 		response.getWriter().print(json.toString());
		return NONE;
	}

	// 命令下发并反馈结果
	public void commandsDown() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String result = "下发失败";
		
		if (StringUtils.isNotBlank(commands)) {
			try {        
				logger.info(commands);
				String[] params = {"/bin/sh","-c",commands};
				Process process = Runtime.getRuntime().exec(params);      
				logger.info(process.toString());
				InputStreamReader ir = new InputStreamReader(process.getInputStream());
				logger.info(ir.toString() + "--it");
				BufferedReader input = new BufferedReader (ir);
				logger.info(input.toString() + "--input");
				StringBuffer sb = new StringBuffer();
				String line;  
				System.out.println(input.read() + "----");
				while ((line = input.readLine()) != null) {
					logger.info(line);
					sb.append(line).append("\n");
				}
				logger.info(sb.toString());
				result = sb.toString();
			} catch (java.io.IOException e){           
				System.err.println ("IOException " + e.getMessage());
			}
		}
		logger.info(result);
		out.print(result);
	}

	public File getFileInput() {
		return fileInput;
	}

	public void setFileInput(File fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputContentType() {
		return fileInputContentType;
	}

	public void setFileInputContentType(String fileInputContentType) {
		this.fileInputContentType = fileInputContentType;
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public String getCommands() {
		return commands;
	}

	public void setCommands(String commands) {
		this.commands = commands;
	}
}