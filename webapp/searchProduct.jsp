<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Product</title>
</head>
<body>
	<div align="center">
    <h2>Tìm kiếm mặt hàng</h2>
    <form action="ProductServlet" method="get">
        <input type="text" name="search" placeholder="Nhập tên sản phẩm" required />
        <button type="submit">Tìm kiếm</button>
    </form>

    <!-- Display the product table -->
    ${productTable}
    </div>
</body>
</html>
