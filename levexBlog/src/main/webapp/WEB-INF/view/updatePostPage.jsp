<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<body>
<h2>Registration Form</h2>
<br>
<form:form action="updateUserByAdmin" modelAttribute="user" method="post">

    <form:hidden path="id"/>

    Title<form:input path="title" />
    <br>
    Text<form:input path="text"/>
    <br>
    Status of yor post<form:select path="status">
    <form:option value="PUBLIC" label="public"/>
    <form:option value="DRAFT" label="draft"/>
</form:select>

    <input type="submit" value="update">

</form:form>
<br>
</body>
</html>