/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import insurance.remote.ContractTypeBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Category;

/**
 *
 * @author tostrowski
 */
@WebServlet("/AddContractTypeServlet")
public class AddContractTypeServlet extends HttpServlet {

    @EJB
    ContractTypeBeanRemote mContractTypeBean;
    
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
        response.getWriter().append("Served at: ").append(request.getContextPath());
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
        String stringCategory = request.getParameter("category");
        String description = request.getParameter("description");
        String minimalAmount = request.getParameter("minimalAmount");
        
        if (stringCategory.equals("automobile")) {
            mContractTypeBean.addContractType(Category.AUTOMOBILE, description, Double.parseDouble(minimalAmount));
        } else if (stringCategory.equals("habitation")) {
            mContractTypeBean.addContractType(Category.HABITATION, description, Double.parseDouble(minimalAmount));
        } else if (stringCategory.equals("vie")) {
            mContractTypeBean.addContractType(Category.VIE, description, Double.parseDouble(minimalAmount));
        }
     
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ajout succès</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Le type de contrat a été ajouté avec succès !</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
