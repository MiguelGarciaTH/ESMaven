package sql;

import sql.mysql.MySQL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySQLTest {


    @org.junit.jupiter.api.Test
    void select() {
        SQL sql = new MySQL();
        sql.connectDB("es_maven", "es_maven", "es_maven");
        sql.insertUser("teste", "teste");
        String res = sql.selectUser("teste");
        assertEquals("username: teste\tpassword: teste\n", res, "Return user teste and password test");
        sql.deleteUser("teste");
    }
}
