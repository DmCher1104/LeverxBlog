<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Index Page</title>
</head>
<body>
<div>

    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <h4><a href="login">login</a></h4>
        <h4><a href="registration">registration</a></h4>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <h4><a href="logout">logout</a></h4>
        <h4><a href="createNewPost" methods="get">createNewPost</a></h4>
    </sec:authorize>

    <security:authorize access="hasRole('USER')">
    <h4><a href="news">News (only users)</a></h4>
    </security:authorize>
    <security:authorize access="hasRole('ADMIN')">
    <h4><a href="admin/getUsers">Info about users (only admins)</a></h4>
    </security:authorize>

    <br><br>
    <h3>All posts you can see below (just public) </h3>
    <table>
    <tr>
        <th>Title</th>
        <th>Text</th>
        <th>Status</th>
        <th>Created_at</th>
        <th>Updated_at</th>
        <th>tag(s)</th>
    </tr>

    <c:forEach var="post" items="${postList}">

        <tr>
            <td>${post.title} </td>
            <td>${post.text}</td>
            <td>${post.status}</td>
            <td>${post.createdAt}</td>
            <td>${post.updatedAt}</td>
<%--            <ul>--%>
<%--                <c:forEach var="tag" items="${post.tags}">--%>
<%--                <li>${tag}</li>--%>

<%--                </c:forEach>--%>

<%--            </ul>--%>
<%--            <td>${post.tags}</td>--%>
        </tr>
    </c:forEach>
    </table>
</div>
</body>
</html>