/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static java.lang.System.out;


public class viewOrderProduct extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); 
        Order ord = new Order();
        ArrayList<Order> order_list = new ArrayList<>();
        
        try {
            order_list = ord.viewOrderProduct(request.getParameter("in_tungay"),request.getParameter("in_denngay")) ; 
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonArray = gson.toJson(order_list);
            jsonArray = "{\"page\":1,\"total\":\"2\",\"records\":"
                    + order_list.size() + ",\"rows\":" + jsonArray + "}";            

            out.println(jsonArray);

        } catch (SQLException ex) {
            Logger.getLogger(viewOrderProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
