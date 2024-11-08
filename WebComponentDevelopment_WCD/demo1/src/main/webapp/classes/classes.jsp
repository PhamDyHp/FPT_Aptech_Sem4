<%@ page import="java.util.List" %>
<%@ page import="com.example.demo1.entity.Student" %>
<%@ page import="com.example.demo1.entity.Classes" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class Listing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <h1>List of Class</h1>`
    <button class="btn btn-primary my-3" data-bs-toggle="modal" data-bs-target="#addStudentModal">Add Class</button>

    <!-- Class List Table -->
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Class name</th>
        </tr>
        </thead>
        <tbody>
        <% for(Classes c : (List<Classes>)request.getAttribute("classes")){ %>
        <tr>
            <th scope="row"><%= c.getId() %></th>
            <td><%= c.getName() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <!-- Add Student Modal -->
    <div class="modal fade" id="addStudentModal" tabindex="-1" aria-labelledby="addStudentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="<%= request.getContextPath() %>/classes" method="post">
                <div class="modal-header">
                        <h5 class="modal-title" id="addStudentModalLabel">Add New Class</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
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
