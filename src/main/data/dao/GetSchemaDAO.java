package main.data.dao;

import main.bdo.TableSchema.ForeignKey;
import main.bdo.TableSchema.PrimaryKey;
import main.bdo.TableSchema.Table;

import java.util.List;

public interface GetSchemaDAO {
    List<Table> getAlTableInfo();
    List<PrimaryKey> getAllPrimaryKeys(List<Table> tableList);
    List<ForeignKey> getAllForeignKey(List<Table> tableList);
}
