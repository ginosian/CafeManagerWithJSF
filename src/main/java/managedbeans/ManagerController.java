package managedbeans;


import springContext.dto.ProductDTO;
import springContext.dto.TableDTO;
import springContext.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ModelAndView;
import springContext.service.ProductService;
import springContext.service.TableService;
import springContext.service.UserService;

import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@ManagedBean(name = "manager")
public class ManagerController {

    public static final String WAITERS = "waiters";
    public static final String NO_WAITERS = "no_waiters";
    public static final String TABLES = "tables";
    public static final String NO_TABLES = "no_tables";
    public static final String PRODUCTS = "products";
    public static final String NO_PRODUCTS = "no_products";

    @Autowired
    UserService userService;

    @Autowired
    TableService tableService;

    @Autowired
    ProductService productService;

    @Autowired
    Environment environment;


    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView();
        List<UserDTO> waiters = null;
        List<TableDTO> tables = null;
        List<ProductDTO> products = null;


        return modelAndView;
    }
    public ModelAndView waiterCreationPage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    public ModelAndView createWaiter(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    public ModelAndView productCreationPage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    public ModelAndView createProduct(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    public ModelAndView tableCreationPage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    public ModelAndView createTable(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    public ModelAndView tableDetailPage(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
    public ModelAndView updateTableDetail(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

}
