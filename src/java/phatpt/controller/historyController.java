/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phatpt.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phatpt.dao.orderDAO;
import phatpt.dto.orderDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "historyController", urlPatterns = {"/historyController"})
public class historyController extends HttpServlet {

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
        String date = request.getParameter("boxMonth");
        String searchValue = request.getParameter("txtProName");
        String role = (String) session.getAttribute("role");
        String username = (String) session.getAttribute("welcomeName");
        String searchName = (String) request.getParameter("txtSearchingUser");
        try {
            if (role.equals("user")) {
                List<orderDTO> listOrder = new ArrayList<>();
                orderDAO dao = new orderDAO();
                listOrder = dao.getOrder(username, Integer.parseInt(date), role, searchValue);
                for (orderDTO order : listOrder) {
                    order.setListProduct(dao.getOderdetail(order.getOrderID()));
                }
                request.setAttribute("history", listOrder);
            } else if (role.equals("admin")) {
                List<orderDTO> listOrder = new ArrayList<>();
                orderDAO dao = new orderDAO();
                listOrder = dao.getOrder(searchName, Integer.parseInt(date), role, searchValue);
                for (orderDTO order : listOrder) {
                    order.setListProduct(dao.getOderdetail(order.getOrderID()));
                }
                request.setAttribute("history", listOrder);
            }
        } catch (Exception e) {
            log("ERROR AT historyController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("history.jsp").forward(request, response);
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
