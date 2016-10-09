package springContext.dto;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Martha on 7/29/2016.
 */
@Entity
@Table(name = "orders")
public class OrderDTO {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Set<ProductInOrderDTO> products;

    @OneToOne
    private TableDTO table;

    private boolean isActive;

    public OrderDTO() {
    }

    public OrderDTO(Set<ProductInOrderDTO> products, TableDTO table) {
        this.products = products;
        this.table = table;
        this.isActive = false;
    }

    public void open(){
        isActive = true;
    }

    public void close(){
        isActive = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ProductInOrderDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductInOrderDTO> products) {
        this.products = products;
    }

    public TableDTO getTable() {
        return table;
    }

    public void setTable(TableDTO table) {
        this.table = table;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
