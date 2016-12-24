
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Order"%>

<link href="../css/main_gt.css" rel="stylesheet" type="text/css"/>
<link href="../css/menu.css" rel="stylesheet" type="text/css"/>
<link href="../css/report.css" rel="stylesheet" type="text/css"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>thanh hoa restaurant</title>         
    </head>
    <body >          
        
        
        <% Order ord = new Order(); 
        Date today=new Date(System.currentTimeMillis());
        SimpleDateFormat timeFormat= new SimpleDateFormat("dd/MM/yyyy");
        String s=timeFormat.format(today.getTime());%>
   
        <div id="main">
            <div id="head">
                <p> Phone: 088.6677.1508  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Address: 26A - Đại lộ Lê Lợi - TP.Thanh hóa</p>              
                <h1>THANH HOA RESTAURANT</h1>
                <p id = "linkDN"> <b><a style="color: black" href="../login.jsp">Đăng xuất</a></b></p>
            </div>
            <div id="head-link">
                <div id="menu">
                     <ul>                         
                        <li><a href="view_order_product.jsp">Đơn đặt món</a></li> 
                        <li><a href="#">Thống kê bán hàng</a>
                            <ul>
                                <li><a href="report_product.jsp">Theo món</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>               
            </div>                         

            <div id="content" style="width: 990px">                    
                <form method="post" action="">
                    <table border="0.5" cellpadding="0" cellspacing="0">
                        <tr class="head">
                            <td id="t1">Thống kê món ăn đã bán</td>
                            <td class="null"></td>
                            <td class="null"></td>
                            <td class="null"></td>
                            <td class="null"></td>
                        </tr>                            
                        <tr>
                            <td id="c1">Từ ngày</td>
                            <td id="c2"> <input type="date" name="tu_ngay"></td>
                            <td id="c3">Đến ngày</td>
                            <td id="c4"> <input type="date" name="den_ngay"> </td>
                            <td id="c5"> <input type="submit" value="Xem số liệu"></td>
                        </tr>                                                   
                    </table>                                            
                </form> 
                
                <table border="0.5" cellpadding="1" cellspacing="1">
                    <tr class="head1">
                        <td >Tên món ăn</td>
                        <td >Số lượng</td>
                        <td >Tổng tiền</td>
                    </tr>
                    <% for(Order orde : ord.getOderProduct(request.getParameter("tu_ngay"),request.getParameter("den_ngay"))) { %>
                    <tr>
                        <td id="name"> <% out.println(orde.getName());%> </td> 
                        <td id="count"> <% out.println(orde.getCount_product());%> </td> 
                        <td id="money"> <% out.println(orde.getMoney());%> </td> 
                    </tr>  
                    <% } %>                      
                </table>                                                  
            </div>
            
            <div id="footer">
                <p>design by: amateur coder</p>
            </div>
        </div>
    </body>
</html>

