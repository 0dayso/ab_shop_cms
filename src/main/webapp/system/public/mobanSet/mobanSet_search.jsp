<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_parameter.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/dateTime.js"></script>
	</head>
	<body>
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">模板管理</font></span>
    	</div>
    	<div class="main">
			<table
				style="line-height: 30px; border: 1px solid #ccc; padding: 10px; background: #f4f4f4; width: 100%;">
				<tbody>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">
							请选择微站模板：
						</td>
					</tr>
					<tr>
						<td>
							<table id="ctl00_ContentPlaceHolder1_wxSkins" cellspacing="0"
								border="0" style="border-collapse: collapse;">
								<tbody>
									<tr>
										<td>
											<table style="margin-right: 10px;">
												<tbody>
													<tr>
														<td>
															<img
																id="ctl00_ContentPlaceHolder1_wxSkins_ctl00_wxImgUrl"
																src="<%=request.getContextPath()%>/public/images/moban/default.jpg"
																style="height: 400px; width: 250px; border-width: 0px;">
														</td>
													</tr>
													<tr>
														<td>
															<input type="radio" name="skinId" id="skinid_default"
																value="default">
															<label for="skinid_default">
																默认皮肤
															</label>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
										<td>
											<table style="margin-right: 10px;">
												<tbody>
													<tr>
														<td>
															<img
																id="ctl00_ContentPlaceHolder1_wxSkins_ctl01_wxImgUrl"
																src="<%=request.getContextPath()%>/public/images/moban/newskin.jpg"
																style="height: 400px; width: 250px; border-width: 0px;">
														</td>
													</tr>
													<tr>
														<td>
															<input type="radio" name="skinId" id="skinid_newskin"
																value="newskin">
															<label for="skinid_newskin">
																默认皮肤0
															</label>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
										<td>
											<table style="margin-right: 10px;">
												<tbody>
													<tr>
														<td>
															<img
																id="ctl00_ContentPlaceHolder1_wxSkins_ctl02_wxImgUrl"
																src="<%=request.getContextPath()%>/public/images/moban/skin1.jpg"
																style="height: 400px; width: 250px; border-width: 0px;">
														</td>
													</tr>
													<tr>
														<td>
															<input type="radio" name="skinId" id="skinid_skin1"
																value="skin1">
															<label for="skinid_skin1">
																默认皮肤1
															</label>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
									<tr>
										<td>
											<table style="margin-right: 10px;">
												<tbody>
													<tr>
														<td>
															<img
																id="ctl00_ContentPlaceHolder1_wxSkins_ctl03_wxImgUrl"
																src="<%=request.getContextPath()%>/public/images/moban/skin2.jpg"
																style="height: 400px; width: 250px; border-width: 0px;">
														</td>
													</tr>
													<tr>
														<td>
															<input type="radio" name="skinId" id="skinid_skin2"
																value="skin2">
															<label for="skinid_skin2">
																默认皮肤2
															</label>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
										<td>
											<table style="margin-right: 10px;">
												<tbody>
													<tr>
														<td>
															<img
																id="ctl00_ContentPlaceHolder1_wxSkins_ctl04_wxImgUrl"
																src="<%=request.getContextPath()%>/public/images/moban/skin3.jpg"
																style="height: 400px; width: 250px; border-width: 0px;">
														</td>
													</tr>
													<tr>
														<td>
															<input type="radio" name="skinId" id="skinid_skin3"
																value="skin3">
															<label for="skinid_skin3">
																默认皮肤3
															</label>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
										<td>
											<table style="margin-right: 10px;">
												<tbody>
													<tr>
														<td>
															<img
																id="ctl00_ContentPlaceHolder1_wxSkins_ctl05_wxImgUrl"
																src="<%=request.getContextPath()%>/public/images/moban/skin4.jpg"
																style="height: 400px; width: 250px; border-width: 0px;">
														</td>
													</tr>
													<tr>
														<td>
															<input type="radio" name="skinId" id="skinid_skin4"
																value="skin4">
															<label for="skinid_skin4">
																默认皮肤4
															</label>
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
