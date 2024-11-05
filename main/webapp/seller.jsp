<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seller Dashboard</title>
</head>
<body>
    <div align="center">
        <h1>Welcome, Seller!</h1>
        <form action="InvoiceServlet" method="get">
    		<input type="hidden" name="action" value="approve">
    		<button type="submit">Duyệt đơn</button>
		</form>
    </div>
</body>
</html>
