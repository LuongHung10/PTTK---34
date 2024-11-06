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
        String sql = "SELECT nguoidung410.id, nguoidung410.username, nguoidung410.password, nguoidung410.name, nguoidung410.diachi, nguoidung410.email, nguoidung410.sdt " +
                     "FROM nvgiaohang410 " +
                     "JOIN nhanvien410 ON nvgiaohang410.id = nhanvien410.id " +
                     "JOIN nguoidung410 ON nhanvien410.id = nguoidung410.id";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");

                NVGiaoHang410 nvGiaoHang = new NVGiaoHang410(id, username, password, name, diachi, email, sdt, "Giao hang");
                deliveryStaffList.add(nvGiaoHang);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deliveryStaffList;
    }

    public NVGiaoHang410 getNVGiaoHangId(String id) {
        NVGiaoHang410 shipper = null;
        String sql = "SELECT nguoidung410.id, nguoidung410.username, nguoidung410.password, nguoidung410.name, nguoidung410.diachi, nguoidung410.email, nguoidung410.sdt " +
                     "FROM nvgiaohang410 " +
                     "JOIN nhanvien410 ON nvgiaohang410.id = nhanvien410.id " +
                     "JOIN nguoidung410 ON nhanvien410.id = nguoidung410.id " +
                     "WHERE nvgiaohang410.id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String sdt = rs.getString("sdt");

                shipper = new NVGiaoHang410(id, username, password, name, diachi, email, sdt, "Giao hang");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shipper;
    }
}
