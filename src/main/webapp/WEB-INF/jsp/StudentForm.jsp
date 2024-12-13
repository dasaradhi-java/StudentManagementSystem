<%@ page import="com.ManagementSystem.Entity.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
        }
        form {
            width: 50%;
            margin: auto;
        }
        label {
            display: inline-block;
            width: 120px;
            margin-bottom: 10px;
        }
        input[type="text"], button {
            padding: 8px;
            width: calc(100% - 130px);
            margin-bottom: 10px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>Student Form</h2>
    <form action="/saveStudent" method="post">
        <% 
            Student student = (Student) request.getAttribute("student"); 
            if (student == null) {
                student = new Student(); // Default empty student object for a fresh form
            }
        %>
        <% if (student.getRollNo() != null && !student.getRollNo().isEmpty()) { %>
            <label for="rollNo">Roll Number:</label>
            <input type="text" id="rollNo" name="rollNo" value="<%= student.getRollNo() %>" readonly /><br>
        <% } %>

        <label for="studentName">Student Name:</label>
        <input type="text" id="studentName" name="studentName" value="<%= student.getStudentName() != null ? student.getStudentName() : "" %>" required /><br>

        <label for="mobileNumber">Mobile Number:</label>
        <input type="text" id="mobileNumber" name="mobileNumber" value="<%= student.getMobileNumber() != null ? student.getMobileNumber() : "" %>" required /><br>

        <button type="submit">Save</button>
    </form>
</body>
</html>
