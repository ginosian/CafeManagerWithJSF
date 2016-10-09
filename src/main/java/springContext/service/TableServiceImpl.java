package springContext.service;

import springContext.dao.TableDAO;
import springContext.dto.OrderDTO;
import springContext.dto.TableDTO;
import springContext.dto.UserDTO;
import springContext.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Service
@Transactional
public class TableServiceImpl implements TableService{

    @Autowired
    TableDAO tableDAO;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Override
    public TableDTO createTable(String tableName) {
        return tableDAO.addTable(new TableDTO(tableName));
    }

    @Override
    public TableDTO getTable(Long tableId) throws Exception {
        if(tableId == null || tableId <= 0) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = tableDAO.getTable(tableId);
        if(tableDTO == null)throw new NoSuchTableException();

        return tableDTO;
    }

    @Override
    public TableDTO getTable(String tableName) throws Exception {
        if(tableName == null || tableName.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = tableDAO.getTable(tableName);
        if(tableDTO == null)throw new NoSuchTableException();

        return tableDTO;
    }

    @Override
    public List<TableDTO> getAllTables() throws NoSuchTableException {
        List<TableDTO> tables = tableDAO.getAllTables();
        if(tables.size() < 1) throw new NoSuchTableException();

        return tables;
    }

    @Override
    public TableDTO updateTableWIthWaiter(String userId, String tableId) throws Exception {
        if(userId == null || userId.isEmpty() || tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = getTable(tableId);
        UserDTO userDTO = userService.getUserById(userId);

        tableDTO.setUser(userDTO);
        tableDAO.updateTable(tableDTO);

        return tableDTO;
    }

    @Override
    public TableDTO updateTableWIthOrder(String orderId, String tableId) throws Exception,
            TableHasActiveOrderException, NullOrEmptyArgumentsException {
        if(orderId == null || orderId.isEmpty() || tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = getTable(tableId);
        if(tableDTO.getOrder().isActive()) throw new TableHasActiveOrderException();

        OrderDTO orderDTO = orderService.getOrderById(orderId);
        if(orderDTO.isActive()) throw new NoSuchOrderException();

        tableDTO.setOrder(orderDTO);
        tableDAO.updateTable(tableDTO);

        orderDTO.setIsActive(true);
        orderService.updateOrder(orderDTO);

        return tableDTO;
    }

    @Override
    public TableDTO deactivateTableOrder(String tableId) throws Exception {
        if(tableId == null || tableId.isEmpty()) throw new NullOrEmptyArgumentsException();

        TableDTO tableDTO = getTable(tableId);

        OrderDTO orderDTO = tableDTO.getOrder();
        if(orderDTO == null || !orderDTO.isActive())throw new TableHasNoActiveOrderException();

        orderDTO.setIsActive(false);
        orderService.updateOrder(orderDTO);

        return tableDTO;
    }

    @Override
    public List<TableDTO> getTablesByWaiter(String userId) throws Exception {
        if(userId == null || userId.isEmpty()) throw new NullOrEmptyArgumentsException();

        UserDTO userDTO = userService.getUserById(userId);

        List<TableDTO> tables = tableDAO.getTablesByWaiter(userDTO.getId());
        if(tables.size() == 0)throw new NoSuchTableException();

        return tables;
    }
}
