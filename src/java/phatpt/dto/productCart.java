/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class productCart implements Serializable{
	private String customerName;
	private HashMap<String, productDTO> cart;

	public productCart(String customerName, HashMap<String, productDTO> cart) {
		this.customerName = customerName;
		this.cart = cart;
	}

	public productCart(String customerName) {
		this.customerName = customerName;
		this.cart = new HashMap<>();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public HashMap<String, productDTO> getCart() {
		return cart;
	}

	public void setCart(HashMap<String, productDTO> cart) {
		this.cart = cart;
	}
	
	public void addToCart(productDTO dto) throws Exception{
		if(this.cart.containsKey(dto.getProductID())){
			return;
		} else{
			this.cart.put(dto.getProductID(), dto);
		}
	}
	
	public void remove(String id) throws Exception {
		if(this.cart.containsKey(id)){
			this.cart.remove(id);
		}
	}
	
	public float getTotal() throws Exception{
		float result = 0;
		for(productDTO dto : this.cart.values()){
			result += dto.getQuantity() * dto.getPrice();
		}
		return result;
	}
	
	public void updateCart(String id, int quantity) throws Exception{
		if(this.cart.containsKey(id)){
			this.cart.get(id).setQuantity(quantity);
		}	
	}

}
