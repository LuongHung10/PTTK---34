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
        String query = "SELECT * FROM NVBanHang WHERE name = ?";
        
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return seller;
    }
}