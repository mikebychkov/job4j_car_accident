<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Accidents list</h2>
    <a href="<c:url value='/create'/>">Add accident</a>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Edit</th>
                <th>ID</th>
                <th>Name</th>
                <th>Text</th>
                <th>Address</th>
                <th>Type</th>
                <th>Rules</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="it" items="${list}" >
                <tr>
                    <td><a href="<c:url value='/update?id=${it.id}'/>">Edit</a></td>
                    <td><c:out value="${it.id}"/></td>
                    <td><c:out value="${it.name}"/></td>
                    <td><c:out value="${it.text}"/></td>
                    <td><c:out value="${it.address}"/></td>
                    <td><c:out value="${it.type.name}"/></td>
                    <td><c:out value="${it.rules}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>