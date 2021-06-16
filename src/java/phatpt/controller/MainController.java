/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

	private static final String LOAD_FOOD = "loadFoodController";
	private static final String LOAD_DRINK = "loadDrinkController";
	private static final String LOGIN = "loginController";
	private static final String LOGOUT = "logoutController";
	private static final String SEARCH = "searchController";
	private static final String BRIGDE = "brigdeController";
	private static final String CREATE = "createProductController";
	private static final String UPDATE = "updateProductController";
	private static final String DELETE = "deleteProductController";
	private static final String ADD_TO_CART = "addProductToCartController";
	private static final String REMOVE_FROM_CART = "removeFromCartController";
	private static final String CONFIRM_ORDER = "confirmOrderController";
	private static final String TO_CART = "brigdeController";
	private static final String HISTORY = "history.jsp";
	private static final String HISTORY_RESULT = "historyController";
	private static final String UPDATE_CART = "updateCartQuantityController";
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
		String url = LOAD_FOOD;
		String action = request.getParameter("action");
		try {
			if(action == null)
				url = LOAD_FOOD;
			else if(action.equals("drink"))
				url = LOAD_DRINK;
			else if(action.equals("Login"))
				url = LOGIN;
			else if(action.equals("Search"))
				url = SEARCH;
			else if(action.equals("createProduct"))
				url = BRIGDE;
			else if(action.equals("updateProduct"))
				url = BRIGDE;
			else if(action.equals("Create"))
				url = CREATE;
			else if(action.equals("Delete"))
				url = DELETE;
			else if(action.equals("Update"))
				url = UPDATE;
			else if(action.equals("addToCart"))
				url = ADD_TO_CART;
			else if(action.equals("Remove"))
				url = REMOVE_FROM_CART;
			else if(action.equals("Checkout"))
				url = CONFIRM_ORDER;
			else if(action.equals("viewCart"))
				url = TO_CART;
			else if(action.equals("history"))
				url = HISTORY;
			else if(action.equals("historySearch"))
				url = HISTORY_RESULT;
			else if(action.equals("logout"))
				url = LOGOUT;
			else if(action.equals("updateQuantity"))
				url = UPDATE_CART;
			
		} catch (Exception e) {
			log("ERROR AT MainController: "+ e.getMessage());
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
