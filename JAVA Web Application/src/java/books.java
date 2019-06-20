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
import java.util.Arrays;
import java.util.List;
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
public class books extends HttpServlet {

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
            out.println("Logged in, "+getServletContext().getAttribute("us_name"));
            out.println("<p class=\"signin button\"><input type=submit value=\"Logout\" onclick=window.location.href='register' style=width:10%;cursor:pointer;background:rgb(61,157,179);padding-left:3px;padding-right:3px;padding-top:4px;padding-bottom:4px;color:#fff;font-size:15px;border-radius:15px;></p>");
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<head><title>Books Selected</title></head>"); 
            
            out.println("<body style=margin-left:10%;margin-right:10%;font-family:'FranchiseRegular','Arial Narrow',Arial,sans-serif;><h1 style=color:rgb(74,179,198);>Books Selected</h1><br>");
            String[] book = request.getParameterValues("book");
            MyDb db=new MyDb();
            Connection con=db.getCon();
            Statement stmt=con.createStatement();
            int count=0;
            for(String s:book)
            {
                ResultSet rs=stmt.executeQuery("select * from books where (book_id='"+s+"')");
                count++;
                while(rs.next())
                {
                String bookid=rs.getString("book_id");
                String bookscount=rs.getString("books_count");
                String authors=rs.getString("authors");
                String year=rs.getString("year");
                String title=rs.getString("title");
                String genre=rs.getString("genre");
                String language=rs.getString("language");
                String rating=rs.getString("rating");  
                String image_url=rs.getString("image_url");
                out.println("<img src='"+image_url+"' style=margin-bottom:5%;><span style=font-weight:bold;position:absolute;margin-left:5%;font-size:20px;>"+title+"</span><span style=position:absolute;margin-top:3%;margin-left:5%;><b>BookID = </b>"+bookid+"</span><span style=position:absolute;margin-top:5%;margin-left:5%;><b>Books Available = </b>"+bookscount+"</span><span style=position:absolute;margin-top:7%;margin-left:5%;><b>Author(s) = </b>"+authors+"</span><span style=position:absolute;margin-top:3%;margin-left:25%;><b>Pubication Year = </b>"+year+"</span><span style=position:absolute;margin-top:9%;margin-left:5%;><b>Book Genre = </b>"+genre+"</span><span style=position:absolute;margin-top:5%;margin-left:25%;><b>Language = </b>"+language+"</span><span style=position:absolute;margin-top:9%;margin-left:25%;><b>Rating = </b>"+rating+"</span><br>");
                //out.println(bookid+" "+bookscount+" "+authors+" "+year+" "+title+" "+genre+" "+language+" "+rating+"<br>");
                
                
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(books.class.getName()).log(Level.SEVERE, null, ex);
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
