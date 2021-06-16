/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phatpt.dao.productDAO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "deleteProductController", urlPatterns = {"/deleteProductController"})
public class deleteProductController extends HttpServlet {
	private static final String LOAD_FOOD = "loadFoodController";
	private static final String LOAD_DRINK = "loadDrinkController";
	private static final String SEARCH = "searchController";
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
		if(page.equals("drink"))
			url = LOAD_DRINK;
		else if(page.equals("food"))
			url = LOAD_FOOD;
		else if(page.equals("searching"))
			url = SEARCH;
		try {
			String search = request.getParameter("txtSearchValue");
			String withCate = request.getParameter("searchCateID");
			String indexString = request.getParameter("index");
			String minString = request.getParameter("txtMinPrice");
			String maxString = request.getParameter("txtMaxPrice");
			String[] listProductID = request.getParameterValues("chkRemove");
			if (listProductID == null) {
			} else {
				productDAO dao = new productDAO();
				for (String id : listProductID) {
					dao.delete(id);
				}
			}
			request.setAttribute("txtSearchValue", search);
			request.setAttribute("searchCateID", withCate);
			request.setAttribute("txtMinPrice", minString);
			request.setAttribute("txtMaxPrice", maxString);
			request.setAttribute("index", indexString);
		} catch (Exception e) {
			log("ERROR At DeleteController: " + e.getMessage());
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
