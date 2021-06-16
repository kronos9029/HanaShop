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
@WebServlet(name = "searchController", urlPatterns = {"/searchController"})
public class searchController extends HttpServlet {

    private static final String ADMIN = "searchResult.jsp";
    private static final String GUEST = "customerSearchResult.jsp";
    private static final String USER = "customerSearchResult.jsp";

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
            String page = request.getParameter("page");
            String search = request.getParameter("txtSearchValue");
            String withCate = request.getParameter("searchCateID");
            String indexString = request.getParameter("index");
            String minString = request.getParameter("txtMinPrice");
            String maxString = request.getParameter("txtMaxPrice");
            int index = Integer.parseInt(indexString);
            productDAO dao = new productDAO();
            categoryDAO cateDAO = new categoryDAO();
            List<productDTO> listSearch = null;
            int count;
            if (!minString.isEmpty() && !maxString.isEmpty()) {
                float minPrice = Float.parseFloat(minString);
                float maxPrice = Float.parseFloat(maxString);
                count = dao.countWithPrice(search, minPrice, maxPrice);
                listSearch = dao.searchInRange(search, index, minPrice, maxPrice);
            } else {
                count = dao.count(search, withCate);
                listSearch = dao.searchWithName(search, withCate, index);
            }
            int pageSize = 10;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }

            List<categoryDTO> listCate = cateDAO.getAllCategory();
            request.setAttribute("result", listSearch);
            request.setAttribute("end", endPage);
            request.setAttribute("txtSearchValue", search);
            request.setAttribute("searchCateID", withCate);
            request.setAttribute("listCate", listCate);
            request.setAttribute("txtMinPrice", minString);
            request.setAttribute("txtMaxPrice", maxString);
            request.setAttribute("index", indexString);
            request.setAttribute("page", page);
        } catch (Exception e) {
            log("ERROR AT searchController: " + e.getMessage());
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
