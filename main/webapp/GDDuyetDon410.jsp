<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Approve Invoices</title>
</head>
<body>
    <h2>Approve Invoices</h2>
    <form action="InvoiceServlet?action=approveSelected" method="post">
        <table border="1">
            <thead>
                <tr>
                    <th>Chọn</th>
                    <th>Mã hóa đơn</th>
                    <th>Tên khách hàng</th>
                    <th>Tổng tiền</th>
                </tr>
            </thead>
            <tbody id="invoiceTableBody">
                <c:forEach var="invoice" items="${unapprovedInvoices}">
                    <tr>
                        <td><input type="checkbox" name="invoiceId" value="<c:out value='${invoice.id}'/>" /></td>
                        <td><c:out value="${invoice.id}"/></td>
                        <td><c:out value="${invoice.client}"/></td>
                        <td><c:out value="${invoice.tongTien}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <label for="shipperSelect">Select Shipper:</label>
        <select id="shipperSelect" name="shipperId">
            <c:forEach var="shipper" items="${shippers}">
                <option value="<c:out value='${shipper.id}'/>"><c:out value="${shipper.name}"/></option>
            </c:forEach>
        </select>
        <button type="submit">Approve Selected</button>
    </form>

    <c:if test="${not empty message}">
        <p>${message}</p>
        <form action="InvoiceServlet?action=print" method="get">
            <c:forEach var="invoice" items="${approvedInvoices}">
                <input type="hidden" name="invoiceId" value="${invoice.id}">
            </c:forEach>
            <button type="submit">Print Invoices</button>
        </form>
    </c:if>
</body>
</html>
