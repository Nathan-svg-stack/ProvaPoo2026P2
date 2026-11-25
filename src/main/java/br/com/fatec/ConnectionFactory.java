package br.com.fatec;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String usuario = "root";
    private static final String senha = "1234";
    private static final String host = "localhost";
    private static final String porta = "3306";
    private static final String db = "db_gta";

    public static Connection obterConexao() {
        try {
            Connection c = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + porta + "/" + db,
                usuario,
                senha
            );
            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(obterConexao());
    }
}
