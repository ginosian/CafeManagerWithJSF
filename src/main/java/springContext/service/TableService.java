package springContext.service;

import springContext.dto.TableDTO;
import springContext.exception.*;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface TableService {

    /**
     * @param tableName given name of table
     * Creates new empty (without waiter and order) Table.
     * @return {@link TableDTO}
     * */
    TableDTO createTable(String tableName);

    /**
     * Finds specified Table.
     * @param tableId String representation of table id.
     * @return {@link TableDTO}
     * @throws NoSuchTableException if table doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO getTable(Long tableId)throws Exception;

    /**
     * Finds specified Table.
     * @param tableName name of table.
     * @return {@link TableDTO}
     * @throws NoSuchTableException if table doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO getTable(String tableName)throws Exception;

    /**
     * Finds all existing tables.
     * @return {@link TableDTO}
     * @throws NoSuchTableException if no table exist.
     * */
    List<TableDTO> getAllTables()throws Exception;

    /**
     * Updates specified Table.
     * @param userId String representation of assignee waiter id.
     * @param tableId String representation of table id.
     * @return {@link TableDTO}
     * @throws NoSuchUserException if user doesn't exist.
     * @throws NoSuchTableException if table doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO updateTableWIthWaiter(String userId, String tableId)throws Exception;

    /**
     * Assignes specified Table with new order and activates order.
     * @param orderId String representation of order id.
     * @return {@link TableDTO}
     * @throws NoSuchOrderException if order doesn't exist. First an order should be created.
     * @throws NoSuchTableException if table doesn't exist.
     * @throws TableHasActiveOrderException Table already has active order so can not accept new one.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO updateTableWIthOrder(String orderId, String tableId)throws Exception;

    /**
     * Deactivates specified Table's order if exists and active.
     * @param tableId String representation of table id.
     * @return {@link TableDTO}
     * @throws NoSuchTableException if table doesn't exist.
     * @throws NoSuchOrderException if order doesn't exist or is inactive.
     * @throws TableHasNoActiveOrderException Table has no active order so can nothing to deactivate.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    TableDTO deactivateTableOrder(String tableId)throws Exception;

    /**
     * Gets those Tables which are assigned to specified waiter. Checks if id is empty or 0 throws EmptyArgumentException.
     * @param userId String representation of assignee waiter id.
     * @return {@link List<TableDTO>}
     * @throws NoSuchTableException if waiter doesnt have tables assigned.
     * @throws NoSuchUserException if user doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    List<TableDTO> getTablesByWaiter(String userId)throws Exception;
}
