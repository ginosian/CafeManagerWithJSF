package managedbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springContext.dto.*;
import springContext.service.OrderService;
import springContext.service.ProductService;
import springContext.service.TableService;
import springContext.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Martha on 7/29/2016.
 */
@ManagedBean(name = "waiter")
public class WaiterController  implements Serializable {

    @Autowired
    Environment environment;

    @Autowired
    TableService tableService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @PostConstruct
    private void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).
                getAutowireCapableBeanFactory().
                autowireBean(this);
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        init();
    }

//    @ManagedProperty(value="#{table}")
    private List<TableDTO> tables;
    private List<ProductDTO> products;
    private Set<ProductInOrderDTO> selectedProducts;
    private UserDTO waiter;
    private Long tableId;
    private boolean defalutsAreSet;



    public WaiterController() {
    }

    public void addProductsToOrder () throws Exception {
        TableDTO table = tableService.getTable(tableId);
        OrderDTO order = new OrderDTO(selectedProducts, table);
    }

    public List<TableDTO> getTables() throws Exception {
        UserDTO waiter = userService.getUserByUsername(environment.getProperty("waiter_username"));
        String waiterId = waiter.getId().toString();
        orderService.initDefaults(waiterId);

        this.tables = tableService.getTablesByWaiter(waiter.getId().toString());
        return this.tables;
    }

    public void setTables(List<TableDTO> tables) {
        this.tables = tables;
    }

    public List<ProductDTO> getProducts(){
        List<ProductDTO> products;
        try {
            products = productService.getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("******************************************************************************");
            return null;
        }
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public Set<ProductInOrderDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(Set<ProductInOrderDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
