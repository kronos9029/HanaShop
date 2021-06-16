/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import phatpt.db.MyConnection;
import phatpt.dto.productDTO;

/**
 *
 * @author Admin
 */
public class productDAO {

    Connection conn;
    PreparedStatement preStm;
    ResultSet rs;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<productDTO> getAllproductID() throws Exception {
        List<productDTO> result = new ArrayList<productDTO>();
        String sql = "SELECT productID, productName, quantity FROM tblProduct";
        productDTO dto;
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                dto = new productDTO(rs.getString("productID"), rs.getString("productName"), rs.getInt("quantity"));
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public productDTO getProductByID(String id) throws Exception {
        String sql = "SELECT productID, productName, quantity FROM tblProduct WHERE productID = ?";
        productDTO dto = null;
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String producID = rs.getString("productID");
                String productName = rs.getString("productName");
                int quantity = rs.getInt("quantity");
                dto = new productDTO(producID, productName, quantity);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<productDTO> getProductByCateID(String cateID, int index, String role) throws Exception {
        List<productDTO> result = null;
        productDTO dto = null;
        String productID, productName, picture, status, updateUser, description;
        float price;
        int quantity;
        Date createDate;
        Date updateDate;
        String sql = null;
        if (null == role) {
            sql = "WITH x AS (SELECT ROW_NUMBER() OVER(ORDER BY createDate DESC) AS roll\n"
                    + ",productID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM tblProduct WHERE cateID = ? AND status = 'active' AND quantity > 0)\n"
                    + "SELECT productID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM x WHERE roll BETWEEN ?*20-19 AND ?*20";
        } else {
            switch (role) {
                case "admin":
                    sql = "WITH x AS (SELECT ROW_NUMBER() OVER(ORDER BY createDate DESC) AS roll\n"
                            + ",productID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM tblProduct WHERE cateID = ?)\n"
                            + "SELECT productID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM x WHERE roll BETWEEN ?*20-19 AND ?*20";
                    break;
                default:
                    sql = "WITH x AS (SELECT ROW_NUMBER() OVER(ORDER BY createDate DESC) AS roll\n"
                            + ",productID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM tblProduct WHERE cateID = ? AND status = 'active' AND quantity > 0)\n"
                            + "SELECT productID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM x WHERE roll BETWEEN ?*20-19 AND ?*20";
                    break;
            }
        }
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, cateID);
            preStm.setInt(2, index);
            preStm.setInt(3, index);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                productID = rs.getString("productID");
                productName = rs.getString("productName");
                description = rs.getString("description");
                picture = rs.getString("picture");
                price = rs.getFloat("price");
                quantity = rs.getInt("quantity");
                createDate = rs.getDate("createDate");
                status = rs.getString("status");
                updateDate = rs.getDate("updateDate");
                updateUser = rs.getString("updateUser");
                dto = new productDTO(productID, cateID, productName, picture, status, quantity, price, createDate, updateDate, updateUser, description);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int countAll(String cateID) throws Exception {
        try {
            String sql = "SELECT count(*) FROM tblProduct WHERE cateID = ? AND quantity > 0 AND status = 'active'";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, cateID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public int count(String txtSearch, String cateID) throws Exception {
        try {
            String sql;
            if (cateID.equals("all")) {
                sql = "SELECT count(*) FROM tblProduct WHERE productName LIKE ? AND quantity > 0 AND status = 'active'";
            } else {
                sql = "SELECT count(*) FROM tblProduct WHERE productName LIKE ? AND cateID = ? AND quantity > 0 AND status = 'active'";
            }
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            if (cateID.equals("all")) {
                preStm.setString(1, "%" + txtSearch + "%");
            } else {
                preStm.setString(1, "%" + txtSearch + "%");
                preStm.setString(2, cateID);
            }
            rs = preStm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public int countWithPrice(String searchValue, float min, float max) throws Exception {
        String sql = "SELECT count(*) FROM tblProduct WHERE productName LIKE ? AND price >= ? AND price <= ? AND quantity > 0 AND status = 'active'";
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + searchValue + "%");
            preStm.setFloat(2, min);
            preStm.setFloat(3, max);
            rs = preStm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return 0;
    }

    public List<productDTO> searchWithName(String searchValue, String withID, int index) throws Exception {
        productDTO dto = null;
        String productID, productName, picture, cateID, updateUser, description, status;
        float price;
        int quantity;
        Date createDate, updateDate;
        List<productDTO> listProduct;
        String sql;
        if (withID.equals("all")) {
            sql = "WITH x AS (SELECT ROW_NUMBER() OVER(ORDER BY createDate DESC) AS roll\n"
                    + ",productID, cateID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM tblProduct WHERE productName LIKE ? AND quantity > 0 AND status = 'active')\n"
                    + "SELECT productID, cateID, productName,description, picture, price, quantity, createDate, status, updateDate, updateUser FROM x WHERE roll BETWEEN ?*10-9 AND ?*10";
        } else {
            sql = "WITH x AS (SELECT ROW_NUMBER() OVER(ORDER BY createDate DESC) AS roll\n"
                    + ",productID, cateID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM tblProduct WHERE productName LIKE ? AND cateID = ? AND quantity > 0 AND status = 'active')\n"
                    + "SELECT productID, cateID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM x WHERE roll BETWEEN ?*10-9 AND ?*10";
        }

        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            if (withID.equals("all")) {
                preStm.setString(1, "%" + searchValue + "%");
                preStm.setInt(2, index);
                preStm.setInt(3, index);
            } else {
                preStm.setString(1, "%" + searchValue + "%");
                preStm.setString(2, withID);
                preStm.setInt(3, index);
                preStm.setInt(4, index);
            }
            rs = preStm.executeQuery();
            listProduct = new ArrayList<>();
            while (rs.next()) {
                productID = rs.getString("productID");
                cateID = rs.getString("cateID");
                productName = rs.getString("productName");
                description = rs.getString("description");
                picture = rs.getString("picture");
                price = rs.getFloat("price");
                quantity = rs.getInt("quantity");
                createDate = rs.getDate("createDate");
                status = rs.getString("status");
                updateDate = rs.getDate("updateDate");
                updateUser = rs.getString("updateUser");
                dto = new productDTO(productID, cateID, productName, picture, status, quantity, price, createDate, updateDate, updateUser, description);
                if (withID.equals("all")) {
                    listProduct.add(dto);
                }

                if (withID.equals(cateID)) {
                    listProduct.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return listProduct;
    }

    public List<productDTO> searchInRange(String searchValue, int index, float min, float max) throws Exception {
        productDTO dto = null;
        String productID, productName, picture, cateID, updateUser, description, status;
        float price;
        int quantity;
        Date createDate, updateDate;
        List<productDTO> listProduct;
        String sql = "WITH x AS (SELECT ROW_NUMBER() OVER(ORDER BY createDate DESC) AS roll\n"
                + ",productID, cateID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM tblProduct WHERE productName LIKE ? AND price >= ? AND price <= ? AND quantity > 0 AND status = 'active')\n"
                + "SELECT productID, cateID, productName, description, picture, price, quantity, createDate, status, updateDate, updateUser FROM x WHERE roll BETWEEN ?*10-9 AND ?*10";

        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + searchValue + "%");
            preStm.setFloat(2, min);
            preStm.setFloat(3, max);
            preStm.setInt(4, index);
            preStm.setInt(5, index);
            rs = preStm.executeQuery();
            listProduct = new ArrayList<>();
            while (rs.next()) {
                productID = rs.getString("productID");
                cateID = rs.getString("cateID");
                productName = rs.getString("productName");
                description = rs.getString("description");
                picture = rs.getString("picture");
                price = rs.getFloat("price");
                quantity = rs.getInt("quantity");
                createDate = rs.getDate("createDate");
                status = rs.getString("status");
                updateDate = rs.getDate("updateDate");
                updateUser = rs.getString("updateUser");
                dto = new productDTO(productID, cateID, productName, picture, status, quantity, price, createDate, updateDate, updateUser, description);
                listProduct.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listProduct;
    }

    public boolean insert(String productID, String cateID, String productName, String description, String picture, float price, Date createDate, int quantity) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO tblProduct(productID, cateID, productName, description, picture, price, createDate, quantity, status) VALUES(?,?,?,?,?,?,?,?,'active')";
            Connection conn = MyConnection.getMyConnection();
            PreparedStatement preStm = conn.prepareCall(sql);
            preStm.setString(1, productID);
            preStm.setString(2, cateID);
            preStm.setString(3, productName);
            preStm.setString(4, description);
            preStm.setString(5, picture);
            preStm.setFloat(6, price);
            preStm.setTimestamp(7, new Timestamp(createDate.getTime()));
            preStm.setInt(8, quantity);
            check = preStm.executeUpdate() > 0;
            return check;
        } finally {
            closeConnection();
        }
    }

    public boolean delete(String productID) throws Exception {
        boolean check = false;
        try {
            String sql = "UPDATE tblProduct SET status = 'inactive' WHERE productID= ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, productID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int update(productDTO product) throws Exception {
        try {
            String sql = "UPDATE tblProduct SET cateID = ?, productName = ?, description = ?, picture = ?, price = ?, quantity = ?, status = ?, updateDate = ?, updateUser = ? WHERE productID= ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(10, product.getProductID());
            preStm.setString(1, product.getCateID());
            preStm.setString(2, product.getProductName());
            preStm.setString(3, product.getDescription());
            preStm.setString(4, product.getPicture());
            preStm.setFloat(5, product.getPrice());
            preStm.setInt(6, product.getQuantity());
            preStm.setString(7, product.getStatus());
            preStm.setTimestamp(8, new Timestamp(product.getUpdateDate().getTime()));
            preStm.setString(9, product.getUpdateUser());
            return preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public boolean updateQuantity(int quantity, String productID) throws Exception {
        try {
            String sql = "UPDATE tblProduct SET quantity = ? WHERE productID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            preStm.setString(2, productID);
            return preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
    }

    public productDTO findByPrimaryKey(String productID) throws Exception {
        productDTO dto = null;
        String productName, picture, cateID;
        float price;
        String sql = "SELECT cateID, productName, picture, price FROM tblProduct WHERE productID = ?";
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, productID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                cateID = rs.getString("cateID");
                productName = rs.getString("productName");
                picture = rs.getString("picture");
                price = rs.getFloat("price");
                dto = new productDTO(productID, cateID, productName, picture, price);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
