package com.malf.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author malf
 * @description 写入 Excel 的模型对象
 * @project springboot
 * @since 2020/11/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WriteModel extends BaseRowModel {
	@ExcelProperty(value = "姓名", index = 0)
	private String name;

	@ExcelProperty(value = "密码", index = 1)
	private String password;

	@ExcelProperty(value = "年龄", index = 2)
	private Integer age;

}
