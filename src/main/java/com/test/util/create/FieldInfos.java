/**
 * 
 */
package com.test.util.create;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Administrator
 *
 */
public class FieldInfos {
	/**
	 * 表名
	 */
	@XmlElement(name = "TABLE_NAME")
	private String table_name;
	/**
	 * 列名
	 */
	@XmlElement(name = "COLUMN_NAME")
	private String column_name;
	/**
	 * 是否允许为空
	 */
	@XmlElement(name = "IS_NULLABLE")
	private String is_nullable;
	/**
	 * 数据类型
	 */
	@XmlElement(name = "DATA_TYPE")
	private String data_type;
	/**
	 * 字段主键
	 */
	@XmlElement(name = "COLUMN_KEY")
	private String column_key;
	/**
	 * 注释
	 */
	@XmlElement(name = "COLUMN_COMMENT")
	private String column_comment;

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getIs_nullable() {
		return this.is_nullable ;
	}

	public void setIs_nullable(String is_nullable) {
		this.is_nullable = is_nullable;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getColumn_key() {
		return column_key;
	}

	public void setColumn_key(String column_key) {
		this.column_key = column_key;
	}

	public String getColumn_comment() {
		return column_comment;
	}

	public void setColumn_comment(String column_comment) {
		this.column_comment = column_comment;
	}

	@Override
	public String toString() {
		String s = "";
		s += "\r\ntable_name:" + this.table_name;
		s += "\r\ncolumn_name:" + this.column_name;
		s += "\r\nis_nullable:" + this.is_nullable;
		s += "\r\ndata_type:" + this.data_type;
		s += "\r\ncolumn_key:" + this.column_key;
		s += "\r\ncolumn_comment:" + this.column_comment;
		s = s.substring(2);
		return s;
	}
}
