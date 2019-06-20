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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akshay
 */
public class genre extends HttpServlet {

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
            out.println("Logged in, "+getServletContext().getAttribute("us_name"));
            out.println("<p class=\"signin button\"><input type=submit value=\"Logout\" onclick=window.location.href='register' style=width:10%;cursor:pointer;background:rgb(61,157,179);padding-left:3px;padding-right:3px;padding-top:4px;padding-bottom:4px;color:#fff;font-size:15px;border-radius:15px;></p>");
            String genre = request.getParameter("genre").toUpperCase();
            
            out.println("<head><title>"+genre+"</title></head>"); 
            
            out.println("<body style=margin-left:10%;margin-right:10%;font-family:'FranchiseRegular','Arial Narrow',Arial,sans-serif;><h1 style=color:rgb(74,179,198);>"+genre+"</h1><br>");
            out.println("<p style=font-weight:bold;font-size:25px;>&emsp;&emsp;&emsp;&emsp;Title&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;|&emsp;&emsp;&emsp;Authors&emsp;&emsp;&emsp;|&emsp;&emsp;&emsp;Year of Publication&emsp;|&emsp;Rating</p><br>");
            MyDb db=new MyDb();
            Connection con=db.getCon();
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from books where (genre='"+genre+"')");
            out.println("<div id=\"wrapper\"><form action=books method=post> ");
            while(rs.next())
            {   
                String bookid=rs.getString("book_id");
                String bookscount=rs.getString("books_count");
                String authors=rs.getString("authors");
                String year=rs.getString("year");
                String title=rs.getString("title");
                String rating=rs.getString("rating");
                
                out.println("<div style=background-color:rgb(138,206,218);padding-left:14px;padding-top:15px;margin-bottom:2px;border-radius:25px;><input type=checkbox name=book value='"+bookid+"' style=\"width:1.5em; height:1.5em; margin-bottom: 25px; margin-right: 10px;\">");
                out.println("<span><b>"+title+"</b>&emsp;|&emsp;"+authors+"&emsp;|&emsp;"+year+"&emsp;|&emsp;"+rating+"</span><br></div>");
           
            }
            out.println("<p class=\"signin button\"><input type=submit value=\"Select Books\" style=width:15%;position:absolute;left:0;margin-left:10%;margin-top:3%;margin-bottom:5%;cursor:pointer;background:rgb(61,157,179);padding-left:5px;padding-right:5px;padding-top:8px;padding-bottom:8px;color:#000;font-size:24px;border-radius:15px;></p></form></div>");
            
        } catch (SQLException ex) {
            Logger.getLogger(genre.class.getName()).log(Level.SEVERE, null, ex);
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
