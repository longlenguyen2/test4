
package model;

import com.sun.webkit.dom.NodeImpl;
import static java.lang.System.out;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import login.Connect;

public class Order {
    private String name_desk;
    private int id_product;
    private String name;
    private int quantity;
    private long count_product;
    private long money;

    public Order() {
    }

    public Order(String name_desk, int id_product, String name, int quantity) {
        this.name_desk = name_desk;
        this.id_product = id_product;
        this.name = name;
        this.quantity = quantity;
    }

    public String getName_desk() {
        return name_desk;
    }

    public int getId_product() {
        return id_product;
    }

    public int getQuantity() {
        return quantity;
    }
    public long getCount_product() {
        return count_product;
    }
    public long getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }
    
    public void setName_desk(String name_desk) {
        this.name_desk = name_desk;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount_product(long count_product) {
        this.count_product = count_product;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setMoney(long money) {
        this.money = money;
    }
    
    //thống kê theo món
    public ArrayList<Order> getOderProduct(String tu_ngay, String den_ngay) throws SQLException {
        Connect con = new Connect();
        Connection  cons= con.getConnection();
        CallableStatement callableStatement = null;
        callableStatement = cons.prepareCall("{call getOrderProduct('"+tu_ngay+"','"+den_ngay+"')}");
        ArrayList<Order> list = new ArrayList<>();
        try {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setName(rs.getString("name_product"));
                order.setCount_product(rs.getLong("count_product"));
                order.setMoney(rs.getLong("money"));
                list.add(order);
            }
            cons.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    // xem đơn đặt món
    public ArrayList<Order> viewOrderProduct(String tu_ngay, String den_ngay) throws SQLException {
        Connect con = new Connect();
        Connection  cons= con.getConnection();
        CallableStatement callableStatement = null;
        callableStatement = cons.prepareCall("{call viewOrderProduct('"+tu_ngay+"','"+den_ngay+"')}");
        ArrayList<Order> list = new ArrayList<>();
        try {
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setName_desk(rs.getString("name_desk"));
                order.setName(rs.getString("name_product"));
                order.setQuantity(rs.getInt("quantity"));
                list.add(order);
            }
            cons.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    //thêm đơn đặt món
    public void insertOrderProduct(String nameDesk, String nameProduct, int quantity) throws SQLException {
        Connect con = new Connect();
        Connection  cons= con.getConnection();
        CallableStatement callableStatement = null;
        callableStatement = cons.prepareCall("{call insertOrderProduct('"+ nameDesk + "','" + nameProduct + "'," + quantity+")}");
        ResultSet rs = callableStatement.executeQuery();
        cons.close();        
    }
            
}
