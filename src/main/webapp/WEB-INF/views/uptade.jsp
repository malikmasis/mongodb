<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Uptade User</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="https://cloud.github.com/downloads/digitalBush/jquery.maskedinput/jquery.maskedinput-1.3.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cloud.github.com/downloads/digitalBush/jquery.maskedinput/jquery.maskedinput-1.3.min.js"></script>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
<script type="text/javascript"
	src="https://cloud.github.com/downloads/digitalBush/jquery.maskedinput/jquery.maskedinput-1.3.min.js"></script>

<script type="text/javascript">
	function submitForm() {
		var validator = $("#myForm").validate({
			rules : {
				name : "required",
				surname : "required",
				phone : "required"
			},
			errorElement : "span",
			messages : {
				name : " Enter Name",
				surname : " Enter Surname",
				phone : "Enter Contact No"
			}
		});
		if (validator.form()) { // validation perform
			$('form#myForm').attr({

				action : '#'

			});
			alert("Success");
			$('form#myForm').submit();
			self.location = "/HelloWorld/list"
		}
	}
</script>

<script type="text/javascript">
	$(document).ready(
			function() {

				$("#phone").mask("(999) 999-9999");

				$("#phone").on(
						"blur",
						function() {
							var last = $(this).val().substr(
									$(this).val().indexOf("-") + 1);

							if (last.length == 3) {
								var move = $(this).val().substr(
										$(this).val().indexOf("-") - 1, 1);
								var lastfour = move + last;

								var first = $(this).val().substr(0, 9);

								$(this).val(first + '-' + lastfour);
							}
						});

			});
</script>
</head>
<body>
   <!-- daha once bunlarin cogunu index sayfamizda aciklamistik -->
	<form method="post" id="myForm" action="update">
		<table>

			<tr>
				<td><label for="name">Name</label></td>
				<td><input tabindex="1" type="text" name="name" id="name"
					value="${name}" />${user.name }</td>
			</tr>

			<tr>
				<td><label for="surname">Surname</label></td>
				<td><input tabindex="2" type="text" name="surname" id="surname"
					value="${surname}" />
				<td>
			</tr>

			<tr>
				<td><label for="phone">Contact Number</label></td>
				<td><input tabindex="3" type="text" name="phone" id="phone"
					value="${phone}" />${nameNew }</td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit"
					onclick="submitForm(); " /></td>
				<td><input type="button" value="Cancel"
					onclick="window.close();" /></td>
			</tr>
		</table>
	</form></body>
</html>