/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import context.DBContext;
import dao.MenuDAO;
import dao.ShareDAO;
import entity.Menu;
import entity.Share;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MenuControl extends HttpServlet {

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
        try {
            
            
             
            ShareDAO shareDAO = new ShareDAO();
            List<Share> listShare = shareDAO.getShare();
            request.setAttribute("share", listShare);
            
           
            String pageIndex = request.getParameter("pageIndex");
            if(pageIndex == null){
                pageIndex = "1";
            }
           
            int index = Integer.parseInt(pageIndex);
            
            
            MenuDAO menuDAO = new MenuDAO();
            int pageSize = 3;
            int total = menuDAO.getCountMenu();
            int maxPage = total / pageSize;
            if(total % pageSize != 0){
                maxPage++;
            }
            
            List<Menu> listMenu = menuDAO.getListMenu(index, pageSize);
            request.setAttribute("listMenu", listMenu);
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("pageIndex", index);
            request.setAttribute("activeMenu", "activeMenu");
            request.getRequestDispatcher("Menu.jsp").forward(request, response);
        } catch (Exception e) {
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
