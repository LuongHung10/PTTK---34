<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Approve Invoices</title>
</head>
<body>
    <h2>Approve Invoices</h2>

    <!-- Bảng hóa đơn chưa được duyệt -->
    <h3>Pending Invoices</h3>
    <form action="ProductServlet" method="post">
        <table border="1">
            <tr>
                <th>Select</th>
                <th>Invoice ID</th>
                <th>Client</th>
                <th>Total Amount</th>
            </tr>
            <c:forEach var="invoice" items="${pendingInvoices}">
                <tr>
                    <td><input type="checkbox" name="selectedInvoices" value="${invoice.id}"/></td>
                    <td>${invoice.id}</td>
                    <td>${invoice.client.name}</td>
                    <td>${invoice.tongTien}</td>
                </tr>
            </c:forEach>
        </table>

        <!-- Bảng danh sách nhân viên giao hàng -->
        <h3>Select Delivery Staff</h3>
        <table border="1">
            <tr>
                <th>Select</th>
                <th>Staff ID</th>
                <th>Name</th>
                <th>Position</th>
            </tr>
            <c:forEach var="shipper" items="${deliveryStaff}">
                <tr>
                    <td><input type="radio" name="selectedShipper" value="${shipper.id}"/></td>
                    <td>${shipper.id}</td>
                    <td>${shipper.name}</td>
                    <td>${shipper.vitri}</td>
                </tr>
            </c:forEach>
        </table>

        <!-- Nút duyệt đơn và in hóa đơn -->
        <button type="submit" name="action" value="approve">Approve Selected Invoices</button>
        <button type="submit" name="action" value="print">Print Selected Invoices</button>
    </form>
</body>
</html>
