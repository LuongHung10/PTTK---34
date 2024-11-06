package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.NVBanHang410;


public class NVBanHangDAO410 extends DAO410{
	private Connection connection;
	
	public NVBanHangDAO410() throws SQLException, ClassNotFoundException {
		super();
		connection = super.getConnection();
	}
	
    public NVBanHang410 getSellerByName(String name) {
        NVBanHang410 seller = null;
        String query = "SELECT nguoidung410.id, nguoidung410.username, nguoidung410.password, nguoidung410.name, nguoidung410.diachi, nguoidung410.email, nguoidung410.sdt " +
                "FROM nvbanhang410 " +
                "JOIN nhanvien410 ON nvbanhang410.id = nhanvien410.id " +
                "JOIN nguoidung410 ON nhanvien410.id = nguoidung410.id " +
                "WHERE nvbanhang410.id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                seller = new NVBanHang410(
                    resultSet.getString("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("name"),
                    resultSet.getString("diachi"),
                    resultSet.getString("email"),
                    resultSet.getString("sdt"),
                    resultSet.getString("vitri")
                );
                System.out.println(resultSet.getString("id") + " " + resultSet.getString("name"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return seller;
    }
}
