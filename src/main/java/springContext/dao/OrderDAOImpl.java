package springContext.dao;

import springContext.dto.OrderDTO;
import springContext.dto.ProductInOrderDTO;
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
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        Session session = getSession();
        try{
            session.save(orderDTO);
            return orderDTO;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderDTO getOrder(Long orderId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from OrderDTO orderDTO where orderDTO.pro = :id");
            query.setParameter("id", orderId);
            if (query.list().size() < 1) return null;
            OrderDTO orderDTO = (OrderDTO)query.list().get(0);
            return orderDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Session session = getSession();
        try{
            session.update(orderDTO);
            return orderDTO;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductInOrderDTO> getOrderProducts(Long orderId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("select orderDTO.products from OrderDTO orderDTO where orderDTO.id = :id");
            query.setParameter("id", orderId);
            List<ProductInOrderDTO> orderDTO = query.list();
            if (orderDTO.size() < 1) return null;
            return orderDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductInOrderDTO addProductToOrder(ProductInOrderDTO productInOrderDTO) {
        Session session = getSession();
        try{
            session.save(productInOrderDTO);
            return productInOrderDTO;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductInOrderDTO getProductInOrder(Long productInOrderId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from ProductInOrderDTO productInOrder where productInOrder.id = :id");
            query.setParameter("id", productInOrderId);
            ProductInOrderDTO productInOrderDTO = (ProductInOrderDTO)query.list().get(0);
            if (query.list().size() < 1) return null;
            return productInOrderDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductInOrderDTO updateProductToOrder(ProductInOrderDTO productInOrderDTO) {
        Session session = getSession();
        try{
            session.update(productInOrderDTO);
            return productInOrderDTO;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductInOrderDTO deleteProductToOrder(ProductInOrderDTO productInOrderDTO) {
        Session session = getSession();
        try{
            session.delete(productInOrderDTO);
            return productInOrderDTO;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
