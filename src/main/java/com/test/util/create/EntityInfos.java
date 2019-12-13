/**
 * 
 */
package com.test.util.create;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Administrator
 *
 */
public class EntityInfos {
	@XmlElement(name = "TABLE_NAME")
	private String table_name;
	@XmlElement(name = "TABLE_COMMENT")
	private String table_comment;
	
	List<FieldInfos> fieldInfosList;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getTable_comment() {
		return table_comment;
	}

	public void setTable_comment(String table_comment) {
		this.table_comment = table_comment;
	}

	public List<FieldInfos> getFieldInfosList() {
		return fieldInfosList;
	}

	public void setFieldInfosList(List<FieldInfos> fieldInfosList) {
		this.fieldInfosList = fieldInfosList;
	}

	@Override
	public String toString() {
		String s = "";
		s += "\r\ntable_name:" + this.table_name;
		s += "\r\ntable_comment:" + this.table_comment;
		s = s.substring(2);
		return s;
	}
}
