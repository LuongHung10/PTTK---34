package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.HoaDonKH410;
import model.NVGiaoHang410;
import dao.HoaDonDAO410;
import dao.NVGiaoHangDAO410;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HoaDonDAO410 hoaDonDAO;
    private NVGiaoHangDAO410 giaoHangDAO;

    public void init() {
        try {
            hoaDonDAO = new HoaDonDAO410();
            giaoHangDAO = new NVGiaoHangDAO410();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }    
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("approve".equals(action)) {
            showApprovalPage(request, response);
        } else if ("print".equals(action)) {
            try {
                System.out.println("Calling printInvoices..."); // Log kiểm tra
                printInvoices(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "An error occurred while trying to print invoices. Please try again later.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
            }
        } else {
//            response.sendRedirect("seller.jsp");
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("approveSelected".equals(action)) {
            approveInvoices(request, response);
        }
    }

    private void showApprovalPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HoaDonKH410> unapprovedInvoices = hoaDonDAO.getHoaDon();
        List<NVGiaoHang410> shippers = giaoHangDAO.getAllNVGiaoHang();
        request.setAttribute("unapprovedInvoices", unapprovedInvoices);
        request.setAttribute("shippers", shippers);
        request.getRequestDispatcher("GDDuyetDon410.jsp").forward(request, response);
    }

    private void approveInvoices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] invoiceIds = request.getParameterValues("invoiceId");
            String shipperId = request.getParameter("shipperId");

            for (String invoiceId : invoiceIds) {
                HoaDonKH410 invoice = hoaDonDAO.getHonDonID(invoiceId);
                invoice.setTrangthai(true);
                invoice.setShipper(giaoHangDAO.getNVGiaoHangId(shipperId));
                hoaDonDAO.setHoaDon(invoice);
            }

            List<HoaDonKH410> approvedInvoices = hoaDonDAO.getInvoicesByIds(invoiceIds);

            request.setAttribute("message", "Invoices approved successfully! You can now print the approved invoices.");
            request.setAttribute("approvedInvoices", approvedInvoices);

            showApprovalPage(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // Thêm dòng này để log chi tiết lỗi
            request.setAttribute("message", "An error occurred while processing the approval.");
            request.getRequestDispatcher("GDDuyetDon410.jsp").forward(request, response);
        }
    }


    private void printInvoices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HoaDonKH410> invoicesToPrint = hoaDonDAO.getInvoicesPrint();

        request.setAttribute("invoicesToPrint", invoicesToPrint);
        request.getRequestDispatcher("GDinHoaDon410.jsp").forward(request, response);
    }

}
