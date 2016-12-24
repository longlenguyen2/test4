
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Order"%>

<link href="../css/main_gt.css" rel="stylesheet" type="text/css"/>
<link href="../css/menu.css" rel="stylesheet" type="text/css"/>
<link href="../css/report.css" rel="stylesheet" type="text/css"/>

<link href="../js/css/ui.jqgrid.css" rel="stylesheet" type="text/css"/>


<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery-3.1.1.min.js" type="text/javascript"></script>
<script src="../js/js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="../js/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script>



<a href="../js/jqGrid.jquery.json"></a>
<script src="../js/js/JsonXml.js" type="text/javascript"></script>
<a href="../js/bower.json"></a>


<script src="../js/js/grid.custom.js" type="text/javascript"></script>
<script src="../js/js/grid.jqueryui.js" type="text/javascript"></script>





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
                <table border="0.5" cellpadding="0" cellspacing="0">
                    <tr class="head">
                        <td id="t1">Xem đơn đặt món</td>
                        <td class="null"></td>
                        <td class="null"></td>
                        <td class="null"></td>
                        <td class="null"></td>
                    </tr>                            
                    <tr>
                        <td id="c1">Từ ngày</td>
                        <td id="c2"> <input type="date" name="tu_ngay" id="tu_ngay"></td>
                        <td id="c3">Đến ngày</td>
                        <td id="c4"> <input type="date" name="den_ngay" id="den_ngay"> </td>
                        <td id="c5"> <input type="button" value="Xem" onclick="viewOrderProduct()"></td>
                    </tr>                                                   
                </table>                                            
           
                <table id="list"><tr><td></td></tr></table> 
                <div id="pager"></div> 
                
            </div>
            <div id="footer">
                <p>design by: amateur coder</p>
            </div>
        </div>
    </body>
</html>

<script type="text/javascript">
    function viewOrderProduct() { 
        var tungay = $("#tu_ngay").val();
        var denngay = $("#den_ngay").val();
        $("#list").jqGrid({
            type: "get",
            url: "viewOrderProduct", 
            data: "in_tungay="+tungay+"&in_denngay="+denngay,
            dataType: "json",
            colNames: ["Tên bàn", "Tên món", "Số lượng"],
            colModel: [
                {name:"name_desk", index :"name_desk",width: 300, align: "left", sortable: true },
                {name:"name",index :"name", width: 300, align: "left", sortable: true },
                {name:"quantity",index :"quantity", width: 150,align: "right", sortable: true }
            ],
            pager: "#pager",
            rowNum: 10,
            rowList: [10, 20, 30],
            viewrecords: true,
            jsonReader: {
                        repeatitems : false
                },
            caption: "Danh sách các món đã đặt"
            
        }); 
        
    }
</script>

