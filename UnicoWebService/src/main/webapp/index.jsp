<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<h2>Unico Demo</h2>
	<hr />

	<form action="pushNumber" method="post">
		<table>
			<tr>
				<td>First Number</td>
				<td><input type="text" name="firstNumber" /></td>
			</tr>
			<tr>
				<td>Second Number</td>
				<td><input type="text" name="secondNumber" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Push Numbers to Queue" /></td>
			</tr>
		</table>
	</form>
	<br />
	<a href="/list">Get All Message Form Queue</a>
	
	<br/>
	<br/>
	<br/><br/>
	<dir>$message</dir>
</body>
</body>
</html>