package com.ManagementSystem.Service;

import java.util.List;

import com.ManagementSystem.Entity.Student;

public interface StudentService {

	public Student saveStudent(Student student) ;
	List<Student> getAllStudents();
	public Student findStudenyByRollNo(String rollNo) ;
	public String findLatestRollNo();
	public Student updateStudent(String rollNo,Student student);

}
