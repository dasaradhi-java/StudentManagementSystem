package com.ManagementSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ManagementSystem.Entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	Student save(Student student);
	List<Student> findAll();
	

}
