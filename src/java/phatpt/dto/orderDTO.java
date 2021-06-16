/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class orderDTO implements Serializable {

    String orderID, username, productName;
    float price;
    Date date;
    List<productDTO> listProduct;

    public orderDTO(String orderID, String username, float price, Date date, String productName) {
        this.orderID = orderID;
        this.username = username;
        this.price = price;
        this.date = date;
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<productDTO> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<productDTO> listProduct) {
        this.listProduct = listProduct;
    }

}
