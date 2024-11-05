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
import model.NVBanHang410;
import model.NVGiaoHang410;
import dao.HoaDonDAO410;
import dao.NVBanHangDAO410;
import dao.NVGiaoHangDAO410;

@WebServlet("/InvoiceServlet")
public class InvoiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private HoaDonDAO410 hoaDonDAO;
    private NVGiaoHangDAO410 giaoHangDAO;
    private NVBanHangDAO410 banHangDAO;
    
    public void init() {
        try {
            hoaDonDAO = new HoaDonDAO410();
            giaoHangDAO = new NVGiaoHangDAO410();
            banHangDAO = new NVBanHangDAO410();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }    
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("approve".equals(action)) {
            showApprovalPage(request, response);
        } else if ("print".equals(action)) {
            printInvoices(request, response);
        } else {
            response.sendRedirect("seller.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("approveSelected".equals(action)) {
            approveInvoices(request, response);
        }
    }

    private void showApprovalPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HoaDonKH410> unapprovedInvoices = hoaDonDAO.getUnapprovedInvoices();
        List<NVGiaoHang410> shippers = giaoHangDAO.getAllNVGiaoHang();
        request.setAttribute("unapprovedInvoices", unapprovedInvoices);
        request.setAttribute("shippers", shippers);
        request.getRequestDispatcher("approveInvoices.jsp").forward(request, response);
    }

    private void approveInvoices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] invoiceIds = request.getParameterValues("invoiceId");
            String shipperId = request.getParameter("shipperId");
            String sellerName = (String) request.getSession().getAttribute("loggedInSeller");

            // Fetch the full seller object
            NVBanHang410 seller = banHangDAO.getSellerByName(sellerName);
            if (seller == null) {
                request.setAttribute("error", "Seller not found.");
                request.getRequestDispatcher("errorPage.jsp").forward(request, response);
                return;
            }

            for (String invoiceId : invoiceIds) {
                HoaDonKH410 invoice = hoaDonDAO.getInvoiceById(invoiceId);
                invoice.setTrangthai(true);
                invoice.setSeller(seller);
                invoice.setShipper(giaoHangDAO.getShipperById(shipperId));
                hoaDonDAO.updateInvoice(invoice);
            }

            // Set a success message and reload the page with updated data
            request.setAttribute("message", "Invoices approved successfully!");
            
            // Reload data to display updated unapproved invoices
            showApprovalPage(request, response);

        } catch (Exception e) {
            request.setAttribute("message", "An error occurred while processing the approval.");
            request.getRequestDispatcher("approveInvoices.jsp").forward(request, response);
        }
    }


    private void printInvoices(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] invoiceIds = request.getParameterValues("invoiceId");
        
        List<HoaDonKH410> invoicesToPrint = hoaDonDAO.getInvoicesByIds(invoiceIds);
        request.setAttribute("invoicesToPrint", invoicesToPrint);
        request.getRequestDispatcher("printInvoices.jsp").forward(request, response);
    }
}
