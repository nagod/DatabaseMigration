package main.bdo.TableSchema;

/**
 * TYPE_NAME  varchar
 * COLUMN_SIZE  255
 * DECIMAL_DIGITS  0
 * NUM_PREC_RADIX  10
 * NULLABLE  1
 */

public class Column {

    private String columnName;
    private String columnTypeName;
    private String columnSize;
    private Boolean nullable;   // Default value : FALSE
    private String DATA_TYPE; // SQL TYPE



    public Column() {
        this.columnName = null;
        this.columnTypeName = null;
        this.columnSize = null;
        this.nullable = false;
        this.DATA_TYPE = null;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnTypeName() {
        return columnTypeName;
    }

    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }

    public String getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(String columnSize) {
        this.columnSize = columnSize;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public String getDATA_TYPE() {
        return DATA_TYPE;
    }

    public void setDATA_TYPE(String DATA_TYPE) {
        this.DATA_TYPE = DATA_TYPE;
    }



    @Override
    public String toString() {
        return "Column{" +
                "\tcolumnName='" + columnName + '\'' +
                ", \tcolumnTypeName='" + columnTypeName + '\'' +
                ", \tcolumnSize='" + columnSize + '\'' +
                ", \tnullable=" + nullable +
                ", \tDATA_TYPE='" + DATA_TYPE + '\'' +
                '}';
    }
}
