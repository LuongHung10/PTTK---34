package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.MatHang410;

public class MatHangDAO410 extends DAO410 {
    private Connection connection;

    public MatHangDAO410() throws SQLException, ClassNotFoundException {
        super();
        connection = super.getConnection();
    }

    public List<MatHang410> searchProducts(String name) {
        List<MatHang410> products = new ArrayList<>();
        String query = "SELECT * FROM mathang410 WHERE name LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(">>>> " + preparedStatement.toString());

            while (rs.next()) {
                MatHang410 product = new MatHang410();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                products.add(product);
                
                System.out.println(rs.getString("name") + " " + rs.getFloat("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public MatHang410 getMatHang(int id) {
        MatHang410 product = null;
        String query = "SELECT * FROM mathang410 WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                product = new MatHang410();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setSoluong(rs.getInt("soluong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
