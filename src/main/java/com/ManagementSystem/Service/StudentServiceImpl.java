package com.ManagementSystem.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ManagementSystem.Entity.ConvertToBean;
import com.ManagementSystem.Entity.Student;
import com.ManagementSystem.Repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	private final StudentRepository studentRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public Student saveStudent(Student student) {

		Student save = studentRepository.save(student);
		return save;

	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> allStudent = studentRepository.findAll();
		return allStudent;
	}

	public Student findStudenyByRollNo(String rollNo) {
		String sql = "SELECT * FROM student_details_learning WHERE roll_no = ? ";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql, new Object[] { rollNo });
		if (queryForList == null || queryForList.isEmpty()) {
			return null;
		}
		Student data = ConvertToBean.getData(Student.class, queryForList.get(0));
		return data;

	}

	public Student updateStudent(String rollNo, Student student) {
		Student findStudenyByRollNo = findStudenyByRollNo(rollNo);
		if (findStudenyByRollNo == null) {
			return null;
		}
		findStudenyByRollNo.setStudentName(student.getStudentName());
		findStudenyByRollNo.setMobileNumber(student.getMobileNumber());
		return studentRepository.save(findStudenyByRollNo);

	}

	public String findLatestRollNo() {
		String sql = "SELECT roll_no FROM student_details_learning ORDER BY id desc";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		Map<String, Object> row = queryForList.get(0);
		String rollNo = (String) row.get("roll_no");
		return rollNo;

	}


}
