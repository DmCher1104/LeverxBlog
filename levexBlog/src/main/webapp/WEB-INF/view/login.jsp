<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
    ${message}
    <form:form action="login" method="post">
    <h2>Вход в систему</h2>
    <div>

        <input name="username" type="text" placeholder="Username"
               autofocus="true"/>
        <input name="password" type="password" placeholder="Password"/>
        <button type="submit">Log In</button>
        </form:form>
        <h4><a href="registration">Зарегистрироваться</a></h4>
    </div>

</div>

</body>
</html>
