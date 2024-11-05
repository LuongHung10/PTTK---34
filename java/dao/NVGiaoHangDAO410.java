package dao;

import model.NVGiaoHang410;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NVGiaoHangDAO410 extends DAO410 {
    private Connection connection;

    public NVGiaoHangDAO410() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }

    public List<NVGiaoHang410> getAllNVGiaoHang() {
        List<NVGiaoHang410> deliveryStaffList = new ArrayList<>();
        String sql = "SELECT * FROM nvgiaohang410"; // Giả sử bảng trong DB tên là nvgiaohang410

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String diachi = resultSet.getString("diachi");
                String email = resultSet.getString("email");
                String sdt = resultSet.getString("sdt");

                NVGiaoHang410 nvGiaoHang = new NVGiaoHang410(id, username, password, name, diachi, email, sdt, "Giao hang");
                deliveryStaffList.add(nvGiaoHang);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deliveryStaffList;
    }

    public NVGiaoHang410 getShipperById(String id) {
        NVGiaoHang410 shipper = null;
        String sql = "SELECT * FROM nvgiaohang410 WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String diachi = resultSet.getString("diachi");
                String email = resultSet.getString("email");
                String sdt = resultSet.getString("sdt");

                shipper = new NVGiaoHang410(id, username, password, name, diachi, email, sdt, "Giao hang");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shipper;
    }
}
