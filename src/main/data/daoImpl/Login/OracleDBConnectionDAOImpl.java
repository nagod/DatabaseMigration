package main.data.daoImpl.Login;

import main.Config.DBConfig;
import main.bdo.User.OracleUser;
import main.data.dao.DBConnectionDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class OracleDBConnectionDAOImpl implements DBConnectionDAO {

    private static OracleDBConnectionDAOImpl instance;
    private Connection connection;
    private String url;

    // Constructor
    private OracleDBConnectionDAOImpl() {
    }

    // Singleton
    public static OracleDBConnectionDAOImpl getInstance() {
        if (instance == null) {
            instance = new OracleDBConnectionDAOImpl();
        }
        return instance;
    }
    public void setUrl(String url){
        this.url = url;
    }

    @Override
    public Connection openConnection() throws SQLException {
        OracleUser user = OracleUser.getInstance();
        connection = DriverManager.getConnection(url, user.getUsername(), user.getPassword());
        return connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnectionString(String connectiontype) {
        switch(connectiontype){
            case "SSH":
                setUrl(DBConfig.oracle_Database_SSH_URL);
                break;
            case "VPN":
                setUrl(DBConfig.oracle_Database_VPN_URL);
                break;
        }
    }


    @Override
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
