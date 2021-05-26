package main.application.service;

import main.data.dao.DBConnectionDAO;
import main.data.daoImpl.Login.DBConnectionHandler;
import java.sql.SQLException;

/**
 * Loginservice
 *
 * handles DatabseLogin
 */


public class LoginService {

    private DBConnectionDAO OracleConnection;
    private DBConnectionDAO PgConnection;
    private DBConnectionHandler connectionHandler;

    /**
     * Singleton Pattern
     */
    private static LoginService instance;

    private LoginService(){}

    public static LoginService getInstance(){
        if ( instance == null){
            instance = new LoginService();
        }
        return instance;
    }

    /**
     *
     * @param dbname
     * @param url : connection String  [VPN or SSH , look at DBConfig]
     * @return true if connection was successful and is valid
     */
    public Boolean login(String dbname, String url){
        connectionHandler = DBConnectionHandler.getInstance();
        switch (dbname){
            case "postgres":
                PgConnection = connectionHandler.getConnection(dbname);
                PgConnection.setConnectionString(url);
                 try{
                     PgConnection.openConnection();
                     return PgConnection.getConnection().isValid(0) && PgConnection.getConnection() != null;
                 }catch(SQLException e){
                     e.printStackTrace();
                     return false;
                 }
            case "oracle":
                OracleConnection = connectionHandler.getConnection("oracle");
                OracleConnection.setConnectionString(url);
                try{
                    OracleConnection.openConnection();
                    return OracleConnection.getConnection().isValid(0) && OracleConnection.getConnection() != null;
                }catch (SQLException e){
                    e.printStackTrace();
                    return false;
                }
        }
        return null;
    }



}
