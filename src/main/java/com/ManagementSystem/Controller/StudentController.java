package com.ManagementSystem.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ManagementSystem.Entity.Student;
import com.ManagementSystem.Service.StudentService;

@Controller
public class StudentController {
	@Autowired
	StudentService service;

	@GetMapping("/studentForm1")
	public String showForm(Model model) {
		model.addAttribute("student", new Student());
		return "StudentForm"; // studentForm.jsp will be displayed
	}

	@PostMapping("/saveStudent")
	public String saveOrUpdateStudent(@ModelAttribute("student") Student student, Model model) {
	    Student savedStudent;

	    // If the roll number is provided, it's an update
	    if (student.getRollNo() != null && !student.getRollNo().isEmpty()) {
	        // Check if the student exists
	        Student existingStudent = service.findStudenyByRollNo(student.getRollNo());
	        if (existingStudent != null) {
	            // Update the existing student
	            existingStudent.setStudentName(student.getStudentName());
	            existingStudent.setMobileNumber(student.getMobileNumber());
	            savedStudent = service.updateStudent(existingStudent.getRollNo(), existingStudent);
	        } else {
	            // Handle the case where the student does not exist
	            model.addAttribute("errorMessage", "Student with roll number " + student.getRollNo() + " does not exist.");
	            return "errorPage";  // Show an error page if student doesn't exist
	        }
	    } else {
	        // If no roll number is provided, create a new student
	        String findLatestRollNo = service.findLatestRollNo();
	        if (findLatestRollNo == null || findLatestRollNo.isEmpty()) {
	            findLatestRollNo = "ST0000";
	        }
	        String nextRollNo = incrementRollNo(findLatestRollNo);
	        student.setRollNo(nextRollNo);

	        savedStudent = service.saveStudent(student);  // Save the new student
	    }

	    // Add only the saved student to the model and return the student details page
	    model.addAttribute("students", List.of(savedStudent));  // Add only the saved student
	    return "studentDetails";  // Return the view to show the saved student details
	}



	@GetMapping("/findStudentByrollNo/{rollNo}")
	public String getStudentByrollNo(@PathVariable String rollNo, Model model) {
		Student findStudenyByRollNo = service.findStudenyByRollNo(rollNo);
		if (findStudenyByRollNo == null) {
			model.addAttribute("errorMessage", "Student with roll number " + rollNo + " not found.");
			return "errorPage"; // Show error page if student not found
		}
		model.addAttribute("students", List.of(findStudenyByRollNo));
		return "studentDetails";
	}

	@GetMapping("/studentDetails")
	public String getAllStudents(Model model) {
		List<Student> allStudents = service.getAllStudents(); // Fetch all students from the service
		if (allStudents == null) {
			model.addAttribute("errorMessage", "No Student Found.");
			return "errorPage"; // Show error page if student not found
		}
		model.addAttribute("students", allStudents); // Add the list to the model
		return "studentDetails"; // Return the name of the JSP view
	}
	 private String incrementRollNo(String latestRollNo) {
	        String numericPart = latestRollNo.substring(2);
	        int number = Integer.parseInt(numericPart); // Convert to an integer
	        number++;  // Increment the number by 1
	        return String.format("ST%04d", number);
	    }
	 @PutMapping("/updateStudent/{rollNumber}")
	    public String updateStudents(@PathVariable("rollNo") String rollNo, @RequestBody Student studentDetails,Model model) {
		 Student updateStudent = service.updateStudent(rollNo, studentDetails);
		 Student student1 = service.saveStudent(updateStudent);
		 model.addAttribute("students", List.of(student1));
		 return "studentDetails";
	    }
	 
	 @GetMapping("/studentForm")
	 public String showForm(@RequestParam(value = "rollNo", required = false) String rollNo, Model model) {
	     if (rollNo != null) {
	         // Fetch the student details by rollNo and populate the form
	         Student student = service.findStudenyByRollNo(rollNo);
	         if (student != null) {
	             model.addAttribute("student", student);
	         } else {
	             model.addAttribute("errorMessage", "Student with Roll Number " + rollNo + " not found.");
	         }
	     } else {
	         // Add a blank student object for creating new records
	         model.addAttribute("student", new Student());
	     }
	     return "StudentForm"; // Render the JSP form
	 }

}
