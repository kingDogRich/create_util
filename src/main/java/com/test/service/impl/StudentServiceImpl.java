package com.test.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.test.service.StudentService;
import com.test.db.dao.StudentMapper;
import com.test.db.model.entity.Student;

/**
 * Service
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper studentMapper;


	@Override
	public List<Student> getStudentList(Student student) {

		List<Student> studentList = studentMapper.getStudentList(student);

		return studentList;
	}

	@Override
	public Student getStudent (Student student) {

		Student entity = studentMapper.getStudent(student);

		return entity;
	}

	@Override
	public void insertStudent (Student student) {
		studentMapper.insertStudent(student);
	}

	@Override
	public void updateStudent (Student student) {
		studentMapper.updateStudent(student);
	}

	@Override
	public void updateStudentByValue (Student student) {
		studentMapper.updateStudentByValue(student);
	}

	@Override
	public void deleteStudent (Student student) {
		studentMapper.deleteStudent(student);
	}

}