package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonKH410;
import model.KhachHang410; // Import lớp KhachHang410 nếu cần

public class HoaDonDAO410 extends DAO410 {
	
	private Connection connection;
	private List<HoaDonKH410> approvedInvoices;

    public HoaDonDAO410() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
        approvedInvoices = new ArrayList<>();
    }

    public List<HoaDonKH410> getHoaDon() {
        List<HoaDonKH410> unapprovedInvoices = new ArrayList<>();
        String query = "SELECT * FROM hoadon410 WHERE trangthai = FALSE";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	ResultSet rs = statement.executeQuery();
        	System.out.println(">>>> " + statement.toString());
        	
            while (rs.next()) {
                HoaDonKH410 invoice = new HoaDonKH410();
                invoice.setId(rs.getString("id"));

                KhachHang410 client = new KhachHang410();
                client.setName(rs.getString("client"));
                invoice.setClient(client);

                invoice.setTongTien(rs.getFloat("tongtien"));
                invoice.setTrangthai(rs.getBoolean("trangthai"));
                unapprovedInvoices.add(invoice);
                
                System.out.println(client.setName(rs.getString("client")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unapprovedInvoices;
    }

    public void setHoaDon(HoaDonKH410 invoice) {
        String query = "UPDATE hoadon410 SET trangthai = ?, seller = ?, shipper = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, invoice.isTrangthai());
            statement.setString(2, (invoice.getSeller() != null) ? invoice.getSeller().getId() : null);
            statement.setString(3, (invoice.getShipper() != null) ? invoice.getShipper().getId() : null);
            statement.setString(4, invoice.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Lấy hóa đơn theo ID
    public HoaDonKH410 getHonDonID(String id) {
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

                invoice.setTongTien((float) resultSet.getDouble("tongtien"));
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
                String clientId = resultSet.getString("client");
                client.setId(clientId);
                
                String clientNameQuery = "SELECT name FROM khachhang410 WHERE id = ?";
                try (PreparedStatement clientStatement = connection.prepareStatement(clientNameQuery)) {
                    clientStatement.setString(1, clientId);
                    ResultSet clientResultSet = clientStatement.executeQuery();
                    
                    if (clientResultSet.next()) {
                        client.setName(clientResultSet.getString("name"));
                    }
                }

                invoice.setClient(client);
                invoice.setTongTien((float) resultSet.getDouble("tongtien"));
                invoice.setTrangthai(resultSet.getBoolean("trangthai"));
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    
    public List<HoaDonKH410> getInvoicesPrint() {
        List<HoaDonKH410> invoices = new ArrayList<>();

        // Truy vấn để lấy tất cả các hóa đơn có trạng thái là 1
        String query = "SELECT * FROM hoadon410 WHERE trangthai = 1";

        try (PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                HoaDonKH410 invoice = new HoaDonKH410();
                invoice.setId(resultSet.getString("id"));

                // Lấy thông tin khách hàng
                KhachHang410 client = new KhachHang410();
                String clientId = resultSet.getString("client");
                client.setId(clientId);

                String clientNameQuery = "SELECT name FROM khachhang410 WHERE id = ?";
                try (PreparedStatement clientStatement = connection.prepareStatement(clientNameQuery)) {
                    clientStatement.setString(1, clientId);
                    ResultSet clientResultSet = clientStatement.executeQuery();

                    if (clientResultSet.next()) {
                        client.setName(clientResultSet.getString("name"));
                    }
                }

                invoice.setClient(client);
                invoice.setTongTien((float) resultSet.getDouble("tongtien"));
                invoice.setTrangthai(resultSet.getBoolean("trangthai"));
                invoices.add(invoice);
            }

            // Cập nhật trạng thái của các hóa đơn đã lấy được lên 2
            String updateQuery = "UPDATE hoadon410 SET trangthai = 2 WHERE trangthai = 1";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
}
