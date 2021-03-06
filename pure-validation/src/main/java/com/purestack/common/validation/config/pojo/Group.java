package com.purestack.common.validation.config.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证组
 * @author Benson
 *
 */
public class Group {
	/**
	 * 验证组名称
	 */
	private String name;
	/**
	 * 验证字段
	 */
	private List<Field> fields;
	
	/**
	 * 添加字段，暂时不处理去重
	 * @param field 字段对象
	 */
	public void addField(Field field){
		this.addField(field);
	}
	
	public Group() {

		this.fields = new ArrayList<Field>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	@Override
	public String toString() {

		return "[ name="+name+",fields="+fields+"]";
	}
}
