/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import phatpt.db.MyConnection;
import phatpt.dto.categoryDTO;

/**
 *
 * @author Admin
 */
public class categoryDAO {
	private Connection conn;
	private PreparedStatement preStm;
	private ResultSet rs;
	
	private void closeConnection() throws Exception {
		if(rs != null)
			rs.close();
		if(preStm != null)
			preStm.close();
		if(conn != null)
			conn.close();
	}
	
	public List<categoryDTO> getAllCategory() throws Exception {
		List<categoryDTO> result = null;
		String cateID, cateName;
		categoryDTO dto;
		String sql = "SELECT cateID, cateName FROM tblCategory";
		try{
			conn = MyConnection.getMyConnection();
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList();
			while(rs.next()){				
				cateID = rs.getString("cateID");
				cateName = rs.getString("cateName");
				dto = new categoryDTO(cateID, cateName);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

}
