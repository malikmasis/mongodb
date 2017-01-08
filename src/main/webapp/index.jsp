<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add User</title>
</head>
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
				//Kurallari burada tanimliyoruz
				//requried giris zorunludur demektir
				name : "required",
				surname : "required",
				phone : "required"
			},
			errorElement : "span",
			messages : {
				//burada eger hata ile karsilasirsak yazacagimiz mesaj
				name : " Enter Name",
				surname : " Enter Surname",
				phone : "Enter Contact No"
			//fakat telefon numarasini biraz daha gelistirecegiz
			}
		});
		if (validator.form()) { // validation perform
			$('form#myForm').attr({
				action : 'save'
			//gidecegimiz sayfayi burada tanimliyoruz
			//eger ayni yerde kalmak istersek '#' koyabiliriz
			});
			$('form#myForm').submit();
		}
	}
</script>

<script type="text/javascript">
	$(document).ready(function() {

		$("#phone").mask("(999) 999-9999");
		//telefon maskemizi burada tanmladk

	});
</script>

<body>

	<form method="post" id="myForm" action="save">
		<!-- action ile save sayfasini calistiracagimizi soyluyoruz -->
		<table>
			<tr>
				<td><label for="name">Name</label></td>
				<td><input tabindex="1" type="text" name="name" id="name" /></td>
			</tr>

			<tr>
				<td><label for="surname">Surname</label></td>
				<td><input tabindex="2" type="text" name="surname" id="surname" />
				<td>
			</tr>

			<tr>
				<td><label for="phone">Phone</label></td>
				<td><input tabindex="3" type="text" name="phone" id="phone" />
				<td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit" onclick="submitForm();" /></td>
				<!-- farkli olarak onclick submitForm() komutunu unutmayin -->
				<td><input type="button" value="List Records"
					onclick="javascript: window.location('list'); return true" /></td>
				<!-- farkli pencerede açmak isterseniz location yerine open yazmaniz yeterli olacaktir -->
			</tr>
		</table>
	</form>
</body>
</html>