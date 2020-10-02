<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Create accident</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <h2>Create accident</h2>
    <form class="form-horizontal" action="<c:url value='/save'/>" method='POST'>
        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" placeholder="Name" name="name">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="text">Text:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="text" placeholder="Text" name="text">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="address">Address:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="address" placeholder="Address" name="address">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="type.id">Type:</label>
            <div class="col-sm-10">
                <select name="type.id" id="type.id" class="form-control">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="rIds">Rules:</label>
            <div class="col-sm-10">
                <select name="rIds" id="rIds" multiple class="form-control">
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Save</button>
            </div>
        </div>

    </form>
</div>

</body>
</html>