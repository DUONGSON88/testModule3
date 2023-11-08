<%--
  Created by IntelliJ IDEA.
  User: duongson
  Date: 07/11/2023
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>StudentManager</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous"></script>
</head>
<body>

<h1>Student Manager</h1>
<a href="/student?action=create">
    <button type="button" class="btn btn-success">Add</button>
</a>
<div>
    <form class="input-group input-group-outline" action="/student" method="get" style="width: 25%;display: flex">
        <input type="text" hidden="hidden" name="action" value="home">
        <input type="text" class="form-control" name="search">
        <button class="btn btn-info" type="submit">Tìm</button>
    </form>
</div>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">Date</th>
        <th scope="col">Address</th>
        <th scope="col">Phone Number</th>
        <th scope="col">Class Name</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${listStudent}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.email}</td>
            <td>${item.dateOfBirth}</td>
            <td>${item.address}</td>
            <td>${item.phoneNumber}</td>
            <td>${item.clasez.name}</td>
            <td>
                <a href="http://localhost:8080/student?action=edit&id=${item.id}"><button type="button" class="btn btn-primary">Edit</button></a>
            </td>
            <td>
                <button type="button" class="btn btn-danger"
                        onclick="confirmFunction('/student?action=delete&id=${item.id}')">Delete
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script>
    function confirmFunction(url) {
        let a = confirm(`ok`);
        if (a) {
            window.location.href = url;
        }

    }
</script>
</html>
