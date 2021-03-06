/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import insurance.remote.ContractBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.contract.Automobile;
import model.contract.Contract;
import model.contract.Habitation;
import model.contract.Life;

/**
 *
 * @author tostrowski
 */
@WebServlet("/InsuredContractsServlet")
public class InsuredContractsServlet extends HttpServlet {

    @EJB
    ContractBeanRemote mContractBean;
    
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
        String userLogin = null;
        
        String insuredLogin = request.getParameter("insuredLogin");
        if (insuredLogin != null) {
            userLogin = insuredLogin;
        } else {
            userLogin = request.getRemoteUser();
        }
        
        boolean isUserInsured = request.isUserInRole("Insured");
        request.setAttribute("isUserInsured", isUserInsured);
        
        List<Automobile> automobiles = mContractBean.getAutomobileContractsByUser(userLogin);
        List<Habitation> habitations = mContractBean.getHabitationContractsByUser(userLogin);
        List<Life> lifes = mContractBean.getLifeContractsByUser(userLogin);
        
        request.setAttribute("automobiles", automobiles);
        request.setAttribute("habitations", habitations);
        request.setAttribute("lifes", lifes);
        
        RequestDispatcher rd = request.getRequestDispatcher("InsuredContractList.jsp");
        rd.forward(request, response);
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
