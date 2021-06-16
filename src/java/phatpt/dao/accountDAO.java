/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import phatpt.db.MyConnection;

/**
 *
 * @author Admin
 */
public class accountDAO implements Serializable{
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	private void closeConnection() throws Exception{
		if(rs != null)
			rs.close();
		if(ps != null)
			ps.close();
		if(conn != null)
			conn.close();
	}

	public String checkLogin(String username, String password) throws Exception{
		String role = "failed";
		String sql = "SELECT role FROM tblAccount WHERE username = ? AND password = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
		try{
			conn = MyConnection.getMyConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				role = rs.getString("Role");
			}
		} finally {
			closeConnection();
		}
		return role;
	}	
}
