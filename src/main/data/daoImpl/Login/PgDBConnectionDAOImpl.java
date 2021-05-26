package main.data.daoImpl.Login;

import main.Config.DBConfig;
import main.bdo.User.PgUser;
import main.data.dao.DBConnectionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgDBConnectionDAOImpl implements DBConnectionDAO  {

    private static PgDBConnectionDAOImpl instance;
    private Connection connection;
    private String url;

    // Constructor
    private PgDBConnectionDAOImpl(){}

    // Singleton
    public static PgDBConnectionDAOImpl getInstance(){
        if(instance == null){
            instance = new PgDBConnectionDAOImpl();
        }
        return instance;
    }

    public void setUrl(String url){
        this.url = url;
    }

    @Override
    public Connection openConnection() throws SQLException{
        PgUser user= PgUser.getInstance();
        connection = DriverManager.getConnection(url, user.getUsername(), user.getPassword());
        return connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnectionString(String connectiontype) {
        switch (connectiontype){
            case "SSH":
                setUrl(DBConfig.pg_Database_SSH_URL);
                break;
            case "VPN":
                setUrl(DBConfig.pg_Database_VPN_URL);
                break;
        }
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
