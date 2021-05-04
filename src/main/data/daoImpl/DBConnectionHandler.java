package main.data.daoImpl;


import main.data.dao.DBConnectionDAO;

/*
* Factory Pattern
* */
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
