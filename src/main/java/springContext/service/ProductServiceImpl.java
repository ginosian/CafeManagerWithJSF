package springContext.service;

import springContext.dao.ProductDAO;
import springContext.dto.ProductDTO;
import springContext.exception.NoSuchProductException;
import springContext.exception.NullOrEmptyArgumentsException;
import springContext.exception.ProductExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Martha on 7/31/2016.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Override
    public ProductDTO createProduct(String productName) throws Exception {
        if(productName == null || productName.isEmpty()) throw new NullOrEmptyArgumentsException();

        if(productDAO.getProductByName(productName) != null) throw new ProductExistException();

        return productDAO.addProduct(new ProductDTO(productName));
    }

    @Override
    public ProductDTO getProduct(String productId) throws Exception {
        if(productId == null || productId.isEmpty()) throw new NullOrEmptyArgumentsException();

        ProductDTO productDTO = productDAO.getProductById(Long.parseLong(productId));
        if(productDTO == null) throw new NoSuchProductException();

        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts() throws Exception {
        List<ProductDTO> products = productDAO.getAllProducts();
        if(products == null) throw new NoSuchProductException();
        return products;
    }
}
