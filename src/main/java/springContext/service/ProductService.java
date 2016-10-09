package springContext.service;

import springContext.dto.ProductDTO;
import springContext.exception.NoSuchProductException;
import springContext.exception.NullOrEmptyArgumentsException;
import springContext.exception.ProductExistException;

import java.util.List;

/**
 * Created by Martha on 7/31/2016.
 */
public interface ProductService {

    /**
     * Creates product.
     * @param productName Name of product.
     * @return {@link ProductDTO}
     * @throws ProductExistException if a product with the same name exists.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    ProductDTO createProduct(String productName) throws Exception;

    /**
     * Finds product by specified Id.
     * @param productId String representation of product id.
     * @return {@link ProductDTO}
     * @throws NoSuchProductException if a product with the same name exists.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    ProductDTO getProduct(String productId) throws Exception;

    /**
     * Finds all existing products.
     * @return {@link List <ProductDTO>}
     * @throws NoSuchProductException if a product with the same name exists.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    List<ProductDTO> getAllProducts() throws Exception;


}
