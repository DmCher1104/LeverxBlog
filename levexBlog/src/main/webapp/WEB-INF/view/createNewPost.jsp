<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<body>
<h2>Creation Posts Page</h2>
<br>

<form:form action="createNewPost" modelAttribute="posts" method="post">

    <form:hidden path="id"/>

    Title<form:input path="title"/>
    <br>
    Text<form:input type="text" path="text"/>
    <br>
    Status of yor post<form:select path="status">
    <form:option value="PUBLIC" label="public"/>
    <form:option value="DRAFT" label="draft"/>
    </form:select>
    <br><br>
    Choose tag(s) of post:
<%--    <c:forEach var="tag" items="${tagsList}">--%>
<%--    Horror<form:checkbox path="tags" value= "${tag1}"/>--%>
<%--    Sci-fi<form:checkbox path="tags" value="${tag2}"/>--%>
<%--    Fantasy<form:checkbox path="tags" value="${tag3}"/>--%>
<%--    </c:forEach>--%>
     <br><br>
    <input type="submit" value="createNewPost">

</form:form>
<br>
<h4><a href="index">go back to index page</a></h4>
</body>
</html>