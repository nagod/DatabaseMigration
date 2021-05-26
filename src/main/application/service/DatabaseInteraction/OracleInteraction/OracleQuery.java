package main.application.service.DatabaseInteraction.OracleInteraction;

import main.bdo.TableSchema.Column;
import main.data.dao.DBConnectionDAO;
import main.data.daoImpl.Login.DBConnectionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OracleQuery {
    public static Map<String, String> generetedSQLArray = new HashMap<>();

    public static String createTable(String tableName, List<Column> columnList){
        String query = "CREATE TABLE "+ tableName+" (";
        // CREATE TABLE tablename ( columnName columnType(columnSize) [Nullable]  || VARRAY(n) of <element_type>
        //                                                                           n is the number of elements
        //                                                                           (maximum) in the varray,
        columnList.forEach(column -> {

            String columnName = column.getColumnName();  // NAME
            String columnType = column.getColumnTypeName();// int4 ,numeric,bool, varchar,  _int4,_varchar
            String columnSize = column.getColumnSize();  // SIZE
            String columDataType = column.getDATA_TYPE(); // INTEGER, ARRAY, BIT, VARCHAR, NUMERIC
            String nullAble = column.getNullable() ? "NULL" : "NOT NULL";

            String convertedType = ColumnTypeConverter(columnType,columnSize); // conv type e.g VARCHAR(255)

            if(columDataType.equals("ARRAY")){
                // create Array type with columname  + add it to list
                //createArrayType(columnName,columnType);

                // manipulate QueryString
            }else{
                // converte Type
            }
        });
        return null;
    }
    public static void createArrayType(String columnName, String convertedType, String size) {
        try {
            DBConnectionDAO oracle = DBConnectionHandler.getInstance().getConnection("oracle");
            Connection connection = oracle.getConnection();
            Statement stmt = connection.createStatement();

            String ArrayName = columnName +"Array";
            // push arrayname and columnNAme in HAshmap
            generetedSQLArray.put(columnName, ArrayName);

            stmt.execute("CREATE OR REPLACE TYPE " + ArrayName + " AS VARRAY(" + size+") OF "+convertedType+"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public static String ColumnTypeConverter(String columnType, String size ){
        switch (columnType){
            case "int4":
                return "NUMBER("+size+")"; // int4 = Integer = Number(38) unsere size ist bei int4 immer10
            case "varchar":
                return "VARCHAR2("+size+")"; //
            case "numeric":
                return "NUMERIC("+size+",3)"; // 3 lol
            case "bool":
                return "CHAR("+size+")"; //
            case "_int4":
                return "NUMBER(38)";//
            case "_varchar":
                return "VARCHAR2(255)";//
        }
        return null;
     }


}
