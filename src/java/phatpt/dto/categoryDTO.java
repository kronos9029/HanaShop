/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Admin
 */
public class categoryDTO implements Serializable{
	String cateID, cateName;

	List<productDTO> listProduct;

	public List<productDTO> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<productDTO> listProduct) {
		this.listProduct = listProduct;
	}
	
	public categoryDTO(String cateID, String cateName) {
		this.cateID = cateID;
		this.cateName = cateName;
	}

	public String getCateID() {
		return cateID;
	}

	public void setCateID(String cateID) {
		this.cateID = cateID;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

}
