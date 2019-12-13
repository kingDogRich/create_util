package com.test.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.service.StudentService;
import com.test.db.model.entity.Student;

/**
 * Controller
 */
@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	/**
	 * 取得List
	 * 
	 * @return List
	 */
	@RequestMapping(value = "/getStudentList")
	public List<Student> getStudentList(Student student) {
		return studentService.getStudentList(student);
	}

	/**
	 * 取得
	 * 
	 * @return 
	 */
	@RequestMapping(value = "/getStudent")
	public Student getStudent(Student student) {
		return studentService.getStudent(student);
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/saveStudent")
	public void saveStudent(@RequestBody Student student) {
		studentService.insertStudent(student);
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/deleteStudent")
	public void deleteStudent(@RequestBody Student student) {
		studentService.deleteStudent(student);
	}

}