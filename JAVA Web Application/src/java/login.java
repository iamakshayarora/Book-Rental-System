/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akshay
 */
public class login extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            getServletContext().setAttribute("us_name", username);
            MyDb db=new MyDb();
            Connection con=db.getCon();
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select username,password from user");
            int flag=0;
            while(rs.next())
            {
                String uname=rs.getString("username");
                String pass=rs.getString("password");
                if(uname.compareTo(username)==0)
                {
                    if(pass.compareTo(password)==0)
                    {
                        out.println("Logged in, "+username);
                        out.println("<p class=\"signin button\"><input type=submit value=\"Logout\" onclick=window.location.href='register' style=width:10%;cursor:pointer;background:rgb(61,157,179);padding-left:3px;padding-right:3px;padding-top:4px;padding-bottom:4px;color:#fff;font-size:15px;border-radius:15px;></p>");
                        
                        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/interface.html");
                        rd.include(request, response);
                        flag=1;
                        break;
                    }
                }
                
            }
            if(flag==0)
            {
                out.println("Wrong Username or Password");
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.html");
                rd.include(request, response);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
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
