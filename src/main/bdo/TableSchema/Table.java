package main.bdo.TableSchema;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private String tableName;
    private String schemaName;
    private List<Column> columnList;

    /*
     Constructor
     */
    public Table(){
        this.columnList = new ArrayList<Column>();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public void addColumn(Column column){
        columnList.add(column);
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", schemaName='" + schemaName + '\'' +
                ", columnList=" + columnList +
                '}';
    }
}
