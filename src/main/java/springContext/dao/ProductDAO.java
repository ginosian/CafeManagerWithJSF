package springContext.dao;


import springContext.dto.ProductDTO;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface ProductDAO {

    /** Creates new product */
    ProductDTO addProduct(ProductDTO productDTO);

    /** Finds product with specified Id */
    ProductDTO getProductById(Long productId);

    /** Finds product with specified Name */
    ProductDTO getProductByName(String productName);

    /** Finds all existing products. */
    List<ProductDTO> getAllProducts();
}
