
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Order;

public class InsertOrdernew extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); 
        HttpSession session = request.getSession();
        Cart cart =(Cart) session.getAttribute("cart"); 
        if(cart == null) cart = new Cart();
        
        try {
            if(cart.countCart()==0){
                out.println("Vui lòng chọn món ăn trước");
                out.println("<a href='product.jsp'>Thử lại</a>");
            }else{
                processRequest(request, response);
                String desk = request.getParameter("i_desk");      
                Order order = new Order();

                for(int i =0; i< cart.countCart();i++){
                    String nameProduct = cart.getItem(i).getProduct().getProductName();
                    int quantity = cart.getItem(i).getQuantity();
                    order.insertOrderProduct(desk, nameProduct, quantity);
                }      

                response.sendRedirect("product.jsp");
            } 
        } catch (SQLException ex) {
                Logger.getLogger(InsertOrdernew.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
}
