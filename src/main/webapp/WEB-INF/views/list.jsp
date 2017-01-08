<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring 4 MVC -HelloWorld</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<center>
		<h2>List</h2>
		<table id="example">
			<c:forEach items="${orderList}" var="order">
				<td><a href="/HelloWorld/list/order/${order}">${order}${orderShape}</a></td>
			</c:forEach>

			<c:forEach items="${userList}" var="user">
				<!-- forEach döngüsü javadaki yeni for döngüaü mantıgıyla calisiyor -->
				<tr>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.surname}" /></td>
					<td><c:out value="${user.phone}" /></td>
					<td><a href="/HelloWorld/list/${user.id}"
						onclick="return confirm('silmek istediginize emin misin ? ');">Delete
					</a></td>
					<!-- ${user.id} tkladgmz satiri silebilmemiz için gereklidir. -->
					<!-- return confirm ise karsmza ckan sileyim mi onay kutucugudur. -->
					<td><a
						href="/HelloWorld/uptade/${user.name}/${user.surname}/${user.phone}/${user.id}">Edit</a>
					</td>
			</c:forEach>
		</table>
	</center>
</body>
</html>