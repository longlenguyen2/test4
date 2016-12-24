
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import login.Connect;

public class Product {
    private int id;
    private int id_Categoly;
    private String name;
    private float price;
    private String image;

    public Product() {
    }
    public Product(int id, int id_Categoly, String name, float price, String image ) {
        this.id = id;
        this.id_Categoly =id_Categoly;
        this.name=name;
        this.price=price;
        this.image=image;
    }
    public int getProductID() {
        return id;
    }
    public void setProductID(int id) {
        this.id = id;
    }
     public int getProductID_Categoly() {
        return id_Categoly;
    }
    public void setProductID_Categoly(int id_Categoly) {
        this.id = id_Categoly;
    }
    public String getProductName() {
        return name;
    }
    public void setProductName(String name) {
        this.name = name;
    }
     public float getProductPrice() {
        return price;
    }
    public void setProductPrice(float price) {
        this.price = price;
    }     
     public String getProductImage() {
        return image;
    }
    public void setProductImage(String image) {
        this.image = image;
    }
    public ArrayList<Product> getListProduct(int Category) {
        Connect con = new Connect();
        Connection  cons= con.getConnection();
        String sql = "select * from dm_product where id_Category = '"+Category+"';";
        ArrayList<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setProductPrice(rs.getFloat("price"));
                product.setProductImage(rs.getString("image"));
                list.add(product);
            }
            cons.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    
    
}
