package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.MatHangDAO410;
import model.MatHang410;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatHangDAO410 matHangDAO;

    public void init() {
        try {
			matHangDAO = new MatHangDAO410();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("details".equals(action)) {
            showProductDetails(request, response);
        } else {
            searchProducts(request, response);
        }
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<MatHang410> productList = matHangDAO.searchProductsByName(search);

        // Generate HTML directly in the servlet
        StringBuilder htmlResponse = new StringBuilder();
        if (productList != null && !productList.isEmpty()) {
            htmlResponse.append("<table border='1'><tr><th>Tên sản phẩm</th><th>Đơn giá</th></tr>");
            for (MatHang410 product : productList) {
                htmlResponse.append("<tr>")
                            .append("<td><a href='ProductServlet?action=details&id=")
                            .append(product.getId())
                            .append("'>")
                            .append(product.getName())
                            .append("</a></td>")
                            .append("<td>")
                            .append(product.getPrice())
                            .append("</td>")
                            .append("</tr>");
            }
            htmlResponse.append("</table>")
            			.append("<a href='client.jsp'>Quay lại trang chủ</a>");
        } else {
            htmlResponse.append("<p>No products found.</p>")
            			.append("<a href='client.jsp'>Quay lại trang chủ</a>");
        }

        request.setAttribute("productTable", htmlResponse.toString());
        request.getRequestDispatcher("searchProduct.jsp").forward(request, response);
    }


    private void showProductDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        MatHang410 product = matHangDAO.getProductById(id);

        // Generate HTML for product details
        StringBuilder htmlResponse = new StringBuilder();
        if (product != null) {
            htmlResponse.append("<h2>Chi tiết sản phẩm</h2>")
                        .append("<p><strong>Tên sản phẩm:</strong> ").append(product.getName()).append("</p>")
                        .append("<p><strong>Đơn giá:</strong> ").append(product.getPrice()).append("</p>")
                        .append("<p><strong>Số lượng:</strong> ").append(product.getSoluong()).append("</p>")
                        .append("<a href='searchProduct.jsp'>Quay lại trang tìm kiếm</a>");
        } else {
            htmlResponse.append("<p>Product not found.</p>")
                        .append("<a href='searchProduct.jsp'>Quay lại trang tìm kiếm</a>");
        }

        request.setAttribute("productDetails", htmlResponse.toString());
        request.getRequestDispatcher("productDetails.jsp").forward(request, response);
    }

}
