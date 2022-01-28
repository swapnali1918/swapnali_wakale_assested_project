package com.ecommerce;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecommerce.DBConnection;

/**
* Servlet implementation class ProductDetails
*/
@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
        private static final long serialVersionUID = 1L;
       
    /**
* @see HttpServlet#HttpServlet()
*/
    public ProductDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                // TODO Auto-generated method stub
                
                try {
                         PrintWriter out = response.getWriter();
                         out.println("<html><body>");

                         InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
                         Properties props = new Properties();
                         props.load(in);
                         
                         DBConnection conn = new DBConnection(props.getProperty("url"), props.getProperty("userid"), props.getProperty("password"));
                         Statement stmt = conn.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                         stmt.executeUpdate("insert into eproduct (name, price, date_added) values ('HP', 17800.00)");
                         ResultSet rst = stmt.executeQuery("select * from eproduct");
                         
                         while (rst.next()) {
                                 out.println(rst.getInt("ID") + ", " + rst.getString("name") + "<Br>");
                         }
                         
                         stmt.close();
                         
                         
                         
                         out.println("</body></html>");
                         conn.closeConnection();
                         
                 } catch (ClassNotFoundException e) {
                         e.printStackTrace();
                 } catch (SQLException e) {
                         e.printStackTrace();
                 }
         }
        
}
