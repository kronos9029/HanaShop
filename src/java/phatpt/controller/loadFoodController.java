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
import javax.servlet.http.HttpSession;
import phatpt.dao.categoryDAO;
import phatpt.dao.productDAO;
import phatpt.dto.categoryDTO;
import phatpt.dto.productDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "loadFoodController", urlPatterns = {"/loadFoodController"})
public class loadFoodController extends HttpServlet {

    private static final String ADMIN = "adminViewFood.jsp";
    private static final String GUEST = "customerFoodPage.jsp";
    private static final String USER = "customerFoodPage.jsp";

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
        String url = null;
        if (role == null) {
            url = GUEST;
        } else if (role.equals("admin")) {
            url = ADMIN;
        } else if (role.equals("user")) {
            url = USER;
        }

        try {
            int index;
            String indexString = request.getParameter("index");
            if (indexString == null) {
                indexString = "1";
            }
            index = Integer.parseInt(indexString);
            productDAO dao = new productDAO();
            categoryDAO cateDAO = new categoryDAO();
            int count = dao.countAll("C1");
            int pageSize = 20;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            List<productDTO> result = dao.getProductByCateID("C1", index, role);
            List<categoryDTO> listCate = cateDAO.getAllCategory();
            request.setAttribute("endPage", endPage);
            request.setAttribute("result", result);
            request.setAttribute("listCate", listCate);
            request.setAttribute("index", indexString);
        } catch (Exception e) {
            log("ERROR AT loadFoodController: " + e.getMessage());
        } finally {
             
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
