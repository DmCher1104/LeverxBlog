<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<body>
<h2>Registration Form</h2>
<br>

<form:form action="registration" modelAttribute="user" method="post">

    <form:hidden path="id"/>

    FirstName<form:input path="username"/>
    <br>
    LastName<form:input path="lastName"/>
    <br>
    Password<form:input path="password"/>
    <br>
    Email<form:input path="email"/>
    <br><br>
    <input type="submit" value="registrate">

</form:form>

</body>
</html>