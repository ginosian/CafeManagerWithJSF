package springContext.service;

import org.springframework.core.env.Environment;
import springContext.dao.OrderDAO;
import springContext.dto.*;
import springContext.exception.NoSuchOrderException;
import springContext.exception.NoSuchProductException;
import springContext.exception.NullOrEmptyArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.Nullable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Martha on 7/31/2016.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    public static boolean defaultsAreSet;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    ProductService productService;

    @Autowired
    TableService tableService;

    @Autowired
    UserService userService;

    @Autowired
    Environment environment;

    @Override
    public OrderDTO createOrder(String tableId) throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTable(tableService.getTable(Long.parseLong(tableId)));
        orderDTO.setIsActive(true);
        return orderDAO.addOrder(orderDTO);
    }

    @Override
    public OrderDTO getOrderById(String orderId) throws Exception {
        if(!isValid(orderId)) throw new NullOrEmptyArgumentsException();

        OrderDTO orderDTO = orderDAO.getOrder(Long.parseLong(orderId));
        if(orderDTO == null) throw new NoSuchOrderException();

        return orderDTO;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) throws Exception {
        if(orderDTO == null) throw new NullOrEmptyArgumentsException();

        OrderDTO order = orderDAO.getOrder(orderDTO.getId());
        if(order == null) throw new NoSuchOrderException();

        return orderDAO.updateOrder(orderDTO);
    }

    @Override
    public ConcurrentHashMap<TableDTO, OrderDTO> getOrdersByWaiter(String waiterId) throws Exception {
        if(!isValid(waiterId)) throw new NullOrEmptyArgumentsException();

        List<TableDTO> assignedTables = tableService.getTablesByWaiter(waiterId);
        ConcurrentHashMap<TableDTO, OrderDTO> tablesMap = new ConcurrentHashMap<TableDTO, OrderDTO>();
        for (int i = 0; i < assignedTables.size(); i++) {
            TableDTO tableDTO = assignedTables.get(i);
            OrderDTO orderDTO = tableDTO.getOrder();
            if(orderDTO != null && orderDTO.isActive()){
                tablesMap.put(tableDTO, orderDTO);
            } else {
                Nullable<OrderDTO> nullOrder = new Nullable<OrderDTO>(new OrderDTO());
                nullOrder.setMessage("No orders for this table");
                tablesMap.put(tableDTO, nullOrder.get());
            }
        }
        return tablesMap;
    }

    @Override
    public ProductInOrderDTO addProductInOrder(String orderId, String productId, int productAmount) throws Exception {
        if(!isValid(orderId) || !isValid(productId) || productAmount <= 0) throw new NullOrEmptyArgumentsException();

        OrderDTO orderDTO = getOrderById(orderId);
        ProductDTO productDTO = productService.getProduct(productId);

        return orderDAO.addProductToOrder(new ProductInOrderDTO(productDTO, orderDTO, productAmount, true));
    }

    @Override
    public ProductInOrderDTO changeProductAmountInOrder(String productId, int updatedAmount) throws Exception {
        if(!isValid(productId) || updatedAmount <= 0)throw new NullOrEmptyArgumentsException();

        ProductInOrderDTO productInOrderDTO = orderDAO.getProductInOrder(Long.parseLong(productId));
        if(productInOrderDTO == null) throw new NoSuchProductException();

        productInOrderDTO.setAmount(updatedAmount);
        return orderDAO.updateProductToOrder(productInOrderDTO);
    }

    @Override
    public void deleteProductInOrder(String productId) throws Exception {
        if(!isValid(productId)) throw new NullOrEmptyArgumentsException();

        Long id = Long.parseLong(productId);
        ProductInOrderDTO productInOrderDTO = orderDAO.getProductInOrder(id);
        if(productInOrderDTO == null) throw new NoSuchProductException();

        orderDAO.deleteProductToOrder(productInOrderDTO);
    }

    private boolean isValid(String argument){
        return !(argument == null || argument.isEmpty());
    }

    @Override
    public void initDefaults(String waiterId) throws Exception {
        if(!defaultsAreSet) {
            String table1name = environment.getProperty("table_name_1");
            String table2name = environment.getProperty("table_name_2");
            String table3name = environment.getProperty("table_name_3");
            String table4name = environment.getProperty("table_name_4");

            tableService.createTable(table1name);
            tableService.createTable(table2name);
            tableService.createTable(table3name);
            tableService.createTable(table4name);

            TableDTO table1 = tableService.getTable(table1name);
            TableDTO table2 = tableService.getTable(table2name);
            TableDTO table3 = tableService.getTable(table3name);
            TableDTO table4 = tableService.getTable(table4name);

            String table1Id = table1.getId().toString();
            String table2Id = table2.getId().toString();
            String table3Id = table3.getId().toString();
            String table4Id = table4.getId().toString();

            tableService.updateTableWIthWaiter(waiterId, table1Id);
            tableService.updateTableWIthWaiter(waiterId, table2Id);
            tableService.updateTableWIthWaiter(waiterId, table3Id);
            tableService.updateTableWIthWaiter(waiterId, table4Id);

            productService.createProduct("Soda");
            productService.createProduct("Dish");
            productService.createProduct("Salad");
            productService.createProduct("Ice Cream");
            productService.createProduct("Coffee");
            productService.createProduct("Desert");
            productService.createProduct("Cocktail");
            productService.createProduct("Tea");

            defaultsAreSet = true;
        }
    }
}
