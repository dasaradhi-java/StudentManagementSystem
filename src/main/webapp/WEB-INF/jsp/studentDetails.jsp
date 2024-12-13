<%@ page import="java.util.List" %>
<%@ page import="com.ManagementSystem.Entity.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Students Details</title>
    <style>
        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>Students Details</h2>
    
    <div class=error-message></div>
    <table>
        <thead>
            <tr>
                <th>Roll Number</th>
                <th>Student Name</th>
                <th>Mobile Number</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Student> students = (List<Student>) request.getAttribute("students"); 
                if (students != null) {
                    for (Student student : students) {
            %>
                <tr>
                    <td><%= student.getRollNo() %></td>
                    <td><%= student.getStudentName() %></td>
                    <td><%= student.getMobileNumber() %></td>
                      <td>
                        <!-- Edit Button, pass roll number to open the student form with details -->
                        <a href="<%= request.getContextPath() + "/studentForm?rollNo=" + student.getRollNo() %>" class="action-btn">Edit</a>
                    </td>
                </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
</body>
</html>
