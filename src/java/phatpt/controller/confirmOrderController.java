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
import phatpt.dao.orderDAO;
import phatpt.dao.productDAO;
import phatpt.dto.productCart;
import phatpt.dto.productDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "confirmOrderController", urlPatterns = {"/confirmOrderController"})
public class confirmOrderController extends HttpServlet {

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
        String[] cartQuantity = request.getParameterValues("txtCartQuan");
        try {
            HttpSession session = request.getSession();
            productCart shoppinCart = (productCart) session.getAttribute("shoppingCart");
            orderDAO dao = new orderDAO();
            productDAO productDAO = new productDAO();
            int countPro = 0;
            for (productDTO dto : shoppinCart.getCart().values()) {
                dto.setQuantity(Integer.parseInt(cartQuantity[countPro++]));
            }
            for (productDTO dto : shoppinCart.getCart().values()) {
                int vaultQuan = (productDAO.getProductByID(dto.getProductID())).getQuantity();
                String outOfSock = "Sorry, We Don't Have Enought Product For Your Order \n" + dto.getProductName() + " Only " + vaultQuan + " Left In Store";
                if (dto.getQuantity() > vaultQuan) {
                    request.setAttribute("StockProduct", dto.getProductID());
                    request.setAttribute("OutOfStock", outOfSock);
                    request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
                    return;
                }
            }
            String lastID = dao.getLastOrderIDByUser(shoppinCart.getCustomerName());
            String orderID = "";
            if (lastID == null) {
                orderID = "OD-" + shoppinCart.getCustomerName() + "-1";
            } else {
                String[] tmp = lastID.split("-");
                orderID = "OD-" + shoppinCart.getCustomerName() + "-" + (Integer.parseInt(tmp[2]) + 1);
            }
            boolean checkCreateOrder = dao.createOrder(orderID, shoppinCart.getCustomerName(), shoppinCart.getTotal(), new Date());
            if (checkCreateOrder) {
                int count = 0;
                for (productDTO dto : shoppinCart.getCart().values()) {
                    String OrderDetailID = orderID + "-" + count;
                    float priceOfProduct = dto.getQuantity() * dto.getPrice();
                    dao.createOrderDetail(OrderDetailID, orderID, dto.getProductID(), dto.getQuantity(), priceOfProduct);
                    count++;
                    productDTO product = productDAO.getProductByID(dto.getProductID());
                    int storage = product.getQuantity();
                    int buyQuantity = dto.getQuantity();
                    int newQuantity = storage - buyQuantity;
                    productDAO.updateQuantity(newQuantity, product.getProductID());
                }
            }

            session.setAttribute("shoppingCart", null);
            request.getRequestDispatcher("loadFoodController").forward(request, response);
        } catch (Exception e) {
            log("ERROR at confirmOrderController: " + e.getMessage());
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
