/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import insurance.remote.RequestBeanRemote;
import insurance.remote.UserBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.contract.Automobile;
import model.contract.Habitation;
import model.contract.Life;
import model.contract.Request;
import model.user.Insured;

/**
 *
 * @author tostrowski
 */
@WebServlet(name = "UnderwriterPendingRequestsServlet", urlPatterns = {"/UnderwriterPendingRequestsServlet"})
public class UnderwriterPendingRequestsServlet extends HttpServlet {

    @EJB
    UserBeanRemote mUserBean;
    
    @EJB
    RequestBeanRemote mRequestBean;
    
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
        String remoteUnderwriter = request.getRemoteUser();
        List<Insured> insured = mUserBean.selectInsuredByUnderwriter(remoteUnderwriter);
        
        List<Automobile> automobiles = new ArrayList<>();
        List<Habitation> habitations = new ArrayList<>();
        List<Life> lifes = new ArrayList<>();
        List<Request> createRequests = new ArrayList<>();
        
        for (Insured i : insured) {
            List<Request> stopRequests = mRequestBean.getRequestsByUser("stop", i.getLogin());
            for (Request r : stopRequests) {
                if (r.getContract() instanceof Automobile) {
                    automobiles.add((Automobile)r.getContract());
                } else if (r.getContract() instanceof Habitation) {
                    habitations.add((Habitation)r.getContract());
                } else if (r.getContract() instanceof Life) {
                    lifes.add((Life) r.getContract());
                }
            }
        
            List<Request> crRequests = mRequestBean.getRequestsByUser("create", i.getLogin());
            createRequests.addAll(crRequests);
        }
        
        request.setAttribute("automobiles", automobiles);
        request.setAttribute("habitations", habitations);
        request.setAttribute("lifes", lifes);
        request.setAttribute("createRequests", createRequests);
        
        RequestDispatcher rd = request.getRequestDispatcher("UnderwriterPendingRequests.jsp");
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
