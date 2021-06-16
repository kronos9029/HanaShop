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
import phatpt.dto.orderDTO;
import phatpt.dto.productDTO;

/**
 *
 * @author Admin
 */
public class orderDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

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

    public String getLastOrderIDByUser(String username) throws Exception {
        String orderID = null;
        try {
            String sql = "SELECT orderID FROM tblOrder WHERE DateOfCreate = (SELECT MAX(DateOfCreate) FROM tblOrder WHERE username = ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                orderID = rs.getString("orderID");
            }
        } finally {
            closeConnection();
        }
        return orderID;
    }

    public boolean createOrder(String orderID, String username, float total, Date dateOfCreate) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO tblOrder(orderID, username, totalPrice, DateOfCreate) "
                    + "VALUES(?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, orderID);
            preStm.setString(2, username);
            preStm.setFloat(3, total);
            preStm.setTimestamp(4, new Timestamp(dateOfCreate.getTime()));
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean createOrderDetail(String orderDetailID, String orderID, String productID, int quantity, float price) throws Exception {
        boolean check = false;
        try {
            String sql = "INSERT INTO tblOrderDetail(orderDetaiID, orderID, productID, quantity, price) VALUES(?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, orderDetailID);
            preStm.setString(2, orderID);
            preStm.setString(3, productID);
            preStm.setInt(4, quantity);
            preStm.setFloat(5, price);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<orderDTO> getOrder(String username, int Month, String role, String productName) throws Exception {
        String orderID;
        float totalPrice;
        Date date;
        orderDTO dto;
        List<orderDTO> listOrder = new ArrayList<>();
        try {
            if (role.equals("user")) {
                String sql = "SELECT o.orderID, o.username, o.totalPrice, o.DateOfCreate FROM tblOrder o INNER JOIN tblOrderDetail d ON o.orderID = d.orderID INNER JOIN tblProduct p ON p.productID = d.productID WHERE p.productName LIKE ? AND o.username = ? AND MONTH(o.DateOfCreate) = ?";
                conn = MyConnection.getMyConnection();
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + productName + "%");
                preStm.setString(2, username);
                preStm.setInt(3, Month);
                rs = preStm.executeQuery();
            } else {
                String sql = "SELECT o.orderID, o.username, o.totalPrice, o.DateOfCreate FROM tblOrder o INNER JOIN tblOrderDetail d ON o.orderID = d.orderID INNER JOIN tblProduct p ON p.productID = d.productID WHERE p.productName LIKE ? AND o.username LIKE ? AND MONTH(o.DateOfCreate) = ?";
                conn = MyConnection.getMyConnection();
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, "%" + productName + "%");
                preStm.setString(2, "%" + username + "%");
                preStm.setInt(3, Month);
                rs = preStm.executeQuery();
            }
            while (rs.next()) {
                orderID = rs.getString("orderID");
                username = rs.getString("username");
                totalPrice = rs.getFloat("totalPrice");
                date = rs.getDate("DateOfCreate");
                dto = new orderDTO(orderID, username, totalPrice, date, productName);
                listOrder.add(dto);
            }

        } finally {
            closeConnection();
        }
        return listOrder;
    }

    public List<productDTO> getOderdetail(String orderID) throws Exception {
        String productName, picture;
        int quantity;
        float price;
        Date dateOfCreate;
        productDTO dto;
        List<productDTO> listProduct = new ArrayList<>();
        try {
            String sql = "SELECT p.productName, p.picture, p.price, d.quantity, o.DateOfCreate FROM tblOrder o INNER JOIN tblOrderDetail d ON o.orderID = d.orderID INNER JOIN tblProduct p ON p.productID = d.productID WHERE o.orderID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, orderID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productName = rs.getString("productName");
                picture = rs.getString("picture");
                quantity = rs.getInt("quantity");
                price = rs.getFloat("price");
                dateOfCreate = rs.getDate("DateOfCreate");
                dto = new productDTO(productName, picture, quantity, price, dateOfCreate);
                listProduct.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listProduct;
    }

}
