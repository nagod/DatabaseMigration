package main.data.daoImpl;

import main.bdo.PgUser;
import main.data.dao.DBConnectionDAO;
import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgDBConnectionDAOImpl implements DBConnectionDAO {

    private static PgDBConnectionDAOImpl instance;
    private Connection connection;
    private String url = DBConfig.pgDatabaseURL;

    // Constructor
    private PgDBConnectionDAOImpl(){}

    // Singleton
    public static PgDBConnectionDAOImpl getInstance(){
        if(instance == null){
            instance = new PgDBConnectionDAOImpl();
        }
        return instance;
    }

    @Override
    public Connection openConnection() {
        PgUser user= PgUser.getInstance();

        try{
            connection = DriverManager.getConnection(url, user.getUsername(), user.getPassword());
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() {
        try{
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
