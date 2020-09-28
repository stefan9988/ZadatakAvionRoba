package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Zadatak2DodavanjeVrednosti {
    static Dao<Avion,Integer>avionDao;
    static Dao<Roba,Integer>robaDao;
    public static void main(String[] args) {
        ConnectionSource connectionSource=null;
        try {
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao= DaoManager.createDao(connectionSource,Avion.class);
            robaDao= DaoManager.createDao(connectionSource,Roba.class);



            Avion a1=new Avion("avion1",34);
            Avion a2=new Avion("avion2",21);
            avionDao.create(a1);
            avionDao.create(a2);

            Roba r1=new Roba("patike","duboke patike",1);
            Roba r2=new Roba("kosulja","na duge rukave",0.4);
            Roba r3=new Roba("voda","voda za pice",1.4);
            Roba r4=new Roba("ploce","drvene ploce",3.4);
            Roba r5=new Roba("stolica","plasticna stolica",2.4);

            r1.setAvion(a1);
            r2.setAvion(a1);
            r3.setAvion(a1);
            r4.setAvion(a2);
            r5.setAvion(a2);

            robaDao.create(r1);
            robaDao.create(r2);
            robaDao.create(r3);
            robaDao.create(r4);
            robaDao.create(r5);

            List<Avion>avions=avionDao.queryForAll();
            for (Avion avion:avions){
                System.out.println(avion);
            }
            List<Roba>robas=robaDao.queryForAll();
            for (Roba roba:robas){
                System.out.println(roba);
            }




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
