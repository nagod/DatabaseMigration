package main.application.service.DatabaseInteraction;

import main.application.service.DatabaseInteraction.OracleInteraction.OracleQuery;
import main.bdo.TableSchema.Column;

import java.util.List;

public class Query {

    public static String generateQuery(String databasename, String method,String tabelName,List<Column> columnList){
        switch(method){
            case "create":
                return createTable(databasename,tabelName, columnList);
            case "alter":
                return "sdf";
            case "drop":
                return "sfdf";
        }
        return null;
    }

    /**
     * CREATE TABLE TEST (
     *  id  number NOT NULL,
     */

    public static String createTable(String databasename, String tabelName, List<Column> columnList){
        switch (databasename){
            case "oracle":
                return OracleQuery.createTable(tabelName,columnList);
            case "postgres":
                return null;
        }
        return null;
    }


}
