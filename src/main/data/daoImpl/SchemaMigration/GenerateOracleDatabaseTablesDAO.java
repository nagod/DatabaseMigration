package main.data.daoImpl.SchemaMigration;

import main.Config.DBConfig;
import main.bdo.TableSchema.ForeignKey;
import main.bdo.TableSchema.PrimaryKey;
import main.bdo.TableSchema.Table;
import main.bdo.User.OracleUser;
import main.data.dao.CreateDatabaseTablesDAO;
import main.data.dao.DBConnectionDAO;
import main.data.daoImpl.Login.DBConnectionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateOracleDatabaseTablesDAO implements CreateDatabaseTablesDAO {

    DBConnectionHandler connectionHandler;
    DBConnectionDAO oracle;
    Connection connection;

    /**
     *Setup connections during init step
     */
    public GenerateOracleDatabaseTablesDAO(){
        connectionHandler = DBConnectionHandler.getInstance();
        oracle = connectionHandler.getConnection("oracle");
        connection = oracle.getConnection();
    }
    public void makeTable(){
        deleteTables();
    }



    @Override
    public List<Table> createTable(List<Table> tableList) {

        List<Table> oracleTableList = new ArrayList<>();
        //               CREATE TABLE table_name(
        //                  VALUE  PROPERTY
        //                  );


        //String query2 = "create table test3 (id INTEGER not NULL )  ";
        try{

            String name =tableList.get(1).getTableName().toUpperCase();
            String cName =tableList.get(1).getColumnList().get(1).getColumnName();
            String type =tableList.get(1).getColumnList().get(1).getColumnTypeName();

            String query  = "CREATE TABLE"+ name + "(";
            query += cName + " NUMBER NOT NULL )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeQuery();
            System.out.println("EXECUTED");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    /**
     *
     *
     * @param tableList, tableList from other DB, which is going to be transferred
     * @return existingTableList, list of tables with tables already exist in the target database
     */
    @Override
    public List<Table> getExistingTables(List<Table> tableList) {

        try {
            String [] types ={"TABLE"};
            List<Table> existingTableList = new ArrayList<>();

            DatabaseMetaData databaseMetaData = connection.getMetaData();
            // check if Oracle schema is empty or not equals logged in username => set to current Username
            if (DBConfig.oracle_SCHEMA.isEmpty() || !DBConfig.oracle_SCHEMA.equalsIgnoreCase(OracleUser.getInstance().getUsername())) {
                DBConfig.oracle_SCHEMA = OracleUser.getInstance().getUsername().toUpperCase();
            }
            // get all Tables, if existing, from Oracle Connection
            ResultSet resultSetTable = databaseMetaData.getTables(connection.getCatalog(), DBConfig.oracle_SCHEMA, DBConfig.oracle_SCHEMA, types);
            return existingTableList;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PrimaryKey> embedPrimaryKeys(List<PrimaryKey> primaryKeyList) {
        return null;
    }

    @Override
    public List<ForeignKey> embedForeignKeys(List<ForeignKey> foreignKeyList) {
        return null;
    }

    @Override
    public void deleteTables(List<Table> tableList) {

    }

    @Override
    public void deletePrimaryKeys(List<PrimaryKey> primaryKeyList) {

    }

    @Override
    public void deleteForeignKeys(List<ForeignKey> foreignKeyList) {

    }


    public void deleteTables(){
        String [] types ={"TABLE"};
        try{
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            if(DBConfig.oracle_SCHEMA.isEmpty()){
                DBConfig.oracle_SCHEMA = OracleUser.getInstance().getUsername().toUpperCase();
            }
            System.out.println("DBConfig.oracle_SCHEMA :" +DBConfig.oracle_SCHEMA);
            ResultSet resultSetTable = databaseMetaData.getTables(connection.getCatalog(), DBConfig.oracle_SCHEMA,null,types);
            while(resultSetTable.next()){
                System.out.println("GOING TO DELETE TABLE :" + resultSetTable.getString("TABLE_NAME"));
                String query = "DROP TABLE "+ resultSetTable.getString("TABLE_NAME");
                System.out.println(query);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeQuery();
                System.out.println("Delete was successful");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
