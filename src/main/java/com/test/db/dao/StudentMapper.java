package com.test.db.dao;

import java.util.List;

import com.test.db.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * student	Mapper
 */
@Mapper
public interface StudentMapper {

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
	public int insertStudent(Student student);

	/**
	 * 更新
	 */
	public int updateStudent(Student student);

	/**
	 * 更新ByValue
	 */
	public int updateStudentByValue(Student student);

	/**
	 * 删除
	 */
	public int deleteStudent(Student student);

}