package main.bdo.TableSchema;

public class PrimaryKey {

    private String tableName = "";
    private String columnName="";
    private String pkName ="";

    public PrimaryKey(){};

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    @Override
    public String toString() {
        return "PrimaryKey{" +
                "\ttableName='" + tableName + '\'' +
                ", \tcolumnName='" + columnName + '\'' +
                ", \tprimarykeyName='" + pkName + '\'' +
                '}';
    }
}
