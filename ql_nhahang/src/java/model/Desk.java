
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import login.Connect;


public class Desk {
    private int Deskid;
    private String Deskname;

    public int getDeskid() {
        return Deskid;
    }

    public String getDeskname() {
        return Deskname;
    }

    public void setDeskid(int Deskid) {
        this.Deskid = Deskid;
    }

    public void setDeskname(String Deskname) {
        this.Deskname = Deskname;
    }

    public Desk() {
    }

    public Desk(int Deskid, String Deskname) {
        this.Deskid = Deskid;
        this.Deskname = Deskname;
    }
    public ArrayList<Desk> getlistDesk(){
        Connect con = new Connect();
        Connection  cons= con.getConnection();
        String sql = "select * from dm_desk";
        ArrayList<Desk> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Desk desk = new Desk();
//                desk.setDeskid(rs.getInt("id"));
                desk.setDeskname(rs.getString("name"));
                list.add(desk);
            }
            cons.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
            
    
}
