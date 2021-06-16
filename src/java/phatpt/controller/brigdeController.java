/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phatpt.dao.categoryDAO;
import phatpt.dao.productDAO;
import phatpt.dto.categoryDTO;
import phatpt.dto.productDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "brigdeController", urlPatterns = {"/brigdeController"})
public class brigdeController extends HttpServlet {
	private static final String CREATE_PRODUCT = "createFood.jsp";
	private static final String UPDATE_PRODUCT = "updateProduct.jsp";
	private static final String VIEW_CART = "shoppingCart.jsp";
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
		String url = null;
		String index = request.getParameter("index");
		try {
			String action = request.getParameter("action");
			String page = request.getParameter("page");
			String productID = request.getParameter("txtProductID");
			String productName = request.getParameter("txtProductName");
			String productPic = request.getParameter("txtPicture");
			String productDes = request.getParameter("txtDescription");
			String price = request.getParameter("txtPrice");
			String quantity = request.getParameter("txtQuantity");
			String searchValue = request.getParameter("txtSearchValue");
			String cateID = request.getParameter("searchCateID");
			String minPrice = request.getParameter("txtMinPrice");
			String maxPrice = request.getParameter("txtMaxPrice");
			if(action.equals("createProduct")){
				url = CREATE_PRODUCT;
			} else if(action.equals("updateProduct")){
				url = UPDATE_PRODUCT;
			} else if (action.equals("viewCart"))
				url = VIEW_CART;
			categoryDAO cateDAO = new categoryDAO();
			List<categoryDTO> listCate = cateDAO.getAllCategory();
			productDAO dao = new productDAO();
			List<productDTO> listProduct = dao.getAllproductID();
			request.setAttribute("listID", listProduct);
			request.setAttribute("listCate", listCate);
			request.setAttribute("index", index);
			request.setAttribute("page", page);
			request.setAttribute("txtProductID", productID);
			request.setAttribute("txtProductName", productName);
			request.setAttribute("txtDescription", productDes);
			request.setAttribute("txtPicture", productPic);
			request.setAttribute("txtPrice", price);
			request.setAttribute("txtQuantity", quantity);
			request.setAttribute("txtSearchValue", searchValue);
			request.setAttribute("searchCateID", cateID);
			request.setAttribute("txtMinPrice", minPrice);
			request.setAttribute("txtMaxPrice", maxPrice);
		} catch (Exception e) {
			log("ERROR AT bridgeController: "+ e.getMessage());
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
