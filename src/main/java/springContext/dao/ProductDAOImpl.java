package springContext.dao;


import springContext.dto.ProductDTO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO{

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Session session = getSession();
        try{
            session.save(productDTO);
            return productDTO;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from ProductDTO productDTO where productDTO.id = :id");
            query.setParameter("id", productId);
            ProductDTO productDTO = (ProductDTO)query.list().get(0);
            if (query.list().size() < 1) return null;
            return productDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductDTO getProductByName(String productName) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from ProductDTO productDTO where productDTO.name = :name");
            query.setParameter("name", productName);
            ProductDTO productDTO = (ProductDTO)query.list().get(0);
            if (query.list().size() < 1) return null;
            return productDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        Session session = getSession();
        try{
            List<ProductDTO> products = session.createQuery("from ProductDTO productDTO").list();
            if (products.size() < 1) return null;
            return products;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
}
