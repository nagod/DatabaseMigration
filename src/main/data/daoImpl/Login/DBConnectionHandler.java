package main.data.daoImpl.Login;


import main.data.dao.DBConnectionDAO;

/**
 * Database Factory Pattern
 * hanldes alls DB´s
 */
public class DBConnectionHandler {

    private OracleDBConnectionDAOImpl oracleConnection;
    private PgDBConnectionDAOImpl pgConnection;
    private static DBConnectionHandler instance;

    // Singleton
    private DBConnectionHandler(){}

    public static DBConnectionHandler getInstance(){
        if(instance == null){
            instance = new DBConnectionHandler();
        }
        return instance;
    }

    /**
     *
     * @param databse
     * @return ConnectionObj
     */
    public DBConnectionDAO getConnection(String databse){
        switch(databse){
            case "postgres":
               if(pgConnection == null){
                   pgConnection = PgDBConnectionDAOImpl.getInstance();
               }
               return pgConnection;
            case "oracle":
                if (oracleConnection == null){
                    oracleConnection = OracleDBConnectionDAOImpl.getInstance();
                }
                return  oracleConnection;
        }
        return null;
    }


}
