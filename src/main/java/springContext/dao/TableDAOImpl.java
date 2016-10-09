package springContext.dao;


import springContext.dto.TableDTO;
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
public class TableDAOImpl implements TableDAO {

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public TableDTO addTable(TableDTO table) {
        Session session = getSession();
        try{
            session.save(table);
            return table;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TableDTO getTable(Long tableId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from TableDTO tableDTO where tableDTO.id = :id");
            query.setParameter("id", tableId);
            if (query.list().size() < 1) return null;
            TableDTO tableDTO = (TableDTO)query.list().get(0);
            return tableDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TableDTO getTable(String tableName) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from TableDTO tableDTO where tableDTO.name = :name");
            query.setParameter("name", tableName);
            if (query.list().size() < 1) return null;
            TableDTO tableDTO = (TableDTO)query.list().get(0);
            return tableDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TableDTO updateTable(TableDTO tableDTO) {
        Session session = getSession();
        try{
            session.update(tableDTO);
            return tableDTO;
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TableDTO> getTablesByWaiter(Long userId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from TableDTO t where t.user.id = :id");
            query.setParameter("id", userId);
            List<TableDTO> tables = query.list();
            if (tables.size() < 1) return null;
            return tables;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TableDTO> getAllTables() {
        Session session = getSession();
        try{
            List<TableDTO> tables = session.createQuery("from TableDTO").list();
            if (tables.size() < 1) return null;
            return tables;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }
}
