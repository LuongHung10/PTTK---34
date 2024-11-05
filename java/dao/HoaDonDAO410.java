package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonKH410;
import model.KhachHang410; // Import lớp KhachHang410 nếu cần

public class HoaDonDAO410 extends DAO410 {
	
	private Connection connection;

    public HoaDonDAO410() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }

    public List<HoaDonKH410> getUnapprovedInvoices() {
        List<HoaDonKH410> unapprovedInvoices = new ArrayList<>();
        String query = "SELECT * FROM hoadon410 WHERE trangthai = false";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                HoaDonKH410 invoice = new HoaDonKH410();
                invoice.setId(resultSet.getString("id"));

                // Tạo một đối tượng KhachHang410 để lưu client, hoặc sửa thành `invoice.setClient(resultSet.getString("client"));` nếu client chỉ là tên/ID
                KhachHang410 client = new KhachHang410();
                client.setId(resultSet.getString("client"));
                invoice.setClient(client);

                // Chuyển đổi double sang float khi cần
                invoice.setTongTien((float) resultSet.getDouble("tong_tien"));
                invoice.setTrangthai(resultSet.getBoolean("trangthai"));
                unapprovedInvoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unapprovedInvoices;
    }

    public void updateInvoice(HoaDonKH410 invoice) {
        String query = "UPDATE hoadon410 SET trangthai = ?, seller = ?, shipper = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, invoice.isTrangthai()); // Giả sử có phương thức isTrangthai()
            statement.setString(2, invoice.getSeller().getName()); // Giả định seller là đối tượng có tên
            statement.setString(3, invoice.getShipper().getName()); // Giả định shipper là đối tượng có tên
            statement.setString(4, invoice.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy hóa đơn theo ID
    public HoaDonKH410 getInvoiceById(String id) {
        HoaDonKH410 invoice = null;
        String query = "SELECT * FROM hoadon410 WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                invoice = new HoaDonKH410();
                invoice.setId(resultSet.getString("id"));

                KhachHang410 client = new KhachHang410();
                client.setId(resultSet.getString("client"));
                invoice.setClient(client);

                invoice.setTongTien((float) resultSet.getDouble("tong_tien"));
                invoice.setTrangthai(resultSet.getBoolean("trangthai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    // Lấy danh sách hóa đơn theo danh sách ID
    public List<HoaDonKH410> getInvoicesByIds(String[] ids) {
        List<HoaDonKH410> invoices = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM hoadon410 WHERE id IN (");

        for (int i = 0; i < ids.length; i++) {
            query.append("?");
            if (i < ids.length - 1) query.append(",");
        }
        query.append(")");

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {

            for (int i = 0; i < ids.length; i++) {
                statement.setString(i + 1, ids[i]);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HoaDonKH410 invoice = new HoaDonKH410();
                invoice.setId(resultSet.getString("id"));

                KhachHang410 client = new KhachHang410();
                client.setId(resultSet.getString("client"));
                invoice.setClient(client);

                invoice.setTongTien((float) resultSet.getDouble("tong_tien"));
                invoice.setTrangthai(resultSet.getBoolean("trangthai"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    
}
