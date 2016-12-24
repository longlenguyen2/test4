package login;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class login extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                          throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String userName = request.getParameter("txtUsername");
            String passWord = request.getParameter("txtPassword");
            if (userName != null && passWord != null) {
                Users user = new Users(userName, passWord);
                if(user.isValid()){
//                  request.setAttribute("username", userName);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/admin.jsp");
                    requestDispatcher.forward(request, response);
                }else{
                    out.println("Tên đăng nhập hoặc mật khẩu không đúng");
//                  response.sendRedirect("login.jsp");
                    out.println("<a href='login.jsp'>Thử lại</a>");
                }
            }
        }
    }

}
