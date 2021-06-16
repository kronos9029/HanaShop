/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatpt.dao.productDAO;
import phatpt.dto.productDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "updateProductController", urlPatterns = {"/updateProductController"})
public class updateProductController extends HttpServlet {
	private static final String LOAD_FOOD = "loadFoodController";
	private static final String LOAD_DRINK = "loadDrinkController";
	private static final String SEARCHING = "searchController";
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		String url = "error.jsp";
		String page = request.getParameter("page");
		HttpSession session = request.getSession();
		String updateUser = (String)session.getAttribute("welcomeName");
		if(page.equals("searching"))
			url = SEARCHING;
		if(page.equals("food"))
			url = LOAD_FOOD;
		if(page.equals("drink"))
			url = LOAD_DRINK;
		try {
			String cateID = request.getParameter("txtCateID");
			String productID = request.getParameter("txtProductID");
			String productName = request.getParameter("txtProductName");
			String description = request.getParameter("txtDescription");
			String productPic = request.getParameter("txtProductPic").trim();
			float price = Float.parseFloat(request.getParameter("txtProductPrice"));
			int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
			String status = request.getParameter("txtStatus");
			Date createDate = new Date();
			Date updateDate = new Date();
			productDAO dao = new productDAO();
			productDTO dto = new productDTO(productID, cateID, productName, productPic, status, quantity, price, createDate, updateDate, updateUser, description);
			dao.update(dto);
		
		} catch (Exception e) {
			log("ERROR at EditController: " + e.getMessage());
		} finally {
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
