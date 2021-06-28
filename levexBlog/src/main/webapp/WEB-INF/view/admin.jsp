<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<body>
<h2>All users in you BLOG (only for ADMINS!!!)</h2>
<table>
    <tr>

        <th>First_name</th>
        <th>Last_name</th>
        <th>Email</th>
        <th>Created_at</th>
    </tr>
    <c:forEach var="user" items="${userList}">

        <c:url var="updateButton" value="/updateUserByAdmin">
            <c:param name="userId" value="${user.id}"/>

        </c:url>
        <c:url var="deleteButton" value="/deleteUser">
            <c:param name="userId" value="${user.id}"/>
        </c:url>
        <tr>
            <div>
                <td>${user.username} </td>
            </div>
            <div>
                <td>${user.lastName}</td>
            </div>
            <div>
                <td>${user.email}</td>
            </div>
            <div>
                <td>${user.createdAt}</td>
            </div>
            <td><input type="button" value="update"
                       onclick="window.location.href='${updateButton}'"/></td>
            <td><input type="button" value="delete"
                       onclick="window.location.href='${deleteButton}'"/></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>