package zadaci;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.support.ConnectionSource;
import model.Avion;
import model.Roba;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Zadatak3IzmenaVrednosti {
    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer>robaDao;
    public static void main(String[] args) {
        ConnectionSource connectionSource=null;
        try {
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");
            avionDao= DaoManager.createDao(connectionSource,Avion.class);
            robaDao= DaoManager.createDao(connectionSource,Roba.class);

            List<Roba>robas=robaDao.queryForAll();
            for (Roba roba:robas){
                System.out.println(roba);
            }

//            PreparedQuery<Roba>robaPreparedQuery=robaDao.queryBuilder().where()
//                    .like(Roba.POLJE_OPIS,"plasticna stolica").prepare();
//            robas=robaDao.query(robaPreparedQuery);
//            robas.get(0).setOpis("drvena stolica");
//            robaDao.update(robas.get(0));

            List<Roba>r=robaDao.queryForEq(Roba.POLJE_OPIS,"plasticna stolica");
            Roba ru=r.get(0);
            ru.setOpis("drvena stolica");
            robaDao.update(ru);


            robas=robaDao.queryForAll();
            for (Roba roba:robas){
                System.out.println(roba);}




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
