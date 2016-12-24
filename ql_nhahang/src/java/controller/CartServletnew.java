
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.Product;

public class CartServletnew extends HttpServlet {

   
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");        
//        HttpSession session = request.getSession();
//        Cart cart =(Cart) session.getAttribute("cart");        
//        String choice = request.getParameter("choice_in");
//        
//        try (PrintWriter out = response.getWriter()) {
//            if(cart == null) cart = new Cart();               
//            if ("insert".equals(choice)){
//                int productId = Integer.parseInt(request.getParameter("id_in")); 
//                int categoryId = Integer.parseInt(request.getParameter("idCategory_in"));
//                String productName = request.getParameter("name_in");
//                float productPrice = Float.parseFloat(request.getParameter("price_in"));
//                int productQuantity = Integer.parseInt(request.getParameter("quantity"));            
//
//                Product pro = new Product(productId, categoryId, productName.toLowerCase(), productPrice, "");
//                Item item= new Item(pro,productQuantity);
//                cart.insertToCart(item);
//
//                session.setAttribute("cart", cart);   
//            }else{
//                int productId = Integer.parseInt(request.getParameter("id_in"));         
//
//                Product pro = new Product(productId, 1, "", 1, "");
//                Item item= new Item(pro,1);
//                cart.removeToCart(item);
//
//                session.setAttribute("cart", cart);
//            }
//            response.sendRedirect("product.jsp");
//        }
//    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");        
        HttpSession session = request.getSession();
        Cart cart =(Cart) session.getAttribute("cart");        
        String choice = request.getParameter("choice_in");
        
        try (PrintWriter out = response.getWriter()) {
            if(cart == null) cart = new Cart();               
            if ("insert".equals(choice)){
                int productId = Integer.parseInt(request.getParameter("id_in")); 
                int categoryId = Integer.parseInt(request.getParameter("idCategory_in"));
                String productName = request.getParameter("name_in");
                float productPrice = Float.parseFloat(request.getParameter("price_in"));
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));            

                Product pro = new Product(productId, categoryId, productName.toLowerCase(), productPrice, "");
                Item item= new Item(pro,productQuantity);
                cart.insertToCart(item);

                session.setAttribute("cart", cart);   
            }else if("delete".equals(choice)){
                int productId = Integer.parseInt(request.getParameter("id_in"));         
                for(int i =0; i< cart.countCart();i++){
                     if (cart.getItem(i).getProduct().getProductID() == productId) {
                        cart.removeToCart(cart.getItem(i));
                        break;
                     }                        
                }
                session.setAttribute("cart", cart);
            }else{
                int productId = Integer.parseInt(request.getParameter("id_in")); 
                int quantityPro = Integer.parseInt(request.getParameter("quantity"));
                
                cart.alterToCart(productId, quantityPro);
                
                session.setAttribute("cart", cart);                
            }
            response.sendRedirect("product.jsp");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");        
        HttpSession session = request.getSession();
        Cart cart =(Cart) session.getAttribute("cart");        
        String choice = request.getParameter("choice_in");
        
        try (PrintWriter out = response.getWriter()) {
            if(cart == null) cart = new Cart();               
            if ("insert".equals(choice)){
                int productId = Integer.parseInt(request.getParameter("id_in")); 
                int categoryId = Integer.parseInt(request.getParameter("idCategory_in"));
                String productName = request.getParameter("name_in");
                float productPrice = Float.parseFloat(request.getParameter("price_in"));
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));            

                Product pro = new Product(productId, categoryId, productName.toLowerCase(), productPrice, "");
                Item item= new Item(pro,productQuantity);
                cart.insertToCart(item);

                session.setAttribute("cart", cart);   
            }else if("delete".equals(choice)){
                int productId = Integer.parseInt(request.getParameter("id_in"));         
                for(int i =0; i< cart.countCart();i++){
                     if (cart.getItem(i).getProduct().getProductID() == productId) {
                        cart.removeToCart(cart.getItem(i));
                        break;
                     }                        
                }
                
                session.setAttribute("cart", cart);
            }else{
                int productId = Integer.parseInt(request.getParameter("id_in")); 
                int quantityPro = Integer.parseInt(request.getParameter("quantity"));
                
                cart.alterToCart(productId, quantityPro);
                
                session.setAttribute("cart", cart);
                
            }
            response.sendRedirect("product.jsp");
        }
    }
}
