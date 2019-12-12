package sql.mysql;

import java.sql.*;

public class MySQL {
    private Connection con;
    public MySQL(){

    }

    public boolean connect(String databaseName, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, username, password);
            return !con.isClosed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean disconnect(){
        try {
            con.close();
            return con.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public String select(String username){
        StringBuilder builder = new StringBuilder();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select * from user_t where username=?");
            stmt.setString(1,username);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                builder.append("username: "+rs.getString(1) +"\tpassword: "  + rs.getString(2)+"\n");
            }
            return builder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "empty";
   }

   public void delete(String username){
       PreparedStatement stmt = null;
       try {
           stmt = con.prepareStatement(" DELETE FROM user_t WHERE username like ?;");
           stmt.setString(1,username);
           stmt.execute();
       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

    public void insert(String username, String password){
       PreparedStatement stmt = null;
       try {
           stmt = con.prepareStatement(" insert into user_t (username, password) values (?, ?)");
           stmt.setString(1,username);
           stmt.setString(2,password);
           stmt.execute();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
}
