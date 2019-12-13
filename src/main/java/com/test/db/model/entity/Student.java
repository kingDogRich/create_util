package com.test.db.model.entity;


/**
 * student	DBModel
 */
public class Student{

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 名字
	 */
	private String name;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 取得主键
	 * 
	 * @return 主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设定主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 取得名字
	 * 
	 * @return 名字
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设定名字
	 * 
	 * @param name
	 *            名字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 取得性别
	 * 
	 * @return 性别
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * 设定性别
	 * 
	 * @param sex
	 *            性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

}