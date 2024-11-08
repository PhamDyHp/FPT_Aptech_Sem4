<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.entity.Student" %>
<%@ page import="com.example.demo1.entity.Classes" %>
<%@ page import="com.example.demo1.dto.StudentDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Listing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>List of Students</h1>
    <button class="btn btn-primary my-3" data-bs-toggle="modal" data-bs-target="#addStudentModal">Add Student</button>

    <!-- Student List Table -->
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Address</th>
            <th scope="col">Telephone</th>
            <th scope="col">Class name</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for(StudentDTO s : (List<StudentDTO>)request.getAttribute("students")){ %>
        <tr>
            <th scope="row"><%= s.getId() %></th>
            <td><%= s.getName() %></td>
            <td><%= s.getEmail() %></td>
            <td><%= s.getAddress() %></td>
            <td><%= s.getTelephone() %></td>
            <td><%= (s.getClass_name() != null) ? s.getClass_name() : "-/-" %></td>
            <td>
                <!-- Nút Edit mở modal -->
                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editStudentModal<%= s.getId() %>">Edit</button>

                <!-- Nút Delete -->
                <form action="${pageContext.request.contextPath}/student" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="studentId" value="<%= s.getId() %>">
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this student?');">Delete</button>
                </form>
            </td>
        </tr>

        <% } %>
        </tbody>
    </table>

    <!-- Add Student Modal -->
    <div class="modal fade" id="addStudentModal" tabindex="-1" aria-labelledby="addStudentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/student" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addStudentModalLabel">Add New Student</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" id="address" name="address" required>
                        </div>
                        <div class="mb-3">
                            <label for="telephone" class="form-label">Telephone</label>
                            <input type="text" class="form-control" id="telephone" name="telephone" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Class</label>
                            <select name="class_id" class="form-control">
                                <%
                                    List<Classes> classes = (List<Classes>) request.getAttribute("classes");
                                    for (Classes c : classes) {
                                %>
                                <option value="<%= c.getId() %>"><%= c.getName() %></option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
