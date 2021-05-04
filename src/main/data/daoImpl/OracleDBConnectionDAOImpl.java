package main.data.daoImpl;

import main.bdo.OracleUser;
import main.data.dao.DBConnectionDAO;
import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class OracleDBConnectionDAOImpl implements DBConnectionDAO {

    private static  OracleDBConnectionDAOImpl instance;
    private Connection connection;
    private String url = DBConfig.oracleDatabaseURL;

    // Constructor
    private OracleDBConnectionDAOImpl(){}

    // Singleton
    public static OracleDBConnectionDAOImpl getInstance(){
        if(instance == null){
            instance = new OracleDBConnectionDAOImpl();
        }
        return instance;
    }

    @Override
    public Connection openConnection() throws SQLException {
        // TODO implement connection
        OracleUser user = OracleUser.getInstance();
        try {
            Driver myDriver = new OracleDriver();
            DriverManager.registerDriver( myDriver );
            connection = DriverManager.getConnection(DBConfig.oracleDatabaseURL, user.getUsername(), user.getPassword());
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
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
