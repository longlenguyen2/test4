
<%@page import="model.Desk"%>
<%@page import="model.Item"%>
<%@page import="model.Cart"%>
<%@page import="model.Category"%>
<%@page import="model.Product"%>
<link href="css/main_product.css" rel="stylesheet" type="text/css"/>
<link href="css/menu.css" rel="stylesheet" type="text/css"/>
<link href="css/produce.css" rel="stylesheet" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>thanh hoa restaurant</title>                 
    </head>
    <body >          
        <%
            Cart cart = new Cart();
            Item item = new Item();
            Category ca = new Category();
            Product pro = new Product();
            cart =(Cart) session.getAttribute("cart"); 
            String Category = null ;
            if(request.getParameter("id_Category")!=null)
                Category = request.getParameter("id_Category");
            else{
                if(cart.countCart()==0)
                    Category="1";
                else
                    Category =String.valueOf(cart.getItem(cart.countCart()-1).getProduct().getProductID_Categoly()) ;
            }                 
        %>  
        <div id="main">
            <div id="head">
                <p> Phone: 088.6677.1508  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Address: 26A - Đại lộ Lê Lợi - TP.Thanh hóa</p>              
                <h1>THANH HOA RESTAURANT</h1>
                <p id = "linkDN"> <b><a style="color: black" href="login.jsp">Đăng nhập</a></b></p>
            </div>
            <div id="head-link">
                <div id="menu">
                     <ul>                        
                        <li><a href="product.jsp?id_Category=1">Đặt món</a></li> 
                        <li><a href="index.jsp">Hướng dẫn đặt món</a></li>
                    </ul>
                </div>               
            </div>                         
            <div id="left">
                <div id="menu_left">
                    <ul>
                        <% for(Category cate : ca.getListCategory()){ %>
                        <li> <a href="product.jsp?id_Category=<%= cate.getCategoryID()%>" > <% out.println(cate.getCategoryName()); %></a> </li> 
                        <% } %>
                    </ul>
                </div>                
            </div>
            <div id="content"> 
                <% for(Product prod : pro.getListProduct(Integer.parseInt(Category))) { %>
                <form method="post" action="CartServletnew">
                    <table id="tbl_content">
                        <tr>
                            <td id="image"> <img src="<%= prod.getProductImage()%>" ></td>                                                    
                        </tr> 
                        <tr>
                            <td id="price"> <% out.println(prod.getProductPrice()) ;%> &nbsp;VNĐ </td> 
                        </tr>
                        <tr>
                            <td id="name"> <% out.println(prod.getProductName()) ;%> </td>                                                     
                        </tr>                                                
                        <input type="hidden" name="id_in" value="<%= prod.getProductID()%>"> 
                        <input type="hidden" name="idCategory_in" value="<%= Category %>"> 
                        <input type="hidden" name="name_in" value="<%= prod.getProductName()%>">
                        <input type="hidden" name="price_in" value="<%= prod.getProductPrice()%>">
                        <input type="hidden" name="choice_in" value="insert">                        
                        <tr> 
                            <td> <input type="number" id="quantity" name="quantity" min="1" max="100" placeholder="số lượng" required></td>                           
                        </tr>
                        <tr>
                            <td> <input type="submit" id="select" value="Chọn món"> </td>                    
                        </tr>
                    </table> 
                </form>
                <% } %>
            </div>

            <div id="right">
                <h3 id="title">CÁC MÓN ĐÃ CHỌN</h3>
                <form method="post" action="InsertOrdernew">                 
                    <b> Chọn bàn </b>    
                    <select name="i_desk">
                    <% Desk des = new Desk();
                    for(Desk desk: des.getlistDesk()){%>
                    <option > <% out.println(desk.getDeskname()); %> </option>
                    <% } %>
                    </select>                    
                    <input type="submit" value="Lưu">
                    <table id ="tbl_right" border="0.5" cellpadding="0" cellspacing="0">
                        <thead>
                            <tr>
                                <td>Stt</td>
                                <td id="t_name">Tên món</td>
                                <td id="t_sl">Số lượng</td>
                                <td>Đơn giá</td>
                                <td>Thành tiền</td>
                                <td>Bỏ chọn</td>
                            </tr>
                        </thead>
                        <tbody>
                            <%                         
                                if(cart!=null){                                           
                                   for(int i=0;i<cart.countCart();i++){
                                    item = cart.getItem(i);                                    
                                    %>
                                    <tr>
                                        <td> <%= i+1 %></td>
                                        <td id="t_r_name"> <%= item.getProduct().getProductName() %> </td>                                                                                
                                        <td> <input id="t_r_quantity" type="number" min="1" value="<%= item.getQuantity()%>" onchange="change_quantity(<%= item.getProduct().getProductID()%>,value)">  </td>                                                                                
                                        <td> <%= item.getProduct().getProductPrice()  %> </td>
                                        <td> <%= item.getProduct().getProductPrice()*item.getQuantity() %> </td>                                    
                                        <td>  <a href="CartServletnew?choice_in=delete&id_in=<%= item.getProduct().getProductID()%>" > Xóa </a> </td>
                                    </tr>
                                    <%  } %>
                                    <tr>
                                        <td> <%= cart.countCart()%> (Món)</td>
                                        <td></td>
                                        <td></td>
                                        <td>Tổng cộng</td>
                                        <td> <%= cart.countMoney() %> </td>
                                        <td></td>
                                    </tr>
                                <%} %>                               
                        </tbody>                
                    </table>
                </form>
            </div>    
            <div id="footer">
                <p>design by: amateur coder</p>
            </div>
        </div>
    </body>
</html>

<script type="text/javascript">      
    function change_quantity(id,quantity){
        $.ajax({
            type: "get",
            url: "CartServletnew", 
            data: "choice_in=alter&id_in="+id+"&quantity="+quantity,
            dataType: "json"                  
        });             
}
</script>
