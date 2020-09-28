package zadaci;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;

public class Zadatak3IzmenaVrednosti {
    public static void main(String[] args) {
        ConnectionSource connectionSource=null;
        try {
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            if (connectionSource!=null){
                try {
                    connectionSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
