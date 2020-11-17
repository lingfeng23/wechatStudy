package com.malf;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.metadata.TableStyle;
import com.malf.entity.WriteModel;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author malf
 * @description TODO
 * @project springboot
 * @since 2020/11/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ExcelTest {

	/**
	 * 将数据模型写入 Excel
	 *
	 * @throws Exception
	 */
	@Test
	public void writeExcel() throws Exception {
		// 文件输出位置
		OutputStream outputStream = new FileOutputStream("E:/malf/wechatStudy/springboot/src/main/resources/files/test.xlsx");
		ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
		// 写仅有一个 Sheet 的 Excel 文件
		Sheet sheet = new Sheet(1, 0, WriteModel.class);
		// 第一个 Sheet 的名称
		sheet.setSheetName("Sheet1");
		// 写数据到 Writer 上下文中
		writer.write(createModelList(), sheet);
		// 将上下文的最终 outputStream 写入到指定文件中
		writer.finish();
		// 关闭流
		outputStream.close();
	}

	/**
	 * 动态生成 Excel 内容
	 *
	 * @throws Exception
	 */
	@Test
	public void writeDynamicExcel() throws Exception {
		// 文件输出位置
		OutputStream outputStream = new FileOutputStream("E:/malf/wechatStudy/springboot/src/main/resources/files/test.xlsx");
		ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);

		// 动态添加表头，适用于表头动态变化的场景
		Sheet sheet = new Sheet(1, 0);
		sheet.setSheetName("Sheet1");
		// 创建一个表格，用于 Sheet 中使用
		Table table = new Table(1);
		// 自定义表格样式
		table.setTableStyle(createTableStyle());
		// 无注解的模式，动态添加表头
		table.setHead(createStringHead());
		// 写数据
		writer.write1(createDynamicModelList(), sheet, table);
		// 使用 merge 合并单元格
		// 下标是从 0 开始的，合并了第六行到第七行，第一列到第五列
		writer.merge(5, 6, 0, 4);
		// 将上下文的最终 outputStream 写入到指定文件中
		writer.finish();
		// 关闭流
		outputStream.close();
	}

	/**
	 * 创建自定义表头
	 */
	private List<List<String>> createStringHead() {
		// 模型上没有注解，表头数据动态传入
		List<List<String>> heads = new ArrayList<>();
		List<String> headColumn1 = new ArrayList<>();
		List<String> headColumn2 = new ArrayList<>();
		List<String> headColumn3 = new ArrayList<>();
		List<String> headColumn4 = new ArrayList<>();
		List<String> headColumn5 = new ArrayList<>();
		headColumn1.add("第一列");
		headColumn1.add("第一列");
		headColumn1.add("第一列");
		headColumn2.add("第一列");
		headColumn2.add("第一列");
		headColumn2.add("第一列");
		headColumn3.add("第二列");
		headColumn3.add("第二列");
		headColumn3.add("第二列");
		headColumn4.add("第三列1");
		headColumn4.add("第三列2");
		headColumn4.add("第三列2");
		headColumn5.add("第四列");
		headColumn5.add("第四列");
		headColumn5.add("第四列");
		heads.add(headColumn1);
		heads.add(headColumn2);
		heads.add(headColumn3);
		heads.add(headColumn4);
		heads.add(headColumn5);
		return heads;
	}

	/**
	 * 创建表格样式
	 */
	private TableStyle createTableStyle() {
		TableStyle tableStyle = new TableStyle();
		// 设置表头样式
		Font headFont = new Font();
		// 字体是否加粗
		headFont.setBold(true);
		// 字体大小
		headFont.setFontHeightInPoints((short) 12);
		// 字体
		headFont.setFontName("楷体");
		tableStyle.setTableHeadFont(headFont);
		// 背景颜色
		tableStyle.setTableHeadBackGroundColor(IndexedColors.BLUE);

		// 设置表格主体样式
		Font contentFont = new Font();
		contentFont.setBold(true);
		contentFont.setFontHeightInPoints((short) 12);
		contentFont.setFontName("黑体");
		tableStyle.setTableContentFont(contentFont);
		tableStyle.setTableContentBackGroundColor(IndexedColors.GREEN);
		return tableStyle;
	}

	/**
	 * Object 代表无注解的实体类
	 */
	private List<List<Object>> createDynamicModelList() {
		// 所有数据行
		List<List<Object>> rows = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			// 一行数据
			List<Object> row = new ArrayList<>();
			row.add("巅峰小词典" + i);
			row.add(Long.valueOf(123456L + i));
			row.add(Integer.valueOf(12345 + i));
			row.add("凌峰" + i);
			row.add("测试");
			rows.add(row);
		}
		return rows;
	}

	/**
	 * 带注解的实体模型列表
	 */
	private List<WriteModel> createModelList() {
		List<WriteModel> models = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			WriteModel writeModel = WriteModel.builder()
					.name("Hello" + i).password("password").age(i + 1)
					.build();
			models.add(writeModel);
		}
		return models;
	}

}
