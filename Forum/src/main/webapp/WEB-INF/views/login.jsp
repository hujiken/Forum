<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login</title>
 
<style>
 
    .error {
        color: #ff0000;
    }
</style>
 
</head>
<body>
	<form:form method="POST" modelAttribute="login">
		<table>
            <tr>
                <td><label for="username">User name: </label> </td>
                <td><form:input path="username" id="username"/></td>
                <td><form:errors path="username" cssClass="error"/></td>
            </tr>
         
            <tr>
                <td><label for="password">Password: </label> </td>
                <td><form:password path="password" id="password"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
            <tr>
            	<td><input type="submit" value="Login"/></td>
            </tr>
            <tr>
            	<td></td>
            	<td><a>${invalid }</a></td>
            </tr>
            <tr>
                <td colspan="2">Yet Not Registered!! <a href="/Forum/reg">Register Here</a></td>
            </tr>
        </table>
	</form:form>
</body>
</html>