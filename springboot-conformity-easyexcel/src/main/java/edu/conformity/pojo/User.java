package edu.conformity.pojo;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import lombok.Data;

@Data
public class User extends BaseRowModel implements Serializable {

	// 通过@ExcelProperty注解与index属性可以标注成员所映射的列
	@ExcelProperty(value = "id", index = 0)
	private int id;
	
	@ExcelProperty(value = "姓名", index = 1)
	private String name;
	
	@ExcelProperty(value = "性别", index = 2)
	private String sex;
	
	@ExcelProperty(value = "地址", index = 3)
	private String address;
	
	@ExcelProperty(value = "手机号码", index = 4)
	private String phone;
	
	@ExcelProperty(value = "身份证", index = 5)
	private String idcard;
	
}
