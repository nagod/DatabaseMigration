package main.application.service;

import main.bdo.User;
import main.data.dao.DBConnectionDAO;
import main.data.daoImpl.DBConnectionHandler;

import java.sql.SQLException;

public class LoginService {

    private DBConnectionDAO OracleConnection;
    private DBConnectionDAO PgConnection;
    private DBConnectionHandler connectionHandler;
    private User pgUser;
    private User oracleUser;

    private static LoginService instance;

    private LoginService(){}

    public static LoginService getInstance(){
        if ( instance == null){
            instance = new LoginService();
        }
        return instance;
    }

    // login function

    public Boolean login(User user, String dbname){
        connectionHandler = DBConnectionHandler.getInstance();
        switch (dbname){
            case "postgres":
                pgUser = user.getUser();
                PgConnection = connectionHandler.getConnection(dbname);
                 try{
                     PgConnection.openConnection();
                     return PgConnection.getConnection().isValid(0) && PgConnection.getConnection() != null;
                 }catch(SQLException e){
                     e.printStackTrace();
                 }
            case "oracle":
                oracleUser = user.getUser();
                OracleConnection = connectionHandler.getConnection("oracle");
                try{
                    OracleConnection.openConnection();
                    return OracleConnection.getConnection().isValid(0) && OracleConnection.getConnection() != null;
                }catch (SQLException e){
                    e.printStackTrace();
                }
        }

        return null;
    }



}
