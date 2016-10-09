package springContext.service;

import springContext.dto.OrderDTO;
import springContext.dto.ProductInOrderDTO;
import springContext.dto.TableDTO;
import springContext.exception.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Martha on 7/31/2016.
 */
public interface OrderService {

    /**
     * Creates order .
     * @return {@link OrderDTO}
     * */
    OrderDTO createOrder(String tableId) throws Exception;

    /**
     * Finds order with specified id.
     * @param orderId String representation of searching order id.
     * @return {@link OrderDTO}
     * @throws NoSuchOrderException if order with specified id doesn't exist or is not active.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    OrderDTO getOrderById(String orderId) throws Exception;

    /**
     * Updates order with specified id.
     * @param orderDTO Updated order object.
     * @return {@link OrderDTO}
     * @throws NoSuchOrderException if order with specified id doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    OrderDTO updateOrder(OrderDTO orderDTO) throws Exception;


    /**
     * Finds and formats a list of tables with assigned active orders filtered by specified waiter.
     * @param waiterId String representation of table id.
     * @return {@link ConcurrentHashMap<TableDTO, OrderDTO>} tables list with assigned orders filtered by specified waiter.
     *                 If table is assigned to a waiter and has no active order as a value for that table
     *                 a custom {@link util.Nullable} object will be kept.
     * @throws NoSuchUserException if waiter with specified id doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    ConcurrentHashMap<TableDTO, OrderDTO> getOrdersByWaiter(String waiterId) throws Exception;

    /**
     * Adds given product to specified order and activates the product.
     * @param orderId String representation of order id.
     * @param productId String representation of adding product id.
     * @param productAmount Amount for specified product..
     * @return {@link ProductInOrderDTO}
     * @throws NoSuchOrderException if order with specified id doesn't exist.
     * @throws NoSuchProductException if product with specified id doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    ProductInOrderDTO addProductInOrder(String orderId, String productId, int productAmount) throws Exception;

    /**
     * Changes ordered product amount within order.
     * @param productId String representation of product id.
     * @param updatedAmount New amount for specified product.
     * @return {@link ProductInOrderDTO}
     * @throws NoSuchProductException if product with specified id doesn't exist or is not active.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    ProductInOrderDTO changeProductAmountInOrder(String productId, int updatedAmount) throws Exception;

    /**
     * Deletes active product in order.
     * @param productId String representation of changing product id.
     * @throws NoSuchOrderException if order with specified id doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    void deleteProductInOrder(String productId) throws Exception;


    void initDefaults(String waiterId) throws Exception;


}
