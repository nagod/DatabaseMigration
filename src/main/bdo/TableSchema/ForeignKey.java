package main.bdo.TableSchema;

public class ForeignKey {

    private String pkTabelName ="";
    private String pkColumnName ="";
    private String pkName ="";

    private String fkName ="";
    private String fkTablename ="";
    private String fkColumnName ="";

    public ForeignKey(){}

    public String getPkTabelName() {
        return pkTabelName;
    }

    public void setPkTabelName(String pkTabelName) {
        this.pkTabelName = pkTabelName;
    }

    public String getPkColumnName() {
        return pkColumnName;
    }

    public void setPkColumnName(String pkColumnName) {
        this.pkColumnName = pkColumnName;
    }

    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    public String getFkTablename() {
        return fkTablename;
    }

    public void setFkTablename(String fkTablename) {
        this.fkTablename = fkTablename;
    }

    public String getFkColumnName() {
        return fkColumnName;
    }

    public void setFkColumnName(String fkColumnName) {
        this.fkColumnName = fkColumnName;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    @Override
    public String toString() {
        return "ForeignKey{" +
                "\tprimarykeyTabelName='" + pkTabelName + '\'' +
                ",\tprimaykeyColumnName='" + pkColumnName + '\'' +
                ", \tprimarykeyName='" + pkName + '\'' +
                ", \tforeignkeyName='" + fkName + '\'' +
                ", \tforeignTablename='" + fkTablename + '\'' +
                ", \tforeignColumnName='" + fkColumnName + '\'' +
                '}';
    }
}
