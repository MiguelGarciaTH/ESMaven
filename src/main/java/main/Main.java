package main;

import sql.mysql.MySQL;

public class Main {
    public static void main(String[] args) {
        MySQL mySQL = new MySQL();
        mySQL.connect("es_maven", "es_maven", "es_maven");

        System.out.println(mySQL.select("miguel"));
        mySQL.insert("userTeste", "passTest");

        System.out.println(mySQL.select("userTeste"));
        mySQL.disconnect();
    }
}
