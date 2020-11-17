package com.malf.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author malf
 * @description TODO
 * @project springboot
 * @since 2020/11/17
 */
@Controller
public class ExcelController {

	/**
	 * Web 端下载 Excel 示例
	 * TODO GetMapping 部分需要指定 xlsx 格式，代码中文件名未生效
	 */
	@GetMapping("/downloadExcel.xlsx")
	public void download(HttpServletRequest request,
						 HttpServletResponse response) throws Exception {
		ServletOutputStream outputStream = response.getOutputStream();
		ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, true);
		String fileName = new String(("UserInfo" +
				new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date())).getBytes(), "UTF-8");
		Sheet sheet = new Sheet(1, 0);
		sheet.setSheetName("Sheet1");
		writer.write0(createModelList(), sheet);
		writer.finish();
		response.setContentType("multipart/form-data");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		outputStream.flush();
	}

	private List<List<String>> createModelList() {
		// 所有数据行
		List<List<String>> rows = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			// 一行数据
			List<String> row = new ArrayList<>();
			row.add("巅峰小词典" + i);
			row.add("凌峰" + i);
			row.add("测试");
			rows.add(row);
		}
		return rows;
	}

}
