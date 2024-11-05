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
        String query = "SELECT * FROM hoadon410 WHERE trangthai = FALSE";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
        	ResultSet rs = statement.executeQuery();
        	System.out.println(">>>> " + statement.toString());
        	
            while (rs.next()) {
                HoaDonKH410 invoice = new HoaDonKH410();
                invoice.setId(rs.getString("id"));

                KhachHang410 client = new KhachHang410();
                client.setId(rs.getString("client"));
                invoice.setClient(client);

                invoice.setTongTien(rs.getFloat("tongtien"));
                invoice.setTrangthai(rs.getBoolean("trangthai"));
                unapprovedInvoices.add(invoice);
                
                System.out.println(rs.getString("id") + " " + rs.getString("client") + " " + rs.getBoolean("trangthai"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unapprovedInvoices;
    }

    public void updateInvoice(HoaDonKH410 invoice) {
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
                client.setId(resultSet.getString("client"));
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
    
}
