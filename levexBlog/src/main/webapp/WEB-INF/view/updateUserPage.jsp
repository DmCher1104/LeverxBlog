<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<body>
<h2>Registration Form</h2>
<br>
<form:form action="updateUserByAdmin" modelAttribute="user" method="post">

    <form:hidden path="id"/>

    FirstName<form:input path="username" />
    <br>
    LastName<form:input path="lastName"/>
    <br>
    Password<form:input path="password"/>
    <br><br>
    <form:hidden path="email"/>
    <form:hidden path="createdAt"/>

    <input type="submit" value="update">

</form:form>
<br>

</body>
</html>