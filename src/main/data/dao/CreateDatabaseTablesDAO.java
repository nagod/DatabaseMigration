package main.data.dao;

import main.bdo.TableSchema.ForeignKey;
import main.bdo.TableSchema.PrimaryKey;
import main.bdo.TableSchema.Table;
import java.util.List;

public interface CreateDatabaseTablesDAO {
    List<Table> createTable(List<Table> tableList);
    List<Table> getExistingTables(List<Table> tableList);
    List<PrimaryKey> embedPrimaryKeys(List<PrimaryKey> primaryKeyList);
    List<ForeignKey> embedForeignKeys(List<ForeignKey> foreignKeyList);

    void deleteTables(List<Table> tableList);
    void deletePrimaryKeys(List<PrimaryKey> primaryKeyList);
    void deleteForeignKeys(List<ForeignKey> foreignKeyList);
}
