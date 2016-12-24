package login;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class Users {
    private String userName;
    private String passWord;

    public Users(String username, String password) {
        this.userName = username;
        this.passWord = password;
    }
    
    private String md5(String passwordToHash){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public boolean isValid() {           
        Connection conn = null;
        CallableStatement callableStatement = null;
        Boolean loginState = false;

        try {
            Connect db = new Connect();
            conn = db.getConnection(); 
            callableStatement = conn.prepareCall("{call CheckLogin(?, ?)}");
            callableStatement.setString(1, this.userName);
            callableStatement.setString(2, md5(this.passWord));
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                loginState = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return loginState;
    }

}

