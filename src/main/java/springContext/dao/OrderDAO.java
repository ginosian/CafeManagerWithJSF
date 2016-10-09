package springContext.dao;


import springContext.dto.OrderDTO;
import springContext.dto.ProductInOrderDTO;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface OrderDAO {
    /** Creates new order */
    OrderDTO addOrder(OrderDTO orderDTO);

    /** Finds order with specified Id */
    OrderDTO getOrder(Long orderId);

    /** Updates order with specified Id */
    OrderDTO updateOrder(OrderDTO orderDTO);

    /** Gets all products list within specified order */
    List<ProductInOrderDTO> getOrderProducts(Long orderId);

    /** Creates order-adapted ProductInOrder */
    ProductInOrderDTO addProductToOrder(ProductInOrderDTO productInOrderDTO);

    /** Finds specified order-adapted ProductInOrder from ProductInOrder table*/
    ProductInOrderDTO getProductInOrder(Long productInOrderId);

    /** Updates order-adapted existing ProductInOrder */
    ProductInOrderDTO updateProductToOrder(ProductInOrderDTO productInOrderDTO);

    /** Deletes order-adapted existing ProductInOrder */
    ProductInOrderDTO deleteProductToOrder(ProductInOrderDTO productInOrderDTO);

}
