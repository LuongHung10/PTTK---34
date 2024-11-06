package dao;

import java.sql.Connection;
import java.text.ParseException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.KhachHang410;
import model.NVBanHang410;
import model.NVQuanLi410;
import model.NguoiDung410;

public class NguoiDungDAO410 extends DAO410{
	private Connection connection;
	
	public NguoiDungDAO410() throws SQLException, ClassNotFoundException {
		super();
		connection = super.getConnection();
	}
	
	public NguoiDung410 login(String email, String password) throws ParseException{
		String SELECT_USER = "SELECT * FROM nguoidung410 WHERE (email=? AND password=?)";
		try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(">>>> " + preparedStatement.toString());

            while (rs.next()) {
                String id = rs.getString("id");
                System.out.println("id: " + id);
                String name = rs.getString("name");
                String diachi = rs.getString("diachi");
                String sdt = rs.getString("sdt");
                String username = rs.getString("username");

                if (id.startsWith("C")) {
                    boolean laKH = laKH(id);
                    KhachHang410 client = new KhachHang410();
                    client.setId(id)
                            .setName(name)
                            .setUsername(username)
                            .setEmail(email)
                            .setDiachi(diachi)
                            .setSdt(sdt);
                    client.setKH(laKH);
                    System.out.println(laKH);
                    return client;
                } else if (id.startsWith("M")) {
                    NVQuanLi410 manager = new NVQuanLi410();
                    manager.setId(id)
                            .setName(name)
                            .setUsername(username)
                            .setEmail(email)
                            .setDiachi(diachi)
                            .setPassword(password)
                            .setSdt(sdt);
                    return manager;
                } else if (id.startsWith("S")) {
                    NVBanHang410 seller = new NVBanHang410();
                    seller.setId(id)
                    		.setName(name)
                    		.setUsername(username)
                    		.setEmail(email)
                    		.setDiachi(diachi)
                    		.setPassword(password)
                    		.setSdt(sdt);
                    return seller;
                }

            }
        } catch (SQLException ex) {
            printSQLException(ex);
            System.out.println(">>>> try catch");
            return null;
        }
        return null;

    }

	private boolean laKH(String id) {
        String SELECT_CLIENT = "SELECT * FROM khachhang410 WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT);
            preparedStatement.setString(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                boolean laKH = rs.getBoolean("laKH");
                return laKH;
            }

        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return false;
    }

//	private String getVitri(String id) {
//        String SELECT_EMPLOYEE = "SELECT * FROM tblEmployee082 WHERE id=?";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE);
//            preparedStatement.setString(1, id);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                String vitri = rs.getString("vitri");
//                return vitri;
//            }
//
//        } catch (SQLException ex) {
//            printSQLException(ex);
//        }
//        return null;
//	}
	
	private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
