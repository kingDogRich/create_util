package com.test.service;

import java.util.List;

import com.test.db.model.entity.Student;

/**
 * Service
 */
public interface StudentService {

	/**
	 * 取得List
	 * 
	 * @return List
	 */
	public List<Student> getStudentList(Student student);

	/**
	 * 取得
	 * 
	 * @return 
	 */
	public Student getStudent(Student student);

	/**
	 * 添加
	 */
	public void insertStudent(Student student);

	/**
	 * 更新
	 */
	public void updateStudent(Student student);

	/**
	 * 更新ByValue
	 */
	public void updateStudentByValue(Student student);

	/**
	 * 删除
	 */
	public void deleteStudent(Student student);

}