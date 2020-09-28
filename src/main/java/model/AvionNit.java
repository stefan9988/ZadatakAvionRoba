package model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AvionNit extends Thread{
    static Dao<Avion,Integer> avionDao;
    static Dao<Roba,Integer>robaDao;
    private Avion avion;
    static Kljuc kljuc=new Kljuc();
    private static boolean dozvoljeno_poletanje=true;

    public AvionNit(Avion avion) {

        this.avion = avion;

    }

    @Override
    public void run() {
        boolean dozvola=false;
        System.out.println("Pocinje provera za avion "+avion.getId());
        try {
            sleep((int)Math.random()*2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Avion "+avion.getId()+" je spreman za poletanje i ceka dozvolu za poletanje");
        do {
            synchronized (kljuc){
                if (dozvoljeno_poletanje){
                    dozvoljeno_poletanje=false;
                    dozvola=true;
                    System.out.println("Avion "+avion.getId()+" izlazi na pistu i polece");}
            }


                }



        while (!dozvola);

    try {
        sleep(2000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
        synchronized (kljuc){
            if (dozvola){
                System.out.println("Avion "+avion.getId()+" je poleteo");
                dozvoljeno_poletanje=true;
                dozvola=false;
            }
        }
    }

    public static void main(String[] args) {
        ConnectionSource connectionSource=null;
        try {
            connectionSource=new JdbcConnectionSource("jdbc:sqlite:avionRoba.db");

            avionDao= DaoManager.createDao(connectionSource,Avion.class);
            robaDao= DaoManager.createDao(connectionSource,Roba.class);

            List<Avion>avions=avionDao.queryForAll();
            for (Avion a:avions){
                Thread b=new AvionNit(a);
                b.start();
            }

    }
     catch (
    SQLException throwables) {
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
