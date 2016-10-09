package springContext.dao;


import springContext.dto.TableDTO;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface TableDAO {

    /** Creates new Table.*/
    TableDTO addTable(TableDTO table);

    /** Finds specified Table.*/
    TableDTO getTable(Long tableId);

    /** Finds specified Table.*/
    TableDTO getTable(String tableName);

    /** Updates specified Table.*/
    TableDTO updateTable(TableDTO tableDTO);

    /** Gets those Tables which are assigned to specified waiter*/
    List<TableDTO> getTablesByWaiter(Long userId);

    /** Gets all Tables list*/
    List<TableDTO> getAllTables();

}
