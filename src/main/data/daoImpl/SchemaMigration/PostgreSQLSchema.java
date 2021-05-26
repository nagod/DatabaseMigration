package main.data.daoImpl.SchemaMigration;

import main.bdo.TableSchema.Column;
import main.bdo.TableSchema.ForeignKey;
import main.bdo.TableSchema.PrimaryKey;
import main.bdo.TableSchema.Table;
import main.data.dao.DBConnectionDAO;
import main.data.dao.GetSchemaDAO;
import main.Config.DBConfig;
import main.data.daoImpl.Login.DBConnectionHandler;

import java.sql.*;
import java.util.*;

/**
 * TASK : Get Postgres Schema and generate Oracle equivalent
 * <p>
 * TODO :
 * 1) Generate PrimaryKey class ( BDO )  ||  done
 * 2) Generate ForeignKey class ( BDO )  ||  done
 * 3) Die Tabellen mit allen Spalten und passendem Datentyp
 * - Table class
 * - Column class || done
 * 4) Create Interface
 * - List<Table> getAlTableInfo() || done
 * - List<PrimaryKey> getAllPrimaryKeys(List<Table> tableList) || done
 * - List<ForeignKey> getAllForeignKey(List<Table> tableList)  || done
 */
public class PostgreSQLSchema implements GetSchemaDAO {

    DBConnectionHandler connectionHandler;
    DBConnectionDAO pgConnection;
    Connection connection;
    public static Map<String, String> foundTypes = new HashMap<>();

    /*
     * Constuctor for setting up all connections
     */
    public PostgreSQLSchema() {
        connectionHandler = DBConnectionHandler.getInstance();
        pgConnection = connectionHandler.getConnection("postgres");
        connection = pgConnection.getConnection();
    }

    /**
     * @return postgresTablesList, List of all found tables in DB
     */
    @Override
    public List<Table> getAlTableInfo() {
        // type we are looking for
        String[] types = {"TABLE"};
        /*
         * List for all tables
         */
        List<Table> postgresTablesList = new ArrayList<>();
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            // ResultSet with metadata about table
            ResultSet resultSetTable = databaseMetaData.getTables(connection.getCatalog(),
                    DBConfig.pg_SCHEMA, null, types);
            /*
             *  resultSetTable is now iterating through all tables
             */
            while (resultSetTable.next()) {
                // generate Table for each iteration
                Table currentTable = new Table();

                currentTable.setTableName(resultSetTable.getString("TABLE_NAME"));
                currentTable.setSchemaName(DBConfig.pg_SCHEMA);
                // get metadata about columns of current table
                ResultSet resultSetColumns = databaseMetaData.getColumns(connection.getCatalog(), DBConfig.pg_SCHEMA, currentTable.getTableName(), null);

                while (resultSetColumns.next()) {
                    Column currentColumn = new Column();

                    currentColumn.setColumnName(resultSetColumns.getString("COLUMN_NAME"));
                    currentColumn.setColumnTypeName(resultSetColumns.getString("TYPE_NAME"));
                    currentColumn.setColumnSize(resultSetColumns.getString("COLUMN_SIZE"));
                    currentColumn.setDATA_TYPE(JDBCType.valueOf(Integer.parseInt(resultSetColumns.getString("DATA_TYPE"))).getName());
                    if (resultSetColumns.getString("NULLABLE").equals("1")) {
                        currentColumn.setNullable(true);
                    }
                    currentTable.addColumn(currentColumn);
                    foundTypes.put(currentColumn.getColumnTypeName(),currentColumn.getDATA_TYPE());
                }
                resultSetColumns.close();
                postgresTablesList.add(currentTable);
            }
            // close resultSetTable
            resultSetTable.close();
            return postgresTablesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param postgresTablesList , list of all Tables found in Postgres DB
     * @return postgresPrimaryKeyList, List with PrimaryKeys for all found postgresTables
     */
    @Override
    public List<PrimaryKey> getAllPrimaryKeys(List<Table> postgresTablesList) {
        try {
            // try to get Metadata
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            List<PrimaryKey> postgresPrimaryKeyList = new ArrayList<PrimaryKey>();
            // Loop trough all given tables
            // TODO try to write it as a lambda expression ! => not possible , cant modify resultSetPrimaryKey in lambda
            for (Table currentTable : postgresTablesList) {
                ResultSet resultSetPrimaryKey = databaseMetaData.getPrimaryKeys(connection.getCatalog(),
                        DBConfig.pg_SCHEMA, currentTable.getTableName());

                // iterate through PrimaryKeys
                while (resultSetPrimaryKey.next()) {
                    PrimaryKey currentPrimaryKey = new PrimaryKey();

                    currentPrimaryKey.setPkName(resultSetPrimaryKey.getString("PK_NAME"));
                    currentPrimaryKey.setColumnName(resultSetPrimaryKey.getString("COLUMN_NAME"));
                    currentPrimaryKey.setTableName(resultSetPrimaryKey.getString("TABLE_NAME"));

                    postgresPrimaryKeyList.add(currentPrimaryKey);
                }
                // closer ResultSet, current table has been processed
                resultSetPrimaryKey.close();
            }
            // all primaryKey are generated at this point
            return postgresPrimaryKeyList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param postgresTableList, List of all found tables
     * @return foreignKeyList  , List of foreignKeys from postgresTableList
     */
    @Override
    public List<ForeignKey> getAllForeignKey(List<Table> postgresTableList) {
        try {
            // get Data
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            List<ForeignKey> foreignKeyList = new ArrayList<>();

            // Loop trough all given tables
            for (Table currentTable : postgresTableList) {
                ResultSet resultSetForeignKey = databaseMetaData.getExportedKeys(connection.getCatalog(),
                        DBConfig.pg_SCHEMA, currentTable.getTableName());

                while (resultSetForeignKey.next()) {

                    // init temporary Key and set values give by databaseMetadata
                    ForeignKey currentForeignKey = new ForeignKey();

                    currentForeignKey.setPkTabelName(resultSetForeignKey.getString("PKTABLE_NAME"));
                    currentForeignKey.setPkColumnName(resultSetForeignKey.getString("PKCOLUMN_NAME"));
                    currentForeignKey.setPkName(resultSetForeignKey.getString("PK_NAME"));

                    currentForeignKey.setFkTablename(resultSetForeignKey.getString("FKTABLE_NAME"));
                    currentForeignKey.setFkName(resultSetForeignKey.getString("FK_NAME"));
                    currentForeignKey.setFkColumnName(resultSetForeignKey.getString("FKCOLUMN_NAME"));

                    // finally add Foreignkey to foreignKeyList
                    foreignKeyList.add(currentForeignKey);
                }
                // close resultSetForeignKey
                resultSetForeignKey.close();
            }
            return foreignKeyList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void printTables() {
        System.out.println("----------------Called printTables --------------- ");
        List<Table> table = this.getAlTableInfo();
        for (Table t : table) {
            System.out.println("\n");
            System.out.println("------------------" + t.getTableName() + "------------------");
            List<Column> columnList = t.getColumnList();
            int s = 1;
            for (Column c : columnList) {
                System.out.println("----------------COLUMN " + s + "---------------- ");
                System.out.println(c.toString());
                System.out.println("\n");
                s++;
            }
        }
    }

    public void printForeignKeys() {
        System.out.println("----------------Called printForeignKeys --------------- ");
        List<Table> table = this.getAlTableInfo();
        List<ForeignKey> fk = this.getAllForeignKey(table);
        for (ForeignKey f : fk) {
            System.out.println("----------------ForeignKey--------------- ");
            System.out.println(f.toString());
        }
    }

    public void printPrimaryKeys() {
        System.out.println("----------------Called printPrimaryKeys --------------- ");
        List<Table> table = this.getAlTableInfo();
        List<PrimaryKey> pk = this.getAllPrimaryKeys(table);

        for (PrimaryKey p : pk) {
            System.out.println("----------------PrimaryKey--------------- ");
            System.out.println(p.toString());
        }
    }

    public void printTypes() {
        for(Map.Entry<String,String> m:foundTypes.entrySet()){
            System.out.println(m.getKey()+" "+m.getValue());
        }
    }

}
