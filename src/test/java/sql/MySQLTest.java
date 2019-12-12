package sql;

import sql.mysql.MySQL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MySQLTest {


    @org.junit.jupiter.api.Test
    void select() {
        MySQL sql = new MySQL();
        sql.connect("es_maven", "es_maven", "es_maven");
        sql.insert("teste", "teste");
        String res = sql.select("teste");
        assertEquals("username: teste\tpassword: teste\n", res, "Return user teste and password test");
        sql.delete("teste");
    }
}
