package main;

import sql.SQL;
import sql.mysql.MySQL;

public class Main {
    public static void main(String[] args) {
        SQL mySQL = new MySQL();
        mySQL.connectDB("es_maven", "es_maven", "es_maven");

        System.out.println(mySQL.selectUser("miguel"));
        mySQL.insertUser("userTeste", "passTest");

        System.out.println(mySQL.selectUser("userTeste"));
        mySQL.disconnectDB();
    }
}
