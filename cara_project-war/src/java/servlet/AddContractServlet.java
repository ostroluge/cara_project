/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import model.contract.ContractType;
import model.user.Insured;
import insurance.remote.ContractBeanRemote;
import insurance.remote.ContractTypeBeanRemote;
import insurance.remote.UserBeanRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tostrowski
 */
@WebServlet("/AddContractServlet")
public class AddContractServlet extends HttpServlet {

    @EJB
    UserBeanRemote mUserBean;
    
    @EJB
    ContractTypeBeanRemote mContractTypeBean;
    
    @EJB
    ContractBeanRemote mContractBean;

    public AddContractServlet() {
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
        List<Insured> insured = mUserBean.selectAllInsured();
        List<ContractType> contractTypes = mContractTypeBean.getAllContractType();
        
        request.setAttribute("users", insured);
        request.setAttribute("contractTypes", contractTypes);           
        
        RequestDispatcher rd = request.getRequestDispatcher("ContractForm.jsp");
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
        String loginInsured = request.getParameter("insured");
        String contractTypeId = request.getParameter("contractType");
        String subscriptionAmount = request.getParameter("subscriptionAmount");
        String design = request.getParameter("design");
        String registrationNumber = request.getParameter("registrationNumber");
        String nameMainDriver = request.getParameter("nameMainDriver");
        String maxAmount = request.getParameter("maxAmount");
        String address = request.getParameter("address");
        String capitalAmount = request.getParameter("capitalAmount");
        String minimumSubscriptionDuration = request.getParameter("minimumSubscriptionDuration");                                   

        maxAmount = checkField(maxAmount);
        capitalAmount = checkField(capitalAmount);
        minimumSubscriptionDuration = checkField(minimumSubscriptionDuration);
        
        mContractBean.addContract(Double.parseDouble(subscriptionAmount),
                Integer.parseInt(contractTypeId), loginInsured,
                address, Double.parseDouble(maxAmount),
                Double.parseDouble(capitalAmount), Double.parseDouble(minimumSubscriptionDuration),
                design, nameMainDriver, registrationNumber);
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ajout succès</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Le contrat a été ajouté avec succès !</h1>");
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

    private String checkField(String field) {
        if (field == null || field.equals("")) {
            field = "0";
        }
        return field;
    }
}
