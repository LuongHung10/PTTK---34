<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Print Invoices</title>
    <style>
        @media print {
            button { display: none; }
        }
    </style>
</head>
<body>
    <h2>Invoices to Print</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Invoice ID</th>
                <th>Client Name</th>
                <th>Date</th>
                <th>Total Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="invoice" items="${invoicesToPrint}">
                <tr>
                    <td>${invoice.id}</td>
                    <td>${invoice.client.name}</td>
                    <td>${invoice.ngayxuat}</td>
                    <td>${invoice.tongTien}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <button onclick="window.print()">Print</button>
</body>
</html>
