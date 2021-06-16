/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatpt.dao.productDAO;
import phatpt.dto.productCart;
import phatpt.dto.productDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "addProductToCartController", urlPatterns = {"/addProductToCartController"})
public class addProductToCartController extends HttpServlet {

    private static final String GUEST = "login.jsp";
    private static final String USER_FOOD = "loadFoodController";
    private static final String USER_DRINK = "loadDrinkController";
    private static final String USER_SEARCH = "searchController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        String customer = (String) session.getAttribute("welcomeName");
        String page = request.getParameter("page");
        String url = null;
        if (role == null) {
            url = GUEST;
            response.sendRedirect(url);
            return;
        } else if (role.equals("user")) {
            if (page == null) {
                url = USER_FOOD;
            } else if (page.equals("drink")) {
                url = USER_DRINK;
            } else if (page.equals("searching")) {
                url = USER_SEARCH;
            }
        }
        try {
            String productID = request.getParameter("txtProductID");
            productDAO productDAO = new productDAO();
            productDTO productDTO = productDAO.findByPrimaryKey(productID);
            productDTO.setQuantity(1);
            productDTO.setCartPrice(productDTO.getPrice() * productDTO.getQuantity());
            productCart shoppingCart = (productCart) session.getAttribute("shoppingCart");
            if (shoppingCart == null) {
                shoppingCart = new productCart(customer);
            }
            shoppingCart.addToCart(productDTO);
            float totalPrice = shoppingCart.getTotal();
            session.setAttribute("shoppingCart", shoppingCart);
            session.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            log("ERROR AT AddProductToCartController: " + e.getMessage());
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
