package sql;

public interface SQL {

    public boolean connectDB(String db, String db_user, String db_password);

    public boolean disconnectDB();

    public String selectUser(String username);
    public void insertUser(String username, String password);
    public void deleteUser(String username);


}
