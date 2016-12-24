<link href="css/login.css" rel="stylesheet" type="text/css"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>thanh hoa restaurant</title>         
    </head>

    <body>
        <form method="post" action="login">
            <b><a style="color: black" href="index.jsp">Trang chủ</a></b>
            <table>
                <tr>                    
                </tr>
                <tr>
                    <td style="color: white">Tên đăng nhập</td>
                    <td><input type="text" name="txtUsername"></td>
                </tr>
                <tr>
                    <td style="color: white">Mật khẩu</td>
                    <td><input type="password" name="txtPassword"></td>
                </tr>
                <tr>    
                    <td></td>
                    <td><input type="Submit" value="Đăng nhập"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
