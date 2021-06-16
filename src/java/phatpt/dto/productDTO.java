/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class productDTO implements Serializable{
	String productID, cateID, productName, picture, status, description, updateUser;
	int quantity;
	float price, cartPrice;
	Date createDate, updateDate;

	public productDTO() {
	}	

	public productDTO(String productName, String picture, int quantity, float price, Date createDate) {
		this.productName = productName;
		this.picture = picture;
		this.quantity = quantity;
		this.price = price;
		this.createDate = createDate;
	}
	
	public productDTO(String productID, String productName, int quantity) {
		this.productID = productID;
		this.productName = productName;
		this.quantity = quantity;
	}
	
	public productDTO(String productID, String cateID, String productName, String picture, int quantity, float price, Date createDate, Date updateDate, String updateUser, String description) {
		this.productID = productID;
		this.cateID = cateID;
		this.productName = productName;
		this.picture = picture;
		this.quantity = quantity;
		this.price = price;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.description = description;
	}

	public productDTO(String productID, String cateID, String productName, String picture, String status, int quantity, float price, Date createDate, Date updateDate, String updateUser, String description) {
		this.productID = productID;
		this.cateID = cateID;
		this.productName = productName;
		this.picture = picture;
		this.status = status;
		this.quantity = quantity;
		this.price = price;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.description = description;
	}

	public productDTO(String productID, String cateID, String productName, String picture, float price) {
		this.productID = productID;
		this.cateID = cateID;
		this.productName = productName;
		this.picture = picture;
		this.price = price;
	}	

	public float getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(float cartPrice) {
		this.cartPrice = cartPrice;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getCateID() {
		return cateID;
	}

	public void setCateID(String cateID) {
		this.cateID = cateID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	

}
