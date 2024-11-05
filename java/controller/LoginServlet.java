package controller;

import java.io.IOException;
import java.text.ParseException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.NguoiDung410;
import model.KhachHang410;
import model.NVBanHang410;
import model.NVQuanLi410;
import dao.NguoiDungDAO410;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NguoiDungDAO410 nguoiDungDao410;

    public void init() {
        try {
            nguoiDungDao410 = new NguoiDungDAO410();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            NguoiDung410 nguoiDung410 = nguoiDungDao410.login(email, password);
            if (nguoiDung410 != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", nguoiDung410);

                // Redirect based on the user role
                if (nguoiDung410 instanceof NVBanHang410) {
                    request.getRequestDispatcher("seller.jsp").forward(request, response);
                } else if (nguoiDung410 instanceof NVQuanLi410) {
                    request.getRequestDispatcher("manager.jsp").forward(request, response);
                } else if (nguoiDung410 instanceof KhachHang410) {
                    request.getRequestDispatcher("client.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Invalid email or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
